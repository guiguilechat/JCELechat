package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AnchoringSecurityLevelMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTypeFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFuelAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFuelConsumptionGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFuelOnlineAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFullPowerStateArmorPlatingMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFullPowerStateHitpointMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureMoonDrillingServiceModule
    extends StructureModule
{
    /**
     * The maximum security level at which the structure can be anchored. Used as a non-functional display attribute on some deployables.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double anchoringsecuritylevelmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxtypefitted;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * Fuel consumed at the beginning of each hour to keep a service module online.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int servicemodulefuelamount;
    /**
     * Fuel consumed by the structure service module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int servicemodulefuelconsumptiongroup;
    /**
     * Fuel consumed to online the service module.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int servicemodulefuelonlineamount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int servicemodulefullpowerstatearmorplatingmultiplier;
    /**
     * This attribute is authored on structure service modules and when the service module is online will be used to overwrite a hitpoint multiplier attribute on the structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int servicemodulefullpowerstatehitpointmultiplier;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Capacity.INSTANCE, AnchoringSecurityLevelMax.INSTANCE, CanFitShipGroup01 .INSTANCE, Cpu.INSTANCE, ServiceModuleFullPowerStateArmorPlatingMultiplier.INSTANCE, ServiceModuleFullPowerStateHitpointMultiplier.INSTANCE, ServiceModuleFuelConsumptionGroup.INSTANCE, ServiceModuleFuelAmount.INSTANCE, ServiceModuleFuelOnlineAmount.INSTANCE, StructureItemVisualFlag.INSTANCE, Power.INSTANCE, MaxTypeFitted.INSTANCE })));
    public static final StructureMoonDrillingServiceModule.MetaGroup METAGROUP = new StructureMoonDrillingServiceModule.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1032 :
            {
                return anchoringsecuritylevelmax;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  38 :
            {
                return capacity;
            }
            case  50 :
            {
                return cpu;
            }
            case  2431 :
            {
                return maxtypefitted;
            }
            case  30 :
            {
                return power;
            }
            case  162 :
            {
                return radius;
            }
            case  2109 :
            {
                return servicemodulefuelamount;
            }
            case  2108 :
            {
                return servicemodulefuelconsumptiongroup;
            }
            case  2110 :
            {
                return servicemodulefuelonlineamount;
            }
            case  2804 :
            {
                return servicemodulefullpowerstatearmorplatingmultiplier;
            }
            case  2744 :
            {
                return servicemodulefullpowerstatehitpointmultiplier;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StructureMoonDrillingServiceModule> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureMoonDrillingServiceModule>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureMoonDrillingServiceModule.yaml";
        private Map<String, StructureMoonDrillingServiceModule> cache = (null);

        @Override
        public IMetaCategory<? super StructureMoonDrillingServiceModule> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1887;
        }

        @Override
        public String getName() {
            return "StructureMoonDrillingServiceModule";
        }

        @Override
        public synchronized Map<String, StructureMoonDrillingServiceModule> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureMoonDrillingServiceModule.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureMoonDrillingServiceModule> types;
        }
    }
}
