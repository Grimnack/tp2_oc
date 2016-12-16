package algos;

import java.util.ArrayList;

import probleme.Probleme;

/**
 * génère n permutations aléatoire et ne garde que le front pareto
 * @author caron
 *
 */
public class Filtre {
	
	protected Probleme probleme ;
	
	public Filtre(Probleme p) {
		this.probleme = p ;
	}
	
	/**
	 * Dans une stratégie «off-line», tous les points que l’on désire filtrer sont connus «en même temps». 
	 * L’algorithme prend un ensemble de solutions en entrée et retourne le sous-ensemble de solutions non-dominées. 
	 * On pourra éventuellement préciser, pour chaque objectif, s’il est à minimiser ou à maximiser.
	 */
	public ArrayList<ArrayList<Integer>> filtreOffLine(ArrayList<ArrayList<Integer>> lesSolutions) {
		ArrayList<ArrayList<Integer>> filterResult = new ArrayList<ArrayList<Integer>>() ;
		for (ArrayList<Integer> solution1 : lesSolutions) {
			boolean isDominated = false ;
			for (ArrayList<Integer> solution2 : lesSolutions) {
				if (this.probleme.dominate(solution2, solution1)) {
					isDominated = true ;
					break ;
				}
			}
			if(!isDominated){
				filterResult.add(solution1);
			}
		}
		return filterResult ;
	}
	/**
	 * Dans une stratégie «on-line», l’algorithme maintient un ensemble de solutions que l’on appelle une archive A (initialement un ensemble vide). 
	 * De nouvelles solutions lui arrivent l’une après l’autre. 
	 * À chaque fois qu’il prend une nouvelle solution x en entrée, l’algorithme doit décider s’il ajoute la solution x à l’archive A, 
	 * et doit éventuellement supprimer les solutions de A qui sont dominées par x.
	 * Se pose alors la question d’une structure de données adaptées pour stocker le contenu de l’archive (en particulier dans le cas à deux objectifs).
	 * @param lesSolutions
	 * @return
	 */
	public ArrayList<ArrayList<Integer>> filtreOnLine(ArrayList<ArrayList<Integer>> lesSolutions){
		ArrayList<ArrayList<Integer>> filterResult = new ArrayList<ArrayList<Integer>>() ;
		while(!lesSolutions.isEmpty()){
			ArrayList<Integer> solutionCandidate = lesSolutions.remove(0);
			int[] scoreCandidate = this.probleme.eval(solutionCandidate);
			boolean isDominated = false ;
			ArrayList<ArrayList<Integer>> toRemove = new ArrayList<ArrayList<Integer>>() ;
			for (ArrayList<Integer> solutionArchive : filterResult) {
				int[] scoreArchive = this.probleme.eval(solutionArchive);
				if(this.probleme.dominate(solutionArchive, solutionCandidate)){
					isDominated = true ;
				}
				if(this.probleme.dominate(solutionCandidate, solutionArchive)){
					toRemove.add(solutionArchive);
				}
			}
			for (ArrayList<Integer> solution : toRemove) {
				filterResult.remove(solution);
			}
			if(!isDominated){
				filterResult.add(solutionCandidate);
			}
		}
		
		return filterResult ;
	}
	
	
	

}
