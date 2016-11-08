package fce.undoRedo;


import fce.source.FlowChart;

public class Memento {
	
	final FlowChart state;
	
	public Memento(FlowChart state) {
		this.state = state;
	}
	public FlowChart getState(){
		return state;
	}
}
