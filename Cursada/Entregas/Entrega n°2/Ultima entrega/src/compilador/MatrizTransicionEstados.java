package compilador;

import accionesSemanticas.AS01;
import accionesSemanticas.AS02;
import accionesSemanticas.AS03;
import accionesSemanticas.AS06;
import accionesSemanticas.AS07;
import accionesSemanticas.AS08;
import accionesSemanticas.AS09;
import accionesSemanticas.AS10;
import accionesSemanticas.AS04;
import accionesSemanticas.AS05;
import accionesSemanticas.ASError;
import accionesSemanticas.ASFinal;
import accionesSemanticas.ASDescarta;
import accionesSemanticas.AccionSemantica;
import complementos.Pair;

public class MatrizTransicionEstados {
	
	private final int FINAL = -1;
	private final int ERROR = -2;
	
	private int rows = 16;
	private int columns = 21;
	private Pair<Integer, AccionSemantica> [][] matrix;
	
	public MatrizTransicionEstados()
	{
		//inicializo la matriz..
		matrix = new Pair[rows][columns];
		this.cargaMatriz();
	}
	    
    private void cargaMatriz() 
    {
    	matrix [0][0] = new Pair<Integer, AccionSemantica>(15, new AS01());
		matrix [0][1] = new Pair<Integer, AccionSemantica>(10, new AS01());
		matrix [0][2] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][3] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][4] = new Pair<Integer, AccionSemantica>(4, new AS01());
		matrix [0][5] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][6] = new Pair<Integer, AccionSemantica>(1, new AS06());
		matrix [0][7] = new Pair<Integer, AccionSemantica>(2, new AS07());
		matrix [0][8] = new Pair<Integer, AccionSemantica>(3, new AS06());
		matrix [0][9] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][10] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][11] = new Pair<Integer, AccionSemantica>(11, new AS01());
		matrix [0][12] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][13] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][14] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][15] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [0][16] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [0][17] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [0][18] = new Pair<Integer, AccionSemantica>(0, new ASDescarta());
		matrix [0][19] = new Pair<Integer, AccionSemantica>(0, new ASDescarta());
		matrix [0][20] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		
		matrix [1][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][1] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][6] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [1][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [1][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		
		matrix [2][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][1] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][6] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [2][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][8] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [2][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [2][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		
		matrix [3][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][1] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][6] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [3][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [3][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		
		matrix [4][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][1] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][4] = new Pair<Integer, AccionSemantica>(5, new AS08());
		matrix [4][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][6] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		matrix [4][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS09());
		
		matrix [5][0] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][1] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][2] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][3] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][4] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][5] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][6] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][7] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][8] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][9] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][10] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][11] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][12] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][13] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][14] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][15] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][16] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][17] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][18] = new Pair<Integer, AccionSemantica>(0, new ASDescarta());
		matrix [5][19] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		matrix [5][20] = new Pair<Integer, AccionSemantica>(5, new ASDescarta());
		
		matrix [6][0] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][1] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][2] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][3] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][4] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][5] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][6] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][7] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][8] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][9] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][10] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][11] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][12] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][13] = new Pair<Integer, AccionSemantica>(7, new AS01());
		matrix [6][14] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][15] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][16] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][17] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [6][18] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [6][19] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [6][20] = new Pair<Integer, AccionSemantica>(6, new AS01());
		
		matrix [7][0] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][1] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][2] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][3] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][4] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][5] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][6] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][7] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][8] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][9] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][10] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][11] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][12] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][13] = new Pair<Integer, AccionSemantica>(8, new AS01());
		matrix [7][14] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][15] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][16] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][17] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [7][18] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [7][19] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [7][20] = new Pair<Integer, AccionSemantica>(6, new AS01());
		
		matrix [8][0] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][1] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][2] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][3] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][4] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][5] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][6] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][7] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][8] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][9] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][10] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][11] = new Pair<Integer, AccionSemantica>(6, new AS01());;
		matrix [8][12] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][13] = new Pair<Integer, AccionSemantica>(9, new AS01());
		matrix [8][14] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][15] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][16] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][17] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [8][18] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][19] = new Pair<Integer, AccionSemantica>(6, new AS01());
		matrix [8][20] = new Pair<Integer, AccionSemantica>(6, new AS01());
		
		matrix [9][0] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][1] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][2] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][3] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][4] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][5] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][6] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][7] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][8] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][9] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][10] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][11] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][12] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][13] = new Pair<Integer, AccionSemantica>(8, new AS01());
		matrix [9][14] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][15] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][16] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][17] = new Pair<Integer, AccionSemantica>(FINAL, new ASFinal());
		matrix [9][18] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][19] = new Pair<Integer, AccionSemantica>(6, new AS10());
		matrix [9][20] = new Pair<Integer, AccionSemantica>(6, new AS10());
		
		matrix [10][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][1] = new Pair<Integer, AccionSemantica>(10, new AS01());
		matrix [10][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][6] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][11] = new Pair<Integer, AccionSemantica>(11, new AS01());
		matrix [10][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		matrix [10][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS04());
		
		matrix [11][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][1] = new Pair<Integer, AccionSemantica>(11, new AS01());
		matrix [11][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][6] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][16] = new Pair<Integer, AccionSemantica>(12, new AS01());
		matrix [11][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [11][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		
		matrix [12][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][1] = new Pair<Integer, AccionSemantica>(14, new AS01());
		matrix [12][2] = new Pair<Integer, AccionSemantica>(13, new AS01());
		matrix [12][3] = new Pair<Integer, AccionSemantica>(13, new AS01());
		matrix [12][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][6] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		matrix [12][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS02());
		
		matrix [13][0] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][1] = new Pair<Integer, AccionSemantica>(14, new AS01());
		matrix [13][2] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][3] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][4] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][5] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][6] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][7] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][8] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][9] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][10] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][11] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][12] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][13] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][14] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][15] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][16] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][17] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][18] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][19] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		matrix [13][20] = new Pair<Integer, AccionSemantica>(ERROR, new ASError());
		
		matrix [14][0] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][1] = new Pair<Integer, AccionSemantica>(14, new AS01());
		matrix [14][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][6] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		matrix [14][20] = new Pair<Integer, AccionSemantica>(FINAL, new AS05());
		
		matrix [15][0] = new Pair<Integer, AccionSemantica>(15, new AS01());
		matrix [15][1] = new Pair<Integer, AccionSemantica>(15, new AS01());
		matrix [15][2] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][3] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][4] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][5] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][6] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][7] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][8] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][9] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][10] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][11] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][12] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][13] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][14] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][15] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][16] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][17] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][18] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][19] = new Pair<Integer, AccionSemantica>(FINAL, new AS03());
		matrix [15][20] = new Pair<Integer, AccionSemantica>(15, new AS01());
	}

	public Pair<Integer, AccionSemantica> getPair(int f, int c){
    	return this.matrix [f][c];
    }
	
    
    public void viewMatrix(){
    	System.out.println("------------------MATRIZ-------------");
    	for (int i = 0; i < rows; i++) 
    	{
    		System.out.print(i+" --> ");
			for (int j = 0; j < columns; j++)
			{
				System.out.print("E-" + matrix[i][j].getFirst() + "--" + matrix[i][j].getSecond().toString() + " | ");					
			}
			System.out.println("\n");
		}
    }

	public int getColumn(char loQueLee, String lexema) 
	{
		if (!lexema.isEmpty() && lexema.charAt(lexema.length()-1) == ',' && (loQueLee == 'E' || loQueLee == 'e'))
			return 16;
		else if (!lexema.isEmpty() && (lexema.charAt(lexema.length()-1) >= '0' && lexema.charAt(lexema.length()-1) <= '9') && (loQueLee == 'E' || loQueLee == 'e'))
			return 16;
		else if ((loQueLee >= 'a' && loQueLee <= 'z') || (loQueLee >= 'A' && loQueLee <= 'Z') 
				|| loQueLee == 'á' || loQueLee == 'é' || loQueLee == 'í' 
				|| loQueLee == 'ó' || loQueLee == 'ú' || loQueLee == 'Á'
				|| loQueLee == 'É' || loQueLee == 'Í' || loQueLee == 'Ó'
				|| loQueLee == 'Ú') 
			return 0;
		else if (loQueLee >= '0' && loQueLee <= '9')
			return 1;
		else if (loQueLee == '+')
			return 2;
		else if (loQueLee == '-')
			return 3;
		else if (loQueLee == '*')
			return 4;
		else if (loQueLee == '/')
			return 5;
		else if (loQueLee == '=')
			return 6;
		else if (loQueLee == '<')
			return 7;
		else if (loQueLee == '>')
			return 8;
		else if (loQueLee == '(')
			return 9;
		else if (loQueLee == ')')
			return 10;
		else if (loQueLee == ',')
			return 11;
		else if (loQueLee == ':')
			return 12;
		else if (loQueLee == '.')
			return 13;
		else if (loQueLee == '{') 
			return 14;
		else if (loQueLee == '}')
			return 15;
		else if (loQueLee == 'E' || loQueLee == 'e')
			return 16;
		else if (loQueLee == '"')
			return 17;
		else if (loQueLee == '\n')
			return 18;
		else if (loQueLee == ' ' || loQueLee == '\t')
			return 19;
		else if (loQueLee == '_')
			return 20;
		//si no entro a ninguno.. retorna un -2 que seria un error.
		return -2; 
	}

}
