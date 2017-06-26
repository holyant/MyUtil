package 亚信出账准备;

public class MenuHitrateModel {
	private int rowNum;
	private String menuItemIdA;
	private String title;
	private String menuItemIdB;
	private int cnt;
	private boolean flag = true;
	
	public MenuHitrateModel() {
		super();
	}
	
	public MenuHitrateModel(int rowNum, String menuItemIdA, String title,
			String menuItemIdB, int cnt) {
		super();
		this.rowNum = rowNum;
		this.menuItemIdA = menuItemIdA;
		this.title = title;
		this.menuItemIdB = menuItemIdB;
		this.cnt = cnt;
	}

	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getMenuItemIdA() {
		return menuItemIdA;
	}
	public void setMenuItemIdA(String menuItemIdA) {
		this.menuItemIdA = menuItemIdA;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMenuItemIdB() {
		return menuItemIdB;
	}
	public void setMenuItemIdB(String menuItemIdB) {
		this.menuItemIdB = menuItemIdB;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void cleanTitle() {
		for(int i=0;i<this.title.length();i++){
			if(this.title.charAt(i)!='-'){
				this.title = this.title.substring(i);
				break;
			}
		}
	}
	
	
}
