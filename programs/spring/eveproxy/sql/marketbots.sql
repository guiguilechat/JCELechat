with params(days, mininterval) as(
	values(8, 14*interval '1 hour')
),
aggregated(order_id, type_id, datestart, dateend, spread, spread_hours, trunc_spread_hours, trunc_hours, nbupdates) as(
	select
		line.order_id,
		line.type_id,
		min(line.date),
		max(line.date),
		greatest(max(line.date)-min(line.date), interval '1 hour'), --spread
		1.0*extract (epoch from greatest(max(line.date)-min(line.date), interval '1 hour'))/3600, --spread_hours
		(1.0*extract (epoch from greatest(	interval '1 hour'
													+ date_trunc('hour', max(line.date))
													-date_trunc('hour', min(line.date)),
												p.mininterval)
					)/3600), --trunc_spread_hours
		count(distinct(date_trunc('hour', line.date))), --trunc_hours
		count(*)
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
	created.is_buy_order bo,
	it.name typename,
	to_char(1.0*line.nbupdates/line.spread_hours, '99D99') "updates/h",
	to_char(1.0*line.trunc_hours/line.trunc_spread_hours*24, '99D99') "hours/d",
	line.nbupdates,
	line.trunc_hours,
	date_trunc('second', line.spread) update_spread,
	case when created.date_min is not null then to_char(created.date_max, 'YYYY-MM-DD HH24:MI') else null end order_creat_date,
	to_char(line.datestart, 'YYYY-MM-DD HH24:MI') first_updat_date,
	to_char(line.dateend, 'YYYY-MM-DD HH24:MI') last_updat_date,
	to_char(deletion.date_max, 'YYYY-MM-DD HH24:MI') order_delet_date,
	case when created.date_min is not null
		then date_trunc('second', coalesce(deletion.date_max, current_timestamp) - created.date_max)
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
--	1.0*line.nbupdates/line.spread_hours desc ,
	1.0*line.trunc_hours/line.trunc_spread_hours*24 desc
	, 1.0*line.nbupdates/line.spread_hours desc
limit 1000