package fr.guiguilechat.jcelechat.model.sde.industry.targetfilter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

import fr.guiguilechat.jcelechat.model.sde.industry.TargetFilter;
import fr.guiguilechat.jcelechat.model.sde.translate.ArchiveTools;
import fr.guiguilechat.jcelechat.model.sde.translate.ArchiveTools.Archived;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Accessors(fluent = true)
@Slf4j
public class ArchivedTargetFilterList implements Archived<LinkedHashMap<Integer, TargetFilter>> {

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
	private final LinkedHashMap<Integer, TargetFilter> items = TargetFilter.load(is.get());

	//

	public static final String WORKING_PATH = "SDE/industry/targetfilters";

	@Getter(lazy = true)
	private static final List<ArchivedTargetFilterList> list = ArchiveTools.loadList(WORKING_PATH,
			ArchivedTargetFilterList::new);

	public static boolean archiveOnDiff(File observedFile, Instant modified, File rootDir) throws IOException {
		return ArchiveTools.archiveOnDiff(observedFile, modified, new File(rootDir, WORKING_PATH));
	}

}
