import java.util.EventObject;

public class UserRegistrationFormEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param model the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public UserRegistrationFormEvent(Model model) {
        super(model);
    }
}
