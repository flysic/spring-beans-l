package org.machine.spring_beans_l.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.AliasRegistry;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class MySimpleAliasRegistry implements AliasRegistry {

	private Map<String, String> aliasMap;

	public MySimpleAliasRegistry() {
		this.aliasMap = new ConcurrentHashMap<String, String>(16);
	}

	@Override
	public void registerAlias(String name, String alias) {
		Assert.hasText(name, "name must not empty");
		Assert.hasText(alias, "alias must not empty");
		if (name.equals(alias)) {
			return;
		}
		checkCircle(name, alias);
		this.aliasMap.put(alias, name);
	}

	private void checkCircle(String name, String alias) {
		String alias1;
		do {
			alias1 = this.aliasMap.get(name);
			if (alias1 != null) {
				name = alias1;
				if (alias1.equals(alias)) {
					throw new IllegalStateException(
							"cannot register bean '"
									+ alias
									+ "' because it is direct or indirect circle register.");
				}
			}
		} while (alias1 != null);
	}

	@Override
	public void removeAlias(String alias) {
		String name = this.aliasMap.get(alias);
		if (name == null) {
			return;
		}
		this.aliasMap.remove(alias);
	}

	@Override
	public boolean isAlias(String beanName) {
		String alias = this.aliasMap.get(beanName);
		if (alias == null) {
			return false;
		}
		return true;
	}

	@Override
	public String[] getAliases(String name) {
		List<String> aliases = new ArrayList<String>();
		synchronized (this.aliasMap) {
			circleAliases(name, aliases);	
		}
		return StringUtils.toStringArray(aliases);
	}

	private void circleAliases(String name, List<String> aliases) {
		String name1, alias;
		for (Map.Entry<String, String> entry : this.aliasMap.entrySet()) {
			alias = entry.getKey();
			name1 = this.aliasMap.get(alias);
			if (name1.equals(name)) {
				aliases.add(alias);
				circleAliases(alias, aliases);
			}
		}
	}

}
