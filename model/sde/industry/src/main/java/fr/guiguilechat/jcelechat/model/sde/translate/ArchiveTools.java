package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.guiguilechat.jcelechat.model.sde.industry.blueprint.ArchivedBlueprintList;
import lombok.extern.slf4j.Slf4j;

/**
 * utilities to load and store an file archived in a dir
 */
@Slf4j
public class ArchiveTools {

	private static final DateTimeFormatter ARCHIVE_DATEFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

	public static String instant2ArchiveName(Instant instant) {
		return ARCHIVE_DATEFORMAT.format(instant.atOffset(ZoneOffset.UTC)) + ".yaml";
	}

	private static final Pattern FILENAME_PATTERN = Pattern.compile("(.*)\\.yaml");

	public static Instant archiveName2Instant(String archiveName) {
		Matcher m = FILENAME_PATTERN.matcher(archiveName);
		if (!m.matches()) {
			return null;
		}
		try {
			LocalDateTime ld = LocalDateTime.parse(m.group(1), ARCHIVE_DATEFORMAT);
			return ld.atOffset(ZoneOffset.UTC).toInstant();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * load an archive list and convert it to corresponding items. The lib
	 * should be worked from a jar, and we can't list sub resources in a jar, so
	 * it's necessary to have a listing resource to load
	 *
	 * @param <T>
	 */
	public static <T> List<T> loadList(String workingPath, BiFunction<Supplier<InputStream>, Instant, T> constructor) {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(
						ArchivedBlueprintList.class.getClassLoader().getResourceAsStream(workingPath + "/list")))) {
			return br.lines().map(
					line -> constructor.apply(
							() -> ArchiveTools.class.getClassLoader()
									.getResourceAsStream(workingPath + "/" + line),
							archiveName2Instant(line)))
					.toList();
		} catch (IOException e) {
			log.warn("while load blueprint archive list", e);
			return null;
		}
	}

	/**
	 * export a file in the archive dir is the last archived file is different, and
	 * append the new file's name to the "list" file in that dir
	 *
	 * @param observedFile the file we may archive
	 * @param modified     date to use in the new archive's name if needed
	 * @param archiveDir   directory that contain the "list" file and the list of
	 *                     archived files
	 * @return true if the observed file was archived
	 * @throws IOException why not.
	 */
	public static boolean archiveOnDiff(File observedFile, Instant modified, File archiveDir) throws IOException {
		archiveDir.mkdirs();
		File lastCopy = null;
		Instant lastTime = null;
		for (File archive : archiveDir.listFiles()) {
			Instant archiveTime = archiveName2Instant(archive.getName());
			if (archiveTime == null) {
				continue;
			}
			if (lastTime == null || lastTime.isBefore(archiveTime)) {
				lastCopy = archive;
				lastTime = archiveTime;
			}
		}
		if (lastCopy != null && Files.mismatch(lastCopy.toPath(), observedFile.toPath()) == -1) {
			return false;
		}
		// actual copy
		File newArchive = new File(archiveDir, instant2ArchiveName(modified));
		Files.copy(observedFile.toPath(), newArchive.toPath());
		// then append the file name to thelist
		File listFile = new File(archiveDir, "list");
		try (BufferedWriter writer = Files.newBufferedWriter(listFile.toPath(), StandardOpenOption.CREATE,
				StandardOpenOption.APPEND)) {
			writer.append(newArchive.getName());
			writer.newLine();
		}
		return true;
	}

	public interface Archived<T> {

		Instant since();

		T items();
	}

	/**
	 * find the archive for given date. If the date is younger than
	 * first known archive, then return the first archive ; if the date is older
	 * than last known archive, then return last instead ; otherwise makes
	 * a dicho search on the archives and return the last known archive younger than provided date
	 * @param <T>
	 */
	public static <T extends Archived<U>, U> U dichoSearch(List<T> archives, Instant date, U lastItem) {
		if (date.isBefore(archives.get(0).since())) {
			return archives.get(0).items();
		}
		T lastArchive = archives.get(archives.size() - 1);
		if (date.isAfter(lastArchive.since())) {
			return lastItem == null ? lastArchive.items() : lastItem;
		}
		T bestArchive = archives.get(0);
		for (int i = 0, j = archives.size() - 1; j - i > 1;) {
			int k = (i + j) / 2;
			T archive = archives.get(k);
			if (archive.since().isBefore(date)) {
				bestArchive = archive;
				i = k;
			} else {
				j = k;
			}
		}
		return bestArchive.items();
	}

}
