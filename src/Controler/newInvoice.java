package Controler;


import Model.Client;
import Model.Invoice;
import Model.MathClass;
import Model.OneRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class newInvoice extends MainView{

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox comboBoxNames;
    @FXML
    private ComboBox comboBoxProducts;
    @FXML
    private TextField productsCount;
    @FXML
    private Label priceForAllLabel;
    @FXML
    private TableView table;
    @FXML
    private Label info_client_name;
    @FXML
    private Label info_client_address;
    @FXML
    private Label info_client_PSC;
    @FXML
    private Label info_product_name;
    @FXML
    private Label info_product_info;
    @FXML
    private Label info_product_price;


    private int numberOfProducts = 0;
    private int price = 0;
    private String priceForAllProducts;
    private List<String> productsOrder = new ArrayList<>();
    private List<OneRecord> productsOrder2 = new ArrayList<OneRecord>();
    private List<String> kindOfProducts = new ArrayList<>();
    private MathClass math = new MathClass();
    int wholePrice = 0;


    public void initialize() {

        setInvoiceList();
        comboBoxNames.setItems(database.findAllNames());
        comboBoxProducts.setItems(database.findAllProducts());



        productsCount.textProperty().addListener((obs, oldText, newText) -> {
            try {
                showTotalPrice();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // ...
        });

    }

    /**
     * Create colums in table view, which represents invoice list
     */
    public void setInvoiceList(){


        TableColumn nameColumn = new TableColumn("Prokuct");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("product"));

        TableColumn surnameColumn = new TableColumn("Pocet");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        TableColumn countColumn = new TableColumn("Cena");
        countColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(nameColumn, surnameColumn, countColumn);

    }

    public void showTotalPrice() throws Exception {

        numberOfProducts = Integer.parseInt(productsCount.getText());
        priceForAllProducts = String.valueOf(math.countPrice(numberOfProducts,(String) comboBoxProducts.getValue(), database));
        priceForAllLabel.setText(priceForAllProducts);

    }

    public void addNewProduct()
    {
        OneRecord oneRecord = null;
        boolean hasString = kindOfProducts.contains(comboBoxProducts.getValue());
        if (!hasString){
            kindOfProducts.add((String) comboBoxProducts.getValue());
            oneRecord = new OneRecord((String) comboBoxProducts.getValue(), String.valueOf(numberOfProducts) , priceForAllProducts);

        }
        //if item already exist in the table
        else {

            for (OneRecord item : productsOrder2)
            {
                if (item.getProduct().equals(comboBoxProducts.getValue()))
                {
                    item.setPrice(String.valueOf(Integer.parseInt(item.getPrice()) + Integer.parseInt(priceForAllProducts)));
                    item.setCount(String.valueOf(Integer.parseInt(item.getCount()) + numberOfProducts));
                    break;
                }
            }

        }

        productsOrder2.add(oneRecord);
        ObservableList<OneRecord> items2 = FXCollections.observableArrayList(productsOrder2);

        table.setItems(items2);

        wholePrice += Integer.parseInt(priceForAllLabel.getText());



    }

    public void createNewInvoice() throws Exception {
        int ico = database.getIco();
        String selected_client = (String) comboBoxNames.getValue();
        Client client = null;
        try {
            client = new Client(database.findClientByName(selected_client));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Invoice newInvoice = new Invoice((java.time.LocalDate) date.getValue(), client, productsOrder2, ico,wholePrice);
        database.setIco(database.getIco() + 1);

        super.database.addToInvoices(newInvoice);
        super.stage.close();
    }


}
