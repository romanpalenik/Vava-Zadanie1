package Controler;

import Model.Database;
import Model.Invoice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView {

    @FXML
    private TextField icoName;
    @FXML
    private Label ico;
    @FXML
    private Label nameOfClient;
    @FXML
    private Label allPrice;


    protected static Stage stage = new Stage();
    protected static Database database = new Database();
    protected static int mode = 0;

    private Invoice invoice;

    /**
     * open new window when you can create new client
     * @throws IOException
     */
    public void openNewWindowWithNewClient() throws IOException {

        mode = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/View/newClient.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Novy zakaznik");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * open new window when you can create new invoice
     * @throws IOException
     */
    public void editClient() throws IOException {

        mode = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/View/newClient.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Upravit Zakaznika");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * open new window when you can create new product
     * @throws IOException
     */
    public void openNewWindowWithNewProduct() throws IOException {

        mode = 0;
        Parent root = FXMLLoader.load(getClass().getResource("/View/newProduct.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Novy produkt");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * open new window when you can create new product
     * @throws IOException
     */
    public void EditProduct() throws IOException {

        mode = 1;
        Parent root = FXMLLoader.load(getClass().getResource("/View/newProduct.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Edit produkt");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * open new window when you can create new invoice
     * @throws IOException
     */
    public void openNewWindowWithNewInvoice() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/NewInvoice.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Nova faktura");
        stage.setScene(scene);
        stage.show();

    }


    /**
     * open new window when you can create new invoice
     * @throws IOException
     */
    public void openNewWindowWithInvoiceSearch() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/showInvoice.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Nova faktura");
        stage.setScene(scene);
        stage.show();

    }
}
