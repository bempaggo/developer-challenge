/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author allen
 */
public class Edge {

    private final String sense;
    private final Cell cell;

    public Edge(String sense, Cell cell) {
        this.sense = sense;
        this.cell = cell;
    }

    public String getSense() {
        return this.sense;
    }
    
    public Cell getCell(){
        return this.cell;
    }

    @Override
    public boolean equals(Object obj) {
        return this.sense.equals(((Edge) obj).getSense());
    }

}
