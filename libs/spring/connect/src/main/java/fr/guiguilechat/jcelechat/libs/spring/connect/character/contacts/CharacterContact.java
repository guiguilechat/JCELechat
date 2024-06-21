package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list.AFetchedListElement;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contacts_contact_type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterContact")
@Table(name = "esi_connect_charactercontact", indexes = {
    @Index(columnList = "fetchResourceRemoteId,contactType")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterContact extends
    AFetchedListElement<CharacterContact, CharacterContactList> {

	@Entity(name = "EsiConnectCharacterContactList")
	@Table(name = "esi_connect_charactercontactlist", indexes = {
	    @Index(columnList = "fetch_active,expires")
	})
	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class CharacterContactList
	    extends AFetchedList<Integer, R_get_characters_character_id_contacts, CharacterContact> {

	}

	/**
	 * Whether this contact is in the blocked list. Note a missing value denotes
	 * unknown, not true or false
	 */
	private boolean blocked;

	/**
	 * contact_id integer
	 */
	private int contactId;

	/**
	 * contact_type string
	 */
	@Enumerated(EnumType.STRING)
	private get_characters_character_id_contacts_contact_type contactType;

	/**
	 * Standing of the contact
	 */
	private float standing;

	/**
	 * Whether this contact is being watched
	 */
	private boolean watched;

	public static CharacterContact from(R_get_characters_character_id_contacts from) {
		return new CharacterContact(from.is_blocked, from.contact_id, from.contact_type, from.standing, from.is_watched);
	}

}
