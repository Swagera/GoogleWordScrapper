package GUI;

import Scrapper.Scrapper;
import Validation.Validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("FieldCanBeLocal")
public class BookingScrapperGUI extends JFrame implements ActionListener {
    private final JLabel titleLabel, queryLabel, pageLabel, fileNameLabel, dayFromLabel, dayToLabel, monthFromLabel, monthToLabel, yearFromLabel, yearToLabel;
    private final JTextField queryField, pageField, fileNameField, dayFromField, dayToField, monthFromField, monthToField, yearFromField, yearToField;
    private final JButton saveButton;

    public BookingScrapperGUI() {
        setTitle("BookingScrapper");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("BOOKING SCRAPPER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);

        queryLabel = new JLabel("Location:");
        queryLabel.setForeground(Color.WHITE);
        dayFromLabel = new JLabel("Day:");
        dayFromLabel.setForeground(Color.WHITE);
        dayToLabel = new JLabel("Day:");
        dayToLabel.setForeground(Color.WHITE);
        monthFromLabel = new JLabel("Month:");
        monthFromLabel.setForeground(Color.WHITE);
        yearFromLabel = new JLabel("Year:");
        yearFromLabel.setForeground(Color.WHITE);
        monthToLabel = new JLabel("Month:");
        monthToLabel.setForeground(Color.WHITE);
        yearToLabel = new JLabel("Year:");
        yearToLabel.setForeground(Color.WHITE);
        pageLabel = new JLabel("Number of Pages to scrap:");
        pageLabel.setForeground(Color.WHITE);
        fileNameLabel = new JLabel("File Name:");
        fileNameLabel.setForeground(Color.WHITE);

        queryField = new JTextField(20);
        dayFromField = new JTextField(5);
        dayToField = new JTextField(5);
        monthFromField = new JTextField(5);
        monthToField = new JTextField(5);
        yearFromField = new JTextField(5);
        yearToField = new JTextField(5);
        pageField = new JTextField(5);
        fileNameField = new JTextField(20);

        saveButton = new JButton("Save to Excel");
        saveButton.addActionListener(this);

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.setBackground(new Color(0, 53, 128));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0, 53, 128));


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(0, 53, 128));

        JPanel queryPanel = new JPanel();
        queryPanel.add(queryLabel);
        queryPanel.add(queryField);
        queryPanel.setOpaque(false);

        JPanel datePanel = new JPanel();
        datePanel.add(dayFromLabel);
        datePanel.add(dayFromField);
        datePanel.add(monthFromLabel);
        datePanel.add(monthFromField);
        datePanel.add(yearFromLabel);
        datePanel.add(yearFromField);
        datePanel.setOpaque(false);

        JPanel toPanel = new JPanel();
        toPanel.add(dayToLabel);
        toPanel.add(dayToField);
        toPanel.add(monthToLabel);
        toPanel.add(monthToField);
        toPanel.add(yearToLabel);
        toPanel.add(yearToField);
        toPanel.setOpaque(false);

        JPanel pagePanel = new JPanel();
        pagePanel.add(pageLabel);
        pagePanel.add(pageField);
        pagePanel.setOpaque(false);

        JPanel fileNamePanel = new JPanel();
        fileNamePanel.add(fileNameLabel);
        fileNamePanel.add(fileNameField);
        fileNamePanel.setOpaque(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.setOpaque(false);

        centerPanel.add(queryPanel);
        centerPanel.add(datePanel);
        centerPanel.add(toPanel);
        centerPanel.add(pagePanel);
        centerPanel.add(fileNamePanel);
        centerPanel.add(buttonPanel);

        centerPanel.add(Box.createVerticalGlue());

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
        ImageIcon icon = new ImageIcon("src\\main\\resources\\png-transparent-booking-com-hd-logo.png");
        setIconImage(icon.getImage());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String location = queryField.getText().trim();
            String dayFrom = dayFromField.getText().trim();
            String dayTo = dayToField.getText().trim();
            String monthFrom = monthFromField.getText().trim();
            String monthTo = monthToField.getText().trim();
            String yearFrom = yearFromField.getText().trim();
            String yearTo = yearToField.getText().trim();
            String fileName = fileNameField.getText().trim();
            int page = Integer.parseInt(pageField.getText().trim());

            boolean isFormValid = Validation.isFormValid(monthFrom, monthTo, dayFrom, dayTo, yearFrom, yearTo);

        if(isFormValid)
            try {
                new Scrapper(location, dayFrom, dayTo, monthFrom, monthTo, yearFrom, yearTo, page, fileName);
                JOptionPane.showMessageDialog(this, "Excel file saved successfully!");
                System.exit(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred");
                System.exit(0);
            }
        }
    }
}
