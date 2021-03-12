import java.util.ArrayList;
import java.util.Date;

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
    // if (startInd == -1){
    //   return 50;
    // }
    int endInd = ob.binarySearchMax(this.feedback, 0, n - 1, endtime);
    int sum = 0;
    for (int i=startInd; i<endInd+1; i++){
      sum += this.feedback.get(i).mood;
    }
    return sum/(endInd+1-startInd);
  }

  public void addFeedback(Feedback f){
    this.feedback.add(f);
  }

  public Attendee getUser(){
    return attendee;
  }
  public ArrayList<Feedback> getFeedback(){
    return feedback;
  }
}
