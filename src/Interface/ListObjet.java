package Interface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListObjet {
    public static JPanel ObjetsPanel;
    private MyList objetsMyList;

    // Créer un tableau de participants
    public static String[] Objets = new String[0];

    public ListObjet() {
        ObjetsPanel = new JPanel();
        ObjetsPanel.setBounds(850,50, 400, 750);
        ObjetsPanel.setBackground(new Color(231, 188, 179 , 200));

        ObjetsPanel.setLayout(null);

        // Exemple de composant ajouté dans le panneau participant
        JLabel label = new JLabel("Liste des Objets");
        label.setFont(Welcome.FONT_Title);
        label.setBounds(40,100,400,40);
        label.setBackground(new Color(231, 188, 179));
        ObjetsPanel.add(label);

        objetsMyList = new MyList(40,200 , Objets);

        ObjetsPanel.add(objetsMyList);

        //   Enchere.backgroundLabel.setLayout(new BorderLayout());
        Enchere.backgroundLabel.add(ObjetsPanel);
    }

    public ListObjet(String[] Objets) {
        ObjetsPanel = new JPanel();
        ObjetsPanel.setBounds(850,50, 400, 750);
        ObjetsPanel.setBackground(new Color(231, 188, 179 , 200));

        ObjetsPanel.setLayout(null);

        // Exemple de composant ajouté dans le panneau participant
        JLabel label = new JLabel("Liste des Objets");
        label.setFont(Welcome.FONT_Title);
        label.setBounds(40,100,400,40);
        label.setBackground(new Color(231, 188, 179));
        ObjetsPanel.add(label);

        objetsMyList = new MyList(40,200 , Objets);

        ObjetsPanel.add(objetsMyList);

        //   Enchere.backgroundLabel.setLayout(new BorderLayout());
        GererObjet.backgroundLabel.add(ObjetsPanel);
    }


}
