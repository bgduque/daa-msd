package logic;

public class selectionSort {
    public static void sort(double[] arr, String[] productNames, double[] amounts) {
        int n = arr.length;

        for (int i = 0; i < n-1; i++) {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first element
            double temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;

            // Swap the corresponding product names and amounts as well
            String tempProductName = productNames[min_idx];
            productNames[min_idx] = productNames[i];
            productNames[i] = tempProductName;

            double tempAmount = amounts[min_idx];
            amounts[min_idx] = amounts[i];
            amounts[i] = tempAmount;
        }
    }
}