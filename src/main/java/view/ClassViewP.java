package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 */
public class ClassViewP extends JFrame {
    private JTextField m_id = new JTextField(20);
    private JTextField m_nume = new JTextField(20);
    private JTextField m_cantitate = new JTextField(20);
    private JButton afisare = new JButton("View all");
    private JButton insert = new JButton("Insert");
    private JButton update = new JButton("Update");
    private JButton delete = new JButton("Delete");
    private JTable tabel = new JTable();

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();

    /**
     *
     */
    public ClassViewP(){

        JLabel label1 = new JLabel("ID:");
        panel1.add(label1);
        panel1.add(m_id);
        panel1.setLayout(new FlowLayout());

        JLabel label2 = new JLabel("Nume:");
        panel2.add(label2);
        panel2.add(m_nume);
        panel2.setLayout(new FlowLayout());

        JLabel label3 = new JLabel("Cantitate:");
        panel3.add(label3);
        panel3.add(m_cantitate);
        panel3.setLayout(new FlowLayout());


        panel4.add(afisare);
        panel4.add(insert);
        panel4.add(update);
        panel4.add(delete);
        panel4.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        panel.add(panel1);
        panel.add(panel2);
        panel.add(panel3);
        panel.add(panel4);
        panel.add(panel5);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setTitle("Produs");
        setPreferredSize(new Dimension(500, 500));
        this.setContentPane(panel);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    public String getCantitate(){
        return m_cantitate.getText();
    }

    /**
     * @param data
     */
    public void setTabel(Object[][] data){
        String[] coloane={"id", "nume", "cantitate"};
        tabel= new JTable(data, coloane);
        tabel.setBounds(40, 40, 200, 200);
        JScrollPane sp=new JScrollPane(tabel);
        panel5.add(sp);
        tabel.setVisible(true);
        setVisible(true);
    }

    /**
     * @param aal
     */
    public void addAfisListener1(ActionListener aal){
        afisare.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addInsertListener1(ActionListener aal){
        insert.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addDeleteListener1(ActionListener aal){
        delete.addActionListener(aal);
    }

    /**
     * @param aal
     */
    public void addUpdateListener1(ActionListener aal){
        update.addActionListener(aal);
    }
}
