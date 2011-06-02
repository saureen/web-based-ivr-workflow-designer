/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client;

import com.vaadin.graphics.canvas.Canvas;
import com.vaadin.graphics.event.MouseEvent;
import com.vaadin.ui.Component.Listener;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public interface UIElement {
	
	public void draw(Canvas canvas);
	
	public UIElement getNext();
	
	public UIElement getPrevious();
	
	public void setNext(UIElement next);
	
	public void setPrevious(UIElement prev);
	
	public void moveTo(Point p);
	
	public Point getCenter();
	
	public String getId();
	
	public void setId(String id);
	
	public boolean contains(Point p);
	
	public void addListener(Listener listener, MouseEvent.Type eventType);
	
	public void fireMouseEvent(MouseEvent event);
	
	public boolean isSelected();
	
	public void setSelected(boolean selected);
	
	public boolean isPressed();
	
	public void setPressed(boolean pressed);
	
	public String getColor();
	
	public void setColor(String color);
	
	public String getFillColor();
	
	public void setFillColor(String fillColor);
	
	public int getBorderWidth();
	
	public void setBorderWidth(int width);
}
