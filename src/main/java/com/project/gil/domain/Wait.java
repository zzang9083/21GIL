package com.project.gil.domain;

import com.project.gil.api.dto.partydto.JoinPartyRequest;
import com.project.gil.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/*
 * class name    : Wait
 * description   : 서비스 대기정보 명세
 * last modified : 2022.08.20 - 필드 데이터 추가(초안)
 *
 * */

@Entity
@Getter @Setter
@NoArgsConstructor
public class Wait {

    @Id
    @GeneratedValue
    private Long id; // 대기 id

    @Column(nullable = false)
    private Long userId; // 사용자 id

    @Column(nullable = false)
    private Long ottId; // ott id

    @Column(nullable = true, columnDefinition = "VARCHAR(2)")
    private String status; // 대기상태(0.대기중 1.대기완료)

    @Column(nullable = false)
    private Long duration; // 지속기간(개월)

    @CreatedDate
    @Column(columnDefinition="datetime(6) default now(6)")
    private LocalDateTime createAt; // 대기시작시간

    public Wait(JoinPartyRequest request) {
        userId = request.getUserId();
        ottId  = request.getOttId();
        status  = CommonConstant.WAIT_ONGOING;
        duration  = request.getDuration();
    }


}
