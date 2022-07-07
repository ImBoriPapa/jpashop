package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) { // 상품 저장
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId,String name,int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);

        //save 를 호출할 필요가 없다 변경감지로 업데이트

    }

    public List<Item> findItems() { // 상품 조회
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) { //상품 단건 조회
        return itemRepository.findOne(itemId);
    }
}
