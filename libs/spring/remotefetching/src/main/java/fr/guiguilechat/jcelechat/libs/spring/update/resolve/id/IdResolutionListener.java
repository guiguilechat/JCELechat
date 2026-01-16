package fr.guiguilechat.jcelechat.libs.spring.update.resolve.id;

import java.util.Collection;

/** implement in a service to listen to new id resolved */
public interface IdResolutionListener {

	/** called when new Ids are successfully resolved */
	void onNewIdResolutions(Collection<IdResolution> idResolutions);

}