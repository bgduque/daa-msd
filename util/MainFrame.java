package util;

import javax.swing.*;

import logic.customerDelivery;
import logic.knapsack;
import logic.selectionSort;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    JFrame frame = new JFrame("E-Commerce System");
    JButton[] buttons = new JButton[4];
    JPanel[] cards = new JPanel[4];
    
    private JLabel outputLabel;
    private String[] buttonNames = { "Knapsack", "Selection Sort", "Customer Delivery", "String Matcher"};
    
    public MainFrame() {
        this.setTitle("E-Commerce System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        
        JPanel buttonPanel = new JPanel(new GridLayout(buttons.length, 1));
        
        JPanel cardPanel = new JPanel(new CardLayout());
        cardPanel.add(buttonPanel, "Buttons");
        
        for (int x = 0; x < buttons.length; x++) {
            buttons[x] = new JButton(buttonNames[x]);
            
            int finalX = x;
            buttons[x].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) cardPanel.getLayout();
                    cl.show(cardPanel, "Card " + (finalX + 1));
                }
            });
            buttonPanel.add(buttons[x]);
            
            cards[x] = new JPanel(new BorderLayout());
            cards[x].add(new JLabel("Card " + (x + 1)), BorderLayout.CENTER);

            if (x == 0) { // If this is the first card
                outputLabel = new JLabel();
                JTextField grossWeightInput = new JTextField(); // Text field for gross weight input
                JLabel errorMessage = new JLabel(); // Label for error messages
                errorMessage.setForeground(Color.RED); // Set the error message color to red

                JLabel outputLabel = new JLabel(); // Label for the output
                outputLabel.setHorizontalAlignment(JLabel.CENTER); // Center the output

                JButton submitButton = new JButton("Submit");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            double grossWeight = Double.parseDouble(grossWeightInput.getText());
                            if (grossWeight < 0 || grossWeight > 1000000) { // Check if the gross weight is within a reasonable range
                                throw new NumberFormatException();
                            }
                            knapsack k = new knapsack();
                            String output = k.fn(grossWeight);
                            output = "<html>" + output.replace("\n", "<br>") + "</html>"; // Replace newline characters with HTML line breaks
                            outputLabel.setText(output); // Set the output text
                        } catch (NumberFormatException ex) {
                            errorMessage.setText("Invalid input");
                        }
                    }
                });

                JPanel submitButtonPanel = new JPanel(new FlowLayout()); // Panel for the "Submit" button
                submitButtonPanel.add(submitButton);
            
                JPanel centerPanel = new JPanel(new GridBagLayout()); // Panel for the "Submit" button and the output label
                GridBagConstraints c = new GridBagConstraints();
            
                c.gridx = 0;
                c.gridy = 0;
                c.weightx = 1.0;
                c.fill = GridBagConstraints.HORIZONTAL;
                centerPanel.add(submitButtonPanel, c);
            
                c.gridy = 1;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                centerPanel.add(new JScrollPane(outputLabel), c);
            
                cards[x].add(grossWeightInput, BorderLayout.NORTH);
                cards[x].add(centerPanel, BorderLayout.CENTER); // Add the center panel to the center
                cards[x].add(errorMessage, BorderLayout.SOUTH);
            }

            if (x == 1) { // If this is the second card
                JTextArea sortedResults = new JTextArea(); // Text area for sorted results
                sortedResults.setEditable(false); // Make the text area non-editable
        
                JButton sortButton = new JButton("Sort");
                sortButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String currentResults = outputLabel.getText();

                        // Parse the current results into arrays
                        String[] lines = currentResults.split("<br>");
                        List<Double> weights = new ArrayList<>();
                        List<String> productNames = new ArrayList<>();
                        List<Double> amounts = new ArrayList<>();
                        for (int i = 0; i < lines.length; i++) {
                            String[] parts = lines[i].split(": ");
                            if (parts.length != 2) {
                                continue;
                            }
                            String[] weightAndAmount = parts[1].split(", ");
                            if (weightAndAmount.length != 2) {
                                continue;
                            }
                            productNames.add(parts[0]);
                            weights.add(Double.parseDouble(weightAndAmount[0]));
                            amounts.add(Double.parseDouble(weightAndAmount[1]));
                        }

                        // Convert the lists to arrays for the sort method
                        double[] weightsArray = weights.stream().mapToDouble(Double::doubleValue).toArray();
                        String[] productNamesArray = productNames.toArray(new String[0]);
                        double[] amountsArray = amounts.stream().mapToDouble(Double::doubleValue).toArray();

                        // Sort the arrays using the selectionSort logic
                        selectionSort.sort(weightsArray, productNamesArray, amountsArray);

                        // Build the sorted results string
                        StringBuilder sortedResultsString = new StringBuilder();
                        for (int i = 0; i < weightsArray.length; i++) {
                            sortedResultsString.append(productNamesArray[i]).append(": ").append(weightsArray[i]).append(", ").append(amountsArray[i]).append("\n");
                        }

                        // Set the sorted results in the JTextArea
                        sortedResults.setText(sortedResultsString.toString());
                    }
                });
        
                cards[x].add(new JScrollPane(sortedResults), BorderLayout.CENTER); // Add the sorted results text area to the center
                cards[x].add(sortButton, BorderLayout.NORTH); // Add the "Sort" button to the north
            }
            if(x == 2) {
                // Create an instance of customerDelivery
                customerDelivery delivery = new customerDelivery();

                // Get the distances
                Map<String, Map<String, Integer>> distances = delivery.getDistances();

                // Convert the distances to a string
                StringBuilder distancesString = new StringBuilder();
                for (Map.Entry<String, Map<String, Integer>> entry : distances.entrySet()) {
                    distancesString.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }

                // Create a JTextArea to display the distances
                JTextArea distancesTextArea = new JTextArea(distancesString.toString());
                distancesTextArea.setEditable(false); // Make the text area non-editable

                // Add the JTextArea to the card
                cards[x].add(new JScrollPane(distancesTextArea), BorderLayout.CENTER);
            }
            
            JButton backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) cardPanel.getLayout();
                    cl.show(cardPanel, "Buttons");
                }
            });
            cards[x].add(backButton, BorderLayout.SOUTH);

            cardPanel.add(cards[x], "Card " + (x + 1));

        }

        this.add(cardPanel, BorderLayout.CENTER);
    }
}