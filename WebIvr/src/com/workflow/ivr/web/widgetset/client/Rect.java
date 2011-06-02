/**
 * 
 */
package com.vaadin.graphics.canvas.widgetset.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.graphics.canvas.Canvas;
import com.vaadin.graphics.event.MouseEvent;
import com.vaadin.graphics.event.MouseEvent.Type;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.Component.Listener;

/**
 * @author kapil - kapil.verma@globallogic.com
 *
 */
public class Rect implements UIElement {

	private String id;
	private Point start;

	private Point end;
	
	private UIElement next;
	private UIElement prev;
	
	private boolean selected;
	private boolean pressed;
	private String fillColor;
	private String color;
	private int borderWidth;
	
	MouseEventListener listener;
	
	private Map<MouseEvent.Type, List<MouseEventListener>> listeners = new HashMap<MouseEvent.Type, List<MouseEventListener>>();
	
	
	public Rect(Point start, Point end){
		
		this.start = start;
		this.end = end;
		this.selected = false;
		this.borderWidth = -1;
		this.fillColor = "";
		this.color = "";
		
		listener = new MouseEventListener() {
			
			Point downPoint;
			Point upPoint;
			
			public void componentEvent(Event event) {
				
			}
			
			public void onMouseEvent(MouseEvent event) {
				Rect source = (Rect)event.getSource();
				
				if(event.getType() == MouseEvent.Type.DOWN){
					downPoint = event.getPoint();
					source.setPressed(true);
				}else if(event.getType() == MouseEvent.Type.UP){
					upPoint = event.getPoint();
					source.setSelected(true);
					source.setPressed(false);
				}else if(event.getType() == MouseEvent.Type.MOVE){
					if(source.isPressed()){
						Point p = event.getPoint();
						
						Point delta = Point.sub(p, downPoint);
						
						source.start.add(delta);
						
						source.end.add(delta);
						
						downPoint = p;
//						source.draw();
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
	public void draw(Canvas canvas) {
//		canvas.saveContext();
		
		canvas.drawRect(this);
		
//		canvas.beginPath();
//		if(this.color != null){
//			canvas.setStrokeColor(color);
//		}
//		
//		if(this.borderWidth > -1){
//			canvas.setLineWidth(borderWidth);
//		}
//		
//		canvas.strokeRect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
//		canvas.closePath();
////		canvas.rect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
//		if(this.fillColor != null){
//			canvas.setFillStyle(fillColor);
////			canvas.fillRect(start.getX(), start.getY(), end.getX()-start.getX(), end.getY()-start.getY());
//			canvas.fill();
//		}
//		canvas.restoreContext();
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
	public Point getCenter() {
		return Point.mult(Point.add(start, end), 0.5);
	}

	/* (non-Javadoc)
	 * @see com.workflow.ivr.web.model.UIElement#moveTo(double, double)
	 */
	public void moveTo(Point p) {
		
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
	public boolean contains(Point p) {
		return start.getX() <= p.getX() && p.getX() <= end.getX() && start.getY() <= p.getY() && p.getY() <= end.getY();
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
	
	public void setFillColor(String fillColor){
		this.fillColor = fillColor;
	}
	
	public String getFillColor(){
		return this.fillColor;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public String getColor(){
		return this.color;
	}

	/**
	 * @param borderWidth the borderWidth to set
	 */
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}

	/**
	 * @return the borderWidth
	 */
	public int getBorderWidth() {
		return borderWidth;
	}
	

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

}
