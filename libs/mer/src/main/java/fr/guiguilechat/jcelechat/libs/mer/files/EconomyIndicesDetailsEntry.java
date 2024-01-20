package fr.guiguilechat.jcelechat.libs.mer.files;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class EconomyIndicesDetailsEntry {

	@CsvBindByName
	public String period;
	@CsvBindByName
	public String indexName;
	@CsvBindByName
	public String subIndex;
	@CsvBindByName
	public double priceChange;
	@CsvBindByName
	public double totalValue;
	@CsvBindByName
	public double priceChangeWeighted;

	private static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	public static List<EconomyIndicesDetailsEntry> parseDump(InputStream is) {
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
