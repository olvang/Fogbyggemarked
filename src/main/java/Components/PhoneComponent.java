package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

/**
 * <p>Component used to validate a Phone String</p>
 */
public class PhoneComponent implements Component {

    String phone;

    /**
     * Constructor for the component
     * Calls the validate function
     * @param phone Phone string to validate
     */
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

    /**
     * Validates the Phone
     * @return True if the Phone string validates according to the rules
     * @exception ValidationFailedException Thrown if the Phone string trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        if(phone == null || phone == "") {
            throw new ValidationFailedException("Telefon nummer må ikke være tom.");
        }
        String match = "([1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])";
        if(Pattern.matches(match, phone)) {
            return true;
        }
        throw new ValidationFailedException("Telefon nummer skal indeholde 8 tal.");
    }
}
