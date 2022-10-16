package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AnchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowOffensiveModifiers;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnliningDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UnanchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.AssemblyArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.CompressionArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.ControlTower;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.CorporateHangarArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.CynosuralGeneratorArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.CynosuralSystemJammer;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.ElectronicWarfareBattery;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.EnergyNeutralizingBattery;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.JumpPortalArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.Laboratory;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.MobileHybridSentry;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.MobileLaserSentry;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.MobileMissileSentry;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.MobileProjectileSentry;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.MobileReactor;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.MoonMining;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.PersonalHangar;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.ReprocessingArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.ScannerArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.SensorDampeningBattery;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.ShieldHardeningArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.ShipMaintenanceArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.Silo;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.StasisWebificationBattery;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.TrackingArray;
import fr.guiguilechat.jcelechat.model.sde.types.starbase.WarpScramblingBattery;

public abstract class Starbase
    extends EveType
{
    /**
     * How long it takes to anchor or unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int anchoringdelay;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armorhp;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armoruniformity;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowoffensivemodifiers;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * How long it takes to bring this object online.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int onliningdelay;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrength;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrength;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcharge;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldrechargerate;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * How long it takes to unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int unanchoringdelay;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, Radius.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, AnchoringDelay.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, ShieldRechargeRate.INSTANCE, DisallowOffensiveModifiers.INSTANCE })));
    public static final Starbase.MetaCat METACAT = new Starbase.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  556 :
            {
                return anchoringdelay;
            }
            case  265 :
            {
                return armorhp;
            }
            case  524 :
            {
                return armoruniformity;
            }
            case  38 :
            {
                return capacity;
            }
            case  872 :
            {
                return disallowoffensivemodifiers;
            }
            case  9 :
            {
                return hp;
            }
            case  677 :
            {
                return onliningdelay;
            }
            case  162 :
            {
                return radius;
            }
            case  211 :
            {
                return scangravimetricstrength;
            }
            case  209 :
            {
                return scanladarstrength;
            }
            case  210 :
            {
                return scanmagnetometricstrength;
            }
            case  208 :
            {
                return scanradarstrength;
            }
            case  263 :
            {
                return shieldcapacity;
            }
            case  264 :
            {
                return shieldcharge;
            }
            case  479 :
            {
                return shieldrechargerate;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  676 :
            {
                return unanchoringdelay;
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
    public IMetaCategory<Starbase> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Starbase>
    {

        @Override
        public int getCategoryId() {
            return  23;
        }

        @Override
        public String getName() {
            return "Starbase";
        }

        @Override
        public Collection<IMetaGroup<? extends Starbase>> groups() {
            return Arrays.asList(ReprocessingArray.METAGROUP, ShipMaintenanceArray.METAGROUP, ControlTower.METAGROUP, AssemblyArray.METAGROUP, Silo.METAGROUP, Laboratory.METAGROUP, MoonMining.METAGROUP, MobileMissileSentry.METAGROUP, MobileProjectileSentry.METAGROUP, MobileLaserSentry.METAGROUP, MobileReactor.METAGROUP, ElectronicWarfareBattery.METAGROUP, SensorDampeningBattery.METAGROUP, StasisWebificationBattery.METAGROUP, WarpScramblingBattery.METAGROUP, ShieldHardeningArray.METAGROUP, MobileHybridSentry.METAGROUP, CorporateHangarArray.METAGROUP, TrackingArray.METAGROUP, JumpPortalArray.METAGROUP, ScannerArray.METAGROUP, EnergyNeutralizingBattery.METAGROUP, CynosuralGeneratorArray.METAGROUP, CynosuralSystemJammer.METAGROUP, PersonalHangar.METAGROUP, CompressionArray.METAGROUP);
        }
    }
}
