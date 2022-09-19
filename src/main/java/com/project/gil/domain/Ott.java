package com.project.gil.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * class name    : Ott
 * description   : 서비스에서 제공하는 Ott 정보명세
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Entity
@Getter @Setter
public class Ott {

    @Id
    @GeneratedValue
    private Long id; // ottId

    @Column(nullable = false, columnDefinition = "VARCHAR(20)")
    private String Name; // ott명

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String url; // url

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    private int perPrice; // 파티원 부담가격


}
