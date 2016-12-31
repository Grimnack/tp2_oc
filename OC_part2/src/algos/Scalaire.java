package algos;

import java.util.ArrayList;

import probleme.Probleme;

public class Scalaire {
	protected Probleme probleme ;
	protected int[] lesPoids ;
	protected Voisinage voisinage ;
	protected ArrayList<Integer> solutionActuelle ;
	protected int evalActuelle ;
	protected boolean first ;
	
	public Scalaire(Probleme p,Voisinage voisinage, int[] lesPoids, ArrayList<Integer> solutionInitiale,boolean first) {
		this.probleme = p ;
		this.lesPoids = lesPoids ;
		this.voisinage = voisinage ;
		this.solutionActuelle = solutionInitiale ;
		this.voisinage.init(this.solutionActuelle) ;
		int[] evalTmp = this.probleme.eval(solutionInitiale);
		this.evalActuelle = this.sommePondere(evalTmp);
		this.first = first ;
	}
	
	public ArrayList<Integer> run(){
		boolean peutAvancer = true ;
		while(peutAvancer){
			peutAvancer = this.choose();
			this.voisinage = voisinage.init(this.solutionActuelle) ;
		}
		return this.solutionActuelle;
	}
	
	/**
	 * choisit une solution par rapport aux attributs first/best et au voisinnage
	 * @return true si on trouve un meilleur voisin false sinon
	 */
	public boolean choose(){
		boolean trouve = false;
		while(this.voisinage.hasnext()){
			ArrayList<Integer> voisin = this.voisinage.next() ;
			int[] evalVoisinMulti = this.probleme.eval(voisin) ;
			int evalVoisin = this.sommePondere(evalVoisinMulti);
			if(evalVoisin<this.evalActuelle) {
				this.solutionActuelle = voisin ;
				this.evalActuelle = evalVoisin;
				if(first){
					return true;
				}else{ //on est dans le best
					trouve = true;
				}
			}
		}
		return trouve ;
	}

	private int sommePondere(int[] evalVoisinMulti) {
		int result = 0 ;
		for (int i = 0; i < evalVoisinMulti.length; i++) {
			result += (evalVoisinMulti[i] * this.lesPoids[i]) ;
		}
		return result;
	}

	
	
	

}
