/**
 * 
 */
package com.ui.event;

import com.ui.model.UIElement;


/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class MouseDownEvent extends MouseEvent {

	private int x;
	private int y;
	
	/**
	 * @param source
	 */
	public MouseDownEvent(UIElement source, int x, int y) {
		super(source);
		this.type = MouseEvent.Type.DOWN;
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
