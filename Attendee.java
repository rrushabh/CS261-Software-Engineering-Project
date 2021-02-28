import java.util.ArrayList;

import User.java;
import main.java;
import Host.java;
import Event.java;


//ATTENDEE
public class Attendee extends User{

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
