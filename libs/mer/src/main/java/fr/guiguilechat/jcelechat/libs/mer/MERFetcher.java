package fr.guiguilechat.jcelechat.libs.mer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.zip.ZipInputStream;

import fr.guiguilechat.jcelechat.libs.mer.DateURL.DateRangeFormatter;
import lombok.RequiredArgsConstructor;

/**
 * loads MER into a cache dir.
 */
@RequiredArgsConstructor
public class MERFetcher {

	public static final LocalDate DATE_DEFAULTSTART = LocalDate.of(2016, 6, 1);

	public static Stream<LocalDate> streamPossibleMERs() {
		return Stream.iterate(DATE_DEFAULTSTART, ld -> ld.isBefore(LocalDate.now()), ld -> ld.plusMonths(1));
	}

	public static final MERFetcher INSTANCE = new MERFetcher();

	public static DateURL defaultURLs() {
		return new DateURL()
				.with(DateRangeFormatter.starting(2022, 2,
						"'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonth(2022, 1,
						"'https://web.ccpgamescdn.com/aws/community/'MMMM_yyyy'_MER.zip'"))
				.with(DateRangeFormatter.ofMonthsRange(2021, 10, 2022, 1,
						"'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonth(2021, 9,
						"'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_Sept2021.zip'"))
				.with(DateRangeFormatter.ofMonthsRange(2020, 1, 2021, 9,
						"'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonth(2019, 12,
						"'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_Dec2019b.zip'"))
				.with(DateRangeFormatter.ofMonthsRange(2018, 8, 2019, 12,
						"'https://web.ccpgamescdn.com/aws/community/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonthsRange(2018, 5, 2018, 8,
						"'https://web.ccpgamescdn.com/newssystem/media/73619/1/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonth(2018, 4,
						"'https://content.eveonline.com/www/newssystem/media/73592/1/EVEOnline_MER_Apr2018.zip'"))
				.with(DateRangeFormatter.ofMonthsRange(2018, 1, 2018, 4,
						"'https://web.ccpgamescdn.com/newssystem/media/73619/1/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonth(2017, 12,
						"'http://content.eveonline.com/www/newssystem/media/73589/1/EVEOnline_MER_Dec2017.zip'"))
				.with(DateRangeFormatter.ofMonth(2017, 11,
						"'https://content.eveonline.com/www/newssystem/media/73542/1/EVEOnline_MER_Nov2017.zip'"))
				.with(DateRangeFormatter.ofMonth(2017, 10,
						"'https://content.eveonline.com/www/newssystem/media/73479/1/EVEOnline_MER_Oct2017.zip'"))
				.with(DateRangeFormatter.ofMonth(2017, 9,
						"'https://cdn1.eveonline.com/community/MER/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonth(2017, 8,
						"'https://cdn1.eveonline.com/community/MER/EVEOnline_MER_'MMMyy'.zip'"))
				.with(DateRangeFormatter.ofMonthsRange(2017, 1, 2017, 8,
						"'https://cdn1.eveonline.com/community/MER/EVEOnline_MER_'MMMyyyy'.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 12,
						"'https://content.eveonline.com/www/newssystem/media/71808/1/EVEOnline_MER_Dec2016_v1.1.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 11,
						"'https://content.eveonline.com/www/newssystem/media/70766/1/EVEOnline_MER_Nov2016.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 10,
						"'https://content.eveonline.com/www/newssystem/media/70687/1/EVEOnline_MER_Oct2016.zip'"))
				// ignored : this one has invalid CRC
// .with(DateRangeFormatter.ofMonth(2016, 9,
// "'https://content.eveonline.com/www/newssystem/media/70577/1/EVEOnline_MER_Sep2016.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 8,
						"'https://content.eveonline.com/www/newssystem/media/70511/1/EVEOnline_MER_Aug2016.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 7,
						"'https://content.eveonline.com/www/newssystem/media/70454/1/EVEOnline_MER_July2016.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 6,
						"'https://content.eveonline.com/www/newssystem/media/70401/1/EVEOnline_MER_June2016.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 5,
						"'https://content.eveonline.com/www/newssystem/media/70343/1/EVEOnline_MER_May2016.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 4,
						"'https://content.eveonline.com/www/newssystem/media/70258/1/EVEOnline_MER_Apr2016.zip'"))
				.with(DateRangeFormatter.ofMonth(2016, 3,
						"'https://content.eveonline.com/www/newssystem/media/70162/1/EVEOnline_MER_Mar2016.zip'"))
		//
		;
	}

	public MERFetcher() {
		this(defaultURLs());
	}

	private final DateURL dateURLs;

	public record MERFetch(ZipInputStream data, Exception error, String url, LocalDate date) {

		public static MERFetch error(LocalDate date, String url, Exception error) {
			return new MERFetch(null, error, url, date);
		}

		public static MERFetch success(LocalDate date, String url, ZipInputStream data) {
			return new MERFetch(data, null, url, date);
		}

		public static MERFetch noURL(LocalDate date) {
			return new MERFetch(null, null, null, date);
		}

	}

	public MERFetch forDate(LocalDate date) {
		String url = dateURLs.format(date);
		if (url == null) {
			return MERFetch.noURL(date);
		}
		try {
			return MERFetch.success(date, url, new ZipInputStream(new URI(url).toURL().openStream()));
		} catch (IOException | URISyntaxException e) {
			return MERFetch.error(date, url, e);
		}
	}

	public static void main(String[] args) {
		streamPossibleMERs()
		//
		// .parallel()
		//
			.map(INSTANCE::forDate).forEachOrdered(mf -> {
				if (mf.data() == null) {
					System.out.println(
							mf.date() + "\t" + mf.url() + "\t"
									+ (mf.error != null ? mf.error() : ""));
				} else {
						System.out.println(
								mf.date() + "\t" + mf.url() + "\t"
										+ new MER(mf).load().printStats());
				}
			});
	}

}
