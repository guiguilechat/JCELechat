package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contacts_contact_type;

public interface CharacterContactRepository extends IFetchedListElementRepository<CharacterContactList, CharacterContact> {

	List<CharacterContact> findAllByFetchResourceIdInAndContactIdIn(Iterable<Integer> fromIds,
	    Iterable<Integer> toIds);

	List<CharacterContact> findAllByContactIdIn(Iterable<Integer> toIds);

	List<CharacterContact> findAllByFetchResourceAndContactType(CharacterContactList owner,
	    get_characters_character_id_contacts_contact_type contactType);

}
