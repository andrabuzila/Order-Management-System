package validators;

import model.Produs;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class CantitateProdusValidator implements Validator<Produs> {
    private static int cantitateMin=1;

    /**
     * @param produs
     */
    @Override
    public void validate(Produs produs) {
        if(produs.getCantitate()<cantitateMin)
            throw new IllegalArgumentException("Cantitate prea mica.");

    }
}
