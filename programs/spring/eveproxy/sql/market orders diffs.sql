with params(resolution, period, regionid, typeid, groupid, categoryid) as (
	values(5, 7*interval '1 days',
	10000002::integer, --regionid
	null::integer, --typeid
	null::integer, --groupid
	null::integer --categoryid
	)
),
allowed(id) as(
	select
		it.id
	from
		params p,
		sde_items_type it
		join sde_items_group ig on it.group_id=ig.id
	where
		(p.typeid is null or p.typeid=it.id)
		and (p.groupid is null or p.groupid=ig.id)
		and (p.categoryid is null or p.categoryid=ig.category_id)
)
select
	gen.date "period start",
	1.0*coalesce(updates.nb, 0)/p.resolution "updates/m",
	1.0*coalesce(creations.nb, 0)/p.resolution "creations/m",
	1.0*coalesce(sales.nb, 0)/p.resolution "sales/m",
	1.0*coalesce(deletions.nb, 0)/p.resolution "deletions/m",
	1.0*(coalesce(updates.nb, 0)+coalesce(creations.nb, 0)+coalesce(sales.nb, 0)+coalesce(deletions.nb, 0))/p.resolution "total/m"
from
	params p,
	(select generate_series(
		date_trunc('hour', now() - (select period from params)),
		now()-(select resolution from params)*interval '1 minute',
		(select resolution from params)*interval '1 minute') date
	) gen
	left join
	(select	
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		jcelechat_trade_orderupdate line
		join allowed on allowed.id=line.type_id
	where
		(p.regionid is null or p.regionid=line.region_id)
	group by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
	) updates
	on gen.date=updates.date
	left join
	(select	
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		jcelechat_trade_ordercreation line
		join allowed on allowed.id=line.type_id
	where
		(p.regionid is null or p.regionid=line.region_id)
		and line.duration<>365
	group by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute'
	) creations
	on gen.date=creations.date
	left join
	(select	
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		jcelechat_trade_ordersale line
		join allowed on allowed.id=line.type_id
	where
		(p.regionid is null or p.regionid=line.region_id)
	group by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
	) sales
	on gen.date=sales.date
	left join
	(select	
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		jcelechat_trade_orderdeletion line
		join allowed on allowed.id=line.type_id
	where
		(p.regionid is null or p.regionid=line.region_id)
	group by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute'
	) deletions
	on gen.date=deletions.date
;