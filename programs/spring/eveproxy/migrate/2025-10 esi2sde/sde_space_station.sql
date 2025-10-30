insert into sde_space_station(
	id
	, celestial_index
	, orbit_id
	, orbit_index
	, reprocessing_hangar_flag
	, use_operation_name
)values(
	60000001
	, 0
	, 0
	, 0
	, 0
	, false
)

insert into sde_space_station(
	id
	, celestial_index
	, orbit_id
	, orbit_index
	, reprocessing_hangar_flag
	, use_operation_name
)
select
	id
	, 0
	, 0
	, 0
	, 0
	, false
from
	esi_universe_station