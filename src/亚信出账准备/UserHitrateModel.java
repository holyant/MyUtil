package 亚信出账准备;

public class UserHitrateModel {
	private int rowNum;
	private String userId1;
	private String userId2;
	private String cityName;
	private String userName;
	private String hitCount;
	
	public UserHitrateModel() {
		super();
	}
	public UserHitrateModel(int rowNum, String userId1, String userId2,
			String cityName, String userName, String hitCount) {
		super();
		this.rowNum = rowNum;
		this.userId1 = userId1;
		this.userId2 = userId2;
		this.cityName = cityName;
		this.userName = userName;
		this.hitCount = hitCount;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public String getUserId1() {
		return userId1;
	}
	public void setUserId1(String userId1) {
		this.userId1 = userId1;
	}
	public String getUserId2() {
		return userId2;
	}
	public void setUserId2(String userId2) {
		this.userId2 = userId2;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getHitCount() {
		return hitCount;
	}
	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}
	@Override
	public String toString() {
		return super.toString()+";rowNum:"+rowNum+",userId1:"+userId1+",userId2:"+userId2+",cityName:"+cityName+",hitCount:"+hitCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
