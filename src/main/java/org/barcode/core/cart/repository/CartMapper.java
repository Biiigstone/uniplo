package org.barcode.core.cart.repository;

import org.barcode.web.cart.dto.CartDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 기본적인 CRUD.
// 여기서는 뭐다? 어떤 서비스 로직을 담지 않는다~
// 메서드 명명 규칙은 동사+목적어
@Mapper
public interface CartMapper {
    // Create
    void insert(String userID, CartDto dto);
    // Read
    List<CartDto> findCartByUserId(String userId);

    CartDto findItemByUserAndItemDetails(String userId, CartDto dto);


    // Update
    // 서비스단엔, if문 넣어서 같은 상품일경우 수량이 올라가도록 해야할 것 같은데..
    // 그럼 같은 상품일 경우 애플리케이션단에서 인식할 수 있는 무언가를 전달하도록 하면 될 것 같은데...
//    Boolean update(CartDto dto);
    // 같은 상품이냐? true
    // 같은 상품이 아니냐? false
    // => Update는 동일한 상품 추가 시 별도의 창을 출력해서 "이미 장바구니에 담긴 상품입니다. 장바구니로 이동하시겠습니까?" 로 처리하자.
//    그리고 거기서 처리하기로 했으니까 뭐..
    // 따라서 상태 업데이트는 여기선 없다.. 추가되거나 사라지거나



    // Delete
    // 1. 장바구니 전체 없애기
    void deleteAllItemsByUserId(String userId);
    // 2. 장바구니에서 지정한거 하나 없애기
    void deleteItemByUserId(String userId, CartDto cartDto);
}
