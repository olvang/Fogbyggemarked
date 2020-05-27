package Components;

import FunctionLayer.Exceptions.ValidationFailedException;
import FunctionLayer.Log;

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
        String old = this.address;
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
            Log.fine("Address component: Address cannot be empty");
            throw new ValidationFailedException("Adresse må ikke være tom.");
        }
        String match = "^([a-zA-ZæøåÆØÅ]+) (\\d{1,3}|[a-zA-ZæøåÆØÅ]?),? ?([0-9a-zA-ZæøåÆØÅ .]+)?$";
        if(Pattern.matches(match, address)) {
            return true;
        }
        Log.fine("Address component: Address cannot only handle numbers and letters");
        throw new ValidationFailedException("Adressen må kun indeholde tal og bogstaver");
    }
}
