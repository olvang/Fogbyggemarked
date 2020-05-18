package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

/**
 * <p>Component used to validate a city string</p>
 */
public class InclineComponent implements Component {
    int incline;

    /**
     * Constructor for the component
     * Calls the validate function
     * @param incline The incline int to validate
     */
    public InclineComponent(int incline) throws ValidationFailedException {
        this.incline = incline;
        validate();
    }

    /**
     * Validates the incline int
     * @return True if the incline int validates according to the rules
     * @exception ValidationFailedException Thrown if the incline int trying to be validated does not comply with the rules
     */
    @Override
    public boolean validate() throws ValidationFailedException {
        //incline is 0 if the roof is flat
        if(incline == 0) {
            return true;
        }else if(incline > 25) {
            throw new ValidationFailedException("Tagets vinkel må ikke være over 25 grader.");
        } else if(incline < 20) {
            throw new ValidationFailedException("Tagets vinkel må ikke være under 20 grader.");
        }
        return true;
    }

    public void setIncline(int incline) throws ValidationFailedException {
        int inclineTemp = incline;
        this.incline = incline;
        try {
            validate();
        } catch (ValidationFailedException e) {
            this.incline = inclineTemp;
            throw new ValidationFailedException(e.getMessage());
        }
    }

    public int getIncline() {
        return incline;
    }
}
