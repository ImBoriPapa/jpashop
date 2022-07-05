package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @Transactional 단순한 조회 기능에서는 속성값을 readOnly = true 가 성능에 이점이있다.
     * 쓰기 기능에서는 사용 하지 않는다.
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     *  회원 이름으로 검색해서 중복회원 검증
     *  중복된 회원이면 예외 발생
     */
    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }
    //회원 전체 조회
    public List<Member> findMembers(){ //전체 조회
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){ //단건 조회
        return memberRepository.findOne(memberId);
    }
}
