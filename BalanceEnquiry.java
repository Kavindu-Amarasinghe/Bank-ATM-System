import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back;
    String pinnumber;

    BalanceEnquiry(String pinchange){
        this.pinnumber = pinchange;
        setLayout(null);


        ImageIcon i1 = new ImageIcon("src/icon/atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        // ---- Data Base -----


        int balance = 0;
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            String query = "select * from bank where Pin_Number = '"+pinnumber+"' ";
            ResultSet rs = st.executeQuery(query);


            while(rs.next()){
                if (rs.getString("type").equals("Deposit")){
                    balance += Integer.parseInt(rs.getString("amount"));
                }else{
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        JLabel text = new JLabel("Your Current Account Balance is Rs: "+balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170,300,400,30);
        image.add(text);






    // ------ Background --------

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transaction(pinnumber).setVisible(true);

    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
