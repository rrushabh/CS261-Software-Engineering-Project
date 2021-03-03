import java.util.ArrayList;

// import User.java;
// import main.java;
// import Event.java;
// import Attendee.java;


//HOST
public class Host extends User{

  private User user; //references the User this Host object associated with
  private Event currentEvent;

  public Host(int id, String name, User user){
      super(id,name);
      this.user = user;
  }

  public void renameEvent(String name){
    currentEvent.setName(name);
  }
  public boolean toggleIDJoining(){
    return currentEvent.toggleIDAccess();
  }

  //SHOULD
  /*@param tag - string for tag entered in textbox in event menu
  */
  public void addTag(String tag){
    //Checks tag is non empty
    //Adds a tagObject to the currentEvents list of them
    currentEvent.addTag(tag);
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

  public Event getEvent(){
    return this.currentEvent;
  }
  public void setEvent(Event event){
    this.currentEvent = event;
  }
}
