package fr.guiguilechat.jcelechat.libs.mer.files;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class IskVolumeEntry {

	// date (month)

	@CsvBindByName
	public String date;

	public String date() {
		return date;
	}

	static final DateTimeFormatter PERIODFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT);

	public Instant getDate() {
		return LocalDateTime.parse(date(), PERIODFORMAT).atOffset(ZoneOffset.UTC).toInstant();
	}

	// isk volume

	@CsvBindByName(column = "isk.volume")
	public Double volume;

	//
	// loader
	//

	private static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	public static List<IskVolumeEntry> parse(InputStream is) {
		List<IskVolumeEntry> ret = new ArrayList<>();
		try {
			synchronized (parser) {
				CsvToBean<IskVolumeEntry> csvToBean = new CsvToBeanBuilder<IskVolumeEntry>(
						new CSVReaderBuilder(new InputStreamReader(is)).withCSVParser(parser).build())
						.withType(IskVolumeEntry.class).withIgnoreLeadingWhiteSpace(true).build();
				ret = csvToBean.parse();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

}
