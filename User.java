//This file contains the User, Host and Attendee classes (may be worth splitting into separate files at some point)
//This is currently just skeleton code for it all
//Getters and setters aren't included. Worry about them as needed
//files not linked so won't compile atm

import java.util.ArrayList;

//USER
public class User{

  private int id;
  private String name;

  private Host hostMode;
  private Attendee attendeeMode;

  // private ArrayList<(Event,int)>eventList =  new ArrayList<(Event,int)>(); - this is wrong and needs fixing

  //constructer method
  public User(int id, String name){
      //-create User with given id and name
      //-construct Host and Attendee and link them
  }

  //MUST
  /*Trigger: button on main screen pressed
  Output: True if event succesfully created, False otherwise
  */
  public boolean createEvent(){
      //-createsEvent (calls constructor) - User set as Host
      //-moves Host into event editing menu
      return true
  }

  //MUST
  /*Trigger: ID entered or link used
  @param id - id of the event joining
  */
  public void joinEventID(int id){
      //-if event joinable:
        //-add User as attendee to eventList
        //-move attendee to event feedback page
  }

  //SHOULD
  /*Trigger: Event joined via menu
  @param id - id of the event joining
  */
  public void joinEventMenu(int id){
      //-check if host or attendee
      //-move to correct page
  }
}

//HOST
public class Host extends User{

  private User user; //references the User this Host object associated with
  private Event currentEvent;

  public Host(int id, String name, User user){
      //User super method here
      super(id,name);
      //create other parts of Host object
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
      //-create other parts of Attendee object
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
