package fr.blackbalrog.gestionnaire.yaml;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConfigurationSection
{
	private final Map<String, Object> data;

	public ConfigurationSection()
	{
		this.data = new HashMap<>();
	}

	public ConfigurationSection(Map<String, Object> data)
	{
		this.data = data;
	}

	public void set(String key, Object value)
	{
		this.data.put(key, value);
	}

	public Object get(String key)
	{
		return this.data.get(key);
	}

	public String getString(String key)
	{
		Object value = this.get(key);
		return (value instanceof String) ? (String) value : null;
	}

	public int getInt(String key)
	{
		Object value = this.get(key);
		return (value instanceof Integer) ? (Integer) value : 0; // Retourne 0 si la valeur n'est pas un entier
	}

	public boolean getBoolean(String key)
	{
		Object value = this.get(key);
		return (value instanceof Boolean) ? (Boolean) value : false; // Retourne false par défaut
	}

	@SuppressWarnings("unchecked")
	public ConfigurationSection getConfigurationSection(String key)
	{
		Object value = this.get(key);
		if (value instanceof Map)
		{
			return new ConfigurationSection((Map<String, Object>) value);
		}
		return null; // Retourne null si ce n'est pas une section de configuration
	}

	public Set<String> getKeys(boolean deep)
	{
		return this.data.keySet();
	}

	public Map<String, Object> getValues(boolean deep)
	{
		return this.data;
	}
}
