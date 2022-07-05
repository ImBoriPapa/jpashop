package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /**
     *
     * Spring Data JPA 에서는 @PersistenceContext 대신 @Autowired 로
     * EntityManager 를 생성자 주입으로 빈 등록을 할수 있다.
     * 인텔리 제이 오류로 에러표시가 나지만 실행시 문제는 발생하지 않고
     * 롬복 @RequiredArgsConstructor 로 깔금하게 만들수 있다.
     */

    private final EntityManager em;

//    @Autowired //생성자 주입
//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    public void save(Member member) {//저장
        em.persist(member);
    }

    public Member findOne(Long id){ // id로 Member를 찾아서 반환
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){ // Member 전체조회 JPQL의 대상은 테이블이 아닌 객체이다
       return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {//String name 으로 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

    }

}
