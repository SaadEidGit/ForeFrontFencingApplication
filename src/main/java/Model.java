import java.io.IOException;
import java.util.ArrayList;

public class Model {
    public double totalPrice, linearSquareFootPrice;
    public ArrayList<FormView> views;
    private ArrayList<Side> sides;
    private ArrayList<Gate> gates;
    private ArrayList<GateWall> gateWalls;
    private ExcelFileService excelFileService;
    private PDFFileService pdfFileService;
    private String filePath = "C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\Client.xlsx";

    public Model(){
        this.totalPrice = 0;
        this.views = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.gateWalls = new ArrayList<>();
        this.excelFileService = new ExcelFileService();
        this.pdfFileService = new PDFFileService();
    }

    public ArrayList<Side> getSides() {
        return sides;
    }

    public ArrayList<Gate> getGates() {
        return gates;
    }

    public ArrayList<GateWall> getGateWalls() {
        return gateWalls;
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

    public void addGateWall(GateWall gateWall){
        gateWalls.add(gateWall);
    }

    public void removeLastSide(Side side){
        if (sides.size() > 0){
            int lastIndex = sides.size() - 1;
            if (lastIndex >= 0){
                sides.remove(lastIndex);
            }
        }
    }

    public void removeLastGate(Gate gate){
        if (gates.size() > 0){
            int lastIndex = gates.size() - 1;
            if (lastIndex >= 0){
                gates.remove(lastIndex);
            }
        }
    }

    public void removeLastGateWall(GateWall gatewall){
        if (gateWalls.size() > 0){
            int lastIndex = gateWalls.size() - 1;
            if (lastIndex >= 0){
                gateWalls.remove(lastIndex);
            }
        }
    }

    public void setLinearSquareFootPrice(double price){
       this.linearSquareFootPrice = price;
    }

    public void calculateTotalPrice(double taxPercentage){
        // Reset totalPrice each time
        this.totalPrice = 0;
        for(Side side: sides){
            totalPrice += side.getSideLength() * this.linearSquareFootPrice;
        }

        for(Gate gate: gates){
            totalPrice += gate.getGatePrice();
        }

        for(GateWall gateWall: gateWalls){
            totalPrice += gateWall.getGateWallPrice();
        }

        totalPrice = totalPrice + calculateTax(totalPrice, taxPercentage);
    }

    /**
     * Does not include taxes
     */
    public int calculateSubTotalPrice(){
        int subTotalPrice = 0;
        for(Side side: sides){
            subTotalPrice += side.getSideLength() * this.linearSquareFootPrice;
        }

        for(Gate gate: gates){
            subTotalPrice += gate.getGatePrice();
        }

        for(GateWall gateWall: gateWalls){
            subTotalPrice += gateWall.getGateWallPrice();
        }

        return subTotalPrice;
    }

    /**
     * takes the name of the new file and adds it to the file path for the save pdf
     * @param fileName
     */
    public void constuctPDFFilePath(String fileName) {
        pdfFileService.constructFilePath(fileName);
    }

    public void fillPDFForm(SavePDFEvent event) throws IOException {
        pdfFileService.fillForm(event);
    }

    public void setExcelFilePath(String filePath) throws IOException {
        FilePath.setExcelFilePath(filePath);
    }

    public void setPDFFilePath(String filePath) throws IOException {
        FilePath.setPDFFilePath(filePath);
    }

    public void setFilledPDFFilePath(String filePath) throws IOException {
        FilePath.setFilledPDFFilePath(filePath);
    }

    public void resetSides() {
        sides.clear();
    }

    public void resetGates() {
        gates.clear();
    }

    public void resetGateWalls() {
        gateWalls.clear();
    }
}
