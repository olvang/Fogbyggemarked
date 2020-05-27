package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

/**
 * <p>Component used to validate a Name String</p>
 */
public class NameComponent implements Component {

    String name;

    /**
     * <p>Constructor for the component</p>
     * <p>Calls the validate function</p>
     * @param name Name in string to validate
     */
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

    /**
     * <p>Validates a Name string</p>
     * @return True if the Name validates according to the rules
     * @exception ValidationFailedException Thrown if the Name trying to be validated does not comply with the rules
     */
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
