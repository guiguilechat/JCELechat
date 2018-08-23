package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_incursions {
    /**
     * The constellation id in which this incursion takes place
     */
    public int constellation_id;
    /**
     * The attacking faction's id
     */
    public int faction_id;
    /**
     * Whether the final encounter has boss or not
     */
    public boolean has_boss;
    /**
     * A list of infested solar system ids that are a part of this incursion
     */
    public int[] infested_solar_systems;
    /**
     * Influence of this incursion as a float from 0 to 1
     */
    public float influence;
    /**
     * Staging solar system for this incursion
     */
    public int staging_solar_system_id;
    /**
     * The state of this incursion
     */
    public String state;
    /**
     * The type of this incursion
     */
    public String type;
}
