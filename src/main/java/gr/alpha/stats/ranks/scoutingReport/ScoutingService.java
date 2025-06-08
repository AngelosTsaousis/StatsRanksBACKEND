package gr.alpha.stats.ranks.scoutingReport;

import org.springframework.stereotype.Service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
class ScoutingService {

    public byte[] generatePdf(String title, String content) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Add title
            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph titleParagraph = new Paragraph(title, titleFont);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            document.add(titleParagraph);
            document.add(Chunk.NEWLINE);

            // Add content
            Font contentFont = new Font(Font.HELVETICA, 12);
            Paragraph contentParagraph = new Paragraph(content, contentFont);
            document.add(contentParagraph);

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
