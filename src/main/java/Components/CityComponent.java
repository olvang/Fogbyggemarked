package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

/**
 * <p>Component used to validate a city string</p>
 */
public class CityComponent implements Component {
    String city;

    /**
     * Constructor for the component
     * Calls the validate function
     * @param city The city string to validate
     */
    public CityComponent(String city) throws ValidationFailedException {
        this.city = city;
        validate();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws ValidationFailedException {
        String old = city;
        this.city = city;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.city = old;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    /**
     * Validates the city string
     * @return True if the city string validates according to the rules
     * @exception ValidationFailedException Thrown if the city string trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        if(city == null || city == "") {
            throw new ValidationFailedException("By må ikke være tom.");
        }
        String match = "^[a-zA-ZÆØÅæøåÀ-ÿ]+(([' -][a-zA-ZÆØÅæøåÀ-ÿ ])?[a-zA-ZÆØÅæøåÀ-ÿ]*)*$";
        if(Pattern.matches(match, city)) {
            return true;
        }
        throw new ValidationFailedException("By er ikke formateret korrekt.");
    }
}
