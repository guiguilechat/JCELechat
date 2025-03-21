package fr.guiguilechat.jcelechat.model.sde.industry.blueprint;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.translate.ArchiveTools;
import fr.guiguilechat.jcelechat.model.sde.translate.ArchiveTools.Archived;
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
public class ArchivedBlueprintList implements Archived<LinkedHashMap<Integer, Blueprint>> {

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
	private final LinkedHashMap<Integer, Blueprint> items = Blueprint.yaml().load(is.get());

	//

	static final String WORKING_PATH = "SDE/industry/blueprints";

	@Getter(lazy = true)
	private static final List<ArchivedBlueprintList> list = ArchiveTools.loadList(WORKING_PATH,
			ArchivedBlueprintList::new);

	public static boolean archiveOnDiff(File observedFile, Instant modified, File rootDir) throws IOException {
		return ArchiveTools.archiveOnDiff(observedFile, modified, new File(rootDir, WORKING_PATH));
	}

}
