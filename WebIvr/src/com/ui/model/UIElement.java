/**
 * 
 */
package com.ui.model;

import com.google.gwt.touch.client.Point;

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
	
	public void moveTo(double x, double y);
	
	public double getCenterX();
	
	public double getCenterY();
	
	public String getId();
	
	public boolean contains(double x, double y);
}
