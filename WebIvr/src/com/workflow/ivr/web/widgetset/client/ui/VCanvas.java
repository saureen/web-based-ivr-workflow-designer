package com.workflow.ivr.web.widgetset.client.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.widgetideas.graphics.client.CanvasGradient;
import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

/**
 * Client side widget which communicates with the server. Messages from the
 * server are shown as HTML and mouse clicks are sent to the server.
 */
public class VCanvas extends Composite implements Paintable {

	/** Set the CSS class name to allow styling. */
	public static final String CLASSNAME = "v-canvas";

	public static final String CLICK_EVENT_IDENTIFIER = "click";

	/** The client side widget identifier */
	protected String paintableId;

	/** Reference to the server connection object. */
	protected ApplicationConnection client;

	private MouseHandlingCanvas canvas;

	private int widthCache;

	private int heightCache;

	private final Map<String, CanvasGradient> gradients = new HashMap<String, CanvasGradient>();

	/**
	 * The constructor should first call super() to initialize the component and
	 * then handle any initialization relevant to Vaadin.
	 */
	public VCanvas() {
		super();

		canvas = new MouseHandlingCanvas();
		canvas.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				if (client == null) {
					return;
				}

				int x = event.getClientX() - getAbsoluteLeft();
				int y = event.getClientY() - getAbsoluteTop();
				client.updateVariable(paintableId, "mx", x, false);
				client.updateVariable(paintableId, "my", y, false);
				client.updateVariable(paintableId, "event", "mousedown", true);
			}
		});
		canvas.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				if (client == null) {
					return;
				}

				int x = event.getClientX() - getAbsoluteLeft();
				int y = event.getClientY() - getAbsoluteTop();
				client.updateVariable(paintableId, "mx", x, false);
				client.updateVariable(paintableId, "my", y, false);
				client.updateVariable(paintableId, "event", "mouseup", true);
			}
		});
		
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			
			public void onMouseMove(MouseMoveEvent event) {
				if (client == null) {
					return;
				}

				int x = event.getClientX() - getAbsoluteLeft();
				int y = event.getClientY() - getAbsoluteTop();
				client.updateVariable(paintableId, "mx", x, false);
				client.updateVariable(paintableId, "my", y, false);
				client.updateVariable(paintableId, "event", "mousemove", true);
			}
		});

		initWidget(canvas); // All Composites need to call initWidget()

		setStyleName(CLASSNAME);
	}

	/**
	 * Called whenever an update is received from the server
	 */
	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		// This call should be made first. Ensure correct implementation,
		// and let the containing layout manage caption, etc.
		if (client.updateComponent(this, uidl, true)) {
			return;
		}

		// Save reference to server connection object to be able to send
		// user interaction later
		this.client = client;

		// Save the UIDL identifier for the component
		paintableId = uidl.getId();

		canvas.clear(); // Always redraw fully

		for (Iterator<Object> iter = uidl.getChildIterator(); iter.hasNext();) {
			UIDL childUIDL = (UIDL) iter.next();
			String command = childUIDL.getTag().toLowerCase();
			if (command.equals("stroke")) {
				canvas.stroke();
			} else if (command.equals("restorecontext")) {
				canvas.restoreContext();
			} else if (command.equals("savecontext")) {
				canvas.saveContext();
			} else if (command.equals("beginpath")) {
				canvas.beginPath();
			} else if (command.equals("closepath")) {
				canvas.closePath();
			} else if (command.equals("clear")) {
				canvas.clear();
			} else if (command.equals("setglobalalpha")) {
				handleSetGlobalAlpha(childUIDL);
			} else if (command.equals("addcolorstop")) {
				handleAddColorStop(childUIDL);
			} else if (command.equals("setglobalcompositeoperation")) {
				handleSetGlobalCompositeOperation(childUIDL);
			} else if (command.equals("setstrokecolor")) {
				handleStrokeColorCommand(childUIDL);
			} else if (command.equals("setbackgroundcolor")) {
				handleBackgroundColorCommand(childUIDL);
			} else if (command.equals("translate")) {
				handleTranslateCommand(childUIDL);
			} else if (command.equals("scale")) {
				handleScaleCommand(childUIDL);
			} else if (command.equals("arc")) {
				handleArcCommand(childUIDL);
			} else if (command.equals("createlineargradient")) {
				handleCreateLinearGradient(childUIDL);
			} else if (command.equals("createradialgradient")) {
				handleCreateRadialGradient(childUIDL);
			} else if (command.equals("cubiccurveto")) {
				handleCubicCurveTo(childUIDL);
			} else if (command.equals("drawimage1")) {
				handleDrawImage1(childUIDL);
			} else if (command.equals("drawimage2")) {
				handleDrawImage2(childUIDL);
			} else if (command.equals("drawimage3")) {
				handleDrawImage3(childUIDL);
			} else if (command.equals("fill")) {
				canvas.fill();
			} else if (command.equals("fillrect")) {
				handleFillRect(childUIDL);
			} else if (command.equals("lineto")) {
				handleLineTo(childUIDL);
			} else if (command.equals("moveto")) {
				handleMoveTo(childUIDL);
			} else if (command.equals("quadraticcurveto")) {
				handleQuadraticCurveTo(childUIDL);
			} else if (command.equals("rect")) {
				handleRect(childUIDL);
			} else if (command.equals("rotate")) {
				handleRotate(childUIDL);
			} else if (command.equals("setgradientfillstyle")) {
				handleSetGradientFillStyle(childUIDL);
			} else if (command.equals("setfillstyle")) {
				handleSetFillStyle(childUIDL);
			} else if (command.equals("setlinecap")) {
				handleSetLineCap(childUIDL);
			} else if (command.equals("setlinekoin")) {
				handleSetLineJoin(childUIDL);
			} else if (command.equals("setlinewidth")) {
				handleSetLineWidth(childUIDL);
			} else if (command.equals("setmiterlimit")) {
				handleSetMiterLimit(childUIDL);
			} else if (command.equals("setcolorstrokestyle")) {
				handleSetColorStrokeStyle(childUIDL);
			} else if (command.equals("setgradientstrokestyle")) {
				handleSetGradientStrokeStyle(childUIDL);
			} else if (command.equals("strokerect")) {
				handleStrokeRect(childUIDL);
			} else if (command.equals("transform")) {
				handleTransform(childUIDL);
			} else {
				System.err.println("Invalid command: " + command);
			}
		}
	}

	private void handleAddColorStop(UIDL uidl) {
		String gradientName = uidl.getStringAttribute("gradient");
		if (!gradients.containsKey(gradientName)) {
			System.err
					.println("handleAddColorStop: Gradient not foud with name "
							+ gradientName);
			return;
		}

		double offset = uidl.getDoubleAttribute("offset");
		String color = uidl.getStringAttribute("color");
		gradients.get(gradientName).addColorStop(offset, new Color(color));
	}

	private void handleSetGlobalCompositeOperation(UIDL uidl) {
		String mode = uidl.getStringAttribute("mode");
		if (mode.equalsIgnoreCase("DESTINATION_OVER")) {
			canvas.setGlobalCompositeOperation(GWTCanvas.DESTINATION_OVER);
		} else if (mode.equalsIgnoreCase("SOURCE_OVER")) {
			canvas.setGlobalCompositeOperation(GWTCanvas.SOURCE_OVER);
		} else {
			System.err.println("Invalid composition mode: " + mode);
		}
	}

	private void handleSetGlobalAlpha(UIDL uidl) {
		double alpha = uidl.getDoubleAttribute("alpha");

		canvas.setGlobalAlpha(alpha);
	}

	private void handleCreateLinearGradient(UIDL uidl) {
		String name = uidl.getStringAttribute("name");
		double x0 = uidl.getDoubleAttribute("x0");
		double y0 = uidl.getDoubleAttribute("y0");
		double x1 = uidl.getDoubleAttribute("x1");
		double y1 = uidl.getDoubleAttribute("y1");

		CanvasGradient newGradient = canvas
				.createLinearGradient(x0, y0, x1, y1);

		gradients.put(name, newGradient);
	}

	private void handleCreateRadialGradient(UIDL uidl) {
		String name = uidl.getStringAttribute("name");
		double x0 = uidl.getDoubleAttribute("x0");
		double y0 = uidl.getDoubleAttribute("y0");
		double r0 = uidl.getDoubleAttribute("r0");
		double x1 = uidl.getDoubleAttribute("x1");
		double y1 = uidl.getDoubleAttribute("y1");
		double r1 = uidl.getDoubleAttribute("r1");

		CanvasGradient newGradient = canvas.createRadialGradient(x0, y0, r0,
				x1, y1, r1);

		gradients.put(name, newGradient);
	}

	private void handleCubicCurveTo(UIDL uidl) {
		double cp1x = uidl.getDoubleAttribute("cp1x");
		double cp1y = uidl.getDoubleAttribute("cp1y");
		double cp2x = uidl.getDoubleAttribute("cp2x");
		double cp2y = uidl.getDoubleAttribute("cp2y");
		double x = uidl.getDoubleAttribute("x");
		double y = uidl.getDoubleAttribute("y");

		canvas.cubicCurveTo(cp1x, cp1y, cp2x, cp2y, x, y);
	}

	private void handleDrawImage1(UIDL uidl) {
		String url = uidl.getStringAttribute("url");
		double offsetX = uidl.getDoubleAttribute("offsetX");
		double offsetY = uidl.getDoubleAttribute("offsetY");

		// Canvas.drawImage won't show the image unless it's already loaded
		Image.prefetch(url);

		Image image = new Image(url);

		canvas.drawImage(ImageElement.as(image.getElement()), offsetX, offsetY);
	}

	private void handleDrawImage2(UIDL uidl) {
		String url = uidl.getStringAttribute("url");
		double offsetX = uidl.getDoubleAttribute("offsetX");
		double offsetY = uidl.getDoubleAttribute("offsetY");
		double height = uidl.getDoubleAttribute("height");
		double width = uidl.getDoubleAttribute("width");

		// Canvas.drawImage won't show the image unless it's already loaded
		Image.prefetch(url);

		Image image = new Image(url);

		canvas.drawImage(ImageElement.as(image.getElement()), offsetX, offsetY,
				height, width);
	}

	private void handleDrawImage3(UIDL uidl) {
		String url = uidl.getStringAttribute("url");
		double sourceX = uidl.getDoubleAttribute("sourceX");
		double sourceY = uidl.getDoubleAttribute("sourceY");
		double sourceHeight = uidl.getDoubleAttribute("sourceHeight");
		double sourceWidth = uidl.getDoubleAttribute("sourceWidth");
		double destX = uidl.getDoubleAttribute("destX");
		double destY = uidl.getDoubleAttribute("destY");
		double destHeight = uidl.getDoubleAttribute("destHeight");
		double destWidth = uidl.getDoubleAttribute("destWidth");

		// Canvas.drawImage won't show the image unless it's already loaded
		Image.prefetch(url);

		Image image = new Image(url);

		canvas.drawImage(ImageElement.as(image.getElement()), sourceX, sourceY,
				sourceHeight, sourceWidth, destX, destY, destHeight, destWidth);
	}

	private void handleFillRect(UIDL uidl) {
		double startX = uidl.getDoubleAttribute("startX");
		double startY = uidl.getDoubleAttribute("startY");
		double width = uidl.getDoubleAttribute("width");
		double height = uidl.getDoubleAttribute("height");

		canvas.fillRect(startX, startY, width, height);
	}

	private void handleLineTo(UIDL uidl) {
		double x = uidl.getDoubleAttribute("x");
		double y = uidl.getDoubleAttribute("y");

		canvas.lineTo(x, y);
	}

	private void handleMoveTo(UIDL uidl) {
		double x = uidl.getDoubleAttribute("x");
		double y = uidl.getDoubleAttribute("y");

		canvas.moveTo(x, y);
	}

	private void handleQuadraticCurveTo(UIDL uidl) {
		double cpx = uidl.getDoubleAttribute("cpx");
		double cpy = uidl.getDoubleAttribute("cpy");
		double x = uidl.getDoubleAttribute("x");
		double y = uidl.getDoubleAttribute("y");

		canvas.quadraticCurveTo(cpx, cpy, x, y);
	}

	private void handleRect(UIDL uidl) {
		double startX = uidl.getDoubleAttribute("startX");
		double startY = uidl.getDoubleAttribute("startY");
		double width = uidl.getDoubleAttribute("width");
		double height = uidl.getDoubleAttribute("height");

		canvas.rect(startX, startY, width, height);
	}

	private void handleRotate(UIDL uidl) {
		double angle = uidl.getDoubleAttribute("angle");

		canvas.rotate(angle);
	}

	private void handleSetGradientFillStyle(UIDL uidl) {
		String gradientName = uidl.getStringAttribute("gradient");

		if (gradients.containsKey(gradientName)) {
			canvas.setFillStyle(gradients.get(gradientName));
		} else {
			System.out
					.println("handleSetGradientFillStyle: Gradient not foud with name "
							+ gradientName);
		}
	}

	private void handleSetFillStyle(UIDL uidl) {
		String color = uidl.getStringAttribute("color");

		if (color.equalsIgnoreCase("transparent")) {
			canvas.setFillStyle(GWTCanvas.TRANSPARENT);
		} else {
			canvas.setFillStyle(new Color(color));
		}
	}

	private void handleSetLineCap(UIDL uidl) {
		String lineCap = uidl.getStringAttribute("lineCap");

		canvas.setLineCap(lineCap);
	}

	private void handleSetLineJoin(UIDL uidl) {
		String lineJoin = uidl.getStringAttribute("lineJoin");

		canvas.setLineJoin(lineJoin);
	}

	private void handleSetLineWidth(UIDL uidl) {
		double width = uidl.getDoubleAttribute("width");

		canvas.setLineWidth(width);
	}

	private void handleSetMiterLimit(UIDL uidl) {
		double miterLimit = uidl.getDoubleAttribute("miterLimit");

		canvas.setMiterLimit(miterLimit);
	}

	private void handleSetGradientStrokeStyle(UIDL uidl) {
		String gradientName = uidl.getStringAttribute("gradient");

		if (gradients.containsKey(gradientName)) {
			canvas.setStrokeStyle(gradients.get(gradientName));
		} else {
			System.out
					.println("handleSetStrokeStyle: Gradient not found with name "
							+ gradientName);
		}
	}

	private void handleSetColorStrokeStyle(UIDL uidl) {
		String color = uidl.getStringAttribute("color");

		canvas.setStrokeStyle(new Color(color));
	}

	private void handleStrokeRect(UIDL uidl) {
		double startX = uidl.getDoubleAttribute("startX");
		double startY = uidl.getDoubleAttribute("startY");
		double width = uidl.getDoubleAttribute("width");
		double height = uidl.getDoubleAttribute("height");

		canvas.strokeRect(startX, startY, width, height);
	}

	private void handleTransform(UIDL uidl) {
		double m11 = uidl.getDoubleAttribute("m11");
		double m12 = uidl.getDoubleAttribute("m12");
		double m21 = uidl.getDoubleAttribute("m21");
		double m22 = uidl.getDoubleAttribute("m22");
		double dx = uidl.getDoubleAttribute("dx");
		double dy = uidl.getDoubleAttribute("dy");

		canvas.transform(m11, m12, m21, m22, dx, dy);
	}

	private void handleBackgroundColorCommand(UIDL uidl) {
		String rgb = uidl.getStringAttribute("rgb");

		canvas.setBackgroundColor(new Color(rgb));
	}

	private void handleStrokeColorCommand(UIDL uidl) {
		String rgb = uidl.getStringAttribute("rgb");

		canvas.setStrokeStyle(new Color(rgb));
	}

	private void handleArcCommand(UIDL uidl) {
		double x = uidl.getDoubleAttribute("x");
		double y = uidl.getDoubleAttribute("y");
		double radius = uidl.getDoubleAttribute("radius");
		double startAngle = uidl.getDoubleAttribute("startAngle");
		double endAngle = uidl.getDoubleAttribute("endAngle");
		boolean antiClockwise = uidl.getBooleanAttribute("antiClockwise");

		canvas.arc(x, y, radius, startAngle, endAngle, antiClockwise);
	}

	private void handleTranslateCommand(UIDL uidl) {
		double x = uidl.getDoubleAttribute("x");
		double y = uidl.getDoubleAttribute("y");

		canvas.translate(x, y);
	}

	private void handleScaleCommand(UIDL uidl) {
		double x = uidl.getDoubleAttribute("x");
		double y = uidl.getDoubleAttribute("y");

		canvas.scale(x, y);
	}

	@Override
	public void setWidth(String width) {
		try {
			int newWidth = Integer.parseInt(width.replace("px", ""));
			if (newWidth == widthCache) {
				return;
			}

			widthCache = newWidth;

		} catch (Exception ignored) {
			System.out.println("Width '" + width + "' not an integer");
			return;
		}

		super.setWidth(width);
		canvas.setWidth(width);

		// This will reset the contents
		canvas.setCoordWidth(widthCache);

		// Request a redraw
		if (client != null) {
			client.updateVariable(paintableId, "sizeChanged", "" + widthCache
					+ " x " + heightCache, true);
		}
	}

	@Override
	public void setHeight(String height) {

		try {
			int newHeight = Integer.parseInt(height.replace("px", ""));
			if (newHeight == heightCache) {
				return;
			}

			heightCache = newHeight;
		} catch (Exception ignored) {
			System.out.println("Height '" + height + "' not an integer");
			return;
		}

		super.setHeight(height);
		canvas.setHeight(height);

		// This will reset the contents
		canvas.setCoordHeight(heightCache);

		// Request redraw
		if (client != null) {
			client.updateVariable(paintableId, "sizeChanged", "" + widthCache
					+ " x " + heightCache, true);
		}
	}
}