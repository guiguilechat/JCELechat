package fr.guiguilechat.jcelechat.model.sde.types.starbase;

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
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;

public class AssemblyArray
    extends Starbase
{
    /**
     * The maximum security level at which the structure can be anchored. Used as a non-functional display attribute on some deployables.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double anchoringsecuritylevelmax;
    /**
     * The minimum security level at which the structure can be anchored.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(-1.0)
    public double anchoringsecuritylevelmin;
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
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * Can have research and manufacturing functionality
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int isramcompatible;
    /**
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationaldistance;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationalusers;
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
    public int requiresihubupgrade;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityAntiCapitalMissileResistance.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, AnchoringSecurityLevelMax.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, MaxOperationalDistance.INSTANCE, MaxOperationalUsers.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RequiredSkill1Level.INSTANCE, AnchoringSecurityLevelMin.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, IsRAMcompatible.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, AnchoringDelay.INSTANCE, Cpu.INSTANCE, RequiredSkill1 .INSTANCE, RequiresIHubUpgrade.INSTANCE })));
    public static final AssemblyArray.MetaGroup METAGROUP = new AssemblyArray.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1032 :
            {
                return anchoringsecuritylevelmax;
            }
            case  1946 :
            {
                return anchoringsecuritylevelmin;
            }
            case  50 :
            {
                return cpu;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  998 :
            {
                return isramcompatible;
            }
            case  715 :
            {
                return maxoperationaldistance;
            }
            case  716 :
            {
                return maxoperationalusers;
            }
            case  1692 :
            {
                return metagroupid;
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
            case  1595 :
            {
                return requiresihubupgrade;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  136 :
            {
                return uniformity;
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
    public IMetaGroup<AssemblyArray> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AssemblyArray>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/AssemblyArray.yaml";
        private Map<Integer, AssemblyArray> cache = (null);

        @Override
        public IMetaCategory<? super AssemblyArray> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  397;
        }

        @Override
        public String getName() {
            return "AssemblyArray";
        }

        @Override
        public synchronized Map<Integer, AssemblyArray> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AssemblyArray.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, AssemblyArray> types;
        }
    }
}
