package GUI;

import Scrapper.Scrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("FieldCanBeLocal")
public class BookingScrapperGUI extends JFrame implements ActionListener {
    private  JLabel queryLabel, pageLabel, fileNameLabel, dateFromLabel, dateToLabel, monthFromLabel, monthToLabel;
    private JTextField queryField, pageField, fileNameField, dateFromField, dateToField, monthFromField, monthToField;
    private final JButton saveButton;

    public BookingScrapperGUI() {
        setTitle("BookingScrapper");
        setSize(600, 300); // Increase the size of the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        queryLabel = new JLabel("Location:");
        dateFromLabel = new JLabel("From:");
        dateToLabel = new JLabel("To:");
        monthFromLabel = new JLabel("Month:");
        monthToLabel = new JLabel("Month:");
        pageLabel = new JLabel("Number of Pages to scrap:");
        fileNameLabel = new JLabel("File Name:");

        queryField = new JTextField(20);
        dateFromField = new JTextField(5);
        dateToField = new JTextField(5);
        monthFromField = new JTextField(5);
        monthToField = new JTextField(5);
        pageField = new JTextField(5);
        fileNameField = new JTextField(20);

        saveButton = new JButton("Save to Excel");
        saveButton.addActionListener(this);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        centerPanel.add(Box.createVerticalGlue());

        JPanel queryPanel = new JPanel();
        queryPanel.add(queryLabel);
        queryPanel.add(queryField);

        JPanel datePanel = new JPanel();
        datePanel.add(dateFromLabel);
        datePanel.add(dateFromField);
        datePanel.add(monthFromLabel);
        datePanel.add(monthFromField);

        JPanel toPanel = new JPanel();
        toPanel.add(dateToLabel);
        toPanel.add(dateToField);
        toPanel.add(monthToLabel);
        toPanel.add(monthToField);

        JPanel pagePanel = new JPanel();
        pagePanel.add(pageLabel);
        pagePanel.add(pageField);

        JPanel fileNamePanel = new JPanel();
        fileNamePanel.add(fileNameLabel);
        fileNamePanel.add(fileNameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        centerPanel.add(queryPanel);
        centerPanel.add(datePanel);
        centerPanel.add(toPanel);
        centerPanel.add(pagePanel);
        centerPanel.add(fileNamePanel);
        centerPanel.add(buttonPanel);

        centerPanel.add(Box.createVerticalGlue());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String location = queryField.getText();
            String from = dateFromField.getText();
            String to = dateToField.getText();
            String monthFrom = monthFromField.getText();
            String monthTo = monthToField.getText();
            String fileName = fileNameField.getText();
            int page = Integer.parseInt(pageField.getText());
            try {
                new Scrapper(location, from, to, monthFrom, monthTo, page, fileName);
                //TODO: Better Exception Handling
                JOptionPane.showMessageDialog(this, "Excel file saved successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred: Invalid file name");
            }
        }
    }
}
