package Interface;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Welcome extends JFrame {
    public static final String WINDOW_TITLE = "WDP Problems";
    public static Font FONT_Body = new Font("Times New Roman", Font.PLAIN, 24);
    public static  Font FONT_Title = new Font("Times New Roman", Font.BOLD, 32);
    public static  Font FONT_Title_small = new Font("Times New Roman", Font.BOLD, 22);

    private JTextArea texteBienvenue;
    private ImageIcon backgroundImage;
    private JLabel backgroundLabel;
    private JButton BUTTON_Commancer;

    private void showBienvenue() {
        texteBienvenue = new JTextArea(
                "   Bienvenue dans notre application de résolution du problème de détermination du gagnant dans " +
                        "                                                 les enchères combinatoires (WDP).            " +
                "\n\n                         Explorez nos solutions pour maximiser vos gains lors des ventes!!!\n\n    " +
                "      Commencez dès maintenant et découvrez comment notre application peut vous aider " +
                 "                          à réaliser des ventes profitables dans le monde complexe des enchères combinatoires.                 ");
        texteBienvenue.setFont(this.FONT_Body);
        texteBienvenue.setForeground(Color.WHITE);
        texteBienvenue.setBackground(new Color(0, 0, 0 , 200)); // Rend le fond transparent
        texteBienvenue.setBounds(0,0,1000,300);

        texteBienvenue.setWrapStyleWord(true);
        texteBienvenue.setLineWrap(true);
        texteBienvenue.setEditable(false);
        texteBienvenue.setFocusable(false);
        texteBienvenue.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajout de l'espacement autour du texte
        int padding = 20;
        texteBienvenue.setBorder(new EmptyBorder(padding, padding, padding, padding));

        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(texteBienvenue, new GridBagConstraints());
    }

    private void showCommencer() {
        BUTTON_Commancer = new JButton("Commencer");
        BUTTON_Commancer.setFont(this.FONT_Title);

        // Personnalisation de l'apparence du bouton
        BUTTON_Commancer.setBackground(new Color(231, 188, 179 )); // Définit la couleur de fond marron
        BUTTON_Commancer.setForeground(Color.BLACK); // Définit la couleur du texte en blanc
        BUTTON_Commancer.setFocusPainted(false); // Désactive l'effet de mise au point

        // Personnalisation du cadre du bouton
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0 ), 2); // Crée un cadre de couleur noire avec une épaisseur de 2 pixels
        BUTTON_Commancer.setBorder(border);

        int padding = 10;
        BUTTON_Commancer.setBorder(new EmptyBorder(padding, 20, padding, 20));

        // Changement de l'apparence du curseur lorsqu'il survole le bouton
        BUTTON_Commancer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BUTTON_Commancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton est cliqué
                dispose(); // Ferme la fenêtre Welcome

                // Crée et affiche la nouvelle page Neaucat
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });



        // Configuration de la contrainte pour le texte
        GridBagConstraints texteConstraints = new GridBagConstraints();
        texteConstraints.gridx = 0; // Position en colonne
        texteConstraints.gridy = 0; // Position en ligne
        texteConstraints.insets = new Insets(0, 0, 20, 0); // Espacement en bas du texte

        // Configuration de la contrainte pour le bouton
        GridBagConstraints boutonConstraints = new GridBagConstraints();
        boutonConstraints.gridx = 0; // Position en colonne
        boutonConstraints.gridy = 1; // Position en ligne

        backgroundLabel.setLayout(new GridBagLayout());
        backgroundLabel.add(texteBienvenue, texteConstraints);
        backgroundLabel.add(BUTTON_Commancer, boutonConstraints);
    }
    public Welcome() throws IOException {
        super(WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/images/enchere.jpg"));
        backgroundLabel = new JLabel(backgroundImage);

        getContentPane().add(backgroundLabel);

        showBienvenue();

        showCommencer();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
