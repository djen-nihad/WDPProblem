package MetaHeurstique;

import Enchere.Participant;
import TestInstance.ReadFile;

import java.util.List;

public class WDPProblem {
    public  boolean[][] conflits;
    public double montant[];
    public double weights[];
    public WDPProblem(int size){
        this.conflits = new boolean[size][size];
        this.montant = new double[size];
        this.weights = new double[size];
        for ( int i = 0; i < size; i++){
            for ( int j = 0; j < size; j++){
                this.conflits[i][j] = Math.random() > 0.5 && i != j;
                this.conflits[j][i] = this.conflits[i][j];
                if ( this.conflits[i][j]){
                    this.weights[i]++;
                    this.weights[j]++;
                }
            }
            this.montant[i] = Math.random()* 1000;
        }
        for ( int i = 0; i < montant.length; i++)
            this.weights[i] = this.montant[i] / ( this.weights[i] + 1 );
    }
    public  WDPProblem(String path){
        List<Participant> participants = ReadFile.readfile(path);
        this.conflits = new boolean[participants.size()][participants.size()];
        this.montant = new double[participants.size()];
        this.weights = new double[participants.size()];
        for ( int i = 0; i < participants.size(); i++){
            this.montant[i] = participants.get(i).montant;
            for ( int j = i + 1 ; j < participants.size(); j++){
                  this.conflits[i][j] = participants.get(i).isConflit(participants.get(j));
                  this.conflits[j][i] = this.conflits[i][j];
                if ( this.conflits[i][j]){
                    this.weights[i]++;
                    this.weights[j]++;
                }
            }
        }
        for ( int i = 0; i < participants.size(); i++)
            this.weights[i] = this.montant[i] / ( this.weights[i] + 1 );

    }

    public WDPProblem(List<Participant> participants){
        this.conflits = new boolean[participants.size()][participants.size()];
        this.montant = new double[participants.size()];
        this.weights = new double[participants.size()];
        for ( int i = 0; i < participants.size(); i++){
            this.montant[i] = participants.get(i).montant;
            for ( int j = i + 1 ; j < participants.size(); j++){
                this.conflits[i][j] = participants.get(i).isConflit(participants.get(j));
                this.conflits[j][i] = this.conflits[i][j];
                if ( this.conflits[i][j]){
                    this.weights[i]++;
                    this.weights[j]++;
                }
            }
        }
        for ( int i = 0; i < participants.size(); i++)
            this.weights[i] = this.montant[i] / ( this.weights[i] + 1 );
    }
    public int getSizeWDP(){
        return montant.length;
    }

}
