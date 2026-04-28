package fr.guiguilechat.jcelechat.libs.spring.anon.trade.analyze;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Deprecated // not sure if usable later 
@Service
@RequiredArgsConstructor
public class TradeActivityAnalyzer {

	@PersistenceContext
	private final EntityManager entityManager;

	public String rootPath = "trade/analyze";

	protected File mkRootDir() {
		File ret = new File(rootPath);
		ret.mkdirs();
		return ret;
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File rootDir = mkRootDir();

	public String tmpPath = "temp";

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File tmpDir = new File(rootDir(), tmpPath);

	public String reportsPath = "reports";

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final File reportsDir = new File(rootDir(), reportsPath);

	public void runAnalyze() throws IOException {
		LocalDateTime now = Instant.now().atOffset(ZoneOffset.UTC).toLocalDateTime().truncatedTo(ChronoUnit.SECONDS);
		String reportName = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		File tmpReportDir = new File(tmpDir(), reportName);
		tmpReportDir.mkdirs();
		if (!tmpReportDir.isDirectory()) {
			throw new RuntimeException("created dir " + tmpReportDir.getAbsolutePath() + " does not exist");
		}
		addReportFiles(tmpReportDir);
		reportsDir().mkdirs();
		Files.move(tmpReportDir.toPath(), new File(reportsDir(), reportName).toPath());
	}

	public static record OrderReportRow(
			int orderId,
			int regionId,
			int locationId,
			int typeId,
			boolean bo,
			String typeName,
			BigDecimal spreadHours,
			BigDecimal truncPct,
			int nbUpdates,
			int hours) {

	}

	@SuppressWarnings("unchecked")
	protected void addReportFiles(File tmpReportDir) throws IOException {
		List<Object[]> queryResult = entityManager.createNativeQuery("""
with params(days, mininterval) as(
	values(8, 16*interval '1 hour')
),
aggregated(order_id, type_id, datestart, dateend, spread, spread_hours, trunc_spread_hours, trunc_hours, nbupdates) as(
	select
		line.order_id,
		line.type_id,
		min(line.date),
		max(line.date),
		greatest(max(line.date)-min(line.date), interval '1 hour'), --spread
		1.0*extract (epoch from greatest(max(line.date)-min(line.date), interval '1 hour'))/3600, --spread_hours
		(1.0*extract (epoch from greatest(	interval '1 hour'
													+ date_trunc('hour', max(line.date))
													-date_trunc('hour', min(line.date)),
												p.mininterval)
					)/3600), --trunc_spread_hours
		count(distinct(date_trunc('hour', line.date))), --trunc_hours
		count(*)
	from
		params p,
		jcelechat_trade_orderupdate line
	where
		line.date> now()-p.days*interval '1 day'
	group by
		p.days,
		p.mininterval,
		line.order_id,
		line.type_id
)
select
	line.order_id,
	created.region_id,
	created.location_id,
	line.type_id,
	created.is_buy_order bo,
	it.name typename,
	1.0*line.nbupdates/line.spread_hours "updates/h",
	1.0*line.trunc_hours/line.trunc_spread_hours*100 "trunc%",
	line.nbupdates,
	line.trunc_hours,
	date_trunc('second', line.spread) update_spread,
	case when created.date_min is not null then to_char(created.date_max, 'YYYY-MM-DD HH24:MI') else null end order_creat_date,
	to_char(line.datestart, 'YYYY-MM-DD HH24:MI') first_updat_date,
	to_char(line.dateend, 'YYYY-MM-DD HH24:MI') last_updat_date,
	to_char(deletion.date_max, 'YYYY-MM-DD HH24:MI') order_delet_date,
	case when created.date_min is not null
		then date_trunc('second', coalesce(deletion.date_max, current_timestamp) - created.date_max)
		else null
	end orderlife
from
	params p,
	aggregated line
	join sde_items_type it on it.id=line.type_id
	join jcelechat_trade_ordercreation created on line.order_id=created.order_id
	left join jcelechat_trade_orderdeletion deletion on line.order_id=deletion.order_id
where
	created.duration <>365 -- remove ccp bots
order by
--	1.0*line.nbupdates/line.spread_hours desc ,
	1.0*line.trunc_hours/line.trunc_spread_hours desc
	, 1.0*line.nbupdates/line.spread_hours desc
limit 1000
"""
//				, OrderReportRow.class
		).getResultList();
		File botFile = new File(tmpReportDir, "botorders.csv");
		try (FileWriter fw = new FileWriter(botFile);
				BufferedWriter bw = new BufferedWriter(fw)) {
			bw.write("""
				order_id\
					region_id\
					location_id\
					type_id\
					bo\
					typename\
					udpates/h\
					trunc%\
					nbupdates\
					trunc_hours\
					update_spread\
					order_create_date\
					first_updat_date\
					order_delete_date\
					orderlife""");
			for (Object[] line : queryResult) {
				bw.write(Stream.of(line).map(Objects::toString).collect(Collectors.joining("\t")));
				bw.write("\n");
			}
		}
	}

}
