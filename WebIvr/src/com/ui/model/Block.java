/**
 * 
 */
package com.ui.model;

import com.ui.canvas.Canvas;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class Block implements UIElement {

	private String id;
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	
	private Canvas canvas;
	private UIElement next;
	private UIElement prev;
	
	public Block(Canvas canvas, double startX, double startY, double endX, double endY){
		this.canvas = canvas;
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#draw()
	 */
	public void draw() {
		canvas.rect(startX, startY, endX-startX, endY-startY);
		canvas.strokeRect(startX, startY, endX-startX, endY-startY);
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#getNext()
	 */
	public UIElement getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#getPrevious()
	 */
	public UIElement getPrevious() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setNext(UIElement next){
		this.next = next;
	}
	
	public void setPrevious(UIElement prev){
		this.prev = prev;
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#getCenterX()
	 */
	public double getCenterX() {
		return (startX + endX)/2;
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#getCenterY()
	 */
	public double getCenterY() {
		return (startY + endY)/2;
	}
	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#moveTo(double, double)
	 */
	public void moveTo(double x, double y) {
		
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see com.ui.model.UIElement#contains(double, double)
	 */
	public boolean contains(double x, double y) {
		return startX <= x && x <= endX && startY <= y && y <= endY;
	}

}
