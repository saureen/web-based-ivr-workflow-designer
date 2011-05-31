/**
 * 
 */
package com.ui.event;

import java.util.EventObject;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public abstract class MouseEvent extends EventObject {

	public static enum Types{UP, DOWN, MOVE};
	
	protected Types type;
	
	/**
	 * @param source
	 */
	public MouseEvent(Object source) {
		super(source);
	}

	public MouseEvent.Types getType() {
		return type;
	}
	
	public abstract int getX();
	
	public abstract int getY();
}
