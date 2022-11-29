package Model;

public abstract class Part {

    //class variables
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    //constructor
    public Part(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String validatePart(int id, String name, double price, int stock, int min, int max){

        String errorMessage = "default";
        String validMessage = "valid";

        //variables to tell if each field is valid
        boolean idIsValid, nameIsValid, priceIsValid, stockIsValid, minIsValid, maxIsValid;

        //checking id validity
        if (id > 0 ) {
            idIsValid = true;
        } else {
            idIsValid = false;
            errorMessage = "ID must be greater than 0.";
            return errorMessage;
        }

        //checking name validity
        if (!name.isEmpty()) {
            nameIsValid = true;
        } else {
            nameIsValid = false;
            errorMessage = "Name cannot be empty.";
        }

        //checking price validity
        if (price > 0.0) {
            priceIsValid = true;
        } else {
            priceIsValid = false;
            errorMessage = "Price must be greater than 0.";
            return errorMessage;
        }

        if (max > min){
            maxIsValid = true;
        } else {
            maxIsValid = false;
            errorMessage = "Maximum must be greater than minimum.";
            return errorMessage;
        }

        if(min < max){
            minIsValid = true;
        } else {
            minIsValid = false;
            errorMessage = "Minimum must be less than maximum.";
            return errorMessage;
        }

        //checking inventory validity
        if(stock > min && stock <= max && min >= 0){
            stockIsValid = true;
        } else{
            stockIsValid = false;
            errorMessage = "Inventory level must be greater than the min and less than the max.";
            return  errorMessage;
        }


        if (idIsValid && nameIsValid && priceIsValid && stockIsValid && minIsValid && maxIsValid){
            return validMessage;
        }

        else{
            return errorMessage;
        }
    }
}
