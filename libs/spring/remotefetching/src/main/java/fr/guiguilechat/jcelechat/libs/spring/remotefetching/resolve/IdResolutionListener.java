package fr.guiguilechat.jcelechat.libs.spring.remotefetching.resolve;

/** implement in a service to listen to new id resolved */
public interface IdResolutionListener {

	/** called when a new Id is successfully resolved */
	public void onNewIdResolution(IdResolution idResolution);

}