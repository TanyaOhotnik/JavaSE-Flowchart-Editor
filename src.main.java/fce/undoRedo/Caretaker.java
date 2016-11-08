package fce.undoRedo;

import java.util.ArrayList;

import fce.source.FlowChart;

public class Caretaker {

	public ArrayList<Memento> states;
	public int currentState = 0;

	public Caretaker() {
		states = new ArrayList<Memento>(5);
		states.add(new Memento(new FlowChart()));
	}

//	public int getCurrentState() {
//		return currentState;
//	}

//	public int getStateIndex(Memento m) {
//		return states.indexOf(m);
//	}

	public void add(Memento state) {
		currentState++;
//		currentState = states.size();
		for(int i = states.size()-1;i>=currentState;i--)
			states.remove(i);
		states.add(state);
		
	}

//	public Memento get(int index) {
//		return states.get(index);
//	}

	public FlowChart getUndoFlowChart() {
		states.get(0).getState().clear();
		if (currentState > 0) {
			currentState--;
			return states.get(currentState).getState();
		} else
			return states.get(0).getState();

	}
	public FlowChart getRedoFlowChart() {
		if (currentState < states.size()-1) {
			currentState++;
			return states.get(currentState).getState();
		} else {
//			currentState = states.size()-1;
			return states.get(states.size()-1).getState();
			}
	}
}
