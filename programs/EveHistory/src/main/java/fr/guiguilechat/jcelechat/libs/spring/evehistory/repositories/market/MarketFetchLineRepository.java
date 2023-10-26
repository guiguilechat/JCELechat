package fr.guiguilechat.jcelechat.libs.spring.evehistory.repositories.market;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchLine;
import fr.guiguilechat.jcelechat.libs.spring.evehistory.model.market.MarketFetchResult;

public interface MarketFetchLineRepository extends JpaRepository<MarketFetchLine, Long> {

	/**
	 * list the lines belonging to a fetchresult as well as their previous and next
	 * line, for same order, by id.
	 */
	@Query("""
select line0, line1, line2
from MarketFetchLine line1
left join MarketFetchLine line0 on line0.id=(select max(a.id) from MarketFetchLine a where a.order.order_id=line1.order.order_id and a.id<line1.id and a.invalid=false)
left join MarketFetchLine line2 on line2.id=(select min(b.id) from MarketFetchLine b where b.order.order_id=line1.order.order_id and b.id>line1.id and b.invalid=false)
where line1.fetchResult= :fetchresult
""")
	List<Object[]> listOrderChanges(@Param("fetchresult") MarketFetchResult fetchResult);

	int countByFetchResult(MarketFetchResult fetchResult);

	@Query(nativeQuery = true, value="""
select
	distinct result.region_id region,
	date_trunc('hour', line.issued_date) issued_date,
	count(case when line.price_chg then 1 else null end) price_chg,
	count(case when line.volume_chg then 1 else null end) vol_chg,
	count(case when line.created then 1 else null end) created,
	count(case when line.last then 1 else null end) last
from
	market_fetch_result result
	join market_fetch_line line on result.id=line.fetch_result_id
where
	line.invalid=false
group by
	result.region_id,
	date_trunc('hour', line.issued_date)
order by
	result.region_id,
	date_trunc('hour', line.issued_date)
""")
	List<Object[]> analyzeChanges();

	@Query("""
select line
from MarketFetchLine line
where line.issuedDate>:fromDate
	and line.issuedDate<:toDate
	and line.order.type_id=:typeId
	and line.fetchResult.regionId=:regionId
	and line.priceChg=true
	and line.invalid=false
order by
	line.issuedDate asc
""")
	List<MarketFetchLine> listPriceChanges(int regionId, int typeId, Instant fromDate, Instant toDate);

	@Query(nativeQuery = true, value = """
select
	coalesce(sales.sale_day,created.created_day, ended.ended_day) date_day,
	coalesce(sales.sales_nbbo, 0),
	coalesce(sales.sales_bototvalue, 0),
	case when sales.sales_nbbo>0 then sales.sales_bototvalue/sales.sales_nbbo else null end sales_boavg,
	coalesce(sales.sales_nbso, 0),
	coalesce(sales.sales_sototvalue, 0),
	case when sales.sales_nbso>0 then sales.sales_sototvalue/sales.sales_nbso else null end so_avgprice,

	created.created_bo,
	created.created_volbo,
	created.created_so,
	created.created_volso,

	ended.ended_volbo,
	case when ended.ended_volbo>0 then ended.ended_bototvalue/ended.ended_volbo else null end ended_boavg,
	ended.ended_volso,
	case when ended.ended_volso>0 then ended.ended_sototvalue/ended.ended_volso else null end ended_soavg
from
	( 	select
	 		date_trunc('day',l.sold_to) sale_day,
			sum(case when l.is_buy_order then l.sold else 0 end) sales_nbbo,
			sum(case when l.is_buy_order then l.price else 0 end * l.sold) sales_bototvalue,
			sum(case when l.is_buy_order then 0 else l.sold end) sales_nbso,
			sum(case when l.is_buy_order then 0 else l.price end * l.sold) sales_sototvalue
		from
			market_fetch_line l join market_fetch_result r on l.fetch_result_id=r.id
		where
			l.price_chg=true
			and l.invalid=false
			and l.type_id=:typeId
			and r.region_id=:regionId
			and l.sold_to<:toDate
			and l.sold_to>=:fromDate
		group by
			date_trunc('day',l.sold_to)
	) sales
	full join ( select
			date_trunc('day',l.issued_date) created_day,
			sum(case when l.is_buy_order then 1 else 0 end) created_bo,
			sum(case when l.is_buy_order then 0 else 1 end) created_so,
			sum(case when l.is_buy_order then l.volume_total else 0 end) created_volbo,
			sum(case when l.is_buy_order then 0 else l.volume_total end) created_volso
		from
			market_fetch_line l join market_fetch_result r on l.fetch_result_id=r.id
		where
			l.creation=true
			and l.invalid=false
			and l.type_id=:typeId
			and r.region_id=:regionId
			and l.sold_to<:toDate
			and l.sold_to>=:fromDate
		group by
			date_trunc('day',l.issued_date)
	) created on sales.sale_day=created.created_day
	full join ( select
			date_trunc('day',l.removal_to) ended_day,
			sum(case when l.is_buy_order then 1 else 0 end) ended_nbbo,
			sum(case when l.is_buy_order then l.volume_remain else 0 end) ended_volbo,
			sum(case when l.is_buy_order then l.volume_remain*l.price else 0 end) ended_bototvalue,
			sum(case when l.is_buy_order then 0 else 1 end) ended_nbso,
			sum(case when l.is_buy_order then 0 else l.volume_remain end) ended_volso,
			sum(case when l.is_buy_order then 0 else l.volume_remain*l.price end) ended_sototvalue
		from
			market_fetch_line l join market_fetch_result r on l.fetch_result_id=r.id
		where
			l.removal=true
			and l.invalid=false
			and l.eol=false
			and l.type_id=:typeId
			and r.region_id=:regionId
			and l.sold_to<:toDate
			and l.sold_to>=:fromDate
		group by
			date_trunc('day',l.removal_to)
	) ended on sales.sale_day=ended.ended_day
order by
	coalesce(sales.sale_day,created.created_day, ended.ended_day) asc
""")
	List<Object[]> listDailyOnTypeRegionFromTo(int regionId, int typeId, Instant fromDate, Instant toDate);

	@Query(nativeQuery = true, value = """
select
	res.region_id,
	sum(case when line.volume_remain=0 then 1 else 0 end) nbVol0,
	sum(case when line.duration=0 then 1 else 0 end) nbDur0,
	count(line.id) total
from
	market_fetch_line line
	join market_fetch_result res on line.fetch_result_id=res.id and res.last_modified>date_trunc('day', now())
group by
	res.region_id
--having
--	count(line.id) >100
order by res.region_id
""")
	List<Object[]> lidstDailyLineErrorsGroupeByRegion();

}
