package et3_projet.Classes;
import java.util.Map;

import et3_projet.Exceptions.ExceptionUtilisateur;

public class ClasseAdulte extends ClasseUtilisateur {


    ClasseAdulte(String nom, String prenom, int age, String username, String mdp) throws ExceptionUtilisateur {
        if (age < 18){
            throw new ExceptionUtilisateur("Vous devez etre majeur pour vous inscrire \n");
        }
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.username = username;
        this.mdp = mdp;

    }

    static ClasseUtilisateur nouvel_utilisateur(String nom, String prenom, int age, String username, String mdp) throws ExceptionUtilisateur {
        ClasseUtilisateur p = new ClasseAdulte(nom,prenom,age,username,mdp);
        liste_pseudo.put(p.username,p);
        return p ;
    }

    @Override
    protected void ajouter_amis(String username) throws ExceptionUtilisateur {
        if(username.equals(this.username)){
            throw new ExceptionUtilisateur("Impossible : Vous ne pouvez pas vous ajouter en ami \n");
        }
        boolean amis_ajoute = false;
        for(String pseudo : this.liste_amis){
            if(pseudo.equals(username)){
                throw new ExceptionUtilisateur(username + " est deja dans votre liste d'amis \n");
            }
        }
        //On parcours la liste des utilisateurs existants pour verifier que cet utilisateur existe
        for(Map.Entry<String, ClasseUtilisateur> mapentry : liste_pseudo.entrySet()){
            String key = mapentry.getKey();
            ClasseUtilisateur p = mapentry.getValue();
            if(key.equals(username)){
                if(p.age < 18){
                    throw new ExceptionUtilisateur("Impossible : La personne que vous essayez d'ajouter en ami est un enfant ! \n");
                }
                else {
                    this.liste_amis.add(key);
                    amis_ajoute = true;
                }
            }
        }
        //Si il n'existe pas, on renvoie une exception
        if(!amis_ajoute){
            throw new ExceptionUtilisateur("Cet utilisateur n'existe pas");
        }
    }

}

