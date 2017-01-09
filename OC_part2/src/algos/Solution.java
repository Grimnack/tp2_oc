package algos;

import java.util.ArrayList;

public class Solution{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7709736974557095180L;
	protected boolean visited = false ;
	protected ArrayList<Integer> permutation ;
	
	public Solution(ArrayList<Integer> permut){
		this.permutation = permut ;
	}
	
	public void setVisitedTrue(){
		this.visited = true;
	}

	public ArrayList<Integer> getPermutation() {
		return this.permutation ;
	}
}
