package fr.guiguilechat.eveonline.programs.gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.guiguilechat.eveonline.programs.settings.ISettings;

public class Settings implements ISettings {

	@Override
	public String getAppName() {
		return "guiguilechat.evemanager";
	}

	public static class TeamDescription {

		public static class Provision {

			public HashMap<Integer, Integer> total = new HashMap<>();

			public HashMap<Integer, Integer> lpoffers = new HashMap<>();

		}

		public Set<String> members = new HashSet<>();

		public Provision provision = new Provision();

		public Set<String> systems = new HashSet<>();

		@Override
		public TeamDescription clone() {
			TeamDescription ret = new TeamDescription();
			ret.members.addAll(members);
			ret.provision.total.putAll(provision.total);
			ret.provision.lpoffers.putAll(provision.lpoffers);
			ret.members.addAll(members);
			ret.systems.addAll(systems);
			return ret;
		}
	}

	public LinkedHashMap<Integer, String> apiKeys = new LinkedHashMap<>();

	public LinkedHashMap<String, TeamDescription> teams = new LinkedHashMap<>();

	public String focusedTeam = null;

	public boolean hideDebug = true;

	@Override
	public Constructor makeYamlConstructor() {
		Constructor ret = ISettings.super.makeYamlConstructor();
		TypeDescription settingsDescription = new TypeDescription(Settings.class);
		settingsDescription.putMapPropertyType("teams", String.class, TeamDescription.class);
		ret.addTypeDescription(settingsDescription);
		return ret;
	}

}