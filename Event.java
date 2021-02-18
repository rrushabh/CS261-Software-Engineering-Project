//This file contains the Event, UserFeedback, Tag and Feedback classes
//This is currently just skeleton code for it all
//Getters and setters aren't included. Worry about them as needed
//files not linked so won't compile atm

import java.util.ArrayList;
import User;

public class Event{

  int id;
  String name;

  Host host;
  ArrayList<(UserFeedback)>users =  new ArrayList<(UserFeedback)>();
  ArrayList<(Tag)>tags =  new ArrayList<(Tag)>();

  float currentMood;

  //These toggles will be effected by Host in various methods:
  boolean accessible = true; //can the event be accessed by Users (will this kick out users when toggled?)
  boolean feedbackOn = false; //can Users give feedback
  boolean guests = false; //can Users join as guest
  boolean linkAccess = false; //can Users join via a link

  ArrayList<(Float)>moodGraph =  new ArrayList<(moodGraph)>();//for graph may need to store a list of mood values across time (rather than recalculating when needed)

  //Constuctor method
  //Users given and UserFeedback made for each
  //Tags are just default upon setup
  //Maybe not all booleans will be set during initial event creation
  public Event(int id, String name, Host host){
    this.id = id;
    this.name = name;
    this.host = host;
  }

  //checks if user already an attendee of the event and otherwise adds them
  public void AddUser(Attendee attendee){
    for (UserFeedback user: users){
        if (user.user == attendee){
          return false;
        }
    }
    user.add(UserFeedback(attendee));
  }


  //goes through feedback and updates totalMood
  //Start and end time not neccesarily like so (Probably better alternatives to Date objects)
  public void totalMood(Date starttime, Date endtime){
    //-calculate average mood for each User over time period (separate method)
    //-average these averages
  }
}

public class UserFeedback{

  Attendee attendee;
  ArrayList<(Feedback)>feedback =  new ArrayList<(Feedback)>();

  //constructor method
  //this class acts as attendee list for event to so need entry created for each user as they join or are granted access
  public UserFeedback(Attendee attendee){
    this.attendee = attendee;
  }

  //updates mood for a User
  public void averageMood(Date starttime, Date endtime){
    //-calculate average mood of feedback over a time period
  }
}

public class Tag{

  String tag;
  ArrayList<(Feedback)>feedback =  new ArrayList<(Feedback)>();

  //constructor method
  //also gives list of tags for event. some default always set with event creation
  public Tag(String tag){
    //-feedback initally blank
  }
}

public class Feedback{

  String text;

  ArrayList<(Tag)>tags =  new ArrayList<(Tag)>();
  Attendee attendee;

  Date timestamp;

  float mood;

  public Feedback(String text, ArrayList<Tag>tags, Attendee attendee, float mood){

  }

  public void calculateMood(){
    //-calculate mood with semantic analysis on text combined with initial explicit mood value
  }
}
