package com.project.gil.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * class name    : PaymentHistory
 * description   : 파티 결제이력
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Entity
@Getter @Setter
public class PaymentHistory {

    @Id @GeneratedValue
    private Long historyId; // 결제 히스토리id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCH_ID")
    private Match match; // 매치id

//    @Id
//    private LocalDate paymentDate; // 결제일자

    @Column(nullable = false, columnDefinition = "VARCHAR(2)")
    private String status; // 결제상태(1.결제성공 2.결제실패 3.환불처리)

    @Column(nullable = false, columnDefinition = "VARCHAR(2)")
    private String cardCode; // 카드사코드

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String cardNumber; // 카드번호

    @Column(nullable = false)
    private int paymentPrice; // 결제금액

    @CreatedDate
    @Column(columnDefinition="datetime(6) default now(6)")
    private LocalDateTime paymentAt; // 결제시간
}
