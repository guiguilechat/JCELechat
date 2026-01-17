package fr.guiguilechat.jcelechat.libs.spring.anon.war;

import org.springframework.context.annotation.Lazy;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class WarInformationService
		extends RemoteEntityService<WarInformation, Integer, WarInformationRepository> {
	@Override
	protected WarInformation create(Integer entityId) {
		WarInformation ret = new WarInformation();
		ret.setId(entityId);
		return ret;
	}

}
