package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class UnpublishedStructureModules
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup02;
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
     * Fuel consumed at the beginning of each hour to keep a service module online.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ServiceModuleFuelAmount;
    /**
     * Fuel consumed by the structure service module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ServiceModuleFuelConsumptionGroup;
    /**
     * Fuel consumed to online the service module.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ServiceModuleFuelOnlineAmount;
    public final static UnpublishedStructureModules.MetaGroup METAGROUP = new UnpublishedStructureModules.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1298 :
            {
                return CanFitShipGroup01;
            }
            case  1299 :
            {
                return CanFitShipGroup02;
            }
            case  50 :
            {
                return Cpu;
            }
            case  30 :
            {
                return Power;
            }
            case  2109 :
            {
                return ServiceModuleFuelAmount;
            }
            case  2108 :
            {
                return ServiceModuleFuelConsumptionGroup;
            }
            case  2110 :
            {
                return ServiceModuleFuelOnlineAmount;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<UnpublishedStructureModules> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<UnpublishedStructureModules>
    {
        public final static String RESOURCE_PATH = "SDE/items/structuremodule/UnpublishedStructureModules.yaml";
        private Map<String, UnpublishedStructureModules> cache = (null);

        @Override
        public IMetaCategory<? super UnpublishedStructureModules> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1717;
        }

        @Override
        public String getName() {
            return "UnpublishedStructureModules";
        }

        @Override
        public synchronized Map<String, UnpublishedStructureModules> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(UnpublishedStructureModules.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, UnpublishedStructureModules> items;
        }
    }
}
