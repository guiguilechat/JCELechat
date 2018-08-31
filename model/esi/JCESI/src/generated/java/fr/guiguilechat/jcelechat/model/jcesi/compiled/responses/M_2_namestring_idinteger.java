package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_2_namestring_idinteger {
    /**
     * id integer
     */
    public long id;
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
        M_2_namestring_idinteger othersame = ((M_2_namestring_idinteger) other);
        if (id!= othersame.id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(id)+((name == null)? 0 :name.hashCode()));
    }
}
