package fr.guiguilechat.eveonline.programs.manager.V2;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.eveonline.programs.manager.settings.ISettings;

public class Settings implements ISettings {

	@Override
	public String getAppName() {
		return "guiguilechat.evemanager2";
	}

	public LinkedHashMap<String, String> ssoKeys = new LinkedHashMap<>();

	@Override
	public Constructor makeYamlConstructor() {
		Constructor ret = ISettings.super.makeYamlConstructor();
		// TypeDescription settingsDescription = new
		// TypeDescription(SettingsV2.class);
		// settingsDescription.putMapPropertyType("teams", String.class,
		// TeamDescription.class);
		// ret.addTypeDescription(settingsDescription);
		// TypeDescription missionsDescription = new
		// TypeDescription(BurnersEvalParams.class);
		// missionsDescription.putMapPropertyType("missions", String.class,
		// MissionStats.class);
		// ret.addTypeDescription(missionsDescription);
		return ret;
	}

	@Override
	public Representer makeYamlRepresenter() {
		Representer ret = ISettings.super.makeYamlRepresenter();
		ret.getPropertyUtils().setSkipMissingProperties(true);
		return ret;
	}

}
