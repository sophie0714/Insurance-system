package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // Create an array list where all clients profiles are stored
  private ArrayList<Client> listOfClients = new ArrayList<>();

  private Client loadedProfile;

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

      // Calculate overall premium for a client
      int totalPremium = 0;
      for (Policy policy : aclient.getListOfPolicies()) {
        totalPremium += policy.getDiscountedPremium(aclient, policy);
      }

      // When there is a currently loaded profile, put *** in front of the loaded profile
      String loadedOrNot = "";
      if (loadedProfile != null && loadedProfile.equals(aclient)) {
        loadedOrNot = "*** ";
      }

      // When a client has only one policy, "policy" instead of "policies" has to be printed
      String ending = "ies";
      if (aclient.getListOfPolicies().size() == 1) {
        ending = "y";
      }

      // Print list of clients
      MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          loadedOrNot,
          Integer.toString(i + 1),
          aclientName,
          aclientAge,
          Integer.toString(aclient.getListOfPolicies().size()),
          ending,
          Integer.toString(totalPremium));

      // Print all policies for a client
      for (Policy policy : aclient.getListOfPolicies()) {
        // Home policy
        if (policy.getClass().equals(PolicyHome.class)) {
          PolicyHome policyHome = (PolicyHome) policy;
          MessageCli.PRINT_DB_HOME_POLICY.printMessage(
              policyHome.getAddress(),
              Integer.toString(policy.getSumInsured()),
              Integer.toString(policyHome.getBasePremium(aclient)),
              Integer.toString(policyHome.getDiscountedPremium(aclient, policy)));
          // Car policy
        } else if (policy.getClass().equals(PolicyCar.class)) {
          PolicyCar policyCar = (PolicyCar) policy;
          MessageCli.PRINT_DB_CAR_POLICY.printMessage(
              policyCar.getMakeAndModel(),
              Integer.toString(policy.getSumInsured()),
              Integer.toString(policyCar.getBasePremium(aclient)),
              Integer.toString(policyCar.getDiscountedPremium(aclient, policy)));
          // Life policy
        } else {
          PolicyLife policyLife = (PolicyLife) policy;
          MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
              Integer.toString(policy.getSumInsured()),
              Integer.toString(policyLife.getBasePremium(aclient)),
              Integer.toString(policyLife.getDiscountedPremium(aclient, policy)));
        }
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    // Capitalise the first letter and decapitalise the rest of letters
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // If there is a loaded profile, print error message
    if (loadedProfile != null) {
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
      // If a profile to load exists in the database, loading is successful
      if (userName.equals(aclientName)) {
        // If there is a loaded profile already, unload the loaded profile before laoding a new
        // profile
        if (loadedProfile != null) {
          loadedProfile = null;
        }
        MessageCli.PROFILE_LOADED.printMessage(userName);
        loadedProfile = aclient;
        return;
      }
    }
    // If a profile cannot be found, print error message
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
  }

  public void unloadProfile() {
    // Check if a profile to load exists or not
    // If exist, print success message and unload the loadedProfile
    if (loadedProfile != null) {
      String aloadedProfileName = loadedProfile.getName();
      MessageCli.PROFILE_UNLOADED.printMessage(aloadedProfileName);
      loadedProfile = null;
      // If not exist, error message is printed
    } else {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    // Capitalise the first letter and decapitalise the rest of letters
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // If the profile to delete is loaded, error message is printed
    if (loadedProfile != null) {
      String aloadedProfileName = loadedProfile.getName();
      if (aloadedProfileName.equals(userName)) {
        MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
        return;
      }
    }

    // Check if a profile to delete is in the database
    for (int i = 0; i < listOfClients.size(); i++) {
      Client aclient = listOfClients.get(i);
      String aclientName = aclient.getName();
      // If exists, remove a profile from the database
      if (userName.equals(aclientName)) {
        listOfClients.remove(aclient);
        MessageCli.PROFILE_DELETED.printMessage(userName);
        return;
      }
    }
    // If doesn't exist, print error message
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
  }

  public enum Type {
    HOME,
    CAR,
    LIFE
  }

  public void createPolicy(PolicyType type, String[] options) {
    // If there is no loaded profile, print error message
    if (loadedProfile == null) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    switch (type) {
      case HOME:
        PolicyHome policyHome =
            new PolicyHome(
                Integer.valueOf(options[0]), options[1], options[2].toLowerCase().contains("y"));
        loadedProfile.addPolicy(policyHome);
        MessageCli.NEW_POLICY_CREATED.printMessage("home", loadedProfile.getName());
        break;

      case CAR:
        PolicyCar policyCar =
            new PolicyCar(
                Integer.valueOf(options[0]),
                options[1],
                options[2],
                options[3].toLowerCase().contains("y"));
        loadedProfile.addPolicy(policyCar);
        MessageCli.NEW_POLICY_CREATED.printMessage("car", loadedProfile.getName());
        break;

      case LIFE:
        if (Integer.valueOf(loadedProfile.getAge()) > 100) {
          MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfile.getName());
          return;
        }
        // If there is already a life policy, print error message
        ArrayList<Policy> policies = loadedProfile.getListOfPolicies();
        for (int i = 0; i < loadedProfile.getListOfPolicies().size(); i++) {
          if (policies.get(i).getClass().equals(PolicyLife.class)) {
            MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfile.getName());
            return;
          }
        }
        // Store life policy for the loaded profile
        PolicyLife policyLife = new PolicyLife(Integer.valueOf(options[0]));
        loadedProfile.addPolicy(policyLife);
        MessageCli.NEW_POLICY_CREATED.printMessage("life", loadedProfile.getName());
        break;
    }
  }
}
