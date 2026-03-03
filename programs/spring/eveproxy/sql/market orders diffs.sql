select
	gen.date,
	coalesce(aggreg.nb, 0) nb
from 
(select	
	date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % 10) * interval '1 minute' date,
	count(*) nb
from
	jcelechat_trade_ordersale
where
	date_max> now() - 24*interval '1 hour'
group by
	date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % 10) * interval '1 minute'
order by
	date_trunc('minute', date_max) - (CAST(EXTRACT(MINUTE FROM date_max) AS integer) % 10) * interval '1 minute'
) aggreg
right join
(select generate_series(
	date_trunc('hour', now() - 24*interval '1 hours'),
	now(),
	interval '10 minute') date
) gen
on gen.date=aggreg.date
;