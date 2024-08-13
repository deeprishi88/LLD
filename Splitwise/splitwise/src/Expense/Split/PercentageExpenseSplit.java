package Expense.Split;

import java.util.List;

public class PercentageExpenseSplit implements ExpenseSplit{
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        double totalPercentage = 0;
        for(Split split : splitList){
            totalPercentage += split.getPercentage();
        }
        if(totalPercentage!=100){
            System.out.println("Split can't be validated");
        }
    }
}
