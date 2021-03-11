package Model;

public class MathClass {

    /**
     * Count price of products on invoice
     * @param count of products on invoice
     * @param nameOfProduct name to find how much cost that product
     * @param database database in which program will search
     * @return total price of products
     * @throws Exception
     */
    public int countPrice(int count, String nameOfProduct, Database database) throws Exception {
        int price = database.findPriceToProduct(nameOfProduct);
        int totalPrice = count * price;
        return totalPrice;
    }
}
