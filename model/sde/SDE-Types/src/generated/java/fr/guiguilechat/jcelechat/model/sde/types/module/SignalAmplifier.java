package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargetsBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class SignalAmplifier
    extends Module
{
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Additional amount of locked targets that can be handled.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargetsbonus;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxtargetrangebonus;
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
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scangravimetricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthpercent;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolutionbonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, ScanGravimetricStrengthPercent.INSTANCE, ScanLadarStrengthPercent.INSTANCE, Mass.INSTANCE, ScanMagnetometricStrengthPercent.INSTANCE, TechLevel.INSTANCE, ScanRadarStrengthPercent.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE, MaxLockedTargetsBonus.INSTANCE, Cpu.INSTANCE, RequiredSkill1Level.INSTANCE, MaxTargetRangeBonus.INSTANCE, ScanResolutionBonus.INSTANCE, RequiredSkill1 .INSTANCE, MetaLevelOld.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE })));
    public static final SignalAmplifier.MetaGroup METAGROUP = new SignalAmplifier.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  50 :
            {
                return cpu;
            }
            case  235 :
            {
                return maxlockedtargetsbonus;
            }
            case  309 :
            {
                return maxtargetrangebonus;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  30 :
            {
                return power;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1027 :
            {
                return scangravimetricstrengthpercent;
            }
            case  1028 :
            {
                return scanladarstrengthpercent;
            }
            case  1029 :
            {
                return scanmagnetometricstrengthpercent;
            }
            case  1030 :
            {
                return scanradarstrengthpercent;
            }
            case  566 :
            {
                return scanresolutionbonus;
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
    public IMetaGroup<SignalAmplifier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SignalAmplifier>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/SignalAmplifier.yaml";
        private Map<String, SignalAmplifier> cache = (null);

        @Override
        public IMetaCategory<? super SignalAmplifier> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  210;
        }

        @Override
        public String getName() {
            return "SignalAmplifier";
        }

        @Override
        public synchronized Map<String, SignalAmplifier> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SignalAmplifier.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SignalAmplifier> types;
        }
    }
}
