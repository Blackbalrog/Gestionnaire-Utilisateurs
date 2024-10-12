package fr.blackbalrog.gestionnaire.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class YamlConfiguration
{
	private Yaml yaml;
	private Map<String, Object> configuration;
	private File file;

	public YamlConfiguration(File file)
	{
		DumperOptions options = new DumperOptions();
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

		this.yaml = new Yaml(options);
		this.file = file;

		// Utilisation de FileInputStream pour charger le fichier local
		try (FileInputStream inputStream = new FileInputStream(file))
		{
			this.configuration = yaml.load(inputStream);
			if (this.configuration == null)
			{
				this.configuration = new HashMap<>();
			}
		}
		catch (Exception exception)
		{
			this.configuration = new HashMap<>();
		}
	}

	public void set(String key, Object value)
	{
		// Séparer les clés par le point (.)
		String[] keys = key.split("\\.");
		Map<String, Object> currentMap = this.configuration;

		// Parcourir les clés imbriquées et créer les sous-maps
		for (int i = 0; i < keys.length - 1; i++)
		{
			String currentKey = keys[i];
			if (!currentMap.containsKey(currentKey) || !(currentMap.get(currentKey) instanceof Map))
			{
				currentMap.put(currentKey, new HashMap<String, Object>());
			}
			// Aller dans la sous-map
			currentMap = (Map<String, Object>) currentMap.get(currentKey);
		}

		// Ajout la valeur dans la dernière clé
		currentMap.put(keys[keys.length - 1], value);
		this.save();
	}

	private Object getValue(String key)
	{
		String[] keys = key.split("\\.");
		Map<String, Object> currentMap = this.configuration;

		// Parcourir les sous-maps pour trouver la clé
		for (int i = 0; i < keys.length - 1; i++)
		{
			String currentKey = keys[i];
			if (currentMap.containsKey(currentKey) && currentMap.get(currentKey) instanceof Map)
			{
				currentMap = (Map<String, Object>) currentMap.get(currentKey);
			}
			else
			{
				return null; // Retourne null si une partie de la hiérarchie n'existe pas
			}
		}

		return currentMap.get(keys[keys.length - 1]);
	}

	public int getInt(String key)
	{
		return this.getValue(key) != null ? Integer.parseInt(this.getValue(key).toString()) : 0;
	}

	// Méthode pour obtenir une chaîne de caractères
	public String getString(String key)
	{
		return this.getValue(key) != null ? this.getValue(key).toString() : "";
	}

	private void save()
	{
		try (FileWriter writer = new FileWriter(this.file))
		{
			this.yaml.dump(this.configuration, writer);
		}
		catch (Exception exception)
		{

		}
	}

	public Map<String, Object> getMap()
	{
		return this.configuration;
	}

	public Yaml getYaml()
	{
		return this.yaml;
	}

	public File getFile()
	{
		return this.file;
	}

	public String getKey(String key)
	{
		String[] keys = key.split("\\.");
		return keys[0];
	}

	public Set<String> getKeys(boolean deep)
	{
		return deep ? getDeepKeys("", this.configuration) : this.configuration.keySet();
	}

	// Méthode auxiliaire pour parcourir toutes les sous-clés
	private Set<String> getDeepKeys(String parentKey, Map<String, Object> currentMap)
	{
		Set<String> keys = new HashSet<>();

		for (Map.Entry<String, Object> entry : currentMap.entrySet())
		{
			String key = parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey();
			keys.add(key);

			if (entry.getValue() instanceof Map)
			{
				keys.addAll(getDeepKeys(key, (Map<String, Object>) entry.getValue()));
			}
		}
		return keys;
	}

	public ConfigurationSection getConfigurationSection(String key)
	{
		Map<String, Object> sectionMap = (Map<String, Object>) getValue(key);
		return sectionMap != null ? new ConfigurationSection(sectionMap) : null;
	}

	public void setSection(String key, ConfigurationSection section)
	{
		this.configuration.put(key, section.getValues(false)); // On ajoute la section à la configuration
		this.save();
	}

	public void remove(String key)
	{
		// Séparer les clés par le point (.)
		String[] keys = key.split("\\.");
		Map<String, Object> currentMap = this.configuration;

		// Parcourir les clés imbriquées
		for (int i = 0; i < keys.length - 1; i++)
		{
			String currentKey = keys[i];
			if (currentMap.containsKey(currentKey) && currentMap.get(currentKey) instanceof Map)
			{
				currentMap = (Map<String, Object>) currentMap.get(currentKey);
			}
			else
			{
				return; // Si une clé n'existe pas, arrêter
			}
		}
		currentMap.remove(keys[keys.length - 1]);
		this.save();
	}

	public boolean contains(String key)
	{
		return getValue(key) != null;
	}

}
