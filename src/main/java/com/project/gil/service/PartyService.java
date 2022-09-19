package com.project.gil.service;

import com.project.gil.constant.CommonConstant;
import com.project.gil.domain.*;
import com.project.gil.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PartyService {

//    @Autowired
//    UserService userService;
//
//    @Autowired
//    PartyRepository partyRepository;
//
//    @Autowired
//    PaymentHistoryRepository paymentHistoryRepository;
//
//    @Autowired
//    SettleHistoryRepository settleHistoryRepository;

    Logger logger = LoggerFactory.getLogger(PartyService.class);

    @Autowired
    public OttRepository ottRepository;

    @Autowired
    public WaitRepository waitRepository;

    @Autowired
    public PartyRepository partyRepository;


    @Autowired
    public UserRepository userRepository;

    @Autowired
    public MatchRepository matchRepository;

    @Autowired
    private RedisTemplate<Long, Object> queue;

    @Autowired
    private RedisTemplate<Long, Object> priorQueue;


    @Transactional
    public Party findPartyByOttidAndDuration(Long ottid,Long duration) {
        Ott ott = ottRepository.findOne(ottid);
        return partyRepository.findByOttidAndDuration(ott, duration);
    }

    @Transactional
    public void save(Party party) {
        partyRepository.save(party);
    }

    @Transactional
    public void createParty(Party party) {

        // 파티 생성
        partyRepository.save(party);

        // 매치 생성
        Match match = new Match();
        User user = userRepository.findOne(party.getLeaderId());
        match.setParty(party);
        match.setUser(user);
        match.setDuration(party.getDuration());
        match.setProfile(party.getProfile_1());
        match.setStatus(CommonConstant.MATCH_ACTIVE);

        matchRepository.save(match);

    }

    /*
     * CASE - NORMAL_QUEUE : 일반적인 파티 참여신청인 경우 대기열
     *        PRIOR_QUEUE  : 프리미엄 결제 OR 재매치인 경우 대기열
     * */

    @Transactional
    public boolean joinParty(Wait wait, String waitType) {
        switch (waitType) {
            case CommonConstant.NORMAL_QUEUE:
                waitRepository.save(wait); // 대기정보 저장

                queue.opsForList().rightPush(wait.getOttId(), new QueueDTO(wait, 0)); // 대기열에 PUSH
                break;
            case CommonConstant.PRIOR_QUEUE:
                //waitRepository.find
                //priorQueue.opsForList().rightPush(wait.getOttId(), wait);
        }
        return true;
    }



    //결제실행
//    public void executePayment() {
//
//        // 1. 금일자 결제 파티 리스트 추출
//        int paymentDay = LocalDate.now().getDayOfMonth();
//        List<Party> partyList = this.findPaymentPartyList(paymentDay, CommonConstant.PARTY_ACTIVE);
//
//        // 2. 파티단위로 매치 파티원 추출
//        for (Party party : partyList) {
//            List<Match> matchUsers = party.getMatchUsers();
//            Ott ott = party.getOtt();
//
//            // 3. 각각의 파티원들에 결제처리 및 결제이력 생성, 카톡 메시지 전송
//            for (Match matchUser : matchUsers) {
//                User user = matchUser.getUser();
//                PaymentHistory his = new PaymentHistory();
//
//                if(PaymentUtil.requestPayment(user.getPayment(), ott))
//                    his.setStatus(CommonConstant.PAYMENT_SUCCESS);
//                else
//                {
//                    his.setStatus(CommonConstant.PAYMENT_FAIL);
//                    matchUser.setStatus(CommonConstant.MATCH_INACTIVE);
//                    party.setStatus(CommonConstant.PARTY_REMATCHING);
//                    // 파티장 파티신청정보 큐에 PUSH
//                }
//                his.setMatch(matchUser);
//                his.setCardCode(user.getPayment().getCardCode());
//                his.setCardNumber(user.getPayment().getCardNumber());
//                his.setPaymentPrice(0); // 결제금액 정의 필요
//
//                paymentHistoryRepository.save(his);
//
//                KakaoUtil.sendPaymentNoticeMessage(ott,user);
//
//            }
//        }
//    }
//
//    //정산실행
//    public void executeSettle() {
//        // 1. 금일자 정산 파티 리스트 추출
//        int paymentDay = LocalDate.now().getDayOfMonth();
//        List<Party> partyList = this.findSettlePartyList(paymentDay, CommonConstant.PARTY_ACTIVE);
//
//        // 2. 파티별로 파티리더를 추출하여 정산처리 및 정산이력 생성, 카톡 메시지 전송
//        for (Party party : partyList) {
//            User leader = userService.findOne(party.getLeaderId())
//                    .orElseThrow(IllegalArgumentException::new);
//            Ott ott = party.getOtt();
//
//            SettleHistory his = new SettleHistory();
//
//            if(SettleUtil.requestSettle(leader.getSettle(), ott))
//                his.setStatus(CommonConstant.SETTLE_SUCCESS);
//            else
//                his.setStatus(CommonConstant.SETTLE_FAIL);
//
//            his.setLeaderId(leader.getId());
//            his.setBankCode(leader.getSettle().getBankCode());
//            his.setAccountNumber(leader.getSettle().getAccountNumber());
//            his.setParty(party);
//            his.setSettlePrice(0);
//
//            settleHistoryRepository.save(his);
//
//            KakaoUtil.sendSettleNoticeMessage(ott,leader);
//        }
//    }
//
//    public List<Party> findPaymentPartyList(int paymentDay, String status) {
//        return partyRepository.findPaymentPartyList(paymentDay, status);
//    }
//
//    public List<Party> findSettlePartyList(int paymentDay, String status) {
//        return partyRepository.findSettlePartyList(paymentDay, status,LocalDate.now());
//    }

}
