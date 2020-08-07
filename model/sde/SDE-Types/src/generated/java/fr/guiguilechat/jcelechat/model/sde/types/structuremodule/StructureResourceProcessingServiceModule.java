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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup02;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup03;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInHighSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTypeFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnlineMaxSecurityClass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RefiningYieldIce;
import fr.guiguilechat.jcelechat.model.sde.attributes.RefiningYieldMoonOres;
import fr.guiguilechat.jcelechat.model.sde.attributes.RefiningYieldMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.RefiningYieldNormalOres;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFuelAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFuelConsumptionGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFuelOnlineAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFullPowerStateArmorPlatingMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ServiceModuleFullPowerStateHitpointMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureResourceProcessingServiceModule
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup02;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup03;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double cpu;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxtypefitted;
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
    public int onlinemaxsecurityclass;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.5)
    public double refiningyieldice;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.5)
    public double refiningyieldmoonores;
    /**
     * The factor by which the structure modifies the using pilot's refining yield rate.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.5)
    public double refiningyieldmultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.5)
    public double refiningyieldnormalores;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE, RefiningYieldNormalOres.INSTANCE, RefiningYieldMultiplier.INSTANCE, RefiningYieldMoonOres.INSTANCE, RefiningYieldIce.INSTANCE, CanFitShipGroup01 .INSTANCE, Cpu.INSTANCE, DisallowInHighSec.INSTANCE, CanFitShipGroup02 .INSTANCE, ServiceModuleFullPowerStateArmorPlatingMultiplier.INSTANCE, CanFitShipGroup03 .INSTANCE, OnlineMaxSecurityClass.INSTANCE, ServiceModuleFullPowerStateHitpointMultiplier.INSTANCE, ServiceModuleFuelConsumptionGroup.INSTANCE, ServiceModuleFuelAmount.INSTANCE, ServiceModuleFuelOnlineAmount.INSTANCE, Power.INSTANCE, StructureItemVisualFlag.INSTANCE, MaxTypeFitted.INSTANCE })));
    public static final StructureResourceProcessingServiceModule.MetaGroup METAGROUP = new StructureResourceProcessingServiceModule.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  1300 :
            {
                return canfitshipgroup03;
            }
            case  50 :
            {
                return cpu;
            }
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  2431 :
            {
                return maxtypefitted;
            }
            case  2581 :
            {
                return onlinemaxsecurityclass;
            }
            case  30 :
            {
                return power;
            }
            case  2448 :
            {
                return refiningyieldice;
            }
            case  2445 :
            {
                return refiningyieldmoonores;
            }
            case  717 :
            {
                return refiningyieldmultiplier;
            }
            case  2444 :
            {
                return refiningyieldnormalores;
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
    public IMetaGroup<StructureResourceProcessingServiceModule> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureResourceProcessingServiceModule>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureResourceProcessingServiceModule.yaml";
        private Map<String, StructureResourceProcessingServiceModule> cache = (null);

        @Override
        public IMetaCategory<? super StructureResourceProcessingServiceModule> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1322;
        }

        @Override
        public String getName() {
            return "StructureResourceProcessingServiceModule";
        }

        @Override
        public synchronized Map<String, StructureResourceProcessingServiceModule> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureResourceProcessingServiceModule.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureResourceProcessingServiceModule> types;
        }
    }
}
