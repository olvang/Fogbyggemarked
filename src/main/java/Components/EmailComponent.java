package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

public class EmailComponent implements Component {

    String email;

    public EmailComponent(String email) throws ValidationFailedException {
        this.email = email;
        validate();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws ValidationFailedException {
        String old = email;
        this.email = email;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.email = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if(email == null || email == "") {
            throw new ValidationFailedException("Email må ikke være tom.");
        }
        email = email.toLowerCase();
        String match = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
                + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:"
                + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b"
                + "\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])"
                + "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
                + "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2"
                + "[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4]"
                + "[0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
                + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a"
                + "\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])"
                + "+)])";
        if(Pattern.matches(match, email)) {
            return true;
        }
        throw new ValidationFailedException("Email er ikke formateret korrekt.");
    }
}