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
}
