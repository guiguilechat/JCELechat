package fr.guiguilechat.eveonline.programs.gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.eveonline.programs.settings.ISettings;

public class Settings implements ISettings {

	@Override
	public String getAppName() {
		return "guiguilechat.evemanager";
	}

	public static class TeamDescription {

		public Set<String> members = new HashSet<>();

		public Set<String> systems = new HashSet<>();

		public static class Provision {

			public HashMap<Integer, Integer> lpoffers = new HashMap<>();

			public HashMap<Integer, Integer> blueprints = new HashMap<>();

			public HashMap<Integer, Integer> total = new HashMap<>();
		}

		public Provision provisionMaterials = new Provision();

		public Provision provisionProduct = new Provision();

		public Provision provisionSO = new Provision();

		@Override
		public TeamDescription clone() {
			TeamDescription ret = new TeamDescription();
			ret.members.addAll(members);
			ret.provisionMaterials.lpoffers.putAll(provisionMaterials.lpoffers);
			ret.provisionMaterials.blueprints.putAll(provisionMaterials.blueprints);
			ret.provisionMaterials.total.putAll(provisionMaterials.total);
			ret.provisionProduct.lpoffers.putAll(provisionProduct.lpoffers);
			ret.provisionProduct.blueprints.putAll(provisionProduct.blueprints);
			ret.provisionProduct.total.putAll(provisionProduct.total);
			ret.provisionSO.lpoffers.putAll(provisionSO.lpoffers);
			ret.provisionSO.blueprints.putAll(provisionSO.blueprints);
			ret.provisionSO.total.putAll(provisionSO.total);
			ret.members.addAll(members);
			ret.systems.addAll(systems);
			return ret;
		}
	}

	public static enum ProvisionType {
		/** provision material : those need to be in BO or present in hangar */
		MATERIAL,
		/**
		 * provision product : those need to be in result of industry jobs, or in
		 * hangar
		 */
		PRODUCT,
		/** provision so : those need to be in SO */
		SO
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

	@Override
	public Representer makeYamlRepresenter() {
		Representer ret = ISettings.super.makeYamlRepresenter();
		ret.getPropertyUtils().setSkipMissingProperties(true);
		return ret;
	}

}