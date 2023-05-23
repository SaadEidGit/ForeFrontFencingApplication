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
        }else if(e.getSource() == frame.quoteButton){
            //frame.addGate();
        }else if(e.getSource() == frame.saveQuoteButton){
            //frame.removeLastPanel();
        }else if(e.getSource() == frame.resetButton){
            frame.resetCreateQuoteForm(frame.getTextFieldsFromPanel(frame.mainSubPane), frame.getTextFieldsFromScrollPane(frame.scrollPane));
        }
    }
}
