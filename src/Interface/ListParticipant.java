package Interface;

import javax.swing.*;
import java.awt.*;

public class ListParticipant {
    public static  JPanel participant;
    private MyList participantsMyList;

    // Créer un tableau de participants
    public static String[] participants =  new String[0];
    public ListParticipant() {
        /**
        participant = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(231, 188, 179, 180));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        participant.setLayout(null);
        participant.setPreferredSize(new Dimension(400, 200));
         **/
        participant = new JPanel();
        participant.setBounds(0,50, 400, 750);
        participant.setBackground(new Color(231, 188, 179 , 200));

        participant.setLayout(null);

        // Exemple de composant ajouté dans le panneau participant
        JLabel label = new JLabel("Liste des participants");
        label.setFont(Welcome.FONT_Title);
        label.setBounds(40,100,400,40);
        label.setBackground(new Color(231, 188, 179));
        participant.add(label);

        participantsMyList = new MyList(40,200 , participants);

        participant.add(participantsMyList);

     //   Enchere.backgroundLabel.setLayout(new BorderLayout());
        Enchere.backgroundLabel.add(participant);
    }

    public ListParticipant(String[] participants) {
        /**
         participant = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(231, 188, 179, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        }
        };
         participant.setLayout(null);
         participant.setPreferredSize(new Dimension(400, 200));
         **/
        participant = new JPanel();
        participant.setBounds(850,50, 400, 750);
        participant.setBackground(new Color(231, 188, 179 , 200));

        participant.setLayout(null);

        // Exemple de composant ajouté dans le panneau participant
        JLabel label = new JLabel("Liste des participants");
        label.setFont(Welcome.FONT_Title);
        label.setBounds(40,100,400,40);
        label.setBackground(new Color(231, 188, 179));
        participant.add(label);

        participantsMyList = new MyList(40,200 , participants);

        participant.add(participantsMyList);

        //   Enchere.backgroundLabel.setLayout(new BorderLayout());
        GererParticipant.backgroundLabel.add(participant);
    }


    public ListParticipant(String[] participants , int i) {
        participant = new JPanel();
        participant.setBounds(850,150, 400, 600);
        participant.setBackground(new Color(188, 165, 111,180));

        participant.setLayout(null);

        // Exemple de composant ajouté dans le panneau participant
        JLabel label = new JLabel("Liste des Ganagnats");
        label.setFont(Welcome.FONT_Title);
        label.setBounds(40,10,400,40);
        label.setBackground(new Color(125, 60, 22,180));
        participant.add(label);

        participantsMyList = new MyList(40,80 , participants);

        participant.add(participantsMyList);

        //   Enchere.backgroundLabel.setLayout(new BorderLayout());
        Winner.backgroundLabel.add(participant);
    }


}
