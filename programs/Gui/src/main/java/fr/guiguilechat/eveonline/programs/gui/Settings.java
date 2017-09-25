package fr.guiguilechat.eveonline.programs.gui;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.guiguilechat.eveonline.programs.settings.ISettings;

public class Settings implements ISettings {

	@Override
	public String getAppName() {
		return "guiguilechat.evemanager";
	}

	public LinkedHashMap<Integer, String> apiKeys = new LinkedHashMap<>();

	public LinkedHashMap<String, LinkedHashSet<String>> teams = new LinkedHashMap<>();

	public String focusedTeam = null;

	public boolean hideDebug = true;

	public static class Provision {

		public HashMap<Integer, Integer> total = new HashMap<>();

		public HashMap<Integer, Integer> lpoffers = new HashMap<>();

	}

	public LinkedHashMap<String, Provision> provisions = new LinkedHashMap<>();

	@Override
	public Constructor makeYamlConstructor() {
		Constructor ret = ISettings.super.makeYamlConstructor();
		TypeDescription settingsDescription = new TypeDescription(Settings.class);
		settingsDescription.putMapPropertyType("provisions", String.class, Provision.class);
		ret.addTypeDescription(settingsDescription);
		return ret;
	}

}