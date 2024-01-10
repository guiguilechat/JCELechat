package fr.guiguilechat.jcelechat.libs.mer;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import fr.lelouet.tools.application.xdg.XDGApp;

/**
 * loads MER into a cache dir.
 */
public class MerLoader {

	static final LocalDate DATE_DEFAULTSTART = LocalDate.of(2015, 8, 1);

	public static final MerLoader INSTANCE = new MerLoader();

	//
	// There are several formats for a MER URL. We check each of them and return
	// the first one that matches, or return null.
	//
	//
	List<DateTimeFormatter> URL_DATE_FORMATTERS = List.of(
			//
			// https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_Nov2022.zip
			DateTimeFormatter.ofPattern("'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// https://web.ccpgamescdn.com/aws/community/January_2022_MER.zip
			DateTimeFormatter.ofPattern("'https://web.ccpgamescdn.com/aws/community/'MMMM_yyyy'_MER.zip'"),
			//
			// https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_Sept2021.zip
			new DateTimeFormatterBuilder()
			.appendLiteral("https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_")
			.appendText(ChronoField.MONTH_OF_YEAR, Map.of(9l, "Sept"))
			.appendText(ChronoField.YEAR)
			.appendLiteral(".zip")
			.toFormatter(),
			//
			// http://cdn1.eveonline.com/community/MER/EVEOnline_MER_Sep2017.zip
			DateTimeFormatter.ofPattern("'https://cdn1.eveonline.com/community/MER/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// http://cdn1.eveonline.com/community/MER/EVEOnline_MER_Aug17.zip
			DateTimeFormatter.ofPattern("'https://cdn1.eveonline.com/community/MER/EVEOnline_MER_'MMMyy'.zip'"),
			//
			// http://web.ccpgamescdn.com/newssystem/media/73619/1/EVEOnline_MER_Jul2018.zip
			DateTimeFormatter.ofPattern("'https://web.ccpgamescdn.com/newssystem/media/73619/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// http://content.eveonline.com/www/newssystem/media/73592/1/EVEOnline_MER_Apr2018.zip
			DateTimeFormatter
			.ofPattern("'https://content.eveonline.com/www/newssystem/media/73592/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// http://content.eveonline.com/www/newssystem/media/73589/1/EVEOnline_MER_Dec2017.zip
			DateTimeFormatter
			.ofPattern("'https://content.eveonline.com/www/newssystem/media/73589/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// http://content.eveonline.com/www/newssystem/media/73542/1/EVEOnline_MER_Nov2017.zip
			DateTimeFormatter
			.ofPattern("'https://content.eveonline.com/www/newssystem/media/73542/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// http://content.eveonline.com/www/newssystem/media/73479/1/EVEOnline_MER_Oct2017.zip
			DateTimeFormatter
			.ofPattern("'https://content.eveonline.com/www/newssystem/media/73479/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// https://content.eveonline.com/www/newssystem/media/71808/1/EVEOnline_MER_Dec2016_v1.1.zip
			DateTimeFormatter
			.ofPattern("'https://content.eveonline.com/www/newssystem/media/71808/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// https://content.eveonline.com/www/newssystem/media/70766/1/EVEOnline_MER_Nov2016.zip
			DateTimeFormatter
			.ofPattern("'https://content.eveonline.com/www/newssystem/media/70766/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// https://content.eveonline.com/www/newssystem/media/70687/1/EVEOnline_MER_Oct2016.zip
			DateTimeFormatter
			.ofPattern("'https://content.eveonline.com/www/newssystem/media/70687/1/EVEOnline_MER_'MMMyyyy'.zip'"),
			//
			// https://content.eveonline.com/www/newssystem/media/70577/1/EVEOnline_MER_Sep2016.zip
			DateTimeFormatter
					.ofPattern("'https://content.eveonline.com/www/newssystem/media/70577/1/EVEOnline_MER_'MMMyyyy'.zip'")
			);

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
			Map<String, String> errorsFromURL = new HashMap<>();
			File outDir = APP.cacheFile(date.format(FILE_DATE_FORMATTER));
			boolean success = false;
			if (outDir.isDirectory()) {
				success = true;
			} else {
				outDir.mkdirs();
				for (DateTimeFormatter urlFormatter : URL_DATE_FORMATTERS) {
					String url = date.format(urlFormatter);
					String error = unpackUrl(url, outDir);
					success = error == null;
					if (success) {
						break;
					} else {
						errorsFromURL.put(url, error);
					}
				}
			}
			foundOne |= success;
			lastOK = success;
			if (!success) {
				System.err.println(errorsFromURL);
				outDir.delete();
			}
			System.err.println("after" + date + " success=" + success + " foundOne=" + foundOne + " lastOk=" + lastOK);
		}
	}

	public static String unpackUrl(String url, File outDir) {
// System.out.println("unpack " + url + " in " + outDir.getAbsolutePath());
		try {
			InputStream is = new URI(url).toURL().openStream();
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
			return null;
		} catch (Exception e) {
			return e.toString();
		}
	}

}
