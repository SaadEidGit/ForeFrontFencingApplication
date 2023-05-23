import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateQuoteController implements ActionListener {
    public Model model;
    public Form frame;

    public CreateQuoteController(Model model, Form frame){
        this.model = model;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.addSideButton){
            frame.addSideCombination();
        }else if(e.getSource() == frame.addGateButton){
            frame.addGate();
        }else if(e.getSource() == frame.removeLastButton){
            frame.removeLastPanel();
            frame.contentPanelArray[frame.contentPanelArray.length - 1] = null;
            frame.contentPanelArrayCount--;

            for (int i = 0; i < frame.contentPanelArray.length - 1; i++){
                if (frame.contentPanelArray[i] != null && frame.contentPanelArray[i].getClass().equals(JPanel.class)){
                    JPanel panel = frame.contentPanelArray[i];
                    Component[] components = panel.getComponents();
                    for (Component comp : components) {
                        if(comp.getClass().equals(JLabel.class)){
                            JLabel label = (JLabel) components[0];

                            if (label.getText().equals("Gate Price   ")){
                                JTextField textField = (JTextField) components[1];
                                model.removeLastGate(new Gate(Double.parseDouble(textField.getText())));
                                break;
                            }else if (label.getText().equals("Side Length")){
                                JTextField textField = (JTextField) components[1];
                                model.removeLastSide(new Side(Double.parseDouble(textField.getText())));
                                break;
                            }
                        }
                    }
                }
            }
        }else if(e.getSource() == frame.quoteButton){
            //TODO: add the correct panel to their respective lists in the model, either gates, or sides.
            for (int i = 0; i < frame.contentPanelArray.length - 1; i++){
                if (frame.contentPanelArray[i] != null && frame.contentPanelArray[i].getClass().equals(JPanel.class)){
                    JPanel panel = frame.contentPanelArray[i];
                    Component[] components = panel.getComponents();
                    for (Component component : components) {
                        if(component.getClass().equals(JLabel.class)){
                            JLabel label = (JLabel) components[0];

                            if (label.getText().equals("Gate Price   ")){
                                JTextField textField = (JTextField) components[1];
                                model.addGate(new Gate(Double.parseDouble(textField.getText())));
                                break;
                            }else if (label.getText().equals("Side Length")){
                                JTextField textField = (JTextField) components[1];
                                model.addSide(new Side(Double.parseDouble(textField.getText())));
                                break;
                            }
                        }
                    }
                }
            }

            //Error handling if the price and tax percentage fields are empty
            if (frame.priceArea.getText().isEmpty() || frame.taxPercentageField.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Please fill in all fields before pressing Generating Quote button.");
            }else{
                //Setting the total price in the total price field
                model.setLinearSquareFootPrice(Double.parseDouble(frame.priceArea.getText()));
                model.calculateTotalPrice(Double.parseDouble(frame.taxPercentageField.getText()));
                frame.totalPriceField.setText(String.valueOf(model.getTotalPrice()));
                frame.saveQuoteButton.setEnabled(true);
            }
        }else if(e.getSource() == frame.saveQuoteButton){
            Client client = new Client(frame.firstNameField.getText(),
                    frame.lastNameField.getText(),
                    frame.emailField.getText(),
                    frame.phoneField.getText(),
                    frame.addressArea.getText());
            System.out.println(client);

            double pricePerLinearFoot = Double.parseDouble(frame.priceArea.getText());
            System.out.println("pricePerLinearFoot: " + pricePerLinearFoot);
            double taxPercentage = Double.parseDouble(frame.taxPercentageField.getText());
            System.out.println("taxPercentage: " + taxPercentage);
            int dateDay = Integer.parseInt((String) frame.daysCombo.getSelectedItem());
            System.out.println("dateDay: " + dateDay);
            String dateMonth = (String) frame.monthsCombo.getSelectedItem();
            System.out.println("dateMonth: " + dateMonth);
            int dateYear = Integer.parseInt((String) frame.yearsCombo.getSelectedItem());
            System.out.println("dateYear: " + dateYear);
            Date date = new Date(dateYear, dateMonth, dateDay);

            /*TODO: save all fields into the pdf.Pass in the client object, the date object, pricePerLinearFoot,
             * and taxPercentage to the pdf method
             */
        }else if(e.getSource() == frame.resetButton){
            frame.resetCreateQuoteForm(frame.getTextFieldsFromPanel(frame.mainSubPane));
        }
    }
}
