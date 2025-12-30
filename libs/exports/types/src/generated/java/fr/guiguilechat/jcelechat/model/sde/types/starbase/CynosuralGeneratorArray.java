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

public class CynosuralGeneratorArray
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
     * Minimum distance where a starbase structure can be anchored at from the control tower shield extremity in meters.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerminimumdistance;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double emdamageresonance;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double explosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double incapacitationratio;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpharmonics;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double kineticdamageresonance;
    /**
     * How many structures in this group can be anchored for the same alliance per solar system.  0 means there is no limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int posanchoredpersolarsystemamount;
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
    @DefaultIntValue(0)
    public int requiresihubupgrade;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double thermaldamageresonance;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityAntiCapitalMissileResistance.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, AnchoringSecurityLevelMax.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, ControlTowerMinimumDistance.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, IncapacitationRatio.INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, UnanchoringDelay.INSTANCE, JumpHarmonics.INSTANCE, OnliningDelay.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, PosAnchoredPerSolarSystemAmount.INSTANCE, AnchoringDelay.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, Cpu.INSTANCE, ScanResolution.INSTANCE, RequiresIHubUpgrade.INSTANCE })));
    public static final CynosuralGeneratorArray.MetaGroup METAGROUP = new CynosuralGeneratorArray.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1032 :
            {
                return anchoringsecuritylevelmax;
            }
            case  1165 :
            {
                return controltowerminimumdistance;
            }
            case  50 :
            {
                return cpu;
            }
            case  73 :
            {
                return duration;
            }
            case  113 :
            {
                return emdamageresonance;
            }
            case  111 :
            {
                return explosivedamageresonance;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  156 :
            {
                return incapacitationratio;
            }
            case  1253 :
            {
                return jumpharmonics;
            }
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  1195 :
            {
                return posanchoredpersolarsystemamount;
            }
            case  30 :
            {
                return power;
            }
            case  1595 :
            {
                return requiresihubupgrade;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  110 :
            {
                return thermaldamageresonance;
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
    public IMetaGroup<CynosuralGeneratorArray> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CynosuralGeneratorArray>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/CynosuralGeneratorArray.yaml";
        private Map<Integer, CynosuralGeneratorArray> cache = (null);

        @Override
        public IMetaCategory<? super CynosuralGeneratorArray> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  838;
        }

        @Override
        public String getName() {
            return "CynosuralGeneratorArray";
        }

        @Override
        public synchronized Map<Integer, CynosuralGeneratorArray> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CynosuralGeneratorArray.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CynosuralGeneratorArray> types;
        }
    }
}
