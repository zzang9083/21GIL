package com.project.gil.constant;

public class CommonConstant {

    // 사용자 상태코드
    public final static String USER_ACTIVE      = "0";		//활동중
    public final static String USER_INACTIVE    = "1";		//비활동


    // 결제이력 상태코드
    public final static String PAYMENT_SUCCESS  = "0";		//결제성공
    public final static String PAYMENT_FAIL     = "1";		//결제실패
    public final static String PAYMENT_REFUND   = "2";	    //결제환불

    // 정산이력 상태코드
    public final static String SETTLE_SUCCESS  = "0";		//정산성공
    public final static String SETTLE_FAIL     = "1";		//정산실패

    //대기 상태코드
    public final static String WAIT_ONGOING   = "0";		//대기중
    public final static String WAIT_COMPLETE   = "1";		//대기완료


    //파티 상태코드
    public final static String PARTY_MATCHING   = "0";		//매칭중
    public final static String PARTY_ACTIVE     = "1";		//활동중
    public final static String PARTY_INACTIVE   = "2";	    //비활동
    public final static String PARTY_REMATCHING = "3";	    //재매칭중

    //파티매칭 상태코드
    public final static String MATCH_ACTIVE     = "0";		//매치활동중
    public final static String MATCH_INACTIVE   = "1";		//매치해지

    //대기열종류
    public final static String NORMAL_QUEUE     = "0";		//일반매칭큐
    public final static String PRIOR_QUEUE      = "1";		//우선순위매칭큐

}
