package Controler;

import Model.Client;
import Model.Invoice;
import Model.OneRecord;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
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
    @FXML
    private TableColumn idInvocie;
    @FXML
    private TableView table;
    @FXML
    private Label city;

    private Client client;
    private Invoice invoice;
    private List<OneInvoiceInTable> productsOrder2 = new ArrayList<OneInvoiceInTable>();

    public void initialize() {


        countColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn nameColumn = new TableColumn("Product");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("product"));

        TableColumn surnameColumn = new TableColumn("Pocet");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        TableColumn countColumn = new TableColumn("Cena");
        countColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(nameColumn, surnameColumn, countColumn);

        creatingNamesOfInvoice();
        ObservableList<OneInvoiceInTable> factures = FXCollections.observableArrayList(productsOrder2);
        tableViewWithFactures.setItems(factures);

        tableViewWithFactures.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = tableViewWithFactures.getSelectionModel().getSelectedCells();


        selectedCells.addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change c) {
                try {
                    showInfoToInvoice();
                    showClientInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * show information about selected invoice
     * @throws Exception
     */
    public void showInfoToInvoice() throws Exception {

        OneInvoiceInTable one = (OneInvoiceInTable) tableViewWithFactures.getSelectionModel().getSelectedItem();

        ObservableList<OneRecord> items2 = FXCollections.observableArrayList(database.findInvoiceByIco(one.getId()).getProducts());
        table.setItems(items2);
        allPrice.setText(Integer.toString(database.findInvoiceByIco(one.getId()).getWholePrice()));



    }

    /**
     * show client's information in centre of window
     */
    public void showClientInfo()
    {
        nameOfClient.setText(client.getName());
        city.setText(client.getCity());
        PSC.setText(client.getPSC());
        address.setText(client.getAddress());
    }

    /**
     * load invoices and fill table with them
     */
    public void creatingNamesOfInvoice()
    {

        List<Invoice> Invoices = database.getInvoice();

        for (Invoice invoice : Invoices)
        {
            OneInvoiceInTable oneInvoiceInTable = new OneInvoiceInTable();
            String[] arrOfStr = invoice.getClient().getName().split(" ");
            client = invoice.getClient();

            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            oneInvoiceInTable.setName( arrOfStr[1]+ " " + simpleDateFormat.format(invoice.getDate()));
            oneInvoiceInTable.setId(invoice.getIco());

            productsOrder2.add(oneInvoiceInTable);
        }

    }

    public class OneInvoiceInTable
        {
            private String name;
            private int id;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

}
