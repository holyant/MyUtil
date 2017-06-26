package db2Test.entity;
/**
 * java连接数据库所需信息
 * driver,url,user,password
 * @author fangyh
 *
 */
public class DbInfo {
	private String driver;//数据库驱动类
	private String url;//数据库连接字符串
	private String user;//数据库用户
	private String password;//数据库密码
	
	public DbInfo() {
		super();
	}
	public DbInfo(String driver, String url, String user, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}









