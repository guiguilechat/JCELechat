with params(resolution, period, regionid) as (
	values(
		5,-- resolution, as minutes to aggregate
		8*interval '1 days', -- period we observe the events on
		null::integer --regionid
	)
),
limits(start) as (
	values(date_trunc('hour', now() - (select period from params)))
),
updates(date, nb) as (
	select	
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		limits l,
		jcelechat_trade_orderupdate line
		join jcelechat_trade_ordercreation created on line.order_id=created.order_id
	where
		(p.regionid is null or p.regionid=line.region_id)
		and l.start<= date
		and created.duration<>365
	group by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
),
creations(date, nb) as (
	select	
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		limits l,
		jcelechat_trade_ordercreation line
	where
		(p.regionid is null or p.regionid=line.region_id)
		and l.start<= date_max
		and line.duration<>365
	group by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute'
),
sales(date, nb) as (
	select	
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		limits l,
		jcelechat_trade_ordersale line
	where
		(p.regionid is null or p.regionid=line.region_id)
		and l.start<= date
	group by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % p.resolution) * interval '1 minute'
),
deletions(date, nb) as(
	select	
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute' date,
		count(*) nb
	from
		params p,
		limits l,
		jcelechat_trade_orderdeletion line
	where
		(p.regionid is null or p.regionid=line.region_id)
		and l.start<= date_max
	group by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute'
	order by
		date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % p.resolution) * interval '1 minute')
select
	gen.date "period start",
	1.0*coalesce(updates.nb, 0)/p.resolution "updates/m",
	1.0*coalesce(creations.nb, 0)/p.resolution "creations/m",
	1.0*coalesce(sales.nb, 0)/p.resolution "sales/m"
--	, 1.0*coalesce(deletions.nb, 0)/p.resolution "deletions/m"
--	, 1.0*(coalesce(updates.nb, 0)+coalesce(creations.nb, 0)+coalesce(sales.nb, 0)+coalesce(deletions.nb, 0))/p.resolution "total/m"
from
	params p,
	(	select generate_series(
			date_trunc('hour', now() - (select period from params)),
			now()-(select resolution from params)*interval '1 minute',
			(select resolution from params)*interval '1 minute'
		) date
	) gen
	left join updates on gen.date=updates.date
	left join creations	on gen.date=creations.date
	left join sales	on gen.date=sales.date
	left join deletions	on gen.date=deletions.date
;