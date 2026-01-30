package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.AFetchedListElementAutoId;
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
    @Index(columnList = "fetch_resource_id"),
    @Index(columnList = "contactType")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterContact extends
		AFetchedListElementAutoId<CharacterContact, CharacterContactList> {

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
