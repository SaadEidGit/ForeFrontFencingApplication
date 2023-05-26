import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetUpController implements ActionListener {

    public Model model;
    public Form frame;

    public SetUpController(Model model, Form frame){
        this.model = model;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.saveFilePath) {
            try {
                model.setExcelFilePath(frame.newExcelFilePathField.getText());
                //model.setPDFFilePath(frame.newPDFFilePathField.getText());
                model.setFilledPDFFilePath(frame.newExcelFilePathField.getText());
                JOptionPane.showMessageDialog(frame, "The new file paths have been set successfully!");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(frame, "An error has occurred while setting the new file paths!");
            }
        }
    }
}
