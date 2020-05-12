package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

public class AddressComponent implements Component {
    String address;

    public AddressComponent(String address) throws ValidationFailedException {
        this.address = address;
        validate();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws ValidationFailedException {
        String old = address;
        this.address = address;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.address = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if(address == null || address == "") {
            throw new ValidationFailedException("Adresse må ikke være tom.");
        }
        String match = "^(.+) (\\d{1,3}[a-z]?) (\\d{1,2}|st|kl)? ?(\\d{1,2}|tv|mf|th)?.*(\\d{4})$";
        if(Pattern.matches(match, address)) {
            return true;
        }
        throw new ValidationFailedException("Adressen må kun indeholde tal og bogstaver");
    }
}
