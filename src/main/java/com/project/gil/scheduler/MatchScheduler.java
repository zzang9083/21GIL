package com.project.gil.scheduler;


import com.project.gil.constant.CommonConstant;
import com.project.gil.domain.*;
import com.project.gil.repository.UserRepository;
import com.project.gil.service.MatchService;
import com.project.gil.service.PartyService;
import com.project.gil.service.UserService;
import com.project.gil.service.WaitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class MatchScheduler {

    @Autowired
    UserService userService;
    @Autowired
    MatchService matchService;
    @Autowired
    PartyService partyService;

    @Autowired
    WaitService waitService;

    @Autowired
    private RedisTemplate<Long, Object> queue;

    @Autowired
    private RedisTemplate<Long, Object> priorQueue;

    /*
     *  method 명   : 파티 자동매칭 스케쥴러
     *  method 설명 : OTT 파티참여 신청을 한 대기열을 읽어 파티 모집중인 파티에 매칭
     */
    @Scheduled(cron="0/20 * * * * ?")
    @Transactional
    public void autoMatchPartyScheduler() {
        // 각 ott 대기열들에 차례로 매칭처리
        for(long i = 1 ; i <= 4 ; i ++) {
            // 대기신청만큼 처리
            long size = queue.opsForList().size(i);
            for(int j = 0 ; j< size ; j++) {
                QueueDTO q = (QueueDTO)queue.opsForList().leftPop(i);

                int count  = q.getCount();
                Wait wait  = q.getWait();

                User user = userService.findOne(wait.getUserId());
                Party party = partyService.findPartyByOttidAndDuration(wait.getOttId(),wait.getDuration());

                /*
                *  파티매치 실패시 -  3회까지는 일반큐에 다시 push / 3회 초과부터는 우선순위큐로 push
                *          성공시 -  매치처리
                * */

                if(party == null) {
                    if(count <=3)
                        queue.opsForList().rightPush(i, new QueueDTO(wait, count+1));
                    else
                        priorQueue.opsForList().rightPush(i, wait);
                }
                else {

                    //매치 처리
                    Match match = new Match();
                    match.setUser(user);
                    match.setParty(party);
                    match.setDuration(wait.getDuration());
                    match.setStatus(CommonConstant.MATCH_ACTIVE);
                    matchService.save(match);

                    wait.setStatus(CommonConstant.WAIT_COMPLETE);
                    waitService.save(wait);

                    //매치완료(정원 4명) 되었을 때, 파티완료 처리
                    List<Match> matchList = matchService.findByParty(party);
                    if(matchList.size() == 4) {
                        party.setStatus(CommonConstant.PARTY_ACTIVE);
                        partyService.save(party);
                        for(Match m : matchList) {
                            m.setStartAt(LocalDate.now());
                            m.setEndAt(LocalDate.now().plusMonths(m.getDuration()));
                            matchService.save(m);
                        }
                    }
                }

            }
        }

    }
}
