package fr.guiguilechat.jcelechat.libs.spring.anon.faction.information;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.FetchedEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.affiliations.faction")
@Order(2)
public class FactionInfoService
		extends FetchedEntityService<FactionInfo, Integer, FactionInfoRepository> {

	@Override
	protected FactionInfo create(Integer entityId) {
		FactionInfo ret = new FactionInfo();
		ret.setId(entityId);
		return ret;
	}


}
