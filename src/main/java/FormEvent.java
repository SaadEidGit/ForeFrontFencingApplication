import java.util.EventObject;

public class FormEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param model the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FormEvent(Model model) {
        super(model);
    }
}
