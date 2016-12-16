package probleme;

import java.util.ArrayList;

public interface Probleme {

	public int[] eval(ArrayList<Integer> permutation) ;
	public ArrayList<Integer> permutationAleatoire() ;
	public boolean dominate(ArrayList<Integer> solution1, ArrayList<Integer> solution2) ;
	
}
