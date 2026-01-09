package fr.guiguilechat.jcelechat.libs.spring.anon.trade.marketranking;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.anon.trade.regional.MarketLine;
import fr.guiguilechat.tools.FormatTools;

/**
 * actually does not manage the entity, used to have direct HQL
 */
public interface MarketRankingRepository extends JpaRepository<MarketLine, Long> {

	/**
	 * ranking of the type offer. The offer is not ranked against other types, but
	 * against its historical values
	 */
	public record RankedOffer(int type_id, String type_name, Number price, Number rank) {
	}

	@Query(value = """
with
location_prices as (
select
	"type_id" type_id,
	coalesce (max("price") filter(where "is_buy_order"), 'Infinity') bo_price
from
	"esi_trade_market_line"
where
	"location_id" in :locationIds
group by
	"type_id"
),
named_prices as(
select
	location_prices.type_id,
	"sde_items_type"."name" typ_name,
	"sde_items_group"."category_id" cat_id,
	location_prices.bo_price
from
	location_prices
	join "sde_items_type" on "sde_items_type"."id" = location_prices.type_id
	join "sde_items_group" on "sde_items_group"."id" = "sde_items_type"."group_id"
where
	"sde_items_type"."published"
	and "sde_items_type"."market_group_id" is not null
	and "sde_items_type"."market_group_id" >0
	and exists(
		select
			1
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=location_prices.type_id
	)
),
location_ranked as(
select
	named_prices.*,
	0.01* floor(100*(select
		100*coalesce(sum("esi_trade_historyline"."volume") filter (where "average"<named_prices.bo_price), 0)
			/sum("esi_trade_historyline"."volume")
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=named_prices.type_id
	)) pct_sales_below_bo
from
	named_prices
)
select
	location_ranked.type_id,
	location_ranked.typ_name,
	location_ranked.bo_price,
	location_ranked.pct_sales_below_bo
from
	location_ranked
where
	cat_id=:categoryId
order by pct_sales_below_bo desc
""", nativeQuery = true)
	List<RankedOffer> rankCategoryBuyOffers(Iterable<Long> locationIds, int categoryId);

	@Query(value = """
with
location_prices as (
select
	"type_id" type_id,
	coalesce (min("price") filter(where not "is_buy_order"), 'Infinity') so_price
from
	"esi_trade_market_line"
where
	"location_id" in :locationIds
group by
	"type_id"
),
named_prices as(
select
	location_prices.type_id,
	"sde_items_type"."name" typ_name,
	"sde_items_group"."category_id" cat_id,
	location_prices.so_price
from
	location_prices
	join "sde_items_type" on "sde_items_type"."id" = location_prices.type_id
	join "sde_items_group" on "sde_items_group"."id" = "sde_items_type"."group_id"
where
	"sde_items_type"."published"
	and "sde_items_type"."market_group_id" is not null
	and "sde_items_type"."market_group_id" >0
	and exists(
		select
			1
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=location_prices.type_id
	)
),
location_ranked as(
select
	named_prices.*,
	0.01* floor(100*(select
		100*coalesce(sum("esi_trade_historyline"."volume") filter (where "average">named_prices.so_price), 0)
			/sum("esi_trade_historyline"."volume")
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=named_prices.type_id
	)) pct_sales_above_so
from
	named_prices
)
select
	location_ranked.type_id,
	location_ranked.typ_name,
	location_ranked.so_price,
	location_ranked.pct_sales_above_so
from
	location_ranked
where
	cat_id=:categoryId
order by pct_sales_above_so desc
""", nativeQuery = true)
	List<RankedOffer> rankCategorySellOffers(Iterable<Long> locationIds, int categoryId);

