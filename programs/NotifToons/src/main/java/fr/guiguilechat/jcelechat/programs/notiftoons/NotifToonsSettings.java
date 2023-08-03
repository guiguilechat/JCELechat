package fr.guiguilechat.jcelechat.programs.notiftoons;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
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

		public List<ESIAccount> accounts() {
			return refreshTokens.stream()
					.map(rfr -> new ESIAccount(rfr, base64))
					.filter(acc -> acc.isValid())
					.toList();
		}
	}

	private List<Ssokey> ssokeys = new ArrayList<>();

	public List<ESIAccount> accounts() {
		return ssokeys.stream()
				.flatMap(ssok -> ssok.accounts().stream())
				.toList();
	}

	@Data
	public static class Notification {
		private boolean showMarketOrders = true;
		private boolean showRemaps = true;
		private boolean showSkillsChange = true;
		private boolean showSkillsQueueEnd = true;
	}

	private Notification notifications = new Notification();

}
