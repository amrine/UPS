package utiles;


import java.util.List;



/**
 * @author Diallo, Nzeng
 *
 */
public class Modulo3 implements Component{
	private String name;
	private int inputs;
	
	public Modulo3(){
		this.name="Modulo3";
		this.inputs=4;
	}
	
	
	/**
	 * 
	 * @param binaryNumber
	 * @return true if the binary number is a multiple of 3 else false
	 */
	public boolean multipleDe3(String binaryNumber){
		
		int number = Integer.parseInt(binaryNumber, 2);
		
	    if(number % 3 == 0) {
	    	System.out.println("Le nombre est : "+number+" multiple de 3");
	    	return true;
	    }
	    return false;
	    
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
		String binaryNumber="";
		for(int i=0;i<inputValues.size();i++){
			System.out.println(i +" " +inputValues.get(i));
			if(inputValues.get(i)==false) binaryNumber+="0";
			else binaryNumber+="1";
		}
		return multipleDe3(binaryNumber);
	}

}