package fr.guiguilechat.jcelechat.libs.mer;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import fr.lelouet.tools.application.xdg.XDGApp;

/**
 * loads MER into a cache dir.
 */
public class MerLoader {

	public static final LocalDate DATE_DEFAULTSTART = LocalDate.of(2018, 8, 1);

	public static final DateTimeFormatter URL_DATE_FORMATTER_1 = DateTimeFormatter
			.ofPattern("'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_'MMMyyyy'.zip'");
	public static final DateTimeFormatter URL_DATE_FORMATTER_2 = DateTimeFormatter
			.ofPattern("'https://web.ccpgamescdn.com/aws/community/'MMMM_yyyy'_MER.zip'");
	public static final DateTimeFormatter URL_DATE_FORMATTER_3 = new DateTimeFormatterBuilder()
			.appendLiteral("https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_")
			.appendText(ChronoField.MONTH_OF_YEAR, Map.of(9l, "Sept"))
			.appendText(ChronoField.YEAR)
			.appendLiteral(".zip").toFormatter();

	public static final DateTimeFormatter FILE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

	private static final XDGApp APP = new XDGApp("mer.ccp.is");

	public static void main(String[] args) {
		new MerLoader().unpackMERs(APP, null, null);
	}

	/**
	 * proceeds in loading missing data. Starts from the end date and tries to dig
	 * down the MER by decreasing one month. stop when it has already found one
	 * MER and can't find the next one.
	 *
	 * @param app
	 *          application used to make cache dir
	 * @param start
	 *          minimum date to use to start the load. is truncated to month. can
	 *          be null
	 * @param end
	 *          maximum date to load data. default is now()
	 */
	public void unpackMERs(XDGApp app, LocalDate start, LocalDate end) {
		if (end == null) {
			end = LocalDate.now();
		}
		end = end.with(TemporalAdjusters.firstDayOfMonth());

		if (start == null) {
			start = DATE_DEFAULTSTART;
		}

		boolean foundOne = false;
		boolean lastOK = false;
		for (LocalDate date = end; !date.isBefore(start) && (lastOK || !foundOne); date = date.minusMonths(1)) {
			File outDir = APP.cacheFile(date.format(FILE_DATE_FORMATTER));
			boolean success;
			if (outDir.isDirectory()) {
				success = true;
			} else {
				outDir.mkdirs();
				success = unpackUrl(date.format(URL_DATE_FORMATTER_1), outDir);
				if (!success) {
					success = unpackUrl(date.format(URL_DATE_FORMATTER_2), outDir);
				}
				if (!success) {
					success = unpackUrl(date.format(URL_DATE_FORMATTER_3), outDir);
				}
				foundOne |= success;
				lastOK = success;
			}
			if (!success) {
				outDir.delete();
			}
		}
	}

	public static boolean unpackUrl(String url, File outDir) {
		System.out.println("unpack " + url + " in " + outDir.getAbsolutePath());
		try {
			InputStream is = new URL(url).openStream();
			ZipEntry e;
			try (ZipInputStream zis = new ZipInputStream(is)) {
				while ((e = zis.getNextEntry()) != null) {
					if (e.isDirectory()) {
						continue;
					}
					File out = new File(outDir, e.getName());
					out.getParentFile().mkdirs();
					FileWriter fw = new FileWriter(out);
					byte[] b = new byte[100];
					while (zis.available() > 0) {
						int r = zis.read(b, 0, b.length);
						if (r > -1) {
							fw.write(new String(b, 0, r));
						}
					}
					fw.close();
				}
			}
			return true;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}

}
