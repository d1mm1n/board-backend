package com.example.board.application.facade;


import com.example.board.application.dto.request.CreateAccountRequest;
import com.example.board.application.dto.response.CanUseMemberIdResponse;
import com.example.board.domain.member.entity.Member;
import com.example.board.domain.member.service.MemberService;
import com.example.board.global.exception.BaseException;
import com.example.board.global.exception.ErrorCode;
import com.example.board.global.jwt.JwtProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountFacade {

    private final MemberService memberService;
    private  final JwtProvider jwtProvider;
    public CanUseMemberIdResponse canUseMemberId(String memberId){
        //1. 멤버 서지스에서 유저를 memberId로 조회를 한다.
        //2. 존재하면 false 존재하지 않으면 true 반환.

        //존재 하지 않으면 Runtime Exception이 발생
        //존재 하면 그냥 작업이 될것이다.

//        try{
//            Member member = memberService.findByMemberId(memberId);
//            return false;
//        }catch (RuntimeException e){
//            return true;
//        }
        boolean status= memberService.notExistsByMemberId(memberId);
        return new CanUseMemberIdResponse(status);


    }
    public void registerMember(CreateAccountRequest createAccountRequest){
        //1. memberid가 존재하면 예외를 발생 시켜야 함



        String memberId= createAccountRequest.getMemberId();
        if(memberService.existsByMemberId(memberId)){
            throw BaseException.from(ErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = createAccountRequest.toMember();
        memberService.save(member);

    }

    public String loginAccount(String memberId, String password){

        //로그인을 하려면 어떻게 해야 할까???
        //먼저 DB에서 memberId로 조회
        //그 후, 가져온 값에서 password가 일치하는지 확인하면 됨

        Member member=memberService.findByMemberId(memberId); //memberId로 조회를 한다.
        String databasePassword=member.getPassword();


        if(!BCrypt.checkpw(password,databasePassword)){ //비밀번호가 일치하지 않으면
            throw BaseException.from(ErrorCode.INVALID_PASSWORD);
        }
        //여기서 jwt 발급이 필요함
        return jwtProvider.createAccessToken(member.getId());

    }
}