	@Query(value = """
with
location_prices as (
select
	"type_id" type_id,
	coalesce (max("price") filter(where "is_buy_order"), 'Infinity') bo_price
from
	"esi_trade_market_line"
where
	"location_id" in :locationIds
group by
	"type_id"
),
named_prices as(
select
	location_prices.type_id,
	"sde_items_type"."name" typ_name,
	"sde_items_type"."group_id" grp_id,
	location_prices.bo_price
from
	location_prices
	join "sde_items_type" on "sde_items_type"."id" = location_prices.type_id
where
	"sde_items_type"."published"
	and "sde_items_type"."market_group_id" is not null
	and "sde_items_type"."market_group_id" >0
	and exists(
		select
			1
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=location_prices.type_id
	)
),
location_ranked as(
select
	named_prices.*,
	0.01* floor(100*(select
		100*coalesce(sum("esi_trade_historyline"."volume") filter (where "average"<named_prices.bo_price), 0)
			/sum("esi_trade_historyline"."volume")
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=named_prices.type_id
	)) pct_sales_below_bo
from
	named_prices
)
select
	location_ranked.type_id,
	location_ranked.typ_name,
	location_ranked.bo_price,
	location_ranked.pct_sales_below_bo
from
	location_ranked
where
	grp_id=:groupId
order by pct_sales_below_bo desc
""", nativeQuery = true)
	List<RankedOffer> rankGroupBuyOffers(Iterable<Long> locationIds, int groupId);

	@Query(value = """
with
location_prices as (
select
	"type_id" type_id,
	coalesce (min("price") filter(where not "is_buy_order"), 'Infinity') so_price
from
	"esi_trade_market_line"
where
	"location_id" in :locationIds
group by
	"type_id"
),
named_prices as(
select
	location_prices.type_id,
	"sde_items_type"."name" typ_name,
	"sde_items_type"."group_id" grp_id,
	location_prices.so_price
from
	location_prices
	join "sde_items_type" on "sde_items_type"."id" = location_prices.type_id
where
	"sde_items_type"."published"
	and "sde_items_type"."market_group_id" is not null
	and "sde_items_type"."market_group_id" >0
	and exists(
		select
			1
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=location_prices.type_id
	)
),
location_ranked as(
select
	named_prices.*,
	0.01* floor(100*(select
		100*coalesce(sum("esi_trade_historyline"."volume") filter (where "average">named_prices.so_price), 0)
			/sum("esi_trade_historyline"."volume")
		from
			"esi_trade_historyline"
			join "esi_trade_historyreq" on "esi_trade_historyline"."fetch_resource_id"="esi_trade_historyreq"."id"
		where
			"esi_trade_historyreq"."type_id"=named_prices.type_id
	)) pct_sales_above_so
from
	named_prices
)
select
	location_ranked.type_id,
	location_ranked.typ_name,
	location_ranked.so_price,
	location_ranked.pct_sales_above_so
from
	location_ranked
where
	grp_id=:groupId
order by pct_sales_above_so desc
""", nativeQuery = true)
	List<RankedOffer> rankGroupSellOffers(Iterable<Long> locationIds, int groupId);

	//
	// accelerators
	//

	public record RatedAccelerator(
			Number typeId, String typeName, Number expiresEpochDays,
			Number spGain, Number price) {

		public Instant expires() {
			return expiresEpochDays() == null ? null
					: Instant.EPOCH.plusSeconds((long) (expiresEpochDays.doubleValue() * 24 * 3600));
		}

		public boolean isExpired() {
			Instant expires = expires();
			return expires != null && expires.isBefore(Instant.now());
		}

		public double spPIsk() {
			return spGain().doubleValue() / price().doubleValue();
		}

		public String formatedSpPMIsk() {
			return FormatTools.formatPrice(spPIsk() * 1000000);
		}
	}

	@Query("""
select
	t.id,
	t.name,
	expires_att.value,
	(int_att.value*1.5*dur_att.value/60000),
	(select
			min(price)
		from
			EsiTradeMarketLine ml
		where
			ml.typeId=t.id
			and not ml.isBuyOrder
			and ml.locationId=:locationId
	)
from
	SdeItemsType t
	join SdeItemsTypeAttribute int_att on t.id=int_att.typeId and int_att.attributeId=176
	join SdeItemsTypeAttribute dur_att on t.id=dur_att.typeId and dur_att.attributeId=330
	left join SdeItemsTypeAttribute expires_att on t.id=expires_att.typeId and expires_att.attributeId=2422
where
	t.marketGroup.id=2487
	and t.published
""")
	List<RatedAccelerator> rateAccelerators(long locationId);

}
