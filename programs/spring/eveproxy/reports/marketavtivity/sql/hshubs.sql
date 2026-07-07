select
--	ss.id sys_id,
	ss.name sys_name,	
	round(sum(volume_remain*price/100000000))/10 value_b,
	r.name reg_name,
	rank() over(partition by r.name order by sum(volume_remain*price) desc) regional_rank
from
	esi_trade_market_line line
	join sde_space_solarsystem ss on line.solar_system_id=ss.id
	join sde_space_constellation c on ss.constellation_id=c.id
	join sde_space_region r on c.region_id=r.id
	join sde_items_type t on line.type_id=t.id
	join sde_items_group g on t.group_id=g.id
where
	line.is_buy_order
	and line.duration<360
	and ss.security_status>0.45
	and g.category_id not in (
		5 -- acessories : plex, injectors, MTC
		,91 -- skins
		, 2100 -- expert systems
	)
group by 
	ss.id,
	ss.name,
	r.name
having
	sum(volume_remain*price)>1000000000
order by
	sum(volume_remain*price) desc