package com.holyant.activityRecords.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.holyant.activityRecords.dao.HelloDao;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HelloDaoImpl implements HelloDao{
	private JdbcTemplate jdbcTemplate;
	private SqlMapClient sqlMapClient;

	public void setDataSource(DataSource dataSource){
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void deleteEmp(int empno) throws SQLException {
//		String sql = "delete from emp where empno = ?";
//		jdbcTemplate.update(sql, new Object[]{empno});
		this.sqlMapClient.insert("ibatisTest.test");
		this.sqlMapClient.insert("ibatisTest.test");
	}

	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void update() throws SQLException {
			this.sqlMapClient.update("ibatisTest.update1");
	}
	
}
