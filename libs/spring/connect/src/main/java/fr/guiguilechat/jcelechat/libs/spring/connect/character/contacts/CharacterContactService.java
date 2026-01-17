package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.FetchedListElementService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterContactService extends FetchedListElementService<CharacterContact, CharacterContactRepository> {

	private final CharacterContactRepository recordRepo;

	public List<CharacterContact> fromTo(List<Integer> fromIds, List<Integer> toIds) {
		return recordRepo.findAllByFetchResourceIdInAndContactIdIn(fromIds, toIds);
	}

	public List<CharacterContact> forContactIds(List<Integer> toIds) {
		return recordRepo.findAllByContactIdIn(toIds);
	}


}
