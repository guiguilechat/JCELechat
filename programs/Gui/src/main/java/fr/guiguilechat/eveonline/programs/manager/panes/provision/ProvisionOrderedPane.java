package fr.guiguilechat.eveonline.programs.manager.panes.provision;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.sde.industry.Blueprint;
import fr.guiguilechat.eveonline.model.sde.npcs.LPOffer;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.ProvisionType;
import fr.guiguilechat.eveonline.programs.manager.Settings.TeamDescription.Provision;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ProvisionOrderedPane extends GridPane implements EvePane {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProvisionOrderedPane.class);

	protected Manager parent;

	@Override
	public Manager parent() {
		return parent;
	}

	public ProvisionOrderedPane(Manager parent) {
		this.parent = parent;
		setHgap(5);
		setVgap(1);
	}

	LinkedHashMap<Integer, LPOffer> lpoffersbyId = LPOffer.load();
	LinkedHashMap<Integer, Blueprint> bpsbyId = null;

	boolean shown = false;

	public void update() {
		if (!shown) {
			return;
		}
		getChildren().clear();
		if (lpoffersbyId == null) {
			lpoffersbyId = LPOffer.load();
		}
		if (bpsbyId == null) {
			bpsbyId = new LinkedHashMap<>();
			for (Blueprint bp : Blueprint.load().values()) {
				bpsbyId.put(bp.id, bp);
			}
		}
		int row=0;
		String team = parent().settings.focusedTeam;
		for (ProvisionType p : ProvisionType.values()) {
			Provision provisions = parent().getTeamProvision(team, p);
			for (Entry<Integer, Integer> e : provisions.lpoffers.entrySet()) {
				LPOffer lpo = lpoffersbyId.get(e.getKey());
				add(new Label("lp offer " + p), 0, row);
				add(new Label(lpo.name), 1, row);
				add(new Label("" + e.getValue()), 2, row);
				Button btn = new Button("delete");
				btn.setOnAction(ev -> parent().provisionLPOffer(p, lpo, 0));
				add(btn, 3, row);
				row++;
			}

		}
		for (ProvisionType p : ProvisionType.values()) {
			Provision provisions = parent().getTeamProvision(team, p);
			for (Entry<Integer, Integer> e : provisions.blueprints.entrySet()) {
				Blueprint bp = bpsbyId.get(e.getKey());
				add(new Label("bp " + p), 0, row);
				add(new Label(bp.name), 1, row);
				add(new Label("" + e.getValue()), 2, row);
				Button btn = new Button("delete");
				btn.setOnAction(ev -> parent().provisionBP(p, bp, 0));
				add(btn, 3, row);
				row++;
			}
		}
		for (ProvisionType p : ProvisionType.values()) {
			Provision provisions = parent().getTeamProvision(team, p);
			for (Entry<Integer, Integer> e : provisions.total.entrySet()) {
				String itemName = db().getElementById(e.getKey());
				add(new Label(itemName), 1, row);
				add(new Label("" + e.getValue()), 2, row);
				row++;
			}
		}
	}

	@Override
	public void onNewProvision(ProvisionType ptype, int itemID, int qtty) {
		update();
	}

	@Override
	public void onFocusedTeam(String teamName) {
		update();
	}

	@Override
	public void onDelTeam(String name) {
		update();
	}

	@Override
	public void onIsShown(boolean shown) {
		this.shown = shown;
		update();
	}

}
