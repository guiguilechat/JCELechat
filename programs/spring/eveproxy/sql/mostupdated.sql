with params(days, regionId) as (
	values(21, 10000002)
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
	updates.nb nbupdates,
	creations.nb nbcreations,
	to_char(updates.nb/creations.nb, '990D99') ratio
from
	updates
	left join creations on updates.type_id=creations.type_id
	left join sde_items_type t on t.id=updates.type_id
order by
	updates.nb desc
limit 200