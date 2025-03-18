package fr.guiguilechat.jcelechat.model.sde.industry.blueprint;

import java.io.File;
import java.time.Instant;
import java.util.Map;

import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DatedBlueprintList {

	private final File bpFile;

	private final Instant since;

	private static Map<Integer, Blueprint> byId = null;

}
