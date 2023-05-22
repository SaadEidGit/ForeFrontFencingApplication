import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import java.io.File;
import java.io.IOException;

public class PDFFileService
{
    public static void main(String[] args) throws IOException
    {
        String formTemplate = "src/main/resources/org/apache/pdfbox/examples/acroforms/FillFormField.pdf";
        String filledForm = "target/examples-output/FillFormField.pdf";

        // load the document
        PDDocument pdfDocument = PDDocument.load(new File(formTemplate), (MemoryUsageSetting) null);
        // get the document catalog
        PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

        // as there might not be an AcroForm entry a null check is necessary
        if (acroForm != null)
        {
            // Retrieve an individual field and set its value.
            PDField field = acroForm.getField( "sampleField" );
            field.setValue("Text Entry");
            // If a field is nested within the form tree a fully qualified name
            // might be provided to access the field.
            field = acroForm.getField( "fieldsContainer.nestedSampleField" );
            field.setValue("Text Entry");
        }
        // Save and close the filled out form.
        pdfDocument.save(filledForm);
        pdfDocument.close();
    }
}