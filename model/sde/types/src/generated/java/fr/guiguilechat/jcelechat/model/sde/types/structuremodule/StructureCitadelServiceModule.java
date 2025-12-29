package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;

public class StructureCitadelServiceModule
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype10;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype11;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype5;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype6;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype7;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype8;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype9;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CanFitShipType11 .INSTANCE, Cpu.INSTANCE, CanFitShipGroup01 .INSTANCE, CanFitShipGroup02 .INSTANCE, ServiceModuleFullPowerStateArmorPlatingMultiplier.INSTANCE, CanFitShipGroup03 .INSTANCE, CanFitShipType1 .INSTANCE, CanFitShipType8 .INSTANCE, CanFitShipType2 .INSTANCE, CanFitShipType6 .INSTANCE, CanFitShipType9 .INSTANCE, CanFitShipType3 .INSTANCE, CanFitShipType5 .INSTANCE, CanFitShipType10 .INSTANCE, ServiceModuleFullPowerStateHitpointMultiplier.INSTANCE, CanFitShipType4 .INSTANCE, ServiceModuleFuelConsumptionGroup.INSTANCE, ServiceModuleFuelAmount.INSTANCE, Power.INSTANCE, ServiceModuleFuelOnlineAmount.INSTANCE, StructureItemVisualFlag.INSTANCE, MaxTypeFitted.INSTANCE, CanFitShipType7 .INSTANCE })));
    public static final StructureCitadelServiceModule.MetaGroup METAGROUP = new StructureCitadelServiceModule.MetaGroup();

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
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  2488 :
            {
                return canfitshiptype10;
            }
            case  2758 :
            {
                return canfitshiptype11;
            }
            case  1303 :
            {
                return canfitshiptype2;
            }
            case  1304 :
            {
                return canfitshiptype3;
            }
            case  1305 :
            {
                return canfitshiptype4;
            }
            case  1944 :
            {
                return canfitshiptype5;
            }
            case  2103 :
            {
                return canfitshiptype6;
            }
            case  2463 :
            {
                return canfitshiptype7;
            }
            case  2486 :
            {
                return canfitshiptype8;
            }
            case  2487 :
            {
                return canfitshiptype9;
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
    public IMetaGroup<StructureCitadelServiceModule> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureCitadelServiceModule>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureCitadelServiceModule.yaml";
        private Map<Integer, StructureCitadelServiceModule> cache = (null);

        @Override
        public IMetaCategory<? super StructureCitadelServiceModule> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1321;
        }

        @Override
        public String getName() {
            return "StructureCitadelServiceModule";
        }

        @Override
        public synchronized Map<Integer, StructureCitadelServiceModule> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureCitadelServiceModule.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, StructureCitadelServiceModule> types;
        }
    }
}
