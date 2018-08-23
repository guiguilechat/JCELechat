package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_industry_jobs {
    /**
     * Job activity ID
     */
    public int activity_id;
    /**
     * blueprint_id integer
     */
    public long blueprint_id;
    /**
     * Location ID of the location from which the blueprint was installed. Normally a station ID, but can also be an asset (e.g. container) or corporation facility
     */
    public long blueprint_location_id;
    /**
     * blueprint_type_id integer
     */
    public int blueprint_type_id;
    /**
     * ID of the character which completed this job
     */
    public int completed_character_id;
    /**
     * Date and time when this job was completed
     */
    public String completed_date;
    /**
     * The sume of job installation fee and industry facility tax
     */
    public double cost;
    /**
     * Job duration in seconds
     */
    public int duration;
    /**
     * Date and time when this job finished
     */
    public String end_date;
    /**
     * ID of the facility where this job is running
     */
    public long facility_id;
    /**
     * ID of the character which installed this job
     */
    public int installer_id;
    /**
     * Unique job ID
     */
    public int job_id;
    /**
     * Number of runs blueprint is licensed for
     */
    public int licensed_runs;
    /**
     * Location ID of the location to which the output of the job will be delivered. Normally a station ID, but can also be a corporation facility
     */
    public long output_location_id;
    /**
     * Date and time when this job was paused (i.e. time when the facility where this job was installed went offline)
     */
    public String pause_date;
    /**
     * Chance of success for invention
     */
    public float probability;
    /**
     * Type ID of product (manufactured, copied or invented)
     */
    public int product_type_id;
    /**
     * Number of runs for a manufacturing job, or number of copies to make for a blueprint copy
     */
    public int runs;
    /**
     * Date and time when this job started
     */
    public String start_date;
    /**
     * ID of the station where industry facility is located
     */
    public long station_id;
    /**
     * status string
     */
    public String status;
    /**
     * Number of successful runs for this job. Equal to runs unless this is an invention job
     */
    public int successful_runs;
}
