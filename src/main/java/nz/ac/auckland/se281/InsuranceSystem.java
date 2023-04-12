package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Create an array list where all clients profiles are stored
  public ArrayList<Client> listOfClients = new ArrayList<>();

  public Client loadedProfile;

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
      String aclientName = aclient.getName();
      String aclientAge = aclient.getAge();
      if (loadedProfile != null && loadedProfile.equals(aclient)){
        MessageCli.PRINT_DB_PROFILE_HEADER_SHORT.printMessage("*** ", Integer.toString(i+1), aclientName, aclientAge);
      }else{
        MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
            Integer.toString(i + 1), aclientName, aclientAge);
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    // Capitalise the first letter and decapitalise the rest of letters
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // If there is a loaded profile, print error message
    if (loadedProfile != null){
      String aloadedProfileName = loadedProfile.getName();
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(aloadedProfileName);
      return;
    }
    // Determine if a name is unique or not
    // Initialise alreadyExist to false
    boolean alreadyExist = false;

    // Check if newly typed name is alread in the database
    for (int i = 0; i < listOfClients.size(); i++) {
      Client aclient = listOfClients.get(i);
      String aclientName = aclient.getName();

      // If exist, alreadyExist becomes true, meaning the name is not unique
      if (userName.equals(aclientName)) {
        alreadyExist = true;
      }
    }

    // Determine if age is a positive integer
    // Assume age is a positive integer at first
    boolean isPosInt = true;

    // Check if age consists of only numbers (not a negative sign or dot)
    for (int i = 0; i < age.length(); i++) {
      char digit = age.charAt(i);
      // 48 ascii '0', 57 ascii '9'
      if ((int) digit < 48 || (int) digit > 57) {
        // Age is not a positive number
        isPosInt = false;
      }
    }

    // If age is a positive integer and has leading zeros, remove them
    if (isPosInt) {
      age = Integer.toString(Integer.valueOf(age));
    }

    if (userName.length() < 3) {
      // Check if the username is shorter than 3, then print error message
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);

    } else if (isPosInt == false) {
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
    
    // Capitalise the first letter and decapitalise the rest of letters
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();
    
    // Check if a profile to load is in the database or not
    for (int i = 0; i < listOfClients.size(); i++) {
      Client aclient = listOfClients.get(i);
      String aclientName = aclient.getName();

      if (userName.equals(aclientName)) {
        unloadProfile();
        MessageCli.PROFILE_LOADED.printMessage(userName);
        loadedProfile = aclient;
        return;
      }
    }
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
  }

  public void unloadProfile() {
    // Check if a profile to load exists or not
    // If exist, print success message and let the loadedProfile null
    if (loadedProfile != null){
      String aloadedProfileName = loadedProfile.getName();
      MessageCli.PROFILE_UNLOADED.printMessage(aloadedProfileName); 
      loadedProfile = null;
    // If not exist, error message is printed
    }else{
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
