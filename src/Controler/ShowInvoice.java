package Controler;

import Model.Invoice;
import Model.OneRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;


public class ShowInvoice extends MainView{

    @FXML
    private Label nameOfClient;
    @FXML
    private Label allPrice;
    @FXML
    private Label PSC;
    @FXML
    private Label address;
    @FXML
    private TableView tableViewWithFactures;
    @FXML
    private TableColumn countColumn;


    private Invoice invoice;
    private List<One> productsOrder2 = new ArrayList<One>();

    public void initialize() {


        countColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        creatingNamesOfInvoice();
        ObservableList<One> factures = FXCollections.observableArrayList(productsOrder2);
        tableViewWithFactures.setItems(factures);



    }

    public void creatingNamesOfInvoice()
    {

        List<Invoice> Invoices = database.getInvoice();

        One one = new One();

        for (Invoice invoice : Invoices)
        {

            one.setName(invoice.getClient().getName() + invoice.getDate());
            productsOrder2.add(one);
        }

    }

    public class One
        {
            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

}
