
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class AuditLogSecureContainer
    extends Celestial
{

    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill1;
    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    /**
     * This attribute is not needed by default. You only need to add it if you want to stop something from being jettisoned.
     * 
     * The primary case for this was Station Containers.
     * 
     *  0 = Cannot be jettisoned.
     *  1 = Can be jettisoned.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double CanBeJettisoned;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double StructureUniformity;
    public final static String RESOURCE_PATH = "SDE/celestial/AuditLogSecureContainer.yaml";
    private static LinkedHashMap<String, AuditLogSecureContainer> cache = (null);

    @Override
    public int getGroupId() {
        return  448;
    }

    @Override
    public Class<?> getGroup() {
        return AuditLogSecureContainer.class;
    }

    public static LinkedHashMap<String, AuditLogSecureContainer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AuditLogSecureContainer> items;

    }

}
