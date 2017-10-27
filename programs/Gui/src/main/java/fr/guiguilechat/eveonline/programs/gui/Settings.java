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

			public HashMap<Integer, Integer> lpoffersIn = new HashMap<>();

			public HashMap<Integer, Integer> bpIn = new HashMap<>();

			public HashMap<Integer, Integer> directIn = new HashMap<>();

			public HashMap<Integer, Integer> totalIn = new HashMap<>();


			public HashMap<Integer, Integer> directOut = new HashMap<>();

			public HashMap<Integer, Integer> totalOut = new HashMap<>();

		}

		public Set<String> members = new HashSet<>();

		public Provision provision = new Provision();

		public Set<String> systems = new HashSet<>();

		@Override
		public TeamDescription clone() {
			TeamDescription ret = new TeamDescription();
			ret.members.addAll(members);
			ret.provision.totalIn.putAll(provision.totalIn);
			ret.provision.lpoffersIn.putAll(provision.lpoffersIn);
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