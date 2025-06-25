package org.barcode.core.cart.service;

import org.barcode.core.cart.repository.CartMapper;
import org.barcode.web.cart.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/*
자.. 워크 플로우부터 그려보자
이 워크 플로우가 결국 서비스가 되는거니까.

1. 사용자가 상품 페이지들을 보다가 장바구니에 담기 아이콘을 누른다.
=> 유저 아이디와 그 상품의 정보와 함꼐 insert 한다.
=> 동일한 아이템인 경우? 이미 장바구니에 담긴 아이템입니다. 장바구니 페이지로 이동하시겠습니까?

2. 수량 변경은 장바구니에서 처리 x

3. 장바구니 항목 삭제
사용자가 이건 이제 관심 없어 하고 체크하고.. 체크는 컨트롤러단에서 해결하겠지 뭐
여튼 지정된 상품을 장바구니에서 삭제
삭제 기준은 정확해야함..

4. 장바구니 일괄 삭제
이건 그냥 현재 userId 조회해서 일괄 삭제 떄리면 되지 뭐


 */


@Service
public class CartService {

    private final CartMapper cartMapper;


    @Autowired
    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }


    // 자동주입같은건 좀 나중에 생각해보고..
    // 일단은 void로 하되, 그 아이템 정보를 리턴하는 것도 고려해보자.
    // 중복처리를 위해 boolean으로 변경
    // 중복되어서 실패 => false 반환
    public boolean addItem(String userId, CartDto cartDto){
        // 장바구니에 아이템 추가하는 메서드.
        //1 . 중복 검사
        CartDto item = cartMapper.findItemByUserAndItemDetails(userId, cartDto);
        if (item != null){ // 이거만 하면 되지! 조회해봤는데 없으면 null 반환이니까!
            // 여기서 수량 처리를 하고싶은데, 수량이 어떻게 처리되는 지 모르겠다..
            // 중복 수 수량 처리 필요할 경우 여기서 수정할 것.
            return false;
        }
        // 중복 아니면~
        cartMapper.insert(userId, cartDto);
        return true;
    }

    public void deleteItem(String userId, CartDto cartDto){
        // 이거 예외도 복잡하게..?
        // 그냥 일단 구현만 하자.
        // 1. 존재하는지 조회
        // 2. 잘 존재하면 그거 삭제
        CartDto item = cartMapper.findItemByUserAndItemDetails(userId, cartDto);
        if (item != null){
            cartMapper.deleteItemByUserId(userId, cartDto);
        } else{
            throw new RuntimeException("존재하지 않는 아이템 삭제 시도");
        }

    }

    // 일괄 조회 : 페이지 들어갔을 때
    // 결과 0개면 빈 리스트 반환.
    public List<CartDto> getItems(String userId){
        return cartMapper.findCartByUserId(userId);
    }

    // 일괄 삭제
    public void deleteAllItems(String userId){
        cartMapper.deleteAllItemsByUserId(userId);
    }

}
