package nz.ac.auckland.se281;

public class PolicyHome extends Policy{

    // Instance fields
    private String address;
    private Boolean rental;

    // Construct the class
    public PolicyHome(int sumInsured, String address, Boolean rental){
        super(sumInsured);
        this.address = address;
        this.rental = rental;
    }
    
}
