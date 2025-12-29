package fr.guiguilechat.jcelechat.model.sde.types.ship;

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
import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class CombatReconShip
    extends Ship
{
    /**
     * if set to 1 the ship is immune to directional scan
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dscanimmune;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonusreconship1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonusreconship2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double elitebonusreconship3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationlow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationmed;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislots;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lowslots;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslots;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigslots;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusac;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusac2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(5.0)
    public double shipbonuscc;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonuscc2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(5.0)
    public double shipbonusgc;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusgc2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusmc;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusmc2;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MaxPassengers.INSTANCE, EliteBonusReconShip3 .INSTANCE, BaseWarpSpeed.INSTANCE, UpgradeSlotsLeft.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShipBonusAC2 .INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShipBonusCC2 .INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShipBonusGC2 .INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, ShipBonusMC2 .INSTANCE, PowerToSpeed.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, DroneCapacity.INSTANCE, HeatDissipationRateHi.INSTANCE, MaxDirectionalScanRange.INSTANCE, MaxVelocity.INSTANCE, DscanImmune.INSTANCE, TechLevel.INSTANCE, SignatureRadius.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, CpuOutput.INSTANCE, HeatCapacityLow.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RechargeRate.INSTANCE, CargoScanResistance.INSTANCE, SensorDampenerResistance.INSTANCE, MaxLockedTargets.INSTANCE, WeaponDisruptionResistance.INSTANCE, EliteBonusReconShip1 .INSTANCE, EliteBonusReconShip2 .INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, WarpSpeedMultiplier.INSTANCE, PropulsionGraphicID.INSTANCE, ShipBonusAC.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, LauncherSlotsLeft.INSTANCE, ShipBonusGC.INSTANCE, TurretSlotsLeft.INSTANCE, ShipBonusCC.INSTANCE, TypeColorScheme.INSTANCE, ShipBonusMC.INSTANCE, HeatAttenuationHi.INSTANCE, UpgradeCapacity.INSTANCE, HeatAttenuationMed.INSTANCE, KineticDamageResonance.INSTANCE, HeatAttenuationLow.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final CombatReconShip.MetaGroup METAGROUP = new CombatReconShip.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1958 :
            {
                return dscanimmune;
            }
            case  962 :
            {
                return elitebonusreconship1;
            }
            case  963 :
            {
                return elitebonusreconship2;
            }
            case  1537 :
            {
                return elitebonusreconship3;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  1259 :
            {
                return heatattenuationhi;
            }
            case  1262 :
            {
                return heatattenuationlow;
            }
            case  1261 :
            {
                return heatattenuationmed;
            }
            case  14 :
            {
                return hislots;
            }
            case  12 :
            {
                return lowslots;
            }
            case  124 :
            {
                return maincolor;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  13 :
            {
                return medslots;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  478 :
            {
                return shipbonusac;
            }
            case  656 :
            {
                return shipbonusac2;
            }
            case  487 :
            {
                return shipbonuscc;
            }
            case  657 :
            {
                return shipbonuscc2;
            }
            case  486 :
            {
                return shipbonusgc;
            }
            case  658 :
            {
                return shipbonusgc2;
            }
            case  489 :
            {
                return shipbonusmc;
            }
            case  659 :
            {
                return shipbonusmc2;
            }
            case  1768 :
            {
                return typecolorscheme;
            }
            case  1132 :
            {
                return upgradecapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
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
    public IMetaGroup<CombatReconShip> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CombatReconShip>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/CombatReconShip.yaml";
        private Map<Integer, CombatReconShip> cache = (null);

        @Override
        public IMetaCategory<? super CombatReconShip> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  906;
        }

        @Override
        public String getName() {
            return "CombatReconShip";
        }

        @Override
        public synchronized Map<Integer, CombatReconShip> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CombatReconShip.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CombatReconShip> types;
        }
    }
}
