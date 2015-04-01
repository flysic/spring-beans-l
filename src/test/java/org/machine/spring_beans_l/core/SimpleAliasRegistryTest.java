package org.machine.spring_beans_l.core;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.machine.spring_beans_l.core.SimpleAliasRegistry;
import org.springframework.core.AliasRegistry;

public class SimpleAliasRegistryTest {
	
	private AliasRegistry registry;
	private String ORI_SERVICE = "userService"; 

	@Before
	public void setUp() throws Exception {
		registry = new SimpleAliasRegistry();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * 测试加入别名
	 */
	public void testRegister() {
		Assert.assertEquals(0x00, registry.getAliases(ORI_SERVICE).length);
		// 别名和bean name不可同名
		registry.registerAlias(ORI_SERVICE, ORI_SERVICE);
		Assert.assertEquals(0x00, registry.getAliases(ORI_SERVICE).length);
		registry.registerAlias(ORI_SERVICE, "alias1");
		Assert.assertEquals(0x01, registry.getAliases(ORI_SERVICE).length);
		registry.registerAlias(ORI_SERVICE, "alias2");
		Assert.assertEquals(0x02, registry.getAliases(ORI_SERVICE).length);
		Assert.assertEquals(true, registry.isAlias("alias1"));
		Assert.assertEquals(false, registry.isAlias(ORI_SERVICE));
	}
	
	@Test
	/**
	 * 测试循环别名
	 */
	public void testCircleALias() { 
		Assert.assertEquals(0x00, registry.getAliases(ORI_SERVICE).length);
		registry.registerAlias(ORI_SERVICE, "alias1");
		registry.registerAlias("alias1", "alias2");
		Assert.assertEquals(0x02, registry.getAliases(ORI_SERVICE).length);
		// 别名不可循环
		try {
			registry.registerAlias("alias2", ORI_SERVICE);
		} catch (Exception e) {
			return;
		}
		Assert.fail("不应执行到这里");
	}
	
	@Test
	/**
	 * 删除别名
	 */
	public void testRemoveAlias() {
		Assert.assertEquals(0x00, registry.getAliases(ORI_SERVICE).length);
		registry.registerAlias(ORI_SERVICE, "alias1");
		registry.registerAlias(ORI_SERVICE, "alias2");
		// 删除别名 
		registry.removeAlias("alias2");
		Assert.assertEquals(0x01, registry.getAliases(ORI_SERVICE).length);
		registry.removeAlias("alias1");
		Assert.assertEquals(0x00, registry.getAliases(ORI_SERVICE).length);
	}

}
