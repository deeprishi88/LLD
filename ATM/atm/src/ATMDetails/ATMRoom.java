package ATMDetails;

import User.User;

public class ATMRoom {
    ATM atm;
    User user;

    public ATMRoom(ATM atm, User user){
        this.atm = atm;
        this.user = user;
    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
