package fr.guiguilechat.eveonline.programs.manager.panes.api;

import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
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

	protected ListApiTableView listApi;
	protected AddAPIPane addApi;

	protected BorderPane apiListPane = new BorderPane();

	public APIPane(Manager parent) {
		this.parent = parent;
		listApi = new ListApiTableView(parent);
		addApi = new AddAPIPane(parent);
		apiListPane.setCenter(listApi);
		apiListPane.setTop(addApi);

		getTabs().addAll(new Tab("your apis", apiListPane));
		setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		subPanes = new EvePane[] { listApi, addApi };
	}

}
