import java.util.ArrayList;
import java.util.Date;

public class Feedback{

  String text;
  Event event;

  ArrayList<Tag>tags =  new ArrayList<Tag>();
  Attendee attendee;

  long timestamp;

  float explicitMood;
  float sentimentMood;

  float mood;

  public Feedback(String text, ArrayList<Tag>tags, Attendee attendee, float explicitMood, Event event){
    Date date = new Date();
    this.timestamp = date.getTime();
    this.text = text;
    this.tags = tags;
    this.attendee = attendee;
    this.explicitMood = explicitMood;
    this.sentimentMood = 0;
    this.mood = 0;
    this.event = event;

    //do sentiment analysis

    //store the event
    this.storeFeedback();
  }

  public void storeFeedback(){
    for (UserFeedback u : event.userFeedback){
      if (u.getUser() == attendee){
        u.addFeedback(this);
      }
    }
    // for (Tag t: tags){
    //   //add entry in Hashmap
    // }
  }

  public void calculateMood(){
    // if (){
    //
    // }
  }
}
