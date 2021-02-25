//This file contains the Event, UserFeedback, Tag and Feedback classes
//This is currently just skeleton code for it all
//Getters and setters aren't included. Worry about them as needed
//files not linked so won't compile atm

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import User;

public class Event{

  int id;
  String name;

  Host host;

  // ArrayList of all feedback by user
  ArrayList<UserFeedback>userFeedback =  new ArrayList<UserFeedback>();
  // HashMap of ArrayLists of feedback for each Tag
  HashMap<String, ArrayList<UserFeedback>>feedbackByTag = new HashMap<String, ArrayList<UserFeedback>>();

  ArrayList<Tag>tags =  new ArrayList<Tag>();

  float currentMood;

  //These toggles will be effected by Host in various methods:
  boolean accessible = true; //can the event be accessed by Users (will this kick out users when toggled?)
  boolean feedbackOn = false; //can Users give feedback
  boolean guests = false; //can Users join as guest
  boolean linkAccess = false; //can Users join via a link
  boolean explicitMood = true; //whether explicit mood is in the feedback template
  boolean textualFeedback = false; //whether textual feedback is in the feedback template
  int liveTime = 300; //number of seconds live mood will be calulated over, default = 5 mins

  ArrayList<Float>moodGraph =  new ArrayList<Float>();//for graph may need to store a list of mood values across time (rather than recalculating when needed)

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
  public boolean AddUser(Attendee attendee){
    for (UserFeedback user: users){
        if (user.user == attendee){
          return false;
        }
    }
    user.add(UserFeedback(attendee));
    return true;
  }


  //goes through feedback and updates totalMood
  //Start and end time not neccesarily like so (Probably better alternatives to Date objects)
  public float currentMood(){
    //-calculate average mood for each User over time period (separate method)
    //-average these averages
    Date date = new Date();
    long time = date.getTime();       //calculates average mood of feedback between liveTime seconds ago and when this line is called
    return averageMood(time-1000*this.liveTime, time);
  }

  public float averageMood(long starttime, long endtime){
    float sum = 0;
    for (int i=0; i<this.userFeedback.size(); i++){
      sum += this.userFeedback.get(i).averageMood(starttime, endtime);
    }
    return sum/this.userFeedback.size();
  }
}

// Object that stores all the feedback objects a user has submitted to a specific event
class UserFeedback{

  Attendee attendee;
  Event event;
  ArrayList<Feedback>feedback =  new ArrayList<Feedback>();

  //constructor method
  //this class acts as attendee list for event to so need entry created for each user as they join or are granted access
  public UserFeedback(Attendee attendee, Event event){
    this.attendee = attendee;
    this.event = event;
  }

  //updates mood for a User
  public float averageMood(long starttime, long endtime){
    //-calculate average mood of feedback over a time period
    BinarySearch ob = new BinarySearch();
    int n = this.feedback.size();
    int startInd = ob.binarySearchMin(this.feedback, 0, n - 1, starttime);
    int endInd = ob.binarySearchMax(this.feedback, 0, n - 1, endtime);
    ArrayList<Feedback>subarr =  new ArrayList<Feedback>();
    int sum = 0;
    for (int i=startInd; i<endInd+1; i++){
      sum += this.feedback.get(i).mood;
    return sum/(endInd+1-startInd);
    }
  }
}

class BinarySearch {
  // Returns index of smallest element greater than or equal to x
  int binarySearchMin(ArrayList<Feedback> arr, float l, float r, long x){
    if (r >= l) {
      int mid = Math.round(l + (r - l) / 2);

            /*System.out.println(l + (r - l) / 2);
            System.out.println(String.valueOf(l) +" "+ String.valueOf(mid) +" "+ String.valueOf(r));*/

      // If the element is present at the
      // middle itself
      if ( mid == 0 || (arr.get(mid).timestamp >= x && arr.get(mid-1).timestamp < x) ){
        return mid;
      }

      // If element is smaller than mid, then
      // it can only be present in left subarray
      else if (arr.get(mid).timestamp > x){
        return binarySearchMin(arr, l, mid - 1, x);
      }

      // Else the element can only be present
      // in right subarray
      else {
        return binarySearchMin(arr, mid + 1, r, x);
      }
    }

    // We reach here when element is not present
    // in array
    return -1;
  }

      // Returns index of greatest element less than or equal to x
  int binarySearchMax(ArrayList<Feedback> arr, float l, float r, long x){
    if (r >= l) {
      int mid = Math.round(l + (r - l) / 2);

            /*System.out.println(l + (r - l) / 2);
            System.out.println(String.valueOf(l) +" "+ String.valueOf(mid) +" "+ String.valueOf(r));*/

      // If the element is present at the
      // middle itself
      if ( mid == arr.size()-1 || (arr.get(mid).timestamp <= x && arr.get(mid+1).timestamp > x) ){
        return mid;
      }

      // If element is smaller than mid, then
      // it can only be present in left subarray
      else if (arr.get(mid).timestamp > x){
        return binarySearchMax(arr, l, mid - 1, x);
      }

      // Else the element can only be present
      // in right subarray
      else {
        return binarySearchMax(arr, mid + 1, r, x);
      }
    }

    // We reach here when element is not present
    // in array
    return -1;
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

  ArrayList<Tag>tags =  new ArrayList<Tag>();
  Attendee attendee;

  long timestamp;

  float explicitMood;
  float sentimentMood;

  float mood;

  public Feedback(String text, ArrayList<Tag>tags, Attendee attendee, float explicitMood){
    Date date = new Date();
    this.timestamp = date.getTime();
    this.text = text;
    this.tags = tags;
    this.attendee = attendee;
    this.explicitMood = explicitMood;
    this.sentimentMood = 0;
    this.mood = 0;
  }

  public void calculateMood(){
    if (){

    }
  }
}
