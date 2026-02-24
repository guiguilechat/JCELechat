package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.postgresql.PGConnection;
import org.postgresql.copy.CopyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TempMarketLineService {

	final private TempMarketLineRepository repo;

	@Autowired
	private DataSource datasource;

	public void insertAll(List<TempMarketLine> entities) {
		try {
			Connection conn = DataSourceUtils.getConnection(datasource);
			if (conn.isWrapperFor(PGConnection.class)) {
				log.debug("using PG connection to insert {} orders", entities.size());
				if (insertPGCopy(entities, conn.unwrap(PGConnection.class).getCopyAPI())) {
					return;
				} else {
					log.warn("failed to insert using postgresql copy, delegating to hibernate");
				}
			} else {
				log.trace("no PG connection, falling back to hibernate's saveAll");
			}
		} catch (SQLException e) {
			log.warn("error using datasource, letting hibernate handle that crap", e);
		}
		repo.saveAllAndFlush(entities);
	}

	protected boolean insertPGCopy(List<TempMarketLine> entities, CopyManager cm) {
		long start = System.currentTimeMillis();
		StringReader reader =
				new StringReader(
						entities.stream()
								.map(CommonMarketLine::csv)
								// .reduce(new StringBuilder(), (BiFunction<StringBuilder, ? super String,
								// StringBuilder>) StringBuilder::append, (BinaryOperator<StringBuilder>)
								// StringBuilder::append)
								.collect(Collectors.joining("\n")));
		long postReader = System.currentTimeMillis();
		try {
			cm.copyIn("COPY esi_trade_market_line (" + MarketLine.CSV_HEADER + ") FROM STDIN WITH DELIMITER '"
					+ MarketLine.CSV_SEP + "'", reader);
		} catch (SQLException | IOException e) {
			log.error("while PG copy " + entities.size() + " entities", e);
			return false;
		}

		long end = System.currentTimeMillis();
		log.trace("performed copy of {} entries in {} ms (convert={} insert={})",
				entities.size(),
				end - start,
				postReader - start,
				end - postReader);
		return true;
	}

	public void truncate() {
		repo.truncate();
	}

}
