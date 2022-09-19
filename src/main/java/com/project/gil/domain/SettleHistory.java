package com.project.gil.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*
 * class name    : SettleHistory
 * description   : 파티 정산이력
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Entity
@Getter @Setter
public class SettleHistory {

    @Id @GeneratedValue
    private Long historyId; // 정산 히스토리id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARTY_ID")
    private Party party; // 파티

//    @Id
//    private LocalDate startAt; // 정산일자

    @Column(nullable = false, columnDefinition = "VARCHAR(2)")
    private String status; // 정산상태(1.정산성공 2.정산실패)

    @Column(nullable = false)
    private Long leaderId; // 파티장ID

    @Column(nullable = false, columnDefinition = "VARCHAR(2)")
    private String bankCode; // 은행코드

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String accountNumber; // 계좌번호

    @Column(nullable = false)
    private int settlePrice; // 정산금액

    @CreatedDate
    @Column(columnDefinition="datetime(6) default now(6)")
    private LocalDateTime settleAt; // 정산시간
}
