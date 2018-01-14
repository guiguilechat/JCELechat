
package fr.guiguilechat.eveonline.model.sde.compiled.items.orbitals;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Orbitals;
import org.yaml.snakeyaml.Yaml;

public class TestOrbitals
    extends Orbitals
{

    /**
     * How long it takes to unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double UnanchoringDelay;
    /**
     * How long it takes to bring this object online.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double OnliningDelay;
    /**
     * The difficulty in opening this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double AccessDifficulty;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Uniformity;
    /**
     * How long it takes to anchor or unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(60000.0D)
    public double AnchoringDelay;
    public final static String RESOURCE_PATH = "SDE/orbitals/TestOrbitals.yaml";
    private static LinkedHashMap<String, TestOrbitals> cache = (null);

    @Override
    public int getGroupId() {
        return  1073;
    }

    @Override
    public Class<?> getGroup() {
        return TestOrbitals.class;
    }

    public static LinkedHashMap<String, TestOrbitals> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, TestOrbitals> items;

    }

}
