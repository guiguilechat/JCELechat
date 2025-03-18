package fr.guiguilechat.jcelechat.model.sde.industry.blueprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * archived list of blueprint. Since those archived list may not be used, the
 * actual loading is done after listing it, using the inputstream supplier.
 */
@AllArgsConstructor
@Accessors(fluent = true)
@Slf4j
public class ArchivedBlueprintList {

	public static final String WORKING_PATH = "SDE/industry/blueprints";

	public static final String LIST_PATH = WORKING_PATH + "/" + "list";

	/**
	 * input stream generator used to load the file when needed.
	 */
	private final Supplier<InputStream> is;

	/**
	 * moment that list replaced the previous one
	 */
	@Getter
	private final Instant since;

	@Getter(lazy = true)
	private final LinkedHashMap<Integer, Blueprint> blueprints = Blueprint.load(is.get());

	/**
	 * load the archive list and convert it to corresponding items. Since the lib
	 * should be worked from a jar, it's necessary to load a listing resource
	 */
	static List<ArchivedBlueprintList> loadList() {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(ArchivedBlueprintList.class.getClassLoader().getResourceAsStream(LIST_PATH)))) {
			return br.lines().map(
					line ->
					new ArchivedBlueprintList(
							()->ArchivedBlueprintList.class.getClassLoader().getResourceAsStream(WORKING_PATH+"/"+line),
							Blueprint.archiveName2Instant(line)
					)).toList();
		} catch (IOException e) {
			log.warn("while load blueprint archive list", e);
			return null;
		}
	}

	@Getter(lazy = true)
	private static final List<ArchivedBlueprintList> list = loadList();

}
