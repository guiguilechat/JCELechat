package fr.guiguilechat.jcelechat.model.sde;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;

public abstract class EveType {
    public int id;
    public int marketGroup;
    public BigDecimal mass;
    public String name;
    public BigDecimal packagedVolume;
    public int portionSize;
    public BigDecimal price;
    public boolean published;
    public BigDecimal radius;
    public BigDecimal volume;

    public abstract IMetaGroup<?> getGroup();

    public int getGroupId() {
        return getGroup().getGroupId();
    }

    public abstract IMetaCategory<?> getCategory();

    public int getCategoryId() {
        return getCategory().getCategoryId();
    }

    /**
     * get the value affected to this for given attribute, or null if not set
     */
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  4 :
            {
                return mass;
            }
            default:
            {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return (name + "(" + id + ")");
    }

    public void loadDefault() {
        for (Field f: (getClass().getFields())) {
            DefaultRealValue annotDouble = f.getAnnotation(DefaultRealValue.class);
            if (annotDouble!= (null)) {
                try {
                    f.setAccessible(true);
                    f.set(this, annotDouble.value());
                } catch (final Exception ex) {
                }
            } else {
                DefaultIntValue annotLong = f.getAnnotation(DefaultIntValue.class);
                if (annotLong!= (null)) {
                    try {
                        f.setAccessible(true);
                        f.set(this, annotLong.value());
                    } catch (final Exception ex) {
                    }
                }
            }
        }
    }

    /**
     * list all the attributes that are set for this type
     */
    public abstract Set<Attribute> getAttributes();
}
