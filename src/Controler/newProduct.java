package Controler;

import Model.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class newProduct extends MainView{

    @FXML
    private TextField name;
    @FXML
    private TextField information;
    @FXML
    private TextField price;
    @FXML
    private Label labelProductName;
    @FXML
    private ComboBox comboBoxProducts;

    Product product;

    public void initialize() {

        if (mode == 0) {
            labelProductName.setVisible(false);
            comboBoxProducts.setVisible(false);
        }
        else {
            setWindowToEditMode();

        }

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent> (){
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    fillClientsNames();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        comboBoxProducts.setOnAction(event);
    }


    public void choiceCreateEdit()
    {
        if (mode == 0)
        {
            createNewProduct();
        }
        else
        {
            editProduct();
        }

    }

    private void editProduct() {
        if (!name.getText().equals("")){
            this.product.setName(name.getText());
        }

        if (!information.getText().equals(""))
        {
            this.product.setInformation(information.getText());
        }

        if (!price.getText().equals("")) {
            this.product.setPrice(Integer.parseInt(price.getText()));
        }


    }

    private void setWindowToEditMode() {

        labelProductName.setVisible(true);
        comboBoxProducts.setVisible(true);
        comboBoxProducts.setItems(database.findAllProducts());
    }

    public void createNewProduct()
    {
        Product newProduct = new Product(name.getText(), information.getText(),Integer.parseInt(price.getText()));
        database.addToProducts(newProduct);
        stage.close();
    }

    public void fillClientsNames() throws Exception {

        this.product = database.findProductByName((String) comboBoxProducts.getValue());
        name.setPromptText(product.getName());
        information.setPromptText(product.getInformation());
        price.setPromptText(String.valueOf(product.getPrice()));
    }


}
