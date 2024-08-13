package Expense.Split;

import java.util.List;

public class UnequalExpenseSplit implements ExpenseSplit{
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        double amount = 0;
        for(Split split : splitList){
            amount += split.getAmountOwe();
        }
        if(amount != totalAmount){
            System.out.println("Split can't be validated");
        }
    }
}
