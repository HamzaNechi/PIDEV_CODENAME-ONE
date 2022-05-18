
package entite;

import utils.Statics;

public class ClassementPilotes implements Comparable<ClassementPilotes> {

    private int id;
    private Saisons saisons;
    private Pilotes pilotes;
    private int pointsTotal;
    private int position;

    public ClassementPilotes() {
    }

    public ClassementPilotes(int id, Saisons saisons, Pilotes pilotes, int pointsTotal, int position) {
        this.id = id;
        this.saisons = saisons;
        this.pilotes = pilotes;
        this.pointsTotal = pointsTotal;
        this.position = position;
    }

    public ClassementPilotes(Saisons saisons, Pilotes pilotes, int pointsTotal, int position) {
        this.saisons = saisons;
        this.pilotes = pilotes;
        this.pointsTotal = pointsTotal;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Saisons getSaisons() {
        return saisons;
    }

    public void setSaisons(Saisons saisons) {
        this.saisons = saisons;
    }

    public Pilotes getPilotes() {
        return pilotes;
    }

    public void setPilotes(Pilotes pilotes) {
        this.pilotes = pilotes;
    }

    public int getPointsTotal() {
        return pointsTotal;
    }

    public void setPointsTotal(int pointsTotal) {
        this.pointsTotal = pointsTotal;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    @Override
    public int compareTo(ClassementPilotes classementPilotes) {
        switch (Statics.compareVar) {
            case "Saisons":
                return Integer.compare(this.getSaisons().getYear(), classementPilotes.getSaisons().getYear());
            case "Pilotes":
                return Integer.compare(this.getPilotes().getNumero(), classementPilotes.getPilotes().getNumero());
            case "PointsTotal":
                return Integer.compare(this.getPointsTotal(), classementPilotes.getPointsTotal());
            case "Position":
                return Integer.compare(this.getPosition(), classementPilotes.getPosition());

            default:
                return 0;
        }
    }

}