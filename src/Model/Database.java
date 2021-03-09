package Model;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class Database extends Exception {

    List<Invoice> Invoice = new ArrayList<>();
    List<Client> Clients = new ArrayList<>();
    List<Product> Products = new ArrayList<>();
    private int Ico = 0;



    public Database() {
        Client client = new Client("Roman", "Hlboka 32", "93401", "Levice");
        Product product = new Product("PC", "Jako cosi mam",20);
        Clients.add(client);
        Products.add(product);
    }

    public int getIco() {
        return Ico;
    }

    public void setIco(int ico) {
        Ico = ico;
    }

    public void addToInvoices(Invoice newInvoice)
    {

        Invoice.add(newInvoice);
    }

    public void addToClients(Client newClient)
    {

        Clients.add(newClient);

    }

    public void addToProducts(Product newProduct)
    {

        Products.add(newProduct);

    }

    public ObservableList<String> findAllNames()
    {
        ObservableList<String> clientsNames = FXCollections.observableArrayList();
        for (Client client : Clients) {

            clientsNames.add(client.getName());
        }

        return clientsNames;
        }

    public ObservableList<String> findAllProducts()
    {
        ObservableList<String> ProductsNames = FXCollections.observableArrayList();
        for (Product product : Products) {

            ProductsNames.add(product.getName());
        }

        return ProductsNames;
    }

    public int findPriceToProduct(String nameOfProduct) throws Exception {
        for (Product product : Products) {

            if (product.getName() == nameOfProduct)
            {
                return product.getPrice();

            }

        }

        throw new Exception();

    }

    public Client findClientByName(String name) throws Exception {

        for (Client client : Clients) {

            if (client.getName() == name)
            {
                return client;
            }

        }

        throw new Exception();


    }

    public Product findProductByName(String name) throws Exception {

        for (Product product : Products) {

            if (product.getName() == name)
            {
                return product;
            }

        }

        throw new Exception();


    }

    public Invoice findInvoiceByIco(int ico) throws Exception {

        for (Invoice invoice : Invoice) {

            if (invoice.getIco() == ico)
            {
                return invoice;
            }

        }

        throw new Exception();


    }

    public List<Model.Invoice> getInvoice() {
        return Invoice;
    }
}



