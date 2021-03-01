import java.util.ArrayList;
import java.util.Random;

// import User.java;
// import Event.java;
// import Host.java;
// import Attendee.java;

public class main{

  //would conenct to some server or database
  static ArrayList<Event>events;
  static ArrayList<User>users;

  //give default tags
  static ArrayList<Tag>defaults;

  //gives event IDs currently in use
  static ArrayList<String>usedIDs; //IDs are 6-digit integers

  static int userNumber; //user IDs are sequential as only used by system not seen
  //in future a record of IDs in use would be needed but no overflow in prototype

  public static void main(String[] args) {

      //In practise this will link User to main page and let them go from there
      //For now it allows testing

      events = new ArrayList<Event>();
      users = new ArrayList<User>();
      defaults = new ArrayList<Tag>();

      usedIDs = new ArrayList<String>();

      userNumber = 1;

      //generate event ID
      String str = generateID();
      System.out.println("a random id:" + str);

      //create a User
      User user = createUser("name");

      //create an Event
      user.createEvent();
      System.out.println(events);
      Host host = user.getHost();
      System.out.println("eventID:" + host.getEvent().getName());
      //change it's name
      host.renameEvent("eventname");
      System.out.println("eventName:" + host.getEvent().getName());

      //create another User
      User user2 = createUser("name2");

      //new User joins our event
      host.toggleIDJoining();
      user2.joinEventID(events.get(0).getID());
      Attendee attendee = user2.getAttendee();

      //see event users
      ArrayList<UserFeedback> eventusers =  new ArrayList<UserFeedback>();
      eventusers = events.get(0).getUserFeedback();
      System.out.print("names of event users:");
      for (UserFeedback u : eventusers){
        System.out.println(u.getUser().getName());
      }
  }

  //random rather than continuous for security and unwanted access reasons
  public static String generateID(){

    String id;
    Random r = new Random();
    while (true){
      id = "";
      for (int i=0; i<6; i++){
        int randomNum = r.nextInt(10);
        id += randomNum;
      }

      // System.out.println(id);

      if (!usedIDs.contains(id)){
        usedIDs.add(id);
        return id;
      }
    }
  }

  public static User createUser(String name){
    User user = new User(userNumber,name);
    userNumber++;
    user.createHost();
    user.createAttendee();
    users.add(user);
    System.out.println("A user:" + user);
    return user;
  }


}
