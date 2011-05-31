/**
 * 
 */
package com.ui.event;

import com.ui.model.UIElement;


/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class MouseUpEvent extends MouseEvent {

	private int x;
	private int y;

	/**
	 * @param source
	 */
	public MouseUpEvent(UIElement source, int x, int y) {
		super(source);
		this.type = Types.UP;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
