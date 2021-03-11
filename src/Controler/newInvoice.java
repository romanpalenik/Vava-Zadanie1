package Controler;


import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    @FXML
    private Label requiredDate;
    @FXML
    private Label chooseClient;
    @FXML
    private Label chooseProduct;


    private int numberOfProducts = 0;
    private int price = 0;
    private String priceForAllProducts;
    private List<String> productsOrder = new ArrayList<>();
    private List<OneRecord> productsOrder2 = new ArrayList<OneRecord>();
    private List<String> kindOfProducts = new ArrayList<>();
    private MathClass math = new MathClass();
    int wholePrice = 0;
    private Client selectedClient = null;
    private Product selectedProduct = null;


    public void initialize() {

        chooseClient.setVisible(false);
        chooseProduct.setVisible(false);
        requiredDate.setVisible(false);
        setInvoiceList();
        comboBoxNames.setItems(database.findAllNames());
        comboBoxProducts.setItems(database.findAllProducts());

        productsCount.setText("0");


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

    /**
     * show price for selected items and setted count
     * @throws Exception if setted count is empty string
     */
    public void showTotalPrice() throws Exception {

        chooseProduct.setVisible(false);

        if (!productsCount.getText().equals("")) {

            numberOfProducts = Integer.parseInt(productsCount.getText());
            try{
                priceForAllProducts = Integer.toString(math.countPrice(numberOfProducts, (String) comboBoxProducts.getValue(), database));
            }
            catch(NullPointerException e)
            {
                chooseProduct.setVisible(true);
            }
            priceForAllLabel.setText(priceForAllProducts);

        }
    }

    /**
     * add new product to invoice table, if there is already product
     * in table, edit that row
     */
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
                    item.setPrice(Integer.toString(Integer.parseInt(item.getPrice()) + Integer.parseInt(priceForAllProducts)));
                    item.setCount(Integer.toString(Integer.parseInt(item.getCount()) + numberOfProducts));
                    break;
                }
            }
        }

        productsOrder2.add(oneRecord);
        ObservableList<OneRecord> items2 = FXCollections.observableArrayList(productsOrder2);

        table.setItems(items2);

        wholePrice += Integer.parseInt(priceForAllLabel.getText());



    }

    /**
     * get client, product, count,price and create new invoice
     * @throws Exception
     */
    public void createNewInvoice() throws Exception {

        chooseClient.setVisible(false);
        chooseProduct.setVisible(false);
        requiredDate.setVisible(false);

        int ico = database.getIco();
        LocalDate localDate = null;
        localDate = date.getValue();
        Date dateOfInvoice;

        boolean isError = false;
        if (selectedClient == null)
        {
            chooseClient.setVisible(true);
            isError = true;
        }
        if (selectedProduct == null)
        {
            chooseProduct.setVisible(true);
            isError = true;
        }
        if (localDate == null) {
            requiredDate.setVisible(true);
            isError = true;
        }
        if (isError){return;}

        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        dateOfInvoice = Date.from(instant);
        Invoice newInvoice = new Invoice(dateOfInvoice, selectedClient, productsOrder2, ico,wholePrice);
        super.database.setIco(super.database.getIco() + 1);

        super.database.addToInvoices(newInvoice);
        super.stage.close();
    }

    public void setClientToSelected() throws Exception {
        String selectedClientName = (String) comboBoxNames.getValue();
        try {
            selectedClient = new Client(database.findClientByName(selectedClientName));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        info_client_name.setText(selectedClient.getName());
        info_client_address.setText(selectedClient.getAddress());
        info_client_PSC.setText(selectedClient.getPSC());
    }

    /**
     * if its change of value in the combo box, change showed information about product
     * and count new price if there is any price
     * @throws Exception
     */
    public void comboBoxProducts() throws Exception {

        if (productsCount.getText() != "")
        {
            showTotalPrice();
        }

        setProductsToSelected();

    }

    /**
     * show product information in product section
     * @throws Exception
     */
    public void setProductsToSelected() throws Exception {
        selectedProduct = database.findProductByName((String) comboBoxProducts.getValue());
        info_product_name.setText(selectedProduct.getName());
        info_product_info.setText(selectedProduct.getInformation());
        info_product_price.setText(Integer.toString(selectedProduct.getPrice()));

    }

}
