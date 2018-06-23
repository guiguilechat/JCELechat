package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporation_corporation_id_mining_extractions {
    /**
     * The time at which the chunk being extracted will arrive and can be fractured by the moon mining drill.
     * 
     */
    public String chunk_arrival_time;
    /**
     * The time at which the current extraction was initiated.
     * 
     */
    public String extraction_start_time;
    /**
     * moon_id integer
     */
    public int moon_id;
    /**
     * The time at which the chunk being extracted will naturally fracture if it is not first fractured by the moon mining drill.
     * 
     */
    public String natural_decay_time;
    /**
     * structure_id integer
     */
    public long structure_id;
}
