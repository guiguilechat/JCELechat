package fr.guiguilechat.jcelechat.model.sde.types.charge;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseMaxScanDeviation;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseScanRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseSensorStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.ProbeCanScanShips;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RangeFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class ScannerProbe
    extends Charge
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double agility;
    /**
     * This is the lowest maximum scan deviation in AU for probes under the revised probing system
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double basemaxscandeviation;
    /**
     * This is the lowest scan range value in AUs for probes under the revised probing system
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double basescanrange;
    /**
     * This is the highest sensor strength in points for probes under the revised probing system
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int basesensorstrength;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int explosiondelay;
    /**
     * Range in meters of explosion effect area.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int explosionrange;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
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
    public int metagroupid;
    /**
     * If this is 1 then the probe can scan for ships, otherwise it can't.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int probecanscanships;
    /**
     * This is the multiplier/divisor for probe range increases and associated values under the revised probing system
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rangefactor;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(3.0)
    public double warpspeedmultiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, MaxVelocity.INSTANCE, ProbeCanScanShips.INSTANCE, Agility.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE, LauncherGroup.INSTANCE, ExplosionRange.INSTANCE, StructureUniformity.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, WarpSpeedMultiplier.INSTANCE, ExplosionDelay.INSTANCE, BaseScanRange.INSTANCE, BaseSensorStrength.INSTANCE, MetaGroupID.INSTANCE, BaseMaxScanDeviation.INSTANCE, RangeFactor.INSTANCE })));
    public static final ScannerProbe.MetaGroup METAGROUP = new ScannerProbe.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return agility;
            }
            case  1372 :
            {
                return basemaxscandeviation;
            }
            case  1370 :
            {
                return basescanrange;
            }
            case  1371 :
            {
                return basesensorstrength;
            }
            case  281 :
            {
                return explosiondelay;
            }
            case  107 :
            {
                return explosionrange;
            }
            case  9 :
            {
                return hp;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  1413 :
            {
                return probecanscanships;
            }
            case  1373 :
            {
                return rangefactor;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  422 :
            {
                return techlevel;
            }
            case  600 :
            {
                return warpspeedmultiplier;
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
    public IMetaGroup<ScannerProbe> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ScannerProbe>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/ScannerProbe.yaml";
        private Map<Integer, ScannerProbe> cache = (null);

        @Override
        public IMetaCategory<? super ScannerProbe> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  479;
        }

        @Override
        public String getName() {
            return "ScannerProbe";
        }

        @Override
        public synchronized Map<Integer, ScannerProbe> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ScannerProbe.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ScannerProbe> types;
        }
    }
}
