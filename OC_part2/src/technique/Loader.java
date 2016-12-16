package technique;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import probleme.Probleme;

/**
 * Classe ayant pour unique but de lire des fichiers d'instance,
 * pour générer une liste d'instance.
 * @author caron
 *
 */
public class Loader {
	
	
	public static int[][] loadMatrix(String pathname,int size) throws FileNotFoundException {
		File file = new File(pathname) ;
		if (file.exists()) {
			System.out.println("le fichier existe");
		}
		if (file.canRead()) {
			System.out.println("on peut le lire");
		}
		Scanner scanner = new Scanner(file);
		int[][] matrix = new int[size][size] ;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = -1 ;
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix.length; j++) {
				int scanned = scanner.nextInt() ;
				matrix[i][j] = scanned ;
				matrix[j][i] = scanned ;
			}
		}
		
		return matrix;
	}
	
	public static void printMatrix(int[][] matrix){
		String chaine = "" ;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (j == 0) {
					chaine += "\n" ;
				}
				
				chaine += matrix[i][j]+" ";
			}
		}
		System.out.println(chaine);
	}
	
	public static void main(String[] args) {
		Loader loader = new Loader() ;
		try {
			int[][] matrix = loader.loadMatrix("instances/randomA100.tsp", 100);
			loader.printMatrix(matrix);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
