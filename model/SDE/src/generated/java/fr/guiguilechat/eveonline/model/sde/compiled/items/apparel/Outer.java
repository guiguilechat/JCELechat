
package fr.guiguilechat.eveonline.model.sde.compiled.items.apparel;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Outer
    extends Apparel
{

    /**
     * This item of clothing does not allow the category specified to be equipped with this item. It will be removed
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ClothingRemovesCategory;
    /**
     * This item of clothing covers multiple areas of the body, so the category of clothes specified by this attribute is no longer mandatory
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ClothingAlsoCoversCategory;
    /**
     * When evaluating if the character is dressed well enough, this item will be evaluated as it's not equiped
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ClothingRuleException;
    /**
     * This item of clothing covers multiple areas of the body, so the category of clothes specified by this attribute is no longer mandatory
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ClothingAlsoCoversCategory2;
    /**
     * This item of clothing does not allow the category specified to be equipped with this item. It will be removed
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ClothingRemovesCategory2;
    public final static String RESOURCE_PATH = "SDE/apparel/Outer.yaml";
    private static LinkedHashMap<String, Outer> cache = (null);

    @Override
    public int getGroupId() {
        return  1088;
    }

    @Override
    public Class<?> getGroup() {
        return Outer.class;
    }

    public static LinkedHashMap<String, Outer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Outer> items;

    }

}
