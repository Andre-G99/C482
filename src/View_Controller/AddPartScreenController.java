package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddPartScreenController implements Initializable {

    //labels
    @FXML private Label companyName_MachineID_Label;

    //text fields
    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField inventoryTextField;
    @FXML private TextField price_CostTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    @FXML private TextField companyName_MachineID_TextField;

    //buttons
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    //radio buttons
    @FXML private RadioButton inHouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;

    //toggle group
    private ToggleGroup inHouseOrOutsourcedGroup;

    //part properties
    String partID;
    String partName;
    String partInv;
    String partPrice;
    String partMin;
    String partMax;
    String partMachineID;
    String partCompanyName;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set toggle group for radio buttons
        inHouseOrOutsourcedGroup = new ToggleGroup();
        inHouseRadioButton.setToggleGroup(inHouseOrOutsourcedGroup);
        outsourcedRadioButton.setToggleGroup(inHouseOrOutsourcedGroup);

        //set default radio button that is selected
        inHouseOrOutsourcedGroup.selectToggle(inHouseRadioButton);
        radioButtonChanged();

    }

    public void radioButtonChanged(){

        if (this.inHouseOrOutsourcedGroup.getSelectedToggle().equals(this.inHouseRadioButton)) {
            companyName_MachineID_Label.setText("Machine ID        ");
            companyName_MachineID_TextField.setPromptText("Machine ID");
        }

        if (this.inHouseOrOutsourcedGroup.getSelectedToggle().equals(this.outsourcedRadioButton)){
            companyName_MachineID_Label.setText("Company Name");
            companyName_MachineID_TextField.setPromptText("Company Name");
        }

    }

    public void saveButtonWasClicked(ActionEvent event) throws IOException{
         partID = idTextField.getText();
         partName = nameTextField.getText();
         partInv = inventoryTextField.getText();
         partPrice = price_CostTextField.getText();
         partMin = minTextField.getText();
         partMax = maxTextField.getText();
         partMachineID = companyName_MachineID_TextField.getText();
         partCompanyName = companyName_MachineID_TextField.getText();


        //if inhouse radio button is selected save new inhouse part
        if (this.inHouseOrOutsourcedGroup.getSelectedToggle().equals(this.inHouseRadioButton)){

            if(checkForEmptyFields(partID, partName, partInv, partPrice, partMin, partMax, partMachineID) != "valid"){
                    Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
                    emptyAlert.setTitle("Empty Field!");
                    emptyAlert.setContentText(checkForEmptyFields(partID, partName, partInv, partPrice, partMin, partMax, partMachineID));
                    emptyAlert.showAndWait();
            }

            else{

                //creates the new object
                InHouse newInHousePart = new InHouse(Integer.parseInt(partID), partName,
                        Double.parseDouble(partPrice), Integer.parseInt(partInv), Integer.parseInt(partMin),
                        Integer.parseInt(partMax), Integer.parseInt(partMachineID));
                //checks the validity of the input
                if (newInHousePart.validatePart(Integer.parseInt(partID), partName, Double.parseDouble(partPrice), Integer.parseInt(partInv),
                        Integer.parseInt(partMin), Integer.parseInt(partMax)).equals("valid") && Integer.parseInt(partMachineID) > 0) {
                    Inventory.addPart(newInHousePart);                                                                      //if its valid it will add it to the inventory list
                    goToMainScreen(event);
                } else {                                                                                                    //else it will display an error message and wait for GC
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setContentText(newInHousePart.validatePart(Integer.parseInt(partID), partName, Double.parseDouble(partPrice),
                            Integer.parseInt(partInv), Integer.parseInt(partMin), Integer.parseInt(partMax)));
                    alert.showAndWait();
                }
            }
        }

        else{
            if(!checkForEmptyFields(partID, partName, partInv, partPrice, partMin, partMax, partMachineID).equals("valid")){
                Alert emptyAlert = new Alert(Alert.AlertType.ERROR);
                emptyAlert.setTitle("Empty Field!");
                emptyAlert.setContentText(checkForEmptyFields(partID, partName, partInv, partPrice, partMin, partMax, partMachineID));
                emptyAlert.showAndWait();
            }
            else{
                //creates the new object
                Outsourced newOutsourcedPart = new Outsourced(Integer.parseInt(partID), partName,
                        Double.parseDouble(partPrice), Integer.parseInt(partInv), Integer.parseInt(partMin),
                        Integer.parseInt(partMax), partCompanyName);
                //checks the validity of the input
                if (newOutsourcedPart.validatePart(Integer.parseInt(partID), partName, Double.parseDouble(partPrice), Integer.parseInt(partInv),
                        Integer.parseInt(partMin), Integer.parseInt(partMax)).equals("valid") && !partCompanyName.isEmpty()) {
                    Inventory.addPart(newOutsourcedPart);                                                                      //if its valid it will add it to the inventory list
                    goToMainScreen(event);
                } else {                                                                                                    //else it will display an error message and wait for GC
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error! Invalid Input!");
                    errorAlert.setContentText(newOutsourcedPart.validatePart(Integer.parseInt(partID), partName, Double.parseDouble(partPrice),
                            Integer.parseInt(partInv), Integer.parseInt(partMin), Integer.parseInt(partMax)));
                    errorAlert.showAndWait();
                }
            }
        }

    }

    public void cancelButtonClicked(ActionEvent event) throws IOException{
        Alert cancelAlert = new Alert(Alert.AlertType.CONFIRMATION);
        cancelAlert.setTitle("Cancelling Save!");
        cancelAlert.setContentText("Part will not be saved!");

        Optional<ButtonType> result = cancelAlert.showAndWait();
        if(result.get() == ButtonType.OK){
            goToMainScreen(event);
        }
        else{
            cancelAlert.close();
        }
    }

    public String checkForEmptyFields (String id, String name, String inv, String price, String min, String max, String machineID_CompanyName){

        if(id.isEmpty() && name.isEmpty() && inv.isEmpty() && price.isEmpty() && min.isEmpty() && max.isEmpty() && machineID_CompanyName.isEmpty()){
            return "All fields cannot be empty";
        }
          else if (id.isEmpty()){
            return "ID cannot be empty.";
        } else if (name.isEmpty()){
            return "Name cannot be empty.";
        } else if (inv.isEmpty()){
            return "Inventory cannot be empty.";
        } else if (price.isEmpty()){
            return "Price cannot be empty.";
        } else if (min.isEmpty()){
            return "Minimum cannot be empty.";
        } else if (max.isEmpty()){
            return "Maximum cannot be empty.";
        } else if (machineID_CompanyName.isEmpty()) {
            return "MachineID/Company Name cannot be empty.";
        } else{
            return "valid";
        }
    }

    public void goToMainScreen(ActionEvent event) throws IOException{
        //go back to main screen
        Parent mainScreenParent = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        Scene mainScreenScene = new Scene(mainScreenParent);

        //getting the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(mainScreenScene);
        window.show();
    }
}
