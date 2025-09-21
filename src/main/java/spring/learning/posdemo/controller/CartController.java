package spring.learning.posdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.learning.posdemo.models.CartItem;
import spring.learning.posdemo.service.CartService;

import java.util.Collection;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Collection<CartItem> getCart(){
        return cartService.getCart();
    }

    @DeleteMapping
    public ResponseEntity<String> clearCart(){
        cartService.clearCart();
        return ResponseEntity.ok("Cart cleared");
    }

}
