package fr.guiguilechat.eveonline.programs.manager.panes.api;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.api.esi.ESICharsPane;
import fr.guiguilechat.eveonline.programs.manager.panes.api.esi.ESIDevPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class APIPane extends TabPane implements EvePane {

	protected final Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	protected EvePane[] subPanes;

	@Override
	public EvePane[] subEvePanes() {
		return subPanes;
	}

	protected ListXMLV2TableView listApi;
	protected AddXMLV2Pane addApi;
	protected ESIDevPane esidev;
	protected ESICharsPane esiclient;

	protected BorderPane apiListPane = new BorderPane();

	public APIPane(Manager parent) {
		this.parent = parent;
		listApi = new ListXMLV2TableView(parent);
		addApi = new AddXMLV2Pane(parent);
		apiListPane.setCenter(listApi);
		apiListPane.setTop(addApi);

		esidev = new ESIDevPane(parent);
		esiclient = new ESICharsPane(parent);

		getTabs().addAll(new Tab("xmlV2", apiListPane), new Tab("esi dev", esidev), new Tab("esi client", esiclient));
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		subPanes = new EvePane[] { listApi, addApi, esidev, esiclient };
	}

}
