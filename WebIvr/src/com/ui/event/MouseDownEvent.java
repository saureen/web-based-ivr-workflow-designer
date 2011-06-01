/**
 * 
 */
package com.ui.event;

import com.ui.canvas.Point;
import com.ui.model.UIElement;


/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class MouseDownEvent extends MouseEvent {

	private Point p;
	
	/**
	 * @param source
	 */
	public MouseDownEvent(UIElement source, Point p) {
		super(source);
		this.type = MouseEvent.Type.DOWN;
		this.p = p;
	}

	public Point getPoint() {
		return this.p;
	}

}
