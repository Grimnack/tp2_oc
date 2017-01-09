package technique;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import probleme.Probleme;
import probleme.TSP;
import algos.ParetoLocalSearch;
import algos.Solution;
import algos.TwoOpt;

public class ScriptPareto {
	public static void main(String[] args) throws IOException{
		String[] lesPathnames = {"instances/randomA100.tsp","instances/randomB100.tsp","instances/randomC100.tsp","instances/randomD100.tsp","instances/randomE100.tsp","instances/randomF100.tsp"} ;
		String[] nameHelper = {"A","B","C","D","E","F"};
		ArrayList<Solution> lesSolutions = null;
		for (int i = 0; i < lesPathnames.length-1; i++) {
			for (int j = i+1; j < lesPathnames.length; j++) {
				String[] couple = new String[2] ;
				couple[0] = lesPathnames[i] ;
				couple[1] = lesPathnames[j] ;
				Probleme probleme = new TSP(couple, 100); 
				ParetoLocalSearch pareto = new ParetoLocalSearch(probleme, new TwoOpt(),new Solution(probleme.permutationAleatoire()));
				lesSolutions = pareto.run();
				String FileName = nameHelper[i] + nameHelper[j] + "_Pareto.dat" ;
				System.out.println("instance finie");
				File f = new File(FileName);
				FileWriter fw = new FileWriter(f) ;
				for (Solution solution : lesSolutions) {
					int[] score = probleme.eval(solution.getPermutation());
					String ligne = String.valueOf(score[0]) +","+ String.valueOf(score[1]) + "\n" ;
					fw.write(ligne);
				}
				fw.close();
			}
			
		}
		System.out.println("fini");
	}
}
