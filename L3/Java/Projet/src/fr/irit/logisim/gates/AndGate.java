/***
 * @author: Duy Dinh 
 * @contact: Duy.Dinh@irit.fr
 * @version : 1.0
 */
package fr.irit.logisim.gates;

import java.awt.Graphics;

import com.cburch.logisim.analyze.model.Expression;
import com.cburch.logisim.analyze.model.Expressions;
import com.cburch.logisim.data.Value;
import com.cburch.logisim.instance.InstancePainter;
import com.cburch.logisim.instance.InstanceState;
import com.cburch.logisim.util.GraphicsUtil;

import fr.irit.logisim.string.Strings;

/**
 * Provides the skeleton for a logisim AND component. Contains the convenient
 * methods used to display a component in the GUI. The Logisim component
 * behavior is delegated to the Component implementation given at construction
 * 
 */
public class AndGate extends AbstractGate {

	public static AndGate FACTORY = new AndGate();

	public AndGate() {
		super("AND Gate", Strings.getter("andGateComponent"));
		setRectangularLabel("&");
		setIconNames("andGate.gif", "andGateRect.gif", "dinAndGate.gif");
	}

	@Override
	protected void paintIconShaped(InstancePainter painter) {
		Graphics g = painter.getGraphics();
		int[] xp = new int[] { 10, 2, 2, 10 };
		int[] yp = new int[] { 2, 2, 18, 18 };
		g.drawPolyline(xp, yp, 4);
		GraphicsUtil.drawCenteredArc(g, 10, 10, 8, -90, 180);
	}

	@Override
	protected void paintShape(InstancePainter painter, int width, int height) {
		PainterShaped.paintAnd(painter, width, height);
	}

	@Override
	protected void paintDinShape(InstancePainter painter, int width,
			int height, int inputs) {
		PainterDin.paintAnd(painter, width, height, false);
	}

	@Override
	protected Value computeOutput(Value[] inputs, int numInputs,
			InstanceState state) {

		Value ret = inputs[0];
		for (int i = 1; i < numInputs; i++) {
			ret = ret.and(inputs[i]);
		}
		return ret;
	}

	@Override
	protected Expression computeExpression(Expression[] inputs, int numInputs) {
		Expression ret = inputs[0];
		for (int i = 1; i < numInputs; i++) {
			ret = Expressions.and(ret, inputs[i]);
		}
		return ret;
	}

	@Override
	protected Value getIdentity() {
		return Value.TRUE;
	}

}
