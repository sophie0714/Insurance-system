package nz.ac.auckland.se281;

public class PolicyCar extends Policy {

  // Instance fields
  private String makeAndModel;
  private String licensePlate;
  private Boolean breakdown;

  // Construct the class
  public PolicyCar(int sumInsured, String makeAndModel, String licensePlate, Boolean breakdown) {
    super(sumInsured);
    this.makeAndModel = makeAndModel;
    this.licensePlate = licensePlate;
    this.breakdown = breakdown;
  }

  // Get attribute makeAndModel in a type PolicyCar outside of this class
  public String getMakeAndModel() {
    return makeAndModel;
  }

  // Calculate the base premium for car
  @Override
  public int getBasePremium(Client aclient) {
    int basePremium;
    // If the client is youger than 25, the base premium is 15% of the sum insured
    if (Integer.valueOf(aclient.getAge()) < 25) {
      basePremium = (int) (0.15 * sumInsured);
      // If the client is older or equals to 25, the base premium is 10% of the sum insured
    } else {
      basePremium = (int) (0.1 * sumInsured);
    }
    // If the car has a breakdown, extra charge of $80 is added to the base premium
    if (breakdown == true) {
      basePremium = 80 + basePremium;
    }

    // Return the final base premium calculated
    return basePremium;
  }
}
