package fr.guiguilechat.jcelechat.libs.spring.anon.trade.history2.aggregated;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.facade.AggregatedTypeHistory;

public interface AggregatedDailyHistoryRepository
		extends JpaRepository<AggregatedDailyHistory, AggregatedDailyHistory.AggregatedDailyTypeKey> {

	// query for one item

	List<AggregatedDailyHistory> findAllByTypeIdAndMeAndTeAndCopy(int typeId, int me, int te, boolean copy);

	List<AggregatedDailyHistory> findAllByTypeIdAndMeAndTeAndCopyAndDateGreaterThanEqual(int typeId, int me,
			int te, boolean copy, LocalDate startDate);

	List<AggregatedDailyHistory> findAllByTypeIdAndMeAndTeAndCopyAndDateLessThanEqual(int typeId, int me,
			int te, boolean copy, LocalDate endDate);

	List<AggregatedDailyHistory> findAllByTypeIdAndMeAndTeAndCopyAndDateGreaterThanEqualAndDateLessThanEqual(
			int typeId,
			int me, int te, boolean copy, LocalDate startDate, LocalDate endDate);

	// query for several items

	List<AggregatedDailyHistory> findAllByTypeIdInAndMeAndTeAndCopy(Iterable<Integer> typeIds, int me, int te,
			boolean copy);

	List<AggregatedDailyHistory> findAllByTypeIdInAndMeAndTeAndCopyAndDateGreaterThanEqual(Iterable<Integer> typeIds,
			int me,
			int te, boolean copy, LocalDate startDate);

	List<AggregatedDailyHistory> findAllByTypeIdInAndMeAndTeAndCopyAndDateLessThanEqual(Iterable<Integer> typeIds,
			int me,
			int te, boolean copy, LocalDate endDate);

	List<AggregatedDailyHistory> findAllByTypeIdInAndMeAndTeAndCopyAndDateGreaterThanEqualAndDateLessThanEqual(
			Iterable<Integer> typeIds,
			int me, int te, boolean copy, LocalDate startDate, LocalDate endDate);

	// update from histories and contracts

	/**
	 * delete existing entries for given dates
	 */
	@Modifying
	@Query("""
delete from #{#entityName}
where date in :dates
""")
	int deleteForDates(Iterable<LocalDate> dates);

	/**
	 * perform the aggregation for given dates
	 */
	@Modifying
	@Query("""
insert into #{#entityName} (
	average,
	copy,
	date,
	highest,
	lowest,
	me,
	te,
	typeId,
	volume
)
select
	sum(average*volume)/sum(volume) average,
	copy,
	date,
	max(highest),
	min(lowest),
	me,
	te,
	typeId,
	sum(volume)
from
	(select
		average average,
		false copy,
		date date,
		highest highest,
		lowest lowest,
		0 me,
		0 te,
		typeId typeId,
		volume volume
	from
		EsiTradeTypeRegionDateHistory trdh
	where
		date in :targetDays
	union all
	select
		price/(case when offeredCopy then offeredRuns else offeredQuantity end) average,
		offeredCopy copy,
		extract(date from removedBefore) date,
		price/(case when offeredCopy then offeredRuns else offeredQuantity end) highest,
		price/(case when offeredCopy then offeredRuns else offeredQuantity end) lowest,
		offeredMe me,
		offeredTe te,
		offeredTypeId typeId,
		(case when offeredCopy then offeredRuns else offeredQuantity end) volume
	from
		EsiTradeContractInfo ci
	where
		completed
		and extract(date from removedBefore) in :targetDays
		and offeredTypeId is not null
		and offeredTypeId >0
	)
group by
	date,
	typeId,
	me,
	te,
	copy
""")
	int aggregateForDates(Iterable<LocalDate> targetDays);

	// highest sales

	/**
	 * @param minInstant minimimum date, included
	 * @param maxInstant maximum date, included
	 * @return aggregated for each type with at least an history line, over the
	 *         included range of dates
	 */
	@Query("""
select
	tp.id typeId,
	tp.name typeName,
	sum(average*volume) totalvalue,
	sum(volume) totalQuantity,
	false,
	0,
	0
from
	#{entityName} line
	join SdeItemsType tp on line.typeId=tp.id
where
	(cast(:maxDate as timestamp) is null or line.date <= :maxDate)
	and (cast(:minDate as timestamp)is null or line.date >= :minDate)
group by
	tp.id,
	tp.name
order by
 	sum(average*volume) desc
limit :limit
""")
	List<AggregatedTypeHistory> sortSalesByTotalValue(LocalDate minDate, LocalDate maxDate, int limit);

}
