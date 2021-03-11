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
    this.sentimentMood = 50;
    this.mood = 0;
    this.event = event;

    //do sentiment analysis
    this.calculateMood();

    //store the event
    this.storeFeedback();
  }

  //ineffcient method atm - USE HASHMAPS
  public void storeFeedback(){
    for (UserFeedback u : event.userFeedback){
      if (u.getUser() == attendee){
        u.addFeedback(this);
      }
    }
    for (Tag t1: this.tags){
      for (Tag t2: event.tags){
        if (t1.getTag().equals(t2.getTag())) {
          t2.addFeedback(this);
        }
      }
    }
  }

  public void calculateMood(){
    //PERFORM SENTIMENT ANALYSIS to get sentimentMood

    this.mood = (explicitMood + sentimentMood) / 2;
    //average even if no text or no explicit mood. encourages justification and clear mood given
  }

  public String getText(){
    return text;
  }
  public float getMood(){
    return mood;
  }
  public long getTime(){
    return timestamp;
  }
}
