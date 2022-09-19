package com.project.gil.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class SettleScheduler {


    // 정산 스케쥴러
    @Scheduled(cron="0 0 14 * * *")
    public void cronSettleScheduler() {

        // 1.  금일 정산처리대상(파티상태: 파티진행 중, 파티시작일 != today, 결제일 = 오늘일자) party, 파티장 리스트 받아오기
        // 2.  파티장에게 정산금액 이체처리
        // 3.1 이체 성공 시, 정산히스토리 INSERT(정산상태 - 성공), 카톡 알림
        // 3.2 이체 실패 시, 정산히스토리 INSERT(정산상태 - 실패), 카톡 알림
        // ? 이체 실패한 케이스 같은 경우에는 카톡으로 올바른 이체계좌 변경하고 알려달라하고
        //   처리됐을 때 별도 이체처리가 필요할 듯함.
    }
}
