package com.workflow.ivr.web;


import com.ivr.asterisk.AsteriskServer;
import com.ui.canvas.Canvas;
import com.ui.model.Block;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.SystemError;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class WorkflowDesigner extends CustomComponent {

	@AutoGenerated
	private AbsoluteLayout mainLayout;
	@AutoGenerated
	private Panel panel_2;
	@AutoGenerated
	private VerticalLayout verticalLayout_2;
	@AutoGenerated
	private HorizontalSplitPanel hSplitPanel;
	@AutoGenerated
	private Canvas canvas;
	@AutoGenerated
	private Panel panel_1;
	@AutoGenerated
	private VerticalLayout verticalLayout_1;
	@AutoGenerated
	private GridLayout gridLayout_1;
	@AutoGenerated
	private MenuBar menuBar;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public WorkflowDesigner() {
		buildMainLayout();
		setCompositionRoot(mainLayout);

		// TODO add user code here
		hSplitPanel.setSplitPosition(20);
		
		MenuBar.MenuItem file = menuBar.addItem("File", null);
		file.setDescription("File menu");

        MenuBar.MenuItem newItem = file.addItem("New", null);
        newItem.setDescription("Create new Workflow");
        
        MenuBar.MenuItem workflowListItem = file.addItem("Workflows", null);
        newItem.setDescription("List of workflows");
        
        MenuBar.MenuItem testWorkflow = workflowListItem.addItem("TestWorkflow", null);
        testWorkflow.setDescription("This is a test workflow");
		
        createWorkflowMenu(testWorkflow);
		
		MenuBar.MenuItem server = menuBar.addItem("Server", null);
		final MenuBar.MenuItem startItem = server.addItem("Start", null);
		startItem.setEnabled(!AsteriskServer.getStatus());
		
		final MenuBar.MenuItem stopItem = server.addItem("Stop", null);
		stopItem.setEnabled(AsteriskServer.getStatus());
		
		startItem.setCommand(new MenuBar.Command() {
			
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				try {
					selectedItem.setEnabled(false);
					AsteriskServer.start();
					stopItem.setEnabled(true);
				} catch (Exception e) {
					menuBar.setComponentError(new SystemError(e));
					selectedItem.setEnabled(true);
				}
			}
		});
		
		stopItem.setCommand(new MenuBar.Command() {
			
			public void menuSelected(com.vaadin.ui.MenuBar.MenuItem selectedItem) {
				try {
					selectedItem.setEnabled(false);
					AsteriskServer.stop();
					startItem.setEnabled(true);
				} catch (Exception e) {
					menuBar.setComponentError(new SystemError(e));
					selectedItem.setEnabled(true);
				}
			}
		});
		
		
		MenuBar.MenuItem help = menuBar.addItem("Help", null);
		help.addItem("About", new MenuBar.Command() {
			
			public void menuSelected(MenuItem selectedItem) {
				Window aboutBox = new Window("About IVR Designer", new About());
				aboutBox.setModal(true);
				getApplication().getMainWindow().addWindow(aboutBox);
			}
		});
		
		canvas.addListener(new Canvas.CanvasMouseUpListener() {

			public void mouseUp(int x, int y) {
				getWindow().showNotification(
						"Mouse released at (" + x + "," + y + ")");
			}
		});
		
		canvas.addListener(new Canvas.CanvasMouseDownListener() {
			
			public void mouseDown(int x, int y) {
				getWindow().showNotification(
						"Mouse pressed at (" + x + "," + y + ")");
			}
		});

		canvas.saveContext();
		canvas.clear();
//		canvas.translate(0, 0);
		canvas.setStrokeColor("#5285b1");
		canvas.setFillStyle(158,192,222);
		canvas.setLineWidth(3);
		
	/*
		canvas.scale(1.6f, 1.6f);
		for (int i = 1; i < 6; ++i) {
			canvas.saveContext();
			canvas.setFillStyle("rgb(" + (51 * i) + "," + (255 - 51 * i)
					+ ",255)");

			for (int j = 0; j < i * 6; ++j) {
				canvas.rotate((float) (Math.PI * 2 / (i * 6)));
				canvas.beginPath();
				canvas.arc(0, i * 12.5f, 5, 0, (float) (Math.PI*2), true);
				canvas.stroke();
				canvas.fill();
			}

			canvas.restoreContext();
		}

		canvas.restoreContext();
		*/
		
		drawStep();
		canvas.restoreContext();
		//Custom code end
	}
	
	private void drawStep(){
		Block block = new Block(canvas, 100, 200, 300, 400);
		block.draw();
//		block = new Block(canvas, 450, 550, 425, 525);
//		block.draw();
//		block = new Block(canvas, 600, 600, 700, 700);
//		block.draw();
		canvas.fill();
	}
	
	private void createWorkflowMenu(MenuBar.MenuItem menuItem){
		MenuBar.MenuItem openItem = menuItem.addItem("Open", null);
		MenuBar.MenuItem activateItem = menuItem.addItem("Activate", null);
		MenuBar.MenuItem deactivateItem = menuItem.addItem("Deactivate", null);
		MenuBar.MenuItem deleteItem = menuItem.addItem("Delete", null);
	}

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// panel_2
		panel_2 = buildPanel_2();
		mainLayout.addComponent(panel_2, "top:0.0px;left:0.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private Panel buildPanel_2() {
		// common part: create layout
		panel_2 = new Panel();
		panel_2.setImmediate(false);
		panel_2.setWidth("100.0%");
		panel_2.setHeight("100.0%");
		
		// verticalLayout_2
		verticalLayout_2 = buildVerticalLayout_2();
		panel_2.setContent(verticalLayout_2);
		
		return panel_2;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_2() {
		// common part: create layout
		verticalLayout_2 = new VerticalLayout();
		verticalLayout_2.setImmediate(false);
		verticalLayout_2.setWidth("100.0%");
		verticalLayout_2.setHeight("100.0%");
		verticalLayout_2.setMargin(false);
		
		// menuBar
		menuBar = new MenuBar();
		menuBar.setImmediate(false);
		menuBar.setWidth("100.0%");
		menuBar.setHeight("-1px");
		verticalLayout_2.addComponent(menuBar);
		
		// hSplitPanel
		hSplitPanel = buildHSplitPanel();
		verticalLayout_2.addComponent(hSplitPanel);
		verticalLayout_2.setExpandRatio(hSplitPanel, 1.0f);
		
		return verticalLayout_2;
	}

	@AutoGenerated
	private HorizontalSplitPanel buildHSplitPanel() {
		// common part: create layout
		hSplitPanel = new HorizontalSplitPanel();
		hSplitPanel.setImmediate(false);
		hSplitPanel.setWidth("100.0%");
		hSplitPanel.setHeight("100.0%");
		hSplitPanel.setMargin(false);
		
		// panel_1
		panel_1 = buildPanel_1();
		hSplitPanel.addComponent(panel_1);
		
		// canvas
		canvas = new Canvas();
		canvas.setImmediate(false);
		canvas.setDescription("Draw your workflow here");
		canvas.setWidth("100.0%");
		canvas.setHeight("100.0%");
		hSplitPanel.addComponent(canvas);
		
		return hSplitPanel;
	}

	@AutoGenerated
	private Panel buildPanel_1() {
		// common part: create layout
		panel_1 = new Panel();
		panel_1.setImmediate(false);
		panel_1.setWidth("100.0%");
		panel_1.setHeight("100.0%");
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		panel_1.setContent(verticalLayout_1);
		
		return panel_1;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("100.0%");
		verticalLayout_1.setHeight("100.0%");
		verticalLayout_1.setMargin(false);
		
		// gridLayout_1
		gridLayout_1 = new GridLayout();
		gridLayout_1.setImmediate(false);
		gridLayout_1.setWidth("100.0%");
		gridLayout_1.setHeight("100.0%");
		gridLayout_1.setMargin(false);
		gridLayout_1.setColumns(3);
		verticalLayout_1.addComponent(gridLayout_1);
		verticalLayout_1.setExpandRatio(gridLayout_1, 1.0f);
		verticalLayout_1.setComponentAlignment(gridLayout_1, new Alignment(48));
		
		return verticalLayout_1;
	}

}
