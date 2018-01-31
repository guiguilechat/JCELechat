package fr.guiguilechat.eveonline.programs.manager;

import fr.guiguilechat.eveonline.model.esi.ESIConnection;
import fr.guiguilechat.eveonline.programs.ISettings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataHandler {

	// settings

	protected Settings settings = ISettings.load(Settings.class);

	public Settings settings() {
		return settings;
	}

	// api management

	/**
	 * add an sso api
	 *
	 * @param refresh
	 *          the refreshtoken
	 * @param base
	 *          the base64 value of {id}:{secret}
	 */
	public void addAPI(String refresh, String base) {
		ESIConnection api = new ESIConnection(refresh, base);
		if (api.verify.characterName() != null) {
			apis.add(api);
			settings.ssoKeys.put(refresh, base);
			settings.store();
		}
	}

	public final ObservableList<ESIConnection> apis = FXCollections.observableArrayList();

}
