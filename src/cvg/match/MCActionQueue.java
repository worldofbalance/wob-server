package cvg.match;

import java.util.LinkedList;
import java.util.Queue;

// Class just wraps a Queue 

public class MCActionQueue {
	
	private Queue<MCMatchAction> actionQueue;
	
	public MCActionQueue(){
		actionQueue = new LinkedList<MCMatchAction>();
	}
	
	public void push(MCMatchAction action){
		actionQueue.offer(action);
	}
	
	public MCMatchAction pop(){
		MCMatchAction action = actionQueue.poll();
		return action;
	}
	
	public boolean isEmpty(){
		return actionQueue.isEmpty();
	}
	
	public int getActionCount(){
		return actionQueue.size();
	}
	
}
