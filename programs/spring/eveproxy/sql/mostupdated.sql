with params(days, regionId, mindayactivity) as (
	values(31, 10000002, 16)
),
updates as(
select
	line.type_id,
	count(*) nb
from
	params p,
	jcelechat_trade_orderupdate line
	join jcelechat_trade_ordercreation creat on creat.order_id=line.order_id
where
	line.region_id=p.regionId
	and line.date>= date_trunc('day', now()- p.days * interval '1 days')
	and creat.duration <>365
group by
	line.type_id
),
creations as(
select
	line.type_id,
	count(*) nb
from
	params p,
	jcelechat_trade_ordercreation line
where
	line.region_id=p.regionId
	and line.date_max>= date_trunc('day', now() - p.days*interval '1 days')
	and line.duration <>365
group by
	line.type_id
)
select
	updates.type_id,
	t.name,
	coalesce(updates.nb, 0) nbupdates,
	coalesce(creations.nb, 0) nbcreations,
	to_char(100.0*coalesce(updates.nb, 0)/(coalesce(creations.nb, 0)+coalesce(updates.nb, 0)), '990D99') update_pct
from
	params,
	updates
	full join creations on updates.type_id=creations.type_id
	left join sde_items_type t on t.id=coalesce(updates.type_id, creations.type_id)
where
	coalesce(creations.nb, 0)+coalesce(updates.nb, 0)>params.days*params.mindayactivity
order by
	100.0*coalesce(updates.nb, 0)/(coalesce(creations.nb, 0)+coalesce(updates.nb, 0)) desc,
	coalesce(updates.nb, 0) desc
limit 1000