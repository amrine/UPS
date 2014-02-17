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
 * Provides the skeleton for a logisim Modulo3 component. Contains the convenient
 * methods used to display a component in the GUI. The logisim component
 * behavior is delegated to the Component implementation given at construction
 * 
 */
public class MyModulo3Gate extends InstanceFactory {

	/**
	 * the object containing the behavior of the component
	 */
	Component implementation;

	/**
	 * @param implementation
	 *            the Component containing the behavior code
	 */
	public MyModulo3Gate(Component implementation) {
		super(implementation.getName());

		// get the implementation
		this.implementation = implementation;

		// define the bounds in the GUI
		setOffsetBounds(Bounds.create(-60, -11, 60, 60));

		// define the number and position of the I/O ports in the GUI
		setPorts(new Port[] { 
				new Port(-60,10, Port.INPUT, 1),
				new Port(-60,20, Port.INPUT, 1),
				new Port(-60,30, Port.INPUT, 1),
				new Port(-60,40, Port.INPUT, 1),
				new Port(0, 20, Port.OUTPUT, 1), });
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
        painter.drawPorts(); 
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

		// if one of the input ports has an error value, we return an error
		// value
		if (state.getPort(0).equals(Value.ERROR)
				|| state.getPort(1).equals(Value.ERROR)
				|| state.getPort(2).equals(Value.ERROR)
				|| state.getPort(3).equals(Value.ERROR)
				) {
			state.setPort(4, Value.ERROR, 1);

			// if one of the input ports has an unknown value, we return an
			// unknown value
		} else if (state.getPort(0).equals(Value.UNKNOWN)
				|| state.getPort(1).equals(Value.UNKNOWN)
				|| state.getPort(2).equals(Value.UNKNOWN)
				|| state.getPort(3).equals(Value.UNKNOWN)) {
			state.setPort(4, Value.UNKNOWN, 1);

			// else we use the implementation given in the constructor to
			// calculate the output
		} else {

			// for each entry convert it to a boolean
			boolean val0 = !state.getPort(0).equals(Value.FALSE);
			boolean val1 = !state.getPort(1).equals(Value.FALSE);
			boolean val2 = !state.getPort(2).equals(Value.FALSE);
			boolean val3 = !state.getPort(3).equals(Value.FALSE);

			// we compute the value of the output and convert it into a logisim
			// value
			Value res;
			if (implementation.compute(Arrays.asList(val0, val1,val2,val3))) {
				res = Value.TRUE;
			} else {
				res = Value.FALSE;
			}

			// we create the new value of the output, and update it
			Value resVal = Value.createKnown(state.getPort(4).getBitWidth(),
					res.toIntValue());
			state.setPort(4, resVal, resVal.getWidth() + 1);
		}

	}

}