package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

public class ZipCodeComponent implements Component {
    String zip;

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
