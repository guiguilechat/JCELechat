insert into sde_items_type(
	id
	,faction_id
	, graphic_id
	, icon_id
	, portion_size
	, published
	, race_id
	, sound_id
)
select 
	id
	, 0
	, graphic_id
	, icon_id
	, portion_size
	, published
	, 0
	, 0
from
	esi_items_type 