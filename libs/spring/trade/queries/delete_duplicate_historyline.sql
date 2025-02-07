

-- check the issue
select
	fetch_resource_id, date, count(*)
from
	esi_trade_historyline
group by fetch_resource_id, date
having(count(*)>1)
order by count(*) desc
;

-- delete the lines
delete from esi_trade_historyline
where id not in 
	(select
		min(id)
	from esi_trade_historyline
	group by fetch_resource_id, date)
;