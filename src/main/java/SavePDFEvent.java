import java.util.EventObject;

public class SavePDFEvent {

    private Model model;
    private Client client;
    private Date date;
    private double pricePerLinearFoot, taxPercentage;
    private String fenceColour, contractNumber;


    public SavePDFEvent(Model model, Client client, Date date, double pricePerLinearFoot, double taxPercentage, String fenceColour, String contractNumber) {
        this.model = model;
        this.client = client;
        this.date = date;
        this.pricePerLinearFoot = pricePerLinearFoot;
        this.taxPercentage = taxPercentage;
        this.fenceColour = fenceColour;
        this.contractNumber = contractNumber;
    }

    public Model getModel() {
        return model;
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

    public String getFenceColour() {
        return fenceColour;
    }

    public String getContractNumber() {
        return contractNumber;
    }
}
