package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Outer
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
     * This item of clothing covers multiple areas of the body, so the category of clothes specified by this attribute is no longer mandatory
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ClothingAlsoCoversCategory2;
    /**
     * This item of clothing does not allow the category specified to be equipped with this item. It will be removed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ClothingRemovesCategory;
    /**
     * This item of clothing does not allow the category specified to be equipped with this item. It will be removed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ClothingRemovesCategory2;
    /**
     * When evaluating if the character is dressed well enough, this item will be evaluated as it's not equiped
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ClothingRuleException;
    public final static String RESOURCE_PATH = "SDE/items/apparel/Outer.yaml";
    private static Map<String, Outer> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1797 :
            {
                return ClothingAlsoCoversCategory;
            }
            case  2058 :
            {
                return ClothingAlsoCoversCategory2;
            }
            case  1956 :
            {
                return ClothingRemovesCategory;
            }
            case  2063 :
            {
                return ClothingRemovesCategory2;
            }
            case  1957 :
            {
                return ClothingRuleException;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1088;
    }

    @Override
    public Class<?> getGroup() {
        return Outer.class;
    }

    public static synchronized Map<String, Outer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Outer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Outer> items;
    }
}
