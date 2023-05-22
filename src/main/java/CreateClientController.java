import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateClientController implements ActionListener {

    public Model model;
    public Form frame;

    public CreateClientController(Model model, Form frame){
        this.model = model;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == frame.createNewClientButton){
            JOptionPane.showMessageDialog(frame, "Please make sure all fields are filled in and the excel file is closed.");
            model.addClientToExcel(new Client(frame.firstNameCreateUserField.getText(),
                    frame.lastNameCreateUserField.getText(),
                    frame.emailCreateUserField.getText(),
                    frame.phoneCreateUserField.getText(),
                    frame.addressCreateUserArea.getText()));

            frame.resetCreateUserForm();
            //frame.setStatus("Client created successfully!");
        }else if(e.getSource() == frame.resetCreateUserFormButton){
            frame.resetCreateUserForm();
        }

    }
}
