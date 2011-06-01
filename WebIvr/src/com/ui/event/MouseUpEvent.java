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
public class MouseUpEvent extends MouseEvent {

	private Point p;

	/**
	 * @param source
	 */
	public MouseUpEvent(UIElement source, Point p) {
		super(source);
		this.type = Type.UP;
		this.p = p;
	}

	public Point getPoint() {
		return this.p;
	}

}
