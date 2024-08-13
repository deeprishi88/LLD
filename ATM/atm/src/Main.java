import ATMDetails.ATM;
import ATMDetails.ATMRoom;
import ATMDetails.TransactionType;
import BankingDetails.Card;
import BankingDetails.UserBankAccount;
import User.User;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ATM atm = createATM();

        User user = createUser();

        ATMRoom atmRoom = new ATMRoom(atm, user);

        atmRoom.getAtm().printCurrentATMStatus();
        atmRoom.getAtm().getCurrentATMState().insertCard(atmRoom.getAtm(), atmRoom.getUser().getCard());
        atmRoom.getAtm().getCurrentATMState().authenticatePin(atmRoom.getAtm(), atmRoom.getUser().getCard(), 112211);
        atmRoom.getAtm().getCurrentATMState().selectOperation(atmRoom.getAtm(), atmRoom.getUser().getCard(), TransactionType.CASH_WITHDRAWAL);
        atmRoom.getAtm().getCurrentATMState().cashWithdrawal(atmRoom.getAtm(), atmRoom.getUser().getCard(), 2700);
        atmRoom.getAtm().printCurrentATMStatus();

    }

    public static ATM createATM(){
        ATM atm = ATM.getATMObject();
        atm.setAtmBalance(3500, 1,2,5);

        return atm;
    }

    private static User createUser(){

        User user = new User();
        user.setCard(createCard());
        return user;
    }

    private static Card createCard(){

        Card card = new Card();
        card.setBankAccount(createBankAccount());
        return card;
    }

    private static UserBankAccount createBankAccount() {

        UserBankAccount bankAccount = new UserBankAccount();
        bankAccount.balance = 3000;

        return bankAccount;

    }

}