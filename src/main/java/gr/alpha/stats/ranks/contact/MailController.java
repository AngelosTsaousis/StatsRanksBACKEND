package gr.alpha.stats.ranks.contact;

import gr.alpha.stats.ranks.DTOObjects.MailRequestDTO;
import gr.alpha.stats.ranks.recaptcha.RecaptchaService;
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

    @Autowired
    private RecaptchaService recaptchaService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMail(@RequestBody MailRequestDTO request) {
        boolean valid = recaptchaService.verify(request.getRecaptchaToken());
        if (!valid) {
            return ResponseEntity.status(403).body("reCAPTCHA verification failed");
        }
        mailService.sendEmail(request.getName(), request.getEmail(), request.getMessage());
        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "Email sent successfully");
        return ResponseEntity.ok(response);
    }
}
