package et3_projet.Classes;
import et3_projet.Exceptions.ExceptionUtilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class ClasseUtilisateur {
    protected static Map<String, ClasseUtilisateur> liste_pseudo = new HashMap<>();
    protected List<String> liste_amis = new ArrayList<>();

    protected Map<String, List<ClasseChanson>> toutes_playlist = new HashMap<>();
    protected Paire<String, List<ClasseChanson>> playlist = new Paire<>();

    protected List<String[]> notification = new ArrayList<>();

    protected String nom;
    protected String prenom;
    protected int age;
    protected String username;
    protected String mdp;



    public ClasseUtilisateur() {
    }

    protected abstract void ajouter_amis(String username) throws ExceptionUtilisateur;

    protected void retirer_amis(String username) throws ExceptionUtilisateur {
        boolean amis_retire = false;
        for(String pseudo : this.liste_amis){
            if(pseudo.equals(username)){
                this.liste_amis.remove(pseudo);
                amis_retire = true;
                break;
            }
        }
        if(!amis_retire){
            throw new ExceptionUtilisateur("Cet utilisateur ne figure pas sur votre liste d'amis");
        }
    }



    @Override
    public String toString() {
        return ("Nom : " + this.nom +"\nPrenom : " +this.prenom +"\nAge : " + this.age +"\nPseudo : " + this.username+"\nMot de passe : "+this.mdp);
    }

    void recommander_chanson(ClasseUtilisateur user, ClasseChanson chanson){
        String[] infos = new String[3];
        infos[0] = this.username;
        infos[1] = chanson.getArtiste();
        infos[2] = chanson.getTitre();

        user.notification.add(infos);

    }


    void creer_playlist(String nom) throws ExceptionUtilisateur{
        if(this.toutes_playlist.isEmpty()) {
            playlist = new Paire<>(nom, new ArrayList<>());
            this.toutes_playlist.put(playlist.getPremier(), playlist.getSecond());
        }
        else{
            for (int i = toutes_playlist.entrySet().size() - 1; i >= 0; i--) {
                Map.Entry<String, List<ClasseChanson>> mapentry = (Map.Entry<String, List<ClasseChanson>>) toutes_playlist.entrySet().toArray()[i];
                if (mapentry.getKey().equals(nom)) {
                    throw new ExceptionUtilisateur("Cette playliste existe deja !");
                }
            }
            playlist = new Paire<>(nom, new ArrayList<>());
            this.toutes_playlist.put(playlist.getPremier(), playlist.getSecond());


        }
    }

    void supprimer_playlist(String nom) {
        for (int i = toutes_playlist.entrySet().size() - 1; i >= 0; i--) {
            Map.Entry<String, List<ClasseChanson>> mapentry = (Map.Entry<String, List<ClasseChanson>>) toutes_playlist.entrySet().toArray()[i];
            if (mapentry.getKey().equals(nom)) {
                toutes_playlist.remove(mapentry.getKey());
            }
        }
    }

    void ajouter_playlist(String nom, ClasseChanson chanson){
        for (int i = toutes_playlist.entrySet().size() - 1; i >= 0; i--) {
            Map.Entry<String, List<ClasseChanson>> mapentry = (Map.Entry<String, List<ClasseChanson>>) toutes_playlist.entrySet().toArray()[i];
            if (mapentry.getKey().equals(nom)) {
                List<ClasseChanson> liste;
                liste = mapentry.getValue();
                liste.add(chanson);
                toutes_playlist.put(nom, liste);
            }
        }
    }
    protected void inscrire_enfant(String nom, String prenom, int age, String username, String mdp) throws ExceptionUtilisateur {
        if (age > 18){
            throw new ExceptionUtilisateur("La personne que vous essayez d'inscrire est un adulte ! \n");
        }
        ClasseEnfant enfant = new ClasseEnfant(nom,prenom,age, username, mdp);
        this.liste_amis.add(username);
        enfant.liste_amis.add(this.username);
        liste_pseudo.put(enfant.username,enfant);
    }
}
