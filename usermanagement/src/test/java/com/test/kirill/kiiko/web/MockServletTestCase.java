package com.kirill.kiiko.web;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import com.kirill.kiiko.db.DaoFactory;
import com.kirill.kiiko.db.MockDaoFactory;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(properties);
		setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	}

	@After
	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}

	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

}
