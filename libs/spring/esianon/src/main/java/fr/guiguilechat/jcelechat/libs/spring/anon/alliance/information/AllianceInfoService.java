package fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AllianceInfoService extends
		RemoteEntityService<AllianceInfo, Integer, AllianceInfoRepository> {

	@Override
	protected AllianceInfo create(Integer entityId) {
		AllianceInfo ret = new AllianceInfo();
		ret.setId(entityId);
		return ret;
	}

}
