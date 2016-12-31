package algos;

import java.util.ArrayList;

public class TwoOpt implements Voisinage {
	protected int i=0;
	protected int j=1;
	ArrayList<Integer> initial ;

	@Override
	public boolean hasnext() {
		return (i+1<j) || (j+1 < initial.size());
	}

	@Override
	public ArrayList<Integer> next() {
		
		ArrayList<Integer> voisin = new ArrayList<Integer>() ;
		for (int k = 0; k < i; k++) {
			voisin.add(initial.get(k));
		}
		for (int k = j; k>= i;k--){
			voisin.add(initial.get(k));
		}
		for (int k = j + 1; k<initial.size();k++){
			voisin.add(initial.get(k)) ;
		}
		if( this.i + 1 < this.j) { 
			this.i++;
		}else{
			this.i = 0;
			this.j++ ;
		}
		return voisin;
	}

	@Override
	public Voisinage init(ArrayList<Integer> solutionInitiale) {
		this.initial = solutionInitiale ;
		this.i = 0;
		this.j = 1;
		return this;
	}

}
