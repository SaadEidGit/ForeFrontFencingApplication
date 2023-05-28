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
                if (!frame.newExcelFilePathField.getText().isEmpty()) {
                    model.setExcelFilePath(frame.newExcelFilePathField.getText());
                }
                if (!frame.newPDFPathField.getText().isEmpty()) {
                    model.setPDFFilePath(frame.newPDFPathField.getText());
                }
                if (!frame.newFilledPDFPathField.getText().isEmpty()) {
                    model.setFilledPDFFilePath(frame.newFilledPDFPathField.getText());
                }

                // Update the current file path
                frame.currentExcelFilePathField.setText(FilePath.getExcelFilePath());
                frame.currentPDFFilePathField.setText(FilePath.getPDFFilePath());
                frame.currentFilledPDFFilePathField.setText(FilePath.getFilledPDFFormFilePath());

                frame.newExcelFilePathField.setText("");
                frame.newPDFPathField.setText("");
                frame.newFilledPDFPathField.setText("");

                JOptionPane.showMessageDialog(frame, "The new file paths have been set successfully!");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(frame, "An error has occurred while setting the new file paths with exception:\n" + e1.getMessage());
            }
        }
    }
}
