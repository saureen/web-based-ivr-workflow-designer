/**
 * 
 */
package com.ui.model;

import com.ui.canvas.Point;
import com.ui.event.MouseEvent;
import com.vaadin.ui.Component.Listener;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public interface UIElement {
	
	public void draw();
	
	public UIElement getNext();
	
	public UIElement getPrevious();
	
	public void setNext(UIElement next);
	
	public void setPrevious(UIElement prev);
	
	public void moveTo(Point p);
	
	public Point getCenter();
	
	public String getId();
	
	public boolean contains(Point p);
	
	public void addListener(Listener listener, MouseEvent.Type eventType);
	
	public void fireMouseEvent(MouseEvent event);
	
	public boolean isSelected();
	
	public void setSelected(boolean selected);
	
	public boolean isPressed();
	
	public void setPressed(boolean pressed);
}
