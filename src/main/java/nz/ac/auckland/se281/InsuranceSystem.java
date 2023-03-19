package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.PolicyType;
import java.util.ArrayList;

public class InsuranceSystem {
  // Create an array list where all clients profiles are stored
  public ArrayList<Client> listOfClients = new ArrayList<>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    // TODO: Complete this method.
  }

  public void createNewProfile(String userName, String age) {
    
    // Store a typed profile of a client in the array list
    Client c1 = new Client(userName, Integer.valueOf(age));
    listOfClients.add(c1);
    
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
