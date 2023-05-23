import java.util.ArrayList;

public class Model {
    public double totalPrice, linearSquareFootPrice;
    public ArrayList<FormView> views;
    private ArrayList<Side> sides;
    private ArrayList<Gate> gates;
    private ExcelFileService excelFileService;
    private String filePath = "C:\\Users\\saad_\\OneDrive\\Documents\\Client.xlsx";

    public Model(){
        this.totalPrice = 0;
        this.views = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.excelFileService = new ExcelFileService();
    }

    public void addView(FormView v){
        views.add(v);
    }

    public double getTotalPrice() {
        return totalPrice;
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

    public double calculateTotalPrice(double taxPercentage){
        for(Side side: sides){
            totalPrice += side.getSideLength() * this.linearSquareFootPrice;
        }

        for(Gate gate: gates){
            totalPrice += gate.getGatePrice();
        }

        return totalPrice + calculateTax(totalPrice, taxPercentage);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
