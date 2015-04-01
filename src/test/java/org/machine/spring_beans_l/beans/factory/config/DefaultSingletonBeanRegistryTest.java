package org.machine.spring_beans_l.beans.factory.config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DefaultSingletonBeanRegistryTest {
	
	private DefaultSingletonBeanRegistry defaultSingletonBeanRegistry;
	
	class Entity {
		private int id;
		private String name;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}

	@Before
	public void setUp() throws Exception {
		this.defaultSingletonBeanRegistry = new DefaultSingletonBeanRegistry();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterSingleton() {
		Entity entity = new Entity();
		String name = "bean";		this.defaultSingletonBeanRegistry.registerSingleton(name, entity);
		Object obj = this.defaultSingletonBeanRegistry.getSingleton(name);
		assertEquals(obj, entity);
		// 这册空对象
		this.defaultSingletonBeanRegistry.registerSingleton(name+"1", null);
		obj = this.defaultSingletonBeanRegistry.getSingleton(name+"1");
		assertNull(obj);
		// 注册数量
		assertEquals(0x02, this.defaultSingletonBeanRegistry.getSingletonCount());
		// 再次注册
		try {
			this.defaultSingletonBeanRegistry.registerSingleton(name, entity);		
		} catch (Exception e) {
			return;
		}
		fail("不应执行到这里");
	}

}
