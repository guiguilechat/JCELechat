package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class M_get_blueprints_8 {
    /**
     * Unique ID for this item.
     */
    public long item_id;
    /**
     * Type of the location_id
     */
    public String location_flag;
    /**
     * References a solar system, station or item_id if this blueprint is located within a container. If the return value is an item_id, then the Character AssetList API must be queried to find the container using the given item_id to determine the correct location of the Blueprint.
     */
    public long location_id;
    /**
     * Material Efficiency Level of the blueprint.
     */
    public int material_efficiency;
    /**
     * A range of numbers with a minimum of -2 and no maximum value where -1 is an original and -2 is a copy. It can be a positive integer if it is a stack of blueprint originals fresh from the market (e.g. no activities performed on them yet).
     */
    public int quantity;
    /**
     * Number of runs remaining if the blueprint is a copy, -1 if it is an original.
     */
    public int runs;
    /**
     * Time Efficiency Level of the blueprint.
     */
    public int time_efficiency;
    /**
     * type_id integer
     */
    public int type_id;
}
