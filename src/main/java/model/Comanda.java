package model;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class Comanda {
    private int id;
    private int idClient;
    private int idProdus;
    private int cantitate;

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * @param idClient
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     * @return
     */
    public int getIdProdus() {
        return idProdus;
    }

    /**
     * @param idProdus
     */
    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    /**
     * @return
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * @param cantitate
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }
}
