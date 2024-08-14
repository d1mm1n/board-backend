package com.example.board.global.jwt;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component //스프링이 관리하는 클래스가 되도록
public class JwtProvider {
    //secret랑, time이 필요 -> yml에서 받아옴.

    private final long ACCESS_EXPIRATION_TIME;
    private final SecretKey ACCESS_SECRET_KEY;
    public JwtProvider(

            @Value("${jwt.access.exp-time}") long accessExpirationTime, //yml 파일에서 가져오는거임
            @Value("${jwt.access.secret}")  String accessSecret   )    //yml 파일에서 가져오는거임
    {
    this.ACCESS_EXPIRATION_TIME = accessExpirationTime;

    this.ACCESS_SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
    }

    
    //jwt pk는 memberId로 설정
    public String createAccessToken(long memberId){

        long now= new Date().getTime();
        return  Jwts.builder()
                .subject(memberId+"")//subject 함수는 어떤걸 키로 쓸꺼냐?, 스트링 타입으로 바꿔주기 위해 +"" 붙혀준거임
                .signWith(this.ACCESS_SECRET_KEY)  //signWith함수는 jwt를 뭘로 암호화 시킬꺼냐?
                .issuedAt(new Date())  //언제 생성했는지
                .expiration(new Date(now+this.ACCESS_EXPIRATION_TIME))
                .compact();

    }

}
