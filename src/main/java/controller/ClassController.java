package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import bll.ClientBLL;
import bll.ComandaBLL;
import bll.ProdusBLL;
import model.Client;
import model.Comanda;
import model.Produs;
import start.FileData;
import view.ClassView;
import view.ClassViewCom;
import view.ClassViewP;

import javax.net.ssl.CertPathTrustManagerParameters;
import javax.swing.*;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */

public class ClassController {
    private final ClassView viewClient;
    private final ClassViewP viewProdus;
    private final ClassViewCom viewComanda;
    private final ClientBLL cl= new ClientBLL();
    private final ProdusBLL pr = new ProdusBLL();
    private final ComandaBLL com = new ComandaBLL();

    /**
     * @param v1
     * @param v2
     * @param v3
     */
    public ClassController(ClassView v1, ClassViewP v2, ClassViewCom v3) {
        viewClient=v1;
        viewProdus=v2;
        viewComanda = v3;

        viewClient.addAfisListener(new afisareDate());
        viewClient.addInsertListener(new insertData());
        viewClient.addDeleteListener(new deleteData());
        viewClient.addUpdateListener(new updateData());

        viewProdus.addAfisListener1(new afisareDateProdus());
        viewProdus.addInsertListener1(new insertDataProdus());
        viewProdus.addDeleteListener1(new deleteDataProdus());
        viewProdus.addUpdateListener1(new updateDataProdus());

        viewComanda.addAfisListener2(new afisareDateComanda());
        viewComanda.addCreateListener2(new createComanda());
        viewComanda.addDeleteListener2(new deleteDataComanda());

    }

