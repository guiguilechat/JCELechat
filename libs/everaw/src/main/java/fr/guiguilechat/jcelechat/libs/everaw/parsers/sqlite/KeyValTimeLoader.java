package fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.libs.everaw.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.everaw.parsers.SQLiteParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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

	public List<KeyValTime<T>> load(ClientCache cache)
			throws JsonMappingException, JsonProcessingException, SQLException {
		return load(SQLiteParser.loadFile(cache.file(resourceName)));
	}

}
