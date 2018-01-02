package fr.guiguilechat.eveonline.programs.manager.V2;

import fr.guiguilechat.eveonline.model.database.yaml.YamlDatabase;
import fr.guiguilechat.eveonline.programs.manager.settings.ISettings;

public class DataHandler {

	// database

	protected YamlDatabase db = new YamlDatabase();

	public YamlDatabase db() {
		return db;
	}

	// settings

	protected Settings settings = ISettings.load(Settings.class);

	public Settings settings() {
		return settings;
	}

}
