package Interface;

import javax.swing.*;
import java.awt.*;

public class Winner extends JFrame {

    public static JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    JLabel textFelecitation;

    private ListParticipant listGagnant;
    public static String[] winner = new String[0];

    private void showFelecitation() {
        textFelecitation = new JLabel("Félicitations au gagnant de cette enchère");
        textFelecitation.setBackground(new Color(125, 60, 22,180));
        textFelecitation.setOpaque(true);
        textFelecitation.setFont(Welcome.FONT_Title);
        textFelecitation.setForeground(Color.WHITE);
        textFelecitation.setHorizontalAlignment(SwingConstants.CENTER);
        textFelecitation.setBounds(50, 200, 720, 50);
        backgroundLabel.add(textFelecitation);

        JTextArea texteFin = new JTextArea(
                " \n\n\n  Nous espérons que notre application vous aidera à atteindre de nouveaux " +
                        "   \n \n       sommets   dans le domaine des enchères combinatoires.     \n \n  " +
                        "            Bonne chance et que les gains soient avec vous !           ");
        texteFin.setFont(Welcome.FONT_Title_small);
        texteFin.setForeground(Color.WHITE);
        texteFin.setBackground(new Color(125, 60, 22,150));
        texteFin.setBounds(50, 300, 720, 300);
        backgroundLabel.add(texteFin);

    }

    private  void  showRevenu(){
        JLabel labelRevenu = new JLabel("  Revenu =  " + Enchere.solution.dance);
        labelRevenu.setBackground(new Color(125, 60, 22));
        labelRevenu.setOpaque(true);
        labelRevenu.setFont(Welcome.FONT_Title);
        labelRevenu.setForeground(Color.WHITE);
        labelRevenu.setBounds(50, 700, 300, 50);
        backgroundLabel.add(labelRevenu);
    }

    public Winner() {
        super(Welcome.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/images/winner.jpg"));
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null);

        listGagnant = new ListParticipant(Winner.winner, 1);

        showFelecitation();
        showRevenu();
        getContentPane().add(backgroundLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Winner());
    }
}
