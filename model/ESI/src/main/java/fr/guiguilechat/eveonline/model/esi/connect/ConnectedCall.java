package fr.guiguilechat.eveonline.model.esi.connect;

public class ConnectedCall {

	protected ESIConnection connection = null;

	public ConnectedCall(ESIConnection connection) {
		this.connection = connection;
	}

	public ESIConnection getConnection() {
		return connection;
	}

}
