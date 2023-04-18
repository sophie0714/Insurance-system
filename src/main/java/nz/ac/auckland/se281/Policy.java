package nz.ac.auckland.se281;

public abstract class Policy extends InsuranceSystem {

  // Instance fields
  protected int sumInsured;

  // Construct the class
  public Policy(int sumInsured) {
    this.sumInsured = sumInsured;
  }

  // Get attribute sumInsured in a type policy outside of this class
  public int getSumInsured() {
    return sumInsured;
  }

  // All policies need to calculate the base premium
  public abstract int getBasePremium(Client loadedClient);

  // All policies may have discounted premium
  public int getDiscountedPremium(Client aclient, Policy policy){
    int basePremium = policy.getBasePremium(aclient);
    if (aclient.getListOfPolicies().size()== 2){
        return (int)(0.9 * basePremium);
    }else if (aclient.getListOfPolicies().size()>2){
        return (int)(0.8 * basePremium);
    }else{
        return basePremium;
    }
  }
  
}
