package MetaHeurstique;


import Interface.Enchere;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.util.ArrayList;
import java.util.List;

public class BSOAgent extends Agent {
    private  int maxIteration = 500;
    private  int nombreBees = 100;
    private  int flip = 100;
    private  int maxChance = 5;
    private  int nombreChance = maxChance;
    public static WDPProblem problem;
    public  Bee sref;
    public Bee bestSolution;
    public Bee bestQuality;
    public Bee bestDiversity;
    public int maxDistnace;
    public List<Bee> tabou;
    public List<Bee> bees;
    public List<Bee> danceTable;
    public List<String> listLocalSearchAgents = new ArrayList<>();

    protected void setup() {
        Object[] arguments = getArguments();
        if ( arguments != null ){
            this.problem = (WDPProblem) arguments[0];
            sref = Bee.BeeInit(problem);
     //       sref = Bee.BeeInitRandom(problem);
            System.out.println(" BeeIinit : " + sref.dance);
            tabou = new ArrayList<>();
            tabou.add(sref);
            bees = Bee.searchArea(sref , flip , nombreBees , problem);
            bestSolution = new Bee(sref);
            addBehaviour(new CreateLocalSearchAgents());
        }
        else {
            System.out.println(" Error : Problem not received ! ");
        }
    }
    class CreateLocalSearchAgents extends OneShotBehaviour{
        @Override
        public void action() {
            for(int i = 0; i < nombreBees; i++){
                // Create Agents
                try {
                    AgentContainer container = getContainerController();
                    AgentController agentController;
                    agentController = container.createNewAgent("agentSearchLocal_" + i + "_" + maxIteration, SearchLocalAgent.class.getName(), new Object[]{ bees.get(i)});
                    listLocalSearchAgents.add("agentSearchLocal_" + i + "_" + maxIteration);
                    agentController.start();
                } catch (StaleProxyException e) {
                    throw new RuntimeException(e);
                }
            }
            danceTable = new ArrayList<>();
            addBehaviour(new WaitAnswers());
        }
    }
    class WaitAnswers extends Behaviour {
        int nombreReceived = 0;
        @Override
        public void action() {
            ACLMessage message = this.myAgent.blockingReceive();
            int discatnce;
            Bee beeReceived;
            try {
                Object[] contenu = (Object[]) message.getContentObject();
                beeReceived = (Bee) contenu[0];
                danceTable.add(beeReceived);
                if ( bestDiversity == null )
                    bestDiversity = new Bee(beeReceived);
                else {
                    discatnce = beeReceived.calculateDiversity(sref.solution);
                    if ( discatnce > maxDistnace ){
                        maxDistnace = discatnce;
                        bestDiversity = new Bee(beeReceived);
                    }
                }
                if ( bestQuality == null || beeReceived.dance > bestQuality.dance )
                    bestQuality = new Bee(beeReceived);
                if ( bestQuality.dance > bestSolution.dance)
                    bestSolution = new Bee(bestQuality);
                nombreReceived++;
            } catch (UnreadableException e) {
                 throw new RuntimeException(e);
            }
        }
        @Override
        public boolean done() {
            if( nombreReceived == listLocalSearchAgents.size()){
                maxIteration--;
                if ( maxIteration % 100 == 0)    {
                    System.out.println("Best Quality : " + bestQuality.dance);
                    System.out.println("Best Diversity : " + bestDiversity.dance);
                }
                addBehaviour(new selectetion());
                return true;
            }
            return false;
        }
    }
    class selectetion extends OneShotBehaviour{
        @Override
        public void action() {
            if ( maxIteration == 0 ) {
                System.out.println("Best F is " + bestSolution.dance);
                System.out.println("is solution valide ? " + bestSolution.isValide(problem));
                Enchere.solution = bestSolution;
                Enchere.agentTermine();
                this.myAgent.doDelete();
            }
            if ( maxIteration % 100 == 0 )
                System.out.println("NIteration:" + maxIteration + " F:" + bestSolution.dance);

            listLocalSearchAgents.clear();

            if ( bestQuality.dance - sref.dance > 0 ){
                nombreChance = maxChance;
                sref = bestQuality;
            }
            else {
                nombreChance--;
                if ( nombreChance > 0 ) {
                    sref = bestQuality;
                }
                else {
                    sref = bestDiversity;
                    nombreChance = maxChance;
                }
            }
            int k = 10;
            // TODO: traiter le cas ou sref existe dans tabou liste
            for ( int i = 0; i < tabou.size() && k > 0; i++)
                if ( sref.calculateDiversity(tabou.get(i).solution) == 0) {
                    sref = danceTable.get((int) (Math.random() * danceTable.size()));
                    i = 0;
                    k--;
               //     System.out.println(" Existe dans tabou liste : " + maxIteration);
                }
            if ( k == 0 ) {
                System.out.println(" K = 0. Arreter le programme");
                Enchere.solution = bestSolution;
                Enchere.agentTermine();
                this.myAgent.doDelete();
            }
            tabou.add(new Bee(sref));
            bestQuality = null;
            bestDiversity = null;
            bees = Bee.searchArea(sref , flip , nombreBees , problem);
            addBehaviour(new CreateLocalSearchAgents());
        }
    }



}
