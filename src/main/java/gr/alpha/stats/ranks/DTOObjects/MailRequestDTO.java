package gr.alpha.stats.ranks.DTOObjects;

public class MailRequestDTO {
    private String name;
    private String email;
    private String message;
    private String recaptchaToken;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRecaptchaToken() {
        return recaptchaToken;
    }

    public String getMessage() {
        return message;
    }
}
