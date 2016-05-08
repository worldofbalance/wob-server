
package cvg.match;

import java.util.ArrayList;
import java.util.List;

public class MCMatchAction {

	private short actionID;
	private int intCount;
	private int stringCount;
	private List<Integer> intList;
	private List<String> stringList;
	
	public MCMatchAction(){
		this.intList = new ArrayList<Integer>();
		this.stringList = new ArrayList<String>();
	}
	
	public MCMatchAction(short actionID, int intCount, 
			List<Integer> intList, List<String> stringList)
	{
		this.actionID = actionID;
		this.intCount = intCount;
		this.intList = intList;
		this.stringList = stringList;
	}
	
	public void addInt(int item){
		this.intList.add(item);
	}
	
	public void addString(String string){
		this.stringList.add(string);
	}
	
	/**
	 * @return the actionID
	 */
	public short getActionID() {
		return actionID;
	}

	/**
	 * @return the intCount
	 */
	public int getIntCount() {
		return intCount;
	}
	public int getStringCount(){
		return stringCount;
	}
	/**
	 * @return the data
	 */
	public List<Integer> getIntList() {
		return intList;
	}
	public List<String> getStringList(){
		return stringList;
	}
	
	/**
	 * @param actionID the actionID to set
	 */
	public void setActionID(short actionID) {
		this.actionID = actionID;
	}

	/**
	 * @param intCount the intCount to set
	 */
	public void setIntCount(int intCount) {
		this.intCount = intCount;
	}
	
	public void setStringCount(int stringCount){
		this.stringCount = stringCount;
	}
	
	/**
	 * @param data the data to set
	 */
	public void setIntList(List<Integer> intList) {
		this.intList = intList;
	}
	
}
