package bll;

import dao.ClientDAO;
import model.Client;
import validators.AgeValidator;
import validators.EmailValidator;
import validators.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class ClientBLL {
    private ClientDAO cdao;
    private List<Validator<Client>> validators;

    /**
     *
     */
    public ClientBLL(){
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new AgeValidator());
        cdao = new ClientDAO();
    }

    /**
     * @param id
     * @return
     */
    public Client findById(int id){
        Client cl = cdao.findById(id);
        if(cl == null)
            throw new NoSuchElementException("No such element");
        return cl;
    }

    /**
     * @return
     */
    public List<Client> viewAll(){
        return cdao.viewAll();
    }

    /**
     * @param c
     */
    public void insert(Client c){
        cdao.insert(c);
    }

    /**
     * @param c
     */
    public void delete(Client c){
        cdao.delete(c.getId());
    }

    /**
     * @param c
     * @param id
     * @param campuri
     */
    public void update(Client c, int id, List<String> campuri){
        cdao.update(c,id,campuri);
    }


}
