package utiles;


import java.util.List;

import javax.swing.JOptionPane;



/**
 * @author Diallo, Nzeng
 *
 */
public class Somme implements Component{
	private String name;
	private int inputs;
	
	public Somme(){
		this.name="Somme";
		this.inputs=8;
	}
	

	/**
	 * 
	 * @param binaryNumber
	 * @return the int value of the binary number
	 */
	public int binaryToNumber(String binaryNumber){
		return Integer.parseInt(binaryNumber, 2);
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
		String binaryNumber1="";
		String binaryNumber2="";
		//a course in the list to get the two numbers.
		for(int i=0;i<inputValues.size()/2;i++){
			if(inputValues.get(i)==false) binaryNumber1+="0";
			else binaryNumber1+="1";
		}
		for(int j=inputValues.size()/2;j<inputValues.size();j++){
			if(inputValues.get(j)==false) binaryNumber2+="0";
			else binaryNumber2+="1";
		}
		
		//calculating the result.
		int number1=binaryToNumber(binaryNumber1);
		int number2=binaryToNumber(binaryNumber2);
		int result = number1 + number2;
		
		System.out.println("the first number is "+number1+" and the second is "+number2+"\nthe sum equal "+result);
		
		//we show the result of operation in a panel
		JOptionPane.showMessageDialog(null,
		          "La valeur du résultat est :\n" +
		           number1 + " + "+number2+" = "+	  
		          result,
		          "Composant Somme", JOptionPane.NO_OPTION);
		//we convert the result to binary number
		String binary= Integer.toBinaryString(result);
		//if the result greater than 1 bit we return false else true
		if(binary.length()>1) return false;
		else return true;
	}

}
