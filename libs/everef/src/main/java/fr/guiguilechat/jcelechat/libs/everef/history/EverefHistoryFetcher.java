package fr.guiguilechat.jcelechat.libs.everef.history;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * client to fetch everef history entries. stateful userAgent to transmit.
 */
@Slf4j
@RequiredArgsConstructor
public class EverefHistoryFetcher {

	private static final DateTimeFormatter URL_FORMATTER = DateTimeFormatter.ofPattern(
			"'https://data.everef.net/market-history/'yyyy'/market-history-'yyyy-MM-dd'.csv.bz2'",
			Locale.ENGLISH);

	public static final String FIRST_DATE_STR = "2003-10-01";
	public static final LocalDate FIRST_DATE = LocalDate.parse(FIRST_DATE_STR);

	private final String userAgent;

	String uri(LocalDate localDate) {
		return URL_FORMATTER.format(localDate);
	}

	HttpRequest request(LocalDate localDate) {
		try {
			return HttpRequest
					.newBuilder(new URI(uri(localDate)))
					.header("User-Agent", userAgent)
					.build();
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	@Getter(lazy = true, value = AccessLevel.PACKAGE)
	private final HttpClient client = HttpClient.newHttpClient();

	public CompletableFuture<List<HistoryEntry>> fetch(LocalDate localDate) {
		HttpRequest request = request(localDate);
		CompletableFuture<HttpResponse<InputStream>> f = getClient().sendAsync(request, BodyHandlers.ofInputStream());
		return f.thenApply(this::extract);
	}

	List<HistoryEntry> extract(HttpResponse<InputStream> response) {
		if (response.statusCode() == 200) {
			try (BZip2CompressorInputStream c = new BZip2CompressorInputStream(
					new BufferedInputStream(response.body()))) {
				return parse(c);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			log.warn("received status " + response.statusCode() + " for " + response.uri().toString());
		}
		return null;
	}

	public CompletableFuture<List<HistoryEntry>> fetch(int year, int month, int day) {
		return fetch(LocalDate.of(year, month, day));
	}

	//
	// csv loader
	//

	private static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	static List<HistoryEntry> parse(InputStream is) {
		List<HistoryEntry> ret = new ArrayList<>();
		try {
			synchronized (parser) {
				CsvToBean<HistoryEntry> csvToBean = new CsvToBeanBuilder<HistoryEntry>(
						new CSVReaderBuilder(
								new InputStreamReader(is))
								.withCSVParser(parser).build())
						.withType(HistoryEntry.class)
						.withIgnoreLeadingWhiteSpace(true).build();
				ret = csvToBean.parse();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

}
