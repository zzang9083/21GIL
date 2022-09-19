package com.project.gil.api.dto.partydto;


import com.project.gil.domain.Ott;
import lombok.AllArgsConstructor;
import lombok.Data;



/*
 * class name    : CreatePartyRequest
 * description   : 파티 생성 요청 dto
 * last modified : 2022.09.04 - 클래스 및 필드 데이터 추가(초안)
 *
 * */

@Data
@AllArgsConstructor
public class CreatePartyRequest {

    private Long ottId; // ott

    private Long leaderId; // 파티장id

    private String shareId; // 공유아이디

    private String sharePassword; // 공유비밀번호

    private Long duration; // 지속기간(개월)

    private String profile; // 프로필명


}
