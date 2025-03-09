package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.guiguilechat.jcelechat.libs.everaw.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.everaw.parsers.SQLiteParser;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Efighterabilities {

	public static class Value {

		public enum TargetMode {
			itemTargeted,
			pointTargeted,
			untargeted;
		}

		public boolean disallowInHighSec;

		public boolean disallowInLowSec;

		public int displayNameID;

		public int iconID;

		public int tooltipTextID;

		public TargetMode targetMode;

		public int turretGraphicID;
	}

	private int key;

	private Value value;

	private Instant time;

	@Getter(lazy = true)
	private static final ObjectMapper mapper = new ObjectMapper();

	public static Efighterabilities load(ResultSet rs)
			throws SQLException, JsonMappingException, JsonProcessingException {
		int key = rs.getInt("key");
		String valueStr = rs.getString("value");
		double timeD = rs.getDouble("time");
		Instant time = Instant.ofEpochMilli((long) (timeD * 1000));
		ObjectMapper mapper = getMapper();
		Value value = mapper.readerFor(Value.class).readValue(valueStr);
		return builder()
				.key(key)
				.value(value)
				.time(time)
				.build();
	}

	public static List<Efighterabilities> load(Statement st)
			throws SQLException, JsonMappingException, JsonProcessingException {
		ResultSet rs = st.executeQuery("SELECT * FROM cache");
		List<Efighterabilities> ret = new ArrayList<>();
		while (rs.next()) {
			ret.add(load(rs));
		}
		return ret;
	}

	public static List<Efighterabilities> load(ClientCache cache)
			throws JsonMappingException, JsonProcessingException, SQLException {
		return load(SQLiteParser.loadFile(cache.file("staticdata/fighterabilities.static")));
	}


}
