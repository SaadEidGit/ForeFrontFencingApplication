import java.util.EventObject;

public class SavePDFEvent extends EventObject {

    private Client client;
    private Date date;
    private double pricePerLinearFoot, taxPercentage;
    private int dateDay, dateYear;
    private String dateMonth, fenceColour;
    /**
     * Constructs a prototypical Event.
     *
     * @param model the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public SavePDFEvent(Model model, Client client, Date date, double pricePerLinearFoot, double taxPercentage,int dateDay, String dateMonth,int dateYear, String fenceColour) {
        super(model);
        this.client = client;
        this.date = date;
        this.pricePerLinearFoot = pricePerLinearFoot;
        this.taxPercentage = taxPercentage;
        this.dateDay = dateDay;
        this.dateMonth = dateMonth;
        this.dateYear = dateYear;
        this.fenceColour = fenceColour;
    }

    public Client getClient() {
        return client;
    }

    public Date getDate() {
        return date;
    }

    public double getPricePerLinearFoot() {
        return pricePerLinearFoot;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public int getDateDay() {
        return dateDay;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public int getDateYear() {
        return dateYear;
    }
}
