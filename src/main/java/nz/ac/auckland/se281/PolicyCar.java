package nz.ac.auckland.se281;

public class PolicyCar extends Policy{

    // Instance fields
    private String makeAndModel;
    private String licensePlate;
    private Boolean breakdown;

    // Construct the class
    public PolicyCar (int sumInsured, String makeAndModel, String licensePlate, Boolean breakdown){
        super(sumInsured);
        this.makeAndModel = makeAndModel;
        this.licensePlate = licensePlate;
        this.breakdown = breakdown;
    }
    
}
