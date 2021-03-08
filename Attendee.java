import java.util.ArrayList;

// import User.java;
// import main.java;
// import Host.java;
// import Event.java;


//ATTENDEE
public class Attendee extends User{

  private User user; //references the User this Attendee object associated with
  private Event currentEvent;

  private boolean isAnonymous = false; //boolean showing if Attendee appearing as anonymous

  private ArrayList<Guest> anonymousUsers = new ArrayList<Guest>(); //LIST OF GUESTS MADE FOR THIS USER (their currentEvent values should allow finding correct one)
  private Guest anonymousMode; //CREATE WHEN EVENT JOINED
  //list of temp users associated with this user. Each will have an associated event as so can be found if that event currently being accessed
  //deleting info on event deletion would be difficult


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
    if (isAnonymous == false){
      isAnonymous = true;
    }else{
      isAnonymous = false;
    }
    //-toggles Attendees anonymous variable
    //-this will effect other things like feedback submission
  }

  //sets the anonymous mode for a User, done whenever joining an event
  public void setAnonymousMode(){
    for (Guest g : anonymousUsers){
      if (g.getEvent() == this.getEvent()){
        anonymousMode = g;
        return;
      }
    }
    anonymousMode = new Guest(this);
    anonymousMode.setEvent(this.currentEvent);
    anonymousUsers.add(anonymousMode);
    this.currentEvent.addUser(anonymousMode);
  }

  //MUST
  /* Trigger: enter pressed while text or mood non empty
  *(paramters are gathered from display?)
  *baseMood = mid of range if no value given
  */
  public void submitFeedback(int baseMood, ArrayList<Tag> tags, String text){

    //-feedback object constructed using constructor
    if (isAnonymous){
      Feedback feedback = new Feedback(text, tags, this.anonymousMode, baseMood, currentEvent);
    }else{
      Feedback feedback = new Feedback(text, tags, this, baseMood, currentEvent);
    }
    //NOTE: some fields may be blank, and info given to constructor depends on anonymity
  }

  public Event getEvent(){
    return this.currentEvent;
  }
  public User getUser(){
    return this.user;
  }
  public void setEvent(Event event){
    this.currentEvent = event;
  }
  public void setUser(User user){
    this.user = user;
  }
}
