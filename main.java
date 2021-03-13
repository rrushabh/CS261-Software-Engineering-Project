import java.util.*;

// import User.java;
// import Event.java;
// import Host.java;
// import Attendee.java;



public class main{

  //would conenct to some server or database
  //static ArrayList<Event>events;
  //static ArrayList<User>users;

  static HashMap<Integer, Event>events;
  static HashMap<Integer, User>users;

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
    /*
      events = new ArrayList<Event>();
      users = new ArrayList<User>();
    */
      users = new HashMap<Integer, User>();
      events = new HashMap<Integer, Event>();
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
      int event1ID = 0;
      for (int ID : events.keySet()){
        event1ID = ID;
      }
      user2.joinEventID(event1ID);
      Attendee attendee = user2.getAttendee();

      //see event users
      HashMap<Integer, UserFeedback> eventusers =  new HashMap<Integer, UserFeedback>();
      eventusers = events.get(event1ID).getUserFeedback();
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
      attendee.submitFeedback(2, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0))), "This is some feedback");
      attendee.submitFeedback(3, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0))), "This is some more feedback");

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
      user3.joinEventID(event1ID);
      Attendee attendee3 = user3.getAttendee();
      attendee3.submitFeedback(4, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0),attendee.getEvent().getTags().get(1))), "This is even more feedback");

      //GUEST STUFF
      //create guest
      User base = new User(0,"default");
      Guest guest = new Guest(base);
      //joining as guest
      host.toggleGuests();
      guest.joinEventID(event1ID);
      //submitting anonymously
      attendee.toggleAnonymous();
      attendee.submitFeedback(1, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(1))), "This is some anonymous feedback");
      attendee.toggleAnonymous();

      //EVEN MORE EXPERIMENTS
      //create more events and check anonymity as User moves between them
      User user4 = createUser("name4");
      user4.createEvent();
      int event2ID = 0;
      for (int ID : events.keySet()){
        if (event1ID != ID){
          event2ID = ID;
        }
      }
      Host host2 = user4.getHost();
      host2.renameEvent("eventname2");
      host2.toggleIDJoining();
      //user now joins new event
      user2.joinEventID(event2ID); //NEED TO MAKE MORE CLEAR IF THIS FAILS
      attendee.toggleAnonymous();
      host2.addTag("tag2-1");
      attendee.submitFeedback(2, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(0))), "This is some more anonymous feedback on a different event");
      attendee.toggleAnonymous();
      //user now returns to orginal
      user2.joinEventID(event1ID);
      attendee.toggleAnonymous();
      attendee.submitFeedback(1, new ArrayList<Tag>(Arrays.asList(attendee.getEvent().getTags().get(1))), "This is some more anonymous feedback back on the first event");
      attendee.toggleAnonymous();


      //how have values changed?
      System.out.println("Feedback:");
      for (int eID : events.keySet()){
        System.out.println("Event:" + events.get(eID).getName());
        eventusers = events.get(eID).getUserFeedback();
        for (int uID : eventusers.keySet()){
          System.out.println("User:" + eventusers.get(uID).getUser().getName());
          for (Feedback f: events.get(eID).getUserFeedback().get(uID).getFeedback()){
            System.out.println(f.getText() + "," + f.getMood());
          }
        }
        System.out.println(events.get(eID).currentMood());
      }

      //filtering feedback and setting what should be displayed:
      //to display all feedback just filter by all users (or all tags - less efficient)
      events.get(event1ID).setDisplay(host.filterFeedbackUser(new ArrayList<Integer>(Arrays.asList(attendee.getID(),attendee3.getID()))));
      //always sort by time before displaying
      events.get(event1ID).sortDisplay();
      //the display ordered by time
      System.out.println("Filtered feedback:");
      Event event = events.get(event1ID);
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
    User user = new User(userNumber, name);
    userNumber++;
    user.createHost();
    user.createAttendee();
    users.put(userNumber, user);
    // System.out.println("A user:" + user);
    return user;
  }


}
