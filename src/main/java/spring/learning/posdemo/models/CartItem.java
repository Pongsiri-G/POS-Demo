package spring.learning.posdemo.models;

// DTO
public class CartItem {
    private String barcode;
    private String name;
    private Double price;
    private int qty;
    private int qtyOnHand;
    private Double subTotal;

    public CartItem(String barcode,
                    String name,
                    Double price,
                    int qtyOnHand) {
        this.barcode = barcode;
        this.name = name;
        this.price = price;
        this.qty = 1;
        this.qtyOnHand = qtyOnHand;
        this.subTotal = price * qty;
    }

    public String getBarcode() {
        return barcode;
    }


    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
        this.subTotal = price * qty;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }


    public Double getSubTotal() {
        return subTotal;
    }

}
