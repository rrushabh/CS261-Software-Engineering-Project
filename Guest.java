public class Guest extends Attendee {

    public Guest(int id, User user){
      super(id,"temp",user);
      this.setName(generateTempUsername());
    }

    public String generateTempUsername(){
      String string = "temp" + main.generateID(); //will change as same ID pooled shared by events and guests atm
      return string;
    }

    //new joining method?
}
