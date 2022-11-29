package Model;

public class InHouse extends Part {
    //class variables
    private int machineID;

    //constructor
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
    }


    //class methods

    //setter
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    //getter
    public int getMachineID() {
        return machineID;
    }
}
