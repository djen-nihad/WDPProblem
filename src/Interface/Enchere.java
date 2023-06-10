package Interface;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Enchere.Objet;
import Enchere.Participant;
import MetaHeurstique.BSOAgent;
import MetaHeurstique.Bee;
import MetaHeurstique.WDPProblem;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;

public class Enchere extends JFrame {
    public static  JLabel backgroundLabel;
    public static  List<Objet> listObjets = new ArrayList<>();
    public static  List<Participant> listParticipants = new ArrayList<>();
    public static  List<Participant> listWinner = new ArrayList<>();
    public static String[] gagnats = new String[0];
    private ImageIcon backgroundImage;
    private ListParticipant listParticipant;
    private ListObjet listObjet;
    private JButton BUTTON_Lancer;
    private JTextArea timing;
    private Timer timer;
    private int counter;
    private  JLabel timeLabel;
    public static Bee solution;

    public static Lock lock = new ReentrantLock();
    public static Condition agentTermineCondition = lock.newCondition();
    public static boolean agentTermine = false;
    public static void attendreTerminaisonAgent() {
        lock.lock();
        Enchere.agentTermine = false;
        try {
            while (!agentTermine) {
                agentTermineCondition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void agentTermine() {
        lock.lock();
        try {
            agentTermine = true;
            agentTermineCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    private void showButtonLancer(){

        BUTTON_Lancer = new JButton("Annoncer");

        BUTTON_Lancer.setFont(Welcome.FONT_Title);
        // Personnalisation de l'apparence du bouton
        BUTTON_Lancer.setBackground(new Color(231, 188, 179 ));// Définit la couleur de fond marron
        BUTTON_Lancer.setForeground(Color.BLACK); // Définit la couleur du texte en blanc
        BUTTON_Lancer.setFocusPainted(false); // Désactive l'effet de mise au point

        // Personnalisation du cadre du bouton
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0 ), 2); // Crée un cadre de couleur noire avec une épaisseur de 2 pixels
        BUTTON_Lancer.setBorder(border);

        int padding = 10;
        BUTTON_Lancer.setBorder(new EmptyBorder(padding, 50, padding , 50));

        // Changement de l'apparence du curseur lorsqu'il survole le bouton
        BUTTON_Lancer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BUTTON_Lancer.setBounds(450 , 400 , 300,50);

        BUTTON_Lancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                timer.start(); // Démarre le timer


                WDPProblem problem = new WDPProblem(Enchere.listParticipants);

                jade.core.Runtime runtime = jade.core.Runtime.instance();
                jade.wrapper.AgentContainer container = runtime.createMainContainer(new ProfileImpl());
                try {
                    AgentController agentController = container.createNewAgent("BSO", BSOAgent.class.getName(), new Object[]{problem});
                    agentController.start();
                    attendreTerminaisonAgent();
                    for ( int i = 0; i < Enchere.listParticipants.size(); i++)
                        if ( solution.solution[i]) Enchere.listWinner.add(Enchere.listParticipants.get(i));

                    Winner.winner = new String[Enchere.listWinner.size()];
                    for ( int i = 0; i < Winner.winner.length; i++)
                        Winner.winner[i] = Enchere.listWinner.get(i).toString();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                timer.stop();
                dispose(); // Ferme la fenêtre Welcome

                // Crée et affiche la nouvelle page Neaucat
                Winner winner = new Winner();
                winner.setVisible(true);





            }
        });


        Enchere.backgroundLabel.add(BUTTON_Lancer);


    }

    private void showTiming() {
        timing = new JTextArea("Timing : ");
        timing.setFont(Welcome.FONT_Title_small);
        timing.setForeground(Color.black);
       timing.setBackground(Color.RED);
        timing.setBounds(500, 20, 200, 40);


        timing.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));

        timeLabel = new JLabel("0 s");
        timeLabel.setBounds(605, 30, 100, 30);
        timeLabel.setFont(Welcome.FONT_Title_small);


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lorsque le délai s'écoule
                counter++;
                timeLabel.setText(String.valueOf(counter) + " s");
            }
        });




        Enchere.backgroundLabel.add(timeLabel);
        Enchere.backgroundLabel.add(timing);
    }

    private void addNavBar() {
        JButton BUTTON_Welcome = new JButton("Aller au menu");
        BUTTON_Welcome.setBackground(Color.BLACK);
        BUTTON_Welcome.setFont(Welcome.FONT_Title_small);

        BUTTON_Welcome.setForeground(Color.WHITE);
        BUTTON_Welcome.setBounds(450 , 480 , 300,50);
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

        Enchere.backgroundLabel.add(BUTTON_Welcome);

    }

    public Enchere()  {
        super(Welcome.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/images/enchere.jpg"));
        backgroundLabel = new JLabel(backgroundImage);

        listParticipant = new ListParticipant();

        listObjet = new ListObjet();

        showButtonLancer();
        addNavBar();

        showTiming();

        getContentPane().add(backgroundLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            try {
                new Welcome();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
