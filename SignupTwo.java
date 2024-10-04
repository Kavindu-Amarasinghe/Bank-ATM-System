import com.mysql.jdbc.Connection;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.Random;




    public class SignupTwo extends JFrame implements ActionListener {

        // --- Gloable Variables ----


        JTextField   idNum, tinNum;
        JButton next;
        JRadioButton sNo, sYes, eYes, eNo;

        JComboBox religionBox,categoryBox,incomeBox,educationBox,ocupationBox;
        String formno;


        SignupTwo(String formno) {

            this.formno = formno;

            setLayout(null);

            setTitle("New ACCOUNT APPLICATION FORM - PAGE 2");


            JLabel additionalDetails = new JLabel("Page 2: Additional Details");
            additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
            additionalDetails.setBounds(290, 80, 400, 30);
            add(additionalDetails);


            // --- Religion-------

            JLabel religion = new JLabel("Religion: ");
            religion.setFont(new Font("Raleway", Font.BOLD, 20));
            religion.setBounds(100, 140, 100, 30);
            add(religion);


            String valReligion[] = {"Buddhism","Hinduism","Islam","Christianity","Others"};
            religionBox = new JComboBox(valReligion);
            religionBox.setBounds(300, 140, 400, 30);
            religionBox.setBackground(Color.WHITE);
            add(religionBox);




            // --- Category -------

            JLabel category = new JLabel("Account Type: ");
            category.setFont(new Font("Raleway", Font.BOLD, 20));
            category.setBounds(100, 190, 200, 30);
            add(category);

            String valCategory[] = {"Savings accounts","Certificates of deposit","Fixed deposit account","Advantage Savings account","Family Savings account","Salary account","Senior citizens accounts","Other"};
            categoryBox = new JComboBox(valCategory);
            categoryBox.setBounds(300, 190, 400, 30);
            categoryBox.setBackground(Color.WHITE);
            add(categoryBox);






            // --- Income-------

            JLabel Income = new JLabel("Income: ");
            Income.setFont(new Font("Raleway", Font.BOLD, 20));
            Income.setBounds(100, 240, 200, 30);
            add(Income);

            String valIncome[] = {"Null","10,000 - 20,000","20,000 - 50,000","50,000 - 100,000","100,000 - 500,000","Other"};
            incomeBox = new JComboBox(valIncome);
            incomeBox.setBounds(300, 240, 400, 30);
            incomeBox.setBackground(Color.WHITE);
            add(incomeBox);




            // --- Educational Qualification -------


            JLabel education = new JLabel("Educational");
            education.setFont(new Font("Raleway", Font.BOLD, 20));
            education.setBounds(100, 290, 200, 30);
            add(education);

            JLabel Qualification = new JLabel("Qualification: ");
            Qualification.setFont(new Font("Raleway", Font.BOLD, 20));
            Qualification.setBounds(100, 315, 200, 30);
            add(Qualification);


            String valeducation[] = {"O/L","A/L","Non Graduation","Post Graduation","Other"};
            educationBox = new JComboBox(valeducation);
            educationBox.setBounds(300, 315, 400, 30);
            educationBox.setBackground(Color.WHITE);
            add(educationBox);




            // --- Occupation-------

            JLabel Occupation = new JLabel("Occupation: ");
            Occupation.setFont(new Font("Raleway", Font.BOLD, 20));
            Occupation.setBounds(100, 390, 200, 30);
            add(Occupation);


            String valocupation[] = {"Salaried","Self Employe","Bussiness","Student","Retired","Others"};
            ocupationBox = new JComboBox(valocupation);
            ocupationBox.setBounds(300, 390, 400, 30);
            ocupationBox.setBackground(Color.WHITE);
            add(ocupationBox);




            // --- ID Number-------


            JLabel address = new JLabel("ID Number: ");
            address.setFont(new Font("Raleway", Font.BOLD, 20));
            address.setBounds(100, 440, 200, 30);
            add(address);

            idNum = new JTextField();
            idNum.setFont(new Font("Raleway", Font.BOLD, 14));
            idNum.setBounds(300, 440, 400, 30);
            add(idNum);


            // --- TIN Number-------

            JLabel city = new JLabel("TIN Number: ");
            city.setFont(new Font("Raleway", Font.BOLD, 20));
            city.setBounds(100, 490, 200, 30);
            add(city);

            tinNum = new JTextField();
            tinNum.setFont(new Font("Raleway", Font.BOLD, 14));
            tinNum.setBounds(300, 490, 400, 30);
            add(tinNum);


            // --- Senior Citizen -------

            JLabel state = new JLabel("Senior Citizen: ");
            state.setFont(new Font("Raleway", Font.BOLD, 20));
            state.setBounds(100, 540, 200, 30);
            add(state);


            sYes = new JRadioButton("Yes");
            sYes.setBounds(300, 540, 120, 30);
            sYes.setBackground(Color.WHITE);
            add(sYes);



            sNo = new JRadioButton("No");
            sNo.setBounds(450, 540, 120, 30 );
            sNo.setBackground(Color.WHITE);
            add(sNo);

            ButtonGroup Citizengroup = new ButtonGroup();
            Citizengroup.add(sYes);
            Citizengroup.add(sNo);





            // --- Existing Account -------

            JLabel pincode = new JLabel("Existing Account: ");
            pincode.setFont(new Font("Raleway", Font.BOLD, 20));
            pincode.setBounds(100, 590, 200, 30);
            add(pincode);

            eYes = new JRadioButton("Yes");
            eYes.setBounds(300, 590, 120, 30);
            eYes.setBackground(Color.WHITE);
            add(eYes);



            eNo = new JRadioButton("No");
            eNo.setBounds(450, 590, 120, 30 );
            eNo.setBackground(Color.WHITE);
            add(eNo);

            ButtonGroup Existinggroup = new ButtonGroup();
            Existinggroup.add(eYes);
            Existinggroup.add(eNo);




            // ----- Next Button

            next = new JButton("Next");
            next.setBackground(Color.BLACK);
            next.setForeground(Color.BLACK);
            next.setFont(new Font("Raleway", Font.BOLD, 14));
            next.setBounds(629, 660, 80, 30);
            next.addActionListener(this);
            add(next);


            getContentPane().setBackground(Color.WHITE);


            setSize(850, 800);
            setLocation(350, 10);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent ae) {

        //    String formno = "" + random; // Long
            String religionBoxText =(String)religionBox.getSelectedItem(); // setText
            String categoryBoxText =(String)categoryBox.getSelectedItem();
            String income = (String)incomeBox.getSelectedItem();
            String education =  (String)educationBox.getSelectedItem();
            String ocupation = (String)ocupationBox.getSelectedItem();
            String ID = idNum.getText();
            String TIN = tinNum.getText();

            String seniorCitizen = null;


            if (sYes.isSelected()) {
                seniorCitizen = "Yes";
            } else if (sNo.isSelected()) {
                seniorCitizen = "No";
            }


            String exisitingaccount = null;

            if (eYes.isSelected()) {
                exisitingaccount = "Yes";
            } else if (eNo.isSelected()) {
                exisitingaccount = "No";
            }




            try {




                    Connection con = (Connection) ConnectionProvider.getCon();
                    Statement st = con.createStatement();
                    st.executeUpdate("insert into signuptwo values('"+formno+"','"+religionBoxText+"','"+categoryBoxText+"','"+income+"','"+education+"','"+ocupation+"','"+ID+"','"+TIN+"','"+seniorCitizen+"','"+exisitingaccount+"')");


                    // Signup3 - object
                    setVisible(false);
                    new SignupThree(formno).setVisible(true);


            } catch (Exception e) {
                System.out.println(e);
            }


        }


        public static void main(String args[]) {

            new SignupTwo("");

        }

    }



