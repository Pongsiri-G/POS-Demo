package spring.learning.posdemo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import spring.learning.posdemo.models.CartItem;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartService {
    private final Map<String, CartItem> cart = new HashMap<>();

    public void scanItem(String barcode, CartItem item) {
        if (cart.containsKey(barcode)) {
            addOne(barcode);
        }
        else {
            cart.put(barcode, item);
        }
    }

    public void addOne(String barcode) {
        CartItem item = cart.get(barcode);
        setQty(item, item.getQty() + 1);
    }

    public void removeOne(String barcode) {
        CartItem item = cart.get(barcode);
        setQty(item, item.getQty() - 1);
    }

    public void setQty(CartItem item, int qty){
        if (qty > item.getQtyOnHand()) {
            throw new IllegalArgumentException("Don' have enough stock");
        }

        else if (qty <= 0) {
            cart.remove(item.getBarcode());
        }
        else {
            item.setQty(qty);
        }
    }

    public Collection<CartItem> getCartItems() {
        return cart.values();
    }

    public Double getTotal() {
        return cart.values().stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
    }

    public void clear() {
        cart.clear();
    }
}
