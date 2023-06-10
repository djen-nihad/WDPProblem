package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import Enchere.Participant;
import Enchere.Objet;

public class GererParticipant extends JFrame {

    public static JLabel backgroundLabel;
    private ImageIcon backgroundImage;
    private ListParticipant listParticipant;
    JPanel ajouterParticipant;
    private JList<String> list;
    List<String> selectedValues = new ArrayList<>();
    List<Integer> seclectionner = new ArrayList<>();


    private  void selectObjets(){

        list = new JList<>(ListObjet.Objets);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setFont(Welcome.FONT_Body);
        list.setSelectionBackground(new Color(152, 251, 152)); // Vert clair
//        list.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int index = list.locationToIndex(e.getPoint());
//                if (index >= 0 && index < list.getModel().getSize()) {
//                    if (!list.isSelectedIndex(index)) {
//                        list.addSelectionInterval(index, index);
//                    }
//                }
//            }
//        });
//        list.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int[] selectedIndices = list.getSelectedIndices();
//                for (int index : selectedIndices) {
//                    String selectedValue = list.getModel().getElementAt(index);
//                    selectedValues.add(selectedValue);
//                }
//                // Faites quelque chose avec les valeurs sélectionnées
//                System.out.println("Valeurs sélectionnées : " + selectedValues);
//            }
//        });


        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 380, 400, 180);
        ajouterParticipant.add(scrollPane);
    }
    private void addParticipant() {
        ajouterParticipant = new JPanel();
        ajouterParticipant.setLayout(null); // Set null layout
        ajouterParticipant.setBounds(50, 100, 600, 700);
        ajouterParticipant.setBackground(new Color(231, 188, 179 , 200));

        JLabel label = new JLabel("Ajouter Participant : ");
        label.setFont(Welcome.FONT_Title);
        label.setForeground(Color.BLACK);
        label.setBounds(5, 10, 400, 50);
        ajouterParticipant.add(label);

        JLabel labelnom = new JLabel("Nom du Participant : ");
        labelnom.setFont(Welcome.FONT_Title_small);
        labelnom.setForeground(Color.BLACK);
        labelnom.setBounds(10, 100, 300, 50);
        ajouterParticipant.add(labelnom);

        JTextField fieldNoum = new JTextField();
        fieldNoum.setFont(Welcome.FONT_Body);
        fieldNoum.setForeground(Color.BLACK);
        fieldNoum.setBounds(10, 150, 300, 50);

        // Demander le focus sur le JTextField après l'affichage de la JFrame
        SwingUtilities.invokeLater(() -> {
            fieldNoum.requestFocusInWindow();
        });

        ajouterParticipant.add(fieldNoum);

        JLabel labelDescription = new JLabel("Montant : ");
        labelDescription.setFont(Welcome.FONT_Title_small);
        labelDescription.setForeground(Color.BLACK);
        labelDescription.setBounds(10, 210, 300, 50);
        ajouterParticipant.add(labelDescription);

        JTextField fieldMontant = new JTextField();
        fieldMontant.setFont(Welcome.FONT_Body);
        fieldMontant.setForeground(Color.BLACK);
        fieldMontant.setBounds(10, 250, 300, 50);
        ajouterParticipant.add(fieldMontant);

        JLabel labelObjets = new JLabel("List des objets : ");
        labelObjets.setFont(Welcome.FONT_Title_small);
        labelObjets.setForeground(Color.BLACK);
        labelObjets.setBounds(10, 320, 300, 50);
        ajouterParticipant.add(labelObjets);

        selectObjets();

        JButton BUTTON_Ajouter = new JButton("Ajouter");
        BUTTON_Ajouter.setFont(Welcome.FONT_Title);
        BUTTON_Ajouter.setBackground(new Color(55, 37, 53));
        BUTTON_Ajouter.setForeground(Color.WHITE);
        BUTTON_Ajouter.setBounds(100, 600, 200, 50);
        BUTTON_Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( fieldNoum.getText().isEmpty() || fieldMontant.getText().isEmpty() || list.isSelectionEmpty() ){
                    JOptionPane.showMessageDialog(GererParticipant.this, "Erreur : le nom de participant et le montant ne doit pas etre vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    selectedValues = list.getSelectedValuesList();
                    System.out.println(selectedValues);
                    for ( String v : selectedValues){
                        for ( Objet o : Enchere.listObjets){
                            if ( o.toString().equals(v)){
                                seclectionner.add(o.id);
                                break;
                            }
                        }
                    }
                    Participant participant = new Participant(fieldNoum.getText() , Double.parseDouble(fieldMontant.getText()) , seclectionner) ;
                    Enchere.listParticipants.add(participant);
                    mise_a_jour_listParticipant();
                    list.clearSelection();
                    seclectionner = new ArrayList<>();
                    fieldNoum.setText(null);
                    fieldMontant.setText(null);
                }
            }
        });
        ajouterParticipant.add(BUTTON_Ajouter);

        backgroundLabel.add(ajouterParticipant);
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
    public  GererParticipant(){
        super(Welcome.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/images/enchere.jpg"));
        backgroundLabel = new JLabel(backgroundImage);

        if  ( ListParticipant.participants.length == 0 ){
            ListParticipant.participants = new String[1];
            ListParticipant.participants[0] = "Liste est vide";
        }

        listParticipant = new ListParticipant(ListParticipant.participants);

        addNavBar();
        addParticipant();

        getContentPane().add(backgroundLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void mise_a_jour_listParticipant(){
        ListParticipant.participants = new String[Enchere.listParticipants.size()];
        for ( int i = 0; i < ListParticipant.participants.length; i++)
            ListParticipant.participants[i] = Enchere.listParticipants.get(i).toString();
        listParticipant = new ListParticipant(ListParticipant.participants);
        pack();
    }
}
