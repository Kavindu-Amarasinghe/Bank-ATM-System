

import com.mysql.jdbc.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame implements ActionListener {
    
    // ---- Gloable Variables ---
    JButton login,clear,signup;
    JTextField cardTextField;
    JPasswordField pinTextField;
    
        Login(){  
        setTitle("ATM MACHINE");


        
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/4.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70,10,100,100);
        add(label);
        
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD,38));
        text.setBounds(200,40,400,40);
        add(text);
        
        JLabel cardno = new JLabel("Card No: ");
        cardno.setFont(new Font("Raleway", Font.BOLD,28));
        cardno.setBounds(120,150,150,30);
        add(cardno);
        
        cardTextField = new JTextField();
        cardTextField.setBounds(300,150,230,30);
        cardTextField.setFont(new Font("Arial", Font.BOLD,14));
        add(cardTextField);
        
        
        JLabel pin = new JLabel("PIN: ");
        pin.setFont(new Font("Raleway", Font.BOLD,28));
        pin.setBounds(120,220,250,30);
        add(pin);
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300,220,230,30);
        pinTextField.setFont(new Font("Arial", Font.BOLD,14));
        add(pinTextField);
        
        // --- Buttons---
        
        login = new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(this);
        add(clear);
        
        signup = new JButton("SIGN UP");
        signup .setBounds(300,350,230,30);
        signup .setBackground(Color.BLACK);
        signup .setForeground(Color.BLACK);
        signup.addActionListener(this);
        add(signup);
        
        getContentPane().setBackground(Color.WHITE);
        
        
        setSize(700,480);
        setVisible(true);
        setLocation(350,200);
        
        }
        
    public void actionPerformed(ActionEvent ae){
        
        if(ae.getSource() == clear){
            cardTextField.setText("");
            pinTextField.setText("");
            
        }else if (ae.getSource() == login) {
            com.mysql.jdbc.Connection con = (Connection) ConnectionProvider.getCon();
            Statement  st= null;
            try {
                st = con.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String cardnumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();
            String query = "select * from login where Card_Number = '"+cardnumber+"' and Pin_Number = '"+pinnumber+"'";

            try {
               ResultSet rs =  st.executeQuery(query);
               if(rs.next()){
                   setVisible(false);
                   new Transaction(pinnumber).setVisible(true);

               }else {
                   JOptionPane.showMessageDialog(null," Invalid Card Number or Pin");
               }

            }catch (Exception e) {
                e.printStackTrace();

            }

            
        } else if (ae.getSource() == signup){
            setVisible(false);
            SignupOne signupOne = new SignupOne();
            signupOne.setVisible(true);



            
        }
        
        
        
    }
    
    public static void main(String[] args){
        new Login();
    }
}
