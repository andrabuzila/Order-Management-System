package bll;

import dao.ProdusDAO;
import model.Client;
import model.Produs;
import validators.CantitateProdusValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class ProdusBLL {
    private ProdusDAO pdao;
    private List<Validator<Produs>> validators;

    /**
     *
     */
    public ProdusBLL(){
        validators = new ArrayList<Validator<Produs>>();
        validators.add(new CantitateProdusValidator());
        pdao = new ProdusDAO();
    }

    /**
     * @param id
     * @return
     */
    public Produs findById(int id){
        Produs p = pdao.findById(id);
        if(p == null)
            throw new NoSuchElementException("No such element");
        return p;
    }

    /**
     * @return
     */
    public List<Produs> viewAll(){
        return pdao.viewAll();
    }

    /**
     * @param p
     */
    public void insert(Produs p){
        pdao.insert(p);
    }

    /**
     * @param p
     */
    public void delete(Produs p){
        pdao.delete(p.getId());
    }

    /**
     * @param p
     * @param id
     * @param campuri
     */
    public void update(Produs p, int id, List<String> campuri){
        pdao.update(p,id,campuri);
    }
}
