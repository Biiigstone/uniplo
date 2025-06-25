package org.barcode.web.cart.controller;

import org.barcode.web.cart.dto.CartDto;
import org.barcode.core.cart.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {


    private final CartService cartService;



    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/itemList")
    public String showItemList(@RequestParam String userId, Model model){
        List<CartDto> items = cartService.getItems(userId); //
//        model.addAttribute("userId", userId);
        model.addAttribute("items", items); // model.addAttribute("items",// cartService.getItems(userId)>
        return "cart/itemList";
    }


    @PostMapping("/itemAdd")
    public String itemAdd(@ModelAttribute @Valid CartDto dto, @RequestParam String userId, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        // modelAttribute로 보내서 알아서 매핑하도록 할거야.
        // 정상 처리 => 별도 페이지 열고, 상품 추가, 그리고 닫히기 => 그냥 새로고침 해버리기
        
        // 입력 값에 문제가 있는 경우, errPage로 이동.
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "유효하지 않은 입력입니다.");
            return "redirect:/product/itemList";
        }

        boolean result = cartService.addItem(userId, dto);
        // false인 경우, 이미 담긴 상품임 =>
        if (!result){
            redirectAttributes.addFlashAttribute("message", "이미 장바구니에 담긴 상품입니다.");
            return "redirect:/product/itemList";
        }
        redirectAttributes.addFlashAttribute("message", "장바구니에 담았습니다.");
        return "redirect:/product/itemList";
    }

    @PostMapping("/itemRemove")
    public String itemRemove(@ModelAttribute @Valid CartDto dto, @RequestParam String userId, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "유효하지 않은 접근입니다.");
            return "redirect:/cart/itemList?userId=" + userId; // 문제 있으면 다시 유저 장바구니 페이지로
        }
        try{
            cartService.deleteItem(userId, dto);
            redirectAttributes.addFlashAttribute("message", "장바구니 항목이 삭제되었습니다.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/cart/itemList?userId=" + userId;
        }

        return  "redirect:/cart/itemList?userId=" + userId;
    }



    @PostMapping("/clear")
    public String clearCart(@RequestParam String userId) {
        cartService.deleteAllItems(userId);
        return "redirect:/cart/itemList?userId=" + userId;
    }


}
