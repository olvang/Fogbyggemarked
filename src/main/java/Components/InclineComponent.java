package Components;

import FunctionLayer.Exceptions.ValidationFailedException;

public class InclineComponent implements Component {
    int incline;

    public InclineComponent(int incline) throws ValidationFailedException {
        this.incline = incline;
        validate();
    }

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
