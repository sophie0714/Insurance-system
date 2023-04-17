package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Client {
  // Instance fields
  protected String name;
  protected String age;

  public ArrayList<Policy> listOfPolicies = new ArrayList<>();

  // Construct the class
  public Client(String name, String age) {
    this.name = name;
    this.age = age;
  }

  // Get attribute,name, in a type client outside of this class
  public String getName() {
    return this.name;
  }

  // Get attribute, age, in a type client outside of this class
  public String getAge() {
    return this.age;
  }

  // Add policies for each client to its policy list
  public void addPolicy(Policy policy) {
    listOfPolicies.add(policy);
  }

  // Get policy list outside of this class
  public ArrayList<Policy> getListOfPolicies() {
    return listOfPolicies;
  }
}
