package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import java.util.Optional;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.ACharData;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiUser;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.ICharDataRepository;
import fr.guiguilechat.jcelechat.libs.spring.connect.services.EsiUserService.EsiUserListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
public abstract class ACharDataService<Entity extends ACharData<Fetched>, Fetched, Repository extends ICharDataRepository<Entity>>
    extends AFetchedResourceService<Entity, Integer, Fetched, Repository>
    implements EsiUserListener {

	@Override
	public void onNewEsiUser(EsiUser user) {
		Optional<Entity> op = repo().findById(user.getCharacterId());
		if (op.isEmpty()) {
			Entity e = create(user.getCharacterId());
			e.setActive(isActivateNewEntry());
			repo().save(e);
			log.error("created new entry of class {} for character {}", e.getClass().getSimpleName(),
			    user.getCharacterName());
			if (e.isActive()) {
				update(e);
			}
		} else {
			log.error("no need to create new entry for character {}", user.getCharacterName());
		}
	}

}
