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

	// type id

	@CsvBindByName
	public Integer typeID;
	@CsvBindByName
	public Integer type_id;

	public Integer typeID() {
		return typeID != null ? typeID : type_id;
	}

	// type name

	@CsvBindByName
	public String typeName;
	@CsvBindByName
	public String type_name;

	public String typeName() {
		return typeName != null ? typeName : type_name;
	}

	// category id

	@CsvBindByName
	public Integer categoryID;
	@CsvBindByName
	public Integer category_id;

	public Integer categoryID() {
		return categoryID != null ? categoryID : category_id;
	}

	// category name

	@CsvBindByName
	public String categoryName;
	@CsvBindByName
	public String category_name;

	public String categoryName() {
		return categoryName != null ? categoryName : category_name;
	}

	// group id

	@CsvBindByName
	public Integer groupID;
	@CsvBindByName
	public Integer group_id;

	public Integer groupID() {
		return groupID != null ? groupID : group_id;
	}

	// group name

	@CsvBindByName
	public String groupName;
	@CsvBindByName
	public String group_name;

	public String groupName() {
		return groupName != null ? groupName : group_name;
	}

	// primary index

	@CsvBindByName
	public String primaryIndex;
	@CsvBindByName
	public String primary_index;

	public String primaryIndex() {
		return primaryIndex != null ? primaryIndex : primary_index;
	}

	// sub index

	@CsvBindByName
	public String subIndex;
	@CsvBindByName
	public String sub_index;

	public String subIndex() {
		return subIndex != null ? subIndex : sub_index;
	}

	private static final CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

	public static List<IndexBasketsEntry> parse(InputStream is) {
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
