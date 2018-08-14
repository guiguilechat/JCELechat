package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class FestivalLauncher
    extends Module
{
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeGroup1;
    /**
     * Number of charges consumed per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ChargeRate;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * reload time (ms)
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(10000.0)
    public double ReloadTime;
    /**
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int Slots;
    /**
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int Speed;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int TypeColorScheme;
    public final static FestivalLauncher.MetaGroup METAGROUP = new FestivalLauncher.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  604 :
            {
                return ChargeGroup1;
            }
            case  56 :
            {
                return ChargeRate;
            }
            case  50 :
            {
                return Cpu;
            }
            case  30 :
            {
                return Power;
            }
            case  1795 :
            {
                return ReloadTime;
            }
            case  47 :
            {
                return Slots;
            }
            case  51 :
            {
                return Speed;
            }
            case  1768 :
            {
                return TypeColorScheme;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<FestivalLauncher> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FestivalLauncher>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/FestivalLauncher.yaml";
        private Map<String, FestivalLauncher> cache = (null);

        @Override
        public IMetaCategory<? super FestivalLauncher> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  501;
        }

        @Override
        public String getName() {
            return "FestivalLauncher";
        }

        @Override
        public synchronized Map<String, FestivalLauncher> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(FestivalLauncher.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, FestivalLauncher> items;
        }
    }
}
