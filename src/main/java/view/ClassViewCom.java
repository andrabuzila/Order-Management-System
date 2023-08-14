package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 */
public class ClassViewCom extends JFrame {
    private JTextField m_id = new JTextField(20);
    private JTextField m_idCl = new JTextField(20);
    private JTextField m_idProd = new JTextField(20);
    private JTextField m_cantitate = new JTextField(20);
    private JButton afisare = new JButton("View all");
    private JButton create = new JButton("Create");
    private JButton delete = new JButton("Delete");
    JTable tabel = new JTable();

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();

    /**
     *
     */
    public ClassViewCom(){

        JLabel label1 = new JLabel("ID:");
        panel1.add(label1);
        panel1.add(m_id);
        panel1.setLayout(new FlowLayout());

        JLabel label2 = new JLabel("ID Client:");
        panel2.add(label2);
        panel2.add(m_idCl);
        panel2.setLayout(new FlowLayout());

        JLabel label3 = new JLabel("ID Produs:");
        panel3.add(label3);
        panel3.add(m_idProd);
        panel3.setLayout(new FlowLayout());

        JLabel label4 = new JLabel("Cantitate:");
        panel4.add(label4);
        panel4.add(m_cantitate);
        panel4.setLayout(new FlowLayout());


        panel5.add(afisare);
        panel5.add(create);
        panel5.add(delete);
        panel5.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.add(panel6);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setTitle("Comanda");
        setPreferredSize(new Dimension(500, 500));
        this.setContentPane(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * @param aal
     */
    public void addAfisListener2(ActionListener aal){
        afisare.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addDeleteListener2(ActionListener aal){
        delete.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addCreateListener2(ActionListener aal){
        create.addActionListener(aal);
    }

    /**
     * @param data
     */
    public void setTabel(Object[][] data){
        String[] coloane={"id", "idCl", "idProd", "Cantitate"};
        tabel= new JTable(data, coloane);
        tabel.setBounds(40, 40, 200, 200);
        JScrollPane sp=new JScrollPane(tabel);
        panel6.add(sp);
        tabel.setVisible(true);
        setVisible(true);
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
    public String getIdCl(){
        return m_idCl.getText();
    }

    /**
     * @return
     */
    public String getIdProd(){
        return m_idProd.getText();
    }

    /**
     * @return
     */
    public String getCantitate(){
        return m_cantitate.getText();
    }

}
