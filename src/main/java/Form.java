import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Form extends JFrame implements FormView{
    public JPanel buttonPanel, mainSubPane;
    public JButton addSideButton, removeLastButton, addGateButton, addGateWallButton;
    public JScrollPane scrollPane;
    private JPanel contentPanel;
    private JTabbedPane tabbedPane;
    //Create Quote Form
    public JTextField firstNameField, lastNameField, emailField, phoneField, addressArea, priceArea, totalPriceField, taxPercentageField, colourField, contractNumberField, fenceHeightField;
    //Create User form
    public JTextField firstNameCreateUserField, lastNameCreateUserField, emailCreateUserField, phoneCreateUserField, addressCreateUserArea;
    //Setup file path form
    public JTextArea currentExcelFilePathField, currentPDFFilePathField, currentFilledPDFFilePathField, newExcelFilePathField, newPDFPathField, newFilledPDFPathField;
    public JButton createNewClientButton, resetButton, resetCreateUserFormButton, saveQuoteButton, quoteButton, saveFilePath;
    public JComboBox daysCombo, monthsCombo, yearsCombo;
    private CreateClientController createClientController;
    private CreateQuoteController createQuoteController;
    private SetUpController setUpController;
    public JPanel contentPanelArray[] = new JPanel[20];
    public int contentPanelArrayCount = 0;
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

    public Form() throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("ForeFront Fencing Application");
        setBounds(300, 90, 900, 600);
        setSize(500,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLayout(new BorderLayout());

        Model model = new Model();
        model.addView(this);

        createClientController = new CreateClientController(model, this);
        createQuoteController = new CreateQuoteController(model, this);
        setUpController = new SetUpController(model, this);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Create New User", CreateUser());
        tabbedPane.add("Create Quote", CreateQuote());
        tabbedPane.add("File Path Setup", createSetupForm());
        this.add(tabbedPane);

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        validate();
        setVisible(true);
    }

    private JPanel createSetupForm() throws IOException {
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(8, 2));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JLabel FilePathLabel = new JLabel("Current Excel File Path:");
        gridPanel.add(FilePathLabel);
        currentExcelFilePathField = new JTextArea();
        currentExcelFilePathField.setText(FilePath.getExcelFilePath());
        currentExcelFilePathField.setBorder(BorderFactory.createLineBorder(Color.black));
        currentExcelFilePathField.setEnabled(false);
        currentExcelFilePathField.setLineWrap(true);
        currentExcelFilePathField.setWrapStyleWord(true);
        gridPanel.add(currentExcelFilePathField);

        FilePathLabel = new JLabel("Input New Excel File Path:");
        gridPanel.add(FilePathLabel);
        newExcelFilePathField = new JTextArea();
        newExcelFilePathField.setBorder(BorderFactory.createLineBorder(Color.black));
        newExcelFilePathField.setLineWrap(true);
        newExcelFilePathField.setWrapStyleWord(true);
        gridPanel.add(newExcelFilePathField);

        FilePathLabel = new JLabel("Current Template PDF File Path:");
        gridPanel.add(FilePathLabel);
        currentPDFFilePathField = new JTextArea();
        currentPDFFilePathField.setText(FilePath.getPDFFilePath());
        currentPDFFilePathField.setBorder(BorderFactory.createLineBorder(Color.black));
        currentPDFFilePathField.setEnabled(false);
        currentPDFFilePathField.setLineWrap(true);
        currentPDFFilePathField.setWrapStyleWord(true);
        gridPanel.add(currentPDFFilePathField);

        JLabel newFilePathLabel = new JLabel("Input New Template PDF File Path:");
        gridPanel.add(newFilePathLabel);
        newPDFPathField = new JTextArea();
        newPDFPathField.setBorder(BorderFactory.createLineBorder(Color.black));
        newPDFPathField.setLineWrap(true);
        newPDFPathField.setWrapStyleWord(true);
        gridPanel.add(newPDFPathField);

        JLabel currentFilePathLabel = new JLabel("Current Filled PDF Folder File Path:");
        gridPanel.add(currentFilePathLabel);
        currentFilledPDFFilePathField = new JTextArea();
        currentFilledPDFFilePathField.setText(FilePath.getFilledPDFFormFilePath());
        currentFilledPDFFilePathField.setBorder(BorderFactory.createLineBorder(Color.black));
        currentFilledPDFFilePathField.setEnabled(false);
        currentFilledPDFFilePathField.setLineWrap(true);
        currentFilledPDFFilePathField.setWrapStyleWord(true);
        gridPanel.add(currentFilledPDFFilePathField);

        JLabel newFilledPDFFilePathLabel = new JLabel("Input New Filled PDF Folder File Path:");
        gridPanel.add(newFilledPDFFilePathLabel);
        newFilledPDFPathField = new JTextArea();
        newFilledPDFPathField.setBorder(BorderFactory.createLineBorder(Color.black));
        newFilledPDFPathField.setLineWrap(true);
        newFilledPDFPathField.setWrapStyleWord(true);
        gridPanel.add(newFilledPDFPathField);

        saveFilePath = new JButton("Save New File Paths");
        saveFilePath.setHorizontalAlignment(JButton.CENTER);
        saveFilePath.setVerticalAlignment(JButton.CENTER);
        saveFilePath.addActionListener(setUpController);
        buttonPanel.add(saveFilePath);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(gridPanel);
        mainPanel.add(buttonPanel);

        return mainPanel;
    }


    private JScrollPane CreateQuote() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Add the list of years to the years array
        getYearList();
        mainSubPane = new JPanel();
        mainSubPane.setLayout(new GridLayout(11, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(firstNameLabel);
        firstNameField = new JTextField();
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(lastNameLabel);
        lastNameField = new JTextField();
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(lastNameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(emailLabel);
        emailField = new JTextField();
        emailField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(emailField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(phoneField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(addressLabel);
        addressArea = new JTextField();
        addressArea.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(addressArea);

        JLabel colourLabel = new JLabel("Fence Colour:");
        colourLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(colourLabel);
        colourField = new JTextField();
        colourField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(colourField);

        JLabel fenceHeightLabel = new JLabel("Fence Height (FT):");
        fenceHeightLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(fenceHeightLabel);
        fenceHeightField = new JTextField();
        fenceHeightField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(fenceHeightField);

        JLabel priceLabel = new JLabel("Price Per Linear Foot:");
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(priceLabel);
        priceArea = new JTextField();
        priceArea.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(priceArea);

        JLabel taxPercentageLabel = new JLabel("Tax Percentage (%):");
        taxPercentageLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(taxPercentageLabel);
        taxPercentageField = new JTextField();
        taxPercentageField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(taxPercentageField);

        JLabel contractNumberLabel = new JLabel("Contract Number:");
        contractNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(contractNumberLabel);
        contractNumberField = new JTextField();
        contractNumberField.setBorder(BorderFactory.createLineBorder(Color.black));
        mainSubPane.add(contractNumberField);

        JLabel dateToStartProj = new JLabel("Date to Start Project:");
        dateToStartProj.setHorizontalAlignment(JLabel.CENTER);
        mainSubPane.add(dateToStartProj);
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
        daysCombo = new JComboBox(dates);
        datePanel.add(daysCombo);
        monthsCombo = new JComboBox(months);
        datePanel.add(monthsCombo);
        yearsCombo = new JComboBox(years);
        datePanel.add(yearsCombo);
        mainSubPane.add(datePanel);

        addSideButton = new JButton("Add Side");
        addSideButton.setHorizontalAlignment(JButton.CENTER);
        addSideButton.setVerticalAlignment(JButton.CENTER);
        addSideButton.addActionListener(createQuoteController);

        addGateButton = new JButton("Add Gate");
        addGateButton.setHorizontalAlignment(JButton.CENTER);
        addGateButton.setVerticalAlignment(JButton.CENTER);
        addGateButton.addActionListener(createQuoteController);

        addGateWallButton = new JButton("Add Gate Wall");
        addGateWallButton.setHorizontalAlignment(JButton.CENTER);
        addGateWallButton.setVerticalAlignment(JButton.CENTER);
        addGateWallButton.addActionListener(createQuoteController);

        removeLastButton = new JButton("Remove Last");
        removeLastButton.setHorizontalAlignment(JButton.CENTER);
        removeLastButton.setVerticalAlignment(JButton.CENTER);
        removeLastButton.addActionListener(createQuoteController);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel totalPricePanel = new JPanel();
        totalPricePanel.setLayout(new GridLayout(1,2));
        JLabel totalPriceLabel = new JLabel("Total Price With Tax:");
        totalPriceLabel.setHorizontalAlignment(JLabel.CENTER);
        totalPricePanel.setSize(new Dimension(450, 50));
        totalPriceField = new JTextField();
        totalPriceField.setBorder(BorderFactory.createLineBorder(Color.black));
        totalPriceField.setFont(new Font("Serif",Font.BOLD,26));
        totalPriceField.setHorizontalAlignment(JTextField.CENTER);
        totalPriceField.setEnabled(false);
        totalPricePanel.add(totalPriceLabel);
        totalPricePanel.add(totalPriceField);

        JPanel quoteButtonPanel = new JPanel();
        quoteButtonPanel.setLayout(new BoxLayout(quoteButtonPanel, BoxLayout.X_AXIS));
        quoteButton = new JButton("Generate Quote");
        quoteButton.setHorizontalAlignment(JButton.CENTER);
        quoteButton.setVerticalAlignment(JButton.CENTER);
        quoteButton.addActionListener(createQuoteController);

        saveQuoteButton = new JButton("Save Quote");
        saveQuoteButton.setHorizontalAlignment(JButton.CENTER);
        saveQuoteButton.setVerticalAlignment(JButton.CENTER);
        saveQuoteButton.addActionListener(createQuoteController);
        saveQuoteButton.setEnabled(false);

        resetButton = new JButton("Reset Form");
        resetButton.setHorizontalAlignment(JButton.CENTER);
        resetButton.setVerticalAlignment(JButton.CENTER);
        resetButton.addActionListener(createQuoteController);

        quoteButtonPanel.add(quoteButton);
        quoteButtonPanel.add(saveQuoteButton);
        quoteButtonPanel.add(resetButton);
        buttonPanel.add(addSideButton);
        buttonPanel.add(addGateButton);
        buttonPanel.add(addGateWallButton);
        buttonPanel.add(removeLastButton);
        mainPanel.add(mainSubPane);
        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(totalPricePanel);
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
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPane.add(firstNameLabel);
        firstNameCreateUserField = new JTextField();
        firstNameCreateUserField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(firstNameCreateUserField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPane.add(lastNameLabel);
        lastNameCreateUserField = new JTextField();
        lastNameCreateUserField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(lastNameCreateUserField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPane.add(emailLabel);
        emailCreateUserField = new JTextField();
        emailCreateUserField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(emailCreateUserField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPane.add(phoneLabel);
        phoneCreateUserField = new JTextField();
        phoneCreateUserField.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(phoneCreateUserField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPane.add(addressLabel);
        addressCreateUserArea = new JTextField();
        addressCreateUserArea.setBorder(BorderFactory.createLineBorder(Color.black));
        leftPane.add(addressCreateUserArea);

        JPanel createButtonPanel = new JPanel();
        createButtonPanel.setLayout(new BoxLayout(createButtonPanel, BoxLayout.X_AXIS));
        createNewClientButton = new JButton("Create New Client");
        createNewClientButton.setHorizontalAlignment(JButton.CENTER);
        createNewClientButton.setVerticalAlignment(JButton.CENTER);
        createNewClientButton.addActionListener(createClientController);

        resetCreateUserFormButton = new JButton("Reset Form");
        resetCreateUserFormButton.setHorizontalAlignment(JButton.CENTER);
        resetCreateUserFormButton.setVerticalAlignment(JButton.CENTER);
        resetCreateUserFormButton.addActionListener(createClientController);

        createButtonPanel.add(createNewClientButton);
        createButtonPanel.add(resetCreateUserFormButton);
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

    public void addSideCombination() {
        JPanel combinationPanel = new JPanel();
        combinationPanel.setLayout(new BoxLayout(combinationPanel, BoxLayout.X_AXIS));
        combinationPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Side Length");
        JTextField textArea = new JTextField();

        combinationPanel.add(label);
        combinationPanel.add(textArea);

        //Adding the panel to the content panel array
        contentPanelArray[contentPanelArrayCount] = combinationPanel;
        contentPanelArrayCount++;
        contentPanel.add(combinationPanel);

        validate();
        repaint();
    }

    public void addGate() {
        JPanel combinationPanel = new JPanel();
        combinationPanel.setLayout(new BoxLayout(combinationPanel, BoxLayout.X_AXIS));
        combinationPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Gate Price   ");
        JTextField textArea = new JTextField();

        combinationPanel.add(label);
        combinationPanel.add(textArea);

        //Adding the panel to the content panel array
        contentPanelArray[contentPanelArrayCount] = combinationPanel;
        contentPanelArrayCount++;
        contentPanel.add(combinationPanel);

        validate();
        repaint();
    }

    public void addGateWall() {
        JPanel combinationPanel = new JPanel();
        combinationPanel.setLayout(new BoxLayout(combinationPanel, BoxLayout.X_AXIS));
        combinationPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Gate Wall Price");
        JTextField textArea = new JTextField();

        combinationPanel.add(label);
        combinationPanel.add(textArea);

        //Adding the panel to the content panel array
        contentPanelArray[contentPanelArrayCount] = combinationPanel;
        contentPanelArrayCount++;
        contentPanel.add(combinationPanel);

        validate();
        repaint();
    }

    public void removeLastPanel() {
        int panelCount = contentPanel.getComponentCount();

        if (panelCount > 0) {
            Component lastPanel = contentPanel.getComponent(panelCount - 1);
            //remove last panel
            contentPanel.remove(lastPanel);

            validate();
            repaint();

            System.out.println("Last panel removed successfully.");
        } else {
            System.out.println("No panels to remove. Content panel is empty.");
        }
    }

    public void resetCreateQuoteForm(ArrayList<JTextField> mainPanelTextFields){
        //text fields from main pane
        for(JTextField field: mainPanelTextFields){
            field.setText("");
        }

        while(contentPanelArrayCount > 0){
            contentPanelArrayCount--;
            contentPanel.remove(contentPanelArray[contentPanelArrayCount]);
        }

        validate();
        repaint();
    }

    public static ArrayList<JTextField> getTextFieldsFromPanel(JPanel panel) {
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

    public static ArrayList<JTextField> getTextFieldsFromScrollPane(JScrollPane scrollPane) {
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

    @Override
    public void update(SavePDFEvent e) {

    }

    public static void main(String[] args) throws IOException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new Form();
    }

    public void resetCreateUserForm() {
        firstNameCreateUserField.setText("");
        lastNameCreateUserField.setText("");
        emailCreateUserField.setText("");
        phoneCreateUserField.setText("");
        addressCreateUserArea.setText("");
    }

}
