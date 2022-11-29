package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {


    //class variables
    private final static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private final static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    //class methods

    //add methods
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    //lookup methods
    public static Part lookupPart(int partID){ return null; }

    public static Product lookupProduct(int productID){
        return null;
    }

    public static ObservableList<Part> lookupPart(String name){
        return allParts;
    }

    public static ObservableList<Product> lookupProduct(String name){
        return allProducts;
    }

    //update methods
    public static  void updatePart(int index, Part selectedPart){

    }

    public static void updateProduct(int index, Product newProduct){

    }

    //delete methods
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }

    public static boolean deleteProduct(Product selectedProduct){ return allProducts.remove(selectedProduct); }

    //getters
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
