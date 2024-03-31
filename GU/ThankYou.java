import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ThankYou extends JFrame {
    private Image backgroundImage1;
    private Image backgroundImage2;
    private boolean isFirstImage = true;

    public ThankYou() {
        try {
            backgroundImage1 = ImageIO.read(new File("C:\\Users\\evban\\OneDrive\\Pictures\\Nim\\ThankYou1.png"));
            backgroundImage2 = ImageIO.read(new File("C:\\Users\\evban\\OneDrive\\Pictures\\Nim\\ThankYou2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(1000, 707);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Thank You");
        setResizable(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (isFirstImage) {
                    isFirstImage = false;
                } else {
                    dispose();
                }
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isFirstImage) {
            g.drawImage(backgroundImage1, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(backgroundImage2, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThankYou thankYou = new ThankYou();
            thankYou.setVisible(true);
        });
    }
}
