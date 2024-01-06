package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_corporations_corporation_id_divisions_hangar {
    /**
     * division integer
     */
    public int division;
    /**
     * name string
     */
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_corporations_corporation_id_divisions_hangar othersame = ((get_corporations_corporation_id_divisions_hangar) other);
        if (division!= othersame.division) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (division +((name == null)? 0 :name.hashCode()));
    }
}
