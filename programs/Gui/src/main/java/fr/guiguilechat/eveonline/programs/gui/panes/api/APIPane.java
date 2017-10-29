package fr.guiguilechat.eveonline.programs.gui.panes.api;

import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import javafx.scene.layout.BorderPane;

public class APIPane extends BorderPane implements EvePane {

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

	public APIPane(Manager parent) {
		this.parent = parent;
		listApi = new ListApiTableView(parent);
		addApi = new AddAPIPane(parent);
		setCenter(listApi);
		setTop(addApi);
		subPanes = new EvePane[] { listApi, addApi };
	}

}
