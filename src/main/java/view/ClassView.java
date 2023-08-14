package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @Author Andra Buzila
 * @Since Mai 27, 2021
 */
public class ClassView extends JFrame {
    private JTextField m_id = new JTextField(20);
    private JTextField m_nume = new JTextField(20);
    private JTextField m_adresa = new JTextField(20);
    private JTextField m_email = new JTextField(20);
    private JTextField m_varsta = new JTextField(20);
    private JButton afisare = new JButton("View all");
    private JButton insert = new JButton("Insert");
    private JButton update = new JButton("Update");
    private JButton delete = new JButton("Delete");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private JPanel panel7 = new JPanel();

    private JTable tabel=new JTable();

    /**
     *
     */
    public ClassView(){


        JLabel label1 = new JLabel("ID:");
        panel1.add(label1);
        panel1.add(m_id);
        panel1.setLayout(new FlowLayout());

        JLabel label2 = new JLabel("Nume:");
        panel2.add(label2);
        panel2.add(m_nume);
        panel2.setLayout(new FlowLayout());

        JLabel label3 = new JLabel("Adresa:");
        panel3.add(label3);
        panel3.add(m_adresa);
        panel3.setLayout(new FlowLayout());

        JLabel label4 = new JLabel("Email:");
        panel4.add(label4);
        panel4.add(m_email);
        panel4.setLayout(new FlowLayout());

        JLabel label5 = new JLabel("Varsta:");
        panel5.add(label5);
        panel5.add(m_varsta);
        panel5.setLayout(new FlowLayout());


        panel6.add(afisare);
        panel6.add(insert);
        panel6.add(update);
        panel6.add(delete);
        panel6.setLayout(new FlowLayout());



        JPanel panel = new JPanel();
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);
        panel.add(panel7);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setTitle("Client");
        setPreferredSize(new Dimension(500, 500));
        this.setContentPane(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * @param data
     */
    public void setTabel(Object[][] data){
        String[] coloane={"id", "name", "address", "email", "age"};
        tabel= new JTable(data, coloane);
        tabel.setBounds(40, 40, 200, 200);
        JScrollPane sp=new JScrollPane(tabel);
        panel7.add(sp);
        tabel.setVisible(true);
        setVisible(true);
    }

    /**
     * @param aal
     */
    public void addAfisListener(ActionListener aal){
        afisare.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addInsertListener(ActionListener aal){
        insert.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addDeleteListener(ActionListener aal){
        delete.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addUpdateListener(ActionListener aal){
        update.addActionListener(aal);
    }

    /**
     * @return
     */
    public String getId(){
        return m_id.getText();
    }

    /**
     * @return
     */
    public String getNume(){
        return m_nume.getText();
    }

    /**
     * @return
     */
    public String getAdresa(){
        return m_adresa.getText();
    }

    /**
     * @return
     */
    public String getEmail(){
        return m_email.getText();
    }

    /**
     * @return
     */
    public String getVarsta(){
        return m_varsta.getText();
    }

}
