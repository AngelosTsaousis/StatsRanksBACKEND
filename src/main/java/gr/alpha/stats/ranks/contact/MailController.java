package gr.alpha.stats.ranks.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    public static class MailRequest {
        public String name;
        public String email;
        public String message;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMail(@RequestBody MailRequest request) {
        String subject = "Website Contact from " + request.name;
        String body = "Name: " + request.name + "\n" +
                "Email: " + request.email + "\n\n" +
                request.message;
        mailService.sendEmail(subject, body, request.email);
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "Email sent successfully");
        return ResponseEntity.ok(response);
    }
}
