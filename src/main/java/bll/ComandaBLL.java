package bll;

import dao.ComandaDAO;
import model.Client;
import model.Comanda;
import validators.CantitateComandaValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class ComandaBLL {
    private ComandaDAO comdao;
    private List<Validator<Comanda>> validators;

    /**
     *
     */
    public ComandaBLL(){
        validators=new ArrayList<Validator<Comanda>>();
        validators.add(new CantitateComandaValidator());
        comdao=new ComandaDAO();
    }

    /**
     * @return
     */
    public List<Comanda> viewAll(){
        return comdao.viewAll();
    }

    /**
     * @param c
     */
    public void insert(Comanda c){
        comdao.insert(c);
    }

    /**
     * @param c
     */
    public void delete(Comanda c){
        comdao.delete(c.getId());
    }

}
