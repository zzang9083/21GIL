//package com.project.gil.scheduler;
//
//
///*
// * class name    : PaymentScheduler
// * description   : 결제 스케쥴러
// * last modified : 2022.08.24 - 매일 정오에 도는 결제 스케쥴러 신규
// *
// * */
//
//import com.project.gil.service.PartyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//@EnableScheduling
//public class PaymentScheduler {
//
//    @Autowired
//    PartyService partyService;
//
//
//    // 결제스케쥴러
//    //cron = "0  0/10  *  *  *"
//    @Scheduled(cron="0 0 12 * * *")
//    public void cronPaymentScheduler() {
//
//        // 1. 결제처리대상 party(파티상태: 파티진행 중, 파티시작일 != today, 결제일 = 오늘일자) 리스트 받아오기
//        // 2. 파티에 매칭되어있는 파티원들 정보 받아오기
//
//        // 3.1 파티원별 결제처리
//        // 3.2 결제 성공 시, 결제히스토리 INSERT(결제상태 - 성공), 카톡 알림
//        //     결제 실패 시, 결제히스토리 INSERT(결제상태 - 실패), 카톡 알림
//        // 3.3 파티원이 결제 실패 되었을 때, 파티 상태(4.파티재매칭중) / 해당 파티원 상태만 (2.해지) 업데이트
//        // 3.4 재매칭중인 파티의 파티장의 파티신청 정보를 우선순위 큐에 push
//
//    }
//
//}
