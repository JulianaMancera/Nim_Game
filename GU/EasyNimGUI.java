import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Stack;

public class EasyNimGUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel[] pileLabels;
    private JLabel[] starLabels;
    private JButton[] removeButtons;
    private Stack<Integer>[] piles;
    private int lastMovePlayer = 1;
    private JLabel player1Indicator;
    private JLabel player2Indicator;

    private ImageIcon starIcon;

    public EasyNimGUI() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
            initializeGame();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Easy Mode Nim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 707));
        frame.setResizable(false);

        starIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/StarNIM.png");

        JLabel backgroundLabel = new JLabel();
        ImageIcon backgroundImage = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/4.png");
        Image image = backgroundImage.getImage().getScaledInstance(1000, 707, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(image);
        backgroundLabel.setIcon(backgroundImage);

        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel pileButtonPanel = new JPanel();
        pileButtonPanel.setOpaque(false);
        pileButtonPanel.setLayout(new GridBagLayout());
        GridBagConstraints pileButtonPanelConstraints = new GridBagConstraints();
        pileButtonPanelConstraints.gridx = 0;
        pileButtonPanelConstraints.gridy = 0;
        pileButtonPanelConstraints.anchor = GridBagConstraints.CENTER;

        pileLabels = new JLabel[3];
        starLabels = new JLabel[3];
        removeButtons = new JButton[3];
        for (int i = 0; i < 3; i++) {
            int pileIndex = i + 1;
            pileLabels[i] = new JLabel("Pile " + pileIndex + ": ");
            pileLabels[i].setForeground(Color.WHITE);
            pileLabels[i].setFont(pileLabels[i].getFont().deriveFont(Font.BOLD, 20));

            starLabels[i] = new JLabel();
            starLabels[i].setFont(starLabels[i].getFont().deriveFont(Font.PLAIN, 20));

            removeButtons[i] = new JButton();
            try {
                BufferedImage img = ImageIO.read(new File("C:/Users/evban/OneDrive/Pictures/Nim/REMOVE.png"));
                Image scaledImage = img.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
                removeButtons[i].setIcon(new ImageIcon(scaledImage));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            removeButtons[i].setBorderPainted(false);
            removeButtons[i].setContentAreaFilled(false);
            removeButtons[i].addActionListener(new RemoveButtonListener(pileIndex));

            GridBagConstraints pileLabelConstraints = new GridBagConstraints();
            pileLabelConstraints.gridx = 0;
            pileLabelConstraints.gridy = i * 2;
            pileLabelConstraints.anchor = GridBagConstraints.LINE_START;
            pileLabelConstraints.insets = new Insets(10, 10, 0, 10);
            pileButtonPanel.add(pileLabels[i], pileLabelConstraints);

            GridBagConstraints starLabelConstraints = new GridBagConstraints();
            starLabelConstraints.gridx = 1;
            starLabelConstraints.gridy = i * 2;
            starLabelConstraints.anchor = GridBagConstraints.CENTER;
            starLabelConstraints.insets = new Insets(10, 10, 0, 10);
            pileButtonPanel.add(starLabels[i], starLabelConstraints);

            GridBagConstraints removeButtonConstraints = new GridBagConstraints();
            removeButtonConstraints.gridx = 2;
            removeButtonConstraints.gridy = i * 2;
            removeButtonConstraints.anchor = GridBagConstraints.LINE_START;
            removeButtonConstraints.insets = new Insets(10, 10, 0, 10);
            pileButtonPanel.add(removeButtons[i], removeButtonConstraints);

            GridBagConstraints spaceConstraints = new GridBagConstraints();
            spaceConstraints.gridx = 0;
            spaceConstraints.gridy = i * 2 + 1;
            spaceConstraints.weighty = 0.5;
            pileButtonPanel.add(Box.createGlue(), spaceConstraints);
        }

        player1Indicator = createIndicatorSquare("C:/Users/evban/OneDrive/Pictures/Nim/Player1.png");
        player2Indicator = createIndicatorSquare("C:/Users/evban/OneDrive/Pictures/Nim/Player2.png");

        JPanel indicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        indicatorPanel.setOpaque(false);
        indicatorPanel.add(player1Indicator);
        indicatorPanel.add(player2Indicator);

        gbc.gridy++;
        panel.add(pileButtonPanel, gbc);
        gbc.gridy++;
        panel.add(indicatorPanel, gbc);

        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(panel);

        frame.getContentPane().add(backgroundLabel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JLabel createIndicatorSquare(String imagePath) {
        JLabel indicator = new JLabel();
        ImageIcon icon = new ImageIcon(imagePath);
        int newWidth = (int) (icon.getIconWidth() * 0.8);
        int newHeight = (int) (icon.getIconHeight() * 0.8);
        Image scaledImage = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        indicator.setPreferredSize(new Dimension(250, 250));
        indicator.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        indicator.setIcon(scaledIcon);
        indicator.setHorizontalAlignment(SwingConstants.CENTER);
        indicator.setVerticalAlignment(SwingConstants.CENTER);
        return indicator;
    }

    private void initializeGame() {
        piles = new Stack[4];
        piles[1] = new Stack<>();
        piles[2] = new Stack<>();
        piles[3] = new Stack<>();

        for (int i = 1; i <= 3; i++) {
            int pileSize = 3 + 2 * (i - 1);
            for (int j = 0; j < pileSize; j++) {
                piles[i].push(1);
            }
        }

        updateGUI();
    }

    private class RemoveButtonListener implements ActionListener {
        private int pileIndex;

        public RemoveButtonListener(int pileIndex) {
            this.pileIndex = pileIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int numToRemove = chooseNumberToRemove();
            if (numToRemove > 0 && !piles[pileIndex].isEmpty()) {
                for (int i = 0; i < numToRemove && !piles[pileIndex].isEmpty(); i++) {
                    piles[pileIndex].pop();
                }
                lastMovePlayer = 3 - lastMovePlayer;
                updateGUI();
            }
        }

        private int chooseNumberToRemove() {
            String[] options = {"1", "2"};
            return Integer.parseInt((String) JOptionPane.showInputDialog(frame,
                    "Choose the number of objects to remove from Pile " + pileIndex + ":",
                    "Remove",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]));
        }
    }

    private int findWinningPlayer() {
        return lastMovePlayer;
    }

    private void updateGUI() {
        if (lastMovePlayer == 1) {
            player1Indicator.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            player2Indicator.setBorder(null);
        } else {
            player1Indicator.setBorder(null);
            player2Indicator.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }

        for (int i = 0; i < 3; i++) {
            int numObjects = piles[i + 1].size();
            starLabels[i].setIcon(null);
            if (numObjects > 0) {
                StringBuilder stars = new StringBuilder();
                for (int j = 0; j < numObjects; j++) {
                    stars.append("<img src='file:///C:/Users/evban/OneDrive/Pictures/Nim/StarNIM.png' width='22' height='22'/>");
                    if (j < numObjects - 1) {
                        stars.append("&nbsp;");
                    }
                }
                starLabels[i].setText("<html>" + stars.toString() + "</html>");
            } else {
                starLabels[i].setText("");
            }
            pileLabels[i].setText("<html><font color='white'>Pile " + (i + 1) + ": " + numObjects + "</font></html>");
        }

        if (isGameOver()) {
            int winningPlayer = findWinningPlayer();
            if (winningPlayer != -1) {
                new WinningPlayer(winningPlayer);
            }
            frame.dispose();
        }
    }

    private boolean isGameOver() {
        for (int i = 0; i < 3; i++) {
            if (!piles[i + 1].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new EasyNimGUI();
    }
}
