package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_industry_jobs_status;

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
    public get_characters_character_id_industry_jobs_status status;
    /**
     * Number of successful runs for this job. Equal to runs unless this is an invention job
     */
    public int successful_runs;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_industry_jobs othersame = ((R_get_characters_character_id_industry_jobs) other);
        if (activity_id!= othersame.activity_id) {
            return false;
        }
        if (blueprint_id!= othersame.blueprint_id) {
            return false;
        }
        if (blueprint_location_id!= othersame.blueprint_location_id) {
            return false;
        }
        if (blueprint_type_id!= othersame.blueprint_type_id) {
            return false;
        }
        if (completed_character_id!= othersame.completed_character_id) {
            return false;
        }
        if ((completed_date!= othersame.completed_date)&&((completed_date == null)||(!completed_date.equals(othersame.completed_date)))) {
            return false;
        }
        if (cost!= othersame.cost) {
            return false;
        }
        if (duration!= othersame.duration) {
            return false;
        }
        if ((end_date!= othersame.end_date)&&((end_date == null)||(!end_date.equals(othersame.end_date)))) {
            return false;
        }
        if (facility_id!= othersame.facility_id) {
            return false;
        }
        if (installer_id!= othersame.installer_id) {
            return false;
        }
        if (job_id!= othersame.job_id) {
            return false;
        }
        if (licensed_runs!= othersame.licensed_runs) {
            return false;
        }
        if (output_location_id!= othersame.output_location_id) {
            return false;
        }
        if ((pause_date!= othersame.pause_date)&&((pause_date == null)||(!pause_date.equals(othersame.pause_date)))) {
            return false;
        }
        if (probability!= othersame.probability) {
            return false;
        }
        if (product_type_id!= othersame.product_type_id) {
            return false;
        }
        if (runs!= othersame.runs) {
            return false;
        }
        if ((start_date!= othersame.start_date)&&((start_date == null)||(!start_date.equals(othersame.start_date)))) {
            return false;
        }
        if (station_id!= othersame.station_id) {
            return false;
        }
        if ((status!= othersame.status)&&((status == null)||(!status.equals(othersame.status)))) {
            return false;
        }
        if (successful_runs!= othersame.successful_runs) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((((((((((activity_id + Long.hashCode(blueprint_id))+ Long.hashCode(blueprint_location_id))+ blueprint_type_id)+ completed_character_id)+((completed_date == null)? 0 :completed_date.hashCode()))+ Double.hashCode(cost))+ duration)+((end_date == null)? 0 :end_date.hashCode()))+ Long.hashCode(facility_id))+ installer_id)+ job_id)+ licensed_runs)+ Long.hashCode(output_location_id))+((pause_date == null)? 0 :pause_date.hashCode()))+ Double.hashCode(probability))+ product_type_id)+ runs)+((start_date == null)? 0 :start_date.hashCode()))+ Long.hashCode(station_id))+((status == null)? 0 :status.hashCode()))+ successful_runs);
    }
}
