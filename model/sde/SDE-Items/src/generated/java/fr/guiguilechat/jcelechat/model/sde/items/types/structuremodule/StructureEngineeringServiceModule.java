package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureEngineeringServiceModule
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup03;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType10;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType5;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType6;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType7;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType8;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType9;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInEmpireSpace;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInHighSec;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxTypeFitted;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * Determines the maximum security class that a module can be onlined within. Used for structure modules.
     * 
     *  0=Nullsec
     *  1=Lowsec
     *  2=Highsec
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int OnlineMaxSecurityClass;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiresSovUpgrade1;
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
    /**
     * This attribute is authored on structure service modules and when the service module is online will be used to overwrite a hitpoint multiplier attribute on the structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ServiceModuleFullPowerStateHitpointMultiplier;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureItemVisualFlag;
    public final static StructureEngineeringServiceModule.MetaGroup METAGROUP = new StructureEngineeringServiceModule.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/structuremodule/StructureEngineeringServiceModule.yaml";
    private static Map<String, StructureEngineeringServiceModule> cache = (null);

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
            case  1300 :
            {
                return CanFitShipGroup03;
            }
            case  1302 :
            {
                return CanFitShipType1;
            }
            case  2488 :
            {
                return CanFitShipType10;
            }
            case  1303 :
            {
                return CanFitShipType2;
            }
            case  1304 :
            {
                return CanFitShipType3;
            }
            case  1305 :
            {
                return CanFitShipType4;
            }
            case  1944 :
            {
                return CanFitShipType5;
            }
            case  2103 :
            {
                return CanFitShipType6;
            }
            case  2463 :
            {
                return CanFitShipType7;
            }
            case  2486 :
            {
                return CanFitShipType8;
            }
            case  2487 :
            {
                return CanFitShipType9;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1074 :
            {
                return DisallowInEmpireSpace;
            }
            case  1970 :
            {
                return DisallowInHighSec;
            }
            case  2431 :
            {
                return MaxTypeFitted;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  2581 :
            {
                return OnlineMaxSecurityClass;
            }
            case  30 :
            {
                return Power;
            }
            case  1595 :
            {
                return RequiresSovUpgrade1;
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
            case  2744 :
            {
                return ServiceModuleFullPowerStateHitpointMultiplier;
            }
            case  2334 :
            {
                return StructureItemVisualFlag;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1415;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<StructureEngineeringServiceModule> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, StructureEngineeringServiceModule> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureEngineeringServiceModule.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, StructureEngineeringServiceModule> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<StructureEngineeringServiceModule>
    {

        @Override
        public MetaCategory<? super StructureEngineeringServiceModule> category() {
            return StructureModule.METACAT;
        }

        @Override
        public String getName() {
            return "StructureEngineeringServiceModule";
        }

        @Override
        public Collection<StructureEngineeringServiceModule> items() {
            return (load().values());
        }
    }
}
