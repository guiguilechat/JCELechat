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
import fr.guiguilechat.jcelechat.model.sde.attributes.AllowInFullyCorruptedLowSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInEmpireSpace;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOEDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOERange;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayWarningDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class InterdictionBurstProbes
    extends Charge
{
    /**
     * if the module is disallowed in low sec (empire space), if it also have this attribute, it will allow that module to be used in low sec system if the systems is fully corrupted
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int allowinfullycorruptedlowsec;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinempirespace;
    /**
     * Duration of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoeduration;
    /**
     * Radius of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoerange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdaywarningduration;
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
     * Attribute ID of the resistance type v's this Ewar module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remoteresistanceid;
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
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    /**
     * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double speedmultiplier;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DoomsdayAOERange.INSTANCE, SignatureRadius.INSTANCE, DoomsdayAOEDuration.INSTANCE, Hp.INSTANCE, LauncherGroup.INSTANCE, SpeedMultiplier.INSTANCE, StructureUniformity.INSTANCE, DisallowInEmpireSpace.INSTANCE, SpeedFactor.INSTANCE, RequiredSkill1Level.INSTANCE, DoomsdayWarningDuration.INSTANCE, RequiredSkill1 .INSTANCE, RemoteResistanceID.INSTANCE, AllowInFullyCorruptedLowSec.INSTANCE })));
    public static final InterdictionBurstProbes.MetaGroup METAGROUP = new InterdictionBurstProbes.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  5599 :
            {
                return allowinfullycorruptedlowsec;
            }
            case  1074 :
            {
                return disallowinempirespace;
            }
            case  2280 :
            {
                return doomsdayaoeduration;
            }
            case  2279 :
            {
                return doomsdayaoerange;
            }
            case  2262 :
            {
                return doomsdaywarningduration;
            }
            case  9 :
            {
                return hp;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  2138 :
            {
                return remoteresistanceid;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  204 :
            {
                return speedmultiplier;
            }
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<InterdictionBurstProbes> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InterdictionBurstProbes>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/InterdictionBurstProbes.yaml";
        private Map<Integer, InterdictionBurstProbes> cache = (null);

        @Override
        public IMetaCategory<? super InterdictionBurstProbes> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4088;
        }

        @Override
        public String getName() {
            return "InterdictionBurstProbes";
        }

        @Override
        public synchronized Map<Integer, InterdictionBurstProbes> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(InterdictionBurstProbes.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, InterdictionBurstProbes> types;
        }
    }
}
