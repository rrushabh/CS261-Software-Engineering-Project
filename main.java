import java.util.ArrayList;
import java.util.Random;
// import Event.java;
// import User.java;

public class main{

  //would conenct to some server or database
  // ArrayList<Event>events =  new ArrayList<Event>();
  // ArrayList<User>users =  new ArrayList<User>();
  //
  // //give default tags
  // ArrayList<Tag>defaults = new ArrayList<Tag>();

  //gives event IDs currently in use
  static ArrayList<String>usedIDs; //IDs are 4-digit integers

  public static void main(String[] args) {
      //link User to main page and let them go from there
      usedIDs = new ArrayList<String>();
      generateID();
      generateID();
  }

  //random rather than continuous for security and unwanted access reasons
  public static void generateID(){

    String id;
    Random r = new Random();
    while (true){
      id = "";
      for (int i=0; i<4; i++){
        int randomNum = r.nextInt(10);
        id += randomNum;
      }
      
      // System.out.println(id);

      if (!usedIDs.contains(id)){
        usedIDs.add(id);
        return;
      }
    }

  }

}
