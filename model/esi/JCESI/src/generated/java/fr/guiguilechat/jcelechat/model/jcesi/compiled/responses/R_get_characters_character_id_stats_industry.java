package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_stats_industry {
    /**
     * hacking_successes integer
     */
    public long hacking_successes;
    /**
     * jobs_cancelled integer
     */
    public long jobs_cancelled;
    /**
     * jobs_completed_copy_blueprint integer
     */
    public long jobs_completed_copy_blueprint;
    /**
     * jobs_completed_invention integer
     */
    public long jobs_completed_invention;
    /**
     * jobs_completed_manufacture integer
     */
    public long jobs_completed_manufacture;
    /**
     * jobs_completed_manufacture_asteroid integer
     */
    public long jobs_completed_manufacture_asteroid;
    /**
     * jobs_completed_manufacture_asteroid_quantity integer
     */
    public long jobs_completed_manufacture_asteroid_quantity;
    /**
     * jobs_completed_manufacture_charge integer
     */
    public long jobs_completed_manufacture_charge;
    /**
     * jobs_completed_manufacture_charge_quantity integer
     */
    public long jobs_completed_manufacture_charge_quantity;
    /**
     * jobs_completed_manufacture_commodity integer
     */
    public long jobs_completed_manufacture_commodity;
    /**
     * jobs_completed_manufacture_commodity_quantity integer
     */
    public long jobs_completed_manufacture_commodity_quantity;
    /**
     * jobs_completed_manufacture_deployable integer
     */
    public long jobs_completed_manufacture_deployable;
    /**
     * jobs_completed_manufacture_deployable_quantity integer
     */
    public long jobs_completed_manufacture_deployable_quantity;
    /**
     * jobs_completed_manufacture_drone integer
     */
    public long jobs_completed_manufacture_drone;
    /**
     * jobs_completed_manufacture_drone_quantity integer
     */
    public long jobs_completed_manufacture_drone_quantity;
    /**
     * jobs_completed_manufacture_implant integer
     */
    public long jobs_completed_manufacture_implant;
    /**
     * jobs_completed_manufacture_implant_quantity integer
     */
    public long jobs_completed_manufacture_implant_quantity;
    /**
     * jobs_completed_manufacture_module integer
     */
    public long jobs_completed_manufacture_module;
    /**
     * jobs_completed_manufacture_module_quantity integer
     */
    public long jobs_completed_manufacture_module_quantity;
    /**
     * jobs_completed_manufacture_other integer
     */
    public long jobs_completed_manufacture_other;
    /**
     * jobs_completed_manufacture_other_quantity integer
     */
    public long jobs_completed_manufacture_other_quantity;
    /**
     * jobs_completed_manufacture_ship integer
     */
    public long jobs_completed_manufacture_ship;
    /**
     * jobs_completed_manufacture_ship_quantity integer
     */
    public long jobs_completed_manufacture_ship_quantity;
    /**
     * jobs_completed_manufacture_structure integer
     */
    public long jobs_completed_manufacture_structure;
    /**
     * jobs_completed_manufacture_structure_quantity integer
     */
    public long jobs_completed_manufacture_structure_quantity;
    /**
     * jobs_completed_manufacture_subsystem integer
     */
    public long jobs_completed_manufacture_subsystem;
    /**
     * jobs_completed_manufacture_subsystem_quantity integer
     */
    public long jobs_completed_manufacture_subsystem_quantity;
    /**
     * jobs_completed_material_productivity integer
     */
    public long jobs_completed_material_productivity;
    /**
     * jobs_completed_time_productivity integer
     */
    public long jobs_completed_time_productivity;
    /**
     * jobs_started_copy_blueprint integer
     */
    public long jobs_started_copy_blueprint;
    /**
     * jobs_started_invention integer
     */
    public long jobs_started_invention;
    /**
     * jobs_started_manufacture integer
     */
    public long jobs_started_manufacture;
    /**
     * jobs_started_material_productivity integer
     */
    public long jobs_started_material_productivity;
    /**
     * jobs_started_time_productivity integer
     */
    public long jobs_started_time_productivity;
    /**
     * reprocess_item integer
     */
    public long reprocess_item;
    /**
     * reprocess_item_quantity integer
     */
    public long reprocess_item_quantity;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_industry othersame = ((R_get_characters_character_id_stats_industry) other);
        if (hacking_successes!= othersame.hacking_successes) {
            return false;
        }
        if (jobs_cancelled!= othersame.jobs_cancelled) {
            return false;
        }
        if (jobs_completed_copy_blueprint!= othersame.jobs_completed_copy_blueprint) {
            return false;
        }
        if (jobs_completed_invention!= othersame.jobs_completed_invention) {
            return false;
        }
        if (jobs_completed_manufacture!= othersame.jobs_completed_manufacture) {
            return false;
        }
        if (jobs_completed_manufacture_asteroid!= othersame.jobs_completed_manufacture_asteroid) {
            return false;
        }
        if (jobs_completed_manufacture_asteroid_quantity!= othersame.jobs_completed_manufacture_asteroid_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_charge!= othersame.jobs_completed_manufacture_charge) {
            return false;
        }
        if (jobs_completed_manufacture_charge_quantity!= othersame.jobs_completed_manufacture_charge_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_commodity!= othersame.jobs_completed_manufacture_commodity) {
            return false;
        }
        if (jobs_completed_manufacture_commodity_quantity!= othersame.jobs_completed_manufacture_commodity_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_deployable!= othersame.jobs_completed_manufacture_deployable) {
            return false;
        }
        if (jobs_completed_manufacture_deployable_quantity!= othersame.jobs_completed_manufacture_deployable_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_drone!= othersame.jobs_completed_manufacture_drone) {
            return false;
        }
        if (jobs_completed_manufacture_drone_quantity!= othersame.jobs_completed_manufacture_drone_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_implant!= othersame.jobs_completed_manufacture_implant) {
            return false;
        }
        if (jobs_completed_manufacture_implant_quantity!= othersame.jobs_completed_manufacture_implant_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_module!= othersame.jobs_completed_manufacture_module) {
            return false;
        }
        if (jobs_completed_manufacture_module_quantity!= othersame.jobs_completed_manufacture_module_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_other!= othersame.jobs_completed_manufacture_other) {
            return false;
        }
        if (jobs_completed_manufacture_other_quantity!= othersame.jobs_completed_manufacture_other_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_ship!= othersame.jobs_completed_manufacture_ship) {
            return false;
        }
        if (jobs_completed_manufacture_ship_quantity!= othersame.jobs_completed_manufacture_ship_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_structure!= othersame.jobs_completed_manufacture_structure) {
            return false;
        }
        if (jobs_completed_manufacture_structure_quantity!= othersame.jobs_completed_manufacture_structure_quantity) {
            return false;
        }
        if (jobs_completed_manufacture_subsystem!= othersame.jobs_completed_manufacture_subsystem) {
            return false;
        }
        if (jobs_completed_manufacture_subsystem_quantity!= othersame.jobs_completed_manufacture_subsystem_quantity) {
            return false;
        }
        if (jobs_completed_material_productivity!= othersame.jobs_completed_material_productivity) {
            return false;
        }
        if (jobs_completed_time_productivity!= othersame.jobs_completed_time_productivity) {
            return false;
        }
        if (jobs_started_copy_blueprint!= othersame.jobs_started_copy_blueprint) {
            return false;
        }
        if (jobs_started_invention!= othersame.jobs_started_invention) {
            return false;
        }
        if (jobs_started_manufacture!= othersame.jobs_started_manufacture) {
            return false;
        }
        if (jobs_started_material_productivity!= othersame.jobs_started_material_productivity) {
            return false;
        }
        if (jobs_started_time_productivity!= othersame.jobs_started_time_productivity) {
            return false;
        }
        if (reprocess_item!= othersame.reprocess_item) {
            return false;
        }
        if (reprocess_item_quantity!= othersame.reprocess_item_quantity) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((Long.hashCode(hacking_successes)+ Long.hashCode(jobs_cancelled))+ Long.hashCode(jobs_completed_copy_blueprint))+ Long.hashCode(jobs_completed_invention))+ Long.hashCode(jobs_completed_manufacture))+ Long.hashCode(jobs_completed_manufacture_asteroid))+ Long.hashCode(jobs_completed_manufacture_asteroid_quantity))+ Long.hashCode(jobs_completed_manufacture_charge))+ Long.hashCode(jobs_completed_manufacture_charge_quantity))+ Long.hashCode(jobs_completed_manufacture_commodity))+ Long.hashCode(jobs_completed_manufacture_commodity_quantity))+ Long.hashCode(jobs_completed_manufacture_deployable))+ Long.hashCode(jobs_completed_manufacture_deployable_quantity))+ Long.hashCode(jobs_completed_manufacture_drone))+ Long.hashCode(jobs_completed_manufacture_drone_quantity))+ Long.hashCode(jobs_completed_manufacture_implant))+ Long.hashCode(jobs_completed_manufacture_implant_quantity))+ Long.hashCode(jobs_completed_manufacture_module))+ Long.hashCode(jobs_completed_manufacture_module_quantity))+ Long.hashCode(jobs_completed_manufacture_other))+ Long.hashCode(jobs_completed_manufacture_other_quantity))+ Long.hashCode(jobs_completed_manufacture_ship))+ Long.hashCode(jobs_completed_manufacture_ship_quantity))+ Long.hashCode(jobs_completed_manufacture_structure))+ Long.hashCode(jobs_completed_manufacture_structure_quantity))+ Long.hashCode(jobs_completed_manufacture_subsystem))+ Long.hashCode(jobs_completed_manufacture_subsystem_quantity))+ Long.hashCode(jobs_completed_material_productivity))+ Long.hashCode(jobs_completed_time_productivity))+ Long.hashCode(jobs_started_copy_blueprint))+ Long.hashCode(jobs_started_invention))+ Long.hashCode(jobs_started_manufacture))+ Long.hashCode(jobs_started_material_productivity))+ Long.hashCode(jobs_started_time_productivity))+ Long.hashCode(reprocess_item))+ Long.hashCode(reprocess_item_quantity));
    }
}
