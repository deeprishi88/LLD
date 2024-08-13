package User;

import BankingDetails.Card;
import BankingDetails.UserBankAccount;

public class User {
    Card card;
    UserBankAccount bankAccount;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}
