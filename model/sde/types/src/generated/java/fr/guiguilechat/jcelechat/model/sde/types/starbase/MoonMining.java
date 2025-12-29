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

public class MoonMining
    extends Starbase
{
    /**
     * Whether the structure requires the anchorers alliance to hold sovereignty in the system for it to be anchorable.  Only enforced if the security level is 0.4 or less.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int anchoringrequiressovereignty;
    /**
     * The maximum security level at which the structure can be anchored. Used as a non-functional display attribute on some deployables.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double anchoringsecuritylevelmax;
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
     * The quality of the material harvested.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int harvesterquality;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldUniformity.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, HarvesterQuality.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, AnchoringSecurityLevelMax.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, AnchoringRequiresSovereignty.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, AnchoringDelay.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, Cpu.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE })));
    public static final MoonMining.MetaGroup METAGROUP = new MoonMining.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1033 :
            {
                return anchoringrequiressovereignty;
            }
            case  1032 :
            {
                return anchoringsecuritylevelmax;
            }
            case  50 :
            {
                return cpu;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  710 :
            {
                return harvesterquality;
            }
            case  30 :
            {
                return power;
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
    public IMetaGroup<MoonMining> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MoonMining>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/MoonMining.yaml";
        private Map<Integer, MoonMining> cache = (null);

        @Override
        public IMetaCategory<? super MoonMining> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  416;
        }

        @Override
        public String getName() {
            return "MoonMining";
        }

        @Override
        public synchronized Map<Integer, MoonMining> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MoonMining.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MoonMining> types;
        }
    }
}
