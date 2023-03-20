package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Create an array list where all clients profiles are stored
  public ArrayList<Client> listOfClients = new ArrayList<>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {

    // When there is no clients in the listOfClients
    if (listOfClients.size() == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");

      // When there is only one client's profile in the listOfClients
    } else if (listOfClients.size() == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");

      // When there are more than two clients' profiles in the listOfClients
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(
          Integer.toString(listOfClients.size()), "s", ":");
    }

    // Print all existing clients' profiles in database
    for (int i = 0; i < listOfClients.size(); i++) {
      Client aclient = listOfClients.get(i);
      String aClientName = aclient.getName();
      String aClientAge = aclient.getAge();
      System.out.print((i + 1) + ": ");
      System.out.println(aClientName + ", " + aClientAge);
    }
  }

  public void createNewProfile(String userName, String age) {

    // Capitalise the first letter and decapitalise the rest of letters
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // Determine if a name is unique or not
    // Initialise alreadyExist to false
    boolean alreadyExist = false;

    // Check if newly typed name is alread in the database
    for (int i = 0; i < listOfClients.size(); i++) {
      Client aclient = listOfClients.get(i);
      String aClientName = aclient.getName();

      // If exist, alreadyExist becomes true, meaning the name is not unique
      if (userName.equals(aClientName)) {
        alreadyExist = true;
      }
    }

    // Determine if age is an integer or not
    // Initialise isInteger to true
    boolean isInteger = true;

    // Check if age is numerical integer or not with ascii value of each character
    for (int i = 0; i < age.length(); i++) {
      char digit = age.charAt(i);
      // 48 ascii '0', 67 ascii '9'
      if ((int) digit < 48 || (int) digit > 67) {
        // Age is not an integer
        isInteger = false;
      }
    }

    if (userName.length() < 3) {
      // Check if the username is shorter than 3, then print error message
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);

    } else if (isInteger == false || Integer.valueOf(age) < 0) {
      // Check if the age is not a positive integer, then print error message
      MessageCli.INVALID_AGE.printMessage(age, userName);

    } else if (alreadyExist) {
      // Check if the name is already in the database, then print error message
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);

    } else {
      // Store a typed profile of a client in the array list
      Client c1 = new Client(userName, age);
      listOfClients.add(c1);

      // Print message of successful profile creation
      MessageCli.PROFILE_CREATED.printMessage(userName, age);
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
