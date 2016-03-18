package shared.atn.test;

import java.util.HashMap;

import shared.model.ZoneNodes;

import shared.simulation.SpeciesZoneType;
import shared.simulation.config.ManipulationActionType;

public class ATNResultModel {
	private String nodeConfig;
	private ZoneNodes zoneNodes;
	private ManipulationActionType manipulationActionType;
	private String title;
	
	public String getNodeConfig() {
		return nodeConfig;
	}
	public void setNodeConfig(String nodeConfig) {
		this.nodeConfig = nodeConfig;
	}
	public ZoneNodes getZoneNodes() {
		return zoneNodes;
	}
	public void setZoneNodes(ZoneNodes zoneNodes) {
		this.zoneNodes = zoneNodes;
	}
	public void setMainpulationActionType(ManipulationActionType type) {
		this.manipulationActionType = type;
	}
	public ManipulationActionType getManipulationActionType() {
		return manipulationActionType;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}

}
