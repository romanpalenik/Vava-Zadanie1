package Controler;

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

    Client client;


    public void initialize() {

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

    public void choiceCreateEdit()
    {
        if (mode == 0)
            {
            createNewClient();
            }
        else
            {
            editClient();
            }

    }

    public void createNewClient()
    {
        Client newClient = new Client(name.getText(),address.getText(),PSC.getText(),city.getText());
        database.addToClients(newClient);
        stage.close();
    }

    public void setWindowToEditMode()
    {

        labelClientName.setVisible(true);
        comboBoxNames.setVisible(true);
        comboBoxNames.setItems(database.findAllNames());
    }

    public void fillClientsNames() throws Exception {

        this.client = database.findClientByName((String) comboBoxNames.getValue());
        name.setPromptText(client.getName());
        address.setPromptText(client.getAddress());
        PSC.setPromptText(client.getPSC());
    }

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


}
