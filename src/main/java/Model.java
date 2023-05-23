import java.io.IOException;
import java.util.ArrayList;

public class Model {
    public double totalPrice, linearSquareFootPrice;
    public ArrayList<FormView> views;
    private ArrayList<Side> sides;
    private ArrayList<Gate> gates;
    private ExcelFileService excelFileService;
    private PDFFileService pdfFileService;
    private String filePath = "C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\Client.xlsx";

    public Model(){
        this.totalPrice = 0;
        this.views = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.excelFileService = new ExcelFileService();
        this.pdfFileService = new PDFFileService();
    }

    public void addView(FormView v){
        views.add(v);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double calculateTax(double amount, double taxPercentage){
        return amount*(taxPercentage/100);
    }

    public void addClientToExcel(Client client){
        excelFileService.addRows(filePath, client);
    }

    public void addSide(Side side){
        sides.add(side);
    }

    public void addGate(Gate gate){
        gates.add(gate);
    }
    public void removeLastSide(Side side){
        int lastIndex = sides.size() - 1;
        sides.remove(lastIndex);
    }

    public void removeLastGate(Gate gate){
        int lastIndex = gates.size() - 1;
        gates.remove(lastIndex);
    }

    public void setLinearSquareFootPrice(double price){
       this.linearSquareFootPrice = price;
    }

    public void calculateTotalPrice(double taxPercentage){
        for(Side side: sides){
            totalPrice += side.getSideLength() * this.linearSquareFootPrice;
        }

        for(Gate gate: gates){
            totalPrice += gate.getGatePrice();
        }

        totalPrice = totalPrice + calculateTax(totalPrice, taxPercentage);
    }

    public void setPDFFilePath(String filePath) {
        pdfFileService.constructFilePath(filePath);
    }

    public void fillPDFForm(SavePDFEvent event) throws IOException {
        pdfFileService.fillForm(event);
    }

    public void setExcelFilePath(String filePath) {
        this.filePath = filePath;
    }
}
