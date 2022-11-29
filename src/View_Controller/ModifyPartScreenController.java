package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static View_Controller.MainScreenController.getModifyPartIndex;


public class ModifyPartScreenController implements Initializable {

    //radio buttons
    @FXML private RadioButton inHouseRadioButton;
    @FXML private RadioButton outsourcedRadioButton;

    //textfields
    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField inventoryTextField;
    @FXML private TextField priceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
    @FXML private TextField companyName_MachineID_TextField;

    //buttons
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    //label
    @FXML private Label companyName_MachineID_Label;

    //other variables
    int partIndex = getModifyPartIndex();

    private ToggleGroup sourceToggleGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set toggle group for radio buttons
        sourceToggleGroup = new ToggleGroup();
        inHouseRadioButton.setToggleGroup(sourceToggleGroup);
        outsourcedRadioButton.setToggleGroup(sourceToggleGroup);

        //set default radio button that is selected
        sourceToggleGroup.selectToggle(inHouseRadioButton);
        radioButtonChanged();

        //loads data that was filled out for the part that is being modified
        loadPartData(Inventory.getAllParts().get(partIndex));
    }

    public void radioButtonChanged(){
        if (this.sourceToggleGroup.getSelectedToggle().equals(this.inHouseRadioButton)) {
            companyName_MachineID_Label.setText("Machine ID        ");
            companyName_MachineID_TextField.setPromptText("Machine ID");
        }

        if (this.sourceToggleGroup.getSelectedToggle().equals(this.outsourcedRadioButton)){
            companyName_MachineID_Label.setText("Company Name");
            companyName_MachineID_TextField.setPromptText("Company Name");
        }
    }

    public void saveButtonWasClicked(ActionEvent event) throws IOException {

    }

    public void loadPartData(Part partBeingModified){

        if (partBeingModified instanceof InHouse){
            sourceToggleGroup.selectToggle(inHouseRadioButton);
            companyName_MachineID_TextField.setText(Integer.toString(((InHouse)partBeingModified).getMachineID()));
            radioButtonChanged();
        }

        else if (partBeingModified instanceof Outsourced) {
            sourceToggleGroup.selectToggle(outsourcedRadioButton);
            companyName_MachineID_TextField.setText(((Outsourced) partBeingModified).getCompanyName());
            radioButtonChanged();
        }

        String partId = Integer.toString(partBeingModified.getId());
        idTextField.setText(partId);
        nameTextField.setText(partBeingModified.getName());

    }

}
