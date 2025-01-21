package et3_projet.Classes;

public class Paire<T,U>{
    private T premier;
    private U second;

    public Paire(T premier, U second) {
        this.premier = premier;
        this.second = second;
    }

    public Paire(){}

    public T getPremier() {
        return premier;
    }

    public void setPremier(T premier) {
        this.premier = premier;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

}
