package com.workflow.ivr.web;

import com.vaadin.Application;
import com.vaadin.ui.*;

public class IvrApplication extends Application {
	private WorkflowDesigner designer;
	
	@Override
	public void init() {
		Window mainWindow = new Window("Ivr Application");
		designer = new WorkflowDesigner();
		designer.setSizeFull();
		mainWindow.addComponent(designer);
		mainWindow.getContent().setSizeFull();
		setMainWindow(mainWindow);
	}

}
