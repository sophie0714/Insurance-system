package nz.ac.auckland.se281;

public abstract class Policy extends InsuranceSystem{

    // Instance fields
    protected int sumInsured;
    
    // Construct the class
    public Policy (int sumInsured){
        this.sumInsured = sumInsured;
    }
    
    // All policies need to calculate the base premium
    public abstract int getBasePremium(Client loadedClient);
}
