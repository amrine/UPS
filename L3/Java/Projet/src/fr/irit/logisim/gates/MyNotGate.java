package fr.irit.logisim.gates;



import java.util.Arrays;

import utiles.Component;

import com.cburch.logisim.data.Bounds;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.InstanceFactory;
import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.instance.InstanceState;
import com.cburch.logisim.instance.Port;



/**
 * Provides the skeleton for a logisim NOT component. Contains the convenient
 * methods used to display a component in the GUI. The logisim component
 * behavior is delegated to the Component implementation given at construction
 * 
 */
public class MyNotGate extends InstanceFactory {

	/**
	 * the object containing the behavior of the component
	 */
	Component implementation;

	/**
	 * @param implementation
	 *            the Component containing the behavior code
	 */
	public MyNotGate(Component implementation) {
		super(implementation.getName());

		// get the implementation
		this.implementation = implementation;

		// define the bounds in the GUI
		setOffsetBounds(Bounds.create(-30, -15, 30, 30));

		// define the number and position of the I/O ports in the GUI
		setPorts(new Port[] { new Port(-30, 0, Port.INPUT, 1),
				new Port(0, 0, Port.OUTPUT, 1), });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cburch.logisim.instance.InstanceFactory#paintInstance(com.cburch.
	 * logisim.instance.InstancePainter)
	 */
	public void paintInstance(InstancePainter painter) {
		painter.drawRectangle(painter.getBounds(), implementation.getName());
        painter.drawPorts(); ;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cburch.logisim.instance.InstanceFactory#propagate(com.cburch.logisim
	 * .instance.InstanceState)
	 */
	public void propagate(InstanceState state) {

		// first we check some specific cases

		// if the input port has an error value, we return an error value
		if (state.getPort(0).equals(Value.ERROR)) {
			state.setPort(1, Value.ERROR, 1);

			// if the input port has an unknown value, we return an unknown
			// value
		} else if (state.getPort(0).equals(Value.UNKNOWN)) {
			state.setPort(1, Value.UNKNOWN, 1);
			
			// else we use the implementation given in the constructor to
			// calculate the output
		} else {
			
			// convert the entry into a boolean
			boolean val0 = !state.getPort(0).equals(Value.FALSE);

			// we compute the value of the output and convert it into a logisim
			// value
			Value res = implementation.compute(Arrays.asList(val0)) ? Value.TRUE
					: Value.FALSE;

			// we create the new value of the output, and update it
			Value resVal = Value.createKnown(state.getPort(1).getBitWidth(),
					res.toIntValue());
			state.setPort(1, resVal, resVal.getWidth() + 1);
		}

	}

}