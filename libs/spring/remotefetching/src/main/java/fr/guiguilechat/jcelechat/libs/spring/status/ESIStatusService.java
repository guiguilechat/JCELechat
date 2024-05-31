package fr.guiguilechat.jcelechat.libs.spring.status;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ESIStatusService {

	public int availErrors() {
		Requested<R_get_status> esiAccessReq = ESIRawPublic.INSTANCE.get_status(null);
		if (esiAccessReq.isOk()) {
			R_get_status esiAccess = esiAccessReq.getOK();
			if (!esiAccess.vip) {
				return esiAccessReq.getRemainingErrors();
			}
			log.info(" ESI is in VIP mode, skipping");
			return 0;
		}
		log.info(" could not get ESI status, skipping");
		return 0;
	}
}
