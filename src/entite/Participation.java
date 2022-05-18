/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author ahmed
 */
public class Participation {
    private int id;
    private int idPilote ;
    private int idEquipe;
    private int idCourse;
    private int grid;
    private int position;
    private int points;
    private int idQualifying;

    public Participation(int id, int idPilote, int idEquipe, int idCourse, int grid, int position, int points, int idQualifying) {
        this.id = id;
        this.idPilote = idPilote;
        this.idEquipe = idEquipe;
        this.idCourse = idCourse;
        this.grid = grid;
        this.position = position;
        this.points = points;
        this.idQualifying = idQualifying;
    }

    public Participation() {
    }

    public Participation(int idPilote, int idEquipe, int idCourse, int grid, int position, int points,int idQualifying) {
        this.idPilote = idPilote;
        this.idEquipe = idEquipe;
        this.idCourse = idCourse;
        this.grid = grid;
        this.position = position;
        this.points = points;
        this.idQualifying = idQualifying;
    }
    
    public Participation(int grid, int position, int points) {

        this.grid = grid;
        this.position = position;
        this.points = points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPilote(int idPilote) {
        this.idPilote = idPilote;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public void setGrid(int grid) {
        this.grid = grid;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setIdQualifying(int idQualifying) {
        this.idQualifying = idQualifying;
    }

    
    public int getId() {
        return id;
    }

    public int getIdPilote() {
        return idPilote;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public int getGrid() {
        return grid;
    }

    public int getPosition() {
        return position;
    }

    public int getPoints() {
        return points;
    }

    public int getIdQualifying() {
        return idQualifying;
    }
    
    

}
