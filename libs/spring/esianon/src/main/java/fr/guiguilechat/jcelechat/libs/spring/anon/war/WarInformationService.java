package fr.guiguilechat.jcelechat.libs.spring.anon.war;

import org.springframework.context.annotation.Lazy;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.RemoteNumberEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WarInformationService
		extends RemoteNumberEntityService<WarInformation, Integer, WarInformationRepository> {
	@Override
	protected WarInformation create(Integer entityId) {
		WarInformation ret = new WarInformation();
		ret.setId(entityId);
		return ret;
	}

}
