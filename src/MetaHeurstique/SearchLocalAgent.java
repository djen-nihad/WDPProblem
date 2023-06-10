package MetaHeurstique;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchLocalAgent extends Agent {
    private  int maxStep = 30;
    public  Bee bee ;

    protected void setup() {
        Object[] arguments = getArguments();
        if ( arguments != null ){
            this.bee = (Bee) arguments[0];
            addBehaviour(new localSearchAlgorithme());
        }
        else {
            System.out.println(" Error : Problem not received ! ");
        }
    }
    class localSearchAlgorithme extends OneShotBehaviour {
        @Override
        public void action() {
            int x;
            double[] weights = new double[BSOAgent.problem.getSizeWDP()];
            Bee bee_temp = new Bee(bee);
            for ( int i = 0; i < maxStep; i++) {
                // genere un voisin
                x = (int) ( Math.random() * BSOAgent.problem.getSizeWDP());
                bee_temp.solution[x] = ! bee_temp.solution[x];
                if ( bee_temp.solution[x])
                    for ( int k = 0; k < BSOAgent.problem.getSizeWDP(); k++)
                        if ( BSOAgent.problem.conflits[x][k] && x != k ) bee_temp.solution[k] = false;
                bee_temp.calculateDance(BSOAgent.problem);
                if ( bee_temp.dance > bee.dance )
                    bee = new Bee(bee_temp);
            }
            if ( Math.random() > 0.5 ){
                List<Integer> availableNoued =  new ArrayList<>();
                for ( int i = 0; i < BSOAgent.problem.getSizeWDP(); i++)
                    availableNoued.add(i);
                for ( int i = 0; i < BSOAgent.problem.getSizeWDP() && !availableNoued.isEmpty(); i++)
                    if (  bee.solution[i] ){
                        availableNoued.remove(Integer.valueOf(i));
                        for ( int j = 0; j < BSOAgent.problem.getSizeWDP() && !availableNoued.isEmpty(); j++)
                            if ( BSOAgent.problem.conflits[i][j]) availableNoued.remove(Integer.valueOf(j));
                    }
                int maxNoued;
                while (!availableNoued.isEmpty()){
                    maxNoued = availableNoued.get(0);
                    for ( int i = 0; i < availableNoued.size(); i++)
                        if (BSOAgent.problem.weights[availableNoued.get(i)] > BSOAgent.problem.weights[maxNoued])
                            maxNoued = availableNoued.get(i);
                    bee.solution[maxNoued] = true;
                    availableNoued.remove(Integer.valueOf(maxNoued));
                    for ( int j = 0; j < BSOAgent.problem.getSizeWDP() && !availableNoued.isEmpty(); j++){
                        if ( BSOAgent.problem.conflits[j][maxNoued])
                            availableNoued.remove(Integer.valueOf(j));
                    }
                }
            }
            // Envoyer le resultat de recherche local a BSO

            bee.calculateDance(BSOAgent.problem);

            ACLMessage message = new ACLMessage(ACLMessage.INFORM);
            try {
                message.setContentObject(new Object[]{bee});
                AID receiver = new AID("BSO", AID.ISLOCALNAME);
                message.addReceiver(receiver);
                send(message);
                doDelete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
