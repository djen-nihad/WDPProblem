package MetaHeurstique;

import java.io.Serializable;
import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.List;

public class Bee implements Serializable {
     public boolean[] solution;
     public double dance;
    public Bee(boolean[] solution , WDPProblem problem){
        this.solution = new boolean[solution.length];
        System.arraycopy(solution, 0, this.solution, 0, solution.length);
        this.calculateDance(problem);
    }
    public Bee(int size){
        this.solution = new boolean[size];
        this.dance = 0;
    }
    public Bee(Bee bee){
        this.solution = new boolean[bee.solution.length];
        System.arraycopy(bee.solution, 0, this.solution, 0, this.solution.length);
        this.dance = bee.dance;
    }
    public static Bee BeeInitRandom( WDPProblem problem){
        boolean[] solution = new boolean[problem.getSizeWDP()];
        List<Integer> participants = new ArrayList<>();
        int index ;
        for ( int i = 0; i < problem.getSizeWDP(); i++)
            participants.add(i);
        while (!participants.isEmpty()){
            index = (int) ( Math.random() * participants.size());
            index = participants.remove(index);
            solution[index] = true;
            for ( int i = 0; i < problem.getSizeWDP(); i++)
                if ( solution[i] && i != index && problem.conflits[index][i])
                    participants.remove(Integer.valueOf(i));
        }
//        for ( int i = 0; i < problem.getSizeWDP(); i++ )
//            solution[i] = Math.random() > 0.5;
        return new Bee(solution , problem);

    }
    public static Bee BeeInit(WDPProblem problem){
         // Initilaiser weights
         boolean[] solutionInit = new boolean[problem.getSizeWDP()];
         List<Integer> availableNodes = new ArrayList<>();
         for ( int i = 0; i < problem.getSizeWDP(); i++)
             availableNodes.add(i);
         int nouedSelectionne;
         while ( ! availableNodes.isEmpty() ){
             // Recherche le noued qui a le meilleur poids
             nouedSelectionne =  availableNodes.get(0);
             for ( int noued : availableNodes )
                 if ( problem.weights[noued] > problem.weights[nouedSelectionne] )
                     nouedSelectionne = noued;

             solutionInit[nouedSelectionne] = true;
             availableNodes.remove(Integer.valueOf(nouedSelectionne));
             for ( int i = 0; i < problem.getSizeWDP(); i++)
                 if ( problem.conflits[nouedSelectionne][i])
                     availableNodes.remove(Integer.valueOf(i));
         }
         Bee bee = new Bee(solutionInit , problem);
         return  bee;
     }
     // TODO: traiter le cas ou on a pas arrive a nombreBees
    public  static  List<Bee> searchArea(Bee sref , int flip , int nombreBees , WDPProblem problem){
        int step = 0;
        Bee bee;
        List<Bee> bees = new ArrayList<>();
       while ( bees.size() < nombreBees && step < flip){
           bee = new Bee(sref);
           for ( int p = 0; flip * p + step < sref.solution.length; p++ ){
               bee.solution[flip * p + step] = ! bee.solution[flip * p + step];
               if ( bee.solution[flip * p + step] ){
                   for ( int k = 0; k < problem.getSizeWDP(); k++)
                       if ( k != (flip * p + step) && problem.conflits[flip * p + step][k]) bee.solution[k] = false;
                   break;
               }
           }
           bee.calculateDance(problem);
           bees.add(bee);
           step++;
       }
        while ( bees.size() < nombreBees ) bees.add(new Bee(problem.getSizeWDP()));
        return  bees;
    }
    public boolean isValide(WDPProblem problem){
        for ( int i = 0; i < problem.getSizeWDP() - 1; i++)
            if ( this.solution[i]) {
                for ( int j = i + 1; j < problem.getSizeWDP(); j++)
                    if ( this.solution[j] && problem.conflits[i][j]) return false;
            }
    //    this.dance = 0;
    //    this.calculateDance(problem);
    //    System.out.println(" VOUS ETES SUR ?????" + this.dance);
        return  true;
    }
    public int calculateDiversity(boolean[] solution){
        int distance = 0;
        for (int i = 0; i < solution.length; i++)
            if ( this.solution[i] != solution[i])
                distance++;
        return  distance;
    }
    public void calculateDance(WDPProblem problem){
        this.dance = 0;
        for ( int i = 0; i < problem.getSizeWDP() ; i++) {
            if ( solution[i] ) {
                for ( int j = 0; j < problem.getSizeWDP(); j++)
                    if (solution[j] && problem.conflits[i][j]){
                        this.dance = -1;
                        return;
                    }
                this.dance = this.dance + problem.montant[i];
            }

        }
    }



}
