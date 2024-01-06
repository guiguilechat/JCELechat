select
	date_trunc('hour',res.last_modified) modified_hour,
	sum(case when line.creation and not line.invalid then 1 else 0 end) created_nb,
	sum(case when line.price_chg and not line.invalid then 1 else 0 end) price_chg_nb,
	sum(case when line.sold>0 and not line.invalid then 1 else 0 end) sold_nb,
	sum(case when line.removal and not line.invalid then 1 else 0 end) removal_nb
from
	market_fetch_line line
	join market_fetch_result res ON res.id = line.fetch_result_id
where
	res.region_id=10000002
group by
	date_trunc('hour',res.last_modified)
order by
	date_trunc('hour',res.last_modified)
;