package gr.alpha.stats.ranks.contact;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String subject, String message, String replyTo) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setTo("info@statsranks.gr");
            helper.setFrom("info@statsranks.gr");
            helper.setReplyTo(replyTo);
            helper.setSubject(subject);
            helper.setText(message);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }

    }
}
