package et3_projet.Classes;
import et3_projet.Exceptions.ExceptionUtilisateur;

import java.util.Map;


public class ClasseEnfant extends ClasseUtilisateur {
      ClasseEnfant(String nom, String prenom, int age, String username, String mdp){
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.username = username;
        this.mdp = mdp;


      }

    @Override
    protected void ajouter_amis(String username) throws ExceptionUtilisateur {
          boolean amis_ajoute = false;
          for(String pseudo : this.liste_amis){
            if(pseudo.equals(username)){
                throw new ExceptionUtilisateur(username + " est deja dans votre liste d'amis \n");
            }
        }
        for(Map.Entry<String, ClasseUtilisateur> mapentry : liste_pseudo.entrySet()){
            String key = mapentry.getKey();
            ClasseUtilisateur p = mapentry.getValue();
            if(key.equals(username)){
                if(p.age >18){
                    throw new ExceptionUtilisateur("Imporssible : La personne que vous essayez d'ajouter est un adulte ! \n");
                }
                else {
                    this.liste_amis.add(key);
                    System.out.println("Ami ajouté");
                    amis_ajoute = true;
                }

            }
        }
        if(!amis_ajoute){
            throw new ExceptionUtilisateur("Cet utilisateur n'existe pas");
        }
    }



}
