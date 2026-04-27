with params(typeid, weeks) as(
	values(
		90469, --typeid
		3 -- weeks
	)
),
updates(isodow, hod, nbph) as(
	select
		extract(isodow from date) isodow,
		extract(hour from date) hod,
		1.0*count(*)/p.weeks nbph
	from
		params p,
		jcelechat_trade_orderupdate
	where
		type_id=typeid
		and date >= now()-p.weeks * interval '1 weeks'
	group by
		p.weeks,
		extract(isodow from date),
		extract(hour from date)
),
creations(isodow, hod, nbph) as(
	select
		extract(isodow from date_max) isodow,
		extract(hour from date_max) hod,
		1.0*count(*)/p.weeks nbph
	from
		params p,
		jcelechat_trade_ordercreation
	where
		type_id=typeid
		and date_max >= now()-p.weeks * interval '1 weeks'
		and duration <> 365
	group by
		p.weeks,
		extract(isodow from date_max),
		extract(hour from date_max)
)
select
	coalesce(up.isodow, cr.isodow) || to_char(coalesce(up.hod, cr.hod), ' 00' ) formatted,
	coalesce(up.isodow, cr.isodow)*24+coalesce(up.hod, cr.hod)-24 weekhour,
	coalesce(up.isodow, cr.isodow) isodow,
	coalesce(up.hod, cr.hod) hourofday,
	coalesce(up.nbph, 0) hourlyupdates,
	coalesce(cr.nbph, 0) hourlycreates
from
	updates up
	full join creations cr on up.isodow=cr.isodow and up.hod=cr.hod
order by
	coalesce(up.isodow, cr.isodow),
	coalesce(up.hod, cr.hod)
	