package com.project.gil.domain;

import com.project.gil.api.dto.partydto.CreatePartyRequest;
import com.project.gil.constant.CommonConstant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * class name    : Party
 * description   : 서비스 파티 정보명세
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Entity
@Getter @Setter
public class Party {

    @Id
    @GeneratedValue
    private Long id; // 파티id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OTT_ID")
    private Ott ott; // ott

    @Column(nullable = false, columnDefinition = "VARCHAR(1)")
    private String status; // 파티상태(0. 매칭중, 1. 파티진행중 2.파티종료 3.파티재매칭중)

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<Match> matchUsers = new ArrayList<Match>(); // 매치리스트

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private List<SettleHistory> settleHistoryList = new ArrayList<SettleHistory>(); // 정산내역

    @Column(nullable = false)
    private Long leaderId; // 파티장id

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String shareId; // 공유아이디

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String sharePassword; // 공유비밀번호

    @Column(nullable = true)
    private LocalDateTime startAt; // 파티시작일

    @Column(nullable = true)
    private String profile_1; // 파티원 프로필_1

    @Column(nullable = true)
    private String profile_2; // 파티원 프로필_2

    @Column(nullable = true)
    private String profile_3; // 파티원 프로필_3

    @Column(nullable = true)
    private String profile_4; // 파티원 프로필_4

    @Column(nullable = true)
    private LocalDateTime createAt; // 파티종료일

    @Column(nullable = false)
    private int paymentDay; // 결제일자(일자)

    @Column(nullable = false)
    private Long duration; // 지속기간(개월)


//    @Column(nullable = false, columnDefinition = "VARCHAR(1)")
//    private String durationCode; // 지속기간코드(1,3,6,12기월

    public Party() {

    }

    public Party(Ott ott, CreatePartyRequest request) {
        this.ott = ott;
        this.leaderId = request.getLeaderId();
        this.shareId = request.getShareId();
        this.sharePassword = request.getSharePassword();
        this.profile_1 = request.getProfile();
        this.duration = request.getDuration();
        this.status = CommonConstant.PARTY_MATCHING;
    }


}
