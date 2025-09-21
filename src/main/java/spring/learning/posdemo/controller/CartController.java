package spring.learning.posdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.learning.posdemo.models.CartItem;
import spring.learning.posdemo.service.CartService;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/scan/{barcode}")
    public ResponseEntity<String> scanItem(@PathVariable String barcode,
                                           @RequestBody CartItem item) {
        cartService.scanItem(barcode, item);
        return ResponseEntity.ok("Item added");
    }

    @PostMapping("/add/{barcode}")
    public ResponseEntity<String> addOne(@PathVariable String barcode) {
        cartService.addOne(barcode);
        return ResponseEntity.ok("Item added");
    }

    @PostMapping("/remove/{barcode}")
    public ResponseEntity<String> removeOne(@PathVariable String barcode) {
        cartService.removeOne(barcode);
        return ResponseEntity.ok("Item removed");
    }


    @GetMapping
    public Map<String, Object> getCart() {
        return Map.of(
                "items", cartService.getCartItems(),
                "total", cartService.getTotal()
        );
    }

    @DeleteMapping()
    public ResponseEntity<String> clearCart() {
        cartService.clear();
        return ResponseEntity.ok("Cart cleared");
    }
}
