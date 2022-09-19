package com.project.gil.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * class name    : User
 * description   : 서비스 사용자 정보명세
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Entity
@Getter @Setter
@Table(name = "USERS")
public class User {

    @Id
    private Long id; // 사용자id

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String account; // 사용자 계정

    @Column(nullable = false, columnDefinition = "VARCHAR(1)")
    private String status; // 사용자 상태

    @CreatedDate
    @Column(columnDefinition="datetime(6) default now(6)")
    private LocalDateTime createAt; // 가입시간

    @Embedded
    private Payment payment; // 결제정보

    @Embedded
    private Settle settle; // 정산정보

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Match> matchList = new ArrayList<Match>(); // 파티매치리스트


//    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
//    private String contact; // 연락처

}
