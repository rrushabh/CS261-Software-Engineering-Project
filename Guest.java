public class Guest extends Attendee {

    //Note: each guest must be linked to an actual User.
    //If Guest and not Anonymous this is a default User object this user logs in

    public Guest(User user){
      super(user.getID(),"temp",user);
      this.setName(generateTempUsername());
    }

    public String generateTempUsername(){
      String string = "temp" + main.generateID(); //will change as same ID pool shared by events and guests atm
      return string;
    }

    //links guest to main User if not already. Would in practise get logIn details and confirm first
    public void logIn(){
        User u = new User(0,"default"); //only initalised temporarily to compile
        //set user u based on logIn info given
        setUser(u);
        //link user to this users home page
    }

    public void signUp(String name, String password){
        //users don't currently have password object as accounts out of scope but they would be set here
        setUser(main.createUser(name));
        setID(this.getUser().getID());
        //link user to their new home page
    }

    public boolean joinEventID(int id){
        for (Event event: main.events){
          if (event.getID() == id){
            if (event.accessible == true && event.linkAccess == true && event.guests == true){
                if (!event.addUser(this)){//gives false if already member
                  this.addEvent(event,0);
                }
                this.setEvent(event);//this is where we create Guest if needed or find correct one if been in event before
                setMode(0);
                return true;
            }
          }
        }
        return false;
    }

    //guests will be deleted when event they're a part of is
}
