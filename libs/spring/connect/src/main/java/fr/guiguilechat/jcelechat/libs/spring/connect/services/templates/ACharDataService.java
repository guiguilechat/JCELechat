package fr.guiguilechat.jcelechat.libs.spring.connect.services.templates;

import java.util.Optional;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.templates.ACharData;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.templates.ICharDataRepository;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.EsiUserService;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.EsiUserService.EsiUserListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
public abstract class ACharDataService<Entity extends ACharData<Fetched>, Fetched, Repository extends ICharDataRepository<Entity>>
    extends ARemoteFetchedResourceService<Entity, Integer, Fetched, Repository>
    implements EsiUserListener {

	@Override
	public void onNewEsiUser(EsiUser user) {
		Optional<Entity> op = repo().findById(user.getCharacterId());
		if (op.isEmpty()) {
			Entity e = create(user.getCharacterId());
			e.setActive(isActivateNewEntry());
			repo().save(e);
			log.debug("created new entry of class {} for character {}", e.getClass().getSimpleName(),
			    user.getCharacterName());
			if (e.isActive()) {
				update(e);
			}
		} else {
			log.debug("no need to create new entry for character {}", user.getCharacterName());
		}
	}

}
