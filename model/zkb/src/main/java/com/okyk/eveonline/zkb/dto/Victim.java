package com.okyk.eveonline.zkb.dto;

import java.util.Collection;

public class Victim {
    public Long damage_taken;
    public Long ship_type_id;
    public Long character_id;
    public Long corporation_id;
    public Long alliance_id;
    public Collection<Item> items;
    public Position position;
}
