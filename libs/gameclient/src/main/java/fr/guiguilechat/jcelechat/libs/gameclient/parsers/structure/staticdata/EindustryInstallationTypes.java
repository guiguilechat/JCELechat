package fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.common.type.TypeReference;
import lombok.Getter;

public class EindustryInstallationTypes extends TypeReference {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<EindustryInstallationTypes> loader = new KeyValTimeLoader<>(
			EindustryInstallationTypes.class, "staticdata/industry_installation_types.static");

	public static class AssemblyLine {

		public int assemblyLine;

	}

	public List<AssemblyLine> assembly_lines = new ArrayList<>();


}
