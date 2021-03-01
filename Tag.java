import java.util.ArrayList;

class Tag{

  String tag;
  ArrayList<Feedback>feedback =  new ArrayList<Feedback>();

  //constructor method
  //also gives list of tags for event. some default always set with event creation
  public Tag(String tag){
    //-feedback initally blank
    this.tag = tag;
  }
}
