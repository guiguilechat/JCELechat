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

public class CapitalSensorArray
    extends Module
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int CanCloak;
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
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * Stops the module from being activated if the ship is aligning to warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowActivateOnWarp;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowDocking;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowEarlyDeactivation;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowTethering;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * Bonus attribute for capacitor need of EW and propulsion jamming.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EwCapacitorNeedBonus;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupActive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanGravimetricStrengthPercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanLadarStrengthPercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanMagnetometricStrengthPercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanRadarStrengthPercent;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanResolutionBonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public static final CapitalSensorArray.MetaGroup METAGROUP = new CapitalSensorArray.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1163 :
            {
                return CanCloak;
            }
            case  1298 :
            {
                return CanFitShipGroup01;
            }
            case  1299 :
            {
                return CanFitShipGroup02;
            }
            case  6 :
            {
                return CapacitorNeed;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1245 :
            {
                return DisallowActivateOnWarp;
            }
            case  2354 :
            {
                return DisallowDocking;
            }
            case  906 :
            {
                return DisallowEarlyDeactivation;
            }
            case  2343 :
            {
                return DisallowTethering;
            }
            case  73 :
            {
                return Duration;
            }
            case  1190 :
            {
                return EwCapacitorNeedBonus;
            }
            case  763 :
            {
                return MaxGroupActive;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  1027 :
            {
                return ScanGravimetricStrengthPercent;
            }
            case  1028 :
            {
                return ScanLadarStrengthPercent;
            }
            case  1029 :
            {
                return ScanMagnetometricStrengthPercent;
            }
            case  1030 :
            {
                return ScanRadarStrengthPercent;
            }
            case  566 :
            {
                return ScanResolutionBonus;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<CapitalSensorArray> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CapitalSensorArray>
    {
        public static final String RESOURCE_PATH = "SDE/items/module/CapitalSensorArray.yaml";
        private Map<String, CapitalSensorArray> cache = (null);

        @Override
        public IMetaCategory<? super CapitalSensorArray> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1706;
        }

        @Override
        public String getName() {
            return "CapitalSensorArray";
        }

        @Override
        public synchronized Map<String, CapitalSensorArray> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CapitalSensorArray.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CapitalSensorArray> items;
        }
    }
}
