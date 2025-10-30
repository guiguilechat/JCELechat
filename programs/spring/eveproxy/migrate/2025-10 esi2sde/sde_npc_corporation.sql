ALTER TABLE sde_npc_corporation ALTER COLUMN station_id DROP NOT NULL;
ALTER TABLE sde_npc_corporation ALTER COLUMN corporation_id DROP NOT NULL;

insert into sde_npc_corporation(
	id
	, faction_id
	, tax_rate
) values
(1000002, 0, 0)
,(1000003, 0, 0)
ON CONFLICT (id) DO NOTHING;

update
	sde_npc_corporation
set
	station_id=null
where
	station_id=0
;
update
	sde_npc_corporation
set
	tax_rate=0
where
	tax_rate is null
;