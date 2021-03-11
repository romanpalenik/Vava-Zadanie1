package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {

    private Date date;
    private Client client;
    private List<OneRecord> products = new ArrayList<OneRecord>();
    private int ico = 0;
    private int wholePrice = 0;

    public Invoice(Date date, Client client, List<OneRecord> products, int ico, int wholePrice) {
        this.date = date;
        this.client = client;
        this.products = products;
        this.ico = ico;
        this.wholePrice = wholePrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OneRecord> getProducts() {
        return products;
    }

    public void setProducts(List<OneRecord> products) {
        this.products = products;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }

    public int getWholePrice() {
        return wholePrice;
    }

    public void setWholePrice(int wholePrice) {
        this.wholePrice = wholePrice;
    }
}
