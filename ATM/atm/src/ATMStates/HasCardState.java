package ATMStates;

import ATMDetails.ATM;
import ATMDetails.TransactionType;
import BankingDetails.Card;

public class HasCardState extends ATMState{

    public HasCardState(){
        System.out.println("enter your card pin number");
    }

    @Override
    public void authenticatePin(ATM atm, Card card, int pin){
        if(card.isCorrectPINEntered(pin)){
            System.out.println("Authentication Successful");
            atm.setCurrentATMState(new SelectOperationState());
        }
        else{
            System.out.println("Incorrect Pin Entered, Try Again");
            exit(atm);
        }
    }


    @Override
    public void exit(ATM atm){
        returnCard();
        atm.setCurrentATMState(new IdleState());
        System.out.println("Exit happens");
    }

    @Override
    public void returnCard(){
        System.out.println("Please collect your card");
    }

}
