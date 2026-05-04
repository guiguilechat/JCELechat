package fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.diff;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Service
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MarketActivityService {

	@PersistenceContext
	private final EntityManager entityManager;

	public static record MarketActivity(
			long orderId,
			Instant date,
			long volume,
			double price,
			Boolean isBuyOrder,
			String kind) {
	}

	@SuppressWarnings("unchecked")
	protected List<MarketActivity> listActivities(int regionId, int typeId, Instant periodStart, Instant periodEnd) {
		return entityManager.createNativeQuery("""
with params(typeid, regionid) as (
	values(
		(:typeId)::int,
		(:regionId)::int
	)
),
limits(period_start, period_end) as (
	select
		(:periodStart)::date,
		(:periodEnd)::date
),
orders(id) as(
	select
		oc.order_id
	from
		params p,
		limits l,
		jcelechat_trade_ordercreation oc
	where
		oc.region_id=p.regionid
		and oc.type_id=p.typeid
		and oc.date_min<=l.period_end
		and not exists (
			select
				1
			from
				jcelechat_trade_orderdeletion od
			where
				od.order_id=oc.order_id
				and od.date_max<=l.period_start
		)
),
creations(orderid, date, volume, price, is_buy_order, kind) as (
	select
		order_id,
		date_max,
		volume,
		price,
		is_buy_order,
		'create'
	from
		jcelechat_trade_ordercreation
		join orders on order_id=orders.id
),
updates(orderid, date, volume, price, is_buy_order, kind) as (
	select
		order_id,
		date,
		0,
		new_price,
		null::boolean,
		'update'
	from
		limits l,
		jcelechat_trade_orderupdate ou
		join orders on ou.order_id=orders.id
	where
		ou.date>= (
			select
				coalesce(max(date), l.period_start)
			from
				jcelechat_trade_orderupdate ou2
			where
				ou2.order_id=ou.order_id and
				ou2.date<= l.period_start
		)
		and ou.date<= (
			select
				coalesce(min(date), l.period_end)
			from
				jcelechat_trade_orderupdate ou2
			where
				ou2.order_id=ou.order_id and
				ou2.date>= l.period_end
		)
),
deletions(orderid, date, volume, price, is_buy_order, kind)	as(
	select
		order_id,
		date_max,
		volume,
		price,
		is_buy_order,
		'delete'
	from
		limits l,
		jcelechat_trade_orderdeletion
		join orders on order_id=orders.id
	where
		date_max <=l.period_end
)

select
	* from creations
union all select
	* from updates
union all select
	* from deletions
order by
	orderid asc,
	date asc
""", MarketActivity.class)
				.setParameter("regionId", regionId)
				.setParameter("typeId", typeId)
				.setParameter("periodStart", periodStart)
				.setParameter("periodEnd", periodEnd)
				.getResultList();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartup() {
		var fetched = listActivities(10000002, 35833, Instant.now().minus(2, ChronoUnit.DAYS), Instant.now());
		System.out.println("fetched "
				+ fetched.size()
				+ " entries :");
		System.out.println(
				fetched.stream().map(Objects::toString).collect(Collectors.joining("\n")));
	}

	public enum OrderUpdateKind {
		CREATE,
		UPDATE,
		SALE,
		DELETE;

		public static OrderUpdateKind parse(String from) {
			return switch (from) {
			case "create" -> CREATE;
			case "delete" -> DELETE;
			case "sale" -> SALE;
			case "update" -> UPDATE;
			default -> throw new IllegalArgumentException("Unexpected value: " + from);
			};
		}
	}

	/**
	 * numeric values can be 0 when no change.
	 */
	public static final record OrderUpdate(Instant date, OrderUpdateKind kind, long volAfter, double priceAfter) {

	}

	/**
	 * aggregate the orders activities in a completely deterministic way.
	 */
	protected LinkedHashMap<Long, List<OrderUpdate>> aggregateByOrder(List<MarketActivity> activities) {
		Map<Long, List<MarketActivity>> grouped =
				activities.stream().collect(Collectors.groupingBy(MarketActivity::orderId));
		LinkedHashMap<Long, List<OrderUpdate>> ret = new LinkedHashMap<>();
		grouped.entrySet().stream()
				.sorted(Comparator.comparing(Entry::getKey))
				.forEach(e -> ret.put(e.getKey(), extractOrderActivities(e.getValue())));
		return ret;
	}

	protected List<OrderUpdate> extractOrderActivities(List<MarketActivity> values) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
