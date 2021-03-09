package Model;

public class MathClass {

    public int countPrice(int count, String nameOfProduct, Database database) throws Exception {
        int price = database.findPriceToProduct(nameOfProduct);
        int totalPrice = count * price;
        return totalPrice;
    }
}
