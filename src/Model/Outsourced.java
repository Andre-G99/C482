package Model;

public class Outsourced extends Part {

    //class variables
    private String companyName;

    //constructor
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
    }

    //class methods

    //setter
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    //getter
    public String getCompanyName() {
        return companyName;
    }
}
