package model;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class Produs {
    private int id;
    private String nume;
    private int cantitate;

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param idprodus
     */
    public void setId(int idprodus) {
        this.id = idprodus;
    }

    /**
     * @return
     */
    public String getNume() {
        return nume;
    }

    /**
     * @param nume
     */
    public void setNume(String nume) {
        this.nume = nume;
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
