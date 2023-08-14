package validators;

import model.Client;

/**
 * @Author Andra Buzila
 * @Since
 */
public class AgeValidator implements Validator<Client> {
    private static int minAge = 14;

    /**
     * @param c
     */
    public void validate(Client c){
        if(c.getAge()<minAge){
            throw new IllegalArgumentException("Varsta clientului este prea mica.");
        }
    }
}
