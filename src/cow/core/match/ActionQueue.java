package cow.core.match;

import java.util.LinkedList;
import java.util.Queue;

// Class just wraps a Queue 

public class ActionQueue {
	
	private Queue<MatchAction> actionQueue;
	
	public ActionQueue(){
		actionQueue = new LinkedList<MatchAction>();
	}
	
	public void push(MatchAction action){
		actionQueue.offer(action);
	}
	
	public MatchAction pop(){
		MatchAction action = actionQueue.poll();
		return action;
	}
	
	public boolean isEmpty(){
		return actionQueue.isEmpty();
	}
	
	public int getActionCount(){
		return actionQueue.size();
	}
	
}
