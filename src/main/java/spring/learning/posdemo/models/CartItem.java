package spring.learning.posdemo.models;

public class CartItem {
    private String barcode;
    private String name;
    private double price;
    private int qty;
    private int qty_on_hand;
    private double total_price;

    public static CartItem of(String barcode,
                              String name,
                              double price,
                              int qty,
                              int qty_on_hand,
                              double total_price){
        CartItem item = new CartItem();
        item.barcode = barcode;
        item.name = name;
        item.price = price;
        item.qty = qty;
        item.qty_on_hand = qty_on_hand;
        item.total_price = price * qty;
        return item;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        if (qty > qty_on_hand){
            throw new IllegalArgumentException("Not enough stock");
        }
        this.qty = qty;
        this.total_price = price * qty;
    }

    public int getQty_on_hand() {
        return qty_on_hand;
    }

    public double getTotalPrice() {
        return total_price;
    }

}
