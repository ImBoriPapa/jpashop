package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) { //주문 저장
        em.persist(order);
    }

    public Order findOne(Long id) {//단건 조회
        return em.find(Order.class,id);
    }

//    public List<Order> findAll(OrderSearch orderSearch){}
}
