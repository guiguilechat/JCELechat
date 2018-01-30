package fr.guiguilechat.eveonline.model.esi.raw;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import fr.guiguilechat.eveonline.model.esi.connect.ESIConnection;
import fr.guiguilechat.eveonline.model.esi.connect.ESIRawConnection;

@Deprecated
public class Character extends ESIConnection {

	public Character(ESIRawConnection connection) {
		super(connection);
	}

	// character names
	//
	// https://esi.tech.ccp.is/ui/#/Character/get_characters_names
	//

	public static class CharacterName {
		public int character_id;
		public String character_name;
	}

	private static final String CHARACTER_NAMES_BASEURL = "https://esi.tech.ccp.is/latest/characters/names/?character_ids=";
	/** max number of ids we can specify in a http request */
	private static final int maxIDs = 500;
	private static ObjectMapper om = new ObjectMapper();

	public static List<CharacterName> names(int... ids) {
		if (ids == null || ids.length == 0) {
			return Collections.emptyList();
		}
		ArrayList<CharacterName> ret = new ArrayList<>();
		for (int start = 0; start < ids.length; start += maxIDs) {
			StringBuilder idBuilder = null;
			for (int i = start; i < start + maxIDs && i < ids.length; i++) {
				int id = ids[i];
				if (idBuilder == null) {
					idBuilder = new StringBuilder("" + id);
				} else {
					idBuilder.append("," + id);
				}
			}
			CharacterName[] names = null;
			IOException error = null;
			for (int retry = 0; retry < 10 && names == null; retry++) {
				try {
					names = om.readValue(new URL(CHARACTER_NAMES_BASEURL + idBuilder.toString()), CharacterName[].class);
				} catch (IOException e) {
					error = e;
				}
			}
			if (names != null) {
				ret.addAll(Arrays.asList(names));
			} else {
				throw new UnsupportedOperationException(error);
			}
		}
		return ret;
	}

	public static Map<Integer, String> getNames(int... ids) {
		return names(ids).stream().collect(Collectors.toMap(n -> n.character_id, n -> n.character_name));
	}

	// character affiliations
	//
	// https://esi.tech.ccp.is/ui/#/Character/post_characters_affiliation
	//

	public static class CharacterAffiliation {
		public long character_id, corporation_id, alliance_id;
	}

	protected static final ObjectReader affiliationReader = om.readerFor(CharacterAffiliation[].class);

	public static CharacterAffiliation[] affiliations(int... ids) throws IOException {
		if (ids == null || ids.length == 0) {
			return new CharacterAffiliation[0];
		}
		String transmit = IntStream.of(ids).distinct().mapToObj(Integer::toString)
				.collect(Collectors.joining(",", "[", "]"));
		return affiliationReader.readValue(
				ESIRawConnection.connect("https://esi.tech.ccp.is/latest/characters/affiliation/", "POST", null, transmit,
						null));
	}

	// character public infos
	//
	// https://esi.tech.ccp.is/ui/#/Character/get_characters_character_id
	//

	public static class CharacterInformations {
		public long corporation_id;
		public String birthday, name, gender, description;
		public int race_id, bloodline_id, ancestry_id;
	}

	protected static final ObjectReader characterIDReader = om.readerFor(CharacterInformations.class);

	public static CharacterInformations character(int id) throws IOException {
		return characterIDReader
				.readValue(
						ESIRawConnection.connect("https://esi.tech.ccp.is/latest/characters/" + id + "/", "GET", null, null, null));
	}

}
