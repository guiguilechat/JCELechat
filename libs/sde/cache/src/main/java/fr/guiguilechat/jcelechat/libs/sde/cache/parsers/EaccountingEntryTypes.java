package fr.guiguilechat.jcelechat.libs.sde.cache.parsers;

import java.util.LinkedHashMap;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;

public class EaccountingEntryTypes {

	//
	// SDE loading
	//

	public static final IntMapLoader<EaccountingEntryTypes> LOADER = new IntMapLoader<>(
			"accountingEntryTypes",
			EaccountingEntryTypes.class);

	//
	// file structure
	//

	public LinkedHashMap<String, String> description = new LinkedHashMap<>();
	public String internalName;
	public LinkedHashMap<String, String> journalMessage = new LinkedHashMap<>();
	public LinkedHashMap<String, String> name = new LinkedHashMap<>();

	public String enDescription() {
		return description == null ? null : description.get("en");
	}

	public String enJournalMessage() {
		return journalMessage == null ? null : journalMessage.get("en");
	}

	public String enName() {
		return name == null ? null : name.get("en");
	}

	//

	public static void main(String[] args) {
		var loaded = LOADER.yaml().load();
		System.out.println("loaded : " + loaded.size());
		var first = loaded.entrySet().iterator().next().getValue();
		System.out.println("first : internalName=" + first.internalName + " journalMessage=" + first.enJournalMessage()
				+ " enName=" + first.enName());
	}

}
