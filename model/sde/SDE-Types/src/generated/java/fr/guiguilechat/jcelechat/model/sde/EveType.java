package fr.guiguilechat.jcelechat.model.sde;

import java.lang.reflect.Field;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;

public abstract class EveType {
    public int id;
    public int marketGroup;
    public double mass;
    public String name;
    public double packagedVolume;
    public int portionSize;
    public double price;
    public boolean published;
    public double volume;

    public abstract IMetaGroup<?> getGroup();

    public int getGroupId() {
        return getGroup().getGroupId();
    }

    public abstract IMetaCategory<?> getCategory();

    public int getCategoryId() {
        return getCategory().getCategoryId();
    }

    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            default:
            {
                throw new UnsupportedOperationException(((("can't load attribute id "+ attribute.getId())+" on type id ")+(((id)+" ")+(name))));
            }
        }
    }

    @Override
    public String toString() {
        return (name + "(" + id + ")");
    }

    public void loadDefault() {
        for (Field f: (getClass().getFields())) {
            DefaultDoubleValue annotDouble = f.getAnnotation(DefaultDoubleValue.class);
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
}
