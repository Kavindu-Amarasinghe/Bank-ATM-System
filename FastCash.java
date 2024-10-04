import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton money100 , money500 , money1000 , money2000 , money5000 , money10000, exit;
    String pinnumber;

    FastCash(String pinnumber){
        this.pinnumber = pinnumber;

        setLayout(null);


        ImageIcon i1 = new ImageIcon("src/icon/atm.jpg");
        Image i2 = i1.getImage().getScaledInstance(900,900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWL AMOUNT");
        text.setBounds(215,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // --- Buttons -----


        money100 = new JButton("RS 100");
        money100.setBounds(170,415,150,30);
        money100.addActionListener(this);
        image.add(money100);

        money500 = new JButton("RS 500");
        money500.setBounds(350,415,150,30);
        money500.addActionListener(this);
        image.add(money500);

        money1000 = new JButton("RS 1000");
        money1000.setBounds(170,450,150,30);
        money1000.addActionListener(this);
        image.add(money1000);

        money2000 = new JButton("RS 2000");
        money2000.setBounds(350,450,150,30);
        money2000.addActionListener(this);
        image.add(money2000);

        money5000 = new JButton("RS 5000");
        money5000.setBounds(170,485,150,30);
        money5000.addActionListener(this);
        image.add(money5000);

        money10000 = new JButton("RS 10000");
        money10000.setBounds(350,485,150,30);
        money10000.addActionListener(this);
        image.add(money10000);

        exit = new JButton("BACK");
        exit.setBounds(355,520,150,30);
        exit.addActionListener(this);
        image.add(exit);



        // ------ Background --------

        setSize(900,900);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == exit){
           setVisible(false);
           new Transaction(pinnumber).setVisible(true);
        }else{
            String amount = ((JButton)e.getSource()).getText().substring(3);




            try {
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();
                String query = "select * from bank where Pin_Number = '"+pinnumber+"' ";
                ResultSet rs = st.executeQuery(query);

                int balance = 0;
                while(rs.next()){
                    if (rs.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(rs.getString("amount"));
                    }else{
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }

                }
                if(e.getSource() != exit && balance < Integer.parseInt(amount)){
                    JOptionPane.showMessageDialog(null, "You don't have enough money to Withdrawl");
                    return;
                }
                Date date = new Date();
                st.executeUpdate("insert into bank values('"+pinnumber+"','"+date+"','Withdrawl','"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs "+amount+" Debited Successfully");

                setVisible(false);
                new Transaction(pinnumber).setVisible(true);

            }catch (Exception ae){
                ae.printStackTrace();

            }

        }

    }

    public static void main(String[] args) {

        new FastCash("");

    }
}
