package com.project.gil.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * class name    : Match
 * description   : 파티 매치정보 명세
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Entity
@Getter @Setter
public class Match {

    @Id @GeneratedValue
    private Long id; // 매치id


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARTY_ID")
    private Party party; // 파티

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user; // 사용자

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL)
    private List<PaymentHistory> paymentHistoryList = new ArrayList<PaymentHistory>(); // 결제내역

    @Column(nullable = true, columnDefinition = "VARCHAR(10)")
    private String profile; // 프로필

//    @Column(nullable = false, columnDefinition = "VARCHAR(2)")
//    private String leaderYn; // 리더여부

    @Column(nullable = false, columnDefinition = "VARCHAR(2)")
    private String status; // 매치상태(1. 사용중 2.해지)

    @Column(nullable = false)
    private Long duration; // 지속기간(개월)

    @Column(nullable = true)
    private LocalDate startAt; // 파티시작년월일

    @Column(nullable = true)
    private LocalDate endAt; // 파티종료년월일
}
