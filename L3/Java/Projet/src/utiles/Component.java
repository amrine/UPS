package utiles;

import java.util.List;

/**
 * Specify the interface of a component. A component is characterized by a name,
 * a number of inputs and the capability to compute an output based on these
 * inputs
 * 
 */
public interface Component {

	/**
	 * used to obtain the name of the component. This method is used among
	 * others by Logisim when displaying a component in the GUI
	 * 
	 * @return the name of the component
	 */
	public String getName();

	/**
	 * give the number of inputs expected by the component
	 * 
	 * @return the number of inputs
	 */
	public int nbInputs();

	/**
	 * 
	 * compute the output based on the values of its inputs
	 * 
	 * A good implementation should check if the size of the input list matches
	 * the specified number of input before trying to do the calculations
	 * 
	 * @param inputValues
	 *            the list of input values
	 * @return the output value
	 */
	public boolean compute(List<Boolean> inputValues);

}