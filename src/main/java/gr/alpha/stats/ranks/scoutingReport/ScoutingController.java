package gr.alpha.stats.ranks.scoutingReport;

import org.springframework.stereotype.Controller;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scouting")
class ScoutingController {

    private final ScoutingService scoutingService;

    /**
     * Constructor for ScoutingController.
     *
     * @param scoutingService the service to handle scouting-related operations
     */
    public ScoutingController(ScoutingService scoutingService) {
        this.scoutingService = scoutingService;
    }

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generatePdf() {
        byte[] pdfBytes = scoutingService.generatePdf("Sample PDF Title", "This is a sample content for the PDF file.");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "document.pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

}
