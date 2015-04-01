package org.machine.spring_beans_l.beans.factory.config;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.machine.spring_beans_l.core.SimpleAliasRegistry;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class DefaultSingletonBeanRegistry extends SimpleAliasRegistry implements
		SingletonBeanRegistry {

	/** */
	private final Object NULL_OBJ = new Object();

	/** */
	private final Set<String> registerSingletons = new LinkedHashSet<String>(64);

	/** */
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(
			64);

	@Override
	public void registerSingleton(String beanName, Object singletonObject) {
		Assert.notNull(beanName, "'beanName' must not be null.");
		synchronized (this.singletonObjects) {
			Object oldObject = this.singletonObjects.get(beanName);
			if (oldObject != null) {
				throw new IllegalStateException("Could not register ["
						+ singletonObject + "] under bean name '" + beanName
						+ "': there is alreedy object [" + oldObject
						+ "] bound.");
			}
			this.singletonObjects.put(beanName,
					singletonObject != null ? singletonObject : NULL_OBJ);
			this.registerSingletons.add(beanName);
		}
	}

	@Override
	public Object getSingleton(String beanName) {
		Object obj = this.singletonObjects.get(beanName);
		return obj != NULL_OBJ ? obj : null;
	}

	@Override
	public boolean containsSingleton(String beanName) {
			return this.singletonObjects.containsKey(beanName);
	}

	@Override
	public String[] getSingletonNames() {
		synchronized (this.registerSingletons) {
			return StringUtils.toStringArray(this.registerSingletons);
		}
	}

	@Override
	public int getSingletonCount() {
		synchronized (this.registerSingletons) {
			return this.registerSingletons.size();
		}
	}

}
