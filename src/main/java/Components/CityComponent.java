package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

import java.util.regex.Pattern;

public class CityComponent implements Component {
    String city;

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
