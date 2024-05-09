package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts.CharacterContact.CharacterContactList;
import fr.guiguilechat.jcelechat.libs.spring.templates.repositories.ICharDataRecordRepository;

public interface CharacterContactRepository extends ICharDataRecordRepository<CharacterContactList, CharacterContact> {

	public List<CharacterContact> findAllByFetchResourceCharacterIdInAndContactIdIn(Iterable<Integer> fromIds,
	    Iterable<Integer> toIds);

	public List<CharacterContact> findAllByContactIdIn(Iterable<Integer> toIds);

}
