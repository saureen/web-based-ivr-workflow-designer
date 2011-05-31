/**
 * 
 */
package com.ui.event;

import com.ui.model.UIElement;


/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class MouseMoveEvent extends MouseEvent {

	private int x;
	private int y;
	
	/**
	 * @param source
	 */
	public MouseMoveEvent(UIElement source, int x, int y) {
		super(source);
		this.type = Type.MOVE;
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
