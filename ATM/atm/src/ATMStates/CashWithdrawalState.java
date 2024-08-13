package ATMStates;

import ATMDetails.ATM;
import AmountWithdrawal.CashWithdrawProcessor;
import AmountWithdrawal.FiveHundredWithdrawProcessor;
import AmountWithdrawal.OneHundredWithdrawProcessor;
import AmountWithdrawal.TwoThousandWithdrawProcessor;
import BankingDetails.Card;

public class CashWithdrawalState extends ATMState{

    public CashWithdrawalState() {
        System.out.println("Please enter the Withdrawal Amount");
    }

    @Override
    public void cashWithdrawal(ATM atm, Card card, int withdrawalAmountRequest){
        if (atm.getAtmBalance() < withdrawalAmountRequest) {
            System.out.println("Insufficient fund in the ATM Machine");
            exit(atm);
        } else if (card.getBankBalance() < withdrawalAmountRequest) {
            System.out.println("Insufficient fund in the your Bank Account");
            exit(atm);
        } else {
            card.deductBankBalance(withdrawalAmountRequest);
            atm.deductATMBalance(withdrawalAmountRequest);

            //using chain of responsibility for this logic, how many 2k Rs notes, how many 500 Rs notes etc, has to be withdrawal
            CashWithdrawProcessor withdrawProcessor =
                    new TwoThousandWithdrawProcessor(new FiveHundredWithdrawProcessor(new OneHundredWithdrawProcessor(null)));


            withdrawProcessor.withdraw(atm, withdrawalAmountRequest);
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
