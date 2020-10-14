package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_graphics_graphic_id {
    /**
     * collision_file string
     */
    public String collision_file;
    /**
     * graphic_file string
     */
    public String graphic_file;
    /**
     * graphic_id integer
     */
    public int graphic_id;
    /**
     * icon_folder string
     */
    public String icon_folder;
    /**
     * sof_dna string
     */
    public String sof_dna;
    /**
     * sof_fation_name string
     */
    public String sof_fation_name;
    /**
     * sof_hull_name string
     */
    public String sof_hull_name;
    /**
     * sof_race_name string
     */
    public String sof_race_name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_graphics_graphic_id othersame = ((R_get_universe_graphics_graphic_id) other);
        if ((collision_file!= othersame.collision_file)&&((collision_file == null)||(!collision_file.equals(othersame.collision_file)))) {
            return false;
        }
        if ((graphic_file!= othersame.graphic_file)&&((graphic_file == null)||(!graphic_file.equals(othersame.graphic_file)))) {
            return false;
        }
        if (graphic_id!= othersame.graphic_id) {
            return false;
        }
        if ((icon_folder!= othersame.icon_folder)&&((icon_folder == null)||(!icon_folder.equals(othersame.icon_folder)))) {
            return false;
        }
        if ((sof_dna!= othersame.sof_dna)&&((sof_dna == null)||(!sof_dna.equals(othersame.sof_dna)))) {
            return false;
        }
        if ((sof_fation_name!= othersame.sof_fation_name)&&((sof_fation_name == null)||(!sof_fation_name.equals(othersame.sof_fation_name)))) {
            return false;
        }
        if ((sof_hull_name!= othersame.sof_hull_name)&&((sof_hull_name == null)||(!sof_hull_name.equals(othersame.sof_hull_name)))) {
            return false;
        }
        if ((sof_race_name!= othersame.sof_race_name)&&((sof_race_name == null)||(!sof_race_name.equals(othersame.sof_race_name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((collision_file == null)? 0 :collision_file.hashCode())+((graphic_file == null)? 0 :graphic_file.hashCode()))+ graphic_id)+((icon_folder == null)? 0 :icon_folder.hashCode()))+((sof_dna == null)? 0 :sof_dna.hashCode()))+((sof_fation_name == null)? 0 :sof_fation_name.hashCode()))+((sof_hull_name == null)? 0 :sof_hull_name.hashCode()))+((sof_race_name == null)? 0 :sof_race_name.hashCode()));
    }
}
