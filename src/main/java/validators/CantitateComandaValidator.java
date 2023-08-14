package validators;

import model.Comanda;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class CantitateComandaValidator implements Validator<Comanda> {
    private static int cantMin = 1;

    /**
     * @param comanda
     */
    @Override
    public void validate(Comanda comanda) {
        if(comanda.getCantitate()<cantMin)
            throw new IllegalArgumentException("Cantitate prea mica.");
    }
}
