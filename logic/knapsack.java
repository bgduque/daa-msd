package logic;

public class knapsack{
    
    //Default constructor, just so that it can be instantiated in the Main class.
    //Not the most optimal solution, but it is the most optimal when being ran in the GUI.
    public knapsack() { }

    public String fn(double grossingWeight) {
        String[] productNames = {"Canned Goods", "Cooking Oil", "Noodles", "Soap"};
        double[] weightPerBox = {5.0, 3.0, 2.5, 7.0};
        double[] amounts = {450.0, 725.0, 375.0, 500.0};

        double[] totalWeights = new double[productNames.length];
        for (int x = 0; x < productNames.length; x++) {
            totalWeights[x] = weightPerBox[x] * grossingWeight;
        }

        selectionSort.sort(totalWeights, productNames, amounts);
        double totalWeight = 0.0, totalAmount = 0.0;
        StringBuilder output = new StringBuilder();
        for (int x = 0; x < productNames.length; x++) {
            double weight = weightPerBox[x] * grossingWeight;
            double amount = amounts[x];
            totalAmount += amount;
            totalWeight += weight;

            output.append("Product: ").append(productNames[x]).append("\n");
            output.append("Weight: ").append(weight).append(" kilos\n");
            output.append("Amount: ").append(amount).append("\n\n");
        }
        output.append("Total Weight: ").append(totalWeight).append(" kilos\n");
        output.append("Total Amount: ").append(totalAmount).append("\n");
        return output.toString();
    }
}
