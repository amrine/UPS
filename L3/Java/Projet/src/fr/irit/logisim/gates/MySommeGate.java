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
 * Provides the skeleton for a logisim SOMME component. Contains the convenient
 * methods used to display a component in the GUI. The logisim component
 * behavior is delegated to the Component implementation given at construction
 * 
 */
public class MySommeGate extends InstanceFactory {

	/**
	 * the object containing the behavior of the component
	 */
	Component implementation;

	/**
	 * @param implementation
	 *            the Component containing the behavior code
	 */
	public MySommeGate(Component implementation) {
		super(implementation.getName());

		// get the implementation
		this.implementation = implementation;

		// define the bounds in the GUI
		setOffsetBounds(Bounds.create(-120, -30, 120, 120));

		// define the number and position of the I/O ports in the GUI
		setPorts(new Port[] { 
				new Port(-120,10, Port.INPUT, 1),
				new Port(-120,20, Port.INPUT, 1),
				new Port(-120,30, Port.INPUT, 1),
				new Port(-120,40, Port.INPUT, 1),
				
				new Port(-120,50, Port.INPUT, 1),
				new Port(-120,60, Port.INPUT, 1),
				new Port(-120,70, Port.INPUT, 1),
				new Port(-120,80, Port.INPUT, 1),
				
				new Port(0, 40, Port.OUTPUT, 1),
				});
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
				|| state.getPort(4).equals(Value.ERROR)
				|| state.getPort(5).equals(Value.ERROR)
				|| state.getPort(6).equals(Value.ERROR)
				|| state.getPort(7).equals(Value.ERROR)
				) {
			state.setPort(8, Value.ERROR, 1);

			// if one of the input ports has an unknown value, we return an
			// unknown value
		} else if (state.getPort(0).equals(Value.UNKNOWN)
				|| state.getPort(1).equals(Value.UNKNOWN)
				|| state.getPort(2).equals(Value.UNKNOWN)
				|| state.getPort(3).equals(Value.UNKNOWN)
				|| state.getPort(4).equals(Value.UNKNOWN)
				|| state.getPort(5).equals(Value.UNKNOWN)
				|| state.getPort(6).equals(Value.UNKNOWN)
				|| state.getPort(7).equals(Value.UNKNOWN)) {
			state.setPort(8, Value.UNKNOWN, 1);

			// else we use the implementation given in the constructor to
			// calculate the output
		} else {

			// for each entry convert it to a boolean
			boolean val0 = !state.getPort(0).equals(Value.FALSE);
			boolean val1 = !state.getPort(1).equals(Value.FALSE);
			boolean val2 = !state.getPort(2).equals(Value.FALSE);
			boolean val3 = !state.getPort(3).equals(Value.FALSE);
			boolean val4 = !state.getPort(4).equals(Value.FALSE);
			boolean val5 = !state.getPort(5).equals(Value.FALSE);
			boolean val6 = !state.getPort(6).equals(Value.FALSE);
			boolean val7 = !state.getPort(7).equals(Value.FALSE);

			// we compute the value of the output and convert it into a logisim
			// value
			
			Value res;
			if (implementation.compute(Arrays.asList(val0, val1,val2,val3,val4,val5,val6,val7))) {
				res = Value.TRUE;
			
			} else {
				res = Value.FALSE;
			}

			// we create the new value of the output, and update it
			Value resVal = Value.createKnown(state.getPort(8).getBitWidth(),
					res.toIntValue());
			state.setPort(8, resVal, resVal.getWidth() + 1);
		}

	}

}