    /**
     *
     */
    class afisareDate implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Client> clients = new ArrayList<>();
            clients = cl.viewAll();
            List<String []> objects = new ArrayList<String[]>();
            for(Client cli: clients){
                String[] obj = {String.valueOf(cli.getId()),cli.getName(), cli.getAddress(), cli.getEmail(), String.valueOf(cli.getAge()) };
                objects.add(obj);
            }
            viewClient.setTabel(objects.toArray(new Object[][]{}));
        }
    }

    /**
     *
     */
    class insertData implements ActionListener{
        String user1Input="";
        String user2Input="";
        String user3Input="";
        String user4Input="";
        String user5Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewClient.getId();
            user2Input=viewClient.getNume();
            user3Input =viewClient.getAdresa();
            user4Input=viewClient.getEmail();
            user5Input=viewClient.getVarsta();
            try{
            Client c = new Client();
            int id = Integer.parseInt(user1Input);
            int varsta = Integer.parseInt(user5Input);
            c.setId(id);
            c.setName(user2Input);
            c.setAddress(user3Input);
            c.setEmail(user4Input);
            c.setAge(varsta);
            try{
                cl.insert(c);
                System.out.println("S-a inserat clientul cu succes!");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }

        }
    }

    /**
     *
     */
    class deleteData implements ActionListener{

        String user1Input="";
        String user2Input="";
        String user3Input="";
        String user4Input="";
        String user5Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewClient.getId();
            user2Input=viewClient.getNume();
            user3Input =viewClient.getAdresa();
            user4Input=viewClient.getEmail();
            user5Input=viewClient.getVarsta();
            try{
                Client c = new Client();
                int id = Integer.parseInt(user1Input);
                int varsta = Integer.parseInt(user5Input);
                c.setId(id);
                c.setName(user2Input);
                c.setAddress(user3Input);
                c.setEmail(user4Input);
                c.setAge(varsta);
                try{
                cl.delete(c);
                System.out.println("S-a sters clientul cu succes!");}
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }

    /**
     *
     */
    class updateData implements ActionListener{

        String user1Input="";
        String user2Input="";
        String user3Input="";
        String user4Input="";
        String user5Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewClient.getId();
            user2Input=viewClient.getNume();
            user3Input =viewClient.getAdresa();
            user4Input=viewClient.getEmail();
            user5Input=viewClient.getVarsta();

            try{
                Client c = new Client();
                int id = Integer.parseInt(user1Input);
                int varsta;
                if(user5Input!=null)
                    varsta = Integer.parseInt(user5Input);
                else varsta=0;
                c.setId(id);
                c.setName(user2Input);
                c.setAddress(user3Input);
                c.setEmail(user4Input);
                c.setAge(varsta);
                List<String> campuri = new ArrayList<>();
                List<Client> clients = cl.viewAll();
                Client clie = new Client();
                for(Client cli:clients){
                    if(cli.getId()==id){
                        clie.setId(id);
                        String nume= cli.getName();
                        String adresa=cli.getAddress();
                        String mail=cli.getEmail();
                        int v = cli.getAge();
                        if(user2Input!=null){
                             campuri.add("name");
                             clie.setName(user2Input);
                        }else
                            clie.setName(nume);
                        if(user3Input!=null){
                            campuri.add("address");
                            clie.setAddress(user3Input);
                        }else{
                            clie.setAddress(adresa);}
                        if(user4Input!=null){
                            campuri.add("email");
                            clie.setEmail(user4Input);
                        }else
                            clie.setEmail(mail);
                        if(user5Input!=null){
                            campuri.add("age");
                            clie.setAge(varsta);
                        }else
                            clie.setAge(v);
                        try{
                        cl.update(clie,id,campuri);
                        System.out.println("S-a modificat clientul cu succes!");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     *
     */
    class afisareDateProdus implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Produs> produse = new ArrayList<>();
            produse = pr.viewAll();
            List<String []> objects = new ArrayList<String[]>();
            for(Produs produ: produse){
                String[] obj = {String.valueOf(produ.getId()),produ.getNume(), String.valueOf(produ.getCantitate()) };
                objects.add(obj);
            }
            viewProdus.setTabel(objects.toArray(new Object[][]{}));
        }
    }

    /**
     *
     */
    class insertDataProdus implements ActionListener{
        String user1Input="";
        String user2Input="";
        String user3Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewProdus.getId();
            user2Input=viewProdus.getNume();
            user3Input =viewProdus.getCantitate();
            try{
                Produs p = new Produs();
                int id = Integer.parseInt(user1Input);
                int cantitate = Integer.parseInt(user3Input);
                p.setId(id);
                p.setNume(user2Input);
                p.setCantitate(cantitate);
                try{
                    pr.insert(p);
                    System.out.println("S-a inserat produsul cu succes!");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }

    /**
     *
     */
    class deleteDataProdus implements ActionListener{
        String user1Input="";
        String user2Input="";
        String user3Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewProdus.getId();
            user2Input=viewProdus.getNume();
            user3Input =viewProdus.getCantitate();
            try{
                Produs p = new Produs();
                int id = Integer.parseInt(user1Input);
                int cantitate = Integer.parseInt(user3Input);
                p.setId(id);
                p.setNume(user2Input);
                p.setCantitate(cantitate);
                try{
                    pr.delete(p);
                System.out.println("S-a sters produsul cu succes!");}
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }

    /**
     *
     */
    class updateDataProdus implements ActionListener{
        String user1Input="";
        String user2Input="";
        String user3Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewProdus.getId();
            user2Input=viewProdus.getNume();
            user3Input =viewProdus.getCantitate();

            try{
                int id = Integer.parseInt(user1Input);
                List<String> campuri = new ArrayList<>();
                List<Produs> produse = pr.viewAll();
                for(Produs prod:produse){
                    if(prod.getId()==id){
                        prod.setId(id);
                        String nume= prod.getNume();
                        int cantitate=prod.getCantitate();
                        if(user2Input!=null){
                            campuri.add("nume");
                            prod.setNume(user2Input);
                        }else
                            prod.setNume(nume);
                        if(user3Input!=null){
                            campuri.add("cantitate");
                            prod.setCantitate(Integer.parseInt(user3Input));
                        }else{
                            prod.setCantitate(cantitate);}
                        try{
                        pr.update(prod,id,campuri);
                        System.out.println("S-a modificat produsul cu succes!");}
                        catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /**
     *
     */
    class afisareDateComanda implements ActionListener{

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Comanda> comenzi = new ArrayList<>();
            comenzi = com.viewAll();
            List<String []> objects = new ArrayList<String[]>();
            for(Comanda comm: comenzi){
                String[] obj = {String.valueOf(comm.getId()),String.valueOf(comm.getIdClient()), String.valueOf(comm.getIdProdus()), String.valueOf(comm.getCantitate())};
                objects.add(obj);
            }
            viewComanda.setTabel(objects.toArray(new Object[][]{}));
        }
    }

    /**
     *
     */
    class deleteDataComanda implements ActionListener{

        String user1Input="";
        String user2Input="";
        String user3Input="";
        String user4Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewComanda.getId();
            user2Input=viewComanda.getIdCl();
            user3Input =viewComanda.getIdProd();
            user4Input=viewComanda.getCantitate();

            try{
                Comanda coma = new Comanda();
                int id = Integer.parseInt(user1Input);
                int idcl = Integer.parseInt(user2Input);
                int idp = Integer.parseInt(user3Input);
                int cantitate = Integer.parseInt(user4Input);
                coma.setId(id);
                coma.setIdClient(idcl);
                coma.setIdProdus(idp);
                coma.setCantitate(cantitate);
                try{
                    com.delete(coma);
                    System.out.println("S-a sters comanda cu succes!");}
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
    }

    /**
     *
     */
    class createComanda implements ActionListener{

        String user1Input="";
        String user2Input="";
        String user3Input="";
        String user4Input="";

        /**
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            user1Input=viewComanda.getId();
            user2Input=viewComanda.getIdCl();
            user3Input =viewComanda.getIdProd();
            user4Input=viewComanda.getCantitate();
            try{
                int cantitate = Integer.parseInt(user4Input);
                int id = Integer.parseInt(user1Input);
                int idCl = Integer.parseInt(user2Input);
                int idProd = Integer.parseInt(user3Input);
                FileData fdata = new FileData();
                List<Client> clients = new ArrayList<>();
                clients=cl.viewAll();
                List<Produs> produse = new ArrayList<>();
                produse=pr.viewAll();
                for(Client c: clients){
                    if(c.getId()==idCl){
                        for(Produs p: produse){
                            if(p.getId()==idProd){
                                if(cantitate<p.getCantitate()){
                                    Comanda comandamea = new Comanda();
                                    comandamea.setId(id);
                                    comandamea.setIdClient(idCl);
                                    comandamea.setIdProdus(idProd);
                                    comandamea.setCantitate(cantitate);
                                    com.insert(comandamea);
                                    p.setCantitate(p.getCantitate()-cantitate);
                                    List<String> campuri = new ArrayList<>();
                                    campuri.add("cantitate");
                                    pr.update(p,p.getId(),campuri);
                                    fdata.createFile();
                                    fdata.writeInFile("Comanda "+ id +" a produsului: "+ p.getNume()+" in cantitatea: "+cantitate+" de catre clientul: "+ c.getName()+ " a fost efectuata cu succes!");
                                }
                                else
                                    throw new Exception("Cantitate prea mica");
                            }
                        }
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }
    }
}
