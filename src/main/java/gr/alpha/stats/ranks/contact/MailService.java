package gr.alpha.stats.ranks.contact;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.Logger;

@Service
class MailService {
    private static final Logger logger = Logger.getLogger(MailService.class.getName());

    private final SendGrid sendGrid;

    public MailService(@Value("${sendgrid.api.key}") String sendGridApiKey) {
        this.sendGrid = new SendGrid(sendGridApiKey);
    }

    public void sendEmail(String name, String email, String message) {
        Email from = new Email("info@statsranks.gr");
        Email to = new Email("info@statsranks.gr");
        Email replyTo = new Email(email);
        String subject = "Mail from statsranks.gr form. From: " + name;
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);
        mail.setReplyTo(replyTo);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if (response.getStatusCode() == 202) {
                logger.info("Email sent successfully: " + response.getStatusCode());
            } else {
                logger.warning("Email send failed: " + response.getStatusCode() +
                        " - Body: " + response.getBody());
            }
        } catch (IOException ex) {
            logger.severe("Error sending email: " + ex.getMessage());
        }
    }
}
