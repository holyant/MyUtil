package test.model;

public class DwWoPlusKey4bSaleDtlBean {
//	private String isOld;
	private String svcNum;
	private String prodId;
	private String acceptTime;
	private String chnlType;
	private String agent1;
	private String agent2;
	private String dataSrc;
	private String etlDateCycle;
	private String eltTime;
	
	
	public DwWoPlusKey4bSaleDtlBean() {
		super();
	}
	public DwWoPlusKey4bSaleDtlBean(String svcNum, String prodId,
			String acceptTime, String chnlType, String agent1, String agent2,
			String dataSrc) {
		super();
		this.svcNum = svcNum;
		this.prodId = prodId;
		this.acceptTime = acceptTime;
		this.chnlType = chnlType;
		this.agent1 = agent1;
		this.agent2 = agent2;
		this.dataSrc = dataSrc;
		this.etlDateCycle = etlDateCycle;
		this.eltTime = eltTime;
	}
	public String getDataSrc() {
		return dataSrc;
	}
	public void setDataSrc(String dataSrc) {
		this.dataSrc = dataSrc;
	}
	//	public String getIsOld() {
//		return isOld;
//	}
//	public void setIsOld(String isOld) {
//		this.isOld = isOld;
//	}
	public String getSvcNum() {
		return svcNum;
	}
	public void setSvcNum(String svcNum) {
		this.svcNum = svcNum;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getChnlType() {
		return chnlType;
	}
	public void setChnlType(String chnlType) {
		this.chnlType = chnlType;
	}
	public String getAgent1() {
		return agent1;
	}
	public void setAgent1(String agent1) {
		this.agent1 = agent1;
	}
	public String getAgent2() {
		return agent2;
	}
	public void setAgent2(String agent2) {
		this.agent2 = agent2;
	}
	public String getEtlDateCycle() {
		return etlDateCycle;
	}
	public void setEtlDateCycle(String etlDateCycle) {
		this.etlDateCycle = etlDateCycle;
	}
	public String getEltTime() {
		return eltTime;
	}
	public void setEltTime(String eltTime) {
		this.eltTime = eltTime;
	}
	
}
