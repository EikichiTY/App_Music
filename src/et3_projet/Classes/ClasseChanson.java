package et3_projet.Classes;

public class ClasseChanson {
    private String artiste;
    private String titre;
    private String lien;
    private String paroles;

    public ClasseChanson(String artiste, String titre, String lien, String paroles) {
        this.artiste = artiste;
        this.titre = titre;
        this.lien = lien;
        this.paroles = paroles;
    }
    public ClasseChanson(String artiste, String titre, String paroles) {
        this.artiste = artiste;
        this.titre = titre;
        this.paroles = paroles;
    }


    public ClasseChanson(){    }


    void jouerChanson(){
        String paroles = "Debut de la chanson : \n\n";
        paroles += this.getParoles();
        paroles += "\nFin";
        new PageLecture(paroles,this.getTitre());
    }

    @Override
    public String toString(){
        return ("Artiste : "+this.artiste+"\nTitre : "+this.titre+"\nLien : "+this.lien+"\nParoles : "+ this.paroles);
    }

    //getters
    String getArtiste(){return this.artiste;}
    String getTitre(){return this.titre;}
    String getParoles(){return this.paroles;}
}