//This file contains the User, Host and Attendee classes (may be worth splitting into separate files at some point)
//This is currently just skeleton code for it all
//Getters and setters aren't included. Worry about them as needed
//files not linked so won't compile atm

//NOTE: THE USER CLASS HEIRACHY LEADS TO LOTS OF REPEATED AND UNUSED DATA
//IF ANY BETTER OPTIONS ARE DISCOVERED THEY SHOULD BE EXPLORED

import java.util.ArrayList;
import Event;
import main;

//USER
public class User{

  private int id;
  private String name;

  private Host hostMode;
  private Attendee attendeeMode;

  //gives list of events user has access to an whether Host and Attendee:
  //0 = attendee, 1 = host
  private ArrayList<Eventtype>eventList =  new ArrayList<(Eventtype)>();

  //constructer method
  //this method would be run by an account system (we will manually run though)
  private User(int id, String name){
    this.id = id;
    this.name = name;
  }

  //These 2 methods are seperate to constructor or infinite loop occurs with subclasses:
  //create attendee and host variants for User as needed
  public createAttendee(){
    attendeeMode = Attendee(this.id,this.name,this);
  }
  public createHost(){
    hostMode = Host(this.id,this.name,this);
  }

  //MUST
  /*Trigger: button on main screen pressed
  Output: True if event succesfully created, False otherwise
  */
  public boolean createEvent(){
      //Host taken to event creation page
      //On this page the different buttons, sliders, text boxes etc effect each feature of the event
      //Base event created the second User accesses this (given an id and User as Host and little else)
      //Other info set as default and edited by Host in editEvent methods

      String id = generateID();  

      //create skeleton event: -name given as id.
      Event(Integer.parseInt(id), id, this.hostMode);

      //-moves Host into event editing menu. HOW THOUGH I DONT KNOW?
      return true
  }

  //MUST
  /*Trigger: ID entered or link used
  @param id - id of the event joining
  */
  public void joinEventID(int id){
      //for each event in system:  <-KEEP IN HASHMAP in  main?
        //check for ID match
          if (event.host == this.hostMode){
              //-move host to event data page
              return true;
          }
          else if (event.accessible == true && event.linkAccess == true){
              event.addUser(this.attendeeMode);
              //-move attendee to event feedback page
              return true;
          }
      return false;
  }

  //SHOULD
  /*Trigger: Event joined via menu
  @param id - id of the event joining
  */
  public void joinEventMenu(int id){
    //for each event in system:  <-KEEP IN HASHMAP in  main?
      //check for ID match
        if (event.host == this.hostMode){
            //-move host to event data page
            return true;
        }
        else if (event.accessible == true){
            //-move attendee to event feedback page
            return true;
        }
    return false;
  }
}

//Small class negating need for tuples in event list for User
//Type info could just be handled by event but no need to load up all eventData until accessed
public class Eventtype(){

  private Event event;
  private int type;

  public Event(Event event, int type){
    this.event = event;
    this.type = type;
  }
}

//HOST
public class Host extends User{

  private User user; //references the User this Host object associated with
  private Event currentEvent;

  public Host(int id, String name, User user){
      super(id,name);
      this.user = user
  }

  //SHOULD
  /*@param tag - string for tag entered in textbox in event menu
  */
  public void addTag(String tag){
      //Checks tag is non empty
      //Adds a tagObject to the currentEvents list of them
  }

  //SHOULD
  public void generateLink(){
      //-generates link based on ID of currentEvent and gives to Host to copy and share
  }

  //SHOULD
  public void toggleJoining(){
      //-simply toggles an event parameter which blocks joining
  }

  //SHOULD
  public void deleteEvent(){
     //-prompts user if sure they want to delete event
     //-if so it is deleted
  }

  //SHOULD
  public void toggleFeedback(){
      //-simply toggles an event parameter which adjust whether feedback can be submitted
      //-adjust screen of all Attendees in event
  }

  //NOTE: general method needed across following methods (get Attendee from id)

  //SHOULD
  public void filterFeedback(String[] tag){
    //-adjust feedback screen to only show feedback that matches a tag in the list
  }
  public void filterFeedback(int[] Userid){
    //-adjust feedback screen to only show feedback that matches a user whose ID is in the list
  }
  //Can these work interchangeably or is it one or the other?
  //This will be comparaitvely difficult to some other to implement

  //Note: general method needed (filter() - decides feedback to show to Host)
  //We also need to keep track of what current filters and ordering in place

  //SHOULD
  public void addAttendee(int Userid){
    //-find attendee with id
    //-give access to event
  }
  public void removeAttendee(int Userid){
    //-find attendee with ID
    //-revoke access and remove if currently accessing event
    //-delete all associated feedback and remove from calculations
  }

  //SHOULD
  //Do these 2 need to be separate (whats point of save without export?)
  public void saveEvent(){
    //-generates and saves a display of the event
    //-rewrites any existing data for event
  }
  public void exportEvent(){
    //-creates pdf of saved event data which Host can download
  }

  //COULD
  public void ignoreFeedback(Feedback feedback){
    //-Host can select feedback option and delete it
    //-Calculations now ignore it (covered by default as next calculation can't use non existent object)
  }
  //Note: when deleting feedback objects remove from both UserFeedback and any Tag lists

  //COULD
  /*@param type - 0 = reactions, 1 = timestamp
  *@param direction - 0 = descending time/no, 1 = ascending time/number
  */
  public void orderFeedback(int type, int direction){
    //-set type to order feedback by and direction to sort
    //-this automatically orders set collected by filtering and displays to Host
  }
}

//ATTENDEE
public class Attendee extends User(){

  private User user; //references the User this Attendee object associated with
  private Event currentEvent;

  private boolean isAnonymous = false; //boolean showing if Attendee appearing as anonymous
  private String tempUsername; //username for when anonymous (changes each event but consistent for an event)

  //Note: need method that generates this tempUsername whenever event joined
  //Event will have to store user tempNames to store even if join another and come back
  //Store as part of UserFeedback objects

  public Attendee(int id, String name, User user){
      //-User super method here
      super(id,name);
      this.user = user;
  }

  //MUST
  public void toggleAnonymous(){
    //-toggles Attendees anonymous variable
    //-this will effect other things like feedback submission
  }

  //MUST
  /* Trigger: enter pressed while text or mood non empty
  *(paramters are gathered from display?)
  */
  public void submitFeedback(int baseMood, Tag[] tags, String text){

    //-feedback object constructed using constructor
    //-and then submitted for processing
    //NOTE: some fields may be blank, and info given to constructor depends on anonymity
  }

}
