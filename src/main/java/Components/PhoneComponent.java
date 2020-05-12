package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

public class PhoneComponent implements Component {

    String phone;

    public PhoneComponent(String phone) throws ValidationFailedException {
        this.phone = phone;
        validate();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws ValidationFailedException {
        String old = phone;
        this.phone = phone;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.phone = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if(phone == null || phone == "") {
            throw new ValidationFailedException("Telefon nummer må ikke være tom.");
        }
        String match = "([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])";
        if(Pattern.matches(match, phone)) {
            return true;
        }
        throw new ValidationFailedException("Telefon nummer skal indeholde 8 tal.");
    }
}
