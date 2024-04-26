package fr.guiguilechat.jcelechat.libs.spring.connect.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ESIAccountHelper;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.EsiApp;
import fr.guiguilechat.jcelechat.libs.spring.connect.repositories.EsiAppRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EsiAppService {

	private final EsiAppRepository repo;

	public EsiApp findOrCreate(String appId, String appSecret) {
		String appBase64 = ESIAccountHelper.encode(appId, appSecret);
		EsiApp ret = repo.findByAppIdAndAppBase64(appId, appBase64).orElse(null);
		if (ret == null) {
			ret = repo.save(EsiApp.builder().appId(appId).appBase64(appBase64).build());
		}
		return ret;
	}

}
