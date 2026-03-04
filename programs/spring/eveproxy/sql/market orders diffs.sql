select
	gen.date,
	coalesce(aggreg.nb, 0) nb
from 
(select	
	date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % 10) * interval '1 minute' date,
	count(*) nb
from
	jcelechat_trade_orderupdate
group by
	date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % 10) * interval '1 minute'
order by
	date_trunc('minute', date) - (CAST(EXTRACT(MINUTE FROM date) AS integer) % 10) * interval '1 minute'
) aggreg
right join
(select generate_series(
	date_trunc('hour', now() - 5*interval '1 days'),
	now(),
	interval '10 minute') date
) gen
on gen.date=aggreg.date
;