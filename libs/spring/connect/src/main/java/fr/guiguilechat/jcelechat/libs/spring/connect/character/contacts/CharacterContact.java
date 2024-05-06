package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecord;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecordList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contacts_contact_type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity(name = "EsiConnectCharacterContact")
@Table(name = "esi_connect_charactercontact")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterContact extends
    ACharDataRecord<CharacterContact, CharacterContactList> {

	@Entity(name = "EsiConnectCharacterContactList")
	@Table(name = "esi_connect_charactercontactlist")
	@SuperBuilder
	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class CharacterContactList
	    extends ACharDataRecordList<R_get_characters_character_id_contacts, CharacterContact> {

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
		return builder()
		    .blocked(from.is_blocked)
		    .contactId(from.contact_id)
		    .contactType(from.contact_type)
		    .standing(from.standing)
		    .watched(from.is_watched)
		    .build();
	}

}
