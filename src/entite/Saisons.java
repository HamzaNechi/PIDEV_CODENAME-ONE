package entite;


public class Saisons {

    private int id;
    private int year;

    public Saisons() {
    }

    public Saisons(int id, int year) {
        this.id = id;
        this.year = year;
    }

    public Saisons(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}