package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//service를 해줘야 컨테이너에서 Memberservice에 대해 안다
public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepsitory();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x 단축키 ctrl+alt+v
        /**
        Optional<Member> result = memberRepository.findByName(member.getName());
        //기존에는 if null optional로 감싸면 ifpresent같은 메서드 가능
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        **/
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
         .ifPresent(m -> {
             throw new IllegalStateException("이미 존재하는 회원입니다");
         });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
