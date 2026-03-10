with params(days, mininterval) as(
	values(8, 24*interval '1 hour')
),
aggregated(order_id, type_id, hourlyupdates, dailyhours, spread, datestart, dateend, nbhours, spreadhours) as(
	select
		line.order_id,
		line.type_id,
		1.0*count(*)
			/(1.0*extract (epoch from greatest(	interval '1 hour' 
													+ date_trunc('hour', max(line.date))
													-date_trunc('hour', min(line.date)),
												p.mininterval)
					)/3600),
		1.0*count(distinct(date_trunc('hour', line.date)))
			/(1.0*extract (epoch from greatest(	interval '1 hour'
													+ date_trunc('hour', max(line.date))
													-date_trunc('hour', min(line.date)),
												p.mininterval)
					)/3600/24),
		max(line.date)-min(line.date),
		min(line.date),
		max(line.date),
		count(distinct(date_trunc('hour', line.date))),
		(1.0*extract (epoch from greatest(	interval '1 hour'
												+ date_trunc('hour', max(line.date))
												-date_trunc('hour', min(line.date)),
											p.mininterval)
					)/3600)
	from
		params p,
		jcelechat_trade_orderupdate line
	where
		line.date> now()-p.days*interval '1 day'
	group by
		p.days,
		p.mininterval,
		line.order_id,
		line.type_id
)
select
	line.order_id,
	created.region_id,
	created.location_id,
	line.type_id,
	created.is_buy_order,
	it.name typename,
	to_char(line.hourlyupdates, '99D9') "updates/h",
	to_char(line.dailyhours, '99D9') "hours/d",
	line.nbhours,
	line.spread active,
	line.dateend lastupdate,
	case when created.date_min is not null then created.date_max else null end created,
	deletion.date_max deleted,
	case when created.date_min is not null
		then coalesce(deletion.date_max, current_timestamp) - created.date_max
		else null
	end orderlife
from
	params p,
	aggregated line
	join sde_items_type it on it.id=line.type_id
	join jcelechat_trade_ordercreation created on line.order_id=created.order_id
	left join jcelechat_trade_orderdeletion deletion on line.order_id=deletion.order_id
where
	created.duration <>365 -- remove ccp bots
order by
	line.dailyhours desc
	, line.hourlyupdates desc
limit 1000