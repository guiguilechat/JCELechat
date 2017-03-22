package fr.guiguilechat.eveonline.database.elements;

import java.util.ArrayList;

public class Module {

	public String name;
	public String group;

	public final ModuleAttributes attributes = new ModuleAttributes();

	public ArrayList<String> effects = new ArrayList<>();

	public ModuleActivation activation;

}
