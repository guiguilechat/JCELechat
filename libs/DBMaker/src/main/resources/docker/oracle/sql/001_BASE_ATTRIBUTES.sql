whenever sqlerror exit failure

create table BASE_ATTRIBUTES(attribute_id NUMBER NOT NULL, default_value FLOAT NOT NULL, display_name VARCHAR2(4000), icon_id NUMBER NOT NULL, name VARCHAR2(4000), unit_id NUMBER NOT NULL, PRIMARY KEY (attribute_id));

INSERT INTO BASE_ATTRIBUTES (attribute_id, default_value, display_name, icon_id, name, unit_id)
          SELECT 2, 0, '', 0, 'isOnline', 0 FROM DUAL
UNION ALL SELECT 3, 0, 'Item Damage', 1386, 'damage', 113 FROM DUAL
UNION ALL SELECT 4, 0, 'Mass', 76, 'mass', 2 FROM DUAL
UNION ALL SELECT 6, 0, 'Activation Cost', 1400, 'capacitorNeed', 114 FROM DUAL
UNION ALL SELECT 8, 0, '', 1391, 'minRange', 0 FROM DUAL
UNION ALL SELECT 9, 0, 'Structure Hitpoints', 67, 'hp', 113 FROM DUAL
UNION ALL SELECT 11, 0, 'Powergrid Output', 1400, 'powerOutput', 107 FROM DUAL
UNION ALL SELECT 12, 0, 'Low Slots', 295, 'lowSlots', 122 FROM DUAL
UNION ALL SELECT 13, 0, 'Medium Slots', 294, 'medSlots', 122 FROM DUAL
UNION ALL SELECT 14, 0, 'High Slots', 293, 'hiSlots', 122 FROM DUAL
UNION ALL SELECT 15, 0, 'Power Load', 1400, 'powerLoad', 107 FROM DUAL
UNION ALL SELECT 18, 0, '', 1668, 'charge', 114 FROM DUAL
UNION ALL SELECT 19, 0, '', 0, 'powerToSpeed', 0 FROM DUAL
UNION ALL SELECT 20, 1, 'Maximum Velocity Bonus', 1389, 'speedFactor', 124 FROM DUAL
UNION ALL SELECT 21, 0, '', 0, 'warpFactor', 0 FROM DUAL
UNION ALL SELECT 29, 0, '', 0, 'warpInhibitor', 0 FROM DUAL
UNION ALL SELECT 30, 0, 'Powergrid Usage', 70, 'power', 107 FROM DUAL
UNION ALL SELECT 31, 0, '', 1383, 'maxArmor', 0 FROM DUAL
UNION ALL SELECT 32, 0, '', 0, 'breakPoint', 0 FROM DUAL
UNION ALL SELECT 37, 0, 'Maximum Velocity', 1389, 'maxVelocity', 11 FROM DUAL
UNION ALL SELECT 38, 0, 'Capacity', 71, 'capacity', 9 FROM DUAL
UNION ALL SELECT 39, 0, 'Damage Repaired Bonus', 0, 'damageHP', 105 FROM DUAL
UNION ALL SELECT 47, 1, '', 0, 'slots', 0 FROM DUAL
UNION ALL SELECT 48, 0, 'CPU Output', 1405, 'cpuOutput', 106 FROM DUAL
UNION ALL SELECT 49, 0, 'CPU Load', 1405, 'cpuLoad', 106 FROM DUAL
UNION ALL SELECT 50, 0, 'CPU usage', 1405, 'cpu', 106 FROM DUAL
UNION ALL SELECT 51, 0, 'Rate of fire', 1397, 'speed', 101 FROM DUAL
UNION ALL SELECT 52, 0, '', 0, 'damageResistance', 0 FROM DUAL
UNION ALL SELECT 54, 0, 'Optimal Range', 1391, 'maxRange', 1 FROM DUAL
UNION ALL SELECT 55, 0, 'Capacitor Recharge time', 1392, 'rechargeRate', 101 FROM DUAL
UNION ALL SELECT 56, 1, 'Charges Per Cycle', 1397, 'chargeRate', 0 FROM DUAL
UNION ALL SELECT 61, 0, '', 0, 'targetModule', 0 FROM DUAL
UNION ALL SELECT 63, 0, '', 1399, 'accuracyBonus', 0 FROM DUAL
UNION ALL SELECT 64, 1, 'Damage Modifier', 1432, 'damageMultiplier', 104 FROM DUAL
UNION ALL SELECT 65, 0, '', 1383, 'armorBonus', 0 FROM DUAL
UNION ALL SELECT 66, 0, 'Duration Bonus', 1392, 'durationBonus', 105 FROM DUAL
UNION ALL SELECT 67, 0, 'Capacitor Bonus', 1400, 'capacitorBonus', 114 FROM DUAL
UNION ALL SELECT 68, 0, 'Shield Bonus', 1384, 'shieldBonus', 113 FROM DUAL
UNION ALL SELECT 69, 0, '', 1397, 'rateBonus', 0 FROM DUAL
UNION ALL SELECT 70, 0, 'Inertia Modifier', 1401, 'agility', 104 FROM DUAL
UNION ALL SELECT 72, 0, 'Shield Hitpoint Bonus', 69, 'capacityBonus', 113 FROM DUAL
UNION ALL SELECT 73, 0, 'Activation time / duration', 1392, 'duration', 101 FROM DUAL
UNION ALL SELECT 75, 1, '', 0, 'hpToCapacity', 0 FROM DUAL
UNION ALL SELECT 76, 0, 'Maximum Targeting Range', 1391, 'maxTargetRange', 1 FROM DUAL
UNION ALL SELECT 77, 0, 'Mining amount', 0, 'miningAmount', 9 FROM DUAL
UNION ALL SELECT 79, 0, '', 74, 'scanSpeed', 101 FROM DUAL
UNION ALL SELECT 80, 0, 'Speed Bonus', 1389, 'speedBonus', 11 FROM DUAL
UNION ALL SELECT 81, 0, '', 0, 'hpFactor', 0 FROM DUAL
UNION ALL SELECT 82, 0, '', 0, 'structureBonus', 0 FROM DUAL
UNION ALL SELECT 83, 0, 'Structure Hitpoints Repaired', 80, 'structureDamageAmount', 113 FROM DUAL
UNION ALL SELECT 84, 0, 'Armor Hitpoints Repaired', 80, 'armorDamageAmount', 113 FROM DUAL
UNION ALL SELECT 87, 0, 'shield Transfer Range', 1391, 'shieldTransferRange', 1 FROM DUAL
UNION ALL SELECT 88, 0, '', 1384, 'shieldDrainAmount', 0 FROM DUAL
UNION ALL SELECT 89, 0, '', 0, 'shieldDrainRange', 0 FROM DUAL
UNION ALL SELECT 90, 0, 'Energy transfer amount', 1033, 'powerTransferAmount', 120 FROM DUAL
UNION ALL SELECT 91, 0, 'Transfer range', 1391, 'powerTransferRange', 1 FROM DUAL
UNION ALL SELECT 92, 0, '', 0, 'kineticDampeningFieldStrength', 0 FROM DUAL
UNION ALL SELECT 93, 0, '', 0, 'kineticDampeningFieldBonus', 0 FROM DUAL
UNION ALL SELECT 95, 0, '', 0, 'energyReflectionStrength', 0 FROM DUAL
UNION ALL SELECT 96, 0, '', 0, 'energyReflectionBonus', 0 FROM DUAL
UNION ALL SELECT 97, 0, 'Neutralization Amount', 1400, 'energyNeutralizerAmount', 114 FROM DUAL
UNION ALL SELECT 98, 0, 'Neutralization Optimal Range', 1391, 'energyNeutralizerRangeOptimal', 1 FROM DUAL
UNION ALL SELECT 99, 0, 'Area of effect radius', 1391, 'empFieldRange', 1 FROM DUAL
UNION ALL SELECT 101, 0, 'Launcher Hardpoints', 168, 'launcherSlotsLeft', 141 FROM DUAL
UNION ALL SELECT 102, 0, 'Turret Hardpoints', 387, 'turretSlotsLeft', 141 FROM DUAL
UNION ALL SELECT 103, 0, 'Warp Disruption Range', 1391, 'warpScrambleRange', 1 FROM DUAL
UNION ALL SELECT 104, 0, 'Warp Scramble Status', 0, 'warpScrambleStatus', 0 FROM DUAL
UNION ALL SELECT 105, 0, 'Warp Scramble Strength', 111, 'warpScrambleStrength', 0 FROM DUAL
UNION ALL SELECT 106, 0, 'Drone Bay Hardpoints', 138, 'droneBaySlotsLeft', 0 FROM DUAL
UNION ALL SELECT 107, 0, '', 1390, 'explosionRange', 1 FROM DUAL
UNION ALL SELECT 108, 0, '', 0, 'detonationRange', 1 FROM DUAL
UNION ALL SELECT 109, 1, 'Structure Kinetic Damage Resistance', 1393, 'kineticDamageResonance', 108 FROM DUAL
UNION ALL SELECT 110, 1, 'Structure Thermal Damage Resistance', 1394, 'thermalDamageResonance', 108 FROM DUAL
UNION ALL SELECT 111, 1, 'Structure Explosive Damage Resistance', 1395, 'explosiveDamageResonance', 108 FROM DUAL
UNION ALL SELECT 112, 0, '', 1400, 'energyDamageAbsorptionFactor', 0 FROM DUAL
UNION ALL SELECT 113, 1, 'Structure EM Damage Resistance', 1396, 'emDamageResonance', 108 FROM DUAL
UNION ALL SELECT 114, 0, 'EM damage', 1388, 'emDamage', 113 FROM DUAL
UNION ALL SELECT 116, 0, 'Explosive damage', 1387, 'explosiveDamage', 113 FROM DUAL
UNION ALL SELECT 117, 0, 'Kinetic damage', 1385, 'kineticDamage', 113 FROM DUAL
UNION ALL SELECT 118, 0, 'Thermal damage', 1386, 'thermalDamage', 113 FROM DUAL
UNION ALL SELECT 120, 1, 'Range bonus', 1391, 'weaponRangeMultiplier', 109 FROM DUAL
UNION ALL SELECT 121, 0, 'Power Output Bonus', 1384, 'powerOutputBonus', 107 FROM DUAL
UNION ALL SELECT 122, 0, '', 1383, 'armorPiercingChance', 0 FROM DUAL
UNION ALL SELECT 123, 0, '', 0, 'shieldPiercingChance', 0 FROM DUAL
UNION ALL SELECT 124, 0, '', 0, 'mainColor', 0 FROM DUAL
UNION ALL SELECT 125, 0, 'Ship scanning range', 1391, 'shipScanRange', 1 FROM DUAL
UNION ALL SELECT 126, 0, 'Cargoscan range', 1391, 'cargoScanRange', 1 FROM DUAL
UNION ALL SELECT 127, 0, '', 0, 'ammoLoaded', 0 FROM DUAL
UNION ALL SELECT 128, 0, 'Charge size', 1666, 'chargeSize', 117 FROM DUAL
UNION ALL SELECT 129, 0, '', 413, 'maxPassengers', 0 FROM DUAL
UNION ALL SELECT 130, 1, 'Thermal Damage Resistance Bonus', 1394, 'thermalDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 131, 1, 'Kinetic Damage Resistance Bonus', 1393, 'kineticDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 132, 1, 'Explosive Damage Resistance Bonus', 1395, 'explosiveDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 133, 1, 'EM Damage Resistance Bonus', 1396, 'emDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 134, 1, 'Shield recharge rate bonus', 1392, 'shieldRechargeRateMultiplier', 111 FROM DUAL
UNION ALL SELECT 135, 0, '', 0, 'moduleSize', 0 FROM DUAL
UNION ALL SELECT 136, 0, '', 0, 'uniformity', 0 FROM DUAL
UNION ALL SELECT 137, 0, 'Used with (Launcher Group)', 1397, 'launcherGroup', 115 FROM DUAL
UNION ALL SELECT 138, 0, 'EM Damage Bonus', 1388, 'emDamageBonus', 113 FROM DUAL
UNION ALL SELECT 139, 0, 'Explosive Damage Bonus', 1387, 'explosiveDamageBonus', 113 FROM DUAL
UNION ALL SELECT 140, 0, 'Kinetic Damage Bonus', 1385, 'kineticDamageBonus', 113 FROM DUAL
UNION ALL SELECT 141, 0, 'Thermal Damage Bonus', 1386, 'thermalDamageBonus', 113 FROM DUAL
UNION ALL SELECT 142, 0, 'ECM Burst Radius', 1390, 'ecmBurstRange', 1 FROM DUAL
UNION ALL SELECT 143, 0, 'Targeting range', 0, 'targetHostileRange', 1 FROM DUAL
UNION ALL SELECT 144, 1, 'Capacitor recharge rate bonus', 1400, 'capacitorRechargeRateMultiplier', 111 FROM DUAL
UNION ALL SELECT 145, 1, 'Powergrid bonus', 70, 'powerOutputMultiplier', 109 FROM DUAL
UNION ALL SELECT 146, 1, 'Shield Hitpoint Bonus', 1384, 'shieldCapacityMultiplier', 109 FROM DUAL
UNION ALL SELECT 147, 1, 'Capacitor Capacity Bonus', 1400, 'capacitorCapacityMultiplier', 109 FROM DUAL
UNION ALL SELECT 148, 1, 'Armor Hitpoint Bonus', 1383, 'armorHPMultiplier', 109 FROM DUAL
UNION ALL SELECT 149, 1, 'Cargo capacity bonus', 71, 'cargoCapacityMultiplier', 109 FROM DUAL
UNION ALL SELECT 150, 1, 'Structure Hitpoint Bonus', 67, 'structureHPMultiplier', 109 FROM DUAL
UNION ALL SELECT 151, 0, 'Inertia Modifier', 1401, 'agilityBonus', 105 FROM DUAL
UNION ALL SELECT 152, 0, 'Maximum Passengers Bonus', 0, 'maxPassengersBonus', 0 FROM DUAL
UNION ALL SELECT 153, 0, '', 0, 'warpCapacitorNeed', 0 FROM DUAL
UNION ALL SELECT 154, 0, 'Activation proximity', 1390, 'proximityRange', 1 FROM DUAL
UNION ALL SELECT 156, 0, '', 0, 'incapacitationRatio', 0 FROM DUAL
UNION ALL SELECT 157, 0, '', 0, 'orbitRange', 0 FROM DUAL
UNION ALL SELECT 158, 1, 'Accuracy falloff ', 1399, 'falloff', 1 FROM DUAL
UNION ALL SELECT 160, 0, 'Turret Tracking', 1398, 'trackingSpeed', 0 FROM DUAL
UNION ALL SELECT 161, 0, 'Volume', 67, 'volume', 9 FROM DUAL
UNION ALL SELECT 162, 0, '', 1390, 'radius', 0 FROM DUAL
UNION ALL SELECT 163, 0, '', 0, 'dummyDuration', 0 FROM DUAL
UNION ALL SELECT 164, 0, 'Charisma', 1378, 'charisma', 120 FROM DUAL
UNION ALL SELECT 165, 0, 'Intelligence', 1380, 'intelligence', 120 FROM DUAL
UNION ALL SELECT 166, 0, 'Memory', 1381, 'memory', 120 FROM DUAL
UNION ALL SELECT 167, 0, 'Perception', 1382, 'perception', 120 FROM DUAL
UNION ALL SELECT 168, 0, 'Willpower', 1379, 'willpower', 120 FROM DUAL
UNION ALL SELECT 169, 0, 'Inertia Modifier', 1401, 'agilityMultiplier', 121 FROM DUAL
UNION ALL SELECT 170, 0, '', 1378, 'customCharismaBonus', 0 FROM DUAL
UNION ALL SELECT 171, 0, '', 1379, 'customWillpowerBonus', 0 FROM DUAL
UNION ALL SELECT 172, 0, '', 1382, 'customPerceptionBonus', 0 FROM DUAL
UNION ALL SELECT 173, 0, '', 1381, 'customMemoryBonus', 0 FROM DUAL
UNION ALL SELECT 174, 0, '', 1380, 'customIntelligenceBonus', 0 FROM DUAL
UNION ALL SELECT 175, 0, 'Charisma Modifier', 1378, 'charismaBonus', 120 FROM DUAL
UNION ALL SELECT 176, 0, 'Intelligence Modifier', 1380, 'intelligenceBonus', 120 FROM DUAL
UNION ALL SELECT 177, 0, 'Memory Modifier', 1381, 'memoryBonus', 120 FROM DUAL
UNION ALL SELECT 178, 0, 'Perception Modifier', 1382, 'perceptionBonus', 120 FROM DUAL
UNION ALL SELECT 179, 0, 'Willpower Modifier', 1379, 'willpowerBonus', 120 FROM DUAL
UNION ALL SELECT 180, 0, 'Primary attribute', 0, 'primaryAttribute', 119 FROM DUAL
UNION ALL SELECT 181, 0, 'Secondary attribute', 0, 'secondaryAttribute', 119 FROM DUAL
UNION ALL SELECT 182, 0, 'Primary Skill required', 0, 'requiredSkill1', 116 FROM DUAL
UNION ALL SELECT 183, 0, 'Secondary Skill required', 0, 'requiredSkill2', 116 FROM DUAL
UNION ALL SELECT 184, 0, 'Tertiary Skill required', 0, 'requiredSkill3', 116 FROM DUAL
UNION ALL SELECT 185, 0, '', 0, 'attributePoints', 0 FROM DUAL
UNION ALL SELECT 186, 0, '', 0, 'warpCapacitorNeedMultiplier', 0 FROM DUAL
UNION ALL SELECT 187, 1, '', 0, 'repairCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 188, 0, 'Cargo Scan Resistance', 106, 'cargoScanResistance', 127 FROM DUAL
UNION ALL SELECT 189, 0, '', 0, 'targetFilterTypelistID', 0 FROM DUAL
UNION ALL SELECT 190, 0, '', 0, 'corporationMemberLimit', 0 FROM DUAL
UNION ALL SELECT 191, 0, 'corporation Member Bonus', 0, 'corporationMemberBonus', 139 FROM DUAL
UNION ALL SELECT 192, 0, 'Maximum Locked Targets', 109, 'maxLockedTargets', 0 FROM DUAL
UNION ALL SELECT 193, 0, '', 0, 'maxAttackTargets', 0 FROM DUAL
UNION ALL SELECT 194, 0, '', 0, 'jammingResistance', 0 FROM DUAL
UNION ALL SELECT 195, 0, '', 0, 'raceID', 0 FROM DUAL
UNION ALL SELECT 196, 0, '', 0, 'manufactureSlotLimit', 0 FROM DUAL
UNION ALL SELECT 197, 0, 'Survey Scan Range', 0, 'surveyScanRange', 1 FROM DUAL
UNION ALL SELECT 202, 1, 'CPU Output bonus', 1405, 'cpuMultiplier', 109 FROM DUAL
UNION ALL SELECT 203, 0, '', 0, 'miningDurationMultiplier', 0 FROM DUAL
UNION ALL SELECT 204, 1, 'rate of fire bonus', 1389, 'speedMultiplier', 111 FROM DUAL
UNION ALL SELECT 205, 1, '', 1399, 'accuracyMultiplier', 0 FROM DUAL
UNION ALL SELECT 207, 1, 'Mining Amount Multiplier', 0, 'miningAmountMultiplier', 0 FROM DUAL
UNION ALL SELECT 208, 0, 'RADAR Sensor Strength', 2031, 'scanRadarStrength', 120 FROM DUAL
UNION ALL SELECT 209, 0, 'Ladar Sensor Strength', 2030, 'scanLadarStrength', 120 FROM DUAL
UNION ALL SELECT 210, 0, 'Magnetometric Sensor Strength', 2029, 'scanMagnetometricStrength', 120 FROM DUAL
UNION ALL SELECT 211, 0, 'Gravimetric Sensor Strength', 2028, 'scanGravimetricStrength', 120 FROM DUAL
UNION ALL SELECT 212, 1, 'Missile Damage Bonus', 0, 'missileDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 213, 0, 'Missile Damage Bonus', 1397, 'missileDamageMultiplierBonus', 109 FROM DUAL
UNION ALL SELECT 216, 0, 'Capacitor Need Multiplier', 1400, 'capacitorNeedMultiplier', 105 FROM DUAL
UNION ALL SELECT 217, 0, '', 0, 'propulsionGraphicID', 0 FROM DUAL
UNION ALL SELECT 218, 1, '', 1392, 'blueprintResearchTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 219, 1, '', 0, 'manufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 220, 0, '', 1392, 'blueprintResearchTimeMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 221, 1, '', 1392, 'blueprintManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 222, 0, '', 1392, 'blueprintManufactureTimeMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 223, 1, '', 0, 'charismaSkillTrainingTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 224, 1, '', 0, 'intelligenceSkillTrainingTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 225, 1, '', 0, 'memorySkillTrainingTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 226, 1, '', 0, 'perceptionSkillTrainingTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 227, 1, '', 0, 'willpowerSkillTrainingTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 228, 0, '', 0, 'charismaSkillTrainingTimeMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 229, 0, '', 0, 'intelligenceSkillTrainingTimeMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 230, 0, '', 0, 'memorySkillTrainingTimeMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 231, 0, '', 0, 'perceptionSkillTrainingTimeMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 232, 0, '', 0, 'willpowerSkillTrainingTimeMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 235, 0, 'Maximum Locked Targets Bonus', 0, 'maxLockedTargetsBonus', 139 FROM DUAL
UNION ALL SELECT 236, 0, '', 0, 'maxAttackTargetsBonus', 139 FROM DUAL
UNION ALL SELECT 237, 1, 'Targeting Range Bonus', 1391, 'maxTargetRangeMultiplier', 109 FROM DUAL
UNION ALL SELECT 238, 0, 'Gravimetric ECM Jammer Strength', 3226, 'scanGravimetricStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 239, 0, 'Ladar ECM Jammer Strength', 3228, 'scanLadarStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 240, 0, 'Magnetometric ECM Jammer Strength', 3227, 'scanMagnetometricStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 241, 0, 'RADAR ECM Jammer Strength', 3229, 'scanRadarStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 242, 1, '', 104, 'scanSpeedMultiplier', 111 FROM DUAL
UNION ALL SELECT 243, 1, 'Maximum Range Multiplier', 1391, 'maxRangeMultiplier', 104 FROM DUAL
UNION ALL SELECT 244, 1, 'Tracking Speed Multiplier', 1398, 'trackingSpeedMultiplier', 104 FROM DUAL
UNION ALL SELECT 245, 0, '', 0, 'gfxTurretID', 0 FROM DUAL
UNION ALL SELECT 246, 0, '', 0, 'gfxBoosterID', 0 FROM DUAL
UNION ALL SELECT 247, 15000, '', 1391, 'entityAttackRange', 0 FROM DUAL
UNION ALL SELECT 248, 0, '', 0, 'entityLootValueMin', 0 FROM DUAL
UNION ALL SELECT 249, 0, '', 0, 'entityLootValueMax', 0 FROM DUAL
UNION ALL SELECT 250, 0, '', 0, 'entityLootCountMin', 0 FROM DUAL
UNION ALL SELECT 251, 0, '', 0, 'entityLootCountMax', 0 FROM DUAL
UNION ALL SELECT 252, 0, 'Security Status Kill Amount', 0, 'entitySecurityStatusKillBonus', 109 FROM DUAL
UNION ALL SELECT 253, 0, '', 0, 'entitySecurityStatusAggressionBonus', 0 FROM DUAL
UNION ALL SELECT 254, 1, '', 0, 'minLootCount', 0 FROM DUAL
UNION ALL SELECT 256, 0, '', 0, 'maxLootCount', 0 FROM DUAL
UNION ALL SELECT 257, 0, '', 0, 'entityFollowRange', 0 FROM DUAL
UNION ALL SELECT 258, 0, '', 0, 'minLootValue', 0 FROM DUAL
UNION ALL SELECT 259, 0, '', 0, 'maxLootValue', 0 FROM DUAL
UNION ALL SELECT 260, 0, '', 1391, 'attackRange', 0 FROM DUAL
UNION ALL SELECT 261, 0, '', 0, 'killStatusModifier', 0 FROM DUAL
UNION ALL SELECT 262, 0, '', 0, 'attackStatusModifier', 0 FROM DUAL
UNION ALL SELECT 263, 0, 'Shield Capacity', 1384, 'shieldCapacity', 113 FROM DUAL
UNION ALL SELECT 264, 0, '', 0, 'shieldCharge', 0 FROM DUAL
UNION ALL SELECT 265, 0, 'Armor Hitpoints', 1383, 'armorHP', 113 FROM DUAL
UNION ALL SELECT 266, 0, 'Armor Damage', 0, 'armorDamage', 0 FROM DUAL
UNION ALL SELECT 267, 1, 'Armor EM Damage Resistance', 1396, 'armorEmDamageResonance', 108 FROM DUAL
UNION ALL SELECT 268, 1, 'Armor Explosive Damage Resistance', 1395, 'armorExplosiveDamageResonance', 108 FROM DUAL
UNION ALL SELECT 269, 1, 'Armor Kinetic Damage Resistance', 1393, 'armorKineticDamageResonance', 108 FROM DUAL
UNION ALL SELECT 270, 1, 'Armor Thermal Damage Resistance', 1394, 'armorThermalDamageResonance', 108 FROM DUAL
UNION ALL SELECT 271, 1, 'Shield EM Damage Resistance', 1396, 'shieldEmDamageResonance', 108 FROM DUAL
UNION ALL SELECT 272, 1, 'Shield Explosive Damage Resistance', 1395, 'shieldExplosiveDamageResonance', 108 FROM DUAL
UNION ALL SELECT 273, 1, 'Shield Kinetic Damage Resistance', 1393, 'shieldKineticDamageResonance', 108 FROM DUAL
UNION ALL SELECT 274, 1, 'Shield Thermal Damage Resistance', 1394, 'shieldThermalDamageResonance', 108 FROM DUAL
UNION ALL SELECT 275, 0, 'Training time multiplier ', 1392, 'skillTimeConstant', 104 FROM DUAL
UNION ALL SELECT 276, 0, 'Skill Points', 33, 'skillPoints', 0 FROM DUAL
UNION ALL SELECT 277, 0, '', 0, 'requiredSkill1Level', 0 FROM DUAL
UNION ALL SELECT 278, 0, '', 0, 'requiredSkill2Level', 0 FROM DUAL
UNION ALL SELECT 279, 0, '', 0, 'requiredSkill3Level', 0 FROM DUAL
UNION ALL SELECT 280, 0, 'Level', 33, 'skillLevel', 0 FROM DUAL
UNION ALL SELECT 281, 0, 'Maximum Flight Time', 1392, 'explosionDelay', 101 FROM DUAL
UNION ALL SELECT 282, 1, '', 0, 'launcherCapacityMultiplier', 104 FROM DUAL
UNION ALL SELECT 283, 0, 'Drone Capacity', 1084, 'droneCapacity', 9 FROM DUAL
UNION ALL SELECT 284, 0, '', 0, 'excludeGangMembers', 0 FROM DUAL
UNION ALL SELECT 285, 0, '', 0, 'excludeCorporationMembers', 0 FROM DUAL
UNION ALL SELECT 286, 0, '', 0, 'excludeHostiles', 0 FROM DUAL
UNION ALL SELECT 287, 0, '', 0, 'kDmgBonus', 0 FROM DUAL
UNION ALL SELECT 288, 0, '', 0, 'shipCPUBonus', 0 FROM DUAL
UNION ALL SELECT 289, 0, '', 0, 'turretDamageBonus', 0 FROM DUAL
UNION ALL SELECT 290, 0, '', 0, 'skillTurretDmgBonus', 0 FROM DUAL
UNION ALL SELECT 291, 0, 'CPU Skill Bonus', 0, 'cpuskillBonus', 0 FROM DUAL
UNION ALL SELECT 292, 0, 'Damage Multiplier Bonus', 1432, 'damageMultiplierBonus', 105 FROM DUAL
UNION ALL SELECT 293, 0, 'Rate Of Fire Bonus', 1397, 'rofBonus', 105 FROM DUAL
UNION ALL SELECT 294, 0, 'Optimal Range Modifier', 1391, 'rangeSkillBonus', 121 FROM DUAL
UNION ALL SELECT 295, 0, '', 1400, 'abPowerBonus', 0 FROM DUAL
UNION ALL SELECT 296, 0, '', 1400, 'acPowerBonus', 0 FROM DUAL
UNION ALL SELECT 297, 0, '', 1400, 'afPowerBonus', 0 FROM DUAL
UNION ALL SELECT 298, 0, '', 0, 'atPowerBonus', 0 FROM DUAL
UNION ALL SELECT 299, 0, '', 0, 'cbTRangeBonus', 0 FROM DUAL
UNION ALL SELECT 300, 0, '', 0, 'ccTRangeBonus', 0 FROM DUAL
UNION ALL SELECT 301, 0, '', 0, 'cfTRangeBonus', 0 FROM DUAL
UNION ALL SELECT 302, 0, '', 0, 'ciTRangeBonus', 0 FROM DUAL
UNION ALL SELECT 303, 0, '', 0, 'aiPowerBonus', 0 FROM DUAL
UNION ALL SELECT 304, 0, '', 0, 'ctTRangeBonus', 0 FROM DUAL
UNION ALL SELECT 305, 0, '', 0, 'gbCpuBonus', 0 FROM DUAL
UNION ALL SELECT 306, 1, 'Maximum Velocity Modifier', 1389, 'maxVelocityModifier', 109 FROM DUAL
UNION ALL SELECT 307, 0, '', 1392, 'scannerDurationBonus', 0 FROM DUAL
UNION ALL SELECT 308, 0, 'Scan Speed Bonus', 0, 'scanspeedBonus', 105 FROM DUAL
UNION ALL SELECT 309, 0, 'Maximum Targeting Range Bonus', 1391, 'maxTargetRangeBonus', 105 FROM DUAL
UNION ALL SELECT 310, 0, 'CPU Need Bonus', 0, 'cpuNeedBonus', 105 FROM DUAL
UNION ALL SELECT 311, 0, 'Maximum Target Bonus', 0, 'maxTargetBonus', 139 FROM DUAL
UNION ALL SELECT 312, 0, 'Duration Bonus', 1392, 'durationSkillBonus', 121 FROM DUAL
UNION ALL SELECT 313, 0, 'Power Output Bonus', 1400, 'powerEngineeringOutputBonus', 105 FROM DUAL
UNION ALL SELECT 314, 0, 'Cap Recharge Time Reduction', 1400, 'capRechargeBonus', 105 FROM DUAL
UNION ALL SELECT 315, 0, 'Velocity Modifier', 1389, 'velocityBonus', 121 FROM DUAL
UNION ALL SELECT 316, 0, '', 0, 'corpMemberBonus', 0 FROM DUAL
UNION ALL SELECT 317, 0, 'Capacitor Need Bonus', 1400, 'capNeedBonus', 105 FROM DUAL
UNION ALL SELECT 318, 0, 'Speed Bonus', 1389, 'speedFBonus', 105 FROM DUAL
UNION ALL SELECT 319, 0, 'Warp Capacitor Need Bonus', 0, 'warpCapacitorNeedBonus', 124 FROM DUAL
UNION ALL SELECT 320, 0, '', 0, 'powerUseBonus', 0 FROM DUAL
UNION ALL SELECT 321, 0, 'Burst Rate Of Fire', 1397, 'burstSpeed', 3 FROM DUAL
UNION ALL SELECT 322, 0, 'Burst Speed Mutator', 0, 'burstSpeedMutator', 0 FROM DUAL
UNION ALL SELECT 323, 0, 'Power Need Bonus', 1400, 'powerNeedBonus', 105 FROM DUAL
UNION ALL SELECT 324, 0, '', 0, 'barrageDmgMutator', 0 FROM DUAL
UNION ALL SELECT 325, 0, '', 0, 'barrageFalloffMutator', 0 FROM DUAL
UNION ALL SELECT 326, 0, '', 0, 'barrageDmgMultiplier', 0 FROM DUAL
UNION ALL SELECT 327, 0, 'Hitpoint Bonus', 67, 'hullHpBonus', 105 FROM DUAL
UNION ALL SELECT 328, 75, '', 0, 'barrageFalloff', 0 FROM DUAL
UNION ALL SELECT 329, 0, 'Fleet Rate of Fire Bonus', 0, 'gangRofBonus', 0 FROM DUAL
UNION ALL SELECT 330, 0, 'Booster Duration', 1392, 'boosterDuration', 101 FROM DUAL
UNION ALL SELECT 331, 0, 'Implant Slot', 2224, 'implantness', 136 FROM DUAL
UNION ALL SELECT 332, 0, '', 0, 'burstDmg', 0 FROM DUAL
UNION ALL SELECT 333, 0, '', 0, 'burstDmgMutator', 0 FROM DUAL
UNION ALL SELECT 334, 0, '', 0, 'shipPowerBonus', 0 FROM DUAL
UNION ALL SELECT 335, 0, 'Armor Hitpoint Bonus', 1383, 'armorHpBonus', 105 FROM DUAL
UNION ALL SELECT 336, 0, 'Uniformity Bonus', 0, 'uniformityBonus', 104 FROM DUAL
UNION ALL SELECT 337, 0, 'Shield Capacity Bonus', 1384, 'shieldCapacityBonus', 121 FROM DUAL
UNION ALL SELECT 338, 0, 'Recharge Rate Bonus', 1392, 'rechargeratebonus', 105 FROM DUAL
UNION ALL SELECT 349, 0, 'Falloff Bonus', 1399, 'falloffBonus', 105 FROM DUAL
UNION ALL SELECT 350, 0, '', 0, 'skillTrainingTimeBonus', 0 FROM DUAL
UNION ALL SELECT 351, 0, 'Optimal Range Bonus', 1391, 'maxRangeBonus', 105 FROM DUAL
UNION ALL SELECT 352, 0, '', 0, 'maxActiveDrones', 0 FROM DUAL
UNION ALL SELECT 353, 0, 'Maximum Active Drone Bonus', 0, 'maxActiveDroneBonus', 0 FROM DUAL
UNION ALL SELECT 354, 0, '', 0, 'maxDroneBonus', 0 FROM DUAL
UNION ALL SELECT 355, 1, 'Negotiation Multiplier', 0, 'negotiationPercentage', 0 FROM DUAL
UNION ALL SELECT 356, 0, 'Diplomacy Bonus', 0, 'diplomacyBonus', 0 FROM DUAL
UNION ALL SELECT 359, 100, 'Fast Talk Percentage', 0, 'fastTalkPercentage', 0 FROM DUAL
UNION ALL SELECT 360, 0, 'Connections Bonus', 0, 'connectionsBonus', 0 FROM DUAL
UNION ALL SELECT 361, 0, 'Criminal Connections Bonus', 0, 'criminalConnectionsBonus', 0 FROM DUAL
UNION ALL SELECT 362, 100, 'Social Bonus', 0, 'socialBonus', 105 FROM DUAL
UNION ALL SELECT 363, 100, 'Amarr Tech ', 0, 'amarrTechTimePercent', 0 FROM DUAL
UNION ALL SELECT 364, 100, 'Minmatar Tech ', 0, 'minmatarTechTimePercent', 0 FROM DUAL
UNION ALL SELECT 365, 0, 'Gallente Tech ', 0, 'gallenteTechTimePercent', 0 FROM DUAL
UNION ALL SELECT 366, 0, 'Caldari Tech ', 0, 'caldariTechTimePercent', 0 FROM DUAL
UNION ALL SELECT 367, 100, 'Production Time Percentage', 0, 'productionTimePercent', 0 FROM DUAL
UNION ALL SELECT 368, 100, 'Refining Time Percentage', 0, 'refiningTimePercentage', 0 FROM DUAL
UNION ALL SELECT 369, 1, 'Manufacture Cost Multiplier', 0, 'manufactureCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 370, 0, '', 0, 'amarrTechMutator', 0 FROM DUAL
UNION ALL SELECT 371, 0, '', 0, 'caldariTechMutator', 0 FROM DUAL
UNION ALL SELECT 372, 0, '', 0, 'gallenteTechMutator', 0 FROM DUAL
UNION ALL SELECT 373, 0, '', 0, 'productionTimeMutator', 0 FROM DUAL
UNION ALL SELECT 374, 0, '', 0, 'minmatarTechMutator', 0 FROM DUAL
UNION ALL SELECT 375, 0, '', 0, 'productionCostMutator', 0 FROM DUAL
UNION ALL SELECT 376, 100, 'Refining time Multiplier', 0, 'refiningTimePercent', 0 FROM DUAL
UNION ALL SELECT 377, 0, '', 0, 'refiningTimeMutator', 0 FROM DUAL
UNION ALL SELECT 378, 0, 'Refining Yield percentage', 0, 'refiningYieldPercentage', 105 FROM DUAL
UNION ALL SELECT 379, 0, 'Refining Yield Mutator', 0, 'refiningYieldMutator', 105 FROM DUAL
UNION ALL SELECT 380, 0, 'Maximum Active Factory', 0, 'maxActiveFactory', 0 FROM DUAL
UNION ALL SELECT 383, 0, 'Maximum Active Factories', 0, 'maxActiveFactories', 0 FROM DUAL
UNION ALL SELECT 384, 0, 'Maximum Research Gang Size', 0, 'maxResearchGangSize', 0 FROM DUAL
UNION ALL SELECT 385, 1, 'Production time research speed', 0, 'manufacturingTimeResearchSpeed', 0 FROM DUAL
UNION ALL SELECT 386, 100, 'Research Cost Percentage', 0, 'researchCostPercent', 0 FROM DUAL
UNION ALL SELECT 387, 1, 'Blueprint copying speed', 0, 'copySpeedPercent', 0 FROM DUAL
UNION ALL SELECT 388, 100, 'Frigate construction cost', 0, 'frigateConstructionCost', 0 FROM DUAL
UNION ALL SELECT 389, 0, 'Cruiser construction cost', 0, 'cruiserConstructionCost', 0 FROM DUAL
UNION ALL SELECT 392, 0, 'Industrial construction cost', 0, 'industrialConstructionCost', 0 FROM DUAL
UNION ALL SELECT 393, 0, 'Battleship construction cost', 1392, 'battleshipConstructionCost', 0 FROM DUAL
UNION ALL SELECT 394, 100, 'Titan construction time', 0, 'titanConstructionTime', 0 FROM DUAL
UNION ALL SELECT 395, 100, 'Station construction time', 0, 'stationConstructionTime', 0 FROM DUAL
UNION ALL SELECT 396, 100, 'Repair Cost Percentage', 0, 'repairCostPercent', 0 FROM DUAL
UNION ALL SELECT 397, 0, 'Breakthrough Percentage', 0, 'reverseEngineeringChance', 0 FROM DUAL
UNION ALL SELECT 398, 1, 'Mineral need research speed', 0, 'mineralNeedResearchSpeed', 0 FROM DUAL
UNION ALL SELECT 399, 0, 'Prototyping Chance', 0, 'duplicatingChance', 105 FROM DUAL
UNION ALL SELECT 400, 100, '', 0, 'missileStandardVelocityPecent', 0 FROM DUAL
UNION ALL SELECT 401, 0, '', 0, 'cruiseMissileVelocityPercent', 0 FROM DUAL
UNION ALL SELECT 402, 0, '', 0, 'heavyMissileSpeedPercent', 0 FROM DUAL
UNION ALL SELECT 403, 0, 'Rocket Damage Percentage', 0, 'rocketDmgPercent', 105 FROM DUAL
UNION ALL SELECT 404, 0, '', 0, 'torpedoVelocityPercent', 0 FROM DUAL
UNION ALL SELECT 405, 100, 'Defender Velocity Percentage', 0, 'defenderVelocityPercent', 105 FROM DUAL
UNION ALL SELECT 406, 100, '', 0, 'missileFOFVelocityPercent', 0 FROM DUAL
UNION ALL SELECT 407, 0, '', 0, 'researchGangSizeBonus', 0 FROM DUAL
UNION ALL SELECT 408, 0, '', 1392, 'battleshipConstructionTimeBonus', 0 FROM DUAL
UNION ALL SELECT 409, 0, '', 0, 'cruiserConstructionTimeBonus', 0 FROM DUAL
UNION ALL SELECT 410, 0, '', 0, 'frigateConstructionTimeBonus', 0 FROM DUAL
UNION ALL SELECT 411, 0, '', 0, 'industrialConstructionTimeBonus', 0 FROM DUAL
UNION ALL SELECT 412, 0, '', 0, 'connectionBonusMutator', 0 FROM DUAL
UNION ALL SELECT 413, 0, 'Criminal Connections Mutator', 0, 'criminalConnectionsMutator', 0 FROM DUAL
UNION ALL SELECT 414, 0, 'Diplomacy Mutator', 0, 'diplomacyMutator', 0 FROM DUAL
UNION ALL SELECT 415, 0, '', 0, 'fastTalkMutator', 0 FROM DUAL
UNION ALL SELECT 416, 500, '', 0, 'entityFlyRange', 0 FROM DUAL
UNION ALL SELECT 417, 0, 'Maximum Non-Race Corporation Members', 0, 'maxNonRaceCorporationMembers', 0 FROM DUAL
UNION ALL SELECT 418, 0, 'Non-Race Corporation Members Bonus', 0, 'nonRaceCorporationMembersBonus', 105 FROM DUAL
UNION ALL SELECT 419, 10000, 'Skill Points Saved', 33, 'skillPointsSaved', 0 FROM DUAL
UNION ALL SELECT 420, 0, '', 0, 'trackingBonus', 121 FROM DUAL
UNION ALL SELECT 421, 0, '', 0, 'shieldRechargerateBonus', 0 FROM DUAL
UNION ALL SELECT 422, 1, 'Tech Level', 1446, 'techLevel', 140 FROM DUAL
UNION ALL SELECT 423, 0, '', 0, 'entityDroneCount', 0 FROM DUAL
UNION ALL SELECT 424, 0, 'CPU Output Bonus', 0, 'cpuOutputBonus2', 105 FROM DUAL
UNION ALL SELECT 425, 0, '', 0, 'cpuOutputBonus', 0 FROM DUAL
UNION ALL SELECT 426, 1, 'Drone Damage Percentage', 0, 'heavyDroneDamagePercent', 109 FROM DUAL
UNION ALL SELECT 427, 0, '', 0, 'heavyDroneDamageBonus', 0 FROM DUAL
UNION ALL SELECT 428, 100, 'Mining Drone Speed Bonus', 0, 'miningDroneAmountPercent', 0 FROM DUAL
UNION ALL SELECT 429, 0, '', 0, 'miningDroneSpeedBonus', 0 FROM DUAL
UNION ALL SELECT 430, 100, '', 0, 'scoutDroneVelocityPercent', 0 FROM DUAL
UNION ALL SELECT 431, 0, '', 0, 'scoutDroneVelocityBonus', 0 FROM DUAL
UNION ALL SELECT 432, 0, 'Drone Velocity Bonus', 0, 'defenderVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 433, 0, '', 0, 'heavyMissileDamageBonus', 0 FROM DUAL
UNION ALL SELECT 434, 0, 'Mining Amount Bonus', 0, 'miningAmountBonus', 105 FROM DUAL
UNION ALL SELECT 435, 3, 'Maximum Active Command Relays', 0, 'maxGangModules', 139 FROM DUAL
UNION ALL SELECT 436, 100, '', 0, 'standingIncreasePercent', 105 FROM DUAL
UNION ALL SELECT 437, 0, 'Negotiation Bonus', 0, 'negotiationBonus', 105 FROM DUAL
UNION ALL SELECT 438, 0, 'Social Mutator', 0, 'socialMutator', 105 FROM DUAL
UNION ALL SELECT 439, 0, '', 0, 'targetingSpeedBonus', 0 FROM DUAL
UNION ALL SELECT 440, 0, 'Manufacturing Time Bonus', 1392, 'manufacturingTimeBonus', 105 FROM DUAL
UNION ALL SELECT 441, 0, 'Rate of Fire Bonus', 0, 'turretSpeeBonus', 121 FROM DUAL
UNION ALL SELECT 442, 0, '', 0, 'barterDiscount', 0 FROM DUAL
UNION ALL SELECT 443, 0, 'Trade premium', 0, 'tradePremium', 0 FROM DUAL
UNION ALL SELECT 444, 0, 'Contraband fencing chance', 0, 'contrabandFencingChance', 105 FROM DUAL
UNION ALL SELECT 445, 0, '', 0, 'smugglingChance', 0 FROM DUAL
UNION ALL SELECT 446, 0, '', 0, 'tradePremiumBonus', 0 FROM DUAL
UNION ALL SELECT 447, 0, 'Smuggling Chance Bonus', 1196, 'smugglingChanceBonus', 105 FROM DUAL
UNION ALL SELECT 448, 0, '', 0, 'fencingChanceBonus', 0 FROM DUAL
UNION ALL SELECT 449, 0, 'Barter Discount Bonus', 0, 'barterDiscountBonus', 0 FROM DUAL
UNION ALL SELECT 450, 0, 'Manufacturing Slot Bonus', 0, 'manufacturingSlotBonus', 139 FROM DUAL
UNION ALL SELECT 451, 0, '', 0, 'manufactureCostBonus', 105 FROM DUAL
UNION ALL SELECT 452, 0, 'Copy Speed Bonus', 0, 'copySpeedBonus', 105 FROM DUAL
UNION ALL SELECT 453, 0, 'Blueprint Manufacture Time Bonus', 0, 'blueprintmanufactureTimeBonus', 105 FROM DUAL
UNION ALL SELECT 454, 0, '', 0, 'mutaton', 0 FROM DUAL
UNION ALL SELECT 455, 0, 'Learning Bonus', 0, 'learningBonus', 105 FROM DUAL
UNION ALL SELECT 456, 0, '', 0, 'entityEquipmentMin', 0 FROM DUAL
UNION ALL SELECT 457, 0, '', 0, 'entityEquipmentMax', 0 FROM DUAL
UNION ALL SELECT 458, 20000, 'Maximum Drone Control Distance', 0, 'droneControlDistance', 1 FROM DUAL
UNION ALL SELECT 459, 0, 'Drone Control Range Bonus', 0, 'droneRangeBonus', 1 FROM DUAL
UNION ALL SELECT 460, 0, 'Special Ability Bonus', 0, 'shipBonusMF', 105 FROM DUAL
UNION ALL SELECT 461, 0, '', 0, 'specialAbilityBonus', 0 FROM DUAL
UNION ALL SELECT 462, 0, '', 0, 'shipBonusGF', 0 FROM DUAL
UNION ALL SELECT 463, 0, '', 0, 'shipBonusCF', 0 FROM DUAL
UNION ALL SELECT 464, 0, '', 0, 'shipBonusAF', 0 FROM DUAL
UNION ALL SELECT 465, 1, '', 0, 'entityEquipmentGroupMax', 0 FROM DUAL
UNION ALL SELECT 466, 1, '', 0, 'entityReactionFactor', 0 FROM DUAL
UNION ALL SELECT 467, 1, 'Maximum Active Laboratories', 0, 'maxLaborotorySlots', 0 FROM DUAL
UNION ALL SELECT 468, 0, 'Mineral Need Research Bonus', 0, 'mineralNeedResearchBonus', 105 FROM DUAL
UNION ALL SELECT 469, 0, '', 0, 'entityBluePrintDropChance', 0 FROM DUAL
UNION ALL SELECT 470, 600000, '', 0, 'lootRespawnTime', 101 FROM DUAL
UNION ALL SELECT 471, 0, '', 0, 'laboratorySlotsBonus', 0 FROM DUAL
UNION ALL SELECT 472, 0, '', 0, 'stationTypeID', 116 FROM DUAL
UNION ALL SELECT 473, 0, '', 0, 'prototypingBonus', 0 FROM DUAL
UNION ALL SELECT 474, 0, '', 0, 'inventionBonus', 0 FROM DUAL
UNION ALL SELECT 475, 0, '', 0, 'entityAttackDelayMin', 0 FROM DUAL
UNION ALL SELECT 476, 0, '', 0, 'entityAttackDelayMax', 0 FROM DUAL
UNION ALL SELECT 478, 0, '', 0, 'shipBonusAC', 0 FROM DUAL
UNION ALL SELECT 479, 0, 'Shield recharge time', 1392, 'shieldRechargeRate', 101 FROM DUAL
UNION ALL SELECT 480, 0, '', 0, 'maxEffectiveRange', 1 FROM DUAL
UNION ALL SELECT 481, 0, 'Bounty', 0, 'entityKillBounty', 0 FROM DUAL
UNION ALL SELECT 482, 0, 'Capacitor Capacity', 1668, 'capacitorCapacity', 114 FROM DUAL
UNION ALL SELECT 484, 0, '', 0, 'shieldUniformity', 0 FROM DUAL
UNION ALL SELECT 485, 5, '', 0, 'shipBonus2AF', 0 FROM DUAL
UNION ALL SELECT 486, 5, '', 0, 'shipBonusGC', 0 FROM DUAL
UNION ALL SELECT 487, 5, '', 0, 'shipBonusCC', 0 FROM DUAL
UNION ALL SELECT 488, 5, '', 0, 'shipVelocityBonusMC', 0 FROM DUAL
UNION ALL SELECT 489, 0, '', 0, 'shipBonusMC', 0 FROM DUAL
UNION ALL SELECT 490, 5, '', 0, 'shipBonusMB', 0 FROM DUAL
UNION ALL SELECT 491, 5, '', 0, 'shipBonusCB', 0 FROM DUAL
UNION ALL SELECT 492, 5, '', 0, 'shipBonusAB', 0 FROM DUAL
UNION ALL SELECT 493, 5, '', 0, 'shipBonusMI', 0 FROM DUAL
UNION ALL SELECT 494, 5, '', 0, 'shipBonusAI', 0 FROM DUAL
UNION ALL SELECT 495, 5, '', 0, 'shipBonusCI', 0 FROM DUAL
UNION ALL SELECT 496, 5, '', 0, 'shipBonusGI', 0 FROM DUAL
UNION ALL SELECT 497, 0, '', 0, 'entityDefenderChance', 0 FROM DUAL
UNION ALL SELECT 499, 0, '', 0, 'droneCapacityBonus', 0 FROM DUAL
UNION ALL SELECT 500, 0, '', 0, 'shipBonusGB', 0 FROM DUAL
UNION ALL SELECT 501, 0, '', 0, 'shipBonus2CB', 0 FROM DUAL
UNION ALL SELECT 502, 1, '', 0, 'entityConvoyDroneMin', 0 FROM DUAL
UNION ALL SELECT 503, 1, '', 0, 'entityConvoyDroneMax', 0 FROM DUAL
UNION ALL SELECT 504, 0, '', 0, 'entityWarpScrambleChance', 0 FROM DUAL
UNION ALL SELECT 505, 8000, 'Duration', 1392, 'warpScrambleDuration', 3 FROM DUAL
UNION ALL SELECT 506, 20000, 'Rate of fire', 0, 'missileLaunchDuration', 101 FROM DUAL
UNION ALL SELECT 507, 0, '', 0, 'entityMissileTypeID', 116 FROM DUAL
UNION ALL SELECT 508, 0, 'Orbit Velocity', 0, 'entityCruiseSpeed', 10 FROM DUAL
UNION ALL SELECT 509, 0, '', 0, 'cargoScanFalloff', 1 FROM DUAL
UNION ALL SELECT 510, 0, 'Ship Scan Falloff', 0, 'shipScanFalloff', 1 FROM DUAL
UNION ALL SELECT 511, 0, '', 0, 'shipScanResistance', 121 FROM DUAL
UNION ALL SELECT 512, 0, '', 0, 'modifyTargetSpeedChance', 0 FROM DUAL
UNION ALL SELECT 513, 5000, '', 0, 'modifyTargetSpeedDuration', 3 FROM DUAL
UNION ALL SELECT 514, 20000, '', 0, 'modifyTargetSpeedRange', 1 FROM DUAL
UNION ALL SELECT 515, 0, '', 0, 'modifyTargetSpeedCapacitorNeed', 0 FROM DUAL
UNION ALL SELECT 516, 0, 'Required chassis Type', 0, 'chassisType', 116 FROM DUAL
UNION ALL SELECT 517, 1, 'Falloff Modifier', 1399, 'fallofMultiplier', 104 FROM DUAL
UNION ALL SELECT 518, 0, '', 0, 'shipBonusMB2', 0 FROM DUAL
UNION ALL SELECT 519, 1, '', 0, 'cloakingCapacitorNeedRatio', 0 FROM DUAL
UNION ALL SELECT 522, 0, '', 0, 'damageCloudChance', 0 FROM DUAL
UNION ALL SELECT 524, 0, '', 0, 'armorUniformity', 0 FROM DUAL
UNION ALL SELECT 525, 1, '', 0, 'structureUniformity', 0 FROM DUAL
UNION ALL SELECT 526, 0, 'Research Skill Required ', 0, 'reqResearchSkill', 0 FROM DUAL
UNION ALL SELECT 527, 0, 'Required Manufacturing Skill ', 0, 'reqManufacturingSkill', 0 FROM DUAL
UNION ALL SELECT 528, 0, 'Required Manufacturing Skill Level', 0, 'reqManufacturingSkillLevel', 0 FROM DUAL
UNION ALL SELECT 529, 0, 'Required Research Skill Level', 0, 'reqResearchSkillLevel', 0 FROM DUAL
UNION ALL SELECT 530, 0, 'Required Manufacturing Tool', 0, 'reqManufacturingTool', 0 FROM DUAL
UNION ALL SELECT 531, 0, 'Required Research Tool', 0, 'reqResearchTool', 0 FROM DUAL
UNION ALL SELECT 532, 0, '', 0, 'reqResearchComponent', 0 FROM DUAL
UNION ALL SELECT 534, 0, 'Manufacturer Bonus', 0, 'Manufacturer_ID', 0 FROM DUAL
UNION ALL SELECT 535, 0, 'Modification Type', 0, 'installedMod', 0 FROM DUAL
UNION ALL SELECT 536, 0, '', 0, 'reqResearchComponetAmount', 0 FROM DUAL
UNION ALL SELECT 537, 0, 'Primary Manufacture Componet A', 0, 'reqManufacturingComponent1Amount', 0 FROM DUAL
UNION ALL SELECT 538, 0, 'Secondary Manufacture Component', 0, 'reqManufacturingComponent2Amount', 0 FROM DUAL
UNION ALL SELECT 542, 0, '', 0, 'entityStrength', 0 FROM DUAL
UNION ALL SELECT 543, 0, '', 0, 'damageCloudChanceReduction', 0 FROM DUAL
UNION ALL SELECT 544, 0, '', 0, 'cloudEffectDelay', 101 FROM DUAL
UNION ALL SELECT 545, 0, '', 0, 'cloudDuration', 101 FROM DUAL
UNION ALL SELECT 546, 0, '', 0, 'damageCloudType', 116 FROM DUAL
UNION ALL SELECT 547, 0, 'Missile Velocity Bonus', 0, 'missileVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 548, 0, 'Shield Boost Bonus', 2104, 'shieldBoostMultiplier', 121 FROM DUAL
UNION ALL SELECT 549, 0, 'Power Bonus', 0, 'powerIncrease', 107 FROM DUAL
UNION ALL SELECT 550, 0, 'Resistance Bonus', 0, 'resistanceBonus', 121 FROM DUAL
UNION ALL SELECT 551, 1, '', 0, 'rocketVelocityPercent', 0 FROM DUAL
UNION ALL SELECT 552, 100, 'Signature Radius', 1390, 'signatureRadius', 1 FROM DUAL
UNION ALL SELECT 553, 0, '', 0, 'maxGangSizeBonus', 0 FROM DUAL
UNION ALL SELECT 554, 0, 'Signature Radius Modifier', 1390, 'signatureRadiusBonus', 124 FROM DUAL
UNION ALL SELECT 555, 0, '', 0, 'cloakVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 556, 60000, 'Anchoring Delay', 1392, 'anchoringDelay', 101 FROM DUAL
UNION ALL SELECT 557, 0, 'Flight Time Bonus', 0, 'maxFlightTimeBonus', 121 FROM DUAL
UNION ALL SELECT 558, 0, '', 0, 'explosionRangeBonus', 0 FROM DUAL
UNION ALL SELECT 559, 0, '', 1401, 'Inertia', 2 FROM DUAL
UNION ALL SELECT 560, 0, 'Sensor Recalibration Time', 0, 'cloakingTargetingDelay', 101 FROM DUAL
UNION ALL SELECT 561, 0, '', 0, 'shipBonusGB2', 0 FROM DUAL
UNION ALL SELECT 562, 0, '', 0, 'entityFactionLoss', 0 FROM DUAL
UNION ALL SELECT 563, 0, '', 0, 'entitySecurityMaxGain', 0 FROM DUAL
UNION ALL SELECT 564, 0, 'Scan Resolution', 74, 'scanResolution', 102 FROM DUAL
UNION ALL SELECT 565, 0, 'Scan Resolution Bonus', 74, 'scanResolutionMultiplier', 109 FROM DUAL
UNION ALL SELECT 566, 0, 'Scan Resolution Bonus', 74, 'scanResolutionBonus', 105 FROM DUAL
UNION ALL SELECT 567, 0, 'Thrust', 96, 'speedBoostFactor', 125 FROM DUAL
UNION ALL SELECT 568, 0, '', 0, 'eliteBonusInterceptor', 0 FROM DUAL
UNION ALL SELECT 569, 0, '', 0, 'eliteBonusCovertOps1', 0 FROM DUAL
UNION ALL SELECT 570, 0, '', 0, 'eliteBonusBombers', 0 FROM DUAL
UNION ALL SELECT 571, 0, '', 0, 'eliteBonusGunships', 0 FROM DUAL
UNION ALL SELECT 573, 0, '', 0, 'eliteBonusdestroyers', 0 FROM DUAL
UNION ALL SELECT 575, 0, '', 0, 'eliteBonusBattlecruiser', 0 FROM DUAL
UNION ALL SELECT 579, 0, '', 0, 'testForEggert', 0 FROM DUAL
UNION ALL SELECT 580, 5000, '', 0, 'entityChaseMaxDelay', 0 FROM DUAL
UNION ALL SELECT 581, 1, '', 0, 'entityChaseMaxDelayChance', 0 FROM DUAL
UNION ALL SELECT 582, 5000, '', 0, 'entityChaseMaxDuration', 101 FROM DUAL
UNION ALL SELECT 583, 1, '', 0, 'entityChaseMaxDurationChance', 0 FROM DUAL
UNION ALL SELECT 584, 100000, '', 0, 'entityMaxWanderRange', 1 FROM DUAL
UNION ALL SELECT 585, 0, '', 0, 'shipBonusAB2', 0 FROM DUAL
UNION ALL SELECT 586, 0, '', 0, 'shipBonusGF2', 0 FROM DUAL
UNION ALL SELECT 587, 0, '', 0, 'shipBonusMF2', 0 FROM DUAL
UNION ALL SELECT 588, 0, '', 0, 'shipBonusCF2', 0 FROM DUAL
UNION ALL SELECT 589, 0, '', 0, 'isPlayerOwnable', 0 FROM DUAL
UNION ALL SELECT 590, 0, '', 0, 'gestaltBonus1', 0 FROM DUAL
UNION ALL SELECT 591, 0, 'Drone Velocity Bonus', 0, 'droneMaxVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 592, 0, '', 0, 'cloakCapacitorBonus', 0 FROM DUAL
UNION ALL SELECT 594, 0, '', 0, 'Die', 0 FROM DUAL
UNION ALL SELECT 595, 0, '', 0, 'capBoostMultipler', 0 FROM DUAL
UNION ALL SELECT 596, 0, 'Flight Time Bonus', 0, 'explosionDelayBonus', 121 FROM DUAL
UNION ALL SELECT 597, 10, '', 0, 'eliteBonusEscorts', 0 FROM DUAL
UNION ALL SELECT 598, 0, '', 0, 'shipBonusCB3', 0 FROM DUAL
UNION ALL SELECT 600, 3, 'Warp Speed Multiplier', 0, 'warpSpeedMultiplier', 104 FROM DUAL
UNION ALL SELECT 601, 0, 'Warp Speed Bonus', 0, 'warpSpeedBonus', 105 FROM DUAL
UNION ALL SELECT 602, 0, 'Used with (Launcher Group)', 0, 'launcherGroup2', 115 FROM DUAL
UNION ALL SELECT 603, 0, 'Used with (Launcher Group)', 0, 'launcherGroup3', 115 FROM DUAL
UNION ALL SELECT 604, 0, 'Used with (Charge Group)', 1397, 'chargeGroup1', 115 FROM DUAL
UNION ALL SELECT 605, 0, 'Used with (Charge Group)', 1397, 'chargeGroup2', 115 FROM DUAL
UNION ALL SELECT 606, 0, 'Used with (Charge Group)', 1397, 'chargeGroup3', 115 FROM DUAL
UNION ALL SELECT 608, 1, 'Turret Power Need', 0, 'powerNeedMultiplier', 109 FROM DUAL
UNION ALL SELECT 609, 0, 'Used with (Charge Group)', 1397, 'chargeGroup4', 115 FROM DUAL
UNION ALL SELECT 610, 0, 'Used with (Charge Group)', 1397, 'chargeGroup5', 115 FROM DUAL
UNION ALL SELECT 611, 0, 'Duration Bonus', 0, 'durationMultiplier', 111 FROM DUAL
UNION ALL SELECT 612, 0, 'Base Shield Damage', 69, 'baseShieldDamage', 0 FROM DUAL
UNION ALL SELECT 613, 0, 'Base Armor Damage', 68, 'baseArmorDamage', 0 FROM DUAL
UNION ALL SELECT 614, 0, 'Cargo Capacity Bonus', 71, 'cargoCapacityBonus', 121 FROM DUAL
UNION ALL SELECT 616, 0, 'Shield Booster Penalty', 0, 'boosterShieldBoostAmountPenalty', 105 FROM DUAL
UNION ALL SELECT 619, 0, 'Cloaking Targeting Delay Bonus', 0, 'cloakingTargetingDelayBonus', 105 FROM DUAL
UNION ALL SELECT 620, 1000, 'Signature Resolution', 0, 'optimalSigRadius', 1 FROM DUAL
UNION ALL SELECT 621, 1, 'Tracking Speed at Optimal Range', 0, 'trackingSpeedAtOptimal', 10 FROM DUAL
UNION ALL SELECT 622, 0, '', 0, 'massLimit', 2 FROM DUAL
UNION ALL SELECT 623, 0, '', 0, 'cloakingSlotsLeftSuper', 0 FROM DUAL
UNION ALL SELECT 624, 0, 'Warp Speed Bonus', 0, 'WarpSBonus', 105 FROM DUAL
UNION ALL SELECT 625, 0, '', 0, 'bountyBonus', 0 FROM DUAL
UNION ALL SELECT 626, 1, '', 0, 'bountyMultiplier', 0 FROM DUAL
UNION ALL SELECT 627, 0, '', 0, 'bountySkillBonus', 0 FROM DUAL
UNION ALL SELECT 628, 0, '', 0, 'bountySkillMultiplyer', 0 FROM DUAL
UNION ALL SELECT 629, 0, '', 0, 'cargoGroup', 0 FROM DUAL
UNION ALL SELECT 630, 0, '', 0, 'entityArmorRepairDuration', 0 FROM DUAL
UNION ALL SELECT 631, 0, '', 0, 'entityArmorRepairAmount', 0 FROM DUAL
UNION ALL SELECT 632, 0, '', 0, 'interceptorGF', 0 FROM DUAL
UNION ALL SELECT 633, 0, 'Meta Level', 0, 'metaLevelOld', 140 FROM DUAL
UNION ALL SELECT 634, 3, '', 0, 'newAgility', 0 FROM DUAL
UNION ALL SELECT 635, 3, '', 0, 'turnAngle', 0 FROM DUAL
UNION ALL SELECT 636, 10000, '', 0, 'entityShieldBoostDuration', 0 FROM DUAL
UNION ALL SELECT 637, 0, '', 0, 'entityShieldBoostAmount', 0 FROM DUAL
UNION ALL SELECT 638, 0, '', 0, 'entityArmorRepairDelayChance', 0 FROM DUAL
UNION ALL SELECT 639, 1, '', 0, 'entityShieldBoostDelayChance', 0 FROM DUAL
UNION ALL SELECT 640, 1, '', 0, 'entityGroupRespawnChance', 0 FROM DUAL
UNION ALL SELECT 643, 0, 'Arming Time', 0, 'armingTime', 3 FROM DUAL
UNION ALL SELECT 644, 0, '', 0, 'aimedLaunch', 0 FROM DUAL
UNION ALL SELECT 645, 1, 'Missile Velocity Bonus', 0, 'missileEntityVelocityMultiplier', 104 FROM DUAL
UNION ALL SELECT 646, 1, 'Missile Flight Time Bonus', 0, 'missileEntityFlightTimeMultiplier', 104 FROM DUAL
UNION ALL SELECT 647, 1, '', 0, 'missileEntityArmingTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 648, 0, 'Shield tuneup bonus', 0, 'shieldTUNEBonus', 113 FROM DUAL
UNION ALL SELECT 649, 0, '', 0, 'cloakingCpuNeedBonus', 0 FROM DUAL
UNION ALL SELECT 650, 0, 'Maximum Structure Distance', 0, 'maxStructureDistance', 1 FROM DUAL
UNION ALL SELECT 651, 0, 'Area of effect', 0, 'decloakFieldRange', 1 FROM DUAL
UNION ALL SELECT 652, 0, 'Signature Penalty', 0, 'signatureRadiusMultiplier', 109 FROM DUAL
UNION ALL SELECT 653, 0, 'Explosion Velocity', 1389, 'aoeVelocity', 10 FROM DUAL
UNION ALL SELECT 654, 0, 'Explosion Radius', 1390, 'aoeCloudSize', 1 FROM DUAL
UNION ALL SELECT 655, 0, '', 0, 'aoeFalloff', 0 FROM DUAL
UNION ALL SELECT 656, 0, '', 0, 'shipBonusAC2', 0 FROM DUAL
UNION ALL SELECT 657, 0, '', 0, 'shipBonusCC2', 0 FROM DUAL
UNION ALL SELECT 658, 0, '', 0, 'shipBonusGC2', 0 FROM DUAL
UNION ALL SELECT 659, 0, '', 0, 'shipBonusMC2', 0 FROM DUAL
UNION ALL SELECT 660, 0, '', 0, 'impactDamage', 113 FROM DUAL
UNION ALL SELECT 661, 1, '', 0, 'maxDirectionalVelocity', 0 FROM DUAL
UNION ALL SELECT 662, 1, '', 0, 'minTargetVelDmgMultiplier', 0 FROM DUAL
UNION ALL SELECT 663, 1, '', 0, 'minMissileVelDmgMultiplier', 0 FROM DUAL
UNION ALL SELECT 664, 1, '', 0, 'maxMissileVelocity', 0 FROM DUAL
UNION ALL SELECT 665, 2500, '', 0, 'entityChaseMaxDistance', 1 FROM DUAL
UNION ALL SELECT 666, 0, 'Restricted Ship Type', 0, 'moduleShipGroup2', 115 FROM DUAL
UNION ALL SELECT 667, 0, 'Restricted Ship Type', 0, 'moduleShipGroup3', 115 FROM DUAL
UNION ALL SELECT 668, 0, 'Restricted Ship Type', 0, 'moduleShipGroup1', 115 FROM DUAL
UNION ALL SELECT 669, 0, 'Reactivation Delay', 1392, 'moduleReactivationDelay', 101 FROM DUAL
UNION ALL SELECT 670, 0, 'Area Of Effect Bonus', 1390, 'areaOfEffectBonus', 105 FROM DUAL
UNION ALL SELECT 672, 1, 'Orbit Velocity Bonus', 0, 'entityCruiseSpeedMultiplier', 109 FROM DUAL
UNION ALL SELECT 673, 0, '', 0, 'eliteBonusGunship1', 0 FROM DUAL
UNION ALL SELECT 675, 0, '', 0, 'eliteBonusGunship2', 0 FROM DUAL
UNION ALL SELECT 676, 60000, 'Unanchoring Delay', 1392, 'unanchoringDelay', 101 FROM DUAL
UNION ALL SELECT 677, 60000, 'Onlining Delay', 1392, 'onliningDelay', 101 FROM DUAL
UNION ALL SELECT 678, 0, '', 0, 'eliteBonusLogistics1', 0 FROM DUAL
UNION ALL SELECT 679, 0, '', 0, 'eliteBonusLogistics2', 0 FROM DUAL
UNION ALL SELECT 680, 0, 'Shield Radius', 0, 'shieldRadius', 1 FROM DUAL
UNION ALL SELECT 681, 0, 'Stored Type 1', 0, 'typeContainerType1', 116 FROM DUAL
UNION ALL SELECT 682, 0, 'Stored Type 2', 0, 'typeContainerType2', 116 FROM DUAL
UNION ALL SELECT 683, 0, 'Stored Type 3', 0, 'typeContainerType3', 116 FROM DUAL
UNION ALL SELECT 684, 0, 'Storage Capacity 1', 0, 'typeContainerCapacity1', 9 FROM DUAL
UNION ALL SELECT 685, 0, 'Storage Capacity 2', 0, 'typeContainerCapacity2', 9 FROM DUAL
UNION ALL SELECT 686, 0, 'Storage Capacity 3', 0, 'typeContainerCapacity3', 9 FROM DUAL
UNION ALL SELECT 687, 0, 'Operation Consumption Rate', 0, 'operationConsumptionRate', 0 FROM DUAL
UNION ALL SELECT 688, 0, 'Reinforced Consumption Rate', 0, 'reinforcedConsumptionRate', 0 FROM DUAL
UNION ALL SELECT 689, 2391, '', 0, 'packageGraphicID', 0 FROM DUAL
UNION ALL SELECT 690, 250, '', 0, 'packageRadius', 0 FROM DUAL
UNION ALL SELECT 691, 0, '', 0, 'targetSwitchDelay', 101 FROM DUAL
UNION ALL SELECT 692, 0, '', 0, 'eliteBonusHeavyGunship1', 0 FROM DUAL
UNION ALL SELECT 693, 0, '', 0, 'eliteBonusHeavyGunship2', 0 FROM DUAL
UNION ALL SELECT 694, 0, 'Reinforced Mode Type', 0, 'resourceReinforced1Type', 116 FROM DUAL
UNION ALL SELECT 695, 0, 'Reinforced Mode Type', 0, 'resourceReinforced2Type', 116 FROM DUAL
UNION ALL SELECT 696, 0, 'Reinforced Mode Type', 0, 'resourceReinforced3Type', 116 FROM DUAL
UNION ALL SELECT 697, 0, 'Reinforced Mode Type', 0, 'resourceReinforced4Type', 116 FROM DUAL
UNION ALL SELECT 698, 0, 'Reinforced Mode Type', 0, 'resourceReinforced5Type', 116 FROM DUAL
UNION ALL SELECT 699, 0, 'Reinforced Mode Quantity', 0, 'resourceReinforced1Quantity', 9 FROM DUAL
UNION ALL SELECT 700, 0, 'Reinforced Mode Quantity', 0, 'resourceReinforced2Quantity', 9 FROM DUAL
UNION ALL SELECT 701, 0, 'Reinforced Mode Quantity', 0, 'resourceReinforced3Quantity', 9 FROM DUAL
UNION ALL SELECT 703, 0, 'Reinforced Mode Quantity', 0, 'resourceReinforced4Quantity', 9 FROM DUAL
UNION ALL SELECT 704, 0, 'Reinforced Mode Quantity', 0, 'resourceReinforced5Quantity', 9 FROM DUAL
UNION ALL SELECT 705, 0, 'Online Mode Type', 0, 'resourceOnline1Type', 116 FROM DUAL
UNION ALL SELECT 706, 0, 'Online Mode Type', 0, 'resourceOnline2Type', 116 FROM DUAL
UNION ALL SELECT 707, 0, 'Online Mode Type', 0, 'resourceOnline3Type', 116 FROM DUAL
UNION ALL SELECT 708, 0, 'Online Mode Type', 0, 'resourceOnline4Type', 0 FROM DUAL
UNION ALL SELECT 709, 0, 'Harvested Type', 0, 'harvesterType', 116 FROM DUAL
UNION ALL SELECT 710, 0, '', 0, 'harvesterQuality', 0 FROM DUAL
UNION ALL SELECT 711, 0, 'Moon Anchor Distance', 0, 'moonAnchorDistance', 1 FROM DUAL
UNION ALL SELECT 712, 0, 'Charge Usage Damage', 0, 'usageDamagePercent', 105 FROM DUAL
UNION ALL SELECT 713, 0, 'Consumption Type', 0, 'consumptionType', 116 FROM DUAL
UNION ALL SELECT 714, 0, 'Consumption Quantity', 0, 'consumptionQuantity', 138 FROM DUAL
UNION ALL SELECT 715, 0, 'Maximum Operational Distance', 0, 'maxOperationalDistance', 1 FROM DUAL
UNION ALL SELECT 716, 0, 'Maximum Concurrent Operational Users', 0, 'maxOperationalUsers', 0 FROM DUAL
UNION ALL SELECT 717, 0.5, 'Refining Yield Multiplier', 0, 'refiningYieldMultiplier', 127 FROM DUAL
UNION ALL SELECT 719, 0, 'Operational Duration', 0, 'operationalDuration', 101 FROM DUAL
UNION ALL SELECT 720, 0, 'Refinery Capacity', 0, 'refineryCapacity', 9 FROM DUAL
UNION ALL SELECT 721, 1, '', 0, 'refiningDelayMultiplier', 0 FROM DUAL
UNION ALL SELECT 722, 10000, 'Starbase Control Tower Period', 0, 'posControlTowerPeriod', 101 FROM DUAL
UNION ALL SELECT 723, 0, '', 0, 'contrabandDetectionChance', 0 FROM DUAL
UNION ALL SELECT 724, 0, '', 0, 'contrabandDetectionResistance', 0 FROM DUAL
UNION ALL SELECT 725, 0, '', 0, 'contrabandScanChance', 0 FROM DUAL
UNION ALL SELECT 726, 1, 'Moon Mining Amount', 0, 'moonMiningAmount', 9 FROM DUAL
UNION ALL SELECT 727, 0, '', 0, 'destroyerROFpenality', 0 FROM DUAL
UNION ALL SELECT 728, 0, '', 0, 'controlTowerLaserDamageBonus', 105 FROM DUAL
UNION ALL SELECT 729, 0, '', 0, 'shipBonusMD1', 0 FROM DUAL
UNION ALL SELECT 732, 0, '', 0, 'shipBonusD1', 0 FROM DUAL
UNION ALL SELECT 733, 0, '', 0, 'shipBonusD2', 0 FROM DUAL
UNION ALL SELECT 734, 0, '', 0, 'shipBonusCD1', 0 FROM DUAL
UNION ALL SELECT 735, 0, '', 0, 'shipBonusCD2', 0 FROM DUAL
UNION ALL SELECT 738, 0, '', 0, 'shipBonusGD1', 0 FROM DUAL
UNION ALL SELECT 739, 0, '', 0, 'shipBonusGD2', 0 FROM DUAL
UNION ALL SELECT 740, 0, '', 0, 'shipBonusMD2', 0 FROM DUAL
UNION ALL SELECT 741, 0, '', 0, 'shipBonusBC1', 0 FROM DUAL
UNION ALL SELECT 742, 0, '', 0, 'shipBonusBC2', 0 FROM DUAL
UNION ALL SELECT 743, 0, '', 0, 'shipBonusCBC1', 0 FROM DUAL
UNION ALL SELECT 745, 0, '', 0, 'shipBonusCBC2', 0 FROM DUAL
UNION ALL SELECT 746, 0, '', 0, 'shipBonusGBC2', 0 FROM DUAL
UNION ALL SELECT 747, 0, '', 0, 'shipBonusGBC1', 0 FROM DUAL
UNION ALL SELECT 748, 0, '', 0, 'shipBonusMBC1', 0 FROM DUAL
UNION ALL SELECT 749, 0, '', 0, 'shipBonusMBC2', 0 FROM DUAL
UNION ALL SELECT 750, 0, '', 0, 'controlTowerLaserOptimalBonus', 105 FROM DUAL
UNION ALL SELECT 751, 0, 'Hybrid Sentry Optimal Bonus', 0, 'controlTowerHybridOptimalBonus', 105 FROM DUAL
UNION ALL SELECT 752, 0, '', 0, 'controlTowerProjectileOptimalBonus', 105 FROM DUAL
UNION ALL SELECT 753, 0, '', 0, 'controlTowerProjectileFallOffBonus', 105 FROM DUAL
UNION ALL SELECT 754, 0, '', 0, 'controlTowerProjectileROFBonus', 105 FROM DUAL
UNION ALL SELECT 755, 0, '', 0, 'controlTowerMissileROFBonus', 105 FROM DUAL
UNION ALL SELECT 756, 0, '', 0, 'controlTowerMoonHarvesterCPUBonus', 105 FROM DUAL
UNION ALL SELECT 757, 0, '', 0, 'controlTowerSiloCapacityBonus', 105 FROM DUAL
UNION ALL SELECT 758, 0, '', 0, 'shipBonusDF1', 0 FROM DUAL
UNION ALL SELECT 759, 0, '', 0, 'shipBonusDF2', 0 FROM DUAL
UNION ALL SELECT 760, 0, 'Laser Sentry Proximity Bonus', 0, 'controlTowerLaserProximityRangeBonus', 105 FROM DUAL
UNION ALL SELECT 761, 0, 'Projectile Sentry Proximity Bonus', 0, 'controlTowerProjectileProximityRangeBonus', 105 FROM DUAL
UNION ALL SELECT 762, 0, 'Hybrid Sentry Proximity Bonus', 0, 'controlTowerHybridProximityRangeBonus', 105 FROM DUAL
UNION ALL SELECT 763, 0, '', 0, 'maxGroupActive', 0 FROM DUAL
UNION ALL SELECT 764, 0, 'Target Jamming Duration Bonus', 0, 'controlTowerEwRofBonus', 105 FROM DUAL
UNION ALL SELECT 765, 10, '', 0, 'scanRange', 0 FROM DUAL
UNION ALL SELECT 766, 0, '', 0, 'controlTowerHybridDamageBonus', 105 FROM DUAL
UNION ALL SELECT 767, 0, 'Tracking Speed Bonus', 1398, 'trackingSpeedBonus', 105 FROM DUAL
UNION ALL SELECT 769, 0, 'Optimal Range Bonus', 0, 'maxRangeBonus2', 105 FROM DUAL
UNION ALL SELECT 770, 0, '', 0, 'controlTowerEwTargetSwitchDelayBonus', 105 FROM DUAL
UNION ALL SELECT 771, 0, '', 0, 'ammoCapacity', 0 FROM DUAL
UNION ALL SELECT 772, 1, '', 0, 'entityFlyRangeFactor', 0 FROM DUAL
UNION ALL SELECT 773, 0, '', 0, 'shipBonusORE1', 0 FROM DUAL
UNION ALL SELECT 774, 0, '', 0, 'shipBonusORE2', 0 FROM DUAL
UNION ALL SELECT 775, 0, '', 0, 'miningCPUNeedBonus', 0 FROM DUAL
UNION ALL SELECT 776, 0, 'Missile Velocity Bonus', 0, 'structureMissileVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 777, 0, 'Missile Damage Bonus', 0, 'structureMissileDamageBonus', 105 FROM DUAL
UNION ALL SELECT 778, 0, 'Missile Explosion Delay Bonus', 0, 'structureMissileExplosionDelayBonus', 105 FROM DUAL
UNION ALL SELECT 779, 1, '', 0, 'entityFlyRangeMultiplier', 0 FROM DUAL
UNION ALL SELECT 780, 0, 'Cycle Time bonus', 0, 'iceHarvestCycleBonus', 105 FROM DUAL
UNION ALL SELECT 781, 0, 'Asteroid Specialization Group', 0, 'specializationAsteroidGroup', 115 FROM DUAL
UNION ALL SELECT 782, 0, 'Asteroid Specialization Yield Modifier', 0, 'specializationAsteroidYieldMultiplier', 104 FROM DUAL
UNION ALL SELECT 783, 0, 'Volatility', 0, 'crystalVolatilityChance', 127 FROM DUAL
UNION ALL SELECT 784, 0, 'Volatility Damage', 0, 'crystalVolatilityDamage', 113 FROM DUAL
UNION ALL SELECT 785, 0, 'Unfitting Capacitor Cost', 0, 'unfitCapCost', 114 FROM DUAL
UNION ALL SELECT 786, 0, 'Crystals Take Damage', 0, 'crystalsGetDamaged', 137 FROM DUAL
UNION ALL SELECT 787, 0, 'Minimum Scan Deviation', 0, 'minScanDeviation', 1 FROM DUAL
UNION ALL SELECT 788, 0, 'Maximum Scan Deviation', 0, 'maxScanDeviation', 1 FROM DUAL
UNION ALL SELECT 789, 0, 'Specialty Crystal Mining Amount', 0, 'specialtyMiningAmount', 9 FROM DUAL
UNION ALL SELECT 790, 0, 'Reprocessing Skill', 0, 'reprocessingSkillType', 116 FROM DUAL
UNION ALL SELECT 791, 3, 'Probes to Analyze', 0, 'scanAnalyzeCount', 0 FROM DUAL
UNION ALL SELECT 792, 0, '', 0, 'controlTowerMissileVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 793, 0, '', 0, 'shipBonusRole7', 0 FROM DUAL
UNION ALL SELECT 794, 3, '', 0, 'probesInGroup', 0 FROM DUAL
UNION ALL SELECT 795, 0, '', 0, 'shipBonusABC1', 0 FROM DUAL
UNION ALL SELECT 796, 0, 'Mass Addition', 76, 'massAddition', 2 FROM DUAL
UNION ALL SELECT 797, 300000, 'Theoretical Maximum Targeting Range', 1391, 'maximumRangeCap', 1 FROM DUAL
UNION ALL SELECT 798, 0, '', 0, 'entityBracketColour', 0 FROM DUAL
UNION ALL SELECT 799, 2, 'Talisman Set Bonus', 0, 'implantSetBloodraider', 104 FROM DUAL
UNION ALL SELECT 800, 0, 'Contraband Detection Modifier', 0, 'contrabandDetectionChanceBonus', 127 FROM DUAL
UNION ALL SELECT 801, 0, '', 0, 'deadspaceUnsafe', 0 FROM DUAL
UNION ALL SELECT 802, 0, 'Snake Set Bonus', 0, 'implantSetSerpentis', 104 FROM DUAL
UNION ALL SELECT 803, 0, 'Asklepian Set Bonus', 0, 'implantSetSerpentis2', 104 FROM DUAL
UNION ALL SELECT 804, 0, '', 0, 'eliteBonusInterceptor2', 0 FROM DUAL
UNION ALL SELECT 805, 0, 'Quantity', 0, 'quantity', 0 FROM DUAL
UNION ALL SELECT 806, 0, 'Repair Bonus', 0, 'repairBonus', 105 FROM DUAL
UNION ALL SELECT 807, 0, '', 0, 'eliteBonusIndustrial1', 0 FROM DUAL
UNION ALL SELECT 808, 0, '', 0, 'eliteBonusIndustrial2', 0 FROM DUAL
UNION ALL SELECT 809, 0, '', 0, 'shipBonusAI2', 0 FROM DUAL
UNION ALL SELECT 811, 0, '', 0, 'shipBonusCI2', 0 FROM DUAL
UNION ALL SELECT 813, 0, '', 0, 'shipBonusGI2', 0 FROM DUAL
UNION ALL SELECT 814, 0, '', 0, 'shipBonusMI2', 0 FROM DUAL
UNION ALL SELECT 815, 0, 'Fusion Strength', 0, 'propulsionFusionStrengthBonus', 120 FROM DUAL
UNION ALL SELECT 816, 0, 'Ion Strength', 0, 'propulsionIonStrengthBonus', 120 FROM DUAL
UNION ALL SELECT 817, 0, 'Magpulse Strength', 0, 'propulsionMagpulseStrengthBonus', 120 FROM DUAL
UNION ALL SELECT 818, 0, 'Plasma Strength', 0, 'propulsionPlasmaStrengthBonus', 120 FROM DUAL
UNION ALL SELECT 823, 0, '', 0, 'hitsMissilesOnly', 0 FROM DUAL
UNION ALL SELECT 828, 0, 'EW Strength Modifier', 0, 'scanSkillEwStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 829, 0, '', 0, 'propulsionSkillPropulsionStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 830, 5, '', 0, 'bonusComplexAngel10', 0 FROM DUAL
UNION ALL SELECT 831, 0, '', 0, 'ewTargetJam', 0 FROM DUAL
UNION ALL SELECT 832, 0, 'Target Painting Bonus', 0, 'scanSkillTargetPaintStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 833, 0, 'Command Bonus', 0, 'commandBonus', 121 FROM DUAL
UNION ALL SELECT 834, 0, '', 0, 'wingCommandBonus', 0 FROM DUAL
UNION ALL SELECT 837, 1, '', 0, 'stealthBomberLauncherPower', 0 FROM DUAL
UNION ALL SELECT 838, 1, 'Crystal Set Bonus', 0, 'implantSetGuristas', 104 FROM DUAL
UNION ALL SELECT 839, 0, '', 0, 'eliteBonusCovertOps2', 0 FROM DUAL
UNION ALL SELECT 840, 0, 'Agent ID', 0, 'agentID', 0 FROM DUAL
UNION ALL SELECT 841, 100000, 'Agent Comm Range', 0, 'agentCommRange', 1 FROM DUAL
UNION ALL SELECT 842, 0, 'Reaction Type 1', 0, 'reactionGroup1', 115 FROM DUAL
UNION ALL SELECT 843, 0, 'Reaction Type 2', 0, 'reactionGroup2', 115 FROM DUAL
UNION ALL SELECT 844, 5000, 'Agent Auto Popup Range', 0, 'agentAutoPopupRange', 1 FROM DUAL
UNION ALL SELECT 845, 1, '', 0, 'hiddenLauncherDamageBonus', 0 FROM DUAL
UNION ALL SELECT 846, 0, 'Scan Strength Bonus', 0, 'scanStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 847, 0, 'Explosion Velocity Bonus', 1389, 'aoeVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 848, 0, 'Explosion Radius Bonus', 1390, 'aoeCloudSizeBonus', 105 FROM DUAL
UNION ALL SELECT 849, 1, '', 0, 'canUseCargoInSpace', 0 FROM DUAL
UNION ALL SELECT 850, 0, 'Squadron Command Bonus', 0, 'squadronCommandBonus', 121 FROM DUAL
UNION ALL SELECT 851, 0, 'Capacitor Need Bonus', 0, 'shieldBoostCapacitorBonus', 124 FROM DUAL
UNION ALL SELECT 852, 0, '', 0, 'siegeModeWarpStatus', 0 FROM DUAL
UNION ALL SELECT 853, 1, '', 0, 'advancedAgility', 0 FROM DUAL
UNION ALL SELECT 854, 0, 'Disallows Assistance', 0, 'disallowAssistance', 137 FROM DUAL
UNION ALL SELECT 855, 0, '', 0, 'activationTargetLoss', 0 FROM DUAL
UNION ALL SELECT 857, 0, '', 0, 'aoeFalloffBonus', 105 FROM DUAL
UNION ALL SELECT 858, 1, 'Explosion Radius Bonus', 0, 'missileEntityAoeCloudSizeMultiplier', 109 FROM DUAL
UNION ALL SELECT 859, 1, 'Explosion Velocity Bonus', 0, 'missileEntityAoeVelocityMultiplier', 109 FROM DUAL
UNION ALL SELECT 860, 1, '', 0, 'missileEntityAoeFalloffMultiplier', 0 FROM DUAL
UNION ALL SELECT 861, 0, 'Onboard Jump Drive', 0, 'canJump', 137 FROM DUAL
UNION ALL SELECT 862, 0, '', 0, 'usageWeighting', 0 FROM DUAL
UNION ALL SELECT 863, 1, 'Halo Set Bonus', 0, 'implantSetHalo', 104 FROM DUAL
UNION ALL SELECT 864, 0, 'Amulet Set Bonus', 0, 'implantSetAmulet', 104 FROM DUAL
UNION ALL SELECT 865, 100000, '', 0, 'planetAnchorDistance', 1 FROM DUAL
UNION ALL SELECT 866, 0, 'Jump Drive Fuel Need', 0, 'jumpDriveConsumptionType', 116 FROM DUAL
UNION ALL SELECT 867, 0, 'Maximum Jump Range', 1391, 'jumpDriveRange', 126 FROM DUAL
UNION ALL SELECT 868, 2000, 'Jump Drive Consumption Amount', 0, 'jumpDriveConsumptionAmount', 138 FROM DUAL
UNION ALL SELECT 869, 300000, '', 0, 'jumpDriveDuration', 0 FROM DUAL
UNION ALL SELECT 870, 0, 'Jump Drive Range Bonus', 0, 'jumpDriveRangeBonus', 105 FROM DUAL
UNION ALL SELECT 871, 0, '', 0, 'jumpDriveDurationBonus', 105 FROM DUAL
UNION ALL SELECT 872, 0, '', 0, 'disallowOffensiveModifiers', 0 FROM DUAL
UNION ALL SELECT 874, 1, '', 0, 'advancedCapitalAgility', 0 FROM DUAL
UNION ALL SELECT 884, 0, 'Mindlink Bonus', 0, 'mindlinkBonus', 105 FROM DUAL
UNION ALL SELECT 885, 0, 'Consumption Quantity Bonus', 0, 'consumptionQuantityBonus', 138 FROM DUAL
UNION ALL SELECT 886, 0, '', 0, 'freighterBonusA1', 0 FROM DUAL
UNION ALL SELECT 887, 0, '', 0, 'freighterBonusA2', 0 FROM DUAL
UNION ALL SELECT 888, 0, '', 0, 'freighterBonusC1', 0 FROM DUAL
UNION ALL SELECT 889, 0, '', 0, 'freighterBonusC2', 0 FROM DUAL
UNION ALL SELECT 890, 0, '', 0, 'freighterBonusG2', 0 FROM DUAL
UNION ALL SELECT 891, 0, '', 0, 'freighterBonusG1', 0 FROM DUAL
UNION ALL SELECT 892, 0, '', 0, 'freighterBonusM1', 0 FROM DUAL
UNION ALL SELECT 893, 0, '', 0, 'freighterBonusM2', 0 FROM DUAL
UNION ALL SELECT 894, 0, 'Speed Boost Bonus', 0, 'speedBoostBonus', 105 FROM DUAL
UNION ALL SELECT 895, 0, 'Armor Repair Bonus', 80, 'armorDamageAmountBonus', 105 FROM DUAL
UNION ALL SELECT 896, 0, 'Armor Repair Duration Bonus', 1392, 'armorDamageDurationBonus', 105 FROM DUAL
UNION ALL SELECT 897, 0, 'Shield Boost Duration Bonus', 1392, 'shieldBonusDurationBonus', 105 FROM DUAL
UNION ALL SELECT 898, 1, 'Jump Drive Capacitor Need', 90, 'jumpDriveCapacitorNeed', 127 FROM DUAL
UNION ALL SELECT 899, 0, 'Jump Drive Capacitor Need Bonus', 90, 'jumpDriveCapacitorNeedBonus', 105 FROM DUAL
UNION ALL SELECT 901, 0, 'Access Difficulty', 0, 'accessDifficulty', 0 FROM DUAL
UNION ALL SELECT 902, 0, 'Access Difficulty Bonus', 0, 'accessDifficultyBonus', 121 FROM DUAL
UNION ALL SELECT 903, 0, '', 0, 'spawnWithoutGuardsToo', 0 FROM DUAL
UNION ALL SELECT 904, 0, '', 0, 'warcruiserCPUBonus', 0 FROM DUAL
UNION ALL SELECT 905, 10, '', 0, 'tacklerBonus', 0 FROM DUAL
UNION ALL SELECT 906, 0, '', 0, 'disallowEarlyDeactivation', 0 FROM DUAL
UNION ALL SELECT 907, 0, '', 0, 'hasShipMaintenanceBay', 0 FROM DUAL
UNION ALL SELECT 908, 0, 'Ship Maintenance Bay Capacity', 71, 'shipMaintenanceBayCapacity', 9 FROM DUAL
UNION ALL SELECT 909, 0, '', 0, 'maxShipGroupActiveID', 0 FROM DUAL
UNION ALL SELECT 910, 0, 'Maximum Group Active', 0, 'maxShipGroupActive', 0 FROM DUAL
UNION ALL SELECT 911, 0, '', 0, 'hasFleetHangars', 0 FROM DUAL
UNION ALL SELECT 912, 0, 'Fleet Hangar Capacity', 71, 'fleetHangarCapacity', 9 FROM DUAL
UNION ALL SELECT 913, 0, '', 0, 'gallenteNavyBonus', 0 FROM DUAL
UNION ALL SELECT 915, 0, '', 0, 'caldariNavyBonus', 0 FROM DUAL
UNION ALL SELECT 917, 0, '', 0, 'amarrNavyBonus', 0 FROM DUAL
UNION ALL SELECT 919, 0, '', 0, 'republicFleetBonus', 0 FROM DUAL
UNION ALL SELECT 921, 0, '', 0, 'oreCompression', 0 FROM DUAL
UNION ALL SELECT 924, 0, '', 0, 'eliteBonusBarge1', 0 FROM DUAL
UNION ALL SELECT 925, 0, '', 0, 'eliteBonusBarge2', 0 FROM DUAL
UNION ALL SELECT 926, 0, '', 0, 'shipBonusORE3', 0 FROM DUAL
UNION ALL SELECT 927, 0, 'CPU Penalty Reduction', 1405, 'miningUpgradeCPUReductionBonus', 105 FROM DUAL
UNION ALL SELECT 928, 1, '', 0, 'entityTargetJam', 0 FROM DUAL
UNION ALL SELECT 929, 30000, 'ECM Activation time / duration', 0, 'ECMDuration', 101 FROM DUAL
UNION ALL SELECT 930, 0, '', 0, 'ECMEntityChance', 0 FROM DUAL
UNION ALL SELECT 931, 0, '', 0, 'energyNeutralizerEntityChance', 0 FROM DUAL
UNION ALL SELECT 932, 0, '', 0, 'entitySensorDampenDurationChance', 0 FROM DUAL
UNION ALL SELECT 933, 0, '', 0, 'npcTrackingDisruptorActivationChance', 0 FROM DUAL
UNION ALL SELECT 935, 0, '', 0, 'entityTargetPaintDurationChance', 0 FROM DUAL
UNION ALL SELECT 936, 0, 'ECM Optimal Range', 1391, 'ECMRangeOptimal', 1 FROM DUAL
UNION ALL SELECT 937, 0, '', 0, 'entityCapacitorDrainMaxRange', 0 FROM DUAL
UNION ALL SELECT 938, 0, '', 0, 'entitySensorDampenMaxRange', 0 FROM DUAL
UNION ALL SELECT 940, 0, '', 0, 'entityTrackingDisruptMaxRange', 0 FROM DUAL
UNION ALL SELECT 941, 0, '', 0, 'entityTargetPaintMaxRange', 0 FROM DUAL
UNION ALL SELECT 942, 30000, 'Neutralization Duration', 0, 'energyNeutralizerDuration', 101 FROM DUAL
UNION ALL SELECT 943, 30000, '', 0, 'entitySensorDampenDuration', 0 FROM DUAL
UNION ALL SELECT 944, 30000, '', 0, 'entityTrackingDisruptDuration', 0 FROM DUAL
UNION ALL SELECT 945, 30000, '', 0, 'entityTargetPaintDuration', 0 FROM DUAL
UNION ALL SELECT 946, 0, '', 0, 'entityCapacitorDrainAmount', 0 FROM DUAL
UNION ALL SELECT 947, 1, '', 0, 'entitySensorDampenMultiplier', 0 FROM DUAL
UNION ALL SELECT 948, 1, '', 0, 'entityTrackingDisruptMultiplier', 0 FROM DUAL
UNION ALL SELECT 949, 1, '', 0, 'entityTargetPaintMultiplier', 0 FROM DUAL
UNION ALL SELECT 950, 0, '', 0, 'entitySensorDampenFallOff', 0 FROM DUAL
UNION ALL SELECT 951, 0, '', 0, 'entityTrackingDisruptFallOff', 0 FROM DUAL
UNION ALL SELECT 952, 0, '', 0, 'entityCapacitorFallOff', 0 FROM DUAL
UNION ALL SELECT 953, 0, 'ECM Falloff Range', 1399, 'ECMRangeFalloff', 1 FROM DUAL
UNION ALL SELECT 954, 0, '', 0, 'entityTargetPaintFallOff', 0 FROM DUAL
UNION ALL SELECT 955, 0, '', 0, 'isCaldariNavy', 0 FROM DUAL
UNION ALL SELECT 956, 0, '', 0, 'damageModifierMultiplierBonus', 0 FROM DUAL
UNION ALL SELECT 957, 0, '', 0, 'cNavyModOncNavyShip', 0 FROM DUAL
UNION ALL SELECT 958, 0, 'Hardening Bonus', 0, 'hardeningBonus', 105 FROM DUAL
UNION ALL SELECT 959, 0, '', 0, 'entityShieldBoostLargeDelayChance', 0 FROM DUAL
UNION ALL SELECT 960, 0, '', 0, 'caldariNavyBonusMultiplier2', 0 FROM DUAL
UNION ALL SELECT 961, 0, '', 0, 'caldarNavyBonus2', 0 FROM DUAL
UNION ALL SELECT 962, 0, '', 0, 'eliteBonusReconShip1', 0 FROM DUAL
UNION ALL SELECT 963, 0, '', 0, 'eliteBonusReconShip2', 0 FROM DUAL
UNION ALL SELECT 964, 1, '', 1396, 'passiveEmDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 965, 1, '', 1394, 'passiveThermalDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 966, 1, '', 1393, 'passiveKineticDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 967, 1, '', 1395, 'passiveExplosiveDamageResonanceMultiplier', 111 FROM DUAL
UNION ALL SELECT 968, 0, '', 0, 'hasStasisWeb', 0 FROM DUAL
UNION ALL SELECT 969, 0, '', 1396, 'activeEmDamageResonance', 111 FROM DUAL
UNION ALL SELECT 970, 0, '', 1394, 'activeThermalDamageResonance', 111 FROM DUAL
UNION ALL SELECT 971, 0, '', 1393, 'activeKineticDamageResonance', 111 FROM DUAL
UNION ALL SELECT 972, 0, '', 1395, 'activeExplosiveDamageResonance', 111 FROM DUAL
UNION ALL SELECT 973, 0, 'Signature radius bonus', 1390, 'signatureRadiusBonusPercent', 105 FROM DUAL
UNION ALL SELECT 974, 1, 'Structure EM Damage Resistance', 1396, 'hullEmDamageResonance', 108 FROM DUAL
UNION ALL SELECT 975, 1, 'Structure Explosive Damage Resistance', 1395, 'hullExplosiveDamageResonance', 108 FROM DUAL
UNION ALL SELECT 976, 1, 'Structure Kinetic Damage Resistance', 1393, 'hullKineticDamageResonance', 108 FROM DUAL
UNION ALL SELECT 977, 1, 'Structure Thermal Damage Resistance', 1394, 'hullThermalDamageResonance', 108 FROM DUAL
UNION ALL SELECT 978, 0, '', 0, 'maxGroupOnline', 0 FROM DUAL
UNION ALL SELECT 979, 0, 'Maximum Jump Clones', 34, 'maxJumpClones', 0 FROM DUAL
UNION ALL SELECT 980, 0, '', 0, 'hasCloneJumpSlots', 0 FROM DUAL
UNION ALL SELECT 981, 0, '', 0, 'allowsCloneJumpsWhenActive', 0 FROM DUAL
UNION ALL SELECT 982, 0, '', 0, 'canReceiveCloneJumps', 0 FROM DUAL
UNION ALL SELECT 983, 0, 'Signature Radius Modifier', 1390, 'signatureRadiusAdd', 1 FROM DUAL
UNION ALL SELECT 984, 0, 'EM Damage Resistance Bonus', 1396, 'emDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 985, 0, 'Explosive Damage Resistance Bonus', 1395, 'explosiveDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 986, 0, 'Kinetic Damage Resistance Bonus', 1393, 'kineticDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 987, 0, 'Thermal Damage Resistance Bonus', 1394, 'thermalDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 988, 0, '', 0, 'hardeningbonus2', 0 FROM DUAL
UNION ALL SELECT 989, 0, '', 0, 'volumePostPercent', 0 FROM DUAL
UNION ALL SELECT 990, 0, '', 0, 'activeEmResistanceBonus', 0 FROM DUAL
UNION ALL SELECT 991, 0, '', 0, 'activeExplosiveResistanceBonus', 0 FROM DUAL
UNION ALL SELECT 992, 0, '', 0, 'activeThermicResistanceBonus', 0 FROM DUAL
UNION ALL SELECT 993, 0, '', 0, 'activeKineticResistanceBonus', 0 FROM DUAL
UNION ALL SELECT 994, 0, 'Passive EM Damage Resistance Bonus', 1396, 'passiveEmDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 995, 0, 'Passive Explosive Damage Resistance Bonus', 1395, 'passiveExplosiveDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 996, 0, 'Passive Kinetic Damage Resistance Bonus', 1393, 'passiveKineticDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 997, 0, 'Passive Thermal Damage Resistance Bonus', 1394, 'passiveThermicDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 998, 0, '', 0, 'isRAMcompatible', 0 FROM DUAL
UNION ALL SELECT 999, 0, '', 0, 'eliteBonusCommandShips2', 0 FROM DUAL
UNION ALL SELECT 1000, 0, '', 0, 'eliteBonusCommandShips1', 0 FROM DUAL
UNION ALL SELECT 1001, 0, '', 0, 'jumpPortalConsumptionMassFactor', 0 FROM DUAL
UNION ALL SELECT 1002, 300000, '', 0, 'jumpPortalDuration', 101 FROM DUAL
UNION ALL SELECT 1003, 0, '', 0, 'eliteBonusCommandShip1DONOTUSE', 0 FROM DUAL
UNION ALL SELECT 1004, 0, '', 0, 'eliteBonusCommandShip2DONOTUSE', 0 FROM DUAL
UNION ALL SELECT 1005, 0, 'Jump portal activation cost', 90, 'jumpPortalCapacitorNeed', 114 FROM DUAL
UNION ALL SELECT 1006, 0, '', 0, 'entityShieldBoostDelayChanceSmall', 0 FROM DUAL
UNION ALL SELECT 1007, 0, '', 0, 'entityShieldBoostDelayChanceMedium', 0 FROM DUAL
UNION ALL SELECT 1008, 0, '', 0, 'entityShieldBoostDelayChanceLarge', 0 FROM DUAL
UNION ALL SELECT 1009, 0, '', 0, 'entityArmorRepairDelayChanceSmall', 0 FROM DUAL
UNION ALL SELECT 1010, 0, '', 0, 'entityArmorRepairDelayChanceMedium', 0 FROM DUAL
UNION ALL SELECT 1011, 0, '', 0, 'entityArmorRepairDelayChanceLarge', 0 FROM DUAL
UNION ALL SELECT 1012, 0, '', 0, 'eliteBonusInterdictors1', 0 FROM DUAL
UNION ALL SELECT 1013, 0, '', 0, 'eliteBonusInterdictors2', 0 FROM DUAL
UNION ALL SELECT 1014, 0, 'Cannot Auto Repeat', 0, 'disallowRepeatingActivation', 137 FROM DUAL
UNION ALL SELECT 1015, 1, '', 0, 'entityShieldBoostDelayChanceSmallMultiplier', 0 FROM DUAL
UNION ALL SELECT 1016, 1, '', 0, 'entityShieldBoostDelayChanceMediumMultiplier', 0 FROM DUAL
UNION ALL SELECT 1017, 1, '', 0, 'entityShieldBoostDelayChanceLargeMultiplier', 0 FROM DUAL
UNION ALL SELECT 1018, 1, '', 0, 'entityArmorRepairDelayChanceSmallMultiplier', 0 FROM DUAL
UNION ALL SELECT 1019, 1, '', 0, 'entityArmorRepairDelayChanceMediumMultiplier', 0 FROM DUAL
UNION ALL SELECT 1020, 1, '', 0, 'entityArmorRepairDelayChanceLargeMultiplier', 0 FROM DUAL
UNION ALL SELECT 1021, 15000, '', 0, 'warpAccuracyMaxRange', 0 FROM DUAL
UNION ALL SELECT 1022, 1, '', 0, 'warpAccuracyFactor', 0 FROM DUAL
UNION ALL SELECT 1023, 1, '', 0, 'warpAccuracyFactorMultiplier', 0 FROM DUAL
UNION ALL SELECT 1024, 1, '', 0, 'warpAccuracyMaxRangeMultiplier', 0 FROM DUAL
UNION ALL SELECT 1025, 0, '', 0, 'warpAccuracyFactorPercentage', 0 FROM DUAL
UNION ALL SELECT 1026, 0, '', 0, 'warpAccuracyMaxRangePercentage', 0 FROM DUAL
UNION ALL SELECT 1027, 0, 'Gravimetric Strength', 2028, 'scanGravimetricStrengthPercent', 105 FROM DUAL
UNION ALL SELECT 1028, 0, 'Ladar Strength', 2030, 'scanLadarStrengthPercent', 105 FROM DUAL
UNION ALL SELECT 1029, 0, 'Magnetometric Strength', 2029, 'scanMagnetometricStrengthPercent', 105 FROM DUAL
UNION ALL SELECT 1030, 0, 'Radar Strength', 2031, 'scanRadarStrengthPercent', 105 FROM DUAL
UNION ALL SELECT 1031, 1, 'Control Tower Size', 0, 'controlTowerSize', 117 FROM DUAL
UNION ALL SELECT 1032, 1, 'Restricted To Security Level Of At Most', 0, 'anchoringSecurityLevelMax', 0 FROM DUAL
UNION ALL SELECT 1033, 0, '', 0, 'anchoringRequiresSovereignty', 0 FROM DUAL
UNION ALL SELECT 1034, 30000, 'Cloak Reactivation Delay', 0, 'covertOpsAndReconOpsCloakModuleDelay', 101 FROM DUAL
UNION ALL SELECT 1035, 20000, '', 0, 'covertOpsStealthBomberTargettingDelay', 101 FROM DUAL
UNION ALL SELECT 1045, 0, 'Maximum Tractor Velocity', 1389, 'maxTractorVelocity', 10 FROM DUAL
UNION ALL SELECT 1047, 1, '', 0, 'canNotBeTrainedOnTrial', 0 FROM DUAL
UNION ALL SELECT 1048, 0, '', 0, 'disallowOffensiveModifierBonus', 0 FROM DUAL
UNION ALL SELECT 1073, 0, 'Number of Jump Clones Allowed', 0, 'maxJumpClonesBonus', 121 FROM DUAL
UNION ALL SELECT 1074, 0, 'Banned in Empire Space', 0, 'disallowInEmpireSpace', 137 FROM DUAL
UNION ALL SELECT 1075, 0, '', 0, 'missileNeverDoesDamage', 0 FROM DUAL
UNION ALL SELECT 1076, 0, 'Velocity Modifier', 1389, 'implantBonusVelocity', 121 FROM DUAL
UNION ALL SELECT 1077, 0, 'Drone Modules', 0, 'maxDCUModules', 0 FROM DUAL
UNION ALL SELECT 1079, 0, 'Capacitor Modifier', 0, 'capacitorCapacityBonus', 105 FROM DUAL
UNION ALL SELECT 1080, 0, '', 0, 'cpuPenaltySuperWeapon', 0 FROM DUAL
UNION ALL SELECT 1081, 0, '', 0, 'cpuBonusSuperWeapon', 0 FROM DUAL
UNION ALL SELECT 1082, 0, 'Cpu Penalty', 0, 'cpuPenaltyPercent', 105 FROM DUAL
UNION ALL SELECT 1083, 0, 'Armor Hitpoint Bonus', 1383, 'armorHpBonus2', 121 FROM DUAL
UNION ALL SELECT 1084, 0, 'Velocity Modifier', 1389, 'velocityBonus2', 121 FROM DUAL
UNION ALL SELECT 1085, 0, '', 0, 'hasFuelCargo', 0 FROM DUAL
UNION ALL SELECT 1086, 0, 'Fuel Cargo Capacity', 0, 'fuelCargoCapacity', 0 FROM DUAL
UNION ALL SELECT 1087, 0, 'Booster Slot', 0, 'boosterness', 136 FROM DUAL
UNION ALL SELECT 1088, 0, '', 0, 'expiryTime', 0 FROM DUAL
UNION ALL SELECT 1089, 0, 'Chance of Side Effect', 0, 'boosterEffectChance1', 127 FROM DUAL
UNION ALL SELECT 1090, 0, '', 0, 'boosterEffectChance2', 127 FROM DUAL
UNION ALL SELECT 1091, 0, '', 0, 'boosterEffectChance3', 127 FROM DUAL
UNION ALL SELECT 1092, 0, '', 0, 'boosterEffectChance4', 127 FROM DUAL
UNION ALL SELECT 1093, 0, '', 0, 'boosterEffectChance5', 127 FROM DUAL
UNION ALL SELECT 1094, 0, 'Capacitor Capacity Bonus', 0, 'displayCapacitorCapacityBonus', 105 FROM DUAL
UNION ALL SELECT 1095, 0, 'Shield Boost Bonus', 0, 'displayShieldBoostMultiplier', 105 FROM DUAL
UNION ALL SELECT 1096, 0, 'Shield Capacity Bonus', 0, 'displayShieldCapacityBonus', 105 FROM DUAL
UNION ALL SELECT 1097, 0, 'Explosion Velocity', 0, 'displayAoeVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 1098, 0, 'Optimal Range Bonus', 0, 'displayRangeSkillBonus', 105 FROM DUAL
UNION ALL SELECT 1099, 0, 'Side Effect Penalty', 0, 'boosterAttribute1', 105 FROM DUAL
UNION ALL SELECT 1100, 0, '', 0, 'boosterAttribute2', 0 FROM DUAL
UNION ALL SELECT 1101, 0, '', 0, 'boosterAttribute3', 0 FROM DUAL
UNION ALL SELECT 1102, 0, '', 0, 'boosterAttribute4', 0 FROM DUAL
UNION ALL SELECT 1103, 0, '', 0, 'boosterAttribute5', 0 FROM DUAL
UNION ALL SELECT 1104, 0, 'Maximum Velocity Bonus', 0, 'displayMaxVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 1105, 0, 'Armor Hitpoint Penalty', 0, 'displayArmorHpBonus', 105 FROM DUAL
UNION ALL SELECT 1106, 0, 'Missile Maximum Velocity Bonus', 0, 'displayMissileMaxVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 1107, 0, 'Armor Repaired Bonus', 0, 'displayArmorDamageAmountBonus', 105 FROM DUAL
UNION ALL SELECT 1108, 0, 'falloff bonus', 0, 'displayFalloffModifier', 105 FROM DUAL
UNION ALL SELECT 1109, 0, 'Tracking Speed bonus', 0, 'displayTrackingSpeedModifier', 105 FROM DUAL
UNION ALL SELECT 1110, 0, 'Explosion Radius Bonus', 0, 'displayAoeCloudsizeModifier', 105 FROM DUAL
UNION ALL SELECT 1111, 0, 'Optimal Range Bonus', 0, 'displayMaxRangeModifier', 105 FROM DUAL
UNION ALL SELECT 1112, 0, '', 0, 'inventionPropabilityMultiplier', 104 FROM DUAL
UNION ALL SELECT 1113, 0, '', 0, 'inventionMEModifier', 120 FROM DUAL
UNION ALL SELECT 1114, 0, '', 0, 'inventionTEModifier', 0 FROM DUAL
UNION ALL SELECT 1115, 0, '', 0, 'decryptorID', 0 FROM DUAL
UNION ALL SELECT 1116, 0, '', 0, 'scanProbeStrength', 0 FROM DUAL
UNION ALL SELECT 1117, 0, '', 0, 'scanStrengthSignatures', 0 FROM DUAL
UNION ALL SELECT 1118, 0, '', 0, 'scanStrengthDronesProbes', 0 FROM DUAL
UNION ALL SELECT 1119, 0, '', 0, 'scanStrengthScrap', 0 FROM DUAL
UNION ALL SELECT 1120, 0, '', 0, 'scanStrengthShips', 0 FROM DUAL
UNION ALL SELECT 1121, 0, '', 0, 'scanStrengthStructures', 0 FROM DUAL
UNION ALL SELECT 1122, 0, '', 0, 'maxScanGroups', 0 FROM DUAL
UNION ALL SELECT 1123, 60000, '', 0, 'scanDuration', 101 FROM DUAL
UNION ALL SELECT 1124, 0, '', 0, 'inventionMaxRunModifier', 0 FROM DUAL
UNION ALL SELECT 1125, 0, 'Negative Side Effect Chance Bonus', 0, 'boosterChanceBonus', 105 FROM DUAL
UNION ALL SELECT 1126, 0, 'Side effect Modifier', 0, 'boosterAttributeModifier', 105 FROM DUAL
UNION ALL SELECT 1127, 0, '', 0, 'interfaceID', 0 FROM DUAL
UNION ALL SELECT 1128, 0, '', 0, 'datacore1ID', 0 FROM DUAL
UNION ALL SELECT 1129, 0, '', 0, 'datacore2ID', 0 FROM DUAL
UNION ALL SELECT 1130, 0, 'ECM Strength Bonus', 0, 'ecmStrengthBonusPercent', 105 FROM DUAL
UNION ALL SELECT 1131, 0, 'Mass Modifier', 76, 'massBonusPercentage', 105 FROM DUAL
UNION ALL SELECT 1132, 0, 'Calibration', 2887, 'upgradeCapacity', 120 FROM DUAL
UNION ALL SELECT 1133, 6, '', 0, 'entityMaxVelocitySignatureRadiusMultiplier', 0 FROM DUAL
UNION ALL SELECT 1134, 0, '', 0, 'maxTargetRangeMultiplierSet', 0 FROM DUAL
UNION ALL SELECT 1135, 0, '', 0, 'scanResolutionMultiplierSet', 0 FROM DUAL
UNION ALL SELECT 1136, 0, '', 0, 'scanAllStrength', 120 FROM DUAL
UNION ALL SELECT 1137, 0, 'Rig Slots', 3266, 'rigSlots', 122 FROM DUAL
UNION ALL SELECT 1138, 10, 'Drawback', 0, 'drawback', 105 FROM DUAL
UNION ALL SELECT 1139, 10, 'Rig Drawback Reduction', 0, 'rigDrawbackBonus', 105 FROM DUAL
UNION ALL SELECT 1141, 0, 'Armor Hitpoint Penalty', 0, 'boosterArmorHPPenalty', 105 FROM DUAL
UNION ALL SELECT 1142, 0, 'Armor Repair Amount Penalty', 0, 'boosterArmorRepairAmountPenalty', 105 FROM DUAL
UNION ALL SELECT 1143, 0, 'Shield Capacity Penalty', 0, 'boosterShieldCapacityPenalty', 105 FROM DUAL
UNION ALL SELECT 1144, 0, 'Turret Optimal Range Penalty', 0, 'boosterTurretOptimalRangePenalty', 105 FROM DUAL
UNION ALL SELECT 1145, 0, 'Turret Tracking Penalty', 0, 'boosterTurretTrackingPenalty', 105 FROM DUAL
UNION ALL SELECT 1146, 0, 'Turret Falloff Penalty', 0, 'boosterTurretFalloffPenalty', 105 FROM DUAL
UNION ALL SELECT 1147, 0, 'Explosion Velocity Penalty', 0, 'boosterAOEVelocityPenalty', 105 FROM DUAL
UNION ALL SELECT 1148, 0, 'Missile Velocity Penalty', 0, 'boosterMissileVelocityPenalty', 105 FROM DUAL
UNION ALL SELECT 1149, 0, 'Missile Explosion Radius Penalty', 0, 'boosterMissileAOECloudPenalty', 105 FROM DUAL
UNION ALL SELECT 1150, 0, 'Capacitor Capacity Penalty', 0, 'boosterCapacitorCapacityPenalty', 105 FROM DUAL
UNION ALL SELECT 1151, 0, 'Velocity Penalty', 0, 'boosterMaxVelocityPenalty', 105 FROM DUAL
UNION ALL SELECT 1152, 0, '', 0, 'upgradeLoad', 0 FROM DUAL
UNION ALL SELECT 1153, 0, 'Calibration cost', 0, 'upgradeCost', 0 FROM DUAL
UNION ALL SELECT 1154, 0, 'Rig Slots', 3266, 'upgradeSlotsLeft', 122 FROM DUAL
UNION ALL SELECT 1155, 100, 'RP Cost', 0, 'researchPointCost', 120 FROM DUAL
UNION ALL SELECT 1156, 0, 'Maximum Scan Deviation Modifier', 0, 'maxScanDeviationModifier', 105 FROM DUAL
UNION ALL SELECT 1157, 0, '', 0, 'commandBonus2', 0 FROM DUAL
UNION ALL SELECT 1158, 0, 'Untargetable', 0, 'untargetable', 137 FROM DUAL
UNION ALL SELECT 1159, 0, 'Armor Hitpoint Bonus', 1383, 'armorHPBonusAdd', 113 FROM DUAL
UNION ALL SELECT 1160, 0, 'Access Difficulty Bonus Modifier', 0, 'accessDifficultyBonusModifier', 105 FROM DUAL
UNION ALL SELECT 1161, 0, '', 0, 'scanFrequencyResult', 0 FROM DUAL
UNION ALL SELECT 1162, 7200000, '', 0, 'explosionDelayWreck', 0 FROM DUAL
UNION ALL SELECT 1163, 1, '', 0, 'canCloak', 0 FROM DUAL
UNION ALL SELECT 1164, 0, 'Afterburner and Microwarpdrive Maximum Velocity Bonus', 1389, 'speedFactorBonus', 105 FROM DUAL
UNION ALL SELECT 1165, 0, 'Minimum Anchoring Distance From Starbase Shield', 0, 'controlTowerMinimumDistance', 1 FROM DUAL
UNION ALL SELECT 1167, 0, 'Player Controllable', 413, 'posPlayerControlStructure', 137 FROM DUAL
UNION ALL SELECT 1168, 0, '', 0, 'isIncapacitated', 0 FROM DUAL
UNION ALL SELECT 1169, 0, 'Generic Sensor Strength', 0, 'scanGenericStrength', 120 FROM DUAL
;
INSERT INTO BASE_ATTRIBUTES (attribute_id, default_value, display_name, icon_id, name, unit_id)
          SELECT 1170, 0, 'Armor Repair Amount', 0, 'structureArmorRepairAmount', 0 FROM DUAL
UNION ALL SELECT 1171, 0, 'Shield Repair Amount', 0, 'structureShieldRepairAmount', 0 FROM DUAL
UNION ALL SELECT 1172, 0, '', 0, 'structureArmorBoostValue', 0 FROM DUAL
UNION ALL SELECT 1173, 0, '', 0, 'structureShieldBoostValue', 0 FROM DUAL
UNION ALL SELECT 1174, 0, '', 0, 'posStructureControlAmount', 0 FROM DUAL
UNION ALL SELECT 1175, 0, '', 0, 'heatHi', 0 FROM DUAL
UNION ALL SELECT 1176, 0, '', 0, 'heatMed', 0 FROM DUAL
UNION ALL SELECT 1177, 0, '', 0, 'heatLow', 0 FROM DUAL
UNION ALL SELECT 1178, 0, '', 0, 'heatCapacityHi', 0 FROM DUAL
UNION ALL SELECT 1179, 0, '', 0, 'heatDissipationRateHi', 0 FROM DUAL
UNION ALL SELECT 1180, 0, '', 0, 'heatAbsorbtionRateModifier', 0 FROM DUAL
UNION ALL SELECT 1181, 0, 'Overload Duration Modifier', 1386, 'overloadDurationBonus', 105 FROM DUAL
UNION ALL SELECT 1182, 0, '', 0, 'heatAbsorbtionRateHi', 0 FROM DUAL
UNION ALL SELECT 1183, 0, '', 0, 'heatAbsorbtionRateMed', 0 FROM DUAL
UNION ALL SELECT 1184, 0, '', 0, 'heatAbsorbtionRateLow', 0 FROM DUAL
UNION ALL SELECT 1185, 0, 'Required Sovereignty Level', 0, 'onliningRequiresSovereigntyLevel', 0 FROM DUAL
UNION ALL SELECT 1190, 0, 'EW Capacitor Need Bonus', 1400, 'ewCapacitorNeedBonus', 105 FROM DUAL
UNION ALL SELECT 1191, 0, 'Maximum Active Drone Modifier', 0, 'maxDronePercentageBonus', 105 FROM DUAL
UNION ALL SELECT 1192, 0, 'Triage CPU Need Bonus', 0, 'triageCpuNeedBonus', 105 FROM DUAL
UNION ALL SELECT 1193, 0, 'Burst Projector Duration Bonus', 0, 'projECMDurationBonus', 105 FROM DUAL
UNION ALL SELECT 1194, 0, 'Projected ECM CPU Need Bonus', 0, 'projECMCpuNeedBonus', 105 FROM DUAL
UNION ALL SELECT 1195, 0, 'Maximum Anchoring Amount per Solar System', 0, 'posAnchoredPerSolarSystemAmount', 0 FROM DUAL
UNION ALL SELECT 1196, 0, '', 0, 'heatDissipationRateMed', 0 FROM DUAL
UNION ALL SELECT 1198, 0, '', 0, 'heatDissipationRateLow', 0 FROM DUAL
UNION ALL SELECT 1199, 0, '', 0, 'heatCapacityMed', 0 FROM DUAL
UNION ALL SELECT 1200, 0, '', 0, 'heatCapacityLow', 0 FROM DUAL
UNION ALL SELECT 1205, 0, 'Overload rate of fire bonus', 1386, 'overloadRofBonus', 105 FROM DUAL
UNION ALL SELECT 1206, 0, 'Overload duration bonus', 1386, 'overloadSelfDurationBonus', 105 FROM DUAL
UNION ALL SELECT 1207, 0, '', 0, 'isGlobal', 0 FROM DUAL
UNION ALL SELECT 1208, 0, 'Overload hardening bonus', 1386, 'overloadHardeningBonus', 105 FROM DUAL
UNION ALL SELECT 1209, 0, 'Bomb Deployment CPU Bonus', 0, 'bombDeploymentCpuNeedMultiplier', 104 FROM DUAL
UNION ALL SELECT 1210, 0, 'Overload damage bonus', 1386, 'overloadDamageModifier', 105 FROM DUAL
UNION ALL SELECT 1211, 0, 'Heat Damage', 1386, 'heatDamage', 113 FROM DUAL
UNION ALL SELECT 1212, 0, 'Required Thermodynamics Level', 0, 'requiredThermoDynamicsSkill', 140 FROM DUAL
UNION ALL SELECT 1213, 0, 'Heat damage penalty', 1386, 'heatDamageBonus', 105 FROM DUAL
UNION ALL SELECT 1214, 15000, 'Maximum Control Distance', 0, 'posStructureControlDistanceMax', 1 FROM DUAL
UNION ALL SELECT 1216, 0, 'Shield Transport CPU Need Bonus', 0, 'shieldTransportCpuNeedBonus', 105 FROM DUAL
UNION ALL SELECT 1218, 0, 'Energy Transfer Array Power Need', 0, 'powerTransferPowerNeedBonus', 105 FROM DUAL
UNION ALL SELECT 1219, 0, 'Drone Armor Damage Amount Bonus', 0, 'droneArmorDamageAmountBonus', 105 FROM DUAL
UNION ALL SELECT 1220, 0, 'Drone Shield Transport Amount Bonus', 0, 'droneShieldBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1221, 0, 'Jump Delay Duration', 0, 'jumpDelayDuration', 123 FROM DUAL
UNION ALL SELECT 1222, 0, 'Overload optimal range bonus', 1386, 'overloadRangeBonus', 105 FROM DUAL
UNION ALL SELECT 1223, 0, 'Overload Speed Bonus', 1386, 'overloadSpeedFactorBonus', 105 FROM DUAL
UNION ALL SELECT 1224, 1, '', 0, 'heatGenerationMultiplier', 0 FROM DUAL
UNION ALL SELECT 1225, 0, 'Overload ECM Bonus', 1386, 'overloadECMStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 1226, 0, 'Overload ECCM Bonus', 1386, 'overloadECCMStrenghtBonus', 105 FROM DUAL
UNION ALL SELECT 1227, 0, 'Modification of Signature Radius Bonus', 1390, 'signatureRadiusBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1228, 0, 'Signature Radius Multiplier', 0, 'signatureRadiusMultiplierMultiplier', 105 FROM DUAL
UNION ALL SELECT 1229, 0, 'Heat damage modifier', 1386, 'thermodynamicsHeatDamage', 105 FROM DUAL
UNION ALL SELECT 1230, 0, 'Overload Repair Bonus', 1386, 'overloadArmorDamageAmount', 105 FROM DUAL
UNION ALL SELECT 1231, 0, 'Overload Shield Boost Bonus', 1386, 'overloadShieldBonus', 105 FROM DUAL
UNION ALL SELECT 1233, 10000, 'Strontium Bay', 0, 'capacitySecondary', 9 FROM DUAL
UNION ALL SELECT 1234, 0, '', 0, 'surveyScannerRangeBonus', 105 FROM DUAL
UNION ALL SELECT 1235, 0, '', 0, 'cargoScannerRangeBonus', 105 FROM DUAL
UNION ALL SELECT 1236, 0, '', 0, 'commandBonusEffective', 105 FROM DUAL
UNION ALL SELECT 1237, 0, '', 0, 'commandBonusAdd', 0 FROM DUAL
UNION ALL SELECT 1238, 0, '', 0, 'commandBonusEffectiveAdd', 0 FROM DUAL
UNION ALL SELECT 1239, 0, '', 0, 'shipBonusORECapital1', 0 FROM DUAL
UNION ALL SELECT 1240, 0, '', 0, 'shipBonusORECapital2', 0 FROM DUAL
UNION ALL SELECT 1243, 0, '', 0, 'shipBonusORECapital3', 0 FROM DUAL
UNION ALL SELECT 1244, 0, '', 0, 'shipBonusORECapital4', 0 FROM DUAL
UNION ALL SELECT 1245, 0, 'Disallow Activation In Warp', 0, 'disallowActivateOnWarp', 137 FROM DUAL
UNION ALL SELECT 1246, 0, '', 0, 'eliteBonusHeavyInterdictors1', 0 FROM DUAL
UNION ALL SELECT 1247, 0, '', 0, 'eliteBonusHeavyInterdictors2', 0 FROM DUAL
UNION ALL SELECT 1249, 0, '', 0, 'eliteBonusElectronicAttackShip1', 0 FROM DUAL
UNION ALL SELECT 1250, 0, '', 0, 'eliteBonusElectronicAttackShip2', 0 FROM DUAL
UNION ALL SELECT 1251, 0, '', 0, 'securityClearance', 0 FROM DUAL
UNION ALL SELECT 1252, 0, 'Uses Covert Cynosural Field Technology', 0, 'isCovert', 137 FROM DUAL
UNION ALL SELECT 1253, 0, '', 0, 'jumpHarmonics', 0 FROM DUAL
UNION ALL SELECT 1254, 0, 'Cannot Use Stargates', 0, 'canNotUseStargates', 0 FROM DUAL
UNION ALL SELECT 1255, 0, 'Drone Damage Bonus', 0, 'droneDamageBonus', 105 FROM DUAL
UNION ALL SELECT 1256, 0, '', 0, 'droneHPBonus', 105 FROM DUAL
UNION ALL SELECT 1257, 0, '', 0, 'eliteBonusBlackOps1', 0 FROM DUAL
UNION ALL SELECT 1258, 0, '', 0, 'eliteBonusBlackOps2', 0 FROM DUAL
UNION ALL SELECT 1259, 1, 'Heat Attenuation', 0, 'heatAttenuationHi', 0 FROM DUAL
UNION ALL SELECT 1261, 1, '', 0, 'heatAttenuationMed', 0 FROM DUAL
UNION ALL SELECT 1262, 1, '', 0, 'heatAttenuationLow', 0 FROM DUAL
UNION ALL SELECT 1263, 0, '', 0, 'towerHPOnlineMutator', 127 FROM DUAL
UNION ALL SELECT 1264, 10, '', 0, 'brokenRepairCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 1265, 0, '', 0, 'eliteBonusViolators1', 0 FROM DUAL
UNION ALL SELECT 1266, 0, '', 0, 'eliteBonusViolators2', 0 FROM DUAL
UNION ALL SELECT 1267, 10, '', 0, 'moduleRepairRate', 0 FROM DUAL
UNION ALL SELECT 1268, 0, '', 0, 'eliteBonusViolatorsRole1', 0 FROM DUAL
UNION ALL SELECT 1269, 0, '', 0, 'eliteBonusViolatorsRole2', 0 FROM DUAL
UNION ALL SELECT 1270, 0, 'Afterburner and Microwarpdrive Thrust Bonus', 96, 'speedBoostFactorBonus', 105 FROM DUAL
UNION ALL SELECT 1271, 0, 'Drone Bandwidth', 2987, 'droneBandwidth', 128 FROM DUAL
UNION ALL SELECT 1272, 0, 'Bandwidth Needed', 2987, 'droneBandwidthUsed', 128 FROM DUAL
UNION ALL SELECT 1273, 0, '', 0, 'droneBandwidthLoad', 0 FROM DUAL
UNION ALL SELECT 1274, 0, 'Assisted Mining Bonus', 0, 'miningTargetMultiplier', 111 FROM DUAL
UNION ALL SELECT 1275, 1, '', 0, 'droneIsAgressive', 0 FROM DUAL
UNION ALL SELECT 1276, 5, '', 0, 'nonBrokenModuleRepairCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 1277, 0.5, '', 0, 'shipBrokenModuleRepairCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 1278, 0, '', 0, 'droneIsChaotic', 0 FROM DUAL
UNION ALL SELECT 1279, 0, '', 0, 'eliteBonusViolatorsRole3', 0 FROM DUAL
UNION ALL SELECT 1280, 0, '', 0, 'eliteBonusInterceptorRole', 0 FROM DUAL
UNION ALL SELECT 1281, 0, 'Ship Warp Speed', 3759, 'baseWarpSpeed', 144 FROM DUAL
UNION ALL SELECT 1282, 1, 'Nomad Set Bonus', 0, 'implantSetThukker', 104 FROM DUAL
UNION ALL SELECT 1283, 1, '', 0, 'fightersAttackAndFollow', 0 FROM DUAL
UNION ALL SELECT 1284, 1, 'Virtue Set Bonus', 0, 'implantSetSisters', 104 FROM DUAL
UNION ALL SELECT 1285, 0, 'Quaternary Skill required', 0, 'requiredSkill4', 116 FROM DUAL
UNION ALL SELECT 1286, 0, 'Level 5 required', 0, 'requiredSkill4Level', 0 FROM DUAL
UNION ALL SELECT 1287, 0, '', 0, 'requiredSkill5Level', 0 FROM DUAL
UNION ALL SELECT 1288, 0, '', 0, 'requiredSkill6Level', 0 FROM DUAL
UNION ALL SELECT 1289, 0, 'Quinary Skill required', 0, 'requiredSkill5', 116 FROM DUAL
UNION ALL SELECT 1290, 0, 'Senary Skill required', 0, 'requiredSkill6', 116 FROM DUAL
UNION ALL SELECT 1291, 1, 'Edge Set Bonus', 0, 'implantSetSyndicate', 104 FROM DUAL
UNION ALL SELECT 1292, 1, 'Harvest Set Bonus', 0, 'implantSetORE', 104 FROM DUAL
UNION ALL SELECT 1293, 1, 'Centurion Set Bonus', 0, 'implantSetMordus', 104 FROM DUAL
UNION ALL SELECT 1294, 0, 'Nanite Repair Paste Consumption Bonus', 0, 'shipBrokenRepairCostMultiplierBonus', 121 FROM DUAL
UNION ALL SELECT 1295, 0, 'Module Repair Rate Bonus', 0, 'moduleRepairRateBonus', 105 FROM DUAL
UNION ALL SELECT 1296, 0, 'Consumption Quantity Bonus', 0, 'consumptionQuantityBonusPercentage', 105 FROM DUAL
UNION ALL SELECT 1297, 0, '', 0, 'droneFocusFire', 0 FROM DUAL
UNION ALL SELECT 1298, 0, 'Can be fitted to', 1443, 'canFitShipGroup01', 115 FROM DUAL
UNION ALL SELECT 1299, 0, 'Can be fitted to', 1443, 'canFitShipGroup02', 115 FROM DUAL
UNION ALL SELECT 1300, 0, 'Can be fitted to', 1443, 'canFitShipGroup03', 115 FROM DUAL
UNION ALL SELECT 1301, 0, 'Can be fitted to', 1443, 'canFitShipGroup04', 115 FROM DUAL
UNION ALL SELECT 1302, 0, 'Can be fitted to', 1443, 'canFitShipType1', 116 FROM DUAL
UNION ALL SELECT 1303, 0, 'Can be fitted to', 1443, 'canFitShipType2', 116 FROM DUAL
UNION ALL SELECT 1304, 0, 'Can be fitted to', 1443, 'canFitShipType3', 116 FROM DUAL
UNION ALL SELECT 1305, 0, 'Can be fitted to', 1443, 'canFitShipType4', 116 FROM DUAL
UNION ALL SELECT 1306, 0, 'Maximum Range Multiplier Bonus', 0, 'maxRangeMultiplierBonusAdditive', 104 FROM DUAL
UNION ALL SELECT 1307, 0, 'Tracking Speed Multiplier Bonus', 0, 'trackingSpeedMultiplierBonusAdditive', 104 FROM DUAL
UNION ALL SELECT 1308, 0, 'Maximum Targeting Range Bonus', 0, 'maxTargetRangeMultiplierBonusAdditive', 104 FROM DUAL
UNION ALL SELECT 1309, 0, 'Scan Resolution Bonus', 0, 'scanResolutionMultiplierBonusAdditive', 104 FROM DUAL
UNION ALL SELECT 1310, 0, '', 0, 'commandBonusHidden', 105 FROM DUAL
UNION ALL SELECT 1311, 0, '', 0, 'eliteBonusJumpFreighter1', 0 FROM DUAL
UNION ALL SELECT 1312, 0, '', 0, 'eliteBonusJumpFreighter2', 0 FROM DUAL
UNION ALL SELECT 1313, 0, 'Modification of Maximum Targeting Range Bonus', 1391, 'maxTargetRangeBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1314, 0, 'Modification of Scan Resolution Bonus', 74, 'scanResolutionBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1315, 0, 'Modification of Optimal Range Bonus', 1391, 'maxRangeBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1316, 0, 'Modification of Tracking Speed Bonus', 1398, 'trackingSpeedBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1317, 0, '', 0, 'maxRangeHidden', 1 FROM DUAL
UNION ALL SELECT 1318, 0, '', 0, 'warpScrambleStrengthHidden', 0 FROM DUAL
UNION ALL SELECT 1319, 0, '', 0, 'capacitorNeedHidden', 0 FROM DUAL
UNION ALL SELECT 1320, 0, 'ECM Command Bonus', 0, 'commandBonusECM', 105 FROM DUAL
UNION ALL SELECT 1321, 0, 'Remote Sensor Dampening Command Bonus', 0, 'commandBonusRSD', 105 FROM DUAL
UNION ALL SELECT 1322, 0, 'Tracking Disruption Command Bonus', 0, 'commandBonusTD', 105 FROM DUAL
UNION ALL SELECT 1323, 0, 'Target Painting Command Bonus', 0, 'commandBonusTP', 105 FROM DUAL
UNION ALL SELECT 1324, 0, 'Modification of Mass Reduction', 76, 'massBonusPercentageBonus', 105 FROM DUAL
UNION ALL SELECT 1325, 0, 'Modification of Afterburner and Microwarpdrive Thrust Bonus', 96, 'speedBoostFactorBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1326, 0, 'Modification of Afterburner and Microwarpdrive Maximum Velocity Bonus', 1389, 'speedFactorBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1327, 0, 'Warp Scrambler Range Bonus', 1391, 'warpScrambleRangeBonus', 121 FROM DUAL
UNION ALL SELECT 1328, 0, '', 2987, 'droneBandwidthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1329, 0, 'Drone Bandwidth Bonus', 0, 'droneBandwidthBonusAdd', 128 FROM DUAL
UNION ALL SELECT 1330, 0, '', 0, 'isHacking', 0 FROM DUAL
UNION ALL SELECT 1331, 0, '', 0, 'isArcheology', 0 FROM DUAL
UNION ALL SELECT 1332, 1399, 'Modification of Falloff Bonus', 0, 'falloffBonusBonus', 105 FROM DUAL
UNION ALL SELECT 1333, 0, '', 0, 'maxVelocityLimited', 0 FROM DUAL
UNION ALL SELECT 1334, 0, 'Maximum Velocity Limitation', 0, 'maxVelocityActivationLimit', 10 FROM DUAL
UNION ALL SELECT 1335, 0, '', 0, 'defenderRaceID', 0 FROM DUAL
UNION ALL SELECT 1336, 0, 'Unused Clone Vats', 34, 'jumpClonesLeft', 0 FROM DUAL
UNION ALL SELECT 1337, 0, '', 0, 'captureProximityRange', 0 FROM DUAL
UNION ALL SELECT 1339, 0, '', 0, 'factionDefenderID', 0 FROM DUAL
UNION ALL SELECT 1340, 0, '', 0, 'factionOffenderID', 0 FROM DUAL
UNION ALL SELECT 1341, 0, '', 0, 'factionID', 0 FROM DUAL
UNION ALL SELECT 1349, 0, '', 0, 'activationBlocked', 0 FROM DUAL
UNION ALL SELECT 1350, 0, '', 0, 'activationBlockedStrenght', 0 FROM DUAL
UNION ALL SELECT 1351, 0, 'Allowed Cargo Type', 0, 'posCargobayAcceptType', 116 FROM DUAL
UNION ALL SELECT 1352, 0, 'Allowed Cargo Group', 0, 'posCargobayAcceptGroup', 115 FROM DUAL
UNION ALL SELECT 1353, 1, '', 0, 'aoeDamageReductionFactor', 0 FROM DUAL
UNION ALL SELECT 1354, 1, '', 0, 'aoeDamageReductionSensitivity', 0 FROM DUAL
UNION ALL SELECT 1355, 0, 'Tractor Beam Range Bonus', 0, 'roleBonusTractorBeamRange', 105 FROM DUAL
UNION ALL SELECT 1356, 0, '', 0, 'shipBonusICS1', 105 FROM DUAL
UNION ALL SELECT 1357, 0, '', 0, 'roleBonusTractorBeamVelocity', 105 FROM DUAL
UNION ALL SELECT 1358, 0, '', 0, 'shipBonusICS2', 105 FROM DUAL
UNION ALL SELECT 1359, 0, '', 0, 'roleBonusSurveyScannerRange', 105 FROM DUAL
UNION ALL SELECT 1360, 0, '', 0, 'shipBonusHPExtender1', 105 FROM DUAL
UNION ALL SELECT 1361, 0, '', 0, 'eliteIndustrialCovertCloakBonus', 105 FROM DUAL
UNION ALL SELECT 1366, 0, '', 3756, 'subSystemSlot', 0 FROM DUAL
UNION ALL SELECT 1367, 0, 'Subsystem Slots', 3756, 'maxSubSystems', 0 FROM DUAL
UNION ALL SELECT 1368, 0, 'Turret Hardpoint Modifier', 361, 'turretHardPointModifier', 139 FROM DUAL
UNION ALL SELECT 1369, 0, 'Launcher Hardpoint Modifier', 169, 'launcherHardPointModifier', 139 FROM DUAL
UNION ALL SELECT 1370, 0, 'Base Scan Range', 0, 'baseScanRange', 135 FROM DUAL
UNION ALL SELECT 1371, 0, 'Base Sensor Strength', 0, 'baseSensorStrength', 120 FROM DUAL
UNION ALL SELECT 1372, 0, 'Base Maximum Deviation', 0, 'baseMaxScanDeviation', 135 FROM DUAL
UNION ALL SELECT 1373, 0, 'Scan Range Increment Factor', 0, 'rangeFactor', 104 FROM DUAL
UNION ALL SELECT 1374, 0, 'High Slot Modifier', 293, 'hiSlotModifier', 139 FROM DUAL
UNION ALL SELECT 1375, 0, 'Medium Slot Modifier', 294, 'medSlotModifier', 139 FROM DUAL
UNION ALL SELECT 1376, 0, 'Low Slot Modifier', 295, 'lowSlotModifier', 139 FROM DUAL
UNION ALL SELECT 1377, 0, 'CPU Output', 1405, 'cpuOutputAdd', 106 FROM DUAL
UNION ALL SELECT 1378, 0, 'powergrid Output', 1400, 'powerOutputAdd', 107 FROM DUAL
UNION ALL SELECT 1379, 0, 'Maximum Velocity', 1389, 'maxVelocityAdd', 11 FROM DUAL
UNION ALL SELECT 1380, 0, 'Restricted to Ship Type', 1443, 'fitsToShipType', 116 FROM DUAL
UNION ALL SELECT 1381, 0, '', 0, 'wormholeTargetSystemClass', 0 FROM DUAL
UNION ALL SELECT 1382, 0, '', 0, 'wormholeMaxStableTime', 3 FROM DUAL
UNION ALL SELECT 1383, 0, '', 0, 'wormholeMaxStableMass', 2 FROM DUAL
UNION ALL SELECT 1384, 0, '', 0, 'wormholeMassRegeneration', 2 FROM DUAL
UNION ALL SELECT 1385, 0, '', 0, 'wormholeMaxJumpMass', 2 FROM DUAL
UNION ALL SELECT 1386, 0, '', 0, 'wormholeTargetRegion1', 0 FROM DUAL
UNION ALL SELECT 1387, 0, '', 0, 'wormholeTargetRegion2', 0 FROM DUAL
UNION ALL SELECT 1388, 0, '', 0, 'wormholeTargetRegion3', 0 FROM DUAL
UNION ALL SELECT 1389, 0, '', 0, 'wormholeTargetRegion4', 0 FROM DUAL
UNION ALL SELECT 1390, 0, '', 0, 'wormholeTargetRegion5', 0 FROM DUAL
UNION ALL SELECT 1391, 0, '', 0, 'wormholeTargetRegion6', 0 FROM DUAL
UNION ALL SELECT 1392, 0, '', 0, 'wormholeTargetRegion7', 0 FROM DUAL
UNION ALL SELECT 1393, 0, '', 0, 'wormholeTargetRegion8', 0 FROM DUAL
UNION ALL SELECT 1394, 0, '', 0, 'wormholeTargetRegion9', 0 FROM DUAL
UNION ALL SELECT 1395, 0, '', 0, 'wormholeTargetConstellation1', 0 FROM DUAL
UNION ALL SELECT 1396, 0, '', 0, 'wormholeTargetConstellation2', 0 FROM DUAL
UNION ALL SELECT 1397, 0, '', 0, 'wormholeTargetConstellation3', 0 FROM DUAL
UNION ALL SELECT 1398, 0, '', 0, 'wormholeTargetConstellation4', 0 FROM DUAL
UNION ALL SELECT 1399, 0, '', 0, 'wormholeTargetConstellation5', 0 FROM DUAL
UNION ALL SELECT 1400, 0, '', 0, 'wormholeTargetConstellation6', 0 FROM DUAL
UNION ALL SELECT 1401, 0, '', 0, 'wormholeTargetConstellation7', 0 FROM DUAL
UNION ALL SELECT 1402, 0, '', 0, 'wormholeTargetConstellation8', 0 FROM DUAL
UNION ALL SELECT 1403, 0, '', 0, 'wormholeTargetConstellation9', 0 FROM DUAL
UNION ALL SELECT 1404, 0, '', 0, 'wormholeTargetSystem1', 0 FROM DUAL
UNION ALL SELECT 1405, 0, '', 0, 'wormholeTargetSystem2', 0 FROM DUAL
UNION ALL SELECT 1406, 0, '', 0, 'wormholeTargetSystem3', 0 FROM DUAL
UNION ALL SELECT 1407, 0, '', 0, 'wormholeTargetSystem4', 0 FROM DUAL
UNION ALL SELECT 1408, 0, '', 0, 'wormholeTargetSystem5', 0 FROM DUAL
UNION ALL SELECT 1409, 0, '', 0, 'wormholeTargetSystem6', 0 FROM DUAL
UNION ALL SELECT 1410, 0, '', 0, 'wormholeTargetSystem7', 0 FROM DUAL
UNION ALL SELECT 1411, 0, '', 0, 'wormholeTargetSystem8', 0 FROM DUAL
UNION ALL SELECT 1412, 0, '', 0, 'wormholeTargetSystem9', 0 FROM DUAL
UNION ALL SELECT 1413, 0, '', 0, 'probeCanScanShips', 0 FROM DUAL
UNION ALL SELECT 1414, 0, '', 0, 'AI_ShouldUseEvasiveManeuver', 0 FROM DUAL
UNION ALL SELECT 1416, 60000, '', 0, 'AI_TargetSwitchTimer', 101 FROM DUAL
UNION ALL SELECT 1417, 0, '', 0, 'color', 0 FROM DUAL
UNION ALL SELECT 1418, 0, 'Armor Em Damage Resistance', 1396, 'passiveArmorEmDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1419, 0, 'Armor Thermal Damage Resistance', 1394, 'passiveArmorThermalDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1420, 0, 'Armor Kinetic Damage Resistance', 1393, 'passiveArmorKineticDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1421, 0, 'Armor Explosive Damage Resistance', 1395, 'passiveArmorExplosiveDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1422, 0, 'Shield Explosive Damage Resistance', 1395, 'passiveShieldExplosiveDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1423, 0, 'Shield Em Damage Resistance', 1396, 'passiveShieldEmDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1424, 0, 'Shield Kinetic Damage Resistance', 1393, 'passiveShieldKineticDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1425, 0, 'Shield Thermal Damage Resistance', 1394, 'passiveShieldThermalDamageResonance', 108 FROM DUAL
UNION ALL SELECT 1426, 0, 'Structure EM Damage Resistance', 0, 'passiveHullEmDamageResonance', 127 FROM DUAL
UNION ALL SELECT 1427, 0, 'Structure Explosive Damage Resistance', 0, 'passiveHullExplosiveDamageResonance', 127 FROM DUAL
UNION ALL SELECT 1428, 0, 'Structure Kinetic Damage Resistance', 0, 'passiveHullKineticDamageResonance', 127 FROM DUAL
UNION ALL SELECT 1429, 0, 'Structure Thermal Damage Resistance', 0, 'passiveHullThermalDamageResonance', 127 FROM DUAL
UNION ALL SELECT 1430, 0, '', 0, 'lightColor', 0 FROM DUAL
UNION ALL SELECT 1431, 0, '', 0, 'subsystemBonusAmarrCore', 0 FROM DUAL
UNION ALL SELECT 1432, 0, '', 0, 'subsystemBonusAmarrElectronic', 0 FROM DUAL
UNION ALL SELECT 1433, 0, '', 0, 'subsystemBonusAmarrDefensive', 0 FROM DUAL
UNION ALL SELECT 1434, 0, '', 0, 'subsystemBonusAmarrOffensive', 0 FROM DUAL
UNION ALL SELECT 1435, 0, '', 0, 'subsystemBonusAmarrPropulsion', 0 FROM DUAL
UNION ALL SELECT 1436, 0, '', 0, 'subsystemBonusGallenteCore', 0 FROM DUAL
UNION ALL SELECT 1437, 0, '', 0, 'subsystemBonusGallenteElectronic', 0 FROM DUAL
UNION ALL SELECT 1438, 0, '', 0, 'subsystemBonusGallenteDefensive', 0 FROM DUAL
UNION ALL SELECT 1439, 0, '', 0, 'subsystemBonusGallenteOffensive', 0 FROM DUAL
UNION ALL SELECT 1440, 0, '', 0, 'subsystemBonusGallentePropulsion', 0 FROM DUAL
UNION ALL SELECT 1441, 0, '', 0, 'subsystemBonusCaldariCore', 0 FROM DUAL
UNION ALL SELECT 1442, 0, '', 0, 'subsystemBonusCaldariElectronic', 0 FROM DUAL
UNION ALL SELECT 1443, 0, '', 0, 'subsystemBonusCaldariDefensive', 0 FROM DUAL
UNION ALL SELECT 1444, 0, '', 0, 'subsystemBonusCaldariOffensive', 0 FROM DUAL
UNION ALL SELECT 1445, 0, '', 0, 'subsystemBonusCaldariPropulsion', 0 FROM DUAL
UNION ALL SELECT 1446, 0, '', 0, 'subsystemBonusMinmatarCore', 0 FROM DUAL
UNION ALL SELECT 1447, 0, '', 0, 'subsystemBonusMinmatarElectronic', 0 FROM DUAL
UNION ALL SELECT 1448, 0, '', 0, 'subsystemBonusMinmatarDefensive', 0 FROM DUAL
UNION ALL SELECT 1449, 0, '', 0, 'subsystemBonusMinmatarOffensive', 0 FROM DUAL
UNION ALL SELECT 1450, 0, '', 0, 'subsystemBonusMinmatarPropulsion', 0 FROM DUAL
UNION ALL SELECT 1451, 0, '', 0, 'npcAssistancePriority', 0 FROM DUAL
UNION ALL SELECT 1453, 1, '', 0, 'npcRemoteArmorRepairChance', 105 FROM DUAL
UNION ALL SELECT 1454, 10000, '', 0, 'npcRemoteArmorRepairDuration', 101 FROM DUAL
UNION ALL SELECT 1455, 0, '', 0, 'npcRemoteArmorRepairAmount', 0 FROM DUAL
UNION ALL SELECT 1456, 0.25, '', 0, 'npcRemoteArmorRepairThreshold', 105 FROM DUAL
UNION ALL SELECT 1457, 0, '', 0, 'wormholeTargetDistribution', 0 FROM DUAL
UNION ALL SELECT 1458, 20000, '', 0, 'npcRemoteShieldBoostDuration', 101 FROM DUAL
UNION ALL SELECT 1459, 1, '', 0, 'npcRemoteShieldBoostChance', 109 FROM DUAL
UNION ALL SELECT 1460, 50, '', 0, 'npcRemoteShieldBoostAmount', 120 FROM DUAL
UNION ALL SELECT 1462, 0.75, '', 0, 'npcRemoteShieldBoostThreshold', 109 FROM DUAL
UNION ALL SELECT 1464, 5000, '', 0, 'npcAssistanceRange', 1 FROM DUAL
UNION ALL SELECT 1465, 0, 'Armor EM resistance bonus', 0, 'armorEmDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 1466, 0, 'Armor kinetic resistance bonus', 0, 'armorKineticDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 1467, 0, 'Armor thermal resistance bonus', 0, 'armorThermalDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 1468, 0, 'Armor explosive resistance bonus', 0, 'armorExplosiveDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 1469, 0, 'Missile velocity multiplier', 0, 'missileVelocityMultiplier', 104 FROM DUAL
UNION ALL SELECT 1470, 0, 'Maximum velocity multiplier', 0, 'maxVelocityMultiplier', 104 FROM DUAL
UNION ALL SELECT 1471, 0, 'Mass multiplier', 0, 'siegeMassMultiplier', 104 FROM DUAL
UNION ALL SELECT 1472, 0, 'Control range multiplier', 0, 'droneRangeMultiplier', 104 FROM DUAL
UNION ALL SELECT 1473, 0, 'Gravimetric strength multiplier', 0, 'scanGravimetricStrengthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1474, 0, 'Ladar strength multiplier', 0, 'scanLadarStrengthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1475, 0, 'Magnetometric strength multiplier', 0, 'scanMagnetometricStrengthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1476, 0, 'Radar strength multiplier', 0, 'scanRadarStrengthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1477, 0, 'Target painter multiplier', 0, 'signatureRadiusBonusMultiplier', 104 FROM DUAL
UNION ALL SELECT 1478, 0, 'Dampening range reduction multiplier', 0, 'maxTargetRangeBonusMultiplier', 104 FROM DUAL
UNION ALL SELECT 1479, 0, 'Scan resolution reduction multiplier', 0, 'scanResolutionBonusMultiplier', 104 FROM DUAL
UNION ALL SELECT 1480, 0, 'Tracking penalty multiplier', 0, 'trackingSpeedBonusMultiplier', 104 FROM DUAL
UNION ALL SELECT 1481, 0, 'Optimal range penalty multiplier', 0, 'maxRangeBonusMultiplier', 104 FROM DUAL
UNION ALL SELECT 1482, 0, 'Damage multiplier multiplier', 0, 'damageMultiplierMultiplier', 104 FROM DUAL
UNION ALL SELECT 1483, 0, 'Explosion Velocity Multiplier', 0, 'aoeVelocityMultiplier', 104 FROM DUAL
UNION ALL SELECT 1484, 0, 'Drone velocity multiplier', 0, 'maxDroneVelocityMultiplier', 104 FROM DUAL
UNION ALL SELECT 1485, 0, 'Heat damage multiplier', 0, 'heatDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 1486, 0, 'Overload bonus multiplier', 0, 'overloadBonusMultiplier', 104 FROM DUAL
UNION ALL SELECT 1487, 0, 'Smart bomb range multiplier', 0, 'empFieldRangeMultiplier', 104 FROM DUAL
UNION ALL SELECT 1488, 0, 'Smart bomb damage multiplier', 0, 'smartbombDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 1489, 0, 'Shield EM Resistance', 0, 'shieldEmDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 1490, 0, 'Shield Explosive Resistance', 0, 'shieldExplosiveDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 1491, 0, 'Shield Kinetic Resistance', 0, 'shieldKineticDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 1492, 0, 'Shield Thermal Resistance', 0, 'shieldThermalDamageResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 1493, 0, 'Small weapon damage multiplier', 0, 'smallWeaponDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 1494, 0, 'Medium weapon damage multiplier', 0, 'mediumWeaponDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 1495, 0, 'Repair amount multiplier', 0, 'armorDamageAmountMultiplier', 104 FROM DUAL
UNION ALL SELECT 1496, 0, 'Shield repair multiplier', 0, 'shieldBonusMultiplier', 104 FROM DUAL
UNION ALL SELECT 1497, 0, 'Shield transfer amount multiplier', 0, 'shieldBonusMultiplierRemote', 104 FROM DUAL
UNION ALL SELECT 1498, 0, 'Remote repair amount multiplier', 0, 'armorDamageAmountMultiplierRemote', 104 FROM DUAL
UNION ALL SELECT 1499, 0, 'Capacitor capacity multiplier', 0, 'capacitorCapacityMultiplierSystem', 104 FROM DUAL
UNION ALL SELECT 1500, 0, 'Capacitor recharge multiplier', 0, 'rechargeRateMultiplier', 104 FROM DUAL
UNION ALL SELECT 1501, 1, '', 0, 'npcRemoteArmorRepairMaxTargets', 0 FROM DUAL
UNION ALL SELECT 1502, 1, '', 0, 'npcRemoteShieldBoostMaxTargets', 0 FROM DUAL
UNION ALL SELECT 1503, 0, '', 0, 'shipBonusStrategicCruiserAmarr1', 0 FROM DUAL
UNION ALL SELECT 1504, 0, '', 0, 'shipBonusStrategicCruiserCaldari1', 0 FROM DUAL
UNION ALL SELECT 1505, 0, '', 0, 'shipBonusStrategicCruiserGallente1', 0 FROM DUAL
UNION ALL SELECT 1506, 0, '', 0, 'shipBonusStrategicCruiserMinmatar1', 0 FROM DUAL
UNION ALL SELECT 1507, 0, '', 0, 'subsystemBonusAmarrDefensive2', 0 FROM DUAL
UNION ALL SELECT 1508, 0, '', 0, 'subsystemBonusAmarrElectronic2', 0 FROM DUAL
UNION ALL SELECT 1509, 0, '', 0, 'subsystemBonusAmarrCore2', 0 FROM DUAL
UNION ALL SELECT 1510, 0, '', 0, 'subsystemBonusCaldariOffensive2', 0 FROM DUAL
UNION ALL SELECT 1511, 0, '', 0, 'subsystemBonusAmarrOffensive2', 0 FROM DUAL
UNION ALL SELECT 1512, 0, '', 0, 'subsystemBonusAmarrPropulsion2', 0 FROM DUAL
UNION ALL SELECT 1513, 0, '', 0, 'subsystemBonusCaldariPropulsion2', 0 FROM DUAL
UNION ALL SELECT 1514, 0, '', 0, 'subsystemBonusCaldariElectronic2', 0 FROM DUAL
UNION ALL SELECT 1515, 0, '', 0, 'subsystemBonusCaldariCore2', 0 FROM DUAL
UNION ALL SELECT 1516, 0, '', 0, 'subsystemBonusCaldariDefensive2', 0 FROM DUAL
UNION ALL SELECT 1517, 0, '', 0, 'subsystemBonusGallenteDefensive2', 0 FROM DUAL
UNION ALL SELECT 1518, 0, '', 0, 'subsystemBonusGallenteElectronic2', 0 FROM DUAL
UNION ALL SELECT 1519, 0, '', 0, 'subsystemBonusGallenteCore2', 0 FROM DUAL
UNION ALL SELECT 1520, 0, '', 0, 'subsystemBonusGallentePropulsion2', 0 FROM DUAL
UNION ALL SELECT 1521, 0, '', 0, 'subsystemBonusGallenteOffensive2', 0 FROM DUAL
UNION ALL SELECT 1522, 0, '', 0, 'subsystemBonusMinmatarOffensive2', 0 FROM DUAL
UNION ALL SELECT 1523, 0, '', 0, 'subsystemBonusMinmatarPropulsion2', 0 FROM DUAL
UNION ALL SELECT 1524, 0, '', 0, 'subsystemBonusMinmatarElectronic2', 0 FROM DUAL
UNION ALL SELECT 1525, 0, '', 0, 'subsystemBonusMinmatarCore2', 0 FROM DUAL
UNION ALL SELECT 1526, 0, '', 0, 'subsystemBonusMinmatarDefensive2', 0 FROM DUAL
UNION ALL SELECT 1527, 1, '', 0, 'armorMaxDamageResonance', 0 FROM DUAL
UNION ALL SELECT 1528, 1, '', 0, 'shieldMaxDamageResonance', 0 FROM DUAL
UNION ALL SELECT 1529, 1, '', 0, 'hullMaxDamageResonance', 0 FROM DUAL
UNION ALL SELECT 1530, 1, '', 0, 'hullMaxDamageResonanceOld', 0 FROM DUAL
UNION ALL SELECT 1531, 0, '', 0, 'subsystemBonusAmarrOffensive3', 0 FROM DUAL
UNION ALL SELECT 1532, 0, '', 0, 'subsystemBonusGallenteOffensive3', 0 FROM DUAL
UNION ALL SELECT 1533, 0, '', 0, 'subsystemBonusCaldariOffensive3', 0 FROM DUAL
UNION ALL SELECT 1534, 0, '', 0, 'subsystemBonusMinmatarOffensive3', 0 FROM DUAL
UNION ALL SELECT 1535, 0, '', 0, 'shipBonusCC3', 0 FROM DUAL
UNION ALL SELECT 1536, 0, 'ECM Range Bonus', 0, 'ecmRangeBonus', 105 FROM DUAL
UNION ALL SELECT 1537, 0, '', 0, 'eliteBonusReconShip3', 0 FROM DUAL
UNION ALL SELECT 1538, 0, '', 0, 'warpBubbleImmune', 0 FROM DUAL
UNION ALL SELECT 1539, 0, '', 0, 'warpBubbleImmuneModifier', 0 FROM DUAL
UNION ALL SELECT 1540, 0, '', 0, 'stealthBomberLauncherPower2', 0 FROM DUAL
UNION ALL SELECT 1541, 0, '', 0, 'jumpHarmonicsModifier', 0 FROM DUAL
UNION ALL SELECT 1544, 0, 'Max Modules Of This Group Allowed', 0, 'maxGroupFitted', 0 FROM DUAL
UNION ALL SELECT 1547, 0, 'Rig Size', 0, 'rigSize', 117 FROM DUAL
UNION ALL SELECT 1549, 0, 'Fuel Bay Capacity', 71, 'specialFuelBayCapacity', 9 FROM DUAL
UNION ALL SELECT 1550, 1, '', 0, 'implantSetImperialNavy', 0 FROM DUAL
UNION ALL SELECT 1551, 0, 'Days added to subscription', 0, 'numDays', 0 FROM DUAL
UNION ALL SELECT 1552, 1, '', 0, 'implantSetCaldariNavy', 0 FROM DUAL
UNION ALL SELECT 1553, 1, '', 0, 'implantSetFederationNavy', 0 FROM DUAL
UNION ALL SELECT 1554, 1, '', 0, 'implantSetRepublicFleet', 0 FROM DUAL
UNION ALL SELECT 1555, 0, '', 0, 'fwLpKill', 0 FROM DUAL
UNION ALL SELECT 1556, 0, 'Mining Hold Capacity', 71, 'generalMiningHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1557, 0, 'Gas Hold Capacity', 71, 'specialGasHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1558, 0, 'Mineral Hold Capacity', 71, 'specialMineralHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1559, 0, 'Salvage Hold Capacity', 71, 'specialSalvageHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1560, 0, 'Ship Hold Capacity', 71, 'specialShipHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1561, 0, 'Small Ship Hold Capacity', 71, 'specialSmallShipHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1562, 0, 'Medium Ship Hold Capacity', 71, 'specialMediumShipHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1563, 0, 'Large Ship Hold Capacity', 71, 'specialLargeShipHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1564, 0, 'Industrial Ship Hold Capacity', 71, 'specialIndustrialShipHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1565, 0, 'RADAR strength bonus', 0, 'scanRadarStrengthModifier', 139 FROM DUAL
UNION ALL SELECT 1566, 0, 'Ladar strength bonus', 0, 'scanLadarStrengthModifier', 139 FROM DUAL
UNION ALL SELECT 1567, 0, 'Gravimetric strength bonus', 0, 'scanGravimetricStrengthModifier', 139 FROM DUAL
UNION ALL SELECT 1568, 0, 'Magnetometric strength bonus', 0, 'scanMagnetometricStrengthModifier', 139 FROM DUAL
UNION ALL SELECT 1569, 1, '', 0, 'implantSetLGImperialNavy', 0 FROM DUAL
UNION ALL SELECT 1570, 1, '', 0, 'implantSetLGFederationNavy', 0 FROM DUAL
UNION ALL SELECT 1571, 1, '', 0, 'implantSetLGCaldariNavy', 0 FROM DUAL
UNION ALL SELECT 1572, 1, '', 0, 'implantSetLGRepublicFleet', 0 FROM DUAL
UNION ALL SELECT 1573, 0, 'Ammo Hold Capacity', 71, 'specialAmmoHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1574, 0, 'Special Ability Bonus', 0, 'shipBonusATC1', 105 FROM DUAL
UNION ALL SELECT 1575, 0, 'Special Ability Bonus', 0, 'shipBonusATC2', 105 FROM DUAL
UNION ALL SELECT 1576, 0, 'Special Ability Bonus', 0, 'shipBonusATF1', 105 FROM DUAL
UNION ALL SELECT 1577, 0, 'Special Ability Bonus', 0, 'shipBonusATF2', 105 FROM DUAL
UNION ALL SELECT 1578, 0, '', 0, 'eliteBonusCovertOps3', 0 FROM DUAL
UNION ALL SELECT 1579, 0, '', 0, 'effectDeactivationDelay', 0 FROM DUAL
UNION ALL SELECT 1580, -1, 'Maximum Defense Bunkers', 0, 'maxDefenseBunkers', 0 FROM DUAL
UNION ALL SELECT 1581, 0, '', 0, 'eliteBonusAssaultShips1', 0 FROM DUAL
UNION ALL SELECT 1582, 30000, '', 0, 'specialTutorialLootRespawnTime', 101 FROM DUAL
UNION ALL SELECT 1583, 0, 'Required Development Index Level (Military)', 0, 'devIndexMilitary', 140 FROM DUAL
UNION ALL SELECT 1584, 0, 'Required Development Index Level (Industrial)', 0, 'devIndexIndustrial', 140 FROM DUAL
UNION ALL SELECT 1585, 0, 'Required Development Index (economic)', 0, 'devIndexEconomic', 0 FROM DUAL
UNION ALL SELECT 1586, 0, 'Required Development Index (Research ' || CHR(38) || ' Development)', 0, 'devIndexResearchDevelopment', 0 FROM DUAL
UNION ALL SELECT 1590, -1, '', 0, 'anchorDistanceMin', 1 FROM DUAL
UNION ALL SELECT 1591, 250000, '', 0, 'anchorDistanceMax', 1 FROM DUAL
UNION ALL SELECT 1595, 0, 'Requires Infrastructure Upgrade', 0, 'requiresSovUpgrade1', 116 FROM DUAL
UNION ALL SELECT 1597, 0, 'Minimum Sovereignty Period to Install Upgrade', 0, 'sovUpgradeSovereigntyHeldFor', 129 FROM DUAL
UNION ALL SELECT 1598, 0, '', 0, 'sovUpgradeBlockingUpgradeID', 116 FROM DUAL
UNION ALL SELECT 1599, 0, 'Prerequisite Installed Upgrade', 0, 'sovUpgradeRequiredUpgradeID', 116 FROM DUAL
UNION ALL SELECT 1600, 0, 'Required System Oupost Upgrade Level', 0, 'sovUpgradeRequiredOutpostUpgradeLevel', 0 FROM DUAL
UNION ALL SELECT 1601, 0, 'Onlining Requires Infrastructure Upgrade', 0, 'onliningRequiresSovUpgrade1', 116 FROM DUAL
UNION ALL SELECT 1603, 0, 'Daily Upkeep Cost', 0, 'sovBillSystemCost', 133 FROM DUAL
UNION ALL SELECT 1606, 0, '', 0, 'distributionID_blood', 0 FROM DUAL
UNION ALL SELECT 1607, 0, '', 0, 'distributionID_angel', 0 FROM DUAL
UNION ALL SELECT 1608, 0, '', 0, 'distributionID_guristas', 0 FROM DUAL
UNION ALL SELECT 1609, 0, '', 0, 'distributionID_serpentis', 0 FROM DUAL
UNION ALL SELECT 1610, 0, '', 0, 'distributionID_drones', 0 FROM DUAL
UNION ALL SELECT 1611, 0, '', 0, 'distributionID_sanshas', 0 FROM DUAL
UNION ALL SELECT 1612, 172800, '', 0, 'reinforcementDuration', 3 FROM DUAL
UNION ALL SELECT 1613, 10800, '', 0, 'reinforcementVariance', 3 FROM DUAL
UNION ALL SELECT 1614, 0, '', 0, 'distributionID_mordus', 0 FROM DUAL
UNION ALL SELECT 1615, 0, 'Required Development Index Level (Strategic)', 0, 'devIndexSovereignty', 140 FROM DUAL
UNION ALL SELECT 1616, 0, '', 0, 'distributionID', 0 FROM DUAL
UNION ALL SELECT 1619, 0, 'Drone Stasis Web Bonus', 0, 'webSpeedFactorBonus', 105 FROM DUAL
UNION ALL SELECT 1623, 0, '', 0, 'shipBonus3AF', 0 FROM DUAL
UNION ALL SELECT 1624, 0, '', 0, 'shipBonus3CF', 0 FROM DUAL
UNION ALL SELECT 1625, 0, '', 0, 'shipBonus3GF', 0 FROM DUAL
UNION ALL SELECT 1626, 0, '', 0, 'shipBonus3MF', 0 FROM DUAL
UNION ALL SELECT 1631, 0, 'Logistical Capacity', 0, 'logisticalCapacity', 134 FROM DUAL
UNION ALL SELECT 1632, 0, 'Planet Type Restriction', 0, 'planetRestriction', 116 FROM DUAL
UNION ALL SELECT 1633, 0, 'Power Load (per km)', 0, 'powerLoadPerKm', 107 FROM DUAL
UNION ALL SELECT 1634, 0, 'CPU Usage (per km)', 0, 'cpuLoadPerKm', 106 FROM DUAL
UNION ALL SELECT 1635, 0, 'CPU Load Level Modifier', 0, 'cpuLoadLevelModifier', 0 FROM DUAL
UNION ALL SELECT 1636, 0, 'Power Load Level Modifier', 0, 'powerLoadLevelModifier', 0 FROM DUAL
UNION ALL SELECT 1638, 0, 'Import Tax', 0, 'importTax', 133 FROM DUAL
UNION ALL SELECT 1639, 0, 'Export Tax', 0, 'exportTax', 133 FROM DUAL
UNION ALL SELECT 1640, 1, '', 0, 'importTaxMultiplier', 121 FROM DUAL
UNION ALL SELECT 1641, 1, '', 0, 'exportTaxMultiplier', 121 FROM DUAL
UNION ALL SELECT 1642, 100, 'Extraction Quantity', 0, 'pinExtractionQuantity', 0 FROM DUAL
UNION ALL SELECT 1643, 300, 'Cycle Time', 0, 'pinCycleTime', 3 FROM DUAL
UNION ALL SELECT 1644, 10, '', 0, 'extractorDepletionRange', 1 FROM DUAL
UNION ALL SELECT 1645, 0, '', 0, 'extractorDepletionRate', 0 FROM DUAL
UNION ALL SELECT 1646, 0, 'Command Center Hold Capacity', 71, 'specialCommandCenterHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1647, 0, 'Maximum Pilot Age', 0, 'boosterMaxCharAgeHours', 129 FROM DUAL
UNION ALL SELECT 1648, 0, '', 0, 'AI_ShouldUseTargetSwitching', 0 FROM DUAL
UNION ALL SELECT 1649, 0, '', 0, 'AI_ShouldUseSecondaryTarget', 0 FROM DUAL
UNION ALL SELECT 1650, 0, '', 0, 'AI_ShouldUseSignatureRadius', 0 FROM DUAL
UNION ALL SELECT 1651, 0, '', 0, 'AI_ChanceToNotTargetSwitch', 127 FROM DUAL
UNION ALL SELECT 1652, 0, '', 0, 'AI_ShouldUseEffectMultiplier', 0 FROM DUAL
UNION ALL SELECT 1653, 0, 'Planetary Commodities Hold Capacity', 71, 'specialPlanetaryCommoditiesHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1654, 0, 'Immune to super weapon attacks', 0, 'AI_ImmuneToSuperWeapon', 0 FROM DUAL
UNION ALL SELECT 1655, -1, 'Preferred Signature Radius', 0, 'AI_PreferredSignatureRadius', 1 FROM DUAL
UNION ALL SELECT 1656, 0.699999988079071, 'Drone Tanking Modifier', 0, 'AI_TankingModifierDrone', 0 FROM DUAL
UNION ALL SELECT 1657, 0.699999988079071, 'Tanking Modifier', 0, 'AI_TankingModifier', 0 FROM DUAL
UNION ALL SELECT 1658, 300000, 'NPC Remote ECM Duration', 0, 'entityRemoteECMDuration', 0 FROM DUAL
UNION ALL SELECT 1659, 10000, 'NPC Remote ECM Minimum Duration', 0, 'entityRemoteECMMinDuration', 0 FROM DUAL
UNION ALL SELECT 1660, 0.8999999761581421, 'NPC Remote ECM Duration Scaling Factor', 0, 'entityRemoteECMDurationScale', 0 FROM DUAL
UNION ALL SELECT 1661, 300000, 'NPC remote ECM base duration', 0, 'entityRemoteECMBaseDuration', 0 FROM DUAL
UNION ALL SELECT 1662, 8, 'NPC remote ECM Extra Player Scale', 0, 'entityRemoteECMExtraPlayerScale', 0 FROM DUAL
UNION ALL SELECT 1663, 40, 'NPC remote ECM intended number of players', 0, 'entityRemoteECMIntendedNumPlayers', 0 FROM DUAL
UNION ALL SELECT 1664, 1, 'NPC remote ECM chance', 0, 'entityRemoteECMChanceOfActivation', 0 FROM DUAL
UNION ALL SELECT 1669, 0, '', 0, 'shipBonusOreIndustrial1', 0 FROM DUAL
UNION ALL SELECT 1670, 0, '', 0, 'shipBonusOreIndustrial2', 0 FROM DUAL
UNION ALL SELECT 1671, 0, 'NPC group shield resistance bonus', 0, 'entityGroupShieldResistanceBonus', 109 FROM DUAL
UNION ALL SELECT 1672, 10000, 'NPC group shield resistance duration', 0, 'entityGroupShieldResistanceDuration', 0 FROM DUAL
UNION ALL SELECT 1673, 1, 'NPC group shield resistance activation chance', 0, 'entityGroupShieldResistanceActivationChance', 0 FROM DUAL
UNION ALL SELECT 1674, 0, '', 0, 'entityGroupSpeedBonus', 109 FROM DUAL
UNION ALL SELECT 1675, 0, 'NPC group propulsion jamming bonus', 0, 'entityGroupPropJamBonus', 109 FROM DUAL
UNION ALL SELECT 1676, 0, 'NPC group armor resistance bonus', 0, 'entityGroupArmorResistanceBonus', 109 FROM DUAL
UNION ALL SELECT 1677, 10000, '', 0, 'entityGroupSpeedDuration', 0 FROM DUAL
UNION ALL SELECT 1678, 1, '', 0, 'entityGroupSpeedActivationChance', 0 FROM DUAL
UNION ALL SELECT 1679, 10000, '', 0, 'entityGroupPropJamDuration', 0 FROM DUAL
UNION ALL SELECT 1680, 1, '', 0, 'entityGroupPropJamActivationChance', 0 FROM DUAL
UNION ALL SELECT 1681, 10000, '', 0, 'entityGroupArmorResistanceDuration', 0 FROM DUAL
UNION ALL SELECT 1682, 1, '', 0, 'entityGroupArmorResistanceActivationChance', 0 FROM DUAL
UNION ALL SELECT 1683, 0.012000000104308128, 'decay factor', 0, 'ecuDecayFactor', 0 FROM DUAL
UNION ALL SELECT 1684, 9.199999809265137, 'Maximum Volume', 0, 'ecuMaxVolume', 0 FROM DUAL
UNION ALL SELECT 1685, 0.5, '', 0, 'ecuOverlapFactor', 0 FROM DUAL
UNION ALL SELECT 1686, 0, 'System effect damage reduction', 0, 'systemEffectDamageReduction', 124 FROM DUAL
UNION ALL SELECT 1687, 0.800000011920929, '', 0, 'ecuNoiseFactor', 0 FROM DUAL
UNION ALL SELECT 1688, 0, '', 0, 'shipBonusRole8', 0 FROM DUAL
UNION ALL SELECT 1689, 0.30000001192092896, '', 0, 'ecuAreaOfInfluence', 0 FROM DUAL
UNION ALL SELECT 1690, 110, 'Extractor head CPU', 0, 'ecuExtractorHeadCPU', 106 FROM DUAL
UNION ALL SELECT 1691, 500, 'Extractor head Power', 0, 'ecuExtractorHeadPower', 107 FROM DUAL
UNION ALL SELECT 1692, 0, '', 0, 'metaGroupID', 0 FROM DUAL
UNION ALL SELECT 1695, 0, '', 0, 'distributionIDAngel01', 0 FROM DUAL
UNION ALL SELECT 1696, 0, '', 0, 'distributionIDAngel02', 0 FROM DUAL
UNION ALL SELECT 1697, 0, '', 0, 'distributionIDAngel03', 0 FROM DUAL
UNION ALL SELECT 1698, 0, '', 0, 'distributionIDAngel04', 0 FROM DUAL
UNION ALL SELECT 1699, 0, '', 0, 'distributionIDAngel05', 0 FROM DUAL
UNION ALL SELECT 1700, 0, '', 0, 'distributionIDAngel06', 0 FROM DUAL
UNION ALL SELECT 1701, 0, '', 0, 'distributionIDAngel07', 0 FROM DUAL
UNION ALL SELECT 1702, 0, '', 0, 'distributionIDAngel08', 0 FROM DUAL
UNION ALL SELECT 1703, 0, '', 0, 'distributionIDAngel09', 0 FROM DUAL
UNION ALL SELECT 1704, 0, '', 0, 'distributionIDAngel10', 0 FROM DUAL
UNION ALL SELECT 1705, 0, '', 0, 'distributionIDBlood01', 0 FROM DUAL
UNION ALL SELECT 1706, 0, '', 0, 'distributionIDBlood02', 0 FROM DUAL
UNION ALL SELECT 1707, 0, '', 0, 'distributionIDBlood03', 0 FROM DUAL
UNION ALL SELECT 1708, 0, '', 0, 'distributionIDBlood04', 0 FROM DUAL
UNION ALL SELECT 1709, 0, '', 0, 'distributionIDBlood05', 0 FROM DUAL
UNION ALL SELECT 1710, 0, '', 0, 'distributionIDBlood06', 0 FROM DUAL
UNION ALL SELECT 1711, 0, '', 0, 'distributionIDBlood07', 0 FROM DUAL
UNION ALL SELECT 1712, 0, '', 0, 'distributionIDBlood08', 0 FROM DUAL
UNION ALL SELECT 1713, 0, '', 0, 'distributionIDBlood09', 0 FROM DUAL
UNION ALL SELECT 1714, 0, '', 0, 'distributionIDBlood10', 0 FROM DUAL
UNION ALL SELECT 1715, 0, '', 0, 'distributionIDGurista01', 0 FROM DUAL
UNION ALL SELECT 1716, 0, '', 0, 'distributionIDGurista02', 0 FROM DUAL
UNION ALL SELECT 1717, 0, '', 0, 'distributionIDGurista03', 0 FROM DUAL
UNION ALL SELECT 1718, 0, '', 0, 'distributionIDGurista04', 0 FROM DUAL
UNION ALL SELECT 1719, 0, '', 0, 'distributionIDGurista05', 0 FROM DUAL
UNION ALL SELECT 1720, 0, '', 0, 'distributionIDGurista06', 0 FROM DUAL
UNION ALL SELECT 1721, 0, '', 0, 'distributionIDGurista07', 0 FROM DUAL
UNION ALL SELECT 1722, 0, '', 0, 'distributionIDGurista08', 0 FROM DUAL
UNION ALL SELECT 1723, 0, '', 0, 'distributionIDGurista09', 0 FROM DUAL
UNION ALL SELECT 1724, 0, '', 0, 'distributionIDGurista10', 0 FROM DUAL
UNION ALL SELECT 1725, 0, '', 0, 'distributionIDRogueDrone01', 0 FROM DUAL
UNION ALL SELECT 1726, 0, '', 0, 'distributionIDRogueDrone02', 0 FROM DUAL
UNION ALL SELECT 1727, 0, '', 0, 'distributionIDRogueDrone03', 0 FROM DUAL
UNION ALL SELECT 1728, 0, '', 0, 'distributionIDRogueDrone04', 0 FROM DUAL
UNION ALL SELECT 1729, 0, '', 0, 'distributionIDRogueDrone05', 0 FROM DUAL
UNION ALL SELECT 1730, 0, '', 0, 'distributionIDRogueDrone06', 0 FROM DUAL
UNION ALL SELECT 1731, 0, '', 0, 'distributionIDRogueDrone07', 0 FROM DUAL
UNION ALL SELECT 1732, 0, '', 0, 'distributionIDRogueDrone08', 0 FROM DUAL
UNION ALL SELECT 1733, 0, '', 0, 'distributionIDRogueDrone09', 0 FROM DUAL
UNION ALL SELECT 1734, 0, '', 0, 'distributionIDRogueDrone10', 0 FROM DUAL
UNION ALL SELECT 1735, 0, '', 0, 'distributionIDSansha01', 0 FROM DUAL
UNION ALL SELECT 1736, 0, '', 0, 'distributionIDSansha02', 0 FROM DUAL
UNION ALL SELECT 1737, 0, '', 0, 'distributionIDSansha03', 0 FROM DUAL
UNION ALL SELECT 1738, 0, '', 0, 'distributionIDSansha04', 0 FROM DUAL
UNION ALL SELECT 1739, 0, '', 0, 'distributionIDSansha05', 0 FROM DUAL
UNION ALL SELECT 1740, 0, '', 0, 'distributionIDSansha06', 0 FROM DUAL
UNION ALL SELECT 1741, 0, '', 0, 'distributionIDSansha07', 0 FROM DUAL
UNION ALL SELECT 1742, 0, '', 0, 'distributionIDSansha08', 0 FROM DUAL
UNION ALL SELECT 1743, 0, '', 0, 'distributionIDSansha09', 0 FROM DUAL
UNION ALL SELECT 1744, 0, '', 0, 'distributionIDSansha10', 0 FROM DUAL
UNION ALL SELECT 1745, 0, '', 0, 'distributionIDSerpentis01', 0 FROM DUAL
UNION ALL SELECT 1746, 0, '', 0, 'distributionIDSerpentis02', 0 FROM DUAL
UNION ALL SELECT 1747, 0, '', 0, 'distributionIDSerpentis03', 0 FROM DUAL
UNION ALL SELECT 1748, 0, '', 0, 'distributionIDSerpentis04', 0 FROM DUAL
UNION ALL SELECT 1749, 0, '', 0, 'distributionIDSerpentis05', 0 FROM DUAL
UNION ALL SELECT 1750, 0, '', 0, 'distributionIDSerpentis06', 0 FROM DUAL
UNION ALL SELECT 1751, 0, '', 0, 'distributionIDSerpentis07', 0 FROM DUAL
UNION ALL SELECT 1752, 0, '', 0, 'distributionIDSerpentis08', 0 FROM DUAL
UNION ALL SELECT 1753, 0, '', 0, 'distributionIDSerpentis09', 0 FROM DUAL
UNION ALL SELECT 1754, 0, '', 0, 'distributionIDSerpentis10', 0 FROM DUAL
UNION ALL SELECT 1755, 0, '', 0, 'distributionID01', 0 FROM DUAL
UNION ALL SELECT 1756, 0, '', 0, 'distributionID02', 0 FROM DUAL
UNION ALL SELECT 1757, 0, '', 0, 'distributionID03', 0 FROM DUAL
UNION ALL SELECT 1758, 0, '', 0, 'distributionID04', 0 FROM DUAL
UNION ALL SELECT 1759, 0, '', 0, 'distributionID05', 0 FROM DUAL
UNION ALL SELECT 1760, 0, '', 0, 'distributionID06', 0 FROM DUAL
UNION ALL SELECT 1761, 0, '', 0, 'distributionID07', 0 FROM DUAL
UNION ALL SELECT 1762, 0, '', 0, 'distributionID08', 0 FROM DUAL
UNION ALL SELECT 1763, 0, '', 0, 'distributionID09', 0 FROM DUAL
UNION ALL SELECT 1764, 0, '', 0, 'distributionID10', 0 FROM DUAL
UNION ALL SELECT 1766, 0, '', 0, 'entityOverviewShipGroupId', 115 FROM DUAL
UNION ALL SELECT 1768, 0, '', 0, 'typeColorScheme', 0 FROM DUAL
UNION ALL SELECT 1770, 0, 'Special material bay capacity', 0, 'specialMaterialBayCapacity', 9 FROM DUAL
UNION ALL SELECT 1771, 0, '', 0, 'constructionType', 0 FROM DUAL
UNION ALL SELECT 1772, 0, 'Access Difficulty Bonus', 0, 'accessDifficultyBonusAbsolutePercent', 127 FROM DUAL
UNION ALL SELECT 1773, 2, 'Gender', 0, 'gender', 142 FROM DUAL
UNION ALL SELECT 1775, 0, 'Consumption Quantity Bonus', 0, 'consumptionQuantityBonusPercent', 105 FROM DUAL
UNION ALL SELECT 1778, 0, 'Manufacture Cost Bonus', 0, 'manufactureCostBonusShowInfo', 105 FROM DUAL
UNION ALL SELECT 1780, 0.10000000149011612, '', 0, 'npcCustomsOfficeTaxRate', 0 FROM DUAL
UNION ALL SELECT 1781, 0.10000000149011612, '', 0, 'defaultCustomsOfficeTaxRate', 0 FROM DUAL
UNION ALL SELECT 1782, 0, 'Allowed Drone Group', 0, 'allowedDroneGroup1', 115 FROM DUAL
UNION ALL SELECT 1783, 0, 'Allowed Drone Group', 0, 'allowedDroneGroup2', 115 FROM DUAL
UNION ALL SELECT 1785, 0, 'Capital Sized Vessel', 0, 'isCapitalSize', 137 FROM DUAL
UNION ALL SELECT 1786, 1, '', 0, 'bcLargeTurretPower', 0 FROM DUAL
UNION ALL SELECT 1787, 1, '', 0, 'bcLargeTurretCPU', 0 FROM DUAL
UNION ALL SELECT 1788, 1, '', 0, 'bcLargeTurretCap', 0 FROM DUAL
UNION ALL SELECT 1790, 1, '', 0, 'bcSiegeMissileCPU', 0 FROM DUAL
UNION ALL SELECT 1791, 1, '', 0, 'bcSiegeMissilePower', 0 FROM DUAL
UNION ALL SELECT 1792, 0, '', 0, 'shipBonusBC3', 0 FROM DUAL
UNION ALL SELECT 1793, 0, '', 0, 'shipBonusBC4', 0 FROM DUAL
UNION ALL SELECT 1794, 0, 'Effect Bonus', 0, 'skillBonusBooster', 105 FROM DUAL
UNION ALL SELECT 1795, 10000, 'Reload Time', 1397, 'reloadTime', 101 FROM DUAL
UNION ALL SELECT 1797, 0, 'Does Not Require Clothing Type', 0, 'clothingAlsoCoversCategory', 0 FROM DUAL
UNION ALL SELECT 1798, 0, 'Disallow against EW-immune target', 0, 'disallowAgainstEwImmuneTarget', 137 FROM DUAL
UNION ALL SELECT 1799, 1, 'Genolution Set Bonus', 0, 'implantSetChristmas', 104 FROM DUAL
UNION ALL SELECT 1803, 0, 'MWD sig penalty and cap need bonus', 0, 'MWDSignatureRadiusBonus', 105 FROM DUAL
UNION ALL SELECT 1804, 0, 'Quafe Hold Capacity', 0, 'specialQuafeHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 1806, 0, 'Requires Sovereignty', 0, 'requiresSovereigntyDisplayOnly', 137 FROM DUAL
UNION ALL SELECT 1808, 1, '', 0, 'nosReflector', 0 FROM DUAL
UNION ALL SELECT 1809, 1, '', 0, 'neutReflector', 0 FROM DUAL
UNION ALL SELECT 1811, 0, 'Capacitor Attack Reflect Chance', 0, 'capAttackReflector', 111 FROM DUAL
UNION ALL SELECT 1812, 0, 'Damage Reduction Threshold', 0, 'turretDamageScalingRadius', 1 FROM DUAL
UNION ALL SELECT 1813, 0, 'Turret Damage Scaling Radius', 0, 'titanBonusScalingRadius', 1 FROM DUAL
UNION ALL SELECT 1814, 1, '', 0, 'nosReflectAmount', 111 FROM DUAL
UNION ALL SELECT 1815, 1, '', 0, 'neutReflectAmount', 111 FROM DUAL
UNION ALL SELECT 1816, 1, 'Neutralizer Reflect Amount', 0, 'neutReflectAmountBonus', 105 FROM DUAL
UNION ALL SELECT 1817, 1, 'Energy Leech Reflect Amount', 0, 'nosReflectAmountBonus', 105 FROM DUAL
UNION ALL SELECT 1818, 0, '', 0, 'aurumConversionRate', 0 FROM DUAL
UNION ALL SELECT 1820, 10000000, '', 0, 'baseDefenderAllyCost', 133 FROM DUAL
UNION ALL SELECT 1821, 0, 'Ally Cost Modifier Percentage Per Level', 0, 'skillAllyCostModifierBonus', 0 FROM DUAL
UNION ALL SELECT 1822, 0, '', 0, 'rookieSETCapBonus', 0 FROM DUAL
UNION ALL SELECT 1823, 0, '', 0, 'rookieSETDamageBonus', 0 FROM DUAL
UNION ALL SELECT 1824, 0, '', 0, 'rookieWeaponDisruptionBonus', 0 FROM DUAL
UNION ALL SELECT 1825, 0, '', 0, 'rookieArmorResistanceBonus', 0 FROM DUAL
UNION ALL SELECT 1826, 0, '', 0, 'rookieSHTOptimalBonus', 0 FROM DUAL
UNION ALL SELECT 1827, 0, '', 0, 'rookieMissileKinDamageBonus', 0 FROM DUAL
UNION ALL SELECT 1828, 0, '', 0, 'rookieECMStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 1829, 0, '', 0, 'rookieShieldResistBonus', 0 FROM DUAL
UNION ALL SELECT 1830, 0, '', 0, 'rookieSHTDamageBonus', 0 FROM DUAL
UNION ALL SELECT 1831, 0, '', 0, 'rookieDroneBonus', 0 FROM DUAL
UNION ALL SELECT 1832, 0, '', 0, 'rookieDampStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 1833, 0, '', 0, 'rookieArmorRepBonus', 0 FROM DUAL
UNION ALL SELECT 1834, 0, '', 0, 'rookieTargetPainterStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 1835, 0, '', 0, 'rookieShipVelocityBonus', 0 FROM DUAL
UNION ALL SELECT 1836, 0, '', 0, 'rookieSPTDamageBonus', 0 FROM DUAL
UNION ALL SELECT 1837, 0, '', 0, 'rookieShieldBoostBonus', 0 FROM DUAL
UNION ALL SELECT 1838, 0, '', 0, 'miniProfessionRangeBonus', 0 FROM DUAL
UNION ALL SELECT 1839, 10000, 'Damage Delay', 1392, 'damageDelayDuration', 101 FROM DUAL
UNION ALL SELECT 1840, 0, '', 0, 'energyTransferAmountBonus', 0 FROM DUAL
UNION ALL SELECT 1842, 0, '', 0, 'miningFrigatesBonusOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 1843, 0, '', 0, 'shipBonusOREfrig2', 0 FROM DUAL
UNION ALL SELECT 1844, 1, 'Orbital Strike Accuracy', 0, 'orbitalStrikeAccuracy', 0 FROM DUAL
UNION ALL SELECT 1845, 1, 'Orbital Strike Damage', 0, 'orbitalStrikeDamage', 0 FROM DUAL
UNION ALL SELECT 1846, 0, '', 0, 'cargoGroup2', 0 FROM DUAL
UNION ALL SELECT 1847, 0, '', 0, '902', 0 FROM DUAL
UNION ALL SELECT 1848, 0, '', 0, '902', 0 FROM DUAL
UNION ALL SELECT 1849, 0, '', 0, 'resistanceShiftAmount', 0 FROM DUAL
UNION ALL SELECT 1851, 0, 'Sensor Strength Bonus', 0, 'sensorStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 1852, 1, 'Can Be Jettisoned', 0, 'canBeJettisoned', 0 FROM DUAL
UNION ALL SELECT 1854, 0, 'Stable Shutdown', 0, 'doesNotEmergencyWarp', 137 FROM DUAL
UNION ALL SELECT 1855, 0, 'Ignore Drones Below This Size', 0, 'AI_IgnoreDronesBelowSignatureRadius', 1 FROM DUAL
UNION ALL SELECT 1856, 0, 'Mass Penalty Reduction', 0, 'massPenaltyReduction', 105 FROM DUAL
UNION ALL SELECT 1857, 0, '', 0, 'rookieSETTracking', 0 FROM DUAL
UNION ALL SELECT 1858, 0, '', 0, 'rookieSETOptimal', 0 FROM DUAL
UNION ALL SELECT 1859, 0, '', 0, 'rookieNosDrain', 0 FROM DUAL
UNION ALL SELECT 1860, 0, '', 0, 'rookieNeutDrain', 0 FROM DUAL
UNION ALL SELECT 1861, 0, '', 0, 'rookieWebAmount', 0 FROM DUAL
UNION ALL SELECT 1862, 0, '', 0, 'rookieLightMissileVelocity', 0 FROM DUAL
UNION ALL SELECT 1863, 0, '', 0, 'rookieRocketVelocity', 0 FROM DUAL
UNION ALL SELECT 1864, 0, '', 0, 'rookieDroneMWDspeed', 0 FROM DUAL
UNION ALL SELECT 1865, 0, '', 0, 'rookieSHTTracking', 0 FROM DUAL
UNION ALL SELECT 1866, 0, '', 0, 'rookieSHTFalloff', 0 FROM DUAL
UNION ALL SELECT 1867, 0, '', 0, 'rookieSPTTracking', 0 FROM DUAL
UNION ALL SELECT 1868, 0, '', 0, 'rookieSPTFalloff', 0 FROM DUAL
UNION ALL SELECT 1869, 0, '', 0, 'rookieSPTOptimal', 0 FROM DUAL
UNION ALL SELECT 1870, 0, '', 0, 'covertCloakCPUAdd', 0 FROM DUAL
UNION ALL SELECT 1871, 0, '', 0, 'covertCloakCPUPenalty', 0 FROM DUAL
UNION ALL SELECT 1872, 0, 'Can be fitted to', 1443, 'canFitShipGroup05', 115 FROM DUAL
UNION ALL SELECT 1879, 0, 'Can be fitted to', 1443, 'canFitShipGroup06', 115 FROM DUAL
UNION ALL SELECT 1880, 0, 'Can be fitted to', 1443, 'canFitShipGroup07', 115 FROM DUAL
UNION ALL SELECT 1881, 0, 'Can be fitted to', 1443, 'canFitShipGroup08', 115 FROM DUAL
UNION ALL SELECT 1882, 0, '', 0, 'warfareLinkCPUAdd', 0 FROM DUAL
UNION ALL SELECT 1883, 0, '', 0, 'warfareLinkCPUPenalty', 0 FROM DUAL
UNION ALL SELECT 1886, 0, 'Boosted repair multiplier', 0, 'chargedArmorDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 1887, 0, '', 0, 'shipBonusAD1', 0 FROM DUAL
UNION ALL SELECT 1888, 0, '', 0, 'shipBonusAD2', 0 FROM DUAL
UNION ALL SELECT 1889, 0, '', 0, 'shipBonusABC2', 0 FROM DUAL
UNION ALL SELECT 1890, 0, 'Non-Destructible', 0, 'nondestructible', 137 FROM DUAL
UNION ALL SELECT 1891, 0, '', 0, 'allowedInCapIndustrialMaintenanceBay', 0 FROM DUAL
UNION ALL SELECT 1892, 0, '', 0, 'entityArmorRepairAmountPerSecond', 113 FROM DUAL
UNION ALL SELECT 1893, 0, '', 0, 'entityShieldBoostAmountPerSecond', 113 FROM DUAL
UNION ALL SELECT 1894, 1, '', 0, 'entityCapacitorLevel', 109 FROM DUAL
UNION ALL SELECT 1895, 1, '', 0, 'entityCapacitorLevelModifierSmall', 109 FROM DUAL
UNION ALL SELECT 1896, 1, '', 0, 'entityCapacitorLevelModifierMedium', 109 FROM DUAL
UNION ALL SELECT 1897, 1, '', 0, 'entityCapacitorLevelModifierLarge', 109 FROM DUAL
UNION ALL SELECT 1904, 0, 'Processing Fee', 2512, 'securityProcessingFee', 133 FROM DUAL
UNION ALL SELECT 1905, 0, 'Maximum Scan Deviation Modifier', 0, 'maxScanDeviationModifierModule', 105 FROM DUAL
UNION ALL SELECT 1906, 0, 'Duration Bonus', 0, 'scanDurationBonus', 105 FROM DUAL
UNION ALL SELECT 1907, 0, 'Scan Strength Bonus', 0, 'scanStrengthBonusModule', 105 FROM DUAL
UNION ALL SELECT 1908, 0, 'Wormhole Signature Strength', 0, 'scanWormholeStrength', 120 FROM DUAL
UNION ALL SELECT 1909, 0, 'Virus Coherence', 0, 'virusCoherence', 0 FROM DUAL
UNION ALL SELECT 1910, 0, 'Virus Strength', 0, 'virusStrength', 0 FROM DUAL
UNION ALL SELECT 1911, 0, 'Virus Utility Element Slots', 0, 'virusElementSlots', 0 FROM DUAL
UNION ALL SELECT 1912, 20, '', 0, 'spewContainerCount', 138 FROM DUAL
UNION ALL SELECT 1913, 0, '', 0, 'defaultJunkLootTypeID', 116 FROM DUAL
UNION ALL SELECT 1914, 65, '', 0, 'spewVelocity', 10 FROM DUAL
UNION ALL SELECT 1915, 0, 'Virus Coherence Bonus', 0, 'virusCoherenceBonus', 0 FROM DUAL
UNION ALL SELECT 1916, 0, 'Follows Jump Clones', 0, 'followsJumpClones', 137 FROM DUAL
UNION ALL SELECT 1917, 0, '', 0, 'spewContainerLifeExtension', 3 FROM DUAL
UNION ALL SELECT 1918, 0, 'Analyzer Virus Strength Bonus', 0, 'virusStrengthBonus', 0 FROM DUAL
UNION ALL SELECT 1919, 0, '', 0, 'tierDifficulty', 0 FROM DUAL
UNION ALL SELECT 1920, 0, 'Disallow activation in forcefield', 0, 'disallowActivateInForcefield', 137 FROM DUAL
UNION ALL SELECT 1921, 24, 'Clone Jump Cool Down', 0, 'cloneJumpCoolDown', 0 FROM DUAL
UNION ALL SELECT 1922, 0, 'Warfare Link Strength Bonus', 0, 'warfareLinkBonus', 0 FROM DUAL
UNION ALL SELECT 1923, 0, 'Reactivation Bonus', 0, 'roleBonusMarauder', 105 FROM DUAL
UNION ALL SELECT 1924, 0, '', 0, 'eliteBonusCommandShips3', 0 FROM DUAL
UNION ALL SELECT 1925, 0, 'Empire Tax Modifier', 0, 'piTaxReductionModifer', 105 FROM DUAL
UNION ALL SELECT 1926, 1, '', 0, 'piTaxReduction', 0 FROM DUAL
UNION ALL SELECT 1927, 0, '', 0, 'hackable', 137 FROM DUAL
UNION ALL SELECT 1928, 0, 'Raw Material Siphon Amount', 0, 'siphonRawMaterial', 138 FROM DUAL
UNION ALL SELECT 1929, 0, 'Processed Material Siphon Amount', 0, 'siphonProMaterial', 138 FROM DUAL
UNION ALL SELECT 1930, 0, 'Amount Destroyed On Steal', 0, 'siphonWasteAmount', 105 FROM DUAL
UNION ALL SELECT 1932, 1, 'Ascendancy Set bonus', 0, 'implantSetWarpSpeed', 104 FROM DUAL
UNION ALL SELECT 1933, 0, 'Polymer Material Siphon Amount', 0, 'siphonPolyMaterial', 105 FROM DUAL
UNION ALL SELECT 1934, 1, '', 0, 'deactivateIfOffensive', 0 FROM DUAL
UNION ALL SELECT 1935, 0, 'Overload Effectiveness Bonus', 1386, 'overloadTrackingModuleStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 1936, 0, 'Overload Effectiveness Bonus', 1386, 'overloadSensorModuleStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 1937, 0, 'Overload Effectiveness Bonus', 1386, 'overloadPainterStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 1938, 0, 'Mining Amount Bonus Bonus', 0, 'miningAmountBonusBonus', 0 FROM DUAL
UNION ALL SELECT 1939, 0, 'Ore Refining Efficiency Bonus', 0, 'stationOreRefiningBonus', 127 FROM DUAL
UNION ALL SELECT 1942, 0, '', 0, 'eliteBonusExpedition1', 0 FROM DUAL
UNION ALL SELECT 1943, 0, '', 0, 'eliteBonusExpedition2', 0 FROM DUAL
UNION ALL SELECT 1944, 0, 'Can be fitted to', 1443, 'canFitShipType5', 116 FROM DUAL
UNION ALL SELECT 1945, 0, '', 0, 'nosOverride', 137 FROM DUAL
UNION ALL SELECT 1946, -1, 'Restricted To Security Level Of At Least', 0, 'anchoringSecurityLevelMin', 0 FROM DUAL
UNION ALL SELECT 1949, 0, 'Overheat Bonus', 0, 'roleBonusOverheatDST', 105 FROM DUAL
UNION ALL SELECT 1950, 0, 'Warp Speed Increase', 0, 'warpSpeedAdd', 0 FROM DUAL
UNION ALL SELECT 1951, 0, '', 0, 'industryStructureCostBonusSet', 0 FROM DUAL
UNION ALL SELECT 1952, 0, 'Build Cost Multiplier', 0, 'industryStructureCostBonus', 104 FROM DUAL
UNION ALL SELECT 1954, 1, '', 0, 'industryJobCostMultiplier', 104 FROM DUAL
UNION ALL SELECT 1955, 0, '', 0, 'industryBlueprintRank', 0 FROM DUAL
UNION ALL SELECT 1956, 0, 'Does Not Require Clothing Type', 0, 'clothingRemovesCategory', 0 FROM DUAL
UNION ALL SELECT 1957, 0, 'Requires Other Clothing Types', 0, 'clothingRuleException', 0 FROM DUAL
UNION ALL SELECT 1958, 0, '', 0, 'dscanImmune', 137 FROM DUAL
UNION ALL SELECT 1959, 1, 'Invention/reverse engineering speed', 0, 'inventionReverseEngineeringResearchSpeed', 0 FROM DUAL
UNION ALL SELECT 1961, 0, 'Industry Job Length Bonus', 0, 'advancedIndustrySkillIndustryJobTimeBonus', 105 FROM DUAL
UNION ALL SELECT 1966, 0, 'Energy warfare modifier', 0, 'energyWarfareStrengthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1967, 0, 'Explosion radius multiplier', 0, 'aoeCloudSizeMultiplier', 104 FROM DUAL
UNION ALL SELECT 1968, 0, 'Target painter effectiveness multiplier', 0, 'targetPainterStrengthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1969, 0, 'Stasis Webifier strength multiplier', 0, 'stasisWebStrengthMultiplier', 104 FROM DUAL
UNION ALL SELECT 1970, 0, 'Banned in High Sec Space', 0, 'disallowInHighSec', 137 FROM DUAL
UNION ALL SELECT 1971, 1, 'Jump Fatigue Multiplier', 0, 'jumpFatigueMultiplier', 104 FROM DUAL
UNION ALL SELECT 1972, 1, '', 0, 'jumpThroughFatigueMultiplier', 104 FROM DUAL
UNION ALL SELECT 1973, -1000, 'Gate Scramble Status', 0, 'gateScrambleStatus', 0 FROM DUAL
UNION ALL SELECT 1974, 1, 'Gate Scramble Strength', 0, 'gateScrambleStrength', 0 FROM DUAL
UNION ALL SELECT 1975, 1, '', 0, 'published', 0 FROM DUAL
UNION ALL SELECT 1978, 0, 'Global Resistance Reduction', 0, 'resistanceKiller', 105 FROM DUAL
UNION ALL SELECT 1979, 0, '', 0, 'resistanceKillerHull', 0 FROM DUAL
UNION ALL SELECT 1980, 1, 'Asteroid radius size multiplier', 0, 'asteroidRadiusGrowthFactor', 0 FROM DUAL
UNION ALL SELECT 1981, 90, 'Asteroid unit radius', 0, 'asteroidRadiusUnitSize', 1 FROM DUAL
UNION ALL SELECT 1982, 0, 'Manufacturing Time Bonus', 1392, 'manufactureTimePerLevel', 105 FROM DUAL
UNION ALL SELECT 1983, 0, '', 0, 'freighterBonusO1', 0 FROM DUAL
UNION ALL SELECT 1984, 0, '', 0, 'freighterBonusO2', 0 FROM DUAL
UNION ALL SELECT 1985, 0, 'Cooldown between mode switching', 0, 'stanceSwitchTime', 101 FROM DUAL
UNION ALL SELECT 1986, 0, '', 0, 'shipBonusTacticalDestroyerAmarr1', 0 FROM DUAL
UNION ALL SELECT 1987, 0, '', 0, 'shipBonusTacticalDestroyerAmarr2', 0 FROM DUAL
UNION ALL SELECT 1988, 0, '', 0, 'shipBonusTacticalDestroyerAmarr3', 0 FROM DUAL
UNION ALL SELECT 1989, 0, '', 0, 'roleBonusT3ProbeCPU', 0 FROM DUAL
UNION ALL SELECT 1990, 1, '', 0, 'modeMaxRangePostDiv', 0 FROM DUAL
UNION ALL SELECT 1991, 1, '', 0, 'modeMaxTargetRangePostDiv', 0 FROM DUAL
UNION ALL SELECT 1992, 1, '', 0, 'modeRadarStrengthPostDiv', 0 FROM DUAL
UNION ALL SELECT 1993, 1, '', 0, 'modeScanResPostDiv', 0 FROM DUAL
UNION ALL SELECT 1994, 1, '', 0, 'modeLadarStrengthPostDiv', 0 FROM DUAL
UNION ALL SELECT 1995, 1, '', 0, 'modeGravimetricStrengthPostDiv', 0 FROM DUAL
UNION ALL SELECT 1996, 1, '', 0, 'modeMagnetometricStrengthPostDiv', 0 FROM DUAL
UNION ALL SELECT 1997, 1, '', 0, 'modeEmResistancePostDiv', 0 FROM DUAL
UNION ALL SELECT 1998, 1, '', 0, 'modeExplosiveResistancePostDiv', 0 FROM DUAL
UNION ALL SELECT 1999, 1, '', 0, 'modeThermicResistancePostDiv', 0 FROM DUAL
UNION ALL SELECT 2000, 1, '', 0, 'modeKineticResistancePostDiv', 0 FROM DUAL
UNION ALL SELECT 2001, 1, '', 0, 'modeSignatureRadiusPostDiv', 0 FROM DUAL
UNION ALL SELECT 2002, 1, '', 0, 'modeAgilityPostDiv', 0 FROM DUAL
UNION ALL SELECT 2003, 1, '', 0, 'modeVelocityPostDiv', 0 FROM DUAL
UNION ALL SELECT 2004, 0, '', 0, 'shipBonusTacticalDestroyerMinmatar1', 0 FROM DUAL
UNION ALL SELECT 2005, 0, '', 0, 'shipBonusTacticalDestroyerMinmatar2', 0 FROM DUAL
UNION ALL SELECT 2006, 0, '', 0, 'shipBonusTacticalDestroyerMinmatar3', 0 FROM DUAL
UNION ALL SELECT 2007, 0, '', 0, 'modeMWDSigPenaltyPostDiv', 0 FROM DUAL
UNION ALL SELECT 2008, 0, '', 0, 'modeTrackingPostDiv', 0 FROM DUAL
UNION ALL SELECT 2009, 0, '', 0, 'entitySuperWeaponDuration', 0 FROM DUAL
UNION ALL SELECT 2010, 0, '', 0, 'entitySuperWeaponEmDamage', 0 FROM DUAL
UNION ALL SELECT 2011, 0, '', 0, 'entitySuperWeaponKineticDamage', 0 FROM DUAL
UNION ALL SELECT 2012, 0, '', 0, 'entitySuperWeaponThermalDamage', 0 FROM DUAL
UNION ALL SELECT 2013, 0, '', 0, 'entitySuperWeaponExplosiveDamage', 0 FROM DUAL
UNION ALL SELECT 2014, 0, '', 0, 'shipBonusGC3', 0 FROM DUAL
UNION ALL SELECT 2015, 0, '', 0, 'shipBonusTacticalDestroyerCaldari1', 0 FROM DUAL
UNION ALL SELECT 2016, 0, '', 0, 'shipBonusTacticalDestroyerCaldari2', 0 FROM DUAL
UNION ALL SELECT 2017, 0, '', 0, 'shipBonusTacticalDestroyerCaldari3', 0 FROM DUAL
UNION ALL SELECT 2018, 0, '', 0, '2015', 0 FROM DUAL
UNION ALL SELECT 2019, 0, '', 0, 'allowRefills', 0 FROM DUAL
UNION ALL SELECT 2020, 0, '', 0, 'shipBonusAT', 0 FROM DUAL
UNION ALL SELECT 2021, 1, '', 0, 'entosisDurationMultiplier', 0 FROM DUAL
UNION ALL SELECT 2023, 1399, 'Modification of Explosion Radius Bonus', 0, 'aoeCloudSizeBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2024, 1399, 'Modification of Explosion Velocity Bonus', 0, 'aoeVelocityBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2025, 1399, 'Modification of Missile Velocity Bonus', 0, 'missileVelocityBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2026, 1399, 'Modification of Flight Time Bonus', 0, 'explosionDelayBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2027, 0, '', 0, 'shipBonusTacticalDestroyerGallente1', 0 FROM DUAL
UNION ALL SELECT 2028, 0, '', 0, 'shipBonusTacticalDestroyerGallente2', 0 FROM DUAL
UNION ALL SELECT 2029, 0, '', 0, 'shipBonusTacticalDestroyerGallente3', 0 FROM DUAL
UNION ALL SELECT 2030, 1, '', 0, 'modeArmorRepDurationPostDiv', 0 FROM DUAL
UNION ALL SELECT 2031, 0, '', 0, 'modeMWDVelocityPostDiv', 0 FROM DUAL
UNION ALL SELECT 2032, 0, '', 0, 'modeMWDCapPostDiv', 0 FROM DUAL
UNION ALL SELECT 2033, 1000000, '', 0, 'speedLimit', 11 FROM DUAL
UNION ALL SELECT 2034, 0, 'Shield Damage Limit (per second)', 0, 'shieldDamageLimit', 113 FROM DUAL
UNION ALL SELECT 2035, 0, 'Armor Damage Limit (per second)', 0, 'armorDamageLimit', 113 FROM DUAL
UNION ALL SELECT 2036, 0, 'Structure Damage Limit (per second)', 0, 'structureDamageLimit', 113 FROM DUAL
UNION ALL SELECT 2037, 0, 'Shield Repair Limit (per second)', 0, 'shieldRepairLimit', 113 FROM DUAL
UNION ALL SELECT 2038, 0, 'Armor Repair Limit (per second)', 0, 'armorRepairLimit', 113 FROM DUAL
UNION ALL SELECT 2039, 0, 'Structure Repair Limit (per second)', 0, 'structureRepairLimit', 113 FROM DUAL
UNION ALL SELECT 2041, 0, '', 0, 'entosisCPUAdd', 0 FROM DUAL
UNION ALL SELECT 2042, 0, '', 0, 'entosisCPUPenalty', 0 FROM DUAL
UNION ALL SELECT 2043, 0, '', 0, 'roleBonusCBC', 0 FROM DUAL
UNION ALL SELECT 2044, 0, 'Effectiveness Falloff', 1399, 'falloffEffectiveness', 1 FROM DUAL
UNION ALL SELECT 2045, 1, 'Capacitor Warfare Resistance', 1400, 'energyWarfareResistance', 108 FROM DUAL
UNION ALL SELECT 2046, 250000, '', 0, 'entitySuperWeaponMaxRange', 0 FROM DUAL
UNION ALL SELECT 2047, 250000, '', 0, 'entitySuperWeaponFallOff', 0 FROM DUAL
UNION ALL SELECT 2048, 1, '', 0, 'entitySuperWeaponTrackingSpeed', 0 FROM DUAL
UNION ALL SELECT 2049, 20, '', 0, 'entitySuperWeaponOptimalSignatureRadius', 0 FROM DUAL
UNION ALL SELECT 2055, 0, 'Fighter Hangar Capacity', 1084, 'fighterCapacity', 9 FROM DUAL
UNION ALL SELECT 2056, 0, 'Service Slots', 0, 'serviceSlots', 122 FROM DUAL
UNION ALL SELECT 2058, 0, '', 0, 'clothingAlsoCoversCategory2', 0 FROM DUAL
UNION ALL SELECT 2059, 0, '', 0, 'eliteBonusCommandDestroyer1', 0 FROM DUAL
UNION ALL SELECT 2060, 0, '', 0, 'eliteBonusCommandDestroyer2', 0 FROM DUAL
UNION ALL SELECT 2061, 0, '', 0, 'eliteBonusCommandDestroyer3', 0 FROM DUAL
UNION ALL SELECT 2063, 0, 'Does Not Require Clothing Type', 0, 'clothingRemovesCategory2', 0 FROM DUAL
UNION ALL SELECT 2064, 0, '', 0, 'roleBonusCD', 0 FROM DUAL
UNION ALL SELECT 2065, 0, 'Can be fitted to', 1443, 'canFitShipGroup09', 115 FROM DUAL
UNION ALL SELECT 2066, 0, 'Jump Distance', 1389, 'mjdJumpRange', 1 FROM DUAL
UNION ALL SELECT 2067, 0, 'Area Effect Radius', 1391, 'mjfgRadius', 1 FROM DUAL
UNION ALL SELECT 2069, 0, '', 0, 'eliteBonusElectronicAttackShip3', 0 FROM DUAL
UNION ALL SELECT 2070, 0, '', 0, 'shipBonusAC3', 0 FROM DUAL
UNION ALL SELECT 2071, 0, '', 0, 'bumpingStrength', 0 FROM DUAL
UNION ALL SELECT 2072, 0, 'Modification of Gravimetric Strength Bonus', 3226, 'scanGravimetricStrengthBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2073, 0, 'Modification of Ladar Strength Bonus', 3228, 'scanLadarStrengthBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2074, 0, 'Modification of Magnetometric Strength Bonus', 3227, 'scanMagnetometricStrengthBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2075, 0, 'Modification of Radar Strength Bonus', 3229, 'scanRadarStrengthBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2076, 0, 'Used with (Launcher Group)', 0, 'launcherGroup4', 115 FROM DUAL
UNION ALL SELECT 2077, 0, 'Used with (Launcher Group)', 0, 'launcherGroup5', 115 FROM DUAL
UNION ALL SELECT 2078, 0, 'Used with (Launcher Group)', 0, 'launcherGroup6', 115 FROM DUAL
UNION ALL SELECT 2079, 1, 'Set Armor EM Damage Resistance', 0, 'armorEmDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2080, 1, 'Set Armor Explosive Damage Resistance', 0, 'armorExplosiveDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2081, 1, 'Set Armor Kinetic Damage Resistance', 0, 'armorKineticDamageResonancePostAssignment', 0 FROM DUAL
UNION ALL SELECT 2082, 1, 'Set Armor Thermal Damage Resistance', 0, 'armorThermalDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2083, 1, 'Set Shield EM Damage Resistance', 0, 'shieldEmDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2084, 1, 'Set Shield Explosive Damage Resistance', 0, 'shieldExplosiveDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2085, 1, 'Set Shield Kinetic Damage Resistance', 0, 'shieldKineticDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2086, 1, 'Set Shield Thermal Damage Resistance', 0, 'shieldThermalDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2087, 1, 'Set Hull EM Damage Resistance', 0, 'emDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2088, 1, 'Set Hull Explosive Damage Resistance', 0, 'explosiveDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2089, 1, 'Set Hull Thermal Damage Resistance', 0, 'thermalDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2090, 1, 'Set Hull Kinetic Damage Resistance', 0, 'kineticDamageResonancePostAssignment', 108 FROM DUAL
UNION ALL SELECT 2091, 0, '', 0, 'roleBonus', 0 FROM DUAL
UNION ALL SELECT 2092, 0, '', 0, 'eliteBonusLogiFrig1', 0 FROM DUAL
UNION ALL SELECT 2093, 0, '', 0, 'eliteBonusLogiFrig2', 0 FROM DUAL
UNION ALL SELECT 2094, 0, 'Micro Warp Drive Duration', 1392, 'fighterMicroWarpDriveDuration', 101 FROM DUAL
UNION ALL SELECT 2095, 0, '', 0, 'structureRigBonus1', 0 FROM DUAL
UNION ALL SELECT 2096, 0, '', 0, 'structureRigBonus2', 0 FROM DUAL
UNION ALL SELECT 2097, 0, '', 0, 'structureRigBonus3', 0 FROM DUAL
UNION ALL SELECT 2098, 0, '', 0, 'structureRigBonus4', 0 FROM DUAL
UNION ALL SELECT 2099, 0, '', 0, 'structureRigBonus5', 0 FROM DUAL
UNION ALL SELECT 2100, 0, '', 0, 'structureRigBonus6', 0 FROM DUAL
UNION ALL SELECT 2101, 0, '', 0, 'structureRigBonus7', 0 FROM DUAL
UNION ALL SELECT 2102, 0, '', 0, 'ignoreCloakVelocityPenalty', 0 FROM DUAL
UNION ALL SELECT 2103, 0, 'Can be fitted to', 1443, 'canFitShipType6', 116 FROM DUAL
UNION ALL SELECT 2104, 0, 'Number of Doomsday Targets', 0, 'lightningWeaponTargetAmount', 138 FROM DUAL
UNION ALL SELECT 2105, 0, 'Maximum Target Jump Range', 0, 'lightningWeaponTargetRange', 1 FROM DUAL
UNION ALL SELECT 2106, 0, 'Damage Reduction per Target Jump', 0, 'lightningWeaponDamageLossTarget', 111 FROM DUAL
UNION ALL SELECT 2107, 0, 'Micro Jump Drive Duration', 1392, 'fighterMicroJumpDriveDuration', 101 FROM DUAL
UNION ALL SELECT 2108, 0, 'Service Module Fuel Need', 0, 'serviceModuleFuelConsumptionGroup', 115 FROM DUAL
UNION ALL SELECT 2109, 0, 'Service Module Cycle Fuel Need', 0, 'serviceModuleFuelAmount', 138 FROM DUAL
UNION ALL SELECT 2110, 0, 'Service Module Online Fuel Need', 0, 'serviceModuleFuelOnlineAmount', 138 FROM DUAL
UNION ALL SELECT 2111, 0, 'Weekly Vulnerability', 0, 'vulnerabilityRequired', 129 FROM DUAL
UNION ALL SELECT 2112, 1, 'Sensor Warfare Resistance', 74, 'sensorDampenerResistance', 108 FROM DUAL
UNION ALL SELECT 2113, 1, 'Weapon Disruption Resistance', 1398, 'weaponDisruptionResistance', 108 FROM DUAL
UNION ALL SELECT 2114, 1, 'Target Painter Resistance', 1390, 'targetPainterResistance', 108 FROM DUAL
UNION ALL SELECT 2115, 1, 'Stasis Webifier Resistance', 1389, 'stasisWebifierResistance', 108 FROM DUAL
UNION ALL SELECT 2116, 1, 'Remote Logistics Impedance', 80, 'remoteRepairImpedance', 108 FROM DUAL
UNION ALL SELECT 2118, 1, 'Shield EM Damage Resistance', 1396, 'fighterAbilityEvasiveManeuversEmResonance', 108 FROM DUAL
UNION ALL SELECT 2119, 1, 'Shield Thermal Damage Resistance', 1394, 'fighterAbilityEvasiveManeuversThermResonance', 108 FROM DUAL
UNION ALL SELECT 2120, 1, 'Shield Kinetic Damage Resistance', 1393, 'fighterAbilityEvasiveManeuversKinResonance', 108 FROM DUAL
UNION ALL SELECT 2121, 1, 'Shield Explosive Damage Resistance', 1395, 'fighterAbilityEvasiveManeuversExpResonance', 108 FROM DUAL
UNION ALL SELECT 2123, 0, 'Duration', 1392, 'fighterAbilityEvasiveManeuversDuration', 101 FROM DUAL
UNION ALL SELECT 2125, 0, 'Explosion Radius', 1390, 'fighterAbilityMissilesExplosionRadius', 1 FROM DUAL
UNION ALL SELECT 2126, 0, 'Explosion Velocity', 1389, 'fighterAbilityMissilesExplosionVelocity', 10 FROM DUAL
UNION ALL SELECT 2127, 0, '', 0, 'fighterAbilityMissilesDamageReductionFactor', 0 FROM DUAL
UNION ALL SELECT 2128, 0, '', 0, 'fighterAbilityMissilesDamageReductionSensitivity', 0 FROM DUAL
UNION ALL SELECT 2130, 0, 'Damage Multiplier', 1432, 'fighterAbilityMissilesDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 2131, 0, 'EM Damage (Per Fighter)', 1388, 'fighterAbilityMissilesDamageEM', 113 FROM DUAL
UNION ALL SELECT 2132, 0, 'Thermal Damage (Per Fighter)', 1386, 'fighterAbilityMissilesDamageTherm', 113 FROM DUAL
UNION ALL SELECT 2133, 0, 'Kinetic Damage (Per Fighter)', 1385, 'fighterAbilityMissilesDamageKin', 113 FROM DUAL
UNION ALL SELECT 2134, 0, 'Explosive Damage (Per Fighter)', 1387, 'fighterAbilityMissilesDamageExp', 113 FROM DUAL
UNION ALL SELECT 2135, 1, 'Remote Electronic Assistance Impedance', 74, 'remoteAssistanceImpedance', 108 FROM DUAL
UNION ALL SELECT 2136, 0, '', 0, 'maxTargetRangeBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2137, 0, '', 0, 'scanResolutionBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2138, 0, '', 0, 'remoteResistanceID', 119 FROM DUAL
UNION ALL SELECT 2139, 0, '', 0, 'maxRangeBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2140, 0, '', 0, 'falloffBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2141, 0, '', 0, 'trackingSpeedBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2142, 0, '', 0, 'aoeCloudSizeBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2143, 0, '', 0, 'aoeVelocityBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2144, 0, '', 0, 'explosionDelayBonusInterim', 121 FROM DUAL
UNION ALL SELECT 2145, 0, '', 0, 'missileVelocityBonusInterim', 105 FROM DUAL
UNION ALL SELECT 2147, 0, '', 0, 'signatureRadiusBonusInterim', 124 FROM DUAL
UNION ALL SELECT 2148, 1, '', 0, 'speedFactorInterim', 124 FROM DUAL
UNION ALL SELECT 2149, 0, 'Optimal Range', 1391, 'fighterAbilityMissilesRange', 1 FROM DUAL
UNION ALL SELECT 2150, 0, '', 0, 'fighterSquadronSize', 0 FROM DUAL
UNION ALL SELECT 2151, 100, 'Maximum Velocity Bonus', 1389, 'fighterAbilityAfterburnerSpeedBonus', 124 FROM DUAL
UNION ALL SELECT 2152, 0, 'Maximum Velocity Bonus', 1389, 'fighterAbilityMicroWarpDriveSpeedBonus', 124 FROM DUAL
UNION ALL SELECT 2153, 0, 'Signature Radius Bonus', 1390, 'fighterAbilityMicroWarpDriveSignatureRadiusBonus', 124 FROM DUAL
UNION ALL SELECT 2154, 0, 'Jump Range', 1391, 'fighterAbilityMicroJumpDriveDistance', 1 FROM DUAL
UNION ALL SELECT 2155, 0, 'Duration', 1392, 'fighterAbilityMicroJumpDriveDuration', 101 FROM DUAL
UNION ALL SELECT 2156, 0, 'Signature Radius Bonus', 1390, 'fighterAbilityMicroJumpDriveSignatureRadiusBonus', 124 FROM DUAL
UNION ALL SELECT 2157, 0, 'Duration', 1392, 'fighterAbilityMicroWarpDriveDuration', 101 FROM DUAL
UNION ALL SELECT 2158, 0, 'Duration', 1392, 'fighterAbilityAfterburnerDuration', 101 FROM DUAL
UNION ALL SELECT 2170, 0, '', 0, 'fighterAbilityMissilesResistanceID', 119 FROM DUAL
UNION ALL SELECT 2171, 0, 'EM Damage (Per Fighter)', 1388, 'fighterAbilityAttackTurretDamageEM', 113 FROM DUAL
UNION ALL SELECT 2172, 0, 'Thermal Damage (Per Fighter)', 1386, 'fighterAbilityAttackTurretDamageTherm', 113 FROM DUAL
UNION ALL SELECT 2173, 0, 'Kinetic Damage (Per Fighter)', 1385, 'fighterAbilityAttackTurretDamageKin', 113 FROM DUAL
UNION ALL SELECT 2174, 0, 'Explosive Damage (Per Fighter)', 1387, 'fighterAbilityAttackTurretDamageExp', 113 FROM DUAL
UNION ALL SELECT 2175, 0, 'Optimal range', 1391, 'fighterAbilityAttackTurretRangeOptimal', 1 FROM DUAL
UNION ALL SELECT 2176, 0, 'Accuracy Falloff', 1399, 'fighterAbilityAttackTurretRangeFalloff', 1 FROM DUAL
UNION ALL SELECT 2177, 0, 'Rate of fire', 1397, 'fighterAbilityAttackTurretDuration', 101 FROM DUAL
UNION ALL SELECT 2178, 0, 'Damage Multiplier', 1432, 'fighterAbilityAttackTurretDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 2179, 1, 'Signature Resolution', 0, 'fighterAbilityAttackTurretSignatureResolution', 1 FROM DUAL
UNION ALL SELECT 2180, 0, 'Tracking Speed / Accuracy', 1398, 'fighterAbilityAttackTurretTrackingSpeed', 112 FROM DUAL
UNION ALL SELECT 2182, 0, 'Rate of fire', 1397, 'fighterAbilityMissilesDuration', 101 FROM DUAL
UNION ALL SELECT 2183, 0, 'Duration', 1392, 'fighterAbilityStasisWebifierDuration', 101 FROM DUAL
UNION ALL SELECT 2184, 0, 'Maximum Velocity Bonus (Per Fighter)', 1389, 'fighterAbilityStasisWebifierSpeedPenalty', 124 FROM DUAL
UNION ALL SELECT 2185, 0, '', 0, 'fighterAbilityStasisWebifierSpeedPenaltyInterim', 124 FROM DUAL
UNION ALL SELECT 2186, 0, 'Optimal Range', 1391, 'fighterAbilityStasisWebifierOptimalRange', 1 FROM DUAL
UNION ALL SELECT 2187, 0, 'Effectiveness Falloff', 1399, 'fighterAbilityStasisWebifierFalloffRange', 1 FROM DUAL
UNION ALL SELECT 2188, 0, '', 0, 'fighterAbilityStasisWebifierResistanceID', 119 FROM DUAL
UNION ALL SELECT 2189, 0.20000000298023224, '', 0, 'fighterAbilityAntiFighterMissileResistance', 108 FROM DUAL
UNION ALL SELECT 2203, 0, 'Duration', 1392, 'fighterAbilityWarpDisruptionDuration', 101 FROM DUAL
UNION ALL SELECT 2204, 0, 'Optimal Range', 1391, 'fighterAbilityWarpDisruptionRange', 1 FROM DUAL
UNION ALL SELECT 2205, 0, 'Warp Disruption Strength (Per Fighter)', 111, 'fighterAbilityWarpDisruptionPointStrength', 0 FROM DUAL
UNION ALL SELECT 2206, 0, '', 0, 'fighterAbilityWarpDisruptionPointStrengthInterim', 0 FROM DUAL
UNION ALL SELECT 2207, 0, '', 0, 'fighterAbilityEnergyNeutralizerResistanceID', 119 FROM DUAL
UNION ALL SELECT 2208, 0, 'Duration', 1392, 'fighterAbilityEnergyNeutralizerDuration', 101 FROM DUAL
UNION ALL SELECT 2209, 0, 'Optimal Range', 1391, 'fighterAbilityEnergyNeutralizerOptimalRange', 1 FROM DUAL
UNION ALL SELECT 2210, 0, 'Effectiveness Falloff', 1399, 'fighterAbilityEnergyNeutralizerFalloffRange', 1 FROM DUAL
UNION ALL SELECT 2211, 0, 'Energy Amount Neutralized (Per Fighter)', 1400, 'fighterAbilityEnergyNeutralizerAmount', 114 FROM DUAL
UNION ALL SELECT 2212, 0, '', 0, 'fighterSquadronIsLight', 0 FROM DUAL
UNION ALL SELECT 2213, 0, '', 0, 'fighterSquadronIsSupport', 0 FROM DUAL
UNION ALL SELECT 2214, 0, '', 0, 'fighterSquadronIsHeavy', 0 FROM DUAL
UNION ALL SELECT 2215, 0, 'Squadron Size', 0, 'fighterSquadronMaxSize', 0 FROM DUAL
UNION ALL SELECT 2216, 0, 'Fighter Squadron Launch Tubes', 2677, 'fighterTubes', 0 FROM DUAL
UNION ALL SELECT 2217, 0, 'Light Fighter Squadron Limit', 2987, 'fighterLightSlots', 0 FROM DUAL
UNION ALL SELECT 2218, 0, 'Support Fighter Squadron Limit', 2987, 'fighterSupportSlots', 0 FROM DUAL
UNION ALL SELECT 2219, 0, 'Heavy Fighter Squadrons Limit', 2987, 'fighterHeavySlots', 0 FROM DUAL
UNION ALL SELECT 2220, 0, 'Duration', 1392, 'fighterAbilityECMDuration', 101 FROM DUAL
UNION ALL SELECT 2221, 0, 'Optimal Range', 1391, 'fighterAbilityECMRangeOptimal', 1 FROM DUAL
UNION ALL SELECT 2222, 0, 'Effectiveness Falloff', 1399, 'fighterAbilityECMRangeFalloff', 1 FROM DUAL
UNION ALL SELECT 2223, 0, 'Orbit Range', 1391, 'fighterSquadronOrbitRange', 1 FROM DUAL
UNION ALL SELECT 2224, 0, 'Maximum Velocity Bonus', 1389, 'fighterAbilityEvasiveManeuversSpeedBonus', 124 FROM DUAL
UNION ALL SELECT 2225, 0, 'Signature Radius Reduction', 1390, 'fighterAbilityEvasiveManeuversSignatureRadiusBonus', 105 FROM DUAL
UNION ALL SELECT 2226, 0, 'Damage Multiplier', 1432, 'fighterAbilityAttackMissileDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 2227, 0, 'EM Damage (Per Fighter)', 1388, 'fighterAbilityAttackMissileDamageEM', 113 FROM DUAL
UNION ALL SELECT 2228, 0, 'Thermal Damage (Per Fighter)', 1386, 'fighterAbilityAttackMissileDamageTherm', 113 FROM DUAL
UNION ALL SELECT 2229, 0, 'Kinetic Damage (Per Fighter)', 1385, 'fighterAbilityAttackMissileDamageKin', 113 FROM DUAL
UNION ALL SELECT 2230, 0, 'Explosive Damage (Per Fighter)', 1387, 'fighterAbilityAttackMissileDamageExp', 113 FROM DUAL
UNION ALL SELECT 2231, 0, '', 0, 'fighterAbilityAttackMissileReductionFactor', 0 FROM DUAL
UNION ALL SELECT 2232, 0, '', 0, 'fighterAbilityAttackMissileReductionSensitivity', 0 FROM DUAL
UNION ALL SELECT 2233, 0, 'Rate of fire', 1397, 'fighterAbilityAttackMissileDuration', 101 FROM DUAL
UNION ALL SELECT 2234, 0, 'Explosion Radius', 1390, 'fighterAbilityAttackMissileExplosionRadius', 1 FROM DUAL
UNION ALL SELECT 2235, 0, 'Explosion Velocity', 1389, 'fighterAbilityAttackMissileExplosionVelocity', 10 FROM DUAL
UNION ALL SELECT 2236, 0, 'Optimal range', 1391, 'fighterAbilityAttackMissileRangeOptimal', 1 FROM DUAL
UNION ALL SELECT 2237, 0, 'Accuracy Falloff', 1399, 'fighterAbilityAttackMissileRangeFalloff', 1 FROM DUAL
UNION ALL SELECT 2238, 0, 'Duration', 1392, 'fighterAbilityTackleDuration', 101 FROM DUAL
UNION ALL SELECT 2239, 0, 'Range', 1391, 'fighterAbilityTackleRange', 1 FROM DUAL
UNION ALL SELECT 2242, 0, 'Maximum Velocity Bonus (Per Fighter)', 1389, 'fighterAbilityTackleWebSpeedPenalty', 124 FROM DUAL
UNION ALL SELECT 2243, 0, '', 0, 'fighterAbilityTackleWebSpeedPenaltyInterim', 124 FROM DUAL
UNION ALL SELECT 2244, 0.10000000149011612, '', 0, 'fighterAbilityAntiCapitalMissileResistance', 108 FROM DUAL
UNION ALL SELECT 2246, 0, 'Gravimetric ECM Jammer Strength (Per Fighter)', 3226, 'fighterAbilityECMStrengthGravimetric', 0 FROM DUAL
UNION ALL SELECT 2247, 0, 'Ladar ECM Jammer Strength (Per Fighter)', 3228, 'fighterAbilityECMStrengthLadar', 0 FROM DUAL
UNION ALL SELECT 2248, 0, 'Magnetometric ECM Jammer Strength (Per Fighter)', 3227, 'fighterAbilityECMStrengthMagnetometric', 0 FROM DUAL
UNION ALL SELECT 2249, 0, 'Radar ECM Jammer Strength (Per Fighter)', 3229, 'fighterAbilityECMStrengthRadar', 0 FROM DUAL
UNION ALL SELECT 2250, 0, '', 0, 'fighterAbilityECMTargetSuccess', 0 FROM DUAL
UNION ALL SELECT 2251, 0, '', 0, 'fighterAbilityECMTargetJam', 0 FROM DUAL
UNION ALL SELECT 2252, 0, '', 0, 'fighterAbilityECMResistanceID', 119 FROM DUAL
UNION ALL SELECT 2253, 1, 'ECM Resistance', 109, 'ECMResistance', 108 FROM DUAL
UNION ALL SELECT 2255, 0, '', 0, 'scanGravimetricStrengthPercentInterim', 105 FROM DUAL
UNION ALL SELECT 2256, 0, '', 0, 'scanLadarStrengthPercentInterim', 105 FROM DUAL
UNION ALL SELECT 2257, 0, '', 0, 'scanMagnetometricStrengthPercentInterim', 105 FROM DUAL
UNION ALL SELECT 2258, 0, '', 0, 'scanRadarStrengthPercentInterim', 105 FROM DUAL
UNION ALL SELECT 2259, 0, 'Warm-up Neutralization Radius', 1391, 'doomsdayEnergyNeutRadius', 1 FROM DUAL
UNION ALL SELECT 2260, 0, 'Warm-up Neutralization Amount', 1400, 'doomsdayEnergyNeutAmount', 114 FROM DUAL
UNION ALL SELECT 2261, 0, 'Warm-up Neutralization Signature Radius', 1390, 'doomsdayEnergyNeutSignatureRadius', 1 FROM DUAL
UNION ALL SELECT 2262, 0, 'Warm-up Duration', 1400, 'doomsdayWarningDuration', 101 FROM DUAL
UNION ALL SELECT 2263, 0, 'Beam Radius', 1391, 'doomsdayDamageRadius', 1 FROM DUAL
UNION ALL SELECT 2264, 0, 'Beam Duration', 1400, 'doomsdayDamageDuration', 101 FROM DUAL
UNION ALL SELECT 2265, 0, 'Beam Damage Cycle', 1392, 'doomsdayDamageCycleTime', 101 FROM DUAL
UNION ALL SELECT 2266, -99, '', 0, 'speedFactorFloor', 124 FROM DUAL
UNION ALL SELECT 2267, 0, 'Capacitor Warfare Resistance Bonus', 89, 'energyWarfareResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 2268, 0, 'Maximum tethering range', 0, 'tetheringRange', 1 FROM DUAL
UNION ALL SELECT 2269, 0, '', 0, 'isPointTargeted', 0 FROM DUAL
UNION ALL SELECT 2270, 0, '', 0, 'fighterSquadronRole', 0 FROM DUAL
UNION ALL SELECT 2271, 0, 'EM Damage', 1388, 'onDeathDamageEM', 113 FROM DUAL
UNION ALL SELECT 2272, 0, 'Thermal Damage', 1386, 'onDeathDamageTherm', 113 FROM DUAL
UNION ALL SELECT 2273, 0, 'Kinetic Damage', 1385, 'onDeathDamageKin', 113 FROM DUAL
UNION ALL SELECT 2274, 0, 'Explosive Damage', 1387, 'onDeathDamageExp', 113 FROM DUAL
UNION ALL SELECT 2275, 0, 'Explosion Range', 1391, 'onDeathAOERadius', 1 FROM DUAL
UNION ALL SELECT 2276, 0, 'Explosion Signature Radius', 1390, 'onDeathSignatureRadius', 1 FROM DUAL
UNION ALL SELECT 2277, 0, 'Additional doomsday secondary targets', 0, 'structureRigDoomsdayTargetAmountBonus', 138 FROM DUAL
UNION ALL SELECT 2278, 0, 'Bonus to doomsday secondary target damage reduction', 0, 'structureRigDoomsdayDamageLossTargetBonus', 127 FROM DUAL
UNION ALL SELECT 2279, 0, 'AOE Range', 1391, 'doomsdayAOERange', 1 FROM DUAL
UNION ALL SELECT 2280, 0, 'AOE Duration', 1392, 'doomsdayAOEDuration', 101 FROM DUAL
UNION ALL SELECT 2281, 0, 'AOE Signature Radius', 1390, 'doomsdayAOESignatureRadius', 1 FROM DUAL
UNION ALL SELECT 2282, 0, 'Modification of Sensor Strength Bonus', 3226, 'sensorStrengthBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2283, 0, '', 0, 'shipBonusDreadnoughtA1', 0 FROM DUAL
UNION ALL SELECT 2284, 0, '', 0, 'shipBonusDreadnoughtA2', 0 FROM DUAL
UNION ALL SELECT 2285, 0, '', 0, 'shipBonusDreadnoughtA3', 0 FROM DUAL
UNION ALL SELECT 2286, 0, '', 0, 'shipBonusDreadnoughtC1', 0 FROM DUAL
UNION ALL SELECT 2287, 0, '', 0, 'shipBonusDreadnoughtC2', 0 FROM DUAL
UNION ALL SELECT 2288, 0, '', 0, 'shipBonusDreadnoughtC3', 0 FROM DUAL
UNION ALL SELECT 2289, 0, '', 0, 'shipBonusDreadnoughtG1', 0 FROM DUAL
UNION ALL SELECT 2290, 0, '', 0, 'shipBonusDreadnoughtG2', 0 FROM DUAL
UNION ALL SELECT 2291, 0, '', 0, 'shipBonusDreadnoughtG3', 0 FROM DUAL
UNION ALL SELECT 2292, 0, '', 0, 'shipBonusDreadnoughtM1', 0 FROM DUAL
UNION ALL SELECT 2293, 0, '', 0, 'shipBonusDreadnoughtM2', 0 FROM DUAL
UNION ALL SELECT 2294, 0, '', 0, 'shipBonusDreadnoughtM3', 0 FROM DUAL
UNION ALL SELECT 2298, 0, '', 0, 'shipBonusRole1', 0 FROM DUAL
UNION ALL SELECT 2299, 0, '', 0, 'shipBonusRole2', 0 FROM DUAL
UNION ALL SELECT 2300, 0, '', 0, 'shipBonusRole3', 0 FROM DUAL
UNION ALL SELECT 2301, 0, '', 0, 'shipBonusRole4', 0 FROM DUAL
UNION ALL SELECT 2302, 0, '', 0, 'shipBonusRole5', 0 FROM DUAL
UNION ALL SELECT 2303, 0, '', 0, 'shipBonusRole6', 0 FROM DUAL
UNION ALL SELECT 2304, 0, 'Torpedo Velocity Bonus', 1389, 'siegeTorpedoVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 2305, 0, 'XL Launcher ROF Bonus', 1389, 'siegeLauncherROFBonus', 105 FROM DUAL
UNION ALL SELECT 2306, 0, 'Siege Missile Damage Bonus', 1397, 'siegeMissileDamageBonus', 105 FROM DUAL
UNION ALL SELECT 2307, 0, 'Turret Damage Bonus', 1432, 'siegeTurretDamageBonus', 105 FROM DUAL
UNION ALL SELECT 2308, 0, '', 0, 'shipBonusForceAuxiliaryA1', 0 FROM DUAL
UNION ALL SELECT 2309, 0, '', 0, 'shipBonusForceAuxiliaryA2', 0 FROM DUAL
UNION ALL SELECT 2310, 0, '', 0, 'shipBonusForceAuxiliaryA3', 0 FROM DUAL
UNION ALL SELECT 2311, 0, '', 0, 'shipBonusForceAuxiliaryC1', 0 FROM DUAL
UNION ALL SELECT 2312, 0, '', 0, 'shipBonusForceAuxiliaryC2', 0 FROM DUAL
UNION ALL SELECT 2313, 0, '', 0, 'shipBonusForceAuxiliaryC3', 0 FROM DUAL
UNION ALL SELECT 2314, 0, '', 0, 'shipBonusForceAuxiliaryG1', 0 FROM DUAL
UNION ALL SELECT 2315, 0, '', 0, 'shipBonusForceAuxiliaryG2', 0 FROM DUAL
UNION ALL SELECT 2316, 0, '', 0, 'shipBonusForceAuxiliaryG3', 0 FROM DUAL
UNION ALL SELECT 2317, 0, '', 0, 'shipBonusForceAuxiliaryM1', 0 FROM DUAL
UNION ALL SELECT 2318, 0, '', 0, 'shipBonusForceAuxiliaryM2', 0 FROM DUAL
UNION ALL SELECT 2319, 0, '', 0, 'shipBonusForceAuxiliaryM3', 0 FROM DUAL
UNION ALL SELECT 2320, 0, '', 0, 'shipBonusForceAuxiliaryA4', 0 FROM DUAL
UNION ALL SELECT 2321, 0, '', 0, 'shipBonusForceAuxiliaryC4', 0 FROM DUAL
UNION ALL SELECT 2322, 0, '', 0, 'shipBonusForceAuxiliaryG4', 0 FROM DUAL
UNION ALL SELECT 2323, 0, '', 0, 'shipBonusForceAuxiliaryM4', 0 FROM DUAL
UNION ALL SELECT 2324, 0, '', 0, 'fighterAbilityLaunchBombType', 116 FROM DUAL
UNION ALL SELECT 2325, 0, 'EM Damage (Per Fighter)', 0, 'fighterAbilityKamikazeDamageEM', 113 FROM DUAL
UNION ALL SELECT 2326, 0, 'Thermal Damage (Per Fighter)', 0, 'fighterAbilityKamikazeDamageTherm', 113 FROM DUAL
;
INSERT INTO BASE_ATTRIBUTES (attribute_id, default_value, display_name, icon_id, name, unit_id)
          SELECT 2327, 0, 'Kinetic Damage (Per Fighter)', 0, 'fighterAbilityKamikazeDamageKin', 113 FROM DUAL
UNION ALL SELECT 2328, 0, 'Explosive Damage (Per Fighter)', 0, 'fighterAbilityKamikazeDamageExp', 113 FROM DUAL
UNION ALL SELECT 2329, 0, '', 0, 'fighterAbilityKamikazeSignatureRadius', 1 FROM DUAL
UNION ALL SELECT 2330, 500, '', 0, 'fighterAbilityKamikazeRange', 1 FROM DUAL
UNION ALL SELECT 2333, 0, '', 0, 'structureRoleBonus', 105 FROM DUAL
UNION ALL SELECT 2334, 0, '', 0, 'structureItemVisualFlag', 0 FROM DUAL
UNION ALL SELECT 2335, 0, 'Fighter Shield Bonus', 1384, 'fighterBonusShieldCapacityPercent', 109 FROM DUAL
UNION ALL SELECT 2336, 0, 'Fighter Velocity Bonus', 1389, 'fighterBonusVelocityPercent', 109 FROM DUAL
UNION ALL SELECT 2337, 0, 'Fighter ROF Bonus', 1389, 'fighterBonusROFPercent', 111 FROM DUAL
UNION ALL SELECT 2338, 0, 'Fighter Shield Recharge Bonus', 1392, 'fighterBonusShieldRechargePercent', 111 FROM DUAL
UNION ALL SELECT 2339, 0, '', 0, 'structureServiceRoleBonus', 105 FROM DUAL
UNION ALL SELECT 2340, 0, 'Bonus to Fighter Hangar size', 0, 'skillBonusFighterHangarSize', 121 FROM DUAL
UNION ALL SELECT 2342, 0, 'Remote Repair Impedance Bonus', 80, 'remoteRepairImpedanceBonus', 124 FROM DUAL
UNION ALL SELECT 2343, 0, 'Disallow Tethering', 0, 'disallowTethering', 137 FROM DUAL
UNION ALL SELECT 2344, 0, 'Capital Remote Logistics Duration Bonus (Shield / Armor / Hull / Energy)', 1392, 'siegeRemoteLogisticsDurationBonus', 105 FROM DUAL
UNION ALL SELECT 2345, 0, 'Capital Remote Logistics Amount Bonus (Shield / Armor / Hull / Energy)', 0, 'siegeRemoteLogisticsAmountBonus', 105 FROM DUAL
UNION ALL SELECT 2346, 0, 'Armor Repairer / Shield Booster Duration Bonus', 2104, 'siegeLocalLogisticsDurationBonus', 105 FROM DUAL
UNION ALL SELECT 2347, 0, 'Armor Repairer / Shield Booster Amount Bonus', 2104, 'siegeLocalLogisticsAmountBonus', 105 FROM DUAL
UNION ALL SELECT 2348, 0, 'Capital Remote Logistics Range Bonus (Shield / Armor / Hull / Energy)', 1391, 'siegeRemoteLogisticsRangeBonus', 105 FROM DUAL
UNION ALL SELECT 2349, 0, 'Duration', 1392, 'fighterAbilityLaunchBombDuration', 101 FROM DUAL
UNION ALL SELECT 2351, 0, 'Sensor Dampener Resistance Bonus', 74, 'sensorDampenerResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 2352, 0, 'Remote Assistance Impedance Bonus', 74, 'remoteAssistanceImpedanceBonus', 124 FROM DUAL
UNION ALL SELECT 2353, 0, 'Weapon Disruption Resistance Bonus', 1398, 'weaponDisruptionResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 2354, 0, 'Disallow Docking', 0, 'disallowDocking', 137 FROM DUAL
UNION ALL SELECT 2355, 1, 'High Security Bonus Multiplier', 0, 'hiSecModifier', 104 FROM DUAL
UNION ALL SELECT 2356, 1, 'Low Security Bonus Multiplier', 0, 'lowSecModifier', 104 FROM DUAL
UNION ALL SELECT 2357, 1, 'Nullsec and Wormhole Bonus Multiplier', 0, 'nullSecModifier', 104 FROM DUAL
UNION ALL SELECT 2358, 0, '', 0, 'securityModifier', 0 FROM DUAL
UNION ALL SELECT 2359, 0, '', 0, 'shipBonusCarrierA1', 0 FROM DUAL
UNION ALL SELECT 2360, 0, '', 0, 'shipBonusCarrierA2', 0 FROM DUAL
UNION ALL SELECT 2361, 0, '', 0, 'shipBonusCarrierA3', 0 FROM DUAL
UNION ALL SELECT 2362, 0, '', 0, 'shipBonusCarrierA4', 0 FROM DUAL
UNION ALL SELECT 2363, 0, '', 0, 'shipBonusCarrierC1', 0 FROM DUAL
UNION ALL SELECT 2364, 0, '', 0, 'shipBonusCarrierC2', 0 FROM DUAL
UNION ALL SELECT 2365, 0, '', 0, 'shipBonusCarrierC3', 0 FROM DUAL
UNION ALL SELECT 2366, 0, '', 0, 'shipBonusCarrierC4', 0 FROM DUAL
UNION ALL SELECT 2367, 0, '', 0, 'shipBonusCarrierG1', 0 FROM DUAL
UNION ALL SELECT 2368, 0, '', 0, 'shipBonusCarrierG2', 0 FROM DUAL
UNION ALL SELECT 2369, 0, '', 0, 'shipBonusCarrierG3', 0 FROM DUAL
UNION ALL SELECT 2370, 0, '', 0, 'shipBonusCarrierG4', 0 FROM DUAL
UNION ALL SELECT 2371, 0, '', 0, 'shipBonusCarrierM1', 0 FROM DUAL
UNION ALL SELECT 2372, 0, '', 0, 'shipBonusCarrierM2', 0 FROM DUAL
UNION ALL SELECT 2373, 0, '', 0, 'shipBonusCarrierM3', 0 FROM DUAL
UNION ALL SELECT 2374, 0, '', 0, 'shipBonusCarrierM4', 0 FROM DUAL
UNION ALL SELECT 2375, 0, '', 0, 'shipBonusSupercarrierA1', 0 FROM DUAL
UNION ALL SELECT 2376, 0, '', 0, 'shipBonusSupercarrierA2', 0 FROM DUAL
UNION ALL SELECT 2377, 0, '', 0, 'shipBonusSupercarrierA3', 0 FROM DUAL
UNION ALL SELECT 2378, 0, '', 0, 'shipBonusSupercarrierA4', 0 FROM DUAL
UNION ALL SELECT 2379, 0, '', 0, 'shipBonusSupercarrierA5', 0 FROM DUAL
UNION ALL SELECT 2380, 0, '', 0, 'shipBonusSupercarrierC1', 0 FROM DUAL
UNION ALL SELECT 2381, 0, '', 0, 'shipBonusSupercarrierC2', 0 FROM DUAL
UNION ALL SELECT 2382, 0, '', 0, 'shipBonusSupercarrierC3', 0 FROM DUAL
UNION ALL SELECT 2383, 0, '', 0, 'shipBonusSupercarrierC4', 0 FROM DUAL
UNION ALL SELECT 2384, 0, '', 0, 'shipBonusSupercarrierC5', 0 FROM DUAL
UNION ALL SELECT 2385, 0, '', 0, 'shipBonusSupercarrierG1', 0 FROM DUAL
UNION ALL SELECT 2386, 0, '', 0, 'shipBonusSupercarrierG2', 0 FROM DUAL
UNION ALL SELECT 2387, 0, '', 0, 'shipBonusSupercarrierG3', 0 FROM DUAL
UNION ALL SELECT 2388, 0, '', 0, 'shipBonusSupercarrierG4', 0 FROM DUAL
UNION ALL SELECT 2389, 0, '', 0, 'shipBonusSupercarrierG5', 0 FROM DUAL
UNION ALL SELECT 2390, 0, '', 0, 'shipBonusSupercarrierM1', 0 FROM DUAL
UNION ALL SELECT 2391, 0, '', 0, 'shipBonusSupercarrierM2', 0 FROM DUAL
UNION ALL SELECT 2392, 0, '', 0, 'shipBonusSupercarrierM3', 0 FROM DUAL
UNION ALL SELECT 2393, 0, '', 0, 'shipBonusSupercarrierM4', 0 FROM DUAL
UNION ALL SELECT 2394, 0, '', 0, 'shipBonusSupercarrierM5', 0 FROM DUAL
UNION ALL SELECT 2395, 0, 'Only usable while structure is vulnerable', 0, 'disallowWhenInvulnerable', 137 FROM DUAL
UNION ALL SELECT 2396, 0, 'Can be fitted to', 1443, 'canFitShipGroup10', 115 FROM DUAL
UNION ALL SELECT 2397, 0, 'Activation time / duration', 1392, 'durationWeaponDisruptionBurstProjector', 101 FROM DUAL
UNION ALL SELECT 2398, 0, 'Activation time / duration', 1392, 'durationECMJammerBurstProjector', 101 FROM DUAL
UNION ALL SELECT 2399, 0, 'Activation time / duration', 1392, 'durationSensorDampeningBurstProjector', 101 FROM DUAL
UNION ALL SELECT 2400, 0, 'Activation time / duration', 1392, 'durationTargetIlluminationBurstProjector', 101 FROM DUAL
UNION ALL SELECT 2401, 10000, '', 0, 'fighterAbilityKamikazeDuration', 101 FROM DUAL
UNION ALL SELECT 2402, 0, 'Modification of EM Damage Resistance Bonus', 1396, 'emDamageResistanceBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2403, 0, 'Modification of Explosive Damage Resistance Bonus', 1395, 'explosiveDamageResistanceBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2404, 0, 'Modification of Kinetic Damage Resistance Bonus', 1393, 'kineticDamageResistanceBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2405, 0, 'Modification of Thermal Damage Resistance Bonus', 1394, 'thermalDamageResistanceBonusBonus', 105 FROM DUAL
UNION ALL SELECT 2406, 0, '', 0, 'shipBonusTitanA1', 0 FROM DUAL
UNION ALL SELECT 2407, 0, '', 0, 'shipBonusTitanA2', 0 FROM DUAL
UNION ALL SELECT 2408, 0, '', 0, 'shipBonusTitanA3', 0 FROM DUAL
UNION ALL SELECT 2409, 0, '', 0, 'shipBonusTitanA4', 0 FROM DUAL
UNION ALL SELECT 2410, 0, '', 0, 'shipBonusTitanC1', 0 FROM DUAL
UNION ALL SELECT 2411, 0, '', 0, 'shipBonusTitanC2', 0 FROM DUAL
UNION ALL SELECT 2412, 0, '', 0, 'shipBonusTitanC3', 0 FROM DUAL
UNION ALL SELECT 2413, 0, '', 0, 'shipBonusTitanC4', 0 FROM DUAL
UNION ALL SELECT 2414, 0, '', 0, 'shipBonusTitanG1', 0 FROM DUAL
UNION ALL SELECT 2415, 0, '', 0, 'shipBonusTitanG2', 0 FROM DUAL
UNION ALL SELECT 2416, 0, '', 0, 'shipBonusTitanG3', 0 FROM DUAL
UNION ALL SELECT 2417, 0, '', 0, 'shipBonusTitanG4', 0 FROM DUAL
UNION ALL SELECT 2418, 0, '', 0, 'shipBonusTitanM1', 0 FROM DUAL
UNION ALL SELECT 2419, 0, '', 0, 'shipBonusTitanM2', 0 FROM DUAL
UNION ALL SELECT 2420, 0, '', 0, 'shipBonusTitanM3', 0 FROM DUAL
UNION ALL SELECT 2421, 0, '', 0, 'shipBonusTitanM4', 0 FROM DUAL
UNION ALL SELECT 2422, 0, 'Expiry Date', 1392, 'boosterLastInjectionDatetime', 143 FROM DUAL
UNION ALL SELECT 2423, 0, '', 0, 'shipBonusTitanC5', 0 FROM DUAL
UNION ALL SELECT 2424, 0, 'Target Painter Resistance Bonus', 1390, 'targetPainterResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 2425, 0, 'Warp Disruption Strength', 0, 'fighterAbilityTackleWarpDisruptionPointStrength', 0 FROM DUAL
UNION ALL SELECT 2426, 0, 'Refueling Duration', 1392, 'fighterRefuelingTime', 101 FROM DUAL
UNION ALL SELECT 2427, 0, 'Jump/Dock/Tether/Cloak restriction duration', 0, 'doomsdayNoJumpOrCloakDuration', 101 FROM DUAL
UNION ALL SELECT 2428, 0, 'Immobility Duration', 0, 'doomsdayImmobilityDuration', 101 FROM DUAL
UNION ALL SELECT 2429, 0, 'Shape of Superweapon Effect', 0, 'doomsdayAOEShape', 0 FROM DUAL
UNION ALL SELECT 2430, 0, '', 0, 'doomsdayRangeIsFixed', 0 FROM DUAL
UNION ALL SELECT 2431, 0, 'Max Modules Of This Type Allowed', 0, 'maxTypeFitted', 0 FROM DUAL
UNION ALL SELECT 2432, 0, '', 0, 'fighterAbilityKamikazeResistanceID', 119 FROM DUAL
UNION ALL SELECT 2433, 0, '', 0, 'fighterAbilityKamikazeResistance', 108 FROM DUAL
UNION ALL SELECT 2434, 0, 'Maximum Locked Targets Bonus', 109, 'structureRigMaxTargetBonus', 139 FROM DUAL
UNION ALL SELECT 2435, 0, 'Scan Resolution Bonus', 74, 'structureRigScanResBonus', 105 FROM DUAL
UNION ALL SELECT 2436, 0, 'Point Defense Battery Range Bonus', 1391, 'structureRigPDRangeBonus', 105 FROM DUAL
UNION ALL SELECT 2437, 0, 'Point Defense Battery Capacitor Use Bonus', 1400, 'structureRigPDCapUseBonus', 105 FROM DUAL
UNION ALL SELECT 2438, 0, 'Explosion Velocity Bonus', 1389, 'structureRigMissileExploVeloBonus', 105 FROM DUAL
UNION ALL SELECT 2439, 0, 'Missile Velocity Bonus', 1389, 'structureRigMissileVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 2440, 0, 'Optimal Range Bonus', 1391, 'structureRigEwarOptimalBonus', 105 FROM DUAL
UNION ALL SELECT 2441, 0, 'Falloff Bonus', 1399, 'structureRigEwarFalloffBonus', 105 FROM DUAL
UNION ALL SELECT 2442, 0, 'Capacitor Use Bonus', 1400, 'structureRigEwarCapUseBonus', 105 FROM DUAL
UNION ALL SELECT 2444, 0.5, 'Refining yield for Asteroid Belt Ores', 0, 'refiningYieldNormalOres', 127 FROM DUAL
UNION ALL SELECT 2445, 0.5, 'Refining yield for Moon Ores', 0, 'refiningYieldMoonOres', 127 FROM DUAL
UNION ALL SELECT 2446, 0, 'Refining yield for Clear Icicle and White Glaze', 0, 'refiningYieldCalAmarrIce', 127 FROM DUAL
UNION ALL SELECT 2447, 0, 'Refining yield for Blue Ice and Glacial Mass', 0, 'refiningYieldGalMinIce', 127 FROM DUAL
UNION ALL SELECT 2448, 0.5, 'Refining yield for Ice', 0, 'refiningYieldIce', 127 FROM DUAL
UNION ALL SELECT 2449, 0, 'Guided Bomb Explosion Radius Bonus', 1390, 'structureRigMissileExplosionRadiusBonus', 105 FROM DUAL
UNION ALL SELECT 2450, 0, 'Skill is obsolete', 0, 'isSkillIObsolete', 0 FROM DUAL
UNION ALL SELECT 2451, 0, 'Neutralization Signature Resolution', 1390, 'energyNeutralizerSignatureResolution', 1 FROM DUAL
UNION ALL SELECT 2452, 0, 'Neutralization Falloff Range', 1391, 'energyNeutralizerRangeFalloff', 1 FROM DUAL
UNION ALL SELECT 2453, 0, '', 0, 'disallowDriveJumping', 137 FROM DUAL
UNION ALL SELECT 2454, 0, '', 0, 'disallowCloaking', 137 FROM DUAL
UNION ALL SELECT 2455, 0, '', 0, 'cynosuralFieldSpawnRadius', 1 FROM DUAL
UNION ALL SELECT 2456, 0, 'Shield Capacity Bonus', 1384, 'shieldCapacityBonus2', 105 FROM DUAL
UNION ALL SELECT 2457, 0, 'Armor Repair Bonus', 80, 'armorRepairBonus', 105 FROM DUAL
UNION ALL SELECT 2458, 0, 'Mining Cycle Time Modifier', 0, 'miningDurationRoleBonus', 105 FROM DUAL
UNION ALL SELECT 2459, 0, 'Character Skill Point Limit', 0, 'maxCharacterSkillPointLimit', 0 FROM DUAL
UNION ALL SELECT 2460, 0, '', 0, 'eliteBonusLogistics3', 0 FROM DUAL
UNION ALL SELECT 2461, 0, 'Skill Points', 0, 'containedSkillPoints', 0 FROM DUAL
UNION ALL SELECT 2462, 0, '', 0, 'roleBonusRepairRange', 0 FROM DUAL
UNION ALL SELECT 2463, 0, 'Can be fitted to', 1443, 'canFitShipType7', 116 FROM DUAL
UNION ALL SELECT 2464, 0, '', 0, 'affectedByIndustrialInvulnModule', 0 FROM DUAL
UNION ALL SELECT 2467, 0, 'Corpse Hold Capacity', 71, 'specialCorpseHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 2468, 0, '', 0, 'warfareBuff1ID', 0 FROM DUAL
UNION ALL SELECT 2469, 0, '', 0, 'warfareBuff1Value', 0 FROM DUAL
UNION ALL SELECT 2470, 0, '', 0, 'warfareBuff2ID', 0 FROM DUAL
UNION ALL SELECT 2471, 0, '', 0, 'warfareBuff2Value', 0 FROM DUAL
UNION ALL SELECT 2472, 0, '', 0, 'warfareBuff3ID', 0 FROM DUAL
UNION ALL SELECT 2473, 0, '', 0, 'warfareBuff3Value', 0 FROM DUAL
UNION ALL SELECT 2474, 0, '', 0, 'shipBonusICS3', 105 FROM DUAL
UNION ALL SELECT 2475, 0, '', 0, 'shipBonusICS4', 105 FROM DUAL
UNION ALL SELECT 2476, 0, 'Can be fitted to', 1443, 'canFitShipGroup11', 115 FROM DUAL
UNION ALL SELECT 2477, 0, 'Can be fitted to', 1443, 'canFitShipGroup12', 115 FROM DUAL
UNION ALL SELECT 2478, 0, 'Can be fitted to', 1443, 'canFitShipGroup13', 115 FROM DUAL
UNION ALL SELECT 2479, 0, 'Can be fitted to', 1443, 'canFitShipGroup14', 115 FROM DUAL
UNION ALL SELECT 2480, 0, 'Can be fitted to', 1443, 'canFitShipGroup15', 115 FROM DUAL
UNION ALL SELECT 2481, 0, 'Can be fitted to', 1443, 'canFitShipGroup16', 115 FROM DUAL
UNION ALL SELECT 2482, 0, 'Can be fitted to', 1443, 'canFitShipGroup17', 115 FROM DUAL
UNION ALL SELECT 2483, 0, 'Can be fitted to', 1443, 'canFitShipGroup18', 115 FROM DUAL
UNION ALL SELECT 2484, 0, 'Can be fitted to', 1443, 'canFitShipGroup19', 115 FROM DUAL
UNION ALL SELECT 2485, 0, 'Can be fitted to', 1443, 'canFitShipGroup20', 115 FROM DUAL
UNION ALL SELECT 2486, 0, 'Can be fitted to', 1443, 'canFitShipType8', 116 FROM DUAL
UNION ALL SELECT 2487, 0, 'Can be fitted to', 1443, 'canFitShipType9', 116 FROM DUAL
UNION ALL SELECT 2488, 0, 'Can be fitted to', 1443, 'canFitShipType10', 116 FROM DUAL
UNION ALL SELECT 2489, 0, '', 0, 'behaviorMiningAmount', 9 FROM DUAL
UNION ALL SELECT 2490, 0, '', 0, 'behaviorMiningDuration', 101 FROM DUAL
UNION ALL SELECT 2491, 0, '', 0, 'behaviorRemoteArmorRepairDuration', 101 FROM DUAL
UNION ALL SELECT 2492, 0, '', 0, 'behaviorRemoteArmorRepairRange', 1 FROM DUAL
UNION ALL SELECT 2493, 0, '', 0, 'behaviorRemoteArmorRepairFalloff', 1 FROM DUAL
UNION ALL SELECT 2494, 0, '', 0, 'behaviorRemoteArmorRepairDischarge', 0 FROM DUAL
UNION ALL SELECT 2495, 0, '', 0, 'behaviorRemoteShieldBoostDuration', 101 FROM DUAL
UNION ALL SELECT 2496, 0, '', 0, 'behaviorRemoteShieldBoostRange', 1 FROM DUAL
UNION ALL SELECT 2497, 0, '', 0, 'behaviorRemoteShieldBoostFalloff', 1 FROM DUAL
UNION ALL SELECT 2498, 0, '', 0, 'behaviorRemoteShieldBoostDischarge', 114 FROM DUAL
UNION ALL SELECT 2499, 0, '', 0, 'behaviorWebifierDuration', 101 FROM DUAL
UNION ALL SELECT 2500, 0, '', 0, 'behaviorWebifierRange', 1 FROM DUAL
UNION ALL SELECT 2501, 0, '', 0, 'behaviorWebifierFalloff', 1 FROM DUAL
UNION ALL SELECT 2502, 0, '', 0, 'behaviorWebifierDischarge', 114 FROM DUAL
UNION ALL SELECT 2503, 0, '', 0, 'behaviorWarpDisruptDuration', 101 FROM DUAL
UNION ALL SELECT 2504, 0, '', 0, 'behaviorWarpDisruptRange', 1 FROM DUAL
UNION ALL SELECT 2505, 0, '', 0, 'behaviorWarpDisruptDischarge', 114 FROM DUAL
UNION ALL SELECT 2506, 0, '', 0, 'behaviorWarpScrambleDuration', 101 FROM DUAL
UNION ALL SELECT 2507, 0, '', 0, 'behaviorWarpScrambleRange', 1 FROM DUAL
UNION ALL SELECT 2508, 0, '', 0, 'behaviorWarpScrambleDischarge', 114 FROM DUAL
UNION ALL SELECT 2509, 0, '', 0, 'behaviorWarpScrambleStrength', 0 FROM DUAL
UNION ALL SELECT 2510, 0, '', 0, 'behaviorWarpDisruptStrength', 0 FROM DUAL
UNION ALL SELECT 2511, 0, '', 0, 'npcGuidanceDisruptorDuration', 101 FROM DUAL
UNION ALL SELECT 2512, 0, '', 0, 'npcGuidanceDisruptorRange', 1 FROM DUAL
UNION ALL SELECT 2513, 0, '', 0, 'npcGuidanceDisruptorFalloff', 1 FROM DUAL
UNION ALL SELECT 2514, 0, '', 0, 'npcGuidanceDisruptorDischarge', 114 FROM DUAL
UNION ALL SELECT 2515, 0, '', 0, 'npcTrackingDisruptorDuration', 101 FROM DUAL
UNION ALL SELECT 2516, 0, '', 0, 'npcTrackingDisruptorRange', 1 FROM DUAL
UNION ALL SELECT 2517, 0, '', 0, 'npcTrackingDisruptorFalloff', 1 FROM DUAL
UNION ALL SELECT 2518, 0, '', 0, 'npcTrackingDisruptorDischarge', 114 FROM DUAL
UNION ALL SELECT 2519, 0, '', 0, 'behaviorEnergyNeutralizerDuration', 101 FROM DUAL
UNION ALL SELECT 2520, 0, '', 0, 'behaviorEnergyNeutralizerRange', 1 FROM DUAL
UNION ALL SELECT 2521, 0, '', 0, 'behaviorEnergyNeutralizerFalloff', 1 FROM DUAL
UNION ALL SELECT 2522, 0, '', 0, 'behaviorEnergyNeutralizerDischarge', 114 FROM DUAL
UNION ALL SELECT 2523, 0, '', 0, 'behaviorTargetPainterDuration', 101 FROM DUAL
UNION ALL SELECT 2524, 0, '', 0, 'behaviorTargetPainterRange', 1 FROM DUAL
UNION ALL SELECT 2525, 0, '', 0, 'behaviorTargetPainterFalloff', 1 FROM DUAL
UNION ALL SELECT 2526, 0, '', 0, 'behaviorTargetPainterDischarge', 114 FROM DUAL
UNION ALL SELECT 2527, 0, '', 0, 'behaviorSensorDampenerDuration', 101 FROM DUAL
UNION ALL SELECT 2528, 0, '', 0, 'behaviorSensorDampenerRange', 1 FROM DUAL
UNION ALL SELECT 2529, 0, '', 0, 'behaviorSensorDampenerFalloff', 1 FROM DUAL
UNION ALL SELECT 2530, 0, '', 0, 'behaviorSensorDampenerDischarge', 114 FROM DUAL
UNION ALL SELECT 2531, 0, '', 0, 'behaviorECMDuration', 101 FROM DUAL
UNION ALL SELECT 2532, 0, '', 0, 'behaviorECMRange', 1 FROM DUAL
UNION ALL SELECT 2533, 0, '', 0, 'behaviorECMFalloff', 1 FROM DUAL
UNION ALL SELECT 2534, 0, '', 0, 'behaviorECMDischarge', 1 FROM DUAL
UNION ALL SELECT 2535, 0, 'Modifier duration', 1392, 'buffDuration', 101 FROM DUAL
UNION ALL SELECT 2536, 0, '', 0, 'warfareBuff4ID', 0 FROM DUAL
UNION ALL SELECT 2537, 0, '', 0, 'warfareBuff4Value', 0 FROM DUAL
UNION ALL SELECT 2538, 1, '', 0, 'attributeEquipmentManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2539, 1, '', 0, 'attributeEquipmentManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2540, 1, '', 0, 'attributeAmmoManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2541, 1, '', 0, 'attributeAmmoManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2542, 1, '', 0, 'attributeDroneManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2543, 1, '', 0, 'attributeDroneManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2544, 1, '', 0, 'attributeBasSmallShipManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2545, 1, '', 0, 'attributeBasSmallShipManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2546, 1, '', 0, 'attributeBasMediumShipManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2547, 1, '', 0, 'attributeBasMediumShipManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2548, 1, '', 0, 'attributeBasLargeShipManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2549, 1, '', 0, 'attributeBasLargeShipManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2550, 1, '', 0, 'attributeAdvSmallShipManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2551, 1, '', 0, 'attributeAdvSmallShipManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2552, 1, '', 0, 'attributeAdvMediumShipManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2553, 1, '', 0, 'attributeAdvMediumShipManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2555, 1, '', 0, 'attributeAdvLargeShipManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2556, 1, '', 0, 'attributeAdvLargeShipManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2557, 1, '', 0, 'attributeAdvCompManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2558, 1, '', 0, 'attributeAdvCompManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2559, 1, '', 0, 'attributeBasCapCompManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2560, 1, '', 0, 'attributeBasCapCompManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2561, 1, '', 0, 'attributeStructureManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2562, 1, '', 0, 'attributeStructureManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2563, 1, '', 0, 'attributeInventionCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 2564, 1, '', 0, 'attributeInventionTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2565, 1, '', 0, 'attributeMEResearchCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 2566, 1, '', 0, 'attributeMEResearchTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2567, 1, '', 0, 'attributeTEResearchCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 2568, 1, '', 0, 'attributeTEResearchTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2569, 1, '', 0, 'attributeBPCopyCostMultiplier', 0 FROM DUAL
UNION ALL SELECT 2570, 1, '', 0, 'attributeBPCopyTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2571, 0, 'Command Burst Strength Bonus', 0, 'commandBurstStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 2572, 0, 'Command Burst Strength Bonus', 0, 'commandStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 2573, 0, 'Reload Speed Bonus', 0, 'reloadTimeBonus', 105 FROM DUAL
UNION ALL SELECT 2574, 0, 'Command Burst Effect Range Bonus', 0, 'roleBonusCommandBurstAoERange', 105 FROM DUAL
UNION ALL SELECT 2575, 1, '', 0, 'attributeCapShipManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2576, 1, '', 0, 'attributeCapShipManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2577, 0, '', 0, 'shipBonusICS5', 105 FROM DUAL
UNION ALL SELECT 2578, 0, '', 0, 'roleBonusDroneMiningYield', 105 FROM DUAL
UNION ALL SELECT 2579, 0, '', 0, 'roleBonusDroneIceHarvestingSpeed', 105 FROM DUAL
UNION ALL SELECT 2580, 0, '', 0, 'industrialBonusDroneDamage', 105 FROM DUAL
UNION ALL SELECT 2581, 2, '', 0, 'onlineMaxSecurityClass', 0 FROM DUAL
UNION ALL SELECT 2582, 0, '', 0, 'shipBonusORECapital5', 0 FROM DUAL
UNION ALL SELECT 2583, 0, 'Drone Damage and Hitpoints Bonus', 0, 'industrialCoreBonusDroneDamageHP', 105 FROM DUAL
UNION ALL SELECT 2584, 0, 'Drone Maximum Velocity Bonus', 0, 'industrialCoreBonusDroneVelocity', 105 FROM DUAL
UNION ALL SELECT 2585, 0, 'Drone Ore Mining Yield Bonus', 0, 'industrialCoreBonusDroneMining', 105 FROM DUAL
UNION ALL SELECT 2586, 0, 'Drone Ice Harvesting Speed Bonus', 0, 'industrialCoreBonusDroneIceHarvesting', 105 FROM DUAL
UNION ALL SELECT 2587, 0, 'Mining Foreman Burst Strength Bonus', 0, 'industrialCoreBonusMiningBurstStrength', 105 FROM DUAL
UNION ALL SELECT 2588, 0, 'Command and Mining Foreman Burst Range Bonus', 0, 'industrialCoreBonusCommandBurstRange', 105 FROM DUAL
UNION ALL SELECT 2589, 1, '', 0, 'modeDamageBonusPostDiv', 0 FROM DUAL
UNION ALL SELECT 2590, 1, '', 0, 'modeEwarResistancePostDiv', 0 FROM DUAL
UNION ALL SELECT 2591, 1, '', 0, 'attributeAllShipsManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2592, 1, '', 0, 'attributeAllShipsManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2593, 1, 'Time Reduction Bonus', 0, 'attributeEngRigTimeBonus', 105 FROM DUAL
UNION ALL SELECT 2594, 0, 'Material Reduction Bonus', 0, 'attributeEngRigMatBonus', 105 FROM DUAL
UNION ALL SELECT 2595, 1, 'Cost Reduction Bonus', 0, 'attributeEngRigCostBonus', 105 FROM DUAL
UNION ALL SELECT 2596, 0, '', 0, 'warfareBuff1Multiplier', 0 FROM DUAL
UNION ALL SELECT 2597, 0, '', 0, 'warfareBuff2Multiplier', 0 FROM DUAL
UNION ALL SELECT 2598, 0, '', 0, 'warfareBuff3Multiplier', 0 FROM DUAL
UNION ALL SELECT 2599, 0, '', 0, 'warfareBuff4Multiplier', 0 FROM DUAL
UNION ALL SELECT 2600, 1, '', 0, 'strEngMatBonus', 0 FROM DUAL
UNION ALL SELECT 2601, 1, '', 0, 'strEngCostBonus', 0 FROM DUAL
UNION ALL SELECT 2602, 1, '', 0, 'strEngTimeBonus', 0 FROM DUAL
UNION ALL SELECT 2603, 0, 'Maximum Velocity Bonus', 1389, 'maxVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 2604, 0, 'Capital Remote Shield Booster Range Bonus', 1391, 'industrialCoreRemoteLogisticsRangeBonus', 105 FROM DUAL
UNION ALL SELECT 2605, 0, 'Capital Remote Shield Booster Duration and Capacitor Use Bonus', 1392, 'industrialCoreRemoteLogisticsDurationBonus', 105 FROM DUAL
UNION ALL SELECT 2606, 0, 'Shield Booster Duration Bonus', 0, 'industrialCoreLocalLogisticsDurationBonus', 105 FROM DUAL
UNION ALL SELECT 2607, 0, 'Shield Booster Amount Bonus', 0, 'industrialCoreLocalLogisticsAmountBonus', 105 FROM DUAL
UNION ALL SELECT 2608, 0, 'Minimum Velocity Limitation', 1389, 'minVelocityActivationLimit', 10 FROM DUAL
UNION ALL SELECT 2609, 0, '', 0, 'doomsdayEnergyNeutResistanceID', 119 FROM DUAL
UNION ALL SELECT 2610, 0, '', 0, 'pilotSecurityStatus', 0 FROM DUAL
UNION ALL SELECT 2612, 1.2999999523162842, '', 0, 'AI_TankingModifierFighter', 0 FROM DUAL
UNION ALL SELECT 2613, 1, '', 0, 'chargeRateMultiplier', 0 FROM DUAL
UNION ALL SELECT 2614, 0, '', 0, 'behaviorMicroWarpDriveDischarge', 114 FROM DUAL
UNION ALL SELECT 2615, 0, '', 0, 'behaviorMicroWarpDriveDuration', 101 FROM DUAL
UNION ALL SELECT 2616, 0, '', 0, 'behaviorMicroWarpDriveMassAddition', 2 FROM DUAL
UNION ALL SELECT 2617, 0, '', 0, 'behaviorMicroWarpDriveSignatureRadiusBonus', 124 FROM DUAL
UNION ALL SELECT 2618, 0, '', 0, 'behaviorMicroWarpDriveSpeedFactor', 124 FROM DUAL
UNION ALL SELECT 2619, 0, '', 0, 'behaviorMicroWarpDriveSpeedBoostFactor', 125 FROM DUAL
UNION ALL SELECT 2620, 0, '', 0, 'concordRoleBonusSecGain', 0 FROM DUAL
UNION ALL SELECT 2621, 0, '', 0, 'inverseCappedSecStatus', 0 FROM DUAL
UNION ALL SELECT 2622, 0, '', 0, 'concordTankBonus', 0 FROM DUAL
UNION ALL SELECT 2623, 0, '', 0, 'constantZero', 0 FROM DUAL
UNION ALL SELECT 2624, 50, '', 0, 'constantFifty', 0 FROM DUAL
UNION ALL SELECT 2627, 0, '', 0, 'eliteBonusBlackOps3', 0 FROM DUAL
UNION ALL SELECT 2628, 0, '', 0, 'eliteBonusBlackOps4', 0 FROM DUAL
UNION ALL SELECT 2629, 0, '', 0, 'behaviorEnergyNosferatuDischarge', 114 FROM DUAL
UNION ALL SELECT 2630, 0, '', 0, 'behaviorEnergyNosferatuDuration', 101 FROM DUAL
UNION ALL SELECT 2631, 0, '', 0, 'behaviorEnergyNosferatuFalloff', 1 FROM DUAL
UNION ALL SELECT 2632, 0, '', 0, 'behaviorEnergyNosferatuRange', 1 FROM DUAL
UNION ALL SELECT 2633, 0, '', 0, 'behaviorArmorRepairerDuration', 101 FROM DUAL
UNION ALL SELECT 2634, 0, '', 0, 'behaviorArmorRepairerDischarge', 114 FROM DUAL
UNION ALL SELECT 2635, 0, '', 0, 'behaviorArmorRepairerAmount', 113 FROM DUAL
UNION ALL SELECT 2636, 0, '', 0, 'BehaviorSiegeDuration', 101 FROM DUAL
UNION ALL SELECT 2637, 0, '', 0, 'BehaviorSiegeDischarge', 114 FROM DUAL
UNION ALL SELECT 2638, 0, '', 0, 'BehaviorSiegeRemoteRepairImpedanceModifier', 0 FROM DUAL
UNION ALL SELECT 2639, 0, '', 0, 'BehaviorSiegeRemoteAssistanceImpedanceModifier', 0 FROM DUAL
UNION ALL SELECT 2640, 0, '', 0, 'BehaviorSiegeSensorDampenerResistanceModifier', 0 FROM DUAL
UNION ALL SELECT 2641, 0, '', 0, 'BehaviorSiegeWeaponDisruptionResistanceModifier', 0 FROM DUAL
UNION ALL SELECT 2642, 0, '', 0, 'BehaviorSiegeECMResistanceModifier', 0 FROM DUAL
UNION ALL SELECT 2643, 0, '', 0, 'BehaviorSiegeMaxVelocityModifier', 0 FROM DUAL
UNION ALL SELECT 2644, 0, '', 0, 'BehaviorSiegeWarpScrambleStatusModifier', 0 FROM DUAL
UNION ALL SELECT 2645, 0, '', 0, 'BehaviorSiegeDisallowTetheringModifier', 0 FROM DUAL
UNION ALL SELECT 2646, 0, '', 0, 'BehaviorSiegeMassModifier', 0 FROM DUAL
UNION ALL SELECT 2647, 0, '', 0, 'BehaviorSiegeLocalLogisticsAmountModifier', 0 FROM DUAL
UNION ALL SELECT 2648, 0, '', 0, 'BehaviorSiegeLocalLogisticsDurationModifier', 0 FROM DUAL
UNION ALL SELECT 2649, 0, '', 0, 'BehaviorSiegeTurretDamageModifier', 0 FROM DUAL
UNION ALL SELECT 2653, 0, 'Thukker Enhanced Capital Component Material Reduction Bonus', 0, 'attributeThukkerEngRigMatBonus', 105 FROM DUAL
UNION ALL SELECT 2654, 0, '', 0, 'gfxTurretCount', 0 FROM DUAL
UNION ALL SELECT 2655, 0, '', 0, 'gfxLauncherID', 0 FROM DUAL
UNION ALL SELECT 2656, 0, '', 0, 'gfxLauncherCount', 0 FROM DUAL
UNION ALL SELECT 2657, 0, 'Booster Hold Capacity', 71, 'specialBoosterHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 2658, 1, '', 0, 'attributeAdvCapCompManufactureMaterialMultiplier', 0 FROM DUAL
UNION ALL SELECT 2659, 1, '', 0, 'attributeAdvCapCompManufactureTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2660, 0, 'Reaction Time Bonus', 1392, 'reactionTimeBonus', 105 FROM DUAL
UNION ALL SELECT 2661, 0, 'Reaction Slot Bonus', 0, 'reactionSlotBonus', 139 FROM DUAL
UNION ALL SELECT 2662, 1, '', 0, 'reactionTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2664, 0, '', 0, 'reactionSlotLimit', 0 FROM DUAL
UNION ALL SELECT 2665, 0, 'Nosferatu and Neutralizer fitting reduction', 0, 'subsystemEnergyNeutFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2666, 0, 'Medium Hybrid Turret fitting reduction', 0, 'subsystemMHTFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2667, 0, 'Medium Projectile Turret fitting reduction', 0, 'subsystemMPTFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2668, 0, 'Medium Energy Turret fitting reduction', 0, 'subsystemMETFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2669, 0, 'Medium Missile Launcher fitting reduction', 0, 'subsystemMMissileFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2670, 0, 'Medium Remote Shield Booster fitting reduction', 0, 'subsystemMRSBFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2671, 0, 'Medium Remote Armor Repairer fitting reduction', 0, 'subsystemMRARFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2673, 0, '', 0, 'behaviorMiningMaxRange', 1 FROM DUAL
UNION ALL SELECT 2674, 0, '', 0, 'behaviorMiningDischarge', 114 FROM DUAL
UNION ALL SELECT 2675, 0, 'Subsystem Hold Capacity', 71, 'specialSubsystemHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 2676, 0, '', 0, 'shipBonusStrategicCruiserCaldari2', 0 FROM DUAL
UNION ALL SELECT 2677, 0, '', 0, 'shipBonusStrategicCruiserAmarr2', 0 FROM DUAL
UNION ALL SELECT 2678, 0, '', 0, 'shipBonusStrategicCruiserGallente2', 0 FROM DUAL
UNION ALL SELECT 2679, 0, '', 0, 'shipBonusStrategicCruiserMinmatar2', 0 FROM DUAL
UNION ALL SELECT 2680, 0, '', 0, 'subsystemBonusAmarrDefensive3', 0 FROM DUAL
UNION ALL SELECT 2681, 0, '', 0, 'subsystemBonusAmarrCore3', 0 FROM DUAL
UNION ALL SELECT 2682, 0, '', 0, 'subsystemBonusCaldariDefensive3', 0 FROM DUAL
UNION ALL SELECT 2683, 0, '', 0, 'subsystemBonusCaldariCore3', 0 FROM DUAL
UNION ALL SELECT 2684, 0, '', 0, 'subsystemBonusGallenteDefensive3', 0 FROM DUAL
UNION ALL SELECT 2685, 0, '', 0, 'subsystemBonusGallenteCore3', 0 FROM DUAL
UNION ALL SELECT 2686, 0, '', 0, 'subsystemBonusMinmatarDefensive3', 0 FROM DUAL
UNION ALL SELECT 2687, 0, '', 0, 'subsystemBonusMinmatarCore3', 0 FROM DUAL
UNION ALL SELECT 2688, 0, 'Structure Hitpoint Bonus', 0, 'structureHPBonusAdd', 113 FROM DUAL
UNION ALL SELECT 2689, 0, 'Cargo Capacity Bonus', 71, 'cargoCapacityAdd', 9 FROM DUAL
UNION ALL SELECT 2690, 0, 'Additional Inertia Modifier', 0, 'agilityBonusAdd', 0 FROM DUAL
UNION ALL SELECT 2691, 1, '', 0, 'mediumRemoteRepFittingMultiplier', 0 FROM DUAL
UNION ALL SELECT 2692, 0, 'Command Burst fitting reduction', 0, 'subsystemCommandBurstFittingReduction', 105 FROM DUAL
UNION ALL SELECT 2693, 0, 'Remote Shield Booster Falloff Bonus', 0, 'remoteShieldBoosterFalloffBonus', 105 FROM DUAL
UNION ALL SELECT 2694, 0, 'Remote Armor Repairer Falloff Bonus', 0, 'remoteArmorRepairerFalloffBonus', 105 FROM DUAL
UNION ALL SELECT 2695, 0, 'Remote Armor Repairer Optimal Range Bonus', 0, 'remoteArmorRepairerOptimalBonus', 105 FROM DUAL
UNION ALL SELECT 2696, 0, 'Module or subsystem is obsolete', 0, 'moduleIsObsolete', 0 FROM DUAL
UNION ALL SELECT 2697, 0, 'Maximum Scan Range', 0, 'maxScanRange', 1 FROM DUAL
UNION ALL SELECT 2698, 10800, '', 0, 'autoFractureDelay', 3 FROM DUAL
UNION ALL SELECT 2699, 0, '', 0, 'asteroidMetaLevel', 0 FROM DUAL
UNION ALL SELECT 2700, 200000, 'Maximum Auto-Targeting Range', 1391, 'maxFOFTargetRange', 1 FROM DUAL
UNION ALL SELECT 2701, 0, 'Reduction to Survey Probe Scan Time', 0, 'surveyProbeDurationBonus', 105 FROM DUAL
UNION ALL SELECT 2704, 1, 'Extraction Yield Multiplier', 0, 'moonYieldMultiplier', 104 FROM DUAL
UNION ALL SELECT 2705, 1, 'Moon Asteroid Field Radius Multiplier', 0, 'moonAsteroidFieldRadius', 104 FROM DUAL
UNION ALL SELECT 2706, 1, 'Moon Asteroid Decay Time', 0, 'moonAsteroidDecayTimeMultiplier', 3 FROM DUAL
UNION ALL SELECT 2707, 0, 'Chunk Stability Bonus', 0, 'moonRigFractureDelayBonus', 105 FROM DUAL
UNION ALL SELECT 2708, 0, 'Extracted Asteroid Decay Bonus', 0, 'moonRigAsteroidDecayBonus', 105 FROM DUAL
UNION ALL SELECT 2709, 0, 'Moon Asteroid Belt Radius Bonus', 0, 'moonRigSpewRadiusBonus', 105 FROM DUAL
UNION ALL SELECT 2710, 0, 'Moon Extraction Volume Bonus', 0, 'moonRigSpewVolumeBonus', 105 FROM DUAL
UNION ALL SELECT 2711, 0, '', 0, 'oreBasicType', 116 FROM DUAL
UNION ALL SELECT 2713, 0, 'Time Bonus', 0, 'RefRigTimeBonus', 105 FROM DUAL
UNION ALL SELECT 2714, 0, 'Material Reduction Bonus', 0, 'RefRigMatBonus', 105 FROM DUAL
UNION ALL SELECT 2715, 1, '', 0, 'reactionHybTimeMultiplier', 104 FROM DUAL
UNION ALL SELECT 2716, 1, '', 0, 'reactionHybMatMultiplier', 104 FROM DUAL
UNION ALL SELECT 2717, 1, '', 0, 'reactionCompTimeMultiplier', 104 FROM DUAL
UNION ALL SELECT 2718, 1, '', 0, 'reactionCompMatMultiplier', 104 FROM DUAL
UNION ALL SELECT 2719, 1, '', 0, 'reactionBioTimeMultiplier', 104 FROM DUAL
UNION ALL SELECT 2720, 1, '', 0, 'reactionBioMatMultiplier', 104 FROM DUAL
UNION ALL SELECT 2721, 1, '', 0, 'strReactionTimeMultiplier', 0 FROM DUAL
UNION ALL SELECT 2722, 0, '', 0, 'strRefiningYieldBonus', 0 FROM DUAL
UNION ALL SELECT 2723, 0, '', 0, 'BehaviorShieldBoosterAmount', 113 FROM DUAL
UNION ALL SELECT 2724, 0, '', 0, 'BehaviorShieldBoosterDischarge', 114 FROM DUAL
UNION ALL SELECT 2725, 0, '', 0, 'BehaviorShieldBoosterDuration', 101 FROM DUAL
UNION ALL SELECT 2727, 16255, '', 0, 'asteroidMaxRadius ', 9 FROM DUAL
UNION ALL SELECT 2728, 48, 'Approximate lifetime of spawned asteroids', 0, 'moonAsteroidDecayDisplayValue', 129 FROM DUAL
UNION ALL SELECT 2729, 0, '', 0, 'nextActivationTime', 123 FROM DUAL
UNION ALL SELECT 2730, 0, '', 0, 'BehaviorSiegeMissileDamageModifier', 105 FROM DUAL
UNION ALL SELECT 2731, 0, '', 0, 'eliteBonusCovertOps4', 0 FROM DUAL
UNION ALL SELECT 2732, 1, '', 0, 'stealthBomberLauncherCPU', 0 FROM DUAL
UNION ALL SELECT 2733, 0, 'Damage Multiplier Bonus Per Cycle', 1432, 'damageMultiplierBonusPerCycle', 127 FROM DUAL
UNION ALL SELECT 2734, 0.5, 'Maximum Damage Multiplier Bonus', 1432, 'damageMultiplierBonusMax', 127 FROM DUAL
UNION ALL SELECT 2735, 1, '', 0, 'npcStructureStasisWebificationBonus', 104 FROM DUAL
UNION ALL SELECT 2736, 1, '', 0, 'npcStructureEnergyWarfareBonus', 104 FROM DUAL
UNION ALL SELECT 2737, 0, 'Standup Light Fighter Squadron Limit', 2987, 'fighterStandupLightSlots', 0 FROM DUAL
UNION ALL SELECT 2738, 0, 'Standup Support Fighter Squadron Limit', 2987, 'fighterStandupSupportSlots', 0 FROM DUAL
UNION ALL SELECT 2739, 0, 'Standup Heavy Fighter Squadrons Limit', 2987, 'fighterStandupHeavySlots', 0 FROM DUAL
UNION ALL SELECT 2740, 0, '', 0, 'fighterSquadronIsStandupLight', 0 FROM DUAL
UNION ALL SELECT 2741, 0, '', 0, 'fighterSquadronIsStandupSupport', 0 FROM DUAL
UNION ALL SELECT 2742, 0, '', 0, 'fighterSquadronIsStandupHeavy', 0 FROM DUAL
UNION ALL SELECT 2743, 1, '', 0, 'structureFullPowerStateHitpointMultiplier', 0 FROM DUAL
UNION ALL SELECT 2744, 1, 'Full Power Mode Shield and Armor Hitpoint Multiplier', 0, 'serviceModuleFullPowerStateHitpointMultiplier', 104 FROM DUAL
UNION ALL SELECT 2745, 0, 'Duration', 1392, 'durationTargetWarpableBeacon', 101 FROM DUAL
UNION ALL SELECT 2746, 0, 'Activated Damage Resistance', 0, 'resistanceMultiplier', 108 FROM DUAL
UNION ALL SELECT 2747, 0, 'Stasis Webifier Maximum Range Bonus', 1391, 'stasisWebRangeBonus', 121 FROM DUAL
UNION ALL SELECT 2748, 0, 'Maximum Targeting Range Bonus', 1391, 'structureRigMaxTargetRangeBonus', 105 FROM DUAL
UNION ALL SELECT 2749, 0, 'Bonus to the Rate of Fire of Guided Bomb Launchers and Burst Projectors', 1397, 'structureAoERoFRoleBonus', 105 FROM DUAL
UNION ALL SELECT 2750, 1, '', 0, 'hiddenMissileDamageMultiplier', 104 FROM DUAL
UNION ALL SELECT 2751, 1, '', 0, 'hiddenArmorHPMultiplier', 104 FROM DUAL
UNION ALL SELECT 2752, 0, '', 0, 'eliteBonusFlagCruisers1', 0 FROM DUAL
UNION ALL SELECT 2753, 0, 'Reduction in AB, MWD, MJD fitting requirements', 70, 'flagCruiserFittingBonusPropMods', 105 FROM DUAL
UNION ALL SELECT 2754, 1, 'Entosis Assistance Impedance', 0, 'entosisAssistanceImpedanceMultiplier', 108 FROM DUAL
UNION ALL SELECT 2755, 0, 'Modification of Target Painter strength', 1390, 'targetPainterStrengthModifierFlagCruisers', 105 FROM DUAL
UNION ALL SELECT 2756, 0, 'Bonus to Target Painter optimal range', 1390, 'targetPainterRangeModifierFlagCruisers', 105 FROM DUAL
UNION ALL SELECT 2757, 0, 'Reduction in Target Painter and Scan Probe Launcher fitting requirements', 70, 'flagCruiserFittingBonusPainterProbes', 105 FROM DUAL
UNION ALL SELECT 2758, 0, 'Can be fitted to', 1443, 'canFitShipType11', 116 FROM DUAL
UNION ALL SELECT 2759, -1, '', 0, 'typeListId', 0 FROM DUAL
UNION ALL SELECT 2760, 0, 'Abyssal Environment', 0, 'weatherID', 116 FROM DUAL
UNION ALL SELECT 2761, 1, 'Difficulty Tier', 2893, 'difficultyTier', 0 FROM DUAL
UNION ALL SELECT 2762, 0, 'Special Ability Bonus', 0, 'shipBonusPF1', 105 FROM DUAL
UNION ALL SELECT 2763, 0, 'Special Ability Bonus', 0, 'shipBonusPF2', 105 FROM DUAL
UNION ALL SELECT 2764, 0, 'Special Ability Bonus', 0, 'shipBonusPC1', 105 FROM DUAL
UNION ALL SELECT 2765, 0, 'Special Ability Bonus', 0, 'shipBonusPC2', 105 FROM DUAL
UNION ALL SELECT 2766, 0, 'Special Ability Bonus', 0, 'shipBonusPBS1', 105 FROM DUAL
UNION ALL SELECT 2767, 0, 'Special Ability Bonus', 0, 'shipBonusPBS2', 105 FROM DUAL
UNION ALL SELECT 2768, 1, '', 0, 'emDamageResonanceMax', 0 FROM DUAL
UNION ALL SELECT 2769, 1, '', 0, 'thermalDamageResonanceMax', 0 FROM DUAL
UNION ALL SELECT 2770, 1, '', 0, 'kineticDamageResonanceMax', 0 FROM DUAL
UNION ALL SELECT 2771, 1, '', 0, 'explosiveDamageResonanceMax', 0 FROM DUAL
UNION ALL SELECT 2772, 0, 'Bonus to all hitpoints and capacitor capacity', 0, 'conversionRigHPCapBonus', 105 FROM DUAL
UNION ALL SELECT 2773, 0, 'Bonus to T1 Ship Manufacturing Job Time Requirements', 1392, 'attributeT1ShipManufactureTime', 105 FROM DUAL
UNION ALL SELECT 2774, 0, 'Bonus to T2 Ship Manufacturing Job Time Requirements', 1392, 'attributeT2ShipManufactureTime', 105 FROM DUAL
UNION ALL SELECT 2775, 0, 'Bonus to Advanced Component Manufacturing Job Time Requirements', 1392, 'attributeAdvCompManufactureTime', 105 FROM DUAL
UNION ALL SELECT 2776, 0, 'Bonus to Capital Component Manufacturing Job Time Requirements', 1392, 'attributeCapCompManufactureTime', 105 FROM DUAL
UNION ALL SELECT 2777, 0, 'Bonus to Equipment Manufacturing Job Time Requirements', 1392, 'attributeEquipmentManufactureTime', 105 FROM DUAL
UNION ALL SELECT 2778, 0, 'Bonus to ME Research Job Time Requirements', 1392, 'attributeMEResearchTime', 105 FROM DUAL
UNION ALL SELECT 2779, 0, 'Bonus to TE Research Job Time Requirements', 1392, 'attributeTEResearchTime', 105 FROM DUAL
UNION ALL SELECT 2780, 0, 'Bonus to Blueprint Copy Job Time Requirements', 1392, 'attributeCopyTime', 105 FROM DUAL
UNION ALL SELECT 2781, 0, 'Bonus to Invention Job Time Requirements', 1392, 'attributeInventionTime', 105 FROM DUAL
UNION ALL SELECT 2782, 0, 'Reduction in ME, TE, and Copy Job ISK Costs', 1392, 'attributeResearchCosts', 105 FROM DUAL
UNION ALL SELECT 2783, 0, 'Reduction in Invention Job ISK Costs', 1392, 'attributeInventionCosts', 105 FROM DUAL
UNION ALL SELECT 2784, 0, '', 0, 'npcDroneCapacity', 0 FROM DUAL
UNION ALL SELECT 2785, 0, '', 0, 'npcDroneBandwidth', 0 FROM DUAL
UNION ALL SELECT 2786, 0, '', 0, 'npcBehaviorMaximumCombatOrbitRange', 0 FROM DUAL
UNION ALL SELECT 2787, 0, '', 0, 'monumentAllianceID', 0 FROM DUAL
UNION ALL SELECT 2788, 0, 'Duration', 1392, 'panicDuration', 101 FROM DUAL
UNION ALL SELECT 2789, 0, 'Warp Speed and Acceleration Bonus', 0, 'shipRoleBonusWarpSpeed', 105 FROM DUAL
UNION ALL SELECT 2790, 0, 'Maximum cargo deposit range', 0, 'cargoDeliveryRange', 1 FROM DUAL
UNION ALL SELECT 2791, 0, 'Cannot be Unfit', 0, 'cannotBeUnfit', 137 FROM DUAL
UNION ALL SELECT 2792, 0, '', 0, 'preFitServiceSlot0', 0 FROM DUAL
UNION ALL SELECT 2793, 0, 'Added Jump Portal Fuel Consumption', 0, 'jumpPortalAdditionalConsumption', 138 FROM DUAL
UNION ALL SELECT 2794, 0, 'Activation Delay', 0, 'cynoJammerActivationDelay', 3 FROM DUAL
UNION ALL SELECT 2795, 0, 'Activation Delay', 0, 'cynoJammerActivationDelay', 101 FROM DUAL
UNION ALL SELECT 2796, 0, 'Repair Multiplier Bonus Per Cycle', 80, 'repairMultiplierBonusPerCycle', 127 FROM DUAL
UNION ALL SELECT 2797, 0, 'Maximum Repair Multiplier Bonus', 80, 'repairMultiplierBonusMax', 127 FROM DUAL
UNION ALL SELECT 2798, 0, 'Maximum Jump Mass', 0, 'gateMaxJumpMass', 2 FROM DUAL
UNION ALL SELECT 2799, 0, '', 0, 'shipBonusPD1', 105 FROM DUAL
UNION ALL SELECT 2800, 0, '', 0, 'shipBonusPD2', 105 FROM DUAL
UNION ALL SELECT 2801, 0, '', 0, 'shipBonusPBC1', 105 FROM DUAL
UNION ALL SELECT 2802, 0, '', 0, 'shipBonusPBC2', 105 FROM DUAL
UNION ALL SELECT 2803, 0, 'Mining amount', 0, 'miningAmountSet', 9 FROM DUAL
UNION ALL SELECT 2804, 0, '', 0, 'serviceModuleFullPowerStateArmorPlatingMultiplier', 0 FROM DUAL
UNION ALL SELECT 2805, 0, '', 0, 'structurePowerStateArmorPlatingMultiplier', 0 FROM DUAL
UNION ALL SELECT 2806, 1, 'Penaltyless Skill Injections Allowed', 0, 'NonDiminishingSkillInjectorUses', 0 FROM DUAL
UNION ALL SELECT 2807, 0, 'Reactivation Delay Reduction', 1392, 'reactivationDelayBonus', 105 FROM DUAL
UNION ALL SELECT 2808, 0, '', 0, 'TotalArmorRepairOnTarget', 0 FROM DUAL
UNION ALL SELECT 2809, 0, '', 0, 'TotalShieldRepairOnTarget', 0 FROM DUAL
UNION ALL SELECT 2810, 0, '', 0, 'TotalHullRepairOnTarget', 0 FROM DUAL
UNION ALL SELECT 2811, 0, '', 0, 'TotalCapTransferOnTarget', 0 FROM DUAL
UNION ALL SELECT 2812, 0, '', 0, 'behaviorSmartBombDuration', 101 FROM DUAL
UNION ALL SELECT 2814, 0, '', 0, 'behaviorSmartBombDischarge', 114 FROM DUAL
UNION ALL SELECT 2815, 0, '', 0, 'behaviorMicroJumpAttackDischarge', 114 FROM DUAL
UNION ALL SELECT 2816, 0, '', 0, 'behaviorMicroJumpAttackRange', 1 FROM DUAL
UNION ALL SELECT 2818, 0, '', 0, 'behaviorMicroJumpAttackJumpDistance', 1 FROM DUAL
UNION ALL SELECT 2819, 0, '', 0, 'behaviorMicroJumpAttackDuration', 101 FROM DUAL
UNION ALL SELECT 2820, 0, 'High Angle Turret Damage Bonus', 1432, 'siegeHAWTurretDamageBonus', 104 FROM DUAL
UNION ALL SELECT 2821, 0, 'Rapid Torpedo Launcher Bonus', 1389, 'siegeHAWMissileROFBonus', 105 FROM DUAL
UNION ALL SELECT 2822, 0, 'Jam Duration', 0, 'ecmJamDuration', 101 FROM DUAL
UNION ALL SELECT 2823, 1, 'Maximum Damage Bonus Multiplier Modifier', 0, 'damageMultiplierBonusMaxModifier', 105 FROM DUAL
UNION ALL SELECT 2824, 1, 'Damage Multiplier Bonus Per Cycle Modifier', 0, 'damageMultiplierBonusPerCycleModifier', 105 FROM DUAL
UNION ALL SELECT 2825, 1, 'Implant Set Bonus', 0, 'setBonusMimesis', 104 FROM DUAL
UNION ALL SELECT 2826, 0, 'Uses Industrial Cynosural Field Technology', 0, 'isIndustrialCyno', 137 FROM DUAL
UNION ALL SELECT 2827, 0, '', 0, 'hasLongAnimationWhenAddedToSpaceScene', 137 FROM DUAL
UNION ALL SELECT 2828, 0, 'Special Ability Bonus', 0, 'shipBonusPDread1', 105 FROM DUAL
UNION ALL SELECT 2829, 0, '', 0, 'shipBonusDreadnoughtPC2', 0 FROM DUAL
UNION ALL SELECT 2830, 0, '', 0, 'shipBonusDreadnoughtPC1', 0 FROM DUAL
UNION ALL SELECT 2831, 0, '', 0, 'shipBonusDreadnoughtPC3', 0 FROM DUAL
UNION ALL SELECT 2832, 1, 'Maximum Ship Jump cap', 1391, 'mjdShipJumpCap', 0 FROM DUAL
UNION ALL SELECT 3015, 1, 'Shield Hitpoint Bonus', 1384, 'shieldHpBonus', 105 FROM DUAL
UNION ALL SELECT 3017, 1, 'Nirvana Set Bonus', 0, 'ImplantSetNirvana', 104 FROM DUAL
UNION ALL SELECT 3020, 0, 'Frigate Escape Bay', 1443, 'frigateEscapeBayCapacity', 138 FROM DUAL
UNION ALL SELECT 3023, 1, 'Savior Set Bonus', 0, 'implantSetSavior', 104 FROM DUAL
UNION ALL SELECT 3024, 0, 'Remote Rep Cycle Time Bonus', 1392, 'remoteRepDurationBonus', 105 FROM DUAL
UNION ALL SELECT 3025, 0, '', 0, 'ActiveSystemJump', 0 FROM DUAL
UNION ALL SELECT 3026, 561098, '', 0, 'FilamentDescriptionMessageID', 0 FROM DUAL
UNION ALL SELECT 3027, 1, 'Hydra Set Bonus', 0, 'implantSetHydra', 104 FROM DUAL
UNION ALL SELECT 3028, 0, 'Drone Tracking Speed Bonus', 1398, 'hydraDroneTrackingBonus', 105 FROM DUAL
UNION ALL SELECT 3029, 0, 'Drone Optimal and Falloff Range Bonus', 1391, 'hydraDroneRangeBonus', 105 FROM DUAL
UNION ALL SELECT 3030, 0, 'Missile Flight Time Bonus', 1392, 'hydraMissileFlightTimeBonus', 105 FROM DUAL
UNION ALL SELECT 3031, 0, 'Missile Explosion Velocity Bonus', 1389, 'hydraMissileExplosionVelocityBonus', 105 FROM DUAL
UNION ALL SELECT 3034, 0, '', 1004, 'maxLockedTargetsMultiplier', 0 FROM DUAL
UNION ALL SELECT 3035, 0, 'Warp Scramble Strength Bonus', 111, 'warpScrambleStrengthBonus', 139 FROM DUAL
UNION ALL SELECT 3036, 1, 'Vorton Arc Range', 24252, 'VortonArcRange', 1 FROM DUAL
UNION ALL SELECT 3037, 1, 'Arc Chain Targets', 24252, 'VortonArcTargets', 0 FROM DUAL
UNION ALL SELECT 3038, 0, '', 0, 'isCynoJammer', 0 FROM DUAL
UNION ALL SELECT 3039, 1, '', 0, 'behaviorSmartBombEntityDamageMultiplier', 0 FROM DUAL
UNION ALL SELECT 3041, 1, '', 0, 'shipBonusUF1', 0 FROM DUAL
UNION ALL SELECT 3042, 1, '', 0, 'shipBonusUF2', 0 FROM DUAL
UNION ALL SELECT 3043, 1, '', 0, 'shipBonusUC1', 0 FROM DUAL
UNION ALL SELECT 3044, 1, '', 0, 'shipBonusUC2', 0 FROM DUAL
UNION ALL SELECT 3045, 1, '', 0, 'shipBonusUB1', 0 FROM DUAL
UNION ALL SELECT 3046, 1, '', 0, 'shipBonusUB2', 0 FROM DUAL
UNION ALL SELECT 3050, 2, '', 0, 'AmountOfFleetsPerMatch', 0 FROM DUAL
UNION ALL SELECT 3051, 1000, 'Area Effect Radius', 1391, 'FleetMemberPickupRadius', 1 FROM DUAL
UNION ALL SELECT 3052, 1, 'Number Of Ships required', 1391, 'FleetMembersNeeded', 0 FROM DUAL
UNION ALL SELECT 3093, 1, '', 0, 'showSystemInfoBubble', 0 FROM DUAL
UNION ALL SELECT 3095, 0, 'Probe Strength Bonus', 0, 'scanProbeStrengthBonus', 105 FROM DUAL
UNION ALL SELECT 3096, 0, '', 0, 'locationListID', 0 FROM DUAL
UNION ALL SELECT 3097, 1, '', 0, 'lightYearDistanceMax', 0 FROM DUAL
UNION ALL SELECT 3098, 0, '', 0, 'armorRepairDurationBonus', 0 FROM DUAL
UNION ALL SELECT 3099, 0, '', 0, 'shieldBoosterDurationBonus', 0 FROM DUAL
UNION ALL SELECT 3101, 0, 'Quantum Core Type', 0, 'structureRequiresDeedType', 116 FROM DUAL
UNION ALL SELECT 3102, 0, '', 0, 'onlyTractorCorpses', 0 FROM DUAL
UNION ALL SELECT 3104, 0, 'Consumption Quantity', 0, 'reclonerFuelQuantity', 138 FROM DUAL
UNION ALL SELECT 3105, 0, 'Recloner Consumption Type', 0, 'reclonerFuelType', 116 FROM DUAL
UNION ALL SELECT 3107, 1, 'Rapture Set Bonus', 0, 'ImplantSetRapture', 104 FROM DUAL
UNION ALL SELECT 3108, 0, 'Missile Rate of Fire Bonus', 1397, 'bastionMissileROFBonus', 105 FROM DUAL
UNION ALL SELECT 3109, 0, 'Turret Rate of Fire Bonus', 1397, 'bastionTurretROFBonus', 105 FROM DUAL
UNION ALL SELECT 3110, 0, 'Access Difficulty Bonus', 0, 'specAccessDifficultyBonus', 121 FROM DUAL
UNION ALL SELECT 3113, 0, 'Signature Radius Bonus', 1390, 'signatureSuppressorSignatureRadiusBonusPassive', 108 FROM DUAL
UNION ALL SELECT 3114, 0, 'Active Signature Radius Bonus', 1390, 'signatureSuppressorSignatureRadiusBonusActive', 108 FROM DUAL
UNION ALL SELECT 3115, 0, 'Activation time / duration', 1392, 'durationHighisGood', 101 FROM DUAL
UNION ALL SELECT 3117, 0, '', 0, 'cloakStabilizationStrength', 137 FROM DUAL
UNION ALL SELECT 3118, 0, 'Stabilize Cloak Duration', 0, 'stabilizeCloakDuration', 3 FROM DUAL
UNION ALL SELECT 3120, 0, '', 0, 'warpBubbleImmuneBonus', 0 FROM DUAL
UNION ALL SELECT 3123, 0, 'Allows activation whilst cloaked from a stargate jump', 2106, 'canActivateInGateCloak', 137 FROM DUAL
UNION ALL SELECT 3124, 0, 'Drone Bandwidth Penalty', 2987, 'droneBandwidthPercentage', 105 FROM DUAL
UNION ALL SELECT 3125, 0, '', 0, 'enableOpenJumpPortal', 0 FROM DUAL
UNION ALL SELECT 3126, 0, '', 0, 'enablePerformConduitJump', 0 FROM DUAL
UNION ALL SELECT 3130, 0, 'Group Jump Fuel Need', 0, 'groupJumpConsumptionType', 116 FROM DUAL
UNION ALL SELECT 3131, 0, 'Conduit Jump Consumption Amount', 0, 'conduitJumpDriveConsumptionAmount', 138 FROM DUAL
UNION ALL SELECT 3132, 0, '', 0, 'monumentCorporationID', 0 FROM DUAL
UNION ALL SELECT 3133, 0, 'Conduit Jump Passenger Count', 0, 'conduitJumpPassengerCount', 0 FROM DUAL
UNION ALL SELECT 3134, 0, 'Stabilized Cloak Duration Bonus', 1392, 'stabilizeCloakDurationBonus', 105 FROM DUAL
UNION ALL SELECT 3136, 0, 'Ice Hold Capacity', 71, 'specialIceHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 3148, 0, 'Valid target types', 0, 'specializationAsteroidTypeList', 0 FROM DUAL
UNION ALL SELECT 3153, 1, 'Residue Volume Multiplier', 0, 'miningWastedVolumeMultiplier', 104 FROM DUAL
UNION ALL SELECT 3154, 0, 'Residue Probability', 0, 'miningWasteProbability', 121 FROM DUAL
UNION ALL SELECT 3157, 0, '', 0, 'gallenteIndustrialBonusIceHoldCapacity', 105 FROM DUAL
UNION ALL SELECT 3158, 0, '', 0, 'shipBonusGasHold', 105 FROM DUAL
UNION ALL SELECT 3159, 0, 'Residue Volume Multiplier Bonus', 0, 'specializationCrystalMiningWastedVolumeMultiplierBonus', 205 FROM DUAL
UNION ALL SELECT 3160, 0, 'Residue Probability Bonus', 0, 'specializationCrystalMiningWasteProbabilityBonus', 205 FROM DUAL
UNION ALL SELECT 3161, 1, 'Asteroid Specialization Duration Multiplier', 0, 'specializationAsteroidDurationMultiplier', 104 FROM DUAL
UNION ALL SELECT 3164, 0, 'Drop Chance Overwrite', 0, 'dropChanceOverwrite', 0 FROM DUAL
UNION ALL SELECT 3165, 0, 'Probe Strength Bonus', 0, 'shipRoleBonusScanProbeBonus', 105 FROM DUAL
UNION ALL SELECT 3166, 0, '', 0, 'expeditionFrigateBonusMiningLaserDuration', 0 FROM DUAL
UNION ALL SELECT 3167, 0, '', 0, 'expeditionFrigateBonusIceHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3168, 0, '', 0, 'expeditionFrigateBonusGasHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3169, 0, '', 0, 'expeditionFrigateBonusArmorResistance', 0 FROM DUAL
UNION ALL SELECT 3170, 0, '', 0, 'expeditionFrigateBonusLightDronesDamage_DEPRICATED', 0 FROM DUAL
UNION ALL SELECT 3171, 0, '', 0, ' expeditionFrigateBonusMediumDronesDamage_DEPRICATED', 0 FROM DUAL
UNION ALL SELECT 3172, 0, '', 0, 'shipRoleBonusDroneOreMiningCycleTime', 0 FROM DUAL
UNION ALL SELECT 3173, 0, '', 0, 'shipRoleBonusDroneIceMiningCycleTime', 0 FROM DUAL
UNION ALL SELECT 3176, 0, 'Target Lock Silently', 0, 'targetLockSilently', 0 FROM DUAL
UNION ALL SELECT 3177, 0, '', 0, 'shipRoleBonusOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 3178, 0, '', 0, 'shipRoleBonusIceHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3179, 0, '', 0, 'shipRoleBonusDroneDamage', 0 FROM DUAL
UNION ALL SELECT 3180, 0, '', 0, 'shipRoleBonusDroneHitPoints', 0 FROM DUAL
UNION ALL SELECT 3181, 0, '', 0, 'miningBargeBonusOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 3182, 0, '', 0, 'miningBargeBonusIceHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3183, 0, '', 0, 'miningBargeBonusGasHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3184, 0, '', 0, 'miningBargeBonusOreMiningRange', 0 FROM DUAL
UNION ALL SELECT 3185, 0, '', 0, 'miningBargeBonusIceHarvestingRange', 0 FROM DUAL
UNION ALL SELECT 3187, 0, '', 0, 'miningBargeBonusGeneralMiningHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3188, 0, '', 0, 'miningBargeBonusShieldCapacity', 0 FROM DUAL
UNION ALL SELECT 3189, 0, '', 0, 'miningBargeBonusArmorHP', 0 FROM DUAL
UNION ALL SELECT 3190, 0, '', 0, 'expeditionFrigateBonusSignatureRadius', 0 FROM DUAL
UNION ALL SELECT 3191, 0, '', 0, 'expeditionFrigateBonusOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 3192, 0, '', 0, 'expeditionFrigateBonusShieldResistance', 0 FROM DUAL
UNION ALL SELECT 3193, 0, '', 0, 'exhumersBonusOreMiningDuration', 0 FROM DUAL
UNION ALL SELECT 3194, 0, '', 0, 'exhumersBonusIceHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3195, 0, '', 0, 'exhumersBonusSingatureRadius', 0 FROM DUAL
UNION ALL SELECT 3197, 0, '', 0, 'exhumersBonusOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 3198, 0, '', 0, 'exhumersBonusGeneralMiningHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3199, 0, '', 0, 'exhumersBonusShieldResistance', 0 FROM DUAL
UNION ALL SELECT 3200, 0, '', 0, 'exhumersBonusArmorResistance', 0 FROM DUAL
UNION ALL SELECT 3201, 0, '', 0, 'exhumersBonusLightDroneDamage', 0 FROM DUAL
UNION ALL SELECT 3202, 0, '', 0, 'exhumersBonusMediumDronesDamage', 0 FROM DUAL
UNION ALL SELECT 3203, 0, '', 0, 'industrialCommandBonusDroneDamage', 0 FROM DUAL
UNION ALL SELECT 3204, 0, '', 0, 'industrialCommandBonusFuelConsuptionCompactIndustrialCore', 0 FROM DUAL
UNION ALL SELECT 3205, 0, '', 0, 'industrialCommandBonusMiningForemanBurstRange', 0 FROM DUAL
UNION ALL SELECT 3206, 0, 'Stasis Webifier Maximum Range Bonus', 1391, 'stasisWebRangeAdd', 1 FROM DUAL
UNION ALL SELECT 3208, 0, '', 0, 'industrialCommandBonusGasHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3209, 0, '', 0, 'industrialCommandBonusIceHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3210, 0, '', 0, 'minmatarIndustrialBonusGasHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3211, 0, '', 0, 'industrialCommandBonusShipCargoCapacity', 0 FROM DUAL
UNION ALL SELECT 3212, 0, '', 0, 'industrialCommandBonusGeneralMiningHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3213, 0, '', 0, 'expeditionFrigateBonusLightDronesDamage', 0 FROM DUAL
UNION ALL SELECT 3214, 0, '', 0, 'expeditionFrigateBonusMediumDroneDamage', 0 FROM DUAL
UNION ALL SELECT 3221, 0, '', 0, 'industrialCommandBonusDroneOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 3222, 0, '', 0, 'industrialCommandBonusDroneIceHarvestingCycleTime', 0 FROM DUAL
UNION ALL SELECT 3223, 0, '', 0, 'capitalIndustrialShipBonusDroneOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 3224, 0, '', 0, 'capitalIndustrialShipBonusDroneIceCycleTime', 0 FROM DUAL
UNION ALL SELECT 3225, 0, '', 0, 'shipRoleBonusGasHarvesterDuration', 0 FROM DUAL
UNION ALL SELECT 3226, 0, '', 0, 'exhumersBonusGasHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3227, 0, 'Asteroid Hold Capacity', 71, 'specialAsteroidHoldCapacity', 9 FROM DUAL
UNION ALL SELECT 3228, 0, '', 0, 'shipRoleBonusStripMinerActivationCost', 0 FROM DUAL
UNION ALL SELECT 3229, 0, '', 0, 'shipRoleBonusIceHarvesterActivationCost', 0 FROM DUAL
UNION ALL SELECT 3230, 0, '', 0, 'shipRoleBonusOreMiningDuration', 0 FROM DUAL
UNION ALL SELECT 3231, 0, '', 0, 'shipRoleBonusGeneralMiningHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3233, 0, '', 0, 'capitalIndustrialShipBonusDroneHitPoints', 0 FROM DUAL
UNION ALL SELECT 3235, 0, '', 0, 'industrialCommandBonusDroneHitPoints', 0 FROM DUAL
UNION ALL SELECT 3236, 0, '', 0, 'ignoreMiningWaste', 0 FROM DUAL
UNION ALL SELECT 3237, 0, '', 0, 'miningFrigateBonusGasCloudHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3239, 0, '', 0, 'shipRoleBonusGasHarvestingYield', 0 FROM DUAL
UNION ALL SELECT 3240, 0, '', 0, 'miningFrigateBonusIceHarvestingDuration', 0 FROM DUAL
UNION ALL SELECT 3241, 0, '', 0, 'gallenteIndustrialBonusMiningHoldCapacity', 0 FROM DUAL
UNION ALL SELECT 3242, 0, '', 0, 'shipRoleBonusDroneOreMiningYield', 0 FROM DUAL
UNION ALL SELECT 3246, 0, 'Optimal Range', 1391, 'pointDefenseRange', 1 FROM DUAL
UNION ALL SELECT 3250, 0, '', 0, 'eliteBonusHeavyInterdictors3', 0 FROM DUAL
UNION ALL SELECT 3255, 0, 'Compressible Items', 0, 'compressibleItemsTypeList', 0 FROM DUAL
UNION ALL SELECT 3257, 0, 'Warp Scrambler Optimal Range Bonus', 1391, 'scramblerRangeAdd', 1 FROM DUAL
UNION ALL SELECT 3258, 0, 'Hull Damage Resistance Bonus', 0, 'hullDamageResistanceBonus', 124 FROM DUAL
UNION ALL SELECT 3260, 0, 'Gas Decompression Efficiency Bonus', 0, 'GasDecompressionEfficiencyBonus', 0 FROM DUAL
UNION ALL SELECT 3261, 0, '', 0, 'structureGasDecompressionEfficiencyBonus', 127 FROM DUAL
UNION ALL SELECT 3262, 0.800000011920929, '', 0, 'gasDecompressionBaseEfficiency', 127 FROM DUAL
UNION ALL SELECT 3263, 0, '', 0, 'fleetCompressionLogisticsRangeBonus', 0 FROM DUAL
UNION ALL SELECT 3265, 0, '', 0, 'activationRequiresActiveIndustrialCore', 0 FROM DUAL
UNION ALL SELECT 3266, 0, '', 0, 'battleshipPlateHPBonus', 0 FROM DUAL
UNION ALL SELECT 3267, 0, '', 0, 'battleshipExtenderHPBonus', 0 FROM DUAL
UNION ALL SELECT 3268, 0, '', 0, 'battleshipBulkheadHPModifierBonus', 0 FROM DUAL
UNION ALL SELECT 3317, 0, '', 0, 'jumpDriveTargetBeaconTypelistID', 0 FROM DUAL
UNION ALL SELECT 3318, 0, '', 0, 'jumpPortalPassengerRequiredAttributeID', 119 FROM DUAL
UNION ALL SELECT 3319, 1, '', 0, 'isTitanJumpPortalPassenger', 0 FROM DUAL
UNION ALL SELECT 3320, 0, '', 0, 'isBlackOpsJumpPortalPassenger', 0 FROM DUAL
UNION ALL SELECT 3321, 0, '', 0, 'jumpConduitPassengerRequiredAttributeID', 119 FROM DUAL
UNION ALL SELECT 3322, 0, '', 0, 'isBlackOpsJumpConduitPassenger', 0 FROM DUAL
UNION ALL SELECT 3324, 0, '', 0, 'isIndustrialJumpConduitPassenger', 0 FROM DUAL
UNION ALL SELECT 3325, 0, '', 0, 'isIndustrialJumpPortalPassenger', 0 FROM DUAL
UNION ALL SELECT 3326, 0, '', 0, 'capitalIndustrialCommandBonusDroneDamage', 0 FROM DUAL
UNION ALL SELECT 3327, 0, '', 0, 'capitalIndustrialShipBonusMiningForemanBurstRange', 0 FROM DUAL
UNION ALL SELECT 3328, 0, '', 0, 'subsystemBonusMassAddition', 0 FROM DUAL
UNION ALL SELECT 3353, 0, 'Drone Tracking Speed Bonus', 1398, 'droneTrackingBonus', 105 FROM DUAL
UNION ALL SELECT 3354, 0, 'DPS threshold to pause shield repair', 0, 'pauseShieldRepairDpsThreshold', 113 FROM DUAL
UNION ALL SELECT 3355, 0, 'DPS threshold to pause armor repair', 0, 'pauseArmorRepairDpsThreshold', 113 FROM DUAL
UNION ALL SELECT 3356, 0, 'DPS threshold to pause hull repair', 0, 'pauseHullRepairDpsThreshold', 113 FROM DUAL
UNION ALL SELECT 3422, 0, 'Stasis Webifier Resistance Bonus', 1389, 'stasisWebifierResistanceBonus', 105 FROM DUAL
UNION ALL SELECT 3429, 0, 'Armor Damage Resistance Bonus', 0, 'armorDamageResistanceBonus', 124 FROM DUAL
;
