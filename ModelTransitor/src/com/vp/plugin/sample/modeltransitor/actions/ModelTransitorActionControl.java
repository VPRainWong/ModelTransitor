package com.vp.plugin.sample.modeltransitor.actions;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IBusinessProcessDiagramUIModel;
import com.vp.plugin.diagram.IDiagramTypeConstants;
import com.vp.plugin.diagram.IUseCaseDiagramUIModel;
import com.vp.plugin.diagram.shape.IBPTaskUIModel;
import com.vp.plugin.diagram.shape.IUseCaseUIModel;
import com.vp.plugin.model.IBPTask;
import com.vp.plugin.model.IModel;
import com.vp.plugin.model.IUseCase;
import com.vp.plugin.model.factory.IModelElementFactory;
import com.vp.plugin.model.property.ITransitProperty;

public class ModelTransitorActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// Retrieve DiagramManager
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		
		// Create Use Case Diagram with a use case
		IUseCaseDiagramUIModel useCaseDiagram = (IUseCaseDiagramUIModel) diagramManager.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_USE_CASE_DIAGRAM);		
		IUseCase usecase = IModelElementFactory.instance().createUseCase();
		usecase.setName("My Use Case");		
		IUseCaseUIModel usecaseShape = (IUseCaseUIModel) diagramManager.createDiagramElement(useCaseDiagram, usecase);
		usecaseShape.setBounds(100, 100, 80, 40);
		usecaseShape.setRequestResetCaption(true);
		
		// Create Business Process Diagram with a task
		IBusinessProcessDiagramUIModel bpdiagram = (IBusinessProcessDiagramUIModel) diagramManager.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_BUSINESS_PROCESS_DIAGRAM);		
		IBPTask task = IModelElementFactory.instance().createBPTask();
		task.setName("My Task");
		IBPTaskUIModel taskShape = (IBPTaskUIModel) diagramManager.createDiagramElement(bpdiagram, task);
		taskShape.setBounds(100, 100, 80, 40);
		taskShape.setRequestResetCaption(true);
		
		// Establish transitor relationship from use case to task
		ITransitProperty transitProp = (ITransitProperty) usecase.getModelPropertyByName(IModel.PROP_TRANSIT_TO);
		transitProp.addValue(task);
		
		// Show up the diagrams
		diagramManager.openDiagram(bpdiagram);
		diagramManager.openDiagram(useCaseDiagram);
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
