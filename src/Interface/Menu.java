package Interface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{

    public static JLabel backgroundMenuLabel;
    private ImageIcon backgroundImage;
    private JButton BUTTON_GererObjets;
    private JButton BUTTON_GererParticipant;
    private JButton BUTTON_Visulaiser;
    private JButton BUTTON_Benchmarks;

    public  Menu(){
        super(Welcome.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/images/enchere.jpg"));
        backgroundMenuLabel = new JLabel(backgroundImage);

        this.showButtonGererObjets();

        this.showButtonGererParticipant();

        this.showButtonVisulaiser();

        this.showButtonBenchmarks();


        getContentPane().add(backgroundMenuLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void showButtonGererObjets() {
        BUTTON_GererObjets = new JButton("Gestion des Objets");
        BUTTON_GererObjets.setFont(Welcome.FONT_Title);
        // Personnalisation de l'apparence du bouton
        BUTTON_GererObjets.setOpaque(true);
        BUTTON_GererObjets.setBackground(new Color(231, 188, 179 )); // Définit la couleur de fond marron
        BUTTON_GererObjets.setForeground(Color.BLACK); // Définit la couleur du texte en blanc
        BUTTON_GererObjets.setFocusPainted(false); // Désactive l'effet de mise au point

        // Personnalisation du cadre du bouton
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0 ), 2); // Crée un cadre de couleur noire avec une épaisseur de 2 pixels
        BUTTON_GererObjets.setBorder(border);

        int padding = 10;
        BUTTON_GererObjets.setBorder(new EmptyBorder(padding, 50, padding , 50));

        // Changement de l'apparence du curseur lorsqu'il survole le bouton
        BUTTON_GererObjets.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BUTTON_GererObjets.setBounds(350 , 300 , 550,50);

        BUTTON_GererObjets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton est cliqué
                dispose(); // Ferme la fenêtre Welcome

                // Crée et affiche la nouvelle page Neaucat
                GererObjet gererObjet = new GererObjet();
                gererObjet.setVisible(true);
            }
        });
        // Configuration de la contrainte pour le bouton

     //   GridBagConstraints boutonConstraints = new GridBagConstraints();
      //  boutonConstraints.gridx = 0; // Position en colonne
    //    boutonConstraints.gridy = 1; // Position en ligne
       // boutonConstraints.insets = new Insets(10, 0, 10, 0);

       // backgroundMenuLabel.setLayout(new GridBagLayout());
        backgroundMenuLabel.add(BUTTON_GererObjets);
    }

    private void showButtonGererParticipant() {

        BUTTON_GererParticipant = new JButton("Gestion des Participants");
        BUTTON_GererParticipant.setFont(Welcome.FONT_Title);
        // Personnalisation de l'apparence du bouton
        BUTTON_GererParticipant.setOpaque(true);
        BUTTON_GererParticipant.setBackground(new Color(231, 188, 179 )); // Définit la couleur de fond marron
        BUTTON_GererParticipant.setForeground(Color.BLACK); // Définit la couleur du texte en blanc
        BUTTON_GererParticipant.setFocusPainted(false); // Désactive l'effet de mise au point

        // Personnalisation du cadre du bouton
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0 ), 2); // Crée un cadre de couleur noire avec une épaisseur de 2 pixels
        BUTTON_GererParticipant.setBorder(border);

        int padding = 10;
        BUTTON_GererParticipant.setBorder(new EmptyBorder(padding, 50, padding , 50));

        // Changement de l'apparence du curseur lorsqu'il survole le bouton
        BUTTON_GererParticipant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BUTTON_GererParticipant.setBounds(350 , 400 , 550,50);

        BUTTON_GererParticipant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton est cliqué
                dispose(); // Ferme la fenêtre Welcome

                // Crée et affiche la nouvelle page Neaucat
                GererParticipant gererParticipant = new GererParticipant();
                gererParticipant.setVisible(true);
            }
        });
        backgroundMenuLabel.add(BUTTON_GererParticipant);
    }

    private void showButtonVisulaiser() {

        BUTTON_Visulaiser = new JButton("Visulaiser et  lancer l'enchere");
        BUTTON_Visulaiser.setFont(Welcome.FONT_Title);
        // Personnalisation de l'apparence du bouton
        BUTTON_Visulaiser.setOpaque(true);
        BUTTON_Visulaiser.setBackground(new Color(231, 188, 179));// Définit la couleur de fond marron
        BUTTON_Visulaiser.setForeground(Color.BLACK); // Définit la couleur du texte en blanc
        BUTTON_Visulaiser.setFocusPainted(false); // Désactive l'effet de mise au point

        // Personnalisation du cadre du bouton
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0 ), 2); // Crée un cadre de couleur noire avec une épaisseur de 2 pixels
        BUTTON_Visulaiser.setBorder(border);

        int padding = 10;
        BUTTON_Visulaiser.setBorder(new EmptyBorder(padding, 50, padding , 50));

        // Changement de l'apparence du curseur lorsqu'il survole le bouton
        BUTTON_Visulaiser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BUTTON_Visulaiser.setBounds(350 , 500 , 550,50);

        BUTTON_Visulaiser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton est cliqué
                dispose(); // Ferme la fenêtre Welcome

                // Crée et affiche la nouvelle page Neaucat
                Enchere enchere = new Enchere();
                enchere.setVisible(true);
            }
        });
        backgroundMenuLabel.add(BUTTON_Visulaiser);
    }

    private void showButtonBenchmarks() {

        BUTTON_Benchmarks = new JButton("Benchmarks");
        BUTTON_Benchmarks.setFont(Welcome.FONT_Title);
        // Personnalisation de l'apparence du bouton
        BUTTON_Benchmarks.setOpaque(true);
        BUTTON_Benchmarks.setBackground(new Color(231, 188, 179));// Définit la couleur de fond marron
        BUTTON_Benchmarks.setForeground(Color.BLACK); // Définit la couleur du texte en blanc
        BUTTON_Benchmarks.setFocusPainted(false); // Désactive l'effet de mise au point

        // Personnalisation du cadre du bouton
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0 ), 2); // Crée un cadre de couleur noire avec une épaisseur de 2 pixels
        BUTTON_Benchmarks.setBorder(border);

        int padding = 10;
        BUTTON_Benchmarks.setBorder(new EmptyBorder(padding, 50, padding , 50));

        // Changement de l'apparence du curseur lorsqu'il survole le bouton
        BUTTON_Benchmarks.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BUTTON_Benchmarks.setBounds(350 , 600 , 550,50);

        BUTTON_Benchmarks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton est cliqué
                dispose(); // Ferme la fenêtre Welcome

                // Crée et affiche la nouvelle page Neaucat
                Benchmarks benchmarks = new Benchmarks();
                benchmarks.setVisible(true);
            }
        });
        backgroundMenuLabel.add(BUTTON_Benchmarks);
    }




}
