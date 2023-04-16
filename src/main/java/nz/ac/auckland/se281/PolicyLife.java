package nz.ac.auckland.se281;

public class PolicyLife extends Policy{
    
    // Construct the class
    public PolicyLife( int sumInsured){
        super(sumInsured);
    }

    // Calculate the base premium for life
    @Override
    public int getBasePremium( Client loadedClient){
        return ((1+Integer.valueOf(loadedClient.getAge())/100)/100) * sumInsured;
    }
    
}
