package Model;

public class OneRecord {
    private String product;
    private String count;
    private String price;

    public OneRecord(String name, String surname, String price) {
        this.product = name;
        this.count = surname;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public String getPrice() {
        return price;
    }
}
