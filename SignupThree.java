import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener{

    JComboBox branchBox;
    JCheckBox c1,c2,c3,c4 ,c5, c6, c7;
    JButton submit,cancel;
    String formno;


    SignupThree(String formno){

        this.formno = formno;
        setLayout(null);

        JLabel l1 = new JLabel("Page 3: ACCOUNT DETAILS");
        l1.setFont(new Font("Raleway",Font.BOLD,22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

     // ---- Account Type -------

        JLabel type = new JLabel("Branch Location :");
        type.setFont(new Font("Raleway",Font.BOLD,22));
        type.setBounds(100, 140, 200, 30);
        add(type);


        String branceval[] = {"Colombo","Gampaha","Kalutara","Kandy","Matale","Nuwara Eliya","Galle","Matara","Hambantota","Jaffna","Kilinochchi","Mannar","Vavuniya","Mullaitivu","Batticaloa","Ampara","Trincomalee","Kurunegala","Puttalam","Anuradhapura","Polonnaruwa","Badulla","Moneragala","Ratnapura","Kegalle"};
        branchBox = new JComboBox(branceval);
        branchBox.setFont(new Font("Raleway",Font.BOLD,16));
        branchBox.setBackground(Color.white);
        branchBox.setBounds(330, 140, 400, 40);
        add(branchBox);

    // --- Card Number ------

        JLabel cardNum = new JLabel("Card Number :");
        cardNum.setFont(new Font("Raleway",Font.BOLD,22));
        cardNum.setBounds(100, 220, 200, 30);
        add(cardNum);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-1234");
        number.setFont(new Font("Raleway",Font.BOLD,22));
        number.setBounds(330, 220, 400, 30);
        add(number);

        JLabel cardDetails = new JLabel("Your 16 Digits Card Number");
        cardDetails.setFont(new Font("Raleway",Font.BOLD,12));
        cardDetails.setForeground(Color.DARK_GRAY);
        cardDetails.setBounds(100, 250, 400, 20);
        add(cardDetails);

    // ---- PIN Number --------

        JLabel pin = new JLabel("PIN Number :");
        pin.setFont(new Font("Raleway",Font.BOLD,22));
        pin.setBounds(100, 310, 400, 30);
        add(pin);

        JLabel pinNumber = new JLabel("XXXX");
        pinNumber.setFont(new Font("Raleway",Font.BOLD,22));
        pinNumber.setBounds(330, 310, 400, 30);
        add(pinNumber);

        JLabel pinDetails = new JLabel("Your 4 Digits PIN Number");
        pinDetails.setFont(new Font("Raleway",Font.BOLD,12));
        pinDetails.setForeground(Color.DARK_GRAY);
        pinDetails.setBounds(100, 340, 400, 20);
        add(pinDetails);

    // ---- Services -------

        JLabel Services = new JLabel("Services Required :");
        Services.setFont(new Font("Raleway",Font.BOLD,22));
        Services.setBounds(100, 410, 400, 30);
        add(Services);

        c1 = new JCheckBox("ATM CARD");
        c1.setBackground(Color.white);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setBounds(300, 450, 200, 40);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setBackground(Color.white);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setBounds(550, 450, 200, 40);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setBackground(Color.white);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setBounds(300, 500, 200, 40);
        add(c3);

        c4 = new JCheckBox("Email & SMS Alerts");
        c4.setBackground(Color.white);
        c4.setFont(new Font("Raleway",Font.BOLD,16));
        c4.setBounds(550, 500, 200, 40);
        add(c4);

        c5 = new JCheckBox("Cheque Book");
        c5.setBackground(Color.white);
        c5.setFont(new Font("Raleway",Font.BOLD,16));
        c5.setBounds(300, 550, 200, 40);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setBackground(Color.white);
        c6.setFont(new Font("Raleway",Font.BOLD,16));
        c6.setBounds(550, 550, 200, 40);
        add(c6);

    // ------ condition -----

        c7 = new JCheckBox("I hereby declares that the above entered details are correct to the best of my knowledge");
        c7.setBackground(Color.white);
        c7.setFont(new Font("Raleway",Font.BOLD,12));
        c7.setBounds(100, 630, 600, 40);
        add(c7);

     // --- Button -----

        submit = new JButton("Submit");
        submit.setBackground(Color.black);
        submit.setForeground(Color.BLACK);
        submit.setFont(new Font("Raleway",Font.BOLD,14));
        submit.setBounds(300, 720, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.BLACK);
        cancel.setFont(new Font("Raleway",Font.BOLD,14));
        cancel.setBounds(470, 720, 100, 30);
        cancel.addActionListener(this);
        add(cancel);


    // ---- Set Background Size ------
        setSize(850,820);
        setLocation(350,0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            String branch =(String)branchBox.getSelectedItem();

            Random random = new Random();
            String cardnumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);

            String pinnumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            String facility = "";
            if (c1.isSelected()){
                facility = facility + "ATM CARD";
            }else if (c2.isSelected()){
                facility = facility + "Internet Banking";
            }else if (c3.isSelected()){
                facility = facility + "Mobile Banking";
            }else if (c4.isSelected()){
                facility = facility + "Email & SMS Alerts";
            }else if (c5.isSelected()){
                facility = facility + "Cheque Book";
            }else if (c6.isSelected()){
                facility = facility + "E-Statement";
            }

            try {
                if (branch.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter branch");
                }else{
                    Connection con = ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    String Query1 = "insert into signupthree values ('"+formno+"','"+branch+"','"+cardnumber+"','"+pinnumber+"','"+facility+"')";
                    String Query2 = "insert into login values ('"+formno+"','"+cardnumber+"','"+pinnumber+"')";

                    st.executeUpdate(Query1);
                    st.executeUpdate(Query2);


                    JOptionPane.showMessageDialog(null, "Card Number: " + cardnumber + "\n Pin : " + pinnumber );

                    setVisible(false);
                    //new Deposit(pinnumber).setVisible(true);
                    new Transaction(pinnumber).setVisible(true);

                }

            }catch (Exception e){
                System.out.println(e);
            }





        }else if (ae.getSource() == cancel){
            setVisible(false);
            new Login().setVisible(true);

        }
    }



    public static void main(String[] args) {
        new SignupThree("");
    }
}
