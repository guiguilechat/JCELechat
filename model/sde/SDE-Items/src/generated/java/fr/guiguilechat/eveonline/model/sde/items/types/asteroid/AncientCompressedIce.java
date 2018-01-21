
package fr.guiguilechat.eveonline.model.sde.items.types.asteroid;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class AncientCompressedIce
    extends Asteroid
{

    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1;
    /**
     * The skill required to reprocess this ore type.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ReprocessingSkillType;
    public final static String RESOURCE_PATH = "SDE/items/asteroid/AncientCompressedIce.yaml";
    private static LinkedHashMap<String, AncientCompressedIce> cache = (null);

    @Override
    public int getGroupId() {
        return  903;
    }

    @Override
    public Class<?> getGroup() {
        return AncientCompressedIce.class;
    }

    public static LinkedHashMap<String, AncientCompressedIce> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AncientCompressedIce.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AncientCompressedIce> items;

    }

}
