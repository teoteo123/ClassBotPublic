import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;
import javax.swing.*;

public class ClassJoinGUI {

    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton startButton;
    private CredentialSet c;
    private static boolean buttonPressed;

    public ClassJoinGUI() throws Exception{

        JFrame frame = new JFrame("Class Joiner");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(430, 200);
        frame.setResizable(false);
        frame.setVisible(true);

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Pressed");
                c = new CredentialSet(textField2.getText(), textField1.getText());
                try {
                    final Pinger pinger = new Pinger(c);
                } catch (AWTException awtException) {
                    System.out.println("poo");
                } catch (InterruptedException interruptedException) {
                    System.out.println("poo");
                }
                frame.setVisible(false);
            }
        });
    }

}
