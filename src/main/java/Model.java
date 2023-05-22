import java.util.ArrayList;

public class Model {
    public double totalPrice;
    public ArrayList<UserRegistrationFormView> views;
    private ExcelFileService excelFileService;

    private String filePath = "C:\\Users\\saad_\\OneDrive\\Documents\\Client.xlsx";

    public Model(){
        this.views = new ArrayList<>();
        this.totalPrice = 0;
        this.excelFileService = new ExcelFileService();
    }

    public void addView(UserRegistrationFormView v){
        views.add(v);
    }

    public double calculateSidePrice(double length, double linearFootPrice){
        return length*linearFootPrice;
    }

    public double calculateTax(double amount, double taxPercentage){
        return amount*taxPercentage;
    }

    public void addClientToExcel(Client client){
        excelFileService.addRows(filePath, client);
    }
}
