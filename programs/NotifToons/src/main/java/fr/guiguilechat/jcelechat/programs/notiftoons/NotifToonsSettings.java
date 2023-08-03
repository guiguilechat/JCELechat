package fr.guiguilechat.jcelechat.programs.notiftoons;

import java.util.ArrayList;
import java.util.List;

import fr.lelouet.tools.application.yaml.YamlSettings;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotifToonsSettings extends YamlSettings {

	@Override
	public String getAppName() {
		return "guiguilechat.notiftoons";
	}

	@Data
	public static class Ssokey {
		private String base64;
		private String appId;
		private String callback;

		private List<String> refreshTokens = new ArrayList<>();
	}

	private List<Ssokey> ssokeys = new ArrayList<>();

}
