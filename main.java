import java.util.ArrayList;
import java.util.Random;
import java.util.*;

// import User.java;
// import Event.java;
// import Host.java;
// import Attendee.java;



public class main{

  //would conenct to some server or database
  static ArrayList<Event>events;
  static ArrayList<User>users;

  //give default tags
  static ArrayList<Tag>defaults;//note default tags will need to be copied (pass value not pass reference) into event or feedback will be shared across them

  //gives event IDs currently in use
  //cleanup of these IDs on deletion etc. needs to still be added.
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
      // System.out.println("a random id:" + str);

      //create a User
      User user = createUser("name");

      //create an Event
      user.createEvent();
      System.out.println(events);
      Host host = user.getHost();
      // System.out.println("eventID:" + host.getEvent().getName());
      //change it's name
      host.renameEvent("eventname");
      // System.out.println("eventName:" + host.getEvent().getName());

      //create another User
      User user2 = createUser("name2");

      //new User joins our event
      host.toggleIDJoining();
      user2.joinEventID(events.get(0).getID());
      Attendee attendee = user2.getAttendee();

      //see event users
      ArrayList<UserFeedback> eventusers =  new ArrayList<UserFeedback>();
      eventusers = events.get(0).getUserFeedback();
      // System.out.println("names of event users:");
      // for (UserFeedback u : eventusers){
      //   System.out.println(u.getUser().getName());
      // }

      //Host add tags to event
      host.addTag("tag1");
      host.addTag("tag2");

      //see tags
      // System.out.println("event tags:");
      // for (Tag t : events.get(0).getTags()){
      //   System.out.println(t.getTag());
      // }

      //submit feedback
      //50 used for base mood if none given (0-100 scale)
      attendee.submitFeedback(50, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0))), "This is some feedback");
      attendee.submitFeedback(80, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0))), "This is some more feedback");

      //view feedback
      // System.out.println("Feedback:");
      // for (UserFeedback u : eventusers){
      //   System.out.println("User:" + u.getUser().getName());
      //   for (Feedback f: u.getFeedback()){
      //     System.out.println(f.getText() + "," + f.getMood());
      //   }
      // }

      //view currentMood
      // System.out.println(events.get(0).currentMood());

      //JUST MORE EXPERIMENTS:
      //create another User who adds feedback to the event
      User user3 = createUser("name3");
      user3.joinEventID(events.get(0).getID());
      Attendee attendee2 = user3.getAttendee();
      attendee2.submitFeedback(80, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0),attendee.getEvent().getTags().get(1))), "This is even more feedback");

      //GUEST STUFF
      //create guest
      User base = new User(0,"default");
      Guest guest = new Guest(base);
      //joining as guest
      host.toggleGuests();
      guest.joinEventID(events.get(0).getID());
      //submitting anonymously
      attendee.toggleAnonymous();
      attendee.submitFeedback(30, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(1))), "This is some anonymous feedback");
      attendee.toggleAnonymous();

      //EVEN MORE EXPERIMENTS
      //create more events and check anonymity as User moves between them
      User user4 = createUser("name4");
      user4.createEvent();
      Host host2 = user4.getHost();
      host2.renameEvent("eventname2");
      host2.toggleIDJoining();
      //user now joins new event
      user2.joinEventID(events.get(1).getID()); //NEED TO MAKE MORE CLEAR IF THIS FAILS
      attendee.toggleAnonymous();
      host2.addTag("tag2-1");
      attendee.submitFeedback(30, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0))), "This is some more anonymous feedback on a different event");
      attendee.toggleAnonymous();
      //user now returns to orginal
      user2.joinEventID(events.get(0).getID());
      attendee.toggleAnonymous();
      attendee.submitFeedback(30, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(1))), "This is some more anonymous feedback back on the first event");
      attendee.toggleAnonymous();


      //how have values changed?
      System.out.println("Feedback:");
      for (Event e: events){
        System.out.println("Event:" + e.getName());
        eventusers = e.getUserFeedback();
        for (UserFeedback u : eventusers){
          System.out.println("User:" + u.getUser().getName());
          for (Feedback f: u.getFeedback()){
            System.out.println(f.getText() + "," + f.getMood());
          }
        }
        System.out.println(e.currentMood());
      }

      //filtering feedback and setting what should be displayed:
      //to display all feedback just filter by all users (or all tags - less efficient)
      events.get(0).setDisplay(host.filterFeedbackUser(new ArrayList<Integer>(Arrays.asList(attendee.getID(),attendee2.getID()))));
      //always sort by time before displaying
      events.get(0).sortDisplay();
      //the display ordered by time
      System.out.println("Filtered feedback:");
      Event event = events.get(0);
      for (Feedback f: event.getDisplay()){
        System.out.println(f.getText() + "," + f.getTime());
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
    // System.out.println("A user:" + user);
    return user;
  }


}
