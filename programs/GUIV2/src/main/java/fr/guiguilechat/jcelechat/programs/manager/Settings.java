package fr.guiguilechat.jcelechat.programs.manager;

import java.util.LinkedHashMap;

import org.yaml.snakeyaml.representer.Representer;

import fr.guiguilechat.jcelechat.programs.ISettings;

public class Settings implements ISettings {

	@Override
	public String getAppName() {
		return "guiguilechat.evemanager2";
	}

	public LinkedHashMap<String, String> ssoKeys = new LinkedHashMap<>();


	@Override
	public Representer makeYamlRepresenter() {
		Representer ret = ISettings.super.makeYamlRepresenter();
		ret.getPropertyUtils().setSkipMissingProperties(true);
		return ret;
	}

}
