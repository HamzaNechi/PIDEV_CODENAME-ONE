package entite;

public class Pilotes {

    private int id;
    private int numero;

    public Pilotes() {
    }

    public Pilotes(int id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    public Pilotes(int numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


}