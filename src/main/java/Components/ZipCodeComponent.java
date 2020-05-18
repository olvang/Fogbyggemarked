package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

/**
 * <p>Component used to validate a Zip code int</p>
 */
public class ZipCodeComponent implements Component {
    String zip;

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * @param zip Zip code string to validate
     */
    public ZipCodeComponent(String zip) throws ValidationFailedException {
        this.zip = zip;
        validate();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) throws ValidationFailedException {
        String old = zip;
        this.zip = zip;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.zip = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    /**
     * <p>Validates the Zip code</p>
     * @return True if the Zip code validates according to the rules
     * @exception ValidationFailedException Thrown if the Zip code trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        if(zip == null || zip == "") {
            throw new ValidationFailedException("Postnummer må ikke være tom.");
        }
        String match = "([1-9][0-9][0-9][0-9])";
        if(Pattern.matches(match, zip)) {
            return true;
        }
        throw new ValidationFailedException("Postnummer skal indeholde 4 tal.");
    }
}
