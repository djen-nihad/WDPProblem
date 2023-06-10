package Interface;

import MetaHeurstique.BSOAgent;
import MetaHeurstique.WDPProblem;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Benchmarks extends JFrame {

    public static JLabel backgroundLabel;
    private ImageIcon backgroundImage;
    private JPanel choisirfichier;
    private JPanel lanceResultat;
    private  long startTime , endTime;
    private JLabel waitlabel;

    private void choisirFichier() {
        choisirfichier = new JPanel();
        choisirfichier.setLayout(null); // Set null layout
        choisirfichier.setBounds(50, 100, 900, 350);
        choisirfichier.setBackground(new Color(231, 188, 179 , 200));

        JLabel label = new JLabel("Donne le chemin du fichier : ");
        label.setFont(Welcome.FONT_Title);
        label.setForeground(Color.BLACK);
        label.setBounds(5, 10, 700, 50);
        choisirfichier.add(label);

        JTextField path = new JTextField();
        path.setFont(Welcome.FONT_Body);
        path.setForeground(Color.BLACK);
        path.setBounds(10, 100, 800, 50);
        path.setText("Benchmarks/wdp_lau_goh/lau_goh/group02_b1000_g1000/in204.txt");

        // Demander le focus sur le JTextField après l'affichage de la JFrame
        SwingUtilities.invokeLater(() -> {
            path.requestFocusInWindow();
        });
        choisirfichier.add(path);

        JButton BUTTON_Ajouter = new JButton("Lancer");
        BUTTON_Ajouter.setFont(Welcome.FONT_Title);
        BUTTON_Ajouter.setBackground(new Color(55, 37, 53));
        BUTTON_Ajouter.setForeground(Color.WHITE);
        BUTTON_Ajouter.setBounds(10, 200, 200, 50);
        BUTTON_Ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                waitlabel.setVisible(true);
                removeResultat();
                pack();

                JOptionPane.showMessageDialog(Benchmarks.this, "Veuillez patienter un moment.", "Attente", JOptionPane.INFORMATION_MESSAGE);


                WDPProblem problem = new WDPProblem(path.getText());

                jade.core.Runtime runtime = jade.core.Runtime.instance();
                jade.wrapper.AgentContainer container = runtime.createMainContainer(new ProfileImpl());
                try {
                    AgentController agentController = container.createNewAgent("BSO", BSOAgent.class.getName(), new Object[]{problem});
                    startTime = System.currentTimeMillis();
                    agentController.start();
                    Enchere.attendreTerminaisonAgent();
                    endTime = System.currentTimeMillis();
                  //  agentController.kill();
                    lancerResultat();
                    waitlabel.setVisible(false);
                    pack();
                    repaint();
                    revalidate();
                    // Demander le focus sur le JTextField après l'affichage de la JFrame
                    SwingUtilities.invokeLater(() -> {
                        path.requestFocusInWindow();
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        choisirfichier.add(BUTTON_Ajouter);

        backgroundLabel.add(choisirfichier);
    }
    private  void removeResultat(){
        if ( lanceResultat != null )
            backgroundLabel.remove(lanceResultat);
        pack();
        repaint();
        revalidate();
    }
    private void waitAfficher(boolean visible){
        waitlabel = new JLabel("Attends un peu s'il veut plait : ) ");
        waitlabel.setFont(Welcome.FONT_Title);
        waitlabel.setForeground(Color.RED);
        waitlabel.setBounds(5, 130, 900, 350);
        waitlabel.setVisible(visible);
        choisirfichier.add(waitlabel);

    }
    private  void lancerResultat(){
        lanceResultat = new JPanel();
        lanceResultat.setLayout(null); // Set null layout
        lanceResultat.setBounds(50, 500, 900, 200);
        lanceResultat.setBackground(new Color(231, 188, 179 , 200));

        JLabel revenuLabel = new JLabel("Revenu : " + Enchere.solution.dance);
        revenuLabel.setFont(Welcome.FONT_Title);
        revenuLabel.setForeground(Color.BLACK);
        revenuLabel.setBounds(10, 0, 700, 100);
        lanceResultat.add(revenuLabel);

        long tempsExecution = endTime - startTime;

        JLabel tpLabel = new JLabel("Temps d'execution : "+ tempsExecution + " ms");
        tpLabel.setFont(Welcome.FONT_Title);
        tpLabel.setForeground(Color.BLACK);
        tpLabel.setBounds(10, 70, 700, 100);
        lanceResultat.add(tpLabel);

        backgroundLabel.add(lanceResultat);

    }

    public Benchmarks(){
        super(Welcome.WINDOW_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        backgroundImage = new ImageIcon(getClass().getResource("/images/enchere.jpg"));
        backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(null);
        this.addNavBar();

        choisirFichier();
        waitAfficher(false);

        getContentPane().add(backgroundLabel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
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
}
