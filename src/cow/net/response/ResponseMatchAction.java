package cow.net.response;

import java.util.ArrayList;
import java.util.List;

import shared.util.GamePacket;
import shared.util.Log;
import shared.metadata.NetworkCode;
import lby.net.response.GameResponse;

public class ResponseMatchAction extends GameResponse {
	private short code;
	private int intCount;
	private int stringCount;
	private List<Integer> intList = null;
	private List<String> stringList = null;
	
	public ResponseMatchAction() {
		response_id = NetworkCode.MATCH_ACTION;
		intList = new ArrayList<Integer>();
		stringList = new ArrayList<String>();
		intCount = 0;
		stringCount = 0;
	}
	
	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		GamePacket packet = new GamePacket(response_id);
		packet.addShort16(code);
		packet.addInt32(intCount);
		packet.addInt32(stringCount);
		for (int i = 0; i < intCount; i++){
			packet.addInt32(intList.get(i));
		}
		for(int i = 0; i < stringCount; i++){
			//Log.printf("added string: %s", stringList.get(i));
			packet.addString(stringList.get(i));
		}
		return packet.getBytes();
	}

	/**
	 * @return the code
	 */
	public short getCode() {
		return code;
	}

	/**
	 * @return the listSize
	 */
	public int getIntCount() {
		return intCount;
	}

	/**
	 * @return the data
	 */
	public List<Integer> getIntList() {
		return intList;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(short code) {
		this.code = code;
	}

	/**
	 * @param listSize the listSize to set
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
	
	/**
	 * 
	 * @param stringList
	 */
	public void setStringList(List<String> stringList){
		this.stringList = stringList;
	}

}
