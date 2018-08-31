package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_corporations_corporation_divisions_2 {
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
        M_get_corporations_corporation_divisions_2 othersame = ((M_get_corporations_corporation_divisions_2) other);
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
