import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MiniStatement extends JFrame {

    String pinnumber;
    MiniStatement(String pinnumber){
        setTitle("MiniStatement");
        this.pinnumber = pinnumber;
        setLayout(null);

        JLabel mini = new JLabel();

        add(mini);

        JLabel bank = new JLabel("Guardian Bank");
        bank.setBounds(150,20,100,20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);

        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from login where Pin_Number = '"+pinnumber+"'");

           while (rs.next()){
               card.setText("Card Number: " + rs.getString("Card_Number").substring(0,4) + "XXXXXXXX" + rs.getString("Card_Number").substring(12));
           }

        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            int bal = 0;
            ResultSet rs = st.executeQuery("select * from bank where Pin_Number = '"+pinnumber+"'");
            while (rs.next()){
                mini.setText(mini.getText()+ "<html> "+rs.getString("Date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");

                if (rs.getString("type").equals("Deposit")){
                    bal += Integer.parseInt(rs.getString("amount"));
                }else{
                    bal -= Integer.parseInt(rs.getString("amount"));
                }

            }
            balance.setText("Your current Account Balance is Rs "+bal);


        }catch (Exception e){
            e.printStackTrace();
        }
        mini.setBounds(20,140,400,200);



        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MiniStatement("");

    }
}
