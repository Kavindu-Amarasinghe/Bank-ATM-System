import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit,back;
    String pinnumber;


    Deposit(String pinnumber){
        this.pinnumber = pinnumber;
        setLayout(null);

    // ------ Image ---------

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);


        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setBounds(180,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(180,350,320,25);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(355,485,150,30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);



    // ------ Background --------

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == deposit){
                String deposit = amount.getText();
                Date date = new Date();

                if (deposit.equals("")){
                    JOptionPane.showMessageDialog(null,"Enter amount to deposit");
                }else{
                    try {
                        Connection con = ConnectionProvider.getCon();
                        Statement st = con.createStatement();
                        String query = "insert into bank values('"+pinnumber+"','"+date+"','Deposit','"+deposit+"')";

                        st.executeUpdate(query);
                        JOptionPane.showMessageDialog(null,"Rs " + deposit + " Deposited Successfully");
                        setVisible(false);
                        new Transaction(pinnumber).setVisible(true);


                    }catch (Exception ea){
                        ea.printStackTrace();
                    }

                }


        }else if(e.getSource() == back){
            new Transaction(pinnumber).setVisible(true);
        }

    }



    public static void main(String[] args) {
        new Deposit("");
    }
}
