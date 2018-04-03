import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CalcV09 extends JFrame {
    private JTextField mainText;
    private JButton dButton, cButton;
    private JButton Button[] = new JButton[18];
    String[] buttonVal = new String[18];
    private String A = "";

    public void SwingFrame() {
        panel1 p1=new panel1();
        panel2 p2=new panel2();
        p1.setBounds(40,50, 600,40);
        p2.setBounds(40,100, 600,300);
        add(p1);
        add(p2);
        setLayout(null);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SimpleCalc");
        setVisible(true);
    }

    class panel1 extends JPanel {
        public panel1() {
            setBackground(Color.PINK);
            setLayout(new FlowLayout(FlowLayout.CENTER));
            Font font = new Font("arian", Font.BOLD, 14);
            mainText = new JTextField(30);
            mainText.setText("0");
            mainText.setFont(font);
            mainText.setPreferredSize(new Dimension(80, 30));
            mainText.setHorizontalAlignment(JTextField.RIGHT);
            dButton = new JButton("Delete");
            cButton = new JButton("Clear");
            dButton.addActionListener(new MyListener());
            cButton.addActionListener(new MyListener());

            add(mainText);
            add(dButton);
            add(cButton);
        }
    }

    class panel2 extends JPanel {
        public panel2() {
            setBackground(Color.BLUE);
            setLayout(new GridLayout(6,3));
            buttonVal[0] = "7"; buttonVal[1] = "8"; buttonVal[2] = "9";
            buttonVal[3] = "4"; buttonVal[4] = "5"; buttonVal[5] = "6";
            buttonVal[6] = "1"; buttonVal[7] = "2"; buttonVal[8] = "3";
            buttonVal[9] = "0"; buttonVal[10] = "."; buttonVal[11] = "=";
            buttonVal[12] = "+"; buttonVal[13] = "-"; buttonVal[14] = "(";
            buttonVal[15] = "*"; buttonVal[16] = "/"; buttonVal[17] = ")";

            for(int i = 0; i < 18; i++) {
                Button[i] = new JButton(buttonVal[i]);
                add(Button[i]);
                Button[i].addActionListener(new MyListener());
            }
        }
    }

    class MyListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String leftNum, rightNum, oper="";

            //0번째에서 마지막까지 한 char로 읽어서
            //숫자면 leftNum에 add
            //operator만 오른쪽에 add
            //이후숫자면 rightNum에 add

            for (int i = 0; i < 17; i++) {
                if (e.getSource() == Button[i] && e.getSource()!=Button[11]) {
                    if (operYN(buttonVal[i].toCharArray()[0]))
                        A = A+" "+buttonVal[i]+" ";
                    else
                        A = A+buttonVal[i];
                    mainText.setText(A);
                }
            }

            if (e.getSource() == Button[17]){
                A = A+" "+buttonVal[17];
                mainText.setText(A);
            }

            if (e.getSource() == cButton) {
                mainText.setText("0");
                A = "";
            }

            if (e.getSource() == dButton) {
                String temp = mainText.getText();
                int n = temp.length();
                mainText.setText(temp.substring(0, n-1));
                A = temp.substring(0, n-1);
            }

            if (e.getSource() == Button[11]) {
                int operIdx = 0;
                System.out.println("*"+A);
                A = "( 3 + 2 ) / 5";
                int n = A.length();
                eqCalc calc1 = new eqCalc(A);
                System.out.println("\npostfix");
                System.out.println(calc1.getPostEq());
                System.out.println(calc1.getPostCalc());
                mainText.setText(String.valueOf(calc1.getPostCalc()));
/*
                for (int i=0; i<n; i++) {
                    if(operYN(A.charAt(i))==true) {
                        operIdx = i;
                        oper = String.valueOf(A.charAt(i));
                    }
                }


                System.out.println(operIdx);
                leftNum = A.substring(0, operIdx);
                rightNum = A.substring(operIdx+1, n);
                System.out.println(leftNum + oper + rightNum);

                double temp1, temp2;
                temp1 = Double.parseDouble(leftNum);
                temp2 = Double.parseDouble(rightNum);

                if (oper.equals("+")==true) {
                    mainText.setText(String.valueOf(temp1 + temp2));
                }
                else if (oper.equals("-")==true) {
                    mainText.setText(String.valueOf(temp1 - temp2));
                }
                else if (oper.equals("*")==true) {
                    mainText.setText(String.valueOf(temp1 * temp2));
                }
                else if (oper.equals("/")==true) {
                    mainText.setText(String.valueOf(temp1 / temp2));
                }
*/
            }

        }

    }

    boolean operYN(char a) {
        switch(a) {
            case '+' :
            case '-' :
            case '*' :
            case '/' :
            case '(' :
            case ')' :
                return true;
            default :
                return false;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CalcV09 c = new CalcV09();
        c.SwingFrame();
    }
}
