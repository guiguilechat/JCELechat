package fr.guiguilechat.jcelechat.model.sde.items;

import java.lang.reflect.Field;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;

public abstract class Item {
    public int id;
    public double volume;
    public String name;
    public int marketGroup;

    public abstract int getCategoryId();

    public abstract Class<?> getCategory();

    public abstract int getGroupId();

    public abstract Class<?> getGroup();

    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            default:
            {
                throw new UnsupportedOperationException(((("can't load attribute id "+ attribute.getId())+" on type id ")+(((id)+" ")+(name))));
            }
        }
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
