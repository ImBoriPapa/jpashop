package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /**
     * save
     * 최초 저장시 데이터베이스에 저장된 값이 없으면 persist 를하고
     * 저장된 값이 있으면 merge
     */
    public void save(Item item){
        if (item.getId() == null) {
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id) { //단건 조회
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { //전체 조회
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
