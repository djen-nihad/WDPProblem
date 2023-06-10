package Interface;
import javax.swing.*;
import java.awt.*;


public class MyList extends JPanel {
    private JList<String> List;
    private  JScrollPane scrollPane;
    public MyList(int x , int y , String[] participants) {
        // Définir la disposition du panneau
        setLayout(new BorderLayout());
        setBounds(x,y,300,500);
        // Créer une JList pour afficher les participants
        List = new JList<>(participants);
        List.setFont(Welcome.FONT_Body);
        List.setForeground(Color.BLACK);
        List.setBackground(new Color(255, 255, 255));
        // Créer une barre de défilement et l'associer à la liste
        scrollPane = new JScrollPane(List);
       // scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBackground(null);
        scrollPane.setBorder(null);
        this.add(scrollPane);
        this.setBackground(new Color(255, 255, 255));
    }

}
