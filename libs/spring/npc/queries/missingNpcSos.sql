-- should select the items that are seeded by corporation but have no order in a station

select
	corp.id corp_id,
	corp.name corp_name,
	sta.id station_id,
	sta.name station_name,
	typ.id type_id,
	typ.name type_name,
	seed.price seed_value
from
	sde_npc_seeded seed
	join esi_items_type typ on seed.type_id=typ.id
	join esi_universe_station sta on seed.seeder_id=sta.owner_corporation_id
	left join esi_trade_market_line offer on offer.type_id=seed.type_id and offer.location_id=sta.id
	join esi_affiliations_corporationinfo corp on corp.id=seed.seeder_id
where
	offer.id is null
order by 
	corp.name, sta.name, typ.name