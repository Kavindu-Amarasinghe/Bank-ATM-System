import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField repin,pin;
    JButton change,Back;
    String pinnumber;


    PinChange(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon("src/icon/atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setBounds(260,280,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        JLabel pintext = new JLabel("NEW PIN:");
        pintext.setBounds(165,320,180,25);
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 25));
        pin.setBounds(330,320,180,25);
        image.add(pin);

        JLabel repintext = new JLabel("Re-Enter New PIN:");
        repintext.setBounds(165,360,180,25);
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 25));
        repin.setBounds(330,360,180,25);
        image.add(repin);


        change = new JButton("CHANGE");
        change.setBounds(355,485,150,30);
        change.addActionListener(this);
        image.add(change);

        Back = new JButton("Back");
        Back.setBounds(355,520,150,30);
        Back.addActionListener(this);
        image.add(Back);








        // ------ Background --------

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==change) {

            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }
                if (npin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter PIN");
                    return;
                }
                if(rpin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please Re-Enter new PIN");
                    return;
                }
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();
                String query1 = "update bank set Pin_Number='"+rpin+"' where Pin_Number='"+pinnumber+"'";
                String query2 = "update login set Pin_Number='"+rpin+"' where Pin_Number='"+pinnumber+"'";
                String query3 = "update signupthree set Pin_Number='"+rpin+"' where Pin_Number='"+pinnumber+"'";
                st.executeUpdate(query1);
                st.executeUpdate(query2);
                st.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN CHANGED SUCCESSFULLY");
                setVisible(false);
                new Transaction(rpin).setVisible(true);


            } catch (Exception ae) {
                ae.printStackTrace();
            }
        }else {
            setVisible(false);
            new Transaction(pinnumber).setVisible(true);


        }


    }



    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }
}
