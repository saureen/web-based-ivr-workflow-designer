/**
 * 
 */
package com.ui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import com.ui.canvas.Canvas;
import com.ui.event.MouseEvent;
import com.ui.event.MouseEvent.Type;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Component.Listener;

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
	
	private boolean selected;
	private boolean pressed;
	
	private Map<MouseEvent.Type, List<MouseEventListener>> listeners = new HashMap<MouseEvent.Type, List<MouseEventListener>>();
	
	
	public Block(Canvas canvas, double startX, double startY, double endX, double endY){
		this.canvas = canvas;
		
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.selected = false;
		
		MouseEventListener listener = new MouseEventListener() {
			
			public void componentEvent(Event event) {
				
			}
			
			public void onMouseEvent(MouseEvent event) {
				Block source = (Block)event.getSource();
				
				if(event.getType() == MouseEvent.Type.DOWN){
					source.setPressed(true);
				}else if(event.getType() == MouseEvent.Type.UP){
					source.setSelected(true);
					source.setPressed(false);
				}else if(event.getType() == MouseEvent.Type.MOVE){
					if(source.isPressed()){
						double x = event.getX();
						double y = event.getY();
						
						double deltaX = x-getCenterX();
						double deltaY = y-getCenterY();
						
						source.startX += deltaX;
						source.startY += deltaY;
						
						source.endX += deltaX;
						source.endY += deltaY;
						
						source.draw();
					}
				}else{
					System.err.println("Unknown event type: " + event.getType());
				}
			}
		};
		
		List<MouseEventListener> upListeners = new ArrayList<MouseEventListener>();
		upListeners.add(listener);
		listeners.put(Type.UP, upListeners);
		
		List<MouseEventListener> downListeners = new ArrayList<MouseEventListener>();
		downListeners.add(listener);
		listeners.put(Type.DOWN, downListeners);
		
		List<MouseEventListener> moveListeners = new ArrayList<MouseEventListener>();
		moveListeners.add(listener);
		listeners.put(Type.MOVE, moveListeners);
	}
	
	public interface MouseEventListener extends Listener{
		public void onMouseEvent(MouseEvent event);
	}
	
	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#draw()
	 */
	public void draw() {
		canvas.rect(startX, startY, endX-startX, endY-startY);
		canvas.strokeRect(startX, startY, endX-startX, endY-startY);
		canvas.addChild(this);
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
	/* (non-Javadoc)
	 * @see com.ui.model.UIElement#addListener(com.vaadin.ui.Component.Listener)
	 */
	public void addListener(Listener listener, MouseEvent.Type eventType) {
		
	}
	/* (non-Javadoc)
	 * @see com.ui.model.UIElement#fireMouseEvent(com.vaadin.event.MouseEvents)
	 */
	public void fireMouseEvent(MouseEvent event) {
		Type type = event.getType();
		
		List<MouseEventListener> listernerList = this.listeners.get(type);
		for(MouseEventListener listener : listernerList){
			listener.onMouseEvent(event);
		}
	}

	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isPressed() {
		return this.pressed;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

}
