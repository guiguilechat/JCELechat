
-- get all the ships with their groups

select type.*, grp.name as group_name
from 
    base_types type
    join base_groups grp on type.group_id=grp.group_id 
    join base_categories cat on grp.category_id=cat.category_id 
where cat.name='Ship' order by type.name


-- all ship attributes

select distinct att.attribute_id , att.name
from 
    base_types type 
    join base_groups grp on type.group_id=grp.group_id
    join base_categories cat on grp.category_id=cat.category_id
    join base_type_attributes value on value.type_id=type.type_id
    join base_attributes att on value.attribute_id=att.attribute_id
where cat.name='Ship' order by att.attribute_id

-- select the ships, the group, and two attributes
select 
    type.*, 
    grp.name as group_name,
    lowslots.value as lowslots,
    agility.value as agility
from 
    base_types type
    join base_groups grp on type.group_id=grp.group_id 
    join base_categories cat on grp.category_id=cat.category_id
    left join base_type_attributes lowslots on lowslots.type_id = type.type_id and lowslots.attribute_id=12
    left join base_type_attributes agility on agility.type_id = type.type_id and agility.attribute_id=70
    
where cat.name='Ship' order by type.name


-- create non stored view 

create or replace view CAT_SHIPS as
select 
    type.*, 
    grp.name as group_name,
    lowslots.value as lowslots,
    agility.value as agility,
    requiredskill1.value as requiredskill1,
    requiredskill1level.value as requiredskill1level,
    requiredskill2.value as requiredskill2,
    requiredskill2level.value as requiredskill2level
from 
    base_types type
    join base_groups grp on type.group_id=grp.group_id 
    join base_categories cat on grp.category_id=cat.category_id
    left join base_type_attributes lowslots on lowslots.type_id = type.type_id and lowslots.attribute_id=12
    left join base_type_attributes agility on agility.type_id = type.type_id and agility.attribute_id=70
    left join base_type_attributes requiredskill1 on requiredskill1.type_id = type.type_id and requiredskill1.attribute_id=182
    left join base_type_attributes requiredskill1level on requiredskill1level.type_id = type.type_id and requiredskill1level.attribute_id=277
    left join base_type_attributes requiredskill2 on requiredskill2.type_id = type.type_id and requiredskill2.attribute_id=183
    left join base_type_attributes requiredskill2level on requiredskill2level.type_id = type.type_id and requiredskill2level.attribute_id=278
where cat.name='Ship'

-- WiP dynamic generation of the table

CREATE OR REPLACE Procedure UpdateCat
   ( cat_id IN number )
is
    quer clob:=to_clob('create or replace view ');
    cat_name varchar2(4000) ;
    cols clob;
    joins clob;
    att_name varchar2(4000);
    att_id number;
    cursor atts is
        select 
            distinct att.attribute_id , att.name
        from 
            base_types type 
            join base_groups grp on type.group_id=grp.group_id
            join base_categories cat on grp.category_id=cat.category_id
            join base_type_attributes value on value.type_id=type.type_id
            join base_attributes att on value.attribute_id=att.attribute_id
        where 
            cat.category_id=cat_id
        order by 
            att.name
    ;
BEGIN
    select cat.name into cat_name from base_categories cat where cat.category_id=cat_id; 
    dbms_lob.append(quer,cat_name);
    dbms_lob.append(quer,' as select '||cat_id||' as cat_id from dual');
    
    --OPEN atts;
    --LOOP
    --    FETCH atts INTO att_id,att_name;
    --    EXIT WHEN atts%NOTFOUND;
    --END LOOP;
    EXECUTE IMMEDIATE quer;
END;

