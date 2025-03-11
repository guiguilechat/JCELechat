package fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.SQLiteParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Getter
@RequiredArgsConstructor
public class KeyValTimeLoader<T> {

	final private Class<T> valueClass;

	final private String resourceName;

	@Getter(lazy = true, value = AccessLevel.PROTECTED)
	private final ObjectMapper mapper = new ObjectMapper();

	protected KeyValTime<T> load(ResultSet rs) throws SQLException, JsonMappingException, JsonProcessingException {
		int key = rs.getInt("key");
		String valStr = rs.getString("value");
		double time = rs.getDouble("time");
		T value = getMapper().readerFor(valueClass).readValue(valStr);
		return KeyValTime.ofRow(key, value, time);
	}

	public List<KeyValTime<T>> load(Statement st)
			throws SQLException, JsonMappingException, JsonProcessingException {
		ResultSet rs = st.executeQuery("SELECT * FROM cache");
		List<KeyValTime<T>> ret = new ArrayList<>();
		while (rs.next()) {
			ret.add(load(rs));
		}
		return ret;
	}

	@SneakyThrows
	public List<KeyValTime<T>> load(ClientCache cache) {
		return load(SQLiteParser.loadFile(cache.file(resourceName)));
	}

	@SneakyThrows
	public List<KeyValTime<T>> loadPrintCSV(ClientCache cache, PrintStream ps) {
		List<KeyValTime<T>> list = load(cache);
		ps.println(valueClass.getSimpleName() + "\t" + list.size() + "\t" + resourceName);
		return list;
	}

}
