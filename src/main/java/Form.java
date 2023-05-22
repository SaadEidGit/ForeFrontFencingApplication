import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

public class Form extends JFrame {
    private JPanel buttonPanel;
    private JButton addSideButton, addGateButton;
    private JScrollPane scrollPane;
    private JPanel contentPanel;
    private JTabbedPane tabbedPane;
    public JTextField firstNameField, lastNameField, emailField, phoneField, addressArea, priceArea;
    public JButton registerButton, resetButton;
    public JComboBox daysCombo, monthsCombo, yearsCombo;
    private int combinationGateCount = 0, combinationSideCount = 0;
    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private String years[] = new String[5];

    public Form(){
        super("Registration Form");
        setBounds(300, 90, 900, 600);
        setSize(400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Create New User", CreateUser());
        tabbedPane.add("Create Quote", CreateQuote());
        tabbedPane.add("Setup File Path", createSetupForm());

        this.add(tabbedPane);
        validate();
        setVisible(true);
    }

    private JPanel createSetupForm() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 2));

        JLabel currentFilePathLabel = new JLabel("Current File Path:");
        mainPanel.add(currentFilePathLabel);
        JTextField currentFilePathField = new JTextField();
        currentFilePathField.setBorder(BorderFactory.createLineBorder(Color.black));
        currentFilePathField.setEnabled(false);
        mainPanel.add(currentFilePathField);

        JLabel newFilePathLabel = new JLabel("Input New File Path:");
        mainPanel.add(newFilePathLabel);
        JTextField newFilePathField = new JTextField();
        newFilePathField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.add(newFilePathField);

        return mainPanel;
    }

    /**
     * Not fully implemented
     * @return
     */
    private JScrollPane CreateQuote() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add the list of years to the years array
        getYearList();
        JPanel leftPane = new JPanel();
        leftPane.setLayout(new GridLayout(7, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        leftPane.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        leftPane.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        leftPane.add(emailLabel);
        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        leftPane.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        leftPane.add(addressLabel);
        addressArea = new JTextField();
        addressArea.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(addressArea);

        JLabel priceLabel = new JLabel("Price Per Linear Foot:");
        leftPane.add(priceLabel);
        priceArea = new JTextField();
        priceArea.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(priceArea);

        JLabel dateToStartProj = new JLabel("Date to Start Project:");
        leftPane.add(dateToStartProj);
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        daysCombo = new JComboBox(dates);
        datePanel.add(daysCombo);
        monthsCombo = new JComboBox(months);
        datePanel.add(monthsCombo);
        yearsCombo = new JComboBox(years);
        datePanel.add(yearsCombo);
        leftPane.add(datePanel);

        addSideButton = new JButton("Add Side");
        addSideButton.setHorizontalAlignment(JButton.CENTER);
        addSideButton.setVerticalAlignment(JButton.CENTER);
        addSideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSideCombination();
            }
        });

        addGateButton = new JButton("Add Gate");
        addGateButton.setHorizontalAlignment(JButton.CENTER);
        addGateButton.setVerticalAlignment(JButton.CENTER);
        addGateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGate();
            }
        });

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel quoteButtonPanel = new JPanel();
        quoteButtonPanel.setLayout(new BoxLayout(quoteButtonPanel, BoxLayout.X_AXIS));
        JButton quoteButton = new JButton("Create Quote");
        quoteButton.setHorizontalAlignment(JButton.CENTER);
        quoteButton.setVerticalAlignment(JButton.CENTER);
        quoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressArea.getText();

                // Do something with the registration data
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phone);
                System.out.println("Address: " + address);
            }
        });
        resetButton = new JButton("Reset Form");
        resetButton.setHorizontalAlignment(JButton.CENTER);
        resetButton.setVerticalAlignment(JButton.CENTER);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressArea.getText();

                // Do something with the registration data
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phone);
                System.out.println("Address: " + address);
            }
        });

        quoteButtonPanel.add(quoteButton);
        quoteButtonPanel.add(resetButton);
        buttonPanel.add(addSideButton);
        buttonPanel.add(addGateButton);
        mainPanel.add(leftPane);
        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(quoteButtonPanel);

        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }

    public JPanel CreateUser(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel leftPane = new JPanel();
        leftPane.setLayout(new GridLayout(5, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        leftPane.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        leftPane.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        leftPane.add(emailLabel);
        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        leftPane.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        leftPane.add(addressLabel);
        addressArea = new JTextField();
        addressArea.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(addressArea);

        JPanel createButtonPanel = new JPanel();
        createButtonPanel.setLayout(new BoxLayout(createButtonPanel, BoxLayout.X_AXIS));
        registerButton = new JButton("Create New Client");
        registerButton.setHorizontalAlignment(JButton.CENTER);
        registerButton.setVerticalAlignment(JButton.CENTER);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressArea.getText();

                // Do something with the registration data
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phone);
                System.out.println("Address: " + address);
            }
        });

        resetButton = new JButton("Reset Form");
        resetButton.setHorizontalAlignment(JButton.CENTER);
        resetButton.setVerticalAlignment(JButton.CENTER);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressArea.getText();

                // Do something with the registration data
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Email: " + email);
                System.out.println("Phone Number: " + phone);
                System.out.println("Address: " + address);
            }
        });

        createButtonPanel.add(registerButton);
        createButtonPanel.add(resetButton);
        mainPanel.add(leftPane);
        mainPanel.add(createButtonPanel);

        return mainPanel;
    }

    /**
     * Returns 5 years ahead from the current date
     */
    private void getYearList() {
        for (int i = 0; i < 5; i++) {
            years[i] = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) + i);
        }
    }

    private void addSideCombination() {
        JPanel combinationPanel = new JPanel();
        combinationPanel.setLayout(new BoxLayout(combinationPanel, BoxLayout.X_AXIS));
        combinationPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Side " + (combinationSideCount + 1) + " Length");
        JTextField textArea = new JTextField();

        combinationPanel.add(label);
        combinationPanel.add(textArea);

        contentPanel.add(combinationPanel);
        combinationSideCount++;

        validate();
        repaint();
    }

    private void addGate() {
        JPanel combinationPanel = new JPanel();
        combinationPanel.setLayout(new BoxLayout(combinationPanel, BoxLayout.X_AXIS));
        combinationPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Gate " + (combinationGateCount + 1) + " Price   ");
        JTextField textArea = new JTextField();

        combinationPanel.add(label);
        combinationPanel.add(textArea);

        contentPanel.add(combinationPanel);
        combinationGateCount++;

        validate();
        repaint();
    }


    public void resetCreateForm(ArrayList<JTextField> mainPanelTextFields, ArrayList<JTextField> scrollPaneTextFields){
        //text fields from main pane
        for(JTextField field: mainPanelTextFields){
            field.setText("");
        }

        //text fields from scroll pane
        for(JTextField field: scrollPaneTextFields){
            field.setText("");
        }
    }

    private static ArrayList<JTextField> getTextFieldsFromPanel(JPanel panel) {
        ArrayList<JTextField> textFields = new ArrayList<>();
        Component[] components = panel.getComponents();

        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textFields.add(textField);
            }
        }

        return textFields;
    }

    private static ArrayList<JTextField> getTextFieldsFromScrollPane(JScrollPane scrollPane) {
        ArrayList<JTextField> textFields = new ArrayList<>();
        Component view = scrollPane.getViewport().getView();

        if (view instanceof JPanel) {
            JPanel panel = (JPanel) view;
            Component[] components = panel.getComponents();

            for (Component component : components) {
                if (component instanceof JTextField) {
                    JTextField textField = (JTextField) component;
                    textFields.add(textField);
                }
            }
        }

        return textFields;
    }

    public static void main(String[] args) {
        new Form();
    }
}
