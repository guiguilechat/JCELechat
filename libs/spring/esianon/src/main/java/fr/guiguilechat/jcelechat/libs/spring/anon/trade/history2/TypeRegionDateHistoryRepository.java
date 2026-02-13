package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TypeRegionDateHistoryRepository
		extends JpaRepository<TypeRegionDateHistory, TypeRegionDateHistory.TypeRegionDateKey> {

	@Modifying
	@Query("""
delete from #{#entityName}
where date in :days
""")
	void deleteForDates(Iterable<LocalDate> days);

}
