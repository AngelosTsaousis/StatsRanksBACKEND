package gr.alpha.stats.ranks.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMail(@RequestBody MailRequest request) {
        mailService.sendEmail(request.name, request.email, request.message);
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "Email sent successfully");
        return ResponseEntity.ok(response);
    }
}
