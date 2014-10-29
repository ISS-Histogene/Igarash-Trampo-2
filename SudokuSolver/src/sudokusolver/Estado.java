/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Gustavo
 */
public class Estado {
    private Celula[][] matriz;
    private HashMap<ArrayList<Integer>, Celula> blankcells;
    private Estado estadoanterior;

    public Celula[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Celula[][] matriz) {
        this.matriz = matriz;
    }

    public HashMap<ArrayList<Integer>, Celula> getBlankcells() {
        return blankcells;
    }

    public void setBlankcells(HashMap<ArrayList<Integer>, Celula> blankcells) {
        this.blankcells = blankcells;
    }

    public Estado getEstadoanterior() {
        return estadoanterior;
    }

    public void setEstadoanterior(Estado estadoanterior) {
        this.estadoanterior = estadoanterior;
    }

    public Estado(Celula[][] matriz, HashMap<ArrayList<Integer>, Celula> blankcells, Estado estadoanterior) {
        this.matriz = matriz;
        this.blankcells = blankcells;
        this.estadoanterior = estadoanterior;
    }
    
    
    
}
