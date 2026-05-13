with params(typeid, regionid, days) as(
	values(
		35 --34 tritanium, 35 pyerite
		, 10000002
		, 100
	)
),
observedsales(date, vol) as(
	select
		date(date_trunc('day',date)) date,
		sum(volume) vol
	from
		params,
		jcelechat_trade_ordersale
	where
		type_id=typeid
		and region_id =regionid
		and date> now() - days*interval '1 days'
	group by
		date_trunc('day',date)
),
historysales(date, vol) as(
	select
		date,
		volume
	from
		params,
		esi_trade_typeregiondatehistory
	where
		type_id = typeid
		and region_id=regionid
		and date >= now() - days*interval '1 days'
)
select
	coalesce(os.date, hs.date) date,
	os.vol observed,
	hs.vol history
from
	observedsales os
	full join historysales hs on os.date=hs.date
order by
	coalesce(os.date, hs.date) asc