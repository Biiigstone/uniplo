package org.barcode.web.cart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Objects;

public class CartDto {
    @NotBlank private String user_id;
    @NotBlank private String item_id;
    @NotBlank private String item_color_code;
    @NotBlank private String item_size_code;
    @NotBlank @PastOrPresent private LocalDate cart_reg_date;
    // 각 코드에 대해 @Pattern 적용 하려면 정확히 그 패턴 알아와서 할 것.

    public CartDto(){}


    // equals와 hashcode 잘 정의해야 함.
    // 어디까지를 같은 상품으로 취급할거냐?
    // 색상이랑 다 같아야 같은 상품으로 취급할거냐?

    // instanceof 비교 vs getClass() 비교 ..
    // 우리 아이템이 어떻게 설계되었는지가 관건이지 않을까
    // 아닌가 정확히 동일 상품 비교여야하니까..
    // 근데 또 id 비교 다 하니까 상관 없을 것 같기도 하고

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartDto cartDto = (CartDto) o;
        return Objects.equals(user_id, cartDto.user_id) && Objects.equals(item_id, cartDto.item_id) && Objects.equals(item_color_code, cartDto.item_color_code) && Objects.equals(item_size_code, cartDto.item_size_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, item_id, item_color_code, item_size_code);
    }

    public String getUser_id() {
        return user_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getItem_color_code() {
        return item_color_code;
    }

    public String getItem_size_code() {
        return item_size_code;
    }

    public LocalDate getCart_reg_date() {
        return cart_reg_date;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setItem_color_code(String item_color_code) {
        this.item_color_code = item_color_code;
    }

    public void setItem_size_code(String item_size_code) {
        this.item_size_code = item_size_code;
    }

    public void setCart_reg_date(LocalDate cart_reg_date) {
        this.cart_reg_date = cart_reg_date;
    }
}
