package com.holyant.activityRecords.service.impl;

import java.sql.SQLException;

import com.holyant.activityRecords.dao.HelloDao;
import com.holyant.activityRecords.service.HelloService;

public class HelloServiceImpl implements HelloService{
	private HelloDao helloDao;
	public void deleteEmp(int empno) throws SQLException{
		this.helloDao.deleteEmp(empno);
	}
	public void setHelloDao(HelloDao helloDao) {
		this.helloDao = helloDao;
	}
	public void update() throws SQLException {
		//1
		this.helloDao.deleteEmp(1);
		//2
//		this.helloDao.deleteEmp(2);
//		//3
//		this.helloDao.deleteEmp(3);
//		//4
//		this.helloDao.update();
//		//5
//		this.helloDao.deleteEmp(4);
	}

}
