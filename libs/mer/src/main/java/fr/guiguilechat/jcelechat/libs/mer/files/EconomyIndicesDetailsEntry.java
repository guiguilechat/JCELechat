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

public class EconomyIndicesDetailsEntry {

	// period

	@CsvBindByName
	public String period;
	@CsvBindByName
	public String history_date;

	public String period() {
		return period != null ? period : history_date;
	}

	static final DateTimeFormatter PERIODFORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ROOT);

	public Instant getPeriod() {
		return LocalDateTime.parse(period(), PERIODFORMAT).atOffset(ZoneOffset.UTC).toInstant();
	}

	// primary index

	@CsvBindByName
	public String indexName;
	@CsvBindByName
	public String primary_index;

	public String indexName() {
		return indexName != null ? indexName : primary_index;
	}

	// sub index

	@CsvBindByName
	public String subIndex;
	@CsvBindByName
	public String sub_index;

	public String subIndex() {
		return subIndex != null ? subIndex : sub_index;
	}

	// price change

	@CsvBindByName
	public Double priceChange;
	@CsvBindByName
	public Double price_change;

	public Double priceChange() {
		return priceChange != null ? priceChange : price_change;
	}

	// total value

	@CsvBindByName
	public Double totalValue;
	@CsvBindByName
	public Double total_value;

	public Double totalValue() {
		return totalValue != null ? totalValue : total_value;
	}

	// price change weighted

	@CsvBindByName
	public Double priceChangeWeighted;
	@CsvBindByName
	public Double price_change_weighted;

	public Double priceChangeWeighted() {
		return priceChangeWeighted != null ? priceChangeWeighted : price_change_weighted;
	}

	private static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	public static List<EconomyIndicesDetailsEntry> parse(InputStream is) {
		List<EconomyIndicesDetailsEntry> ret = new ArrayList<>();
		try {
			synchronized (parser) {
				CsvToBean<EconomyIndicesDetailsEntry> csvToBean = new CsvToBeanBuilder<EconomyIndicesDetailsEntry>(
						new CSVReaderBuilder(new InputStreamReader(is)).withCSVParser(parser).build())
						.withType(EconomyIndicesDetailsEntry.class).withIgnoreLeadingWhiteSpace(true).build();
				ret = csvToBean.parse();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

}
