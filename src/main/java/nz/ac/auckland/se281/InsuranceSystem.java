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

    // Capitalise the first letter and decapitalise the rest of letters
    userName = userName.substring(0,1).toUpperCase() + userName.substring(1).toLowerCase();

    if (userName.length()<3){ 
      // Check if the username is shorter than 3, then print error message
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);

    }else if (Integer.valueOf(age) < 0){
      // Check if the age is not a positive integer, then print error message
      MessageCli.INVALID_AGE.printMessage(age,userName);

    }else{
       // Store a typed profile of a client in the array list
      Client c1 = new Client(userName, Integer.valueOf(age));
      listOfClients.add(c1);
      
      // Print message of successful profile creation
      MessageCli.PROFILE_CREATED.printMessage(userName,age);
    }
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
