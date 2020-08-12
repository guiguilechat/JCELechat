package fr.guiguilechat.jcelechat.libs.evestager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.jcelechat.model.sde.locations.SolarSystem;

public class EveChocoStagerMain {

	private static final Logger logger = LoggerFactory.getLogger(EveChocoStagerMain.class);

	public static void main(String[] args) {
		show("Hek", 4, false);
	}

	public static void show(String sysName, int qtty, boolean useSquareDistance) {
		logger.info("around " + sysName + " in " + qtty + " ="
				+ EveChocoStager.INSTANCE.around(SolarSystem.getSystem(sysName), qtty, useSquareDistance));
	}

}
