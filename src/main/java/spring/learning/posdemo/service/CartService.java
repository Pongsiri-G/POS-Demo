package spring.learning.posdemo.service;

import org.springframework.stereotype.Service;
import spring.learning.posdemo.models.CartItem;

import java.util.Collection;
import java.util.HashMap;

@Service
public class CartService {
    private final HashMap<String, CartItem> cart;

    public CartService() {
        cart = new HashMap<>();
    }

    // เพิ่มสินค้าด้วยการแสกน
    public void scanItem(String barcode, CartItem item){
        // ถ้ามีสินค้าอยู่แล้ว --> +1 ให้กับจำนวนสินค้านั้น
        if (cart.containsKey(barcode)){
            cart.get(barcode).setQty(cart.get(barcode).getQty() + 1);
        }
        // ถ้ายังไม่มีสินค้า --> เพิ่มสินค้าใน Cart
        else{
            cart.put(barcode, item);
        }
    }

    public void addOneQty(CartItem item){
        try{
            item.setQty(item.getQty() + 1);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeOneQty(CartItem item){
        if (item.getQty() > 1){
            item.setQty(item.getQty() - 1);
        }
        else{
            cart.remove(item.getBarcode());
        }
    }

    public double getTotal() {
        return cart.values().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
    public void clearCart(){
        cart.clear();
    }

    public Collection<CartItem> getCart(){
        return cart.values();
    }
}
