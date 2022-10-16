package fr.guiguilechat.jcelechat.model.sde.types.starbase;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AnchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.AnchoringSecurityLevelMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ControlTowerMinimumDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowOffensiveModifiers;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiCapitalMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IncapacitationRatio;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveConsumptionAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveConsumptionType;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpPortalConsumptionMassFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnliningDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.PosAnchoredPerSolarSystemAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.PosCargobayAcceptType;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiresSovUpgrade1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.UnanchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class JumpPortalArray
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
     * Number of units it consumes per light year.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(2000)
    public int jumpdriveconsumptionamount;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpdriveconsumptiontype;
    /**
     * Range in light years the ship can maximum jump to.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpdriverange;
    /**
     * Multiplier used to calculate amount of quantity used for jumping via portals based on mass of ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpportalconsumptionmassfactor;
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
     * cargo typeID allowed in structures
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int poscargobayaccepttype;
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
    public int requiressovupgrade1;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityAntiCapitalMissileResistance.INSTANCE, PosCargobayAcceptType.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, AnchoringSecurityLevelMax.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, ControlTowerMinimumDistance.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, IncapacitationRatio.INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE, JumpDriveConsumptionType.INSTANCE, Radius.INSTANCE, JumpDriveRange.INSTANCE, JumpDriveConsumptionAmount.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, JumpPortalConsumptionMassFactor.INSTANCE, PosAnchoredPerSolarSystemAmount.INSTANCE, AnchoringDelay.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, Cpu.INSTANCE, ScanResolution.INSTANCE, RequiresSovUpgrade1 .INSTANCE })));
    public static final JumpPortalArray.MetaGroup METAGROUP = new JumpPortalArray.MetaGroup();

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
            case  868 :
            {
                return jumpdriveconsumptionamount;
            }
            case  866 :
            {
                return jumpdriveconsumptiontype;
            }
            case  867 :
            {
                return jumpdriverange;
            }
            case  1001 :
            {
                return jumpportalconsumptionmassfactor;
            }
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  1195 :
            {
                return posanchoredpersolarsystemamount;
            }
            case  1351 :
            {
                return poscargobayaccepttype;
            }
            case  30 :
            {
                return power;
            }
            case  1595 :
            {
                return requiressovupgrade1;
            }
            case  564 :
            {
                return scanresolution;
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
    public IMetaGroup<JumpPortalArray> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<JumpPortalArray>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/JumpPortalArray.yaml";
        private Map<String, JumpPortalArray> cache = (null);

        @Override
        public IMetaCategory<? super JumpPortalArray> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  707;
        }

        @Override
        public String getName() {
            return "JumpPortalArray";
        }

        @Override
        public synchronized Map<String, JumpPortalArray> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(JumpPortalArray.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, JumpPortalArray> types;
        }
    }
}
