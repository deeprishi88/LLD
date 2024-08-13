package ATMStates;

import ATMDetails.ATM;
import ATMDetails.TransactionType;
import BankingDetails.Card;

public class SelectOperationState extends ATMState{

    public SelectOperationState(){
        showOperations();
    }

    @Override
    public void selectOperation(ATM atmObject, Card card, TransactionType txnType){

        switch (txnType) {

            case CASH_WITHDRAWAL:
                atmObject.setCurrentATMState(new CashWithdrawalState());
                break;
            case BALANCE_CHECK:
                atmObject.setCurrentATMState(new CheckBalanceState());
                break;
            default: {
                System.out.println("Invalid Option");
                exit(atmObject);
            }

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


    private void showOperations(){
        System.out.println("Please select the Operation");
        TransactionType.showAllTransactionTypes();
    }

}
