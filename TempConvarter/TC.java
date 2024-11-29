import javax.swing.*;   // Import Swing components for GUI
import java.awt.event.*; // Import event handling classes
import java.awt.*;        // Import layout and event handling utilities

public class TC extends JFrame {
    
    JLabel lblFrom, lblTo, lblValue, lblResult; // Labels to display text
    JComboBox<String> comboFrom, comboTo;        // ComboBoxes for selecting temperature units
    JTextField txtValue, txtResult;              // TextFields for user input and result output
    JButton btnConvert, btnClear;                // Buttons to perform conversion and clear inputs

    public TC(String title) {
        super(title);   // Set the title of the JFrame
        initializeUI(); // Call method to initialize the user interface components
    }

    public void initializeUI() {
        lblFrom = new JLabel("From:");            // Label for 'From' temperature unit
        lblTo = new JLabel("To:");                // Label for 'To' temperature unit
        lblValue = new JLabel("Enter Temperature:");  // Label for input temperature
        lblResult = new JLabel("Converted Temperature:"); // Label for showing the result

        String[] units = {"Celsius", "Fahrenheit", "Kelvin", "Rankine", "Reaumur"};
        comboFrom = new JComboBox<>(units);    // ComboBox for 'From' unit
        comboTo = new JComboBox<>(units);      // ComboBox for 'To' unit

        txtValue = new JTextField();            // TextField for the input temperature
        txtResult = new JTextField();           // TextField for the converted result
        txtResult.setEditable(false);           // Result field should not be editable by user

        btnConvert = new JButton("Convert");    // Button to trigger conversion
        btnClear = new JButton("Clear");        // Button to clear inputs and results

        // Set the layout of the frame to GridLayout with 6 rows and 2 columns
        setLayout(new GridLayout(6, 2, 10, 10));

        add(lblFrom);        // Add the 'From' label
        add(comboFrom);      // Add the 'From' combo box
        add(lblTo);          // Add the 'To' label
        add(comboTo);        // Add the 'To' combo box
        add(lblValue);       // Add the 'Enter Temperature' label
        add(txtValue);       // Add the text field for user input
        add(lblResult);      // Add the 'Converted Temperature' label
        add(txtResult);      // Add the text field for the result
        add(btnConvert);     // Add the 'Convert' button
        add(btnClear);       // Add the 'Clear' button

        // Add action listeners for the buttons
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature(); // Call method to perform conversion
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields(); // Call method to clear the input and result fields
            }
        });

        setSize(400, 250);        // Set the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit the application when the window is closed
        setLocationRelativeTo(null);  // Center the window on the screen
        setVisible(true);             // Make the window visible
    }

    private void convertTemperature() {
        try {
            double value = Double.parseDouble(txtValue.getText()); // Parse the value entered by the user
            String fromUnit = (String) comboFrom.getSelectedItem(); // Get the selected 'From' unit
            String toUnit = (String) comboTo.getSelectedItem();     // Get the selected 'To' unit

            // Variable to hold the converted result
            double result = 0;

            // Check the selected 'From' unit and perform the conversion
            switch (fromUnit) {
                case "Celsius":
                    result = convertFromCelsius(toUnit, value);
                    break;
                case "Fahrenheit":
                    result = convertFromFahrenheit(toUnit, value);
                    break;
                case "Kelvin":
                    result = convertFromKelvin(toUnit, value);
                    break;
                case "Rankine":
                    result = convertFromRankine(toUnit, value);
                    break;
                case "Reaumur":
                    result = convertFromReaumur(toUnit, value);
                    break;
            }

            // Display the result in the result field
            txtResult.setText(String.format("%.2f", result)); // Format the result to 2 decimal places
        } catch (NumberFormatException ex) {
            // Handle case when user enters invalid data (not a number)
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtValue.setText("");    // Clear the input field
        txtResult.setText("");   // Clear the result field
        comboFrom.setSelectedIndex(0);  // Reset the 'From' combo box to the default value
        comboTo.setSelectedIndex(0);    // Reset the 'To' combo box to the default value
    }

    // Conversion logic for Celsius to other units
    private double convertFromCelsius(String toUnit, double value) {
        switch (toUnit) {
            case "Fahrenheit":
                return (value * 9 / 5) + 32;
            case "Kelvin":
                return value + 273.15;
            case "Rankine":
                return (value + 273.15) * 9 / 5;
            case "Reaumur":
                return value * 0.8;
            default:
                return value;
        }
    }

    // Conversion logic for Fahrenheit to other units
    private double convertFromFahrenheit(String toUnit, double value) {
        switch (toUnit) {
            case "Celsius":
                return (value - 32) * 5 / 9;
            case "Kelvin":
                return (value - 32) * 5 / 9 + 273.15;
            case "Rankine":
                return value + 459.67;
            case "Reaumur":
                return (value - 32) * 4 / 9;
            default:
                return value;
        }
    }

    // Conversion logic for Kelvin to other units
    private double convertFromKelvin(String toUnit, double value) {
        switch (toUnit) {
            case "Celsius":
                return value - 273.15;
            case "Fahrenheit":
                return (value - 273.15) * 9 / 5 + 32;
            case "Rankine":
                return value * 9 / 5;
            case "Reaumur":
                return (value - 273.15) * 0.8;
            default:
                return value;
        }
    }

    // Conversion logic for Rankine to other units
    private double convertFromRankine(String toUnit, double value) {
        switch (toUnit) {
            case "Celsius":
                return (value - 491.67) * 5 / 9;
            case "Fahrenheit":
                return value - 459.67;
            case "Kelvin":
                return value * 5 / 9;
            case "Reaumur":
                return (value - 491.67) * 0.8 / 1.8;
            default:
                return value;
        }
    }

    // Conversion logic for Reaumur to other units
    private double convertFromReaumur(String toUnit, double value) {
        switch (toUnit) {
            case "Celsius":
                return value / 0.8;
            case "Fahrenheit":
                return (value * 9 / 4) + 32;
            case "Kelvin":
                return (value / 0.8) + 273.15;
            case "Rankine":
                return (value * 9 / 4) + 491.67;
            default:
                return value;
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Start the application on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TC("Temperature Converter"); // Create and show the GUI window
            }
        });
    }
}
