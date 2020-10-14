package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporation_corporation_id_mining_extractions othersame = ((R_get_corporation_corporation_id_mining_extractions) other);
        if ((chunk_arrival_time!= othersame.chunk_arrival_time)&&((chunk_arrival_time == null)||(!chunk_arrival_time.equals(othersame.chunk_arrival_time)))) {
            return false;
        }
        if ((extraction_start_time!= othersame.extraction_start_time)&&((extraction_start_time == null)||(!extraction_start_time.equals(othersame.extraction_start_time)))) {
            return false;
        }
        if (moon_id!= othersame.moon_id) {
            return false;
        }
        if ((natural_decay_time!= othersame.natural_decay_time)&&((natural_decay_time == null)||(!natural_decay_time.equals(othersame.natural_decay_time)))) {
            return false;
        }
        if (structure_id!= othersame.structure_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((chunk_arrival_time == null)? 0 :chunk_arrival_time.hashCode())+((extraction_start_time == null)? 0 :extraction_start_time.hashCode()))+ moon_id)+((natural_decay_time == null)? 0 :natural_decay_time.hashCode()))+ Long.hashCode(structure_id));
    }
}
