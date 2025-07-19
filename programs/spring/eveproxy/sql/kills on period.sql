with kill as (
	select
		destroyed_ship_id type_id,
		count(*) count_kms,
		trunc(sum(isk_lost::numeric)/1e12,2) total_lost_T,
		trunc(min(isk_lost::numeric)/1e6,2) min_lost_M,
		trunc(sum(isk_lost::numeric)/count(*) /1e6,2) avg_lost_M,
		trunc(max(isk_lost::numeric)/1e6,2) max_lost_M
	from mer_kill
	where
		kill_date>='2025-01-01'
		and kill_date<'2026-01-01'
	group by
		destroyed_ship_id
), "type" as (
	select
		t.id type_id,
		t.name||'('||t.id||')' "type",
		g.id group_id,
		g.name||'('||g.id||')' "group",
		c.id category_id,
		c.name||'('||c.id||')' "category",
		(bp.name is not null
			and bp.published
			and bp.market_group_id is not null
			and bp.market_group_id>0) produceable,
		bp.name||'('||bp.id||')' bp
	from
		esi_items_type t
		left join esi_items_group g on t.group_id=g.id
		left join esi_items_category c on g.category_id = c.id
		left join sde_blueprint_product prod on prod.type_id=t.id
		left join sde_blueprint_activity bpa on bpa.id=prod.activity_id
		left join esi_items_type bp on bp.id = bpa.type_id
)
select
	t."type" "type",
	t."group" "group",
	t."category" "category",
	t.produceable produceable,
	t.bp producer,
	kill.*
from
	kill
	join "type" t on kill.type_id = t.type_id
where
	t.category_id=6 --ships only
	and t.produceable
order by
	kill.total_lost_T desc