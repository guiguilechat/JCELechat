package fr.guiguilechat.eveonline.model.sde.yaml;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;

public class Tools {

	public static DumperOptions blockDumper() {
		DumperOptions ret = new DumperOptions();
		ret.setDefaultFlowStyle(FlowStyle.BLOCK);
		return ret;
	}

}
