package technique;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import probleme.Probleme;
import probleme.TSP;
import algos.Scalaire;
import algos.TwoOpt;

public class ScriptScalaire {
	public static void main(String[] args) throws IOException{
		String[] lesPathnames = {"instances/randomA100.tsp","instances/randomB100.tsp","instances/randomC100.tsp","instances/randomD100.tsp","instances/randomE100.tsp","instances/randomF100.tsp"} ;
		String[] nameHelper = {"A","B","C","D","E","F"};
		for (int i = 0; i < lesPathnames.length-1; i++) {
			for (int j = i+1; j < lesPathnames.length; j++) {
				String[] couple = new String[2] ;
				couple[0] = lesPathnames[i] ;
				couple[1] = lesPathnames[j] ;
				Probleme probleme = new TSP(couple, 100);
				ArrayList<ArrayList<Integer>> lesSolutions = new ArrayList<ArrayList<Integer>>() ;
				for(int k = 0; k <= 10 ; k ++){
					int[] lesPoids = new int[2] ;
					lesPoids[0] = k/10 ;
					lesPoids[1] = 1 - k/10; 
					Scalaire scalaire = new Scalaire(probleme,new TwoOpt(), lesPoids, probleme.permutationAleatoire(), true);
					lesSolutions.add(scalaire.run());
				}
				String FileName = nameHelper[i] + nameHelper[j] + "_Scalaire.dat" ;
				File f = new File(FileName);
				FileWriter fw = new FileWriter(f) ;
				for (ArrayList<Integer> solution : lesSolutions) {
					int[] score = probleme.eval(solution);
					String ligne = String.valueOf(score[0]) +","+ String.valueOf(score[1]) + "\n" ;
					fw.write(ligne);
				}
				fw.close();
			}
			
		}
	}
}
