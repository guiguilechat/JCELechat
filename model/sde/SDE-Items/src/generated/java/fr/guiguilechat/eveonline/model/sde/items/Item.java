
package fr.guiguilechat.eveonline.model.sde.items;

import java.lang.reflect.Field;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;

public abstract class Item {

    public int id;
    public double volume;

    public abstract int getCategoryId();

    public abstract Class<?> getCategory();

    public abstract int getGroupId();

    public abstract Class<?> getGroup();

    public void loadDefault() {
        for (Field f: (getClass().getFields())) {
            DefaultDoubleValue annotDouble = f.getAnnotation(DefaultDoubleValue.class);
            if (annotDouble!= (null)) {
                try {
                    f.setAccessible(true);
                    f.set(this, annotDouble.value());
                } catch (Exception _x) {
                }
            } else {
                DefaultIntValue annotLong = f.getAnnotation(DefaultIntValue.class);
                if (annotLong!= (null)) {
                    try {
                        f.setAccessible(true);
                        f.set(this, annotLong.value());
                    } catch (Exception _x) {
                    }
                }
            }
        }
    }

}
