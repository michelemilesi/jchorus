package org.jchorus.db;

import static org.junit.Assert.*;
import nl.renarj.jasdb.LocalDBSession;
import nl.renarj.jasdb.api.DBSession;
import nl.renarj.jasdb.api.SimpleEntity;
import nl.renarj.jasdb.api.metadata.Instance;
import nl.renarj.jasdb.api.model.EntityBag;
import nl.renarj.jasdb.core.SimpleKernel;
import nl.renarj.jasdb.core.exceptions.ConfigurationException;
import nl.renarj.jasdb.core.exceptions.JasDBException;
import nl.renarj.jasdb.core.exceptions.JasDBStorageException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JasDbTest {

	@BeforeClass
	public static void setUpClass() throws ConfigurationException {
		SimpleKernel.initializeKernel();
	}
	
	@Test
	public void test() throws JasDBStorageException {
		DBSession session = new LocalDBSession();
		session.addAndSwitchInstance("test", "/test");
		for (Instance instance : session.getInstances()) {
			System.out.println(instance.getInstanceId() + " - " + instance.getPath());
		}
		EntityBag bag = session.createOrGetBag("TestBag");
		SimpleEntity entity = new SimpleEntity();
		entity.addProperty("title", "Title of my content");
		entity.addProperty("text", "Some big piece of text content");
		bag.addEntity(entity);
//		session.deleteInstance("test");
		session.closeSession();
	}
	
	@AfterClass
	public static void tearDownClass() throws JasDBException {
		SimpleKernel.shutdown();
	}

}
