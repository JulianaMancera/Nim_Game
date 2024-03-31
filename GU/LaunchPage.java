import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;

public class LaunchPage {
    LaunchPage() {
        JFrame f = new JFrame();
        JButton b = new JButton();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("C:/Users/evban/OneDrive/Pictures/Nim/Main Frame.png");
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setPreferredSize(new Dimension(1000, 707));
        panel.setLayout(null);

        b.setBounds(428, 525, 145, 50);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectGameMode();
                f.dispose();
            }
        });

        f.add(b);
        f.getContentPane().add(panel);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setVisible(true);

        playBackgroundMusic();
    }

    private void playBackgroundMusic() {
        new Thread(() -> {
            try {
                File musicFile = new File("C:/Users/evban/OneDrive/Pictures/Nim/Di Young - Pixel Pig.wav"); 
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                float volume = -20.0f;
                gainControl.setValue(volume);

                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        new LaunchPage();
    }
}
