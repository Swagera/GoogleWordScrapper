package GUI;

import Scrapper.Scrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("FieldCanBeLocal")
public class GoogleWordScrapperGUI extends JFrame implements ActionListener {
    private final JLabel queryLabel, pageLabel, fileNameLabel;
    private final JTextField queryField, pageField, fileNameField;
    private final JButton saveButton;

    public GoogleWordScrapperGUI() {
        setTitle("Google Word Scraper");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        queryLabel = new JLabel("Search Query:");
        pageLabel = new JLabel("Number of Pages:");
        fileNameLabel = new JLabel("File Name:");

        queryField = new JTextField(20);
        pageField = new JTextField(5);
        fileNameField = new JTextField(20);


        saveButton = new JButton("Save to Excel");
        saveButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel queryPanel = new JPanel();
        queryPanel.add(queryLabel);
        queryPanel.add(queryField);

        JPanel pagePanel = new JPanel();
        pagePanel.add(pageLabel);
        pagePanel.add(pageField);

        JPanel fileNamePanel = new JPanel();
        fileNamePanel.add(fileNameLabel);
        fileNamePanel.add(fileNameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);

        panel.add(queryPanel);
        panel.add(pagePanel);
        panel.add(fileNamePanel);
        panel.add(buttonPanel);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            String query = queryField.getText();
            String fileName = fileNameField.getText();
            int page = Integer.parseInt(pageField.getText());
            try {
            new Scrapper(query, page, fileName);
            //TODO: Better Exception Handling
                JOptionPane.showMessageDialog(this, "Excel file saved successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "An error occurred: Invalid file name");
            }
        }
    }
}
