package fr.guiguilechat.eveonline.programs.manager.panes.tools.burners;

import fr.guiguilechat.eveonline.programs.manager.Settings.MissionStats;
import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

public class MissionPane extends GridPane {

	public TypedField<Integer> isk_cstt, isk_indexed, lp, align_time_s, timetokill_s;

	TypedField<Double> warp_speed_aups;

	protected MissionStats stats;

	public MissionPane(String name, MissionStats options) {
		stats = options;
		setStyle("-fx-border-color: black; -fx-border-width: 1;");
		add(new Label(name), 0, 0);

		isk_cstt = TypedField.positivIntField(options.isk_cstt);
		isk_cstt.setOnScroll(new ScrollAdd.IntScrollAdd(1000, isk_cstt));
		isk_cstt.setTooltip(
				new Tooltip("constant isk gain when successfuly doing this mission, in k. includes bounty and BO of loot"));
		addRow(1, new Label("cstt isk"), isk_cstt);

		isk_indexed = TypedField.positivIntField(options.isk_indexed);
		isk_indexed.setOnScroll(new ScrollAdd.IntScrollAdd(100000, isk_indexed));
		isk_indexed.setTooltip(
				new Tooltip("indexed isk gain when successfuly doing this mission, in k.\n"
						+ "includes mission reward with bonus for 1.0 system and negotiation 0"));
		addRow(2, new Label("indexed isk"), isk_indexed);

		lp = TypedField.positivIntField(options.lp);
		lp.setOnScroll(new ScrollAdd.IntScrollAdd(1000, lp));
		lp.setTooltip(
				new Tooltip("indexed lp gain when successfuly doing this mission.\n"
						+ "includes agent reward, in 1.0 system and security connection at 0"));
		addRow(3, new Label("lp rwrd"), lp);

		align_time_s = TypedField.positivIntField(options.align_time_s);
		align_time_s.setOnScroll(new ScrollAdd.IntScrollAdd(1, align_time_s));
		align_time_s.setTooltip(
				new Tooltip("align time in s of the ship used for that mission"));
		addRow(4, new Label("align time"), align_time_s);

		warp_speed_aups = TypedField.positivDecimal(options.warp_speed_aups);
		warp_speed_aups.setOnScroll(new ScrollAdd.DoubleScrollAdd(1.0, warp_speed_aups));
		warp_speed_aups.setTooltip(
				new Tooltip("warp speed of the ship used to do that mission"));
		addRow(5, new Label("warp speed"), warp_speed_aups);

		timetokill_s = TypedField.positivIntField(options.timetokill_s);
		timetokill_s.setOnScroll(new ScrollAdd.IntScrollAdd(10, timetokill_s));
		timetokill_s.setTooltip(
				new Tooltip("time between landing at the mission gate and leaving it."));
		addRow(6, new Label("ttk"), timetokill_s);

		for (Region r : new Region[] { align_time_s, isk_cstt, isk_indexed, lp, timetokill_s, warp_speed_aups }) {
			r.setMaxWidth(70);
		}
	}

	public boolean updateOptions() {
		boolean ret = false;

		int newAlignTime = align_time_s.getValue();
		if (stats.align_time_s != newAlignTime) {
			stats.align_time_s = newAlignTime;
			ret = true;
		}

		int newisk_cstt = isk_cstt.getValue();
		if (stats.isk_cstt != newisk_cstt) {
			stats.isk_cstt = newisk_cstt;
			ret = true;
		}

		int newIsk_indexed = isk_indexed.getValue();
		if (stats.isk_indexed != newIsk_indexed) {
			stats.isk_indexed = newIsk_indexed;
			ret = true;
		}

		int newLP = lp.getValue();
		if (stats.lp != newLP) {
			stats.lp = newLP;
			ret = true;
		}

		int newTTK = timetokill_s.getValue();
		if (stats.timetokill_s != newTTK) {
			stats.timetokill_s = newTTK;
			ret = true;
		}

		double newWarpSpeed = warp_speed_aups.getValue();
		if (stats.warp_speed_aups != newWarpSpeed) {
			stats.warp_speed_aups = newWarpSpeed;
			ret = true;
		}
		return ret;
	}

}
