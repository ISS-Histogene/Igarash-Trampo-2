/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokusolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo
 */
public class SudokuSolver {

    private static Celula[][] sudoku;
    private static HashMap<ArrayList<Integer>, Celula> blankcells;


    public Celula[][] getSudoku() {
        return sudoku;
    }
    
    public static void SudokuSolver(String[][] matriz, SudokuInterface inter){
        sudoku = new Celula[9][9];
        blankcells = new HashMap();
        jogo(matriz);

        Estado estadox = new Estado(sudoku, blankcells, null);
        resolvedor(estadox, 1);
    }
  
    public static void jogo(String[][] matriz){
        for(int a=0; a<=8;a++){
            for(int b=0; b<=8;b++){
                int valor = Integer.parseInt(String.valueOf(a)+String.valueOf(b));
                if(matriz[a][b].equals("")){
                    Celula celula = new Celula(0, a, b);
                    sudoku[a][b] = celula;
                    ArrayList vazio = new ArrayList();
                    vazio.add(a);
                    vazio.add(b);
                    blankcells.put(vazio, celula);
                }
                else{
                    Celula celula = new Celula(Integer.parseInt(matriz[a][b]), a, b);
                    sudoku[a][b] = celula;
                }
            }
        }
    }
    
    public static void resolvedor(Estado testarestado, int valoratual){

        if(testarestado.getBlankcells().isEmpty()){
            JOptionPane.showMessageDialog(null, "Resolvido");
        }
        else if(valoratual<=9){
            System.out.println("restantes: "+testarestado.getBlankcells().size());

            Estado estadonovo = copiarEstado(testarestado);
            ArrayList chavecelula = estadonovo.getBlankcells().keySet().iterator().next();
            Celula celulatestar = estadonovo.getBlankcells().get(chavecelula);
            celulatestar.setNumero(valoratual);
            Celula celulat = estadonovo.getMatriz()[celulatestar.getLinha()][celulatestar.getColuna()];
            celulat.setNumero(valoratual);
            estadonovo.getBlankcells().remove(chavecelula);
            if(!(conflitos(estadonovo.getMatriz()))){
                resolvedor(estadonovo, 1);
            }
            else{
                resolvedor(testarestado, valoratual++);
            }

        }
        else{
            resolvedor(testarestado.getEstadoanterior(), valoratual++);
        }

    }

        
        
  
    
    
    public static Estado copiarEstado(Estado estado){
        HashMap<ArrayList<Integer>, Celula> blankcellestado = estado.getBlankcells();
        Celula[][] celulasestado = estado.getMatriz();
        
        HashMap<ArrayList<Integer>, Celula> blankcellcopiado = new HashMap();
        Celula[][] celulascopiado = new Celula[9][9];
        
        for(ArrayList<Integer> chave : blankcellestado.keySet()){
            blankcellcopiado.put(chave, blankcellestado.get(chave));
        }
        
        for(int j=0; j<=8;j++){
            for(int k=0; k<=8;k++){
                celulascopiado[j][k] = celulasestado[j][k];
            }
        }
        return new Estado(celulascopiado, blankcellcopiado, estado);
        
    }
    
    
    
    
    
    public static boolean conflitos(Celula[][] sudoku){
        for(int g=0;g<=8;g++){
            for(int h=0;h<=8;h++){
                if(sudoku[g][h].getNumero()!=0){
                    if((conflitoLinha(g, h, sudoku[g][h].getNumero(), sudoku)) || (conflitoColuna(g, h, sudoku[g][h].getNumero(), sudoku)) || (conflitoBox(g, h, sudoku[g][h].getNumero(), sudoku))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean conflitoLinha(int posicaox, int posicaoy, int valorteste, Celula[][] sudoku){
        int linhateste = posicaox;
        for(int i=0;i<=8;i++){
            if(i==posicaoy){
                continue;
            }
            Celula celulatestar = sudoku[linhateste][i];
            if(celulatestar.getNumero()==valorteste){
                return true;
            }
        }
        return false;
    }
    public static boolean conflitoColuna(int posicaox, int posicaoy, int valorteste, Celula[][] sudoku){
        int colunateste = posicaoy;
        for(int i=0;i<=8;i++){
            if(i==posicaox){
                continue;
            }
            Celula celulatestar = sudoku[i][colunateste];
            if(celulatestar.getNumero()==valorteste){
                return true;
            }
        }
        return false;
    }
    
    public static boolean conflitoBox(int posicaox, int posicaoy, int valorteste, Celula[][] sudoku){
        int linha = posicaox;
        int coluna = posicaoy;
        if((linha==0) || (linha==3) || (linha==6)){
            if((coluna==0) || (coluna==3) || (coluna==6)){
                for(int x=0;x<=2;x++){
                    for(int y=0;y<=2;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
            else if((coluna==1) || (coluna==4) || (coluna==7)){
                for(int x=0;x<=2;x++){
                    for(int y=-1;y<=1;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
            else if((coluna==2) || (coluna==5) || (coluna==8)){
                for(int x=0;x<=2;x++){
                    for(int y=-2;y<=0;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
        }
        else if((linha==1) || (linha==4) || (linha==7)){
            if((coluna==0) || (coluna==3) || (coluna==6)){
                for(int x=-1;x<=1;x++){
                    for(int y=0;y<=2;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
            else if((coluna==1) || (coluna==4) || (coluna==7)){
                for(int x=-1;x<=1;x++){
                    for(int y=-1;y<=1;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
            else if((coluna==2) || (coluna==5) || (coluna==8)){
                for(int x=-1;x<=1;x++){
                    for(int y=-2;y<=0;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
        }
        else if((linha==2) || (linha==5) || (linha==8)){
            if((coluna==0) || (coluna==3) || (coluna==6)){
                for(int x=-2;x<=0;x++){
                    for(int y=0;y<=2;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
            else if((coluna==1) || (coluna==4) || (coluna==7)){
                for(int x=-2;x<=0;x++){
                    for(int y=-1;y<=1;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
            else if((coluna==2) || (coluna==5) || (coluna==8)){
                for(int x=-2;x<=0;x++){
                    for(int y=-2;y<=0;y++){
                        Celula testar = sudoku[linha+x][coluna+y];
                        if(((linha+x)==posicaox) && ((coluna+y)==posicaoy)){
                            continue;
                        }
                        if(testar.getNumero()==valorteste){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    
}
