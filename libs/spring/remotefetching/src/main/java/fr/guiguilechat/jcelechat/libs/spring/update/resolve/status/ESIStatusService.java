package fr.guiguilechat.jcelechat.libs.spring.update.resolve.status;

import java.time.Instant;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_status;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * get the status of the ESI.
 */
@Service
@Slf4j
public class ESIStatusService {

	@Getter
	Instant errorReset = null;

	public int availErrors() {
		if (errorReset != null) {
			if (Instant.now().isBefore(errorReset)) {
				return 0;
			} else {
				errorReset = null;
			}
		}
		Requested<R_get_status> esiAccessReq = ESIRawPublic.INSTANCE.get_status(null);
		if (esiAccessReq.isOk()) {
			R_get_status esiAccess = esiAccessReq.getOK();
			if (!esiAccess.vip) {
				return esiAccessReq.getRemainingErrors();
			}
			log.info(" ESI is in VIP mode, skipping");
			return 0;
		}
		switch (esiAccessReq.getResponseCode()) {
		case 420:
			errorReset = esiAccessReq.getErrorsResetInstant();
			log.debug("esi error limit reached (420), prevent fetch until {}", errorReset);
			break;
		case 503:
			errorReset = Instant.now().plusSeconds(10);
			log.debug("esi not avail (503), prevent fetch until {}", errorReset);
			break;
		default:
			log.info(" could not get ESI status, skipping for {}", esiAccessReq.getResponseCode());
		}
		return 0;
	}
}
