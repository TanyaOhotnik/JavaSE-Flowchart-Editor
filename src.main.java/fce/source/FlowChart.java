/**
 * 
 */
package fce.source;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Tanya Ohotnik
 *
 */
public class FlowChart extends CompositeBlock{

	private String flowChartName;
	/**
	 * Constructor without parameters
	 */
	public FlowChart(){
		
	}
	/**
	 * Set name of current Flow Chart
	 * @param flowChartName
	 */
    public FlowChart(String flowChartName){
		this.flowChartName = flowChartName;
	}
    /**
     * Method, which draw all diagram
     */
	public ArrayList<Shape> draw(){
//		System.out.println("Полная диаграмма:");
		return super.draw();
	}
	/**
	 * Set  name of current Flow Chart
	 * @param flowChartName
	 */
	public void setFlowChartName(String flowChartName) {
		this.flowChartName = flowChartName;
	}
	/**
	 * 
	 * @return name of current Flow Chart
	 */
	public String getFlowChartName() {
		return flowChartName;
	}
	public DiagrammComponent getElement(int i) {
		
		return elements.get(i);
	}
	public ArrayList<DiagrammComponent> getList(){
		return elements;
	}
//	public ArrayList<Figure> getSimpleElementsList(){
//		ArrayList<Figure> simpleElementsList = new ArrayList<Figure>(elements.size());
//		for(int i=0;i<elements.size();i++)
//			simpleElementsList.addAll(getFigure());
//		return simpleElementsList;
//	}
//	@Override
	public FlowChart clone(){
		FlowChart buf = new FlowChart();
		for(DiagrammComponent i:elements)
		buf.add(i.clone());
		return buf;
	}
	public void clear() {
		elements.clear();
	}
	
}
