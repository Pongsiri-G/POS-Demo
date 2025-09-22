package spring.learning.posdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.learning.posdemo.models.CartItem;
import spring.learning.posdemo.service.CartService;

@Controller
public class PosController {
    private final CartService cartService;

    public PosController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/pos")
    public String posPage(Model model) {
        model.addAttribute("items", cartService.getCartItems());  // เป็น Collection<CartItem>
        model.addAttribute("total", cartService.getTotal());
        return "pos";
    }


    @PostMapping("/pos/scan")
    public String scanItemFromForm(@RequestParam String barcode,
                                   @RequestParam String name,
                                   @RequestParam Double price,
                                   @RequestParam Integer qtyOnHand) {
        CartItem item = new CartItem(barcode, name, price, qtyOnHand);
        cartService.scanItem(barcode, item);
        return "redirect:/pos";
    }

    @PostMapping("/pos/add/{barcode}")
    public String addOne(@PathVariable String barcode) {
        cartService.addOne(barcode);
        return "redirect:/pos";
    }

    @PostMapping("/pos/remove/{barcode}")
    public String removeOne(@PathVariable String barcode) {
        cartService.removeOne(barcode);
        return "redirect:/pos";
    }

    @PostMapping("/pos/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/pos";
    }
}
