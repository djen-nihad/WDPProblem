package Enchere;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    private  static  int compteur = 0;
    public int idParticipant;
    public String   nomParticipant;
    public List<Integer> listObject;
    public double montant;
    public Participant() {
        this.idParticipant = compteur;
        compteur++;
        this.nomParticipant = "Participant" + this.idParticipant;
        this.listObject = new ArrayList<>();
    }

    public Participant(String nomParticipant, double montant, List<Integer> seclectionner) {
        this.nomParticipant = nomParticipant;
        this.montant = montant;
        this.listObject = seclectionner;
    }

    public boolean isConflit( Participant participant){
       for ( Integer o : this.listObject )
           if ( this.isObjectInList(participant.listObject, o)) return  true;
       return  false;
    }
    private  boolean  isObjectInList(List<Integer> listObject , int object){
        for ( Integer o : listObject)
            if ( o == object ) return true;
        return  false;
    }
    public String toString(){
        return this.idParticipant + ":  " + this.nomParticipant + " : " + this.listObject.size() + " : " + this.montant;
    }
}
