package utiles;


import java.util.List;



/**
 * @author Diallo, Nzeng
 *
 */
public class Not implements Component{
	private String name;
	private int inputs;
	
	public Not(){
		this.name="Not";
		this.inputs=1;
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
		//value of input
		Boolean case0 = inputValues.get(0);
		
		if(case0==false) return true;
		//case0=true
		return false;
	}



}