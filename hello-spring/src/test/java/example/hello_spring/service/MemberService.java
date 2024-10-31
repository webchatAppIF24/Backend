package example.hello_spring.service;

import example.hello_spring.domain.Member;
import example.hello_spring.repository.MemberRepository;
import example.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository= new MemoryMemberRepository();

    public Long join(Member member){
      validateDuplicateMember(member);
       //같은 이름 있는 중복회원방지
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("already exists");
                });
    }

    /*
    * 전체회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
