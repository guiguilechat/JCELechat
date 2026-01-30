package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.FetchedListElementService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CharacterStandingService
		extends FetchedListElementService<CharacterStanding, CharacterStandingRepository> {

}
