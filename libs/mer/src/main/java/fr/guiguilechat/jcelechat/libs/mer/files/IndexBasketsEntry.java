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

public class IndexBasketsEntry {

	@CsvBindByName
	public int typeID;
	@CsvBindByName
	public String typeName;
	@CsvBindByName
	public int categoryID;
	@CsvBindByName
	public String categoryName;
	@CsvBindByName
	public int groupID;
	@CsvBindByName
	public String groupName;
	@CsvBindByName
	public String primaryIndex;
	@CsvBindByName
	public String subIndex;

	private static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	public static List<IndexBasketsEntry> parseDump(InputStream is) {
		List<IndexBasketsEntry> ret = new ArrayList<>();
		try {
			synchronized (parser) {
				CsvToBean<IndexBasketsEntry> csvToBean = new CsvToBeanBuilder<IndexBasketsEntry>(
						new CSVReaderBuilder(new InputStreamReader(is)).withCSVParser(parser).build())
						.withType(IndexBasketsEntry.class).withIgnoreLeadingWhiteSpace(true).build();
				ret = csvToBean.parse();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return ret;
	}

}
