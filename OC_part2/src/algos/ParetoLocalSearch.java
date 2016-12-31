package algos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import probleme.Probleme;

public class ParetoLocalSearch {
	protected Probleme probleme ;
	protected Voisinage voisinage ;
	protected ArrayList<Solution> pareto = new ArrayList<Solution>() ;
	protected int[] evalActuelles ;
	protected boolean first ;
	
	
	public ParetoLocalSearch(Probleme probleme, Voisinage voisinage,Solution solutionInitiale, boolean first){
		this.probleme = probleme ;
		this.voisinage = voisinage ;
		this.pareto.add(solutionInitiale);
		this.first = first ;
	}
	
	private boolean domine(int[] eval1, int[] eval2){
		boolean toujoursInfouEgal = true ;
		for(int i=0; i < eval1.length;i++){
			if(!(eval1[i]<=eval2[i])){
				toujoursInfouEgal = false ;
				return false;
			}
		}
		return toujoursInfouEgal && (eval1 != eval2) ;
	}
	
	public ArrayList<Solution> run(){
		while(true){
			// On selectionne au hasard une solution du pareto
			Collections.shuffle(pareto);
			Solution solutionActuelle = pareto.remove(0);
			this.voisinage.init(solutionActuelle);
			while(this.voisinage.hasnext()){
				Solution solutionCandidate =(Solution) voisinage.next();
				
			}
		}
		
		
		return null;
	}
}
