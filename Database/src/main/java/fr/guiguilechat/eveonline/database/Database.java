package fr.guiguilechat.eveonline.database;

import java.util.LinkedHashMap;

import fr.guiguilechat.eveonline.database.elements.Hull;
import fr.guiguilechat.eveonline.database.elements.Module;

public class Database {

	public LinkedHashMap<Integer, Hull> hulls = new LinkedHashMap<>();

	public LinkedHashMap<Integer, Module> modules = new LinkedHashMap<>();

}
