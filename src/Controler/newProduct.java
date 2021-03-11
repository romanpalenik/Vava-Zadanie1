package Controler;

import Model.Product;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;


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
    @FXML
    private Label nameWarning;
    @FXML
    private Label infoWarning;
    @FXML
    private Label priceWarning;


    private Product product;

    public void initialize() {

        nameWarning.setVisible(false);
        infoWarning.setVisible(false);
        priceWarning.setVisible(false);


        if (mode == 0) {
            labelProductName.setVisible(false);
            comboBoxProducts.setVisible(false);
        }
        else {
            setWindowToEditMode();
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent> (){

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


    }

    /**
     * validate all parameters and choice action by mode of window, edit or create product
     */
    public void choiceCreateEdit()
    {
        nameWarning.setVisible(false);
        infoWarning.setVisible(false);
        priceWarning.setVisible(false);
        if( !validateName() || !validateInfo() || !validatePrice() )
        {
            return;
        }
        if (mode == 0)
        {
            createNewProduct();
        }
        else
        {
            editProduct();
        }

    }

    /**
     * change information about product if it's edited
     */
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

    /**
     * show edit widget
     */
    private void setWindowToEditMode() {

        labelProductName.setVisible(true);
        comboBoxProducts.setVisible(true);
        comboBoxProducts.setItems(database.findAllProducts());
    }

    /**
     * create new product and save it to database
     */
    public void createNewProduct()
    {
        Product newProduct = new Product(name.getText(), information.getText(),Integer.parseInt(price.getText()));
        database.addToProducts(newProduct);
        stage.close();
    }


    /**
     * if is window in edit mode, it fills information of client to the text field as promt texts
     * @throws Exception
     */
    public void fillClientsNames() throws Exception {

        this.product = database.findProductByName((String) comboBoxProducts.getValue());
        name.setPromptText(product.getName());
        information.setPromptText(product.getInformation());
        price.setPromptText(Integer.toString(product.getPrice()));
    }

    /**
     * Validate name parameter
     * show warning if it wrong
     */
    public boolean validateName()
    {
        boolean isValid = false;
        if (!nameWarning.getText().equals("")) {
            isValid = Pattern.matches("[A-z]* [A-z]*",name.getText());

        } else {
            nameWarning.setText("Nezadany parameter");
            nameWarning.setVisible(true);
            return false;
        }
        if(!isValid) {
            nameWarning.setText("Nespravne zadany parameter");
            nameWarning.setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Validate information parameter
     * show warning if it wrong
     * @return
     */
    public boolean validateInfo()
    {

        if (information.getText().equals("")) {
            infoWarning.setText("Nezadany parameter");
            infoWarning.setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Validate name parameter
     * show warning if it wrong
     * @return
     */
    public boolean validatePrice()
    {
        boolean isValid = false;
        if (!price.getText().equals("")) {
            isValid = Pattern.matches("[0-9]*",price.getText());
        } else {
            priceWarning.setText("Nezadany parameter");
            priceWarning.setVisible(true);
            return false;
        }
        if(!isValid) {
            priceWarning.setText("Nespravne zadany parameter");
            priceWarning.setVisible(true);
            return false;
        }
        return true;
    }
}
