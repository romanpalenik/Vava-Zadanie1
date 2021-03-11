package Controler;

import java.util.regex.*;
import Model.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ClientController extends MainView{

    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField PSC;
    @FXML
    private Label labelClientName;
    @FXML
    private ComboBox comboBoxNames;
    @FXML
    private Button actionButton;
    @FXML
    private TextField city;
    @FXML
    private Label nameWarning;
    @FXML
    private Label addressWarning;
    @FXML
    private Label cityWarning;
    @FXML
    private Label PSCWarning;

    private Client client;


    public void initialize() {

        nameWarning.setVisible(false);
        addressWarning.setVisible(false);
        cityWarning.setVisible(false);
        PSCWarning.setVisible(false);

        if (mode == 0) {

            labelClientName.setVisible(false);
            comboBoxNames.setVisible(false);
            actionButton.setText("Vytvorit zakaznika");

        }
        else {
            actionButton.setText("Upravit zakaznika");
            setWindowToEditMode();
        }

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent> (){

            public void handle(ActionEvent actionEvent) {
                try {
                    fillClientsNames();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        comboBoxNames.setOnAction(event);
    }

    /**
     * validate inputs, if there is everything ok
     * choice action by mode of window, edit or create client
     */
    public void choiceCreateEdit()
    {
        nameWarning.setVisible(false);
        addressWarning.setVisible(false);
        cityWarning.setVisible(false);
        PSCWarning.setVisible(false);

        if( !validateName() || !validateAddress() || !validateCity() ||  !validatePSC() )
        {
            return;
        }
        if (mode == 0)
            {
            createNewClient();
            }
        else
            {
            editClient();
            }

    }

    /**
     * create new Client
     */
    public void createNewClient()
    {

        Client newClient = new Client(name.getText(),address.getText(),PSC.getText(),city.getText());
        database.addToClients(newClient);
        stage.close();
    }

    /**
     * set Window to edit mode
     */
    public void setWindowToEditMode()
    {
        labelClientName.setVisible(true);
        comboBoxNames.setVisible(true);
        comboBoxNames.setItems(database.findAllNames());
    }


    /**
     * if is window in edit mode, it fills information od client to the text field as promt texts
     * @throws Exception
     */
    public void fillClientsNames() throws Exception {

        this.client = database.findClientByName((String) comboBoxNames.getValue());
        name.setPromptText(client.getName());
        address.setPromptText(client.getAddress());
        PSC.setPromptText(client.getPSC());
    }

    /**
     * change information about client if it is edited
     */
    public void editClient() {

        if (!name.getText().equals("")){
            this.client.setName(name.getText());
        }

        if (!address.getText().equals(""))
        {
            this.client.setAddress(address.getText());
        }

        if (!PSC.getText().equals("")) {
            this.client.setPSC(PSC.getText());
        }

    }

    /**
     * Validate name parameter
     * show warning if it wrong
     * @return
     */
    public boolean validateName()
    {
        boolean isValid = false;
        if (!name.getText().equals("")) {
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
     * Validate city parameter
     * show warning if it wrong
     * @return
     */
    public boolean validateCity()
    {
        boolean isValid = false;
        if (!city.getText().equals("")) {
            isValid = Pattern.matches( "[A-z]* | [A-z]* [A-z]* | [A-z]* [A-z]* [A-z]* | [A-z]* [A-z]* [A-z]* [A-z]* ",city.getText());
        } else {
            cityWarning.setText("Nezadany parameter");
            cityWarning.setVisible(true);
            return false;
        }
        if(!isValid) {
            cityWarning.setText("Nespravne zadany parameter");
            cityWarning.setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Validate address parameter
     * show warning if it wrong
     * @return
     */
    public boolean validateAddress()
    {
        boolean isValid = false;
        if (!address.getText().equals("")) {
            isValid = Pattern.matches("[A-z]* .*", address.getText());

        } else {
            addressWarning.setText("Nezadany parameter");
            addressWarning.setVisible(true);
            return false;
        }
        if(!isValid) {
            addressWarning.setText("Nespravne zadany parameter");
            addressWarning.setVisible(true);
            return false;
        }
        return false;
    }

    /**
     * Validate PSC parameter
     * show warning if it wrong
     * @return
     */
    public boolean validatePSC()
    {
        boolean isValid = false;
        isValid = Pattern.matches("[0-9]5",PSC.getText());
        PSCWarning.setVisible(true);
        if (!PSC.getText().equals("")) {
            isValid = Pattern.matches("[0-9]5",PSC.getText());
        }
        else {
            PSCWarning.setText("Nezadany parameter");
            PSCWarning.setVisible(true);
            return false;
        }
        if(!isValid) {
            PSCWarning.setText("Nespravne zadany parameter");
            PSCWarning.setVisible(true);
            return false;
        }
        return false;
    }

}
