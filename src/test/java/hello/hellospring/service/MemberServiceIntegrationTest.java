package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepsitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
//ctrl+shift+t를 하면 테스트 만들어준다

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired  MemoryMemberRepsitory memberRepsitory;



    /**
     * given,when,then을 쓰면 좋다
     * 테스트는 회원가입이라는 식으로 한글로 써도 된다.
     */
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId= memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member = new Member();
        member.setName("spring");

        Member member1 = new Member();
        member1.setName("spring");
        //when
        Long saveId= memberService.join(member);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        /* 이걸로 검사하기에는 애매하다
        try{
            Long saveId2= memberService.join(member1);
            fail();
        }catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        */

        //then

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}