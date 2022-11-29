package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;

public class MainScreenController implements Initializable {

    //exit button
    @FXML private Button exitButton;


    //search function ui
    @FXML private Button searchPartButton;
    @FXML private Button searchProductButton;
    @FXML private TextField searchPartTextField;
    @FXML private TextField searchProductTextField;

    //add,delete, modify buttons for both part and product
    @FXML private Button addPartButton;
    @FXML private Button addProductButton;
    @FXML private Button modifyPartButton;
    @FXML private Button modifyProductButton;
    @FXML private Button deletePartButton;
    @FXML private Button deleteProductButton;

    //table views
    @FXML private TableView <Part> partsTableView;
    @FXML private TableView <Product> productsTableView;

    //parts table columns
    @FXML private TableColumn<Part, Integer> partIDColumn;
    @FXML private TableColumn<Part, String> partNameColumn;
    @FXML private TableColumn<Part, Integer> partInventoryColumn;
    @FXML private TableColumn<Part, Double> partPriceColumn;

    //product table columns
    @FXML private TableColumn<Product, Integer> productIDColumn;
    @FXML private TableColumn<Product, String> productNameColumn;
    @FXML private TableColumn<Product, Integer> productInventoryColumn;
    @FXML private TableColumn<Product, Double> productPriceColumn;

    //other variables
    Part modifyPart;
    static int modifyPartIndex;

    //this method returns the index of the selected item in the parts table view
    public static int getModifyPartIndex(){
        return modifyPartIndex;
    }

    //this method exits the application
    public void exitApplication(ActionEvent event){
        Platform.exit();
    }

    public void userClickedOnPartsTable(){
        this.modifyPartButton.setDisable(false);
        this.deletePartButton.setDisable(false);
    }

    public void userClickedOnProductsTable(){
        this.modifyProductButton.setDisable(false);
        this.deleteProductButton.setDisable(false);
    }

    //changes scene to add part screen
    public void changeToAddPartScreen(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/AddPartScreen.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        //get the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(addPartScene);
            window.show();
    }

    public void changeToModifyPartScreen(ActionEvent event) throws  IOException{

        modifyPart = partsTableView.getSelectionModel().getSelectedItem();
        modifyPartIndex = Inventory.getAllParts().indexOf(modifyPart);

        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("/View_Controller/ModifyPartScreen.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);

        //get the stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(modifyPartScene);
            window.show();
    }

    public void deletePartButtonPushed() {
        Part part = partsTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(part);
        refreshPartsTable();
    }

    public void deleteProductButton(){
        Product product = productsTableView.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(product);
    }

    public void refreshPartsTable(){
        partsTableView.setItems(getAllParts());
    }

    public void refreshProductTable(){productsTableView.setItems(getAllProducts());}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //setup columns for parts table
        partIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));

        //setup columns for products table
        productIDColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));

        //setting test data and refreshing table view
        partsTableView.setItems(Inventory.getAllParts());
        refreshPartsTable();
        refreshProductTable();

        //allows the tables to have multiple rows selected at once
        partsTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        productsTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //set the delete and modify buttons to disabled until a row is selected
        modifyPartButton.setDisable(true);
        deletePartButton.setDisable(true);
        modifyProductButton.setDisable(true);
        deleteProductButton.setDisable(true);
    }

}
