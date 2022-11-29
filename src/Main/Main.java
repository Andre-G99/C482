package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        setTestData();
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    public void setTestData(){
        //adds inhouse parts
        InHouse a1 = new InHouse(1, "Part A1", 2.99, 10, 5, 100, 101);
        a1.setMachineID(101);
        InHouse a2 = new InHouse(3,"Part A2", 4.99, 11, 5, 100, 103);
        InHouse b = new InHouse(2, "Part B", 3.99, 9, 5, 100, 102);
        Inventory.addPart(a1);
        Inventory.addPart(b);
        Inventory.addPart(a2);
        Inventory.addPart(new InHouse(4, "Part A3", 5.99, 15, 5, 100, 104));
        Inventory.addPart(new InHouse(5, "Part A4",6.99, 5,5,100,105));

        //adds outsourced parts
        Part o1 = new Outsourced(6, "Part O1", 2.99, 10, 5, 100, "ACME Co.");
        Part p = new Outsourced(7, "Part P", 3.99, 9, 5, 100, "ACME Co.");
        Part q = new Outsourced(8, "Part Q", 2.99, 10, 5, 100, "FLORIDA Co.");
        Inventory.addPart(o1);
        Inventory.addPart(p);
        Inventory.addPart(q);
        Inventory.addPart(new Outsourced(9,"Part R",2.99, 10, 5,100, "FLORIDA Co."));
        Inventory.addPart(new Outsourced(10,"Part O2",2.99, 10, 5,100, "NY Co."));

        //add all products
        Product prod1 = new Product(100, "Product 1", 9.99, 20, 5, 100);
        prod1.addAssociatedPart(a1);
        prod1.addAssociatedPart(o1);
        Inventory.addProduct(prod1);
        Product prod2 = new Product(200, "Product 2", 9.99,29,5,100);
        prod2.addAssociatedPart(a2);
        prod2.addAssociatedPart(p);
        Inventory.addProduct(prod2);

    }


    public static void main(String[] args) {

        launch(args);
    }
}
