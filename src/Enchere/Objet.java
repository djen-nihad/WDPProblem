package Enchere;

import java.util.List;

public class Objet {
    public  static int compteur = 0;
    public int id;
    public String nomObject;
    public String description;

    public Objet( String nomObject, String desciption){
        this.nomObject = nomObject;
        this.description = desciption;
        this.id = compteur;
        compteur++;
    }
    public String toString(){
        return this.id + ": " + this.nomObject;
    }
    public String nomObject(int id , List<Objet> list){
        for ( Objet o : list)
            if ( o.id == id ) return  o.nomObject;
        return " ";
    }

    public Objet(int id) {
        this.id = id;
    }

    public boolean objetExiste(Objet[] listObjets){
        for ( Objet o : listObjets)
            if (this.id == o.id ) return true;
        return false;
    }

    public  static  boolean isEnConflitLists (Objet[] list1 , Objet[] list2){
        for ( Objet o1 : list1)
            if ( o1.objetExiste(list2) ) return  true;
        return false;
    }


}
