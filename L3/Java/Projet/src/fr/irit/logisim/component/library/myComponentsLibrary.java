/***
 * @author: Tom Jorquera, Duy Dinh 
 * @contact: Tom.Jorquera@irit.fr, Duy.Dinh@irit.fr
 * @version : 1.0
 */

package fr.irit.logisim.component.library;

import java.util.Arrays;
import java.util.List;

import utiles.ComponentFactory;

import com.cburch.logisim.tools.AddTool;
import com.cburch.logisim.tools.Library;

import fr.irit.logisim.gates.AndGate;
import fr.irit.logisim.gates.MyAndGate;
import fr.irit.logisim.gates.MyModulo3Gate;
import fr.irit.logisim.gates.MyNotGate;
import fr.irit.logisim.gates.MyOrGate;
import fr.irit.logisim.gates.MySommeGate;
import fr.irit.logisim.gates.NotGate;

/**
 * Define a custom Logisim library.
 * 
 */
public class myComponentsLibrary extends Library {

	/**
	 * the list of tools provided by the library
	 */
	private List<AddTool> tools;

	/**
	 * construct the library and load our custom-defined components,
	 * instantiating them with the implementations provided in the
	 * ComponentFactory
	 */
	public myComponentsLibrary() {
		this.tools = Arrays.asList(
		// add the tool to create our custom defined AND gate
				new AddTool(new AndGate()),
				new AddTool(
						new MyAndGate(ComponentFactory.createAndComponent())),
				//add the tool to create our custom defined NOT gate
				new AddTool(
						new MyNotGate(ComponentFactory.createNotComponent())),
						//add the tool to create our custom defined OR gate
				new AddTool(
						new MyOrGate(ComponentFactory.createOrComponent()))
				,
				//add the tool to create our custom defined MODULO gate
				new AddTool(
						new MyModulo3Gate(ComponentFactory.createModulo3Component())),
				//add the tool to create our custom defined MODULO gate
				new AddTool(
						new MySommeGate(ComponentFactory.createSommeComponent())),
				// add the tool to create our custom defined NOT gate
				new AddTool(new NotGate()));
		// add JButton
		// new AddTool(myJButton(ComponentFactory.createJButton()));
	}

	/**
	 * @see com.cburch.logisim.tools.Library#getDisplayName() Returns the name
	 *      of the library that the user will see in logisim.
	 */
	public String getDisplayName() {
		return "myGates";
	}

	/**
	 * @see com.cburch.logisim.tools.Library#getTools() Returns a list of all
	 *      the tools available in this library to be loaded in logisim.
	 */
	public List<AddTool> getTools() {
		return tools;
	}

}
