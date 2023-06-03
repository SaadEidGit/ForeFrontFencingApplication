import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFFileService
{
    private String formName;
    //private static String formTemplate = "C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\FillablePDF.pdf";
    private static String formTemplate;

    static {
        try {
            formTemplate = FilePath.getPDFFilePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //private static String filledForm = "C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\";


    private static String filledForm;
    static {
        try {
            filledForm = FilePath.getFilledPDFFormFilePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void constructFilePath(String formName) {
        this.formName = formName;
        this.filledForm += formName + ".pdf";
    }

    public void deconstructFilePath(String formName) {
        //remove formName from this.filledFrom
        this.filledForm = this.filledForm.replace(formName + ".pdf", "");

    }
//    public String constructFilePath(String formName){
//        return this.filledForm + formName;
//    }

    public void fillForm(SavePDFEvent event) throws IOException{
        PDDocument pdfDocument = PDDocument.load(new File(formTemplate));
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        // Get today's date
        LocalDate today = LocalDate.now();
        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format the date using the formatter
        String formattedDate = today.format(formatter);

        // as there might not be an AcroForm entry a null check is necessary
        if (acroForm!= null)
        {
            // Retrieve an individual field and set its value.
            acroForm.getField("home_address_1").setValue(event.getClient().getAddress());
            acroForm.getField("colour").setValue(event.getFenceColour());
            acroForm.getField("today's_date_1").setValue(formattedDate);

            //sides
            if (event.getModel().getSides() != null) { //and size <= 4
                List<Side> sides = event.getModel().getSides();
                int i = 1;
                for (Side side : sides) {
                    acroForm.getField("side_" + i).setValue("Side " + i);
                    acroForm.getField("side_" + i + "_price").setValue(String.valueOf(side.getSideLength() * event.getModel().linearSquareFootPrice));
                    i++;
                }
            }

            //gates
            if (event.getModel().getGates() != null) { //and size <= 4
                List<Gate> gates = event.getModel().getGates();
                int i = 1;
                for (Gate gate : gates) {
                    acroForm.getField("gate_" + i).setValue("Gate " + i);
                    acroForm.getField("gate_" + i + "_price").setValue(String.valueOf(gate.getGatePrice()));
                    i++;
                }
            }

            //gatewall
            if (event.getModel().getGateWalls() != null) { //and size <= 4
                List<GateWall> gateWalls = event.getModel().getGateWalls();
                int i = 1;
                for (GateWall gateWall : gateWalls) {
                    acroForm.getField("gate_wall").setValue("GateWall");
                    acroForm.getField("gatewall_price").setValue(String.valueOf(gateWall.getGateWallPrice()));
                    i++;
                }
            }

            acroForm.getField("subtotal").setValue(String.valueOf(event.getModel().calculateSubTotalPrice()));
            acroForm.getField("hst").setValue(String.valueOf(event.getModel().calculateTax(event.getModel().totalPrice, event.getTaxPercentage())));
            acroForm.getField("total").setValue(String.valueOf(event.getModel().totalPrice));
            //acroForm.getField("linear_foot_price").setValue(String.valueOf(event.getPricePerLinearFoot()));
            acroForm.getField("document_name").setValue(this.formName);

            acroForm.getField("commence_date").setValue(event.getDate().toString());
            acroForm.getField("contract_number_1").setValue(String.valueOf(event.getContractNumber()));
            acroForm.getField("contract_number_2").setValue(String.valueOf(event.getContractNumber()));
            acroForm.getField("fence_height").setValue(event.getFenceHeight());
            acroForm.getField("note_1").setValue("* PVC Fence " + event.getFenceHeight() + " Height " + event.getFenceColour());
            acroForm.getField("note_2").setValue("* Linear feet cost " + event.getPricePerLinearFoot());
            acroForm.getField("cost_for_proj_hst").setValue(String.valueOf(event.getModel().totalPrice));

            double payment1 = Math.floor(event.getModel().totalPrice * 0.2);
            double payment2 = Math.floor(event.getModel().totalPrice * 0.4);
            acroForm.getField("upon_acceptance").setValue(String.valueOf(payment1));
            acroForm.getField("upon_metal").setValue(String.valueOf(payment2));
            acroForm.getField("upon_completion").setValue(String.valueOf((event.getModel().totalPrice) - (payment1 + payment2)));
            acroForm.getField("today's_date_2").setValue(formattedDate);
            acroForm.getField("home_address_2").setValue(event.getClient().getAddress());
        }
        // Save and close the filled out form.
        pdfDocument.save(filledForm);
        pdfDocument.close();
        deconstructFilePath(this.formName);
    }

//    public static void main(String[] args) throws IOException {
//        // load the document
//        PDDocument pdfDocument = PDDocument.load(new File(formTemplate));
//        // get the document catalog
//        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();
//
//        // as there might not be an AcroForm entry a null check is necessary
//        if (acroForm != null)
//        {
//            // Retrieve an individual field and set its value.
//            PDField field = acroForm.getField("Given Name Text Box");
//            field.setValue("Text Entry");
//
//            field = acroForm.getField("Family Name Text Box");
//            field.setValue("Text Entry");
//
//            // If a field is nested within the form tree a fully qualified name
//            // might be provided to access the field.
//            //field = acroForm.getField( "fieldsContainer.nestedSampleField" );
//            //field.setValue("Text Entry");
//        }
//        // Save and close the filled out form.
//        pdfDocument.save(filledForm);
//        pdfDocument.close();
//    }

//Use this method to find out all the fields in the PDF document.
    public static void main(String[] args) {
        try (PDDocument document = PDDocument.load(new File("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\Fence Installation Proposal And Agreement.pdf"))) {
            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {
                for (PDField field : acroForm.getFields()) {
                    System.out.println("Field Name: " + field.getFullyQualifiedName());
                }
            } else {
                System.out.println("No form fields found in the PDF.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}