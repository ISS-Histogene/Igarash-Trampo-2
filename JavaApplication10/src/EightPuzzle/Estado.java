/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EightPuzzle;

/**
 *
 * @author Gustavo
 */
public class Estado {
    private int[][] matriz = new int[3][3];
    private int custo;
    private Estado pai;
    private int valor;

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Estado getPai() {
        return pai;
    }

    public void setPai(Estado pai) {
        this.pai = pai;
    }
    
    public Estado(int[][] matriznova, int custo, Estado pai, int valor) {
        this.matriz = matriznova;
        this.custo = custo;
        this.pai = pai;
        this.valor = valor;
    }

    public int getCusto() {
        return custo;
    }

    public int[][] getMatriz() {
        return matriz;
    }
    
}