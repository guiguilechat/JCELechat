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
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup02;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup03;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffEffectiveness;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupOnline;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModuleReactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureStasisWebifier
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
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * distance from maximum range at which effectiveness has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int falloffeffectiveness;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupactive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Maximum modules of same group that can be onlined at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgrouponline;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Amount of time that has to be waited after the deactivation of this module until it can be reactivated.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int modulereactivationdelay;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * Attribute ID of the resistance type v's this Ewar module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remoteresistanceid;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, CapacitorNeed.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, Duration.INSTANCE, CanFitShipGroup01 .INSTANCE, Cpu.INSTANCE, MaxGroupOnline.INSTANCE, CanFitShipGroup02 .INSTANCE, SpeedFactor.INSTANCE, CanFitShipGroup03 .INSTANCE, MaxRange.INSTANCE, DisallowRepeatingActivation.INSTANCE, MetaLevelOld.INSTANCE, RemoteResistanceID.INSTANCE, MaxGroupActive.INSTANCE, FalloffEffectiveness.INSTANCE, ModuleReactivationDelay.INSTANCE, Power.INSTANCE, StructureItemVisualFlag.INSTANCE })));
    public static final StructureStasisWebifier.MetaGroup METAGROUP = new StructureStasisWebifier.MetaGroup();

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
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
            }
            case  73 :
            {
                return duration;
            }
            case  2044 :
            {
                return falloffeffectiveness;
            }
            case  9 :
            {
                return hp;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  978 :
            {
                return maxgrouponline;
            }
            case  54 :
            {
                return maxrange;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  669 :
            {
                return modulereactivationdelay;
            }
            case  30 :
            {
                return power;
            }
            case  2138 :
            {
                return remoteresistanceid;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaGroup<StructureStasisWebifier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureStasisWebifier>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureStasisWebifier.yaml";
        private Map<String, StructureStasisWebifier> cache = (null);

        @Override
        public IMetaCategory<? super StructureStasisWebifier> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1441;
        }

        @Override
        public String getName() {
            return "StructureStasisWebifier";
        }

        @Override
        public synchronized Map<String, StructureStasisWebifier> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureStasisWebifier.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StructureStasisWebifier> types;
        }
    }
}
