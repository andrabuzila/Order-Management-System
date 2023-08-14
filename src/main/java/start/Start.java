package start;
import bll.ClientBLL;
import bll.ProdusBLL;
import connection.ConnectionFactory;
import controller.ClassController;
import model.Client;
import model.Produs;
import view.ClassView;
import view.ClassViewCom;
import view.ClassViewP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */

public class Start {

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        /*ClientBLL cl = new ClientBLL();
        ProdusBLL pl = new ProdusBLL();
        List<Client> clients = new ArrayList<>();
        clients=cl.viewAll();
        for(Client c: clients){
            System.out.println(c.getName());
        }

        List<Produs> produse = new ArrayList<>();
        produse=pl.viewAll();
        for(Produs p: produse){
            System.out.println(p.getNume());
        }

        Client c1 = new Client();
        c1.setId(2);
        c1.setName("ana");
        c1.setAddress("satu mare");
        c1.setEmail("ana@yahoo.com");
        c1.setAge(22);
        //cl.insert(c1);
        clients=cl.viewAll();
        for(Client c: clients){
            System.out.println(c.getName());
        }
        //cl.delete(c1);
        clients=cl.viewAll();
        for(Client c: clients){
            System.out.println(c.getName());
        }
        List<String> campuri = new ArrayList<>();
        campuri.add("name");
        campuri.add("email");
        c1.setName("ani");
        c1.setEmail("anii@yahoo");
        cl.update(c1, 2, campuri);
        clients=cl.viewAll();
        for(Client c: clients){
            System.out.println(c.getName());
        }*/

        ClassView clientul = new ClassView();
        clientul.setVisible(true);
        ClassViewP produsul = new ClassViewP();
        produsul.setVisible(true);
        ClassViewCom comand = new ClassViewCom();
        comand.setVisible(true);
        ClassController control = new ClassController(clientul, produsul, comand);
    }
}