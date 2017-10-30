package fr.guiguilechat.eveonline.programs.gui.panes.overview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.LoggerFactory;

import fr.guiguilechat.eveonline.model.database.apiv2.APIRoot;
import fr.guiguilechat.eveonline.model.database.apiv2.Account.EveChar;
import fr.guiguilechat.eveonline.model.database.apiv2.Char;
import fr.guiguilechat.eveonline.model.database.apiv2.Char.JobEntry;
import fr.guiguilechat.eveonline.programs.gui.Manager;
import fr.guiguilechat.eveonline.programs.gui.panes.EvePane;
import fr.guiguilechat.eveonline.programs.gui.panes.overview.JobPane.JobData;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.SortType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class JobPane extends TableView<JobData> implements EvePane {

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

	@Override
	public Manager parent() {
		return parent;
	}

	public JobPane(Manager parent) {
		this.parent = parent;
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
		getColumns().add(dateCol);

		TableColumn<JobData, String> typeCol = new TableColumn<>("type");
		typeCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().type));
		getColumns().add(typeCol);

		TableColumn<JobData, String> desCol = new TableColumn<>("description");
		desCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().description));
		desCol.setMinWidth(400);
		getColumns().add(desCol);

		TableColumn<JobData, String> whereCol = new TableColumn<>("where");
		whereCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().where));
		getColumns().add(whereCol);

		TableColumn<JobData, String> whoCol = new TableColumn<>("who");
		whoCol.setCellValueFactory(ed -> new ReadOnlyObjectWrapper<>(ed.getValue().who));
		getColumns().add(whoCol);

		getSortOrder().add(dateCol);

		setRowFactory(tv -> new TableRow<JobData>() {
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
	public void onNewAPI(APIRoot... apis) {
		if (!shown) {
			return;
		}
		Stream.of(apis).parallel().flatMap(api -> api.account.characters().parallelStream()).forEach(this::addCharJobs);
		sort();
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
				getItems().add(ed);
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
	 * The cache duration is {@value OverViewPane#cache_duration_in_minutes} min.
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
	public void onDelAPI(int key) {
		if (!shown) {
			return;
		}
		APIRoot api = parent().getAPI(key);
		if (api == null) {
			debug("can't del apiroot for id " + key);
			return;
		}
		Set<String> charNames = api.account.characters().stream().map(c -> c.name).collect(Collectors.toSet());
		synchronized (this) {
			getItems().removeIf(ev -> charNames.contains(ev.who));
		}
	}

	/**
	 * request to fetch all jobs. Already cached jobs are still used if possible
	 */
	public void update() {
		getItems().clear();
		parent().streamChars().parallel().forEach(this::addCharJobs);
		sort();
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
