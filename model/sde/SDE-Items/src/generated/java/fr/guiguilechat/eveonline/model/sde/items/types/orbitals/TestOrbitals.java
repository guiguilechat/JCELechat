
package fr.guiguilechat.eveonline.model.sde.items.types.orbitals;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Orbitals;
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
    @DefaultIntValue(60000)
    public int UnanchoringDelay;
    /**
     * How long it takes to bring this object online.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int OnliningDelay;
    /**
     * The difficulty in opening this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double AccessDifficulty;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Uniformity;
    /**
     * How long it takes to anchor or unanchor this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int AnchoringDelay;
    public final static String RESOURCE_PATH = "SDE/items/orbitals/TestOrbitals.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(TestOrbitals.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, TestOrbitals> items;

    }

}
