//This file contains the Event, UserFeedback, Tag and Feedback classes
//This is currently just skeleton code for it all
//Getters and setters aren't included. Worry about them as needed
//files not linked so won't compile atm

public class Event{

  int id;
  String name;

  UserFeedback[] users;
  Tag[] tags;

  float currentMood;

  //These toggles will be effected by Host in various methods
  boolean joinable;
  boolean feedbackOn;
  boolean guests;
  boolean linkAccess;

  float[] moodGraph;//for graph may need to store a list of mood values across time (rather than recalculating when needed)


  //Constuctor method
  //Users given and UserFeedback made for each
  //Tags are just default upon setup
  //Maybe not all booleans will be set during initial event creation
  public Event(int id, String name, User[] users, boolean joinable, boolean feedbackOn, boolean guests, boolean linkAccess)


  //goes through feedback and updates totalMood
  //Start and end time not neccesarily like so (Probably better alternatives to Date objects)
  public void totalMood(Date starttime, Date endtime){
    //-calculate average mood for each User over time period (separate method)
    //-average these averages
  }
}

public class UserFeedback{

  Attendee attendee;
  Feedback[] feedback;

  //constructor method
  //this class acts as attendee list for event to so need entry created for each user as they join or are granted access
  public UserFeedback(Attendee attendee){
    //-feedback initally blank
  }

  //updates mood for a User
  public void averageMood(Date starttime, Date endtime){
    //-calculate average mood of feedback over a time period
  }
}

public class Tag{

  String tag;
  Feedback[] feedback;

  //constructor method
  //also gives list of tags for event. some default always set with event creation
  public Tag(String tag){
    //-feedback initally blank
  }
}

public class Feedback{

  String text;

  Tag[] tags;
  Attendee attendee;

  Date timestamp;

  float mood;

  public Feedback(String text, Tag[] tags, Attendee attendee, float mood){

  }

  public void calculateMood(){
    //-calculate mood with semantic analysis on text combined with initial explicit mood value
  }
}
