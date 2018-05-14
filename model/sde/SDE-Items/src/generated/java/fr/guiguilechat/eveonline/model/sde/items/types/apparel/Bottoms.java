package fr.guiguilechat.eveonline.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Bottoms
    extends Apparel
{
    /**
     * This item of clothing covers multiple areas of the body, so the category of clothes specified by this attribute is no longer mandatory
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ClothingAlsoCoversCategory;
    /**
     * This item of clothing does not allow the category specified to be equipped with this item. It will be removed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ClothingRemovesCategory;
    public final static String RESOURCE_PATH = "SDE/items/apparel/Bottoms.yaml";
    private static LinkedHashMap<String, Bottoms> cache = (null);

    public int attributeInt(IntAttribute attribute) {
        switch (attribute.getId()) {
            case  1797 :
            {
                return ClothingAlsoCoversCategory;
            }
            case  1956 :
            {
                return ClothingRemovesCategory;
            }
            default:
            {
                return super.attributeInt((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1090;
    }

    @Override
    public Class<?> getGroup() {
        return Bottoms.class;
    }

    public static synchronized LinkedHashMap<String, Bottoms> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Bottoms.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Bottoms> items;
    }
}
