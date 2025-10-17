package fr.guiguilechat.jcelechat.libs.spring.npc.agent;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DivisionService extends SdeEntityService<Division, Integer, DivisionRepository> {


	@Override
	protected Division create(Integer entityId) {
		var ret = new Division();
		ret.setId(entityId);
		return ret;
	}

}
