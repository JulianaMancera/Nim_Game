import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinningPlayer extends JFrame {
    private ImageIcon winnerIcon;
    private boolean doYouWantToPlayAgainOpened;

    public WinningPlayer(int winningPlayer) {
        super("Winner");
        doYouWantToPlayAgainOpened = false;

        if (winningPlayer == 1) {
            winnerIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/Winner Player 1.png");
        } else {
            winnerIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/Winner Player 2.png");
        }

        Image scaledImage = winnerIcon.getImage().getScaledInstance(1000, 707, Image.SCALE_SMOOTH);
        winnerIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(winnerIcon);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        setSize(1000, 707);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!doYouWantToPlayAgainOpened) {
                    openDoYouWantToPlayAgainFrame();
                    doYouWantToPlayAgainOpened = true;
                }
                dispose();
            }
        });
    }

    private void openDoYouWantToPlayAgainFrame() {
        DoYouWantToPlayAgain playAgainFrame = new DoYouWantToPlayAgain();
        playAgainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WinningPlayer(1));
    }
}
