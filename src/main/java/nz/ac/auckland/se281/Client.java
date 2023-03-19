package nz.ac.auckland.se281;

public class Client {
    //Instance fields
    private  String name;
    private String age;

    //Construct the class
    public Client(String name, String age){
        this.name = name;
        this.age = age;
    }

    //Get attribute,name, in a type client outside of this class
    public String getName(){
        return this.name;
    }

    //Get attribute, age, in a type client outside of this class
    public String getAge(){
        return this.age;
    }
}
