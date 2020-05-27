package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

public class NameComponent implements Component {

    String name;

    public NameComponent(String name) throws ValidationFailedException {
        this.name = name;
        validate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws ValidationFailedException {
        String old = this.name;
        this.name = name;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.name = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    @Override
    public boolean validate() throws ValidationFailedException {
        if(name == null || name == "") {
            throw new ValidationFailedException("Du skal indtaste et navn");
        }
        String test = "^[a-zA-ZÆØÅæøåÀ-ÿ]+(([' -][a-zA-ZÆØÅæøåÀ-ÿ ])?[a-zA-ZÆØÅæøåÀ-ÿ]*)*$";
        boolean matches = Pattern.matches(test, name);
        if(matches) {
            return true;
        }
        throw new ValidationFailedException("Navn må kun indeholde bogstaver.");
    }
}
