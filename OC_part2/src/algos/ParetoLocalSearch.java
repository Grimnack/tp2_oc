package algos;

import java.util.ArrayList;
import java.util.Collections;

import probleme.Probleme;

public class ParetoLocalSearch {
	protected Probleme probleme ;
	protected Voisinage voisinage ;
	protected ArrayList<Solution> pareto = new ArrayList<Solution>() ;
	protected int[] evalActuelles ;
	protected boolean first ;
	
	
	public ParetoLocalSearch(Probleme probleme, Voisinage voisinage,Solution solutionInitiale){
		this.probleme = probleme ;
		this.voisinage = voisinage ;
		this.pareto.add(solutionInitiale);
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
	
	public Solution randomSelect(ArrayList<Solution> pareto){
		Collections.shuffle(pareto);
		for (Solution solution : pareto) {
			if(!solution.visited){
				return solution ;
			}
		}
		return null;
	}
	
	public ArrayList<Solution> run(){
		long startTime = System.currentTimeMillis();
		while(System.currentTimeMillis() - startTime < 300000){
			// On selectionne au hasard une solution du pareto
			Solution solutionActuelle = randomSelect(pareto);
			if(solutionActuelle == null){
				break; // on a visité toutes les solutions.
			}
			this.voisinage.init(solutionActuelle.permutation);
			solutionActuelle.setVisitedTrue();
			while(this.voisinage.hasnext()){
				ArrayList<Integer> solutionCandidate = voisinage.next();
				int[] evalCandidat = this.probleme.eval(solutionCandidate);
				boolean dominated = false ;
				ArrayList<Solution> toRemove = new ArrayList<Solution>() ;
				for (Solution solution : pareto) {
					int[] evalPareto = this.probleme.eval(solution.permutation) ;
					if(domine(evalCandidat,evalPareto)){
						toRemove.add(solution);
					}else if(domine(evalPareto,evalCandidat)){
						dominated = true ;
						break ;
					}
				}
				for (Solution solution : toRemove){
					pareto.remove(solution);
				}
				if(!dominated){
					pareto.add(new Solution(solutionCandidate));
				}
				
			}
		}
		return pareto;
	}
}
