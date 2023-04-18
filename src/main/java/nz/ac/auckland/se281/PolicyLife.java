package nz.ac.auckland.se281;

public class PolicyLife extends Policy {

  // Construct the class
  public PolicyLife(int sumInsured) {
    super(sumInsured);
  }

  // Calculate the base premium for life
  @Override
  public int getBasePremium(Client aclient) {
    Double age = Double.valueOf(aclient.getAge());
    return (int) (sumInsured * ((1 + (age / 100)) / 100));
  }
}
