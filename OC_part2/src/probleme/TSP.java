package probleme;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import technique.Loader;

/**
 * Classe TSP multi-objectifs 
 * @author caron
 *
 */
public class TSP implements Probleme{
	
	protected int nbVilles ;
	protected int nbObjectifs ;
	protected int[][][] lesObjectifs ; 
	
	public TSP(String[] lesPathname, int size) {
		this.nbVilles = size ;
		this.nbObjectifs = lesPathname.length ;
		this.lesObjectifs = new int[nbObjectifs][nbVilles][nbVilles] ;
		for (int i = 0; i < this.nbObjectifs; i++) {
			try {
				this.lesObjectifs[i] = Loader.loadMatrix(lesPathname[i], size) ;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	@Override
	/**
	 * somme les résultats pour chaque objectifs
	 */
	public int[] eval(ArrayList<Integer> permutation) {
		int[] lesEvals = new int[nbObjectifs] ;
		for (int i = 0; i < lesEvals.length; i++) {
			lesEvals[i] = 0 ;
		}
		for (int i = 1; i< this.nbVilles; i++) {
			int villePrec = permutation.get(i-1) ;
			int villeActuelle = permutation.get(i) ;
			for (int obj = 0; obj < lesEvals.length; obj++) {
				lesEvals[obj] += this.lesObjectifs[obj][villePrec][villeActuelle] ;
			}
		}
		for (int obj = 0; obj < lesEvals.length; obj++) {
			lesEvals[obj] += this.lesObjectifs[obj][permutation.get(0)][permutation.get(nbVilles-1)] ; 
		}
		return lesEvals;
	}


	@Override
	public ArrayList<Integer> permutationAleatoire() {
		ArrayList<Integer> permutation = new ArrayList<Integer>() ;
		for (int i = 0; i < this.nbVilles; i++) {
			permutation.add(i) ;
		}
		Collections.shuffle(permutation);
		return permutation;
	}


	/**
	 * Est ce que solution1 domine la solution2 ?
	 * cad un score inférieur ou égale sur tous les objectifs et
	 * il existe un score ou score1 strictement inférieur que score2
	 */
	@Override
	public boolean dominate(ArrayList<Integer> solution1,
			ArrayList<Integer> solution2) {
		int[] score1 = this.eval(solution1);
		int[] score2 = this.eval(solution2);
		boolean inforegale = true ;
		boolean infStricte = false;
		for (int i = 0; i < score2.length; i++) {
			if(score1[i]<score2[i]) {
				infStricte = true ;
			}
			if(!(score1[i]<=score2[i])){
				inforegale = false ;
			}
		}
		return inforegale && infStricte;
	}

	
	
}
