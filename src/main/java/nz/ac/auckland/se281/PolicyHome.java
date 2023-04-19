package nz.ac.auckland.se281;

public class PolicyHome extends Policy {

  // Instance fields
  private String address;
  private Boolean rental;

  // Construct the class
  public PolicyHome(int sumInsured, String address, Boolean rental) {
    super(sumInsured);
    this.address = address;
    this.rental = rental;
  }

  // Get attribute address in PolicyHome class outside of this class
  public String getAddress() {
    return address;
  }

  // Calculate the base premium of home
  @Override
  public int getBasePremium(Client aclient) {

    // If home is rented out, the base premium is 2% of the sum insured
    if (rental) {
      return (int) (0.02 * sumInsured);
      // If homw is not rented out, the base premium is 1% of the sum insured
    } else {
      return (int) (0.01 * sumInsured);
    }
  }
}
