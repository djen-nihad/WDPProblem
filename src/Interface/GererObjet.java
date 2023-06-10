package Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Enchere.Objet;

public class GererObjet extends JFrame {
    public static JLabel backgroundLabel;
    private ImageIcon backgroundImage;
    private ListObjet listObjet;
    private JPanel ajouterObjet;

    private void addObject() {
        ajouterObjet = new JPanel();
        ajouterObjet.setLayout(null); // Set null layout
        ajouterObjet.setBounds(50, 100, 600, 700);
        ajouterObjet.setBackground(new Color(231, 188, 179 , 200));

        JLabel label = new JLabel("Ajouter un objet : ");
        label.setFont(Welcome.FONT_Title);
        label.setForeground(Color.BLACK);
        label.setBounds(5, 10, 400, 50);
        ajouterObjet.add(label);

        JLabel labelnom = new JLabel("Nom objet : ");
        labelnom.setFont(Welcome.FONT_Title_small);
        labelnom.setForeground(Color.BLACK);
        labelnom.setBounds(10, 100, 300, 50);
        ajouterObjet.add(labelnom);

        JTextField fieldNoum = new JTextField();
        fieldNoum.setFont(Welcome.FONT_Body);
        fieldNoum.setForeground(Color.BLACK);
        fieldNoum.setBounds(10, 150, 300, 50);

        // Demander le focus sur le JTextField après l'affichage de la JFrame
        SwingUtilities.invokeLater(() -> {
            fieldNoum.requestFocusInWindow();
        });
        ajouterObjet.add(fieldNoum);

        JLabel labelDescription = new JLabel("Description de l'objet : ");
        labelDescription.setFont(Welcome.FONT_Title_small);
        labelDescription.setForeground(Color.BLACK);
        labelDescription.setBounds(10, 210, 300, 50);
        ajouterObjet.add(labelDescription);

        JTextArea fieldDescr = new JTextArea();
        fieldDescr.setFont(Welcome.FONT_Body);
        fieldDescr.setForeground(Color.BLACK);
        int padding = 10;
        EmptyBorder border = new EmptyBorder(padding, padding, padding, padding);
        fieldDescr.setBorder(BorderFactory.createCompoundBorder(fieldDescr.getBorder(), border));
        fieldDescr.setLineWrap(true);
        fieldDescr.setWrapStyleWord(true);
        fieldDescr.setBounds(10, 260, 500, 300);
        ajouterObjet.add(fieldDescr);

        JButton BUTTON_Ajouter = new JButton("Ajouter");
        BUTTON_Ajouter.setFont(Welcome.FONT_Title);
        BUTTON_Ajouter.setBackground(new Color(55, 37, 53));
        BUTTON_Ajouter.setForeground(Color.WHITE);
        BUTTON_Ajouter.setBounds(100, 570, 200, 50);
        BUTTON_Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( fieldNoum.getText().isEmpty() ){
                    JOptionPane.showMessageDialog(GererObjet.this, "Erreur : le nom de l'objet ne doit pas etre vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Objet objet = new Objet( fieldNoum.getText() , fieldDescr.getText());
                    Enchere.listObjets.add(objet);
                    mise_a_jour_listObjet();
                    fieldNoum.setText(null);
                }
            }
        });
        ajouterObjet.add(BUTTON_Ajouter);

        backgroundLabel.add(ajouterObjet);
    }

    private void addNavBar() {
        JButton BUTTON_Welcome = new JButton("Aller au menu >> ");
        BUTTON_Welcome.setBackground(Color.BLACK);
        BUTTON_Welcome.setFont(Welcome.FONT_Title_small);

        BUTTON_Welcome.setForeground(Color.WHITE);
        BUTTON_Welcome.setBounds(50, 10, 250, 50);
        // Changement de l'apparence du curseur lorsqu'il survole le bouton
        BUTTON_Welcome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BUTTON_Welcome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton est cliqué
                dispose(); // Ferme la fenêtre Welcome

                // Crée et affiche la nouvelle page Neaucat
                Menu menu = new Menu();
                menu.setVisible(true);
            }
        });

        backgroundLabel.add(BUTTON_Welcome);

    }

    public  GererObjet(){
        super(Welcome.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/images/enchere.jpg"));
        backgroundLabel = new JLabel(backgroundImage);

        if  ( ListObjet.Objets.length == 0 ){
            ListObjet.Objets = new String[1];
            ListObjet.Objets[0] = "Liste est vide";
        }
        listObjet = new ListObjet(ListObjet.Objets);

        getContentPane().add(backgroundLabel);

        addNavBar();
        addObject();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void mise_a_jour_listObjet(){
        ListObjet.Objets = new String[Enchere.listObjets.size()];
        for ( int i = 0; i < ListObjet.Objets.length; i++)
            ListObjet.Objets[i] = Enchere.listObjets.get(i).toString();
        listObjet = new ListObjet(ListObjet.Objets);

        pack();
    }

}
