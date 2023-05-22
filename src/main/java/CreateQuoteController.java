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

    }
}
