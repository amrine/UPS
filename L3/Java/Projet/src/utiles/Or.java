/**
 * Composant Or
 */
package utiles;

import java.util.List;



/**
 * @author Diallo, Nzeng
 *
 */
public class Or implements Component{
	private String name;
	private int inputs;
	
	public Or(){
		this.name="Or";
		this.inputs=2;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int nbInputs() {
		return inputs;
	}

	@Override
	public boolean compute(List<Boolean> inputValues) {
		//we check if the size of the input list matches the specified number of input 
		if(inputValues.size() != inputs) throw new RuntimeException("the size of the input list don't match the specified number of input");
		//values of input 
		Boolean case0 = inputValues.get(0);
		Boolean case1 = inputValues.get(1);
		
		if(case0==false && case1==false) return false;
		else 
		return true;
	}

}