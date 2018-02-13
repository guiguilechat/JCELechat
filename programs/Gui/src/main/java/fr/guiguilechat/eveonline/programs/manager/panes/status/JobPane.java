package fr.guiguilechat.eveonline.programs.manager.panes.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.apiv2.APIRoot;
import fr.guiguilechat.eveonline.model.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.model.apiv2.Char;
import fr.guiguilechat.eveonline.model.apiv2.Char.JobEntry;
import fr.guiguilechat.eveonline.programs.manager.Manager;
import fr.guiguilechat.eveonline.programs.manager.Settings.ScheduledJob;
import fr.guiguilechat.eveonline.programs.manager.panes.EvePane;
import fr.guiguilechat.eveonline.programs.manager.panes.ScrollAdd;
import fr.guiguilechat.eveonline.programs.manager.panes.TypedField;
import javafx.animation.PauseTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class JobPane extends BorderPane implements EvePane {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JobPane.class);

	public static class JobData {
		public Date time;
		public String type;
		public String description;
		public String where;
		public String who;
		public long id;

		@Override
		public int hashCode() {
			return +type.hashCode() + description.hashCode() + where.hashCode() + who.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj.getClass() != JobData.class) {
				return false;
			}
			JobData o = (JobData) obj;
			return type.equals(o.type) && description.equals(o.description) && where.equals(o.where) && who.equals(o.who);
		}

		@Override
		public String toString() {
			return "" + type + ":" + description;
		}
	}

	protected final Manager parent;

	protected TableView<JobData> table = new TableView<>();

	protected GridPane schedule = new GridPane();

	@Override
	public Manager parent() {
		return parent;
	}

	public JobPane(Manager parent) {
		this.parent = parent;
		setTop(new TitledPane("scheduled", schedule));
		setCenter(table);
		TableColumn<JobData, Date> dateCol = new TableColumn<>("date");
		dateCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().time));
		DateFormat df = new SimpleDateFormat("dd/MM HH:mm:ss");
		dateCol.setCellFactory(param -> new TableCell<JobData, Date>() {
			@Override
			protected void updateItem(Date item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					setText(df.format(item));
				} else {
					setText("");
				}
			}
		});
		dateCol.setMinWidth(120);
		dateCol.setSortable(true);
		dateCol.setSortType(SortType.ASCENDING);
		table.getColumns().add(dateCol);

		TableColumn<JobData, String> typeCol = new TableColumn<>("type");
		typeCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().type));
		table.getColumns().add(typeCol);

		TableColumn<JobData, String> desCol = new TableColumn<>("description");
		desCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().description));
		desCol.setMinWidth(400);
		table.getColumns().add(desCol);

		TableColumn<JobData, String> whereCol = new TableColumn<>("where");
		whereCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().where));
		table.getColumns().add(whereCol);

		TableColumn<JobData, String> whoCol = new TableColumn<>("who");
		whoCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().who));
		table.getColumns().add(whoCol);

		table.getSortOrder().add(dateCol);

		table.setRowFactory(tv -> new TableRow<JobData>() {
			@Override
			public void updateItem(JobData item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null && item.time != null && item.time.getTime() <= System.currentTimeMillis()) {
					setStyle("-fx-background-color: palegreen;");
				} else {
					setStyle("");
				}
			}
		});
	}

	@Override
	public void onNewXMLV2(APIRoot... apis) {
		if (!shown) {
			return;
		}
		Stream.of(apis).parallel().flatMap(api -> api.account.characters().parallelStream()).forEach(this::addCharJobs);
		table.sort();
	}

	/**
	 *
	 * @param c
	 */
	protected void addCharJobs(EveChar c) {
		for (JobEntry e : industryJobs(c)) {
			JobData ed = new JobData();
			ed.time = e.endDate;
			ed.type = Char.activityName(e.activityID);
			ed.description = e.blueprintTypeName + " *" + e.runs;
			ed.where = e.solarSystemName;
			ed.who = e.installerName;
			ed.id = e.jobID;
			synchronized (this) {
				table.getItems().add(ed);
			}
		}
	}

	/**
	 * for each character id, its list of jobs.
	 */
	protected Map<String, ArrayList<JobEntry>> cachedJobs = Collections.synchronizedMap(new HashMap<>());
	protected Map<String, Date> cachedJobsDate = Collections.synchronizedMap(new HashMap<>());
	protected int cache_duration_in_minutes = 5;

	/**
	 * Call to {@link APIRoot.#chars}.{@link Char.#industryJobs(long)} and cache
	 * it.<br />
	 * The cache duration is {@value StatusPane#cache_duration_in_minutes} min.
	 * <p>
	 * should be thread safe unless several call of the same character at the same
	 * time
	 * </p>
	 *
	 * @param c
	 * @param api
	 * @return
	 */
	protected ArrayList<JobEntry> industryJobs(EveChar c) {
		Date nextCall = cachedJobsDate.get(c.name);
		Date now = new Date();
		if (nextCall == null || nextCall.before(now)) {
			logger.trace("invalid cache jobs " + c.name);
			ArrayList<JobEntry> ret = c.industryJobs();
			cachedJobs.put(c.name, ret);
			cachedJobsDate.put(c.name, new Date(now.getTime() + cache_duration_in_minutes * 60000));
			return ret;
		} else {
			return cachedJobs.get(c.name);
		}
	}

	@Override
	public void onDelXMLV2(int key) {
		if (!shown) {
			return;
		}
		APIRoot api = parent().getXMLV2(key);
		if (api == null) {
			debug("can't del apiroot for id " + key);
			return;
		}
		Set<String> charNames = api.account.characters().stream().map(c -> c.name).collect(Collectors.toSet());
		synchronized (this) {
			table.getItems().removeIf(ev -> charNames.contains(ev.who));
		}
	}

	/**
	 * request to fetch all jobs. Already cached jobs are still used if possible
	 */
	public void update() {
		schedule.getChildren().clear();
		int row = 0;
		for (Entry<ScheduledJob, Integer> e : parent.settings.scheduled.entrySet()) {
			Label ltype = new Label(e.getKey().activity.name());
			TypedField<Integer> nbcycles = TypedField.positivIntField(e.getValue());
			nbcycles.setMaxWidth(50);
			nbcycles.setOnScroll(new ScrollAdd.IntScrollAdd(1, nbcycles));
			PauseTransition pause = new PauseTransition(Duration.seconds(1));
			nbcycles.textProperty().addListener((observable, oldValue, newValue) -> {
				pause.setOnFinished(event -> updateScheduled(e.getKey(), nbcycles.getValue()));
				pause.playFromStart();
			});
			Label lbp = new Label(e.getKey().bp);
			Label ldetails = new Label(e.getKey().details);
			Button bremove = new Button("remove");
			bremove.setOnAction(event -> updateScheduled(e.getKey(), 0));

			schedule.addRow(row, ltype, nbcycles, lbp, ldetails, bremove);
			row++;
		}
		table.getItems().clear();
		parent().streamChars().parallel().forEach(this::addCharJobs);
		table.sort();
	}

	private void updateScheduled(ScheduledJob key, Integer integer) {
		System.err.println("scheduled " + key + " updated to " + integer + "runs");
		if (integer == 0) {
			parent.settings.scheduled.remove(key);
		} else {
			parent.settings.scheduled.put(key, integer);
		}
		parent.settings.store();
		update();
	}

	/**
	 * clean the cache.
	 */
	public void clean() {
		cachedJobs.clear();
		cachedJobsDate.clear();
	}

	protected boolean shown = false;

	@Override
	public void onIsShown(boolean shown) {
		this.shown = shown;
		if (shown) {
			update();
		}
	}

}
