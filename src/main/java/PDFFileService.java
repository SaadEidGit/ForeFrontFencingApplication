import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PDFFileService
{
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
        this.filledForm += formName + ".pdf";
    }

    public void fillForm(SavePDFEvent event) throws IOException{
        PDDocument pdfDocument = PDDocument.load(new File(formTemplate));
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        // as there might not be an AcroForm entry a null check is necessary
        if (acroForm!= null)
        {
            // Retrieve an individual field and set its value.
            PDField field = acroForm.getField("Given Name Text Box");
            field.setValue("Text Entry");

            field = acroForm.getField("Family Name Text Box");
            field.setValue("Text Entry");

            //To be completed.
        }
        // Save and close the filled out form.
        pdfDocument.save(filledForm);
        pdfDocument.close();
    }

    public static void main(String[] args) throws IOException {
        // load the document
        PDDocument pdfDocument = PDDocument.load(new File(formTemplate));
        // get the document catalog
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        // as there might not be an AcroForm entry a null check is necessary
        if (acroForm != null)
        {
            // Retrieve an individual field and set its value.
            PDField field = acroForm.getField("Given Name Text Box");
            field.setValue("Text Entry");

            field = acroForm.getField("Family Name Text Box");
            field.setValue("Text Entry");

            // If a field is nested within the form tree a fully qualified name
            // might be provided to access the field.
            //field = acroForm.getField( "fieldsContainer.nestedSampleField" );
            //field.setValue("Text Entry");
        }
        // Save and close the filled out form.
        pdfDocument.save(filledForm);
        pdfDocument.close();
    }

//Use this method to find out all the fields in the PDF document.
//    public static void main(String[] args) {
//        try (PDDocument document = PDDocument.load(new File("C:\\Users\\saad_\\Desktop\\ForeFrontFencingApplication\\Documents\\FillablePDF.pdf"))) {
//            PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();
//
//            if (acroForm != null) {
//                for (PDField field : acroForm.getFields()) {
//                    System.out.println("Field Name: " + field.getFullyQualifiedName());
//                }
//            } else {
//                System.out.println("No form fields found in the PDF.");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}