
package fr.guiguilechat.eveonline.model.sde.items;

import java.lang.reflect.Field;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;

public abstract class Item {

    public int id;
    public double volume;

    public abstract int getCategoryId();

    public abstract Class<?> getCategory();

    public abstract int getGroupId();

    public abstract Class<?> getGroup();

    public void loadDefault() {
        for (Field f: (getClass().getFields())) {
            DefaultValue annot = f.getAnnotation(DefaultValue.class);
            if (annot!= (null)) {
                try {
                    f.setAccessible(true);
                    f.set(this, annot.value());
                } catch (Exception _x) {
                }
            }
        }
    }

}
