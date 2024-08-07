package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.IFetchedListElementRepository;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contacts_contact_type;

public interface CharacterContactRepository extends IFetchedListElementRepository<CharacterContactList, CharacterContact> {

	public List<CharacterContact> findAllByFetchResourceIdInAndContactIdIn(Iterable<Integer> fromIds,
	    Iterable<Integer> toIds);

	public List<CharacterContact> findAllByContactIdIn(Iterable<Integer> toIds);

	public List<CharacterContact> findAllByFetchResourceAndContactType(CharacterContactList owner,
	    get_characters_character_id_contacts_contact_type contactType);

	@Override
	@Modifying
	@Query("delete from EsiConnectCharacterContact where fetchResource.id in :ids")
	void deleteByFetchResourceIdIn(Iterable<? extends Number> ids);

}
