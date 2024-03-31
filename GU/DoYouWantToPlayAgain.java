import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoYouWantToPlayAgain extends JFrame {

    public DoYouWantToPlayAgain() {
        setTitle("Play Again?");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1000, 707);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\evban\\OneDrive\\Pictures\\Nim\\PlayAgain.png");

        JButton yesButton = new JButton("");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SelectGameMode();
                System.out.println("Yes clicked");
                dispose();
            }
        });

        JButton noButton = new JButton("");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ThankYou thankYouFrame = new ThankYou();
                thankYouFrame.setVisible(true);
                dispose();
            }
        });

        yesButton.setBounds(242, 394, 176, 69);
        noButton.setBounds(566, 394, 176, 69);

        yesButton.setOpaque(false);
        yesButton.setContentAreaFilled(false);
        yesButton.setBorderPainted(false);

        noButton.setOpaque(false);
        noButton.setContentAreaFilled(false);
        noButton.setBorderPainted(false);

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        backgroundLabel.add(yesButton);
        backgroundLabel.add(noButton);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        getContentPane().add(backgroundLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DoYouWantToPlayAgain().setVisible(true);
            }
        });
    }
}
