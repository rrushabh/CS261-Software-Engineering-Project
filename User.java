//This file contains the User, Host and Attendee classes (may be worth splitting into separate files at some point)
//This is currently just skeleton code for it all
//Getters and setters aren't included. Worry about them as needed
//files not linked so won't compile atm

//NOTE: THE USER CLASS HEIRACHY LEADS TO LOTS OF REPEATED AND UNUSED DATA
//IF ANY BETTER OPTIONS ARE DISCOVERED THEY SHOULD BE EXPLORED

import java.util.ArrayList;

import Event.java;
import main.java;
import Host.java;
import Attendee.java;

//USER
public class User{

  private int id;
  private String name;

  private Host hostMode;
  private Attendee attendeeMode;

  //gives list of events user has access to an whether Host and Attendee:
  //0 = attendee, 1 = host
  private ArrayList<Eventtype>eventList =  new ArrayList<Eventtype>();

  //constructer method
  //this method would be run by an account system (we will manually run though)
  public User(int id, String name){
    this.id = id;
    this.name = name;
  }

  //These 2 methods are seperate to constructor or infinite loop occurs with subclasses:
  //create attendee and host variants for User as needed
  public void createAttendee(){
    attendeeMode = new Attendee(this.id,this.name,this);
  }
  public void createHost(){
    hostMode = new Host(this.id,this.name,this);
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

      String id = main.generateID();

      //create skeleton event: -name given as id.
      Event newEvent = new Event(Integer.parseInt(id), id, this.hostMode);
      main.events.add(newEvent);
      this.eventList.add(new Eventtype(newEvent, 1));

      //-moves Host into event editing menu. HOW THOUGH I DONT KNOW?
      return true;
  }

  //MUST
  /*Trigger: ID entered or link used
  @param id - id of the event joining
  */
  public boolean joinEventID(int id){
      //NOTE: MAY BE WORTH DOING ID STUFF WITH STRINGS ONLY (easier?)
      for (Event event: main.events){
        if (event.id == id){
          if (event.host == this.hostMode){
              //-move host to event data page
              return true;
          }
          else if (event.accessible == true && event.linkAccess == true){
              event.addUser(this.attendeeMode);
              //-move attendee to event feedback page
              return true;
          }
        }
      }
      return false;
  }

  //SHOULD
  /*Trigger: Event joined via menu
  @param id - id of the event joining
  */
  public boolean joinEventMenu(int id){
    for (Event event: main.events){
      if (event.id == id){
        if (event.host == this.hostMode){
            //-move host to event data page
            return true;
        }
        else if (event.accessible == true){
            //-move attendee to event feedback page
            return true;
        }
      }
    }
    return false;
  }

  public Attendee getAttendee(){
    return attendeeMode;
  }
}

//Small class negating need for tuples in event list for User
//Type info could just be handled by event but no need to load up all eventData until accessed
class Eventtype{

  private Event event;
  private int type; //0 IS ATTENDEE, 1 IS HOST

  public Eventtype(Event event, int type){
    this.event = event;
    this.type = type;
  }
}
