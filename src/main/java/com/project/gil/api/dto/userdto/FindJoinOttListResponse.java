package com.project.gil.api.dto.userdto;

/*
 * class name    : FindJoinOttListResponse
 * description   : 사용자 파티정보 응답 dto
 * last modified : 2022.09.07 - 클래스 및 필드 데이터 추가(초안)
 *
 * */


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindJoinOttListResponse<T> {
    private T data;
}

