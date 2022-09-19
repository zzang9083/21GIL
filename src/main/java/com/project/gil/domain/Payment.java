package com.project.gil.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/*
 * class name    : Payment
 * description   : 서비스 사용자 결제정보 명세
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Embeddable
@Getter @Setter
public class Payment {

    @Column(nullable = true, columnDefinition = "VARCHAR(2)")
    private String cardCode; // 카드사코드

    @Column(nullable = true, columnDefinition = "VARCHAR(20)")
    private String cardNumber; // 카드번호
}
