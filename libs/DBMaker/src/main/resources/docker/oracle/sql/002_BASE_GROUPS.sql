whenever sqlerror exit failure

create table BASE_GROUPS(category_id NUMBER NOT NULL, group_id NUMBER NOT NULL, name VARCHAR2(4000), PRIMARY KEY (group_id), FOREIGN KEY (category_id) REFERENCES BASE_CATEGORIES (category_id));

INSERT INTO BASE_GROUPS (category_id, group_id, name)
          SELECT 0, 0, '#System' FROM DUAL
UNION ALL SELECT 1, 1, 'Character' FROM DUAL
UNION ALL SELECT 1, 2, 'Corporation' FROM DUAL
UNION ALL SELECT 2, 3, 'Region' FROM DUAL
UNION ALL SELECT 2, 4, 'Constellation' FROM DUAL
UNION ALL SELECT 2, 5, 'Solar System' FROM DUAL
UNION ALL SELECT 2, 6, 'Sun' FROM DUAL
UNION ALL SELECT 2, 7, 'Planet' FROM DUAL
UNION ALL SELECT 2, 8, 'Moon' FROM DUAL
UNION ALL SELECT 2, 9, 'Asteroid Belt' FROM DUAL
UNION ALL SELECT 2, 10, 'Stargate' FROM DUAL
UNION ALL SELECT 2, 11, 'Asteroid OLD' FROM DUAL
UNION ALL SELECT 2, 12, 'Cargo Container' FROM DUAL
UNION ALL SELECT 2, 13, 'Ring' FROM DUAL
UNION ALL SELECT 2, 14, 'Biomass' FROM DUAL
UNION ALL SELECT 3, 15, 'Station' FROM DUAL
UNION ALL SELECT 3, 16, 'Station Services' FROM DUAL
UNION ALL SELECT 4, 17, 'Money' FROM DUAL
UNION ALL SELECT 4, 18, 'Mineral' FROM DUAL
UNION ALL SELECT 1, 19, 'Faction' FROM DUAL
UNION ALL SELECT 4, 20, 'Drug' FROM DUAL
UNION ALL SELECT 5, 23, 'Clone' FROM DUAL
UNION ALL SELECT 5, 24, 'Voucher' FROM DUAL
UNION ALL SELECT 6, 25, 'Frigate' FROM DUAL
UNION ALL SELECT 6, 26, 'Cruiser' FROM DUAL
UNION ALL SELECT 6, 27, 'Battleship' FROM DUAL
UNION ALL SELECT 6, 28, 'Hauler' FROM DUAL
UNION ALL SELECT 6, 29, 'Capsule' FROM DUAL
UNION ALL SELECT 6, 30, 'Titan' FROM DUAL
UNION ALL SELECT 6, 31, 'Shuttle' FROM DUAL
UNION ALL SELECT 1, 32, 'Alliance' FROM DUAL
UNION ALL SELECT 7, 38, 'Shield Extender' FROM DUAL
UNION ALL SELECT 7, 39, 'Shield Recharger' FROM DUAL
UNION ALL SELECT 7, 40, 'Shield Booster' FROM DUAL
UNION ALL SELECT 7, 41, 'Remote Shield Booster' FROM DUAL
UNION ALL SELECT 7, 43, 'Capacitor Recharger' FROM DUAL
UNION ALL SELECT 7, 46, 'Propulsion Module' FROM DUAL
UNION ALL SELECT 7, 47, 'Cargo Scanner' FROM DUAL
UNION ALL SELECT 7, 48, 'Ship Scanner' FROM DUAL
UNION ALL SELECT 7, 49, 'Survey Scanner' FROM DUAL
UNION ALL SELECT 7, 52, 'Warp Scrambler' FROM DUAL
UNION ALL SELECT 7, 53, 'Energy Weapon' FROM DUAL
UNION ALL SELECT 7, 54, 'Mining Laser' FROM DUAL
UNION ALL SELECT 7, 55, 'Projectile Weapon' FROM DUAL
UNION ALL SELECT 7, 56, 'Missile Launcher' FROM DUAL
UNION ALL SELECT 7, 57, 'Shield Power Relay' FROM DUAL
UNION ALL SELECT 7, 59, 'Gyrostabilizer' FROM DUAL
UNION ALL SELECT 7, 60, 'Damage Control' FROM DUAL
UNION ALL SELECT 7, 61, 'Capacitor Battery' FROM DUAL
UNION ALL SELECT 7, 62, 'Armor Repair Unit' FROM DUAL
UNION ALL SELECT 7, 63, 'Hull Repair Unit' FROM DUAL
UNION ALL SELECT 7, 65, 'Stasis Web' FROM DUAL
UNION ALL SELECT 7, 67, 'Remote Capacitor Transmitter' FROM DUAL
UNION ALL SELECT 7, 68, 'Energy Nosferatu' FROM DUAL
UNION ALL SELECT 7, 71, 'Energy Neutralizer' FROM DUAL
UNION ALL SELECT 7, 72, 'Smart Bomb' FROM DUAL
UNION ALL SELECT 7, 74, 'Hybrid Weapon' FROM DUAL
UNION ALL SELECT 7, 76, 'Capacitor Booster' FROM DUAL
UNION ALL SELECT 7, 77, 'Shield Hardener' FROM DUAL
UNION ALL SELECT 7, 78, 'Reinforced Bulkhead' FROM DUAL
UNION ALL SELECT 7, 80, 'Burst Jammer' FROM DUAL
UNION ALL SELECT 7, 82, 'Passive Targeting System' FROM DUAL
UNION ALL SELECT 8, 83, 'Projectile Ammo' FROM DUAL
UNION ALL SELECT 8, 85, 'Hybrid Charge' FROM DUAL
UNION ALL SELECT 8, 86, 'Frequency Crystal' FROM DUAL
UNION ALL SELECT 8, 87, 'Capacitor Booster Charge' FROM DUAL
UNION ALL SELECT 8, 88, 'Defender Missiles' FROM DUAL
UNION ALL SELECT 8, 89, 'Torpedo' FROM DUAL
UNION ALL SELECT 8, 90, 'Bomb' FROM DUAL
UNION ALL SELECT 8, 92, 'Mine' FROM DUAL
UNION ALL SELECT 10, 94, 'Trading' FROM DUAL
UNION ALL SELECT 10, 95, 'Trade Session' FROM DUAL
UNION ALL SELECT 7, 96, 'Automated Targeting System' FROM DUAL
UNION ALL SELECT 18, 97, 'Proximity Drone' FROM DUAL
UNION ALL SELECT 7, 98, 'Armor Coating' FROM DUAL
UNION ALL SELECT 11, 99, 'Sentry Gun' FROM DUAL
UNION ALL SELECT 18, 100, 'Combat Drone' FROM DUAL
UNION ALL SELECT 18, 101, 'Mining Drone' FROM DUAL
UNION ALL SELECT 9, 104, 'Clone Blueprint' FROM DUAL
UNION ALL SELECT 9, 105, 'Frigate Blueprint' FROM DUAL
UNION ALL SELECT 9, 106, 'Cruiser Blueprint' FROM DUAL
UNION ALL SELECT 9, 107, 'Battleship Blueprint' FROM DUAL
UNION ALL SELECT 9, 108, 'Hauler Blueprint' FROM DUAL
UNION ALL SELECT 9, 109, 'Capsule Blueprint' FROM DUAL
UNION ALL SELECT 9, 110, 'Titan Blueprint' FROM DUAL
UNION ALL SELECT 9, 111, 'Shuttle Blueprint' FROM DUAL
UNION ALL SELECT 9, 118, 'Shield Extender Blueprint' FROM DUAL
UNION ALL SELECT 9, 119, 'Shield Recharger Blueprint' FROM DUAL
UNION ALL SELECT 9, 120, 'Shield Booster Blueprint' FROM DUAL
UNION ALL SELECT 9, 121, 'Remote Shield Booster Blueprint' FROM DUAL
UNION ALL SELECT 9, 123, 'Capacitor Recharger Blueprint' FROM DUAL
UNION ALL SELECT 9, 126, 'Propulsion Module Blueprint' FROM DUAL
UNION ALL SELECT 9, 127, 'Cargo Scanner Blueprint' FROM DUAL
UNION ALL SELECT 9, 128, 'Ship Scanner Blueprint' FROM DUAL
UNION ALL SELECT 9, 129, 'Survey Scanner Blueprint' FROM DUAL
UNION ALL SELECT 9, 130, 'ECM Blueprint' FROM DUAL
UNION ALL SELECT 9, 131, 'ECCM Blueprint' FROM DUAL
UNION ALL SELECT 9, 132, 'Warp Scrambler Blueprint' FROM DUAL
UNION ALL SELECT 9, 133, 'Energy Weapon Blueprint' FROM DUAL
UNION ALL SELECT 9, 134, 'Mining Laser Blueprint' FROM DUAL
UNION ALL SELECT 9, 135, 'Projectile Weapon Blueprint' FROM DUAL
UNION ALL SELECT 9, 136, 'Missile Launcher Blueprint' FROM DUAL
UNION ALL SELECT 9, 137, 'Power Manager Blueprint' FROM DUAL
UNION ALL SELECT 9, 139, 'Gunnery Upgrade Blueprint' FROM DUAL
UNION ALL SELECT 9, 140, 'Damage Control Blueprint' FROM DUAL
UNION ALL SELECT 9, 141, 'Capacitor Battery Blueprint' FROM DUAL
UNION ALL SELECT 9, 142, 'Armor Repair Unit Blueprint' FROM DUAL
UNION ALL SELECT 9, 143, 'Hull Repair Unit Blueprint' FROM DUAL
UNION ALL SELECT 9, 145, 'Stasis Web Blueprint' FROM DUAL
UNION ALL SELECT 9, 147, 'Remote Capacitor Transmitter Blueprint' FROM DUAL
UNION ALL SELECT 9, 148, 'Energy Nosferatu Blueprint' FROM DUAL
UNION ALL SELECT 9, 151, 'Energy Neutralizer Blueprint' FROM DUAL
UNION ALL SELECT 9, 152, 'Smart Bomb Blueprint' FROM DUAL
UNION ALL SELECT 9, 154, 'Hybrid Weapon Blueprint' FROM DUAL
UNION ALL SELECT 9, 156, 'Capacitor Booster Blueprint' FROM DUAL
UNION ALL SELECT 9, 157, 'Shield Hardener Blueprint' FROM DUAL
UNION ALL SELECT 9, 158, 'Hull Mods Blueprint' FROM DUAL
UNION ALL SELECT 9, 160, 'Burst Jammer Blueprint' FROM DUAL
UNION ALL SELECT 9, 161, 'Passive Targeting System Blueprint' FROM DUAL
UNION ALL SELECT 9, 162, 'Automated Targeting System Blueprint' FROM DUAL
UNION ALL SELECT 9, 163, 'Armor Coating Blueprint' FROM DUAL
UNION ALL SELECT 9, 165, 'Projectile Ammo Blueprint' FROM DUAL
UNION ALL SELECT 9, 166, 'Missile Blueprint' FROM DUAL
UNION ALL SELECT 9, 167, 'Hybrid Charge Blueprint' FROM DUAL
UNION ALL SELECT 9, 168, 'Frequency Crystal Blueprint' FROM DUAL
UNION ALL SELECT 9, 169, 'Capacitor Booster Charge Blueprint' FROM DUAL
UNION ALL SELECT 9, 170, 'Defender Missile Blueprint' FROM DUAL
UNION ALL SELECT 9, 172, 'Bomb Blueprint' FROM DUAL
UNION ALL SELECT 9, 174, 'Mine Blueprint' FROM DUAL
UNION ALL SELECT 9, 175, 'Proximity Drone Blueprint' FROM DUAL
UNION ALL SELECT 9, 176, 'Combat Drone Blueprint' FROM DUAL
UNION ALL SELECT 9, 177, 'Mining Drone Blueprint' FROM DUAL
UNION ALL SELECT 9, 178, 'Drug Blueprint' FROM DUAL
UNION ALL SELECT 11, 180, 'Protective Sentry Gun' FROM DUAL
UNION ALL SELECT 11, 182, 'Police Drone' FROM DUAL
UNION ALL SELECT 11, 185, 'Pirate Drone' FROM DUAL
UNION ALL SELECT 2, 186, 'Wreck' FROM DUAL
UNION ALL SELECT 14, 190, 'Bloodline Bonus' FROM DUAL
UNION ALL SELECT 14, 191, 'Physical Benefit' FROM DUAL
UNION ALL SELECT 14, 192, 'Physical Handicap' FROM DUAL
UNION ALL SELECT 14, 193, 'Phobia Handicap' FROM DUAL
UNION ALL SELECT 14, 194, 'Social Handicap' FROM DUAL
UNION ALL SELECT 14, 195, 'Amarr Education' FROM DUAL
UNION ALL SELECT 14, 196, 'Caldari Education' FROM DUAL
UNION ALL SELECT 14, 197, 'Gallente Education' FROM DUAL
UNION ALL SELECT 14, 198, 'Minmatar Education' FROM DUAL
UNION ALL SELECT 14, 199, 'Career Bonus' FROM DUAL
UNION ALL SELECT 7, 201, 'ECM' FROM DUAL
UNION ALL SELECT 7, 202, 'ECCM' FROM DUAL
UNION ALL SELECT 7, 203, 'Sensor Backup Array' FROM DUAL
UNION ALL SELECT 7, 205, 'Heat Sink' FROM DUAL
UNION ALL SELECT 7, 208, 'Sensor Dampener' FROM DUAL
UNION ALL SELECT 7, 209, 'Remote Tracking Computer' FROM DUAL
UNION ALL SELECT 7, 210, 'Signal Amplifier' FROM DUAL
UNION ALL SELECT 7, 211, 'Tracking Enhancer' FROM DUAL
UNION ALL SELECT 7, 212, 'Sensor Booster' FROM DUAL
UNION ALL SELECT 7, 213, 'Tracking Computer' FROM DUAL
UNION ALL SELECT 9, 218, 'Heat Sink Blueprint' FROM DUAL
UNION ALL SELECT 9, 223, 'Sensor Booster Blueprint' FROM DUAL
UNION ALL SELECT 9, 224, 'Tracking Computer Blueprint' FROM DUAL
UNION ALL SELECT 7, 225, 'Cheat Module Group' FROM DUAL
UNION ALL SELECT 2, 226, 'Large Collidable Object' FROM DUAL
UNION ALL SELECT 2, 227, 'Cloud' FROM DUAL
UNION ALL SELECT 6, 237, 'Corvette' FROM DUAL
UNION ALL SELECT 16, 255, 'Gunnery' FROM DUAL
UNION ALL SELECT 16, 256, 'Missiles' FROM DUAL
UNION ALL SELECT 16, 257, 'Spaceship Command' FROM DUAL
UNION ALL SELECT 16, 258, 'Fleet Support' FROM DUAL
UNION ALL SELECT 16, 266, 'Corporation Management' FROM DUAL
UNION ALL SELECT 17, 267, 'Obsolete Books' FROM DUAL
UNION ALL SELECT 16, 268, 'Production' FROM DUAL
UNION ALL SELECT 16, 269, 'Rigging' FROM DUAL
UNION ALL SELECT 16, 270, 'Science' FROM DUAL
UNION ALL SELECT 16, 272, 'Electronic Systems' FROM DUAL
UNION ALL SELECT 16, 273, 'Drones' FROM DUAL
UNION ALL SELECT 16, 274, 'Trade' FROM DUAL
UNION ALL SELECT 16, 275, 'Navigation' FROM DUAL
UNION ALL SELECT 16, 278, 'Social' FROM DUAL
UNION ALL SELECT 11, 279, 'LCO Drone' FROM DUAL
UNION ALL SELECT 17, 280, 'General' FROM DUAL
UNION ALL SELECT 17, 281, 'Frozen' FROM DUAL
UNION ALL SELECT 17, 282, 'Radioactive' FROM DUAL
UNION ALL SELECT 17, 283, 'Livestock' FROM DUAL
UNION ALL SELECT 17, 284, 'Biohazard' FROM DUAL
UNION ALL SELECT 7, 285, 'CPU Enhancer' FROM DUAL
UNION ALL SELECT 11, 286, 'Minor Threat' FROM DUAL
UNION ALL SELECT 11, 287, 'Rogue Drone' FROM DUAL
UNION ALL SELECT 11, 288, 'Faction Drone' FROM DUAL
UNION ALL SELECT 7, 289, 'Projected ECCM' FROM DUAL
UNION ALL SELECT 7, 290, 'Remote Sensor Booster' FROM DUAL
UNION ALL SELECT 7, 291, 'Weapon Disruptor' FROM DUAL
UNION ALL SELECT 7, 295, 'Shield Resistance Amplifier' FROM DUAL
UNION ALL SELECT 9, 296, 'Shield Resistance Amplifier Blueprint' FROM DUAL
UNION ALL SELECT 11, 297, 'Convoy' FROM DUAL
UNION ALL SELECT 11, 298, 'Convoy Drone' FROM DUAL
UNION ALL SELECT 18, 299, 'Repair Drone' FROM DUAL
UNION ALL SELECT 20, 300, 'Cyberimplant' FROM DUAL
UNION ALL SELECT 11, 301, 'Concord Drone' FROM DUAL
UNION ALL SELECT 7, 302, 'Magnetic Field Stabilizer' FROM DUAL
UNION ALL SELECT 20, 303, 'Booster' FROM DUAL
UNION ALL SELECT 20, 304, 'DNA Mutator' FROM DUAL
UNION ALL SELECT 2, 305, 'Comet' FROM DUAL
UNION ALL SELECT 11, 306, 'Spawn Container' FROM DUAL
UNION ALL SELECT 2, 307, 'Construction Platform' FROM DUAL
UNION ALL SELECT 7, 308, 'Countermeasure Launcher' FROM DUAL
UNION ALL SELECT 7, 309, 'Autopilot' FROM DUAL
UNION ALL SELECT 2, 310, 'Beacon' FROM DUAL
UNION ALL SELECT 23, 311, 'Reprocessing Array' FROM DUAL
UNION ALL SELECT 2, 312, 'Planetary Cloud' FROM DUAL
UNION ALL SELECT 17, 313, 'Drugs' FROM DUAL
UNION ALL SELECT 17, 314, 'Miscellaneous' FROM DUAL
UNION ALL SELECT 7, 315, 'Warp Core Stabilizer' FROM DUAL
UNION ALL SELECT 7, 316, 'Gang Coordinator' FROM DUAL
UNION ALL SELECT 7, 317, 'Computer Interface Node' FROM DUAL
UNION ALL SELECT 2, 318, 'Landmark' FROM DUAL
UNION ALL SELECT 11, 319, 'Large Collidable Structure' FROM DUAL
UNION ALL SELECT 7, 321, 'Shield Disruptor' FROM DUAL
UNION ALL SELECT 11, 323, 'Billboard' FROM DUAL
UNION ALL SELECT 6, 324, 'Assault Frigate' FROM DUAL
UNION ALL SELECT 7, 325, 'Remote Armor Repairer' FROM DUAL
UNION ALL SELECT 7, 326, 'Energized Armor Membrane' FROM DUAL
UNION ALL SELECT 7, 328, 'Armor Hardener' FROM DUAL
UNION ALL SELECT 7, 329, 'Armor Plate' FROM DUAL
UNION ALL SELECT 7, 330, 'Cloaking Device' FROM DUAL
UNION ALL SELECT 17, 332, 'Tool' FROM DUAL
UNION ALL SELECT 17, 333, 'Datacores' FROM DUAL
UNION ALL SELECT 17, 334, 'Construction Components' FROM DUAL
UNION ALL SELECT 11, 335, 'Temporary Cloud' FROM DUAL
UNION ALL SELECT 2, 336, 'Mobile Sentry Gun' FROM DUAL
UNION ALL SELECT 11, 337, 'Mission Drone' FROM DUAL
UNION ALL SELECT 7, 338, 'Shield Boost Amplifier' FROM DUAL
UNION ALL SELECT 7, 339, 'Auxiliary Power Core' FROM DUAL
UNION ALL SELECT 2, 340, 'Secure Cargo Container' FROM DUAL
UNION ALL SELECT 7, 341, 'Signature Scrambling' FROM DUAL
UNION ALL SELECT 9, 342, 'Anti Warp Scrambler Blueprint' FROM DUAL
UNION ALL SELECT 9, 343, 'Weapon Disruptor Blueprint' FROM DUAL
UNION ALL SELECT 9, 344, 'Tracking Enhancer Blueprint' FROM DUAL
UNION ALL SELECT 9, 345, 'Remote Tracking Computer Blueprint' FROM DUAL
UNION ALL SELECT 9, 346, 'Co-Processor Blueprint' FROM DUAL
UNION ALL SELECT 9, 347, 'Signal Amplifier Blueprint' FROM DUAL
UNION ALL SELECT 9, 348, 'Armor Hardener Blueprint' FROM DUAL
UNION ALL SELECT 9, 349, 'Armor Plate Blueprint' FROM DUAL
UNION ALL SELECT 9, 350, 'Remote Armor Repairer Blueprint' FROM DUAL
UNION ALL SELECT 9, 352, 'Auxiliary Power Core Blueprint' FROM DUAL
UNION ALL SELECT 7, 353, 'QA Module' FROM DUAL
UNION ALL SELECT 17, 355, 'Refinables' FROM DUAL
UNION ALL SELECT 9, 356, 'Tool Blueprint' FROM DUAL
UNION ALL SELECT 7, 357, 'DroneBayExpander' FROM DUAL
UNION ALL SELECT 6, 358, 'Heavy Assault Cruiser' FROM DUAL
UNION ALL SELECT 9, 360, 'Shield Boost Amplifier Blueprint' FROM DUAL
UNION ALL SELECT 22, 361, 'Mobile Warp Disruptor' FROM DUAL
UNION ALL SELECT 23, 363, 'Ship Maintenance Array' FROM DUAL
UNION ALL SELECT 23, 364, 'Mobile Storage' FROM DUAL
UNION ALL SELECT 23, 365, 'Control Tower' FROM DUAL
UNION ALL SELECT 2, 366, 'Warp Gate' FROM DUAL
UNION ALL SELECT 7, 367, 'Ballistic Control system' FROM DUAL
UNION ALL SELECT 2, 368, 'Global Warp Disruptor' FROM DUAL
UNION ALL SELECT 17, 369, 'Ship Logs' FROM DUAL
UNION ALL SELECT 17, 370, 'Criminal Tags' FROM DUAL
UNION ALL SELECT 9, 371, 'Mobile Warp Disruptor Blueprint' FROM DUAL
UNION ALL SELECT 8, 372, 'Advanced Autocannon Ammo' FROM DUAL
UNION ALL SELECT 8, 373, 'Advanced Railgun Charge' FROM DUAL
UNION ALL SELECT 8, 374, 'Advanced Beam Laser Crystal' FROM DUAL
UNION ALL SELECT 8, 375, 'Advanced Pulse Laser Crystal' FROM DUAL
UNION ALL SELECT 8, 376, 'Advanced Artillery Ammo' FROM DUAL
UNION ALL SELECT 8, 377, 'Advanced Blaster Charge' FROM DUAL
UNION ALL SELECT 7, 378, 'Cruise Control' FROM DUAL
UNION ALL SELECT 7, 379, 'Target Painter' FROM DUAL
UNION ALL SELECT 6, 380, 'Deep Space Transport' FROM DUAL
UNION ALL SELECT 6, 381, 'Elite Battleship' FROM DUAL
UNION ALL SELECT 2, 382, 'Shipping Crates' FROM DUAL
UNION ALL SELECT 11, 383, 'Destructible Sentry Gun' FROM DUAL
UNION ALL SELECT 8, 384, 'Light Missile' FROM DUAL
UNION ALL SELECT 8, 385, 'Heavy Missile' FROM DUAL
UNION ALL SELECT 8, 386, 'Cruise Missile' FROM DUAL
UNION ALL SELECT 8, 387, 'Rocket' FROM DUAL
UNION ALL SELECT 8, 394, 'Auto-Targeting Light Missile' FROM DUAL
UNION ALL SELECT 8, 395, 'Auto-Targeting Heavy Missile' FROM DUAL
UNION ALL SELECT 8, 396, 'Auto-Targeting Cruise Missile' FROM DUAL
UNION ALL SELECT 23, 397, 'Assembly Array' FROM DUAL
UNION ALL SELECT 9, 400, 'Ballistic Control System Blueprint' FROM DUAL
UNION ALL SELECT 9, 401, 'Cloaking Device Blueprint' FROM DUAL
UNION ALL SELECT 23, 404, 'Silo' FROM DUAL
UNION ALL SELECT 7, 405, 'Anti Cloaking Pulse' FROM DUAL
UNION ALL SELECT 7, 406, 'Smartbomb Supercharger' FROM DUAL
UNION ALL SELECT 7, 407, 'Fighter Support Unit' FROM DUAL
UNION ALL SELECT 9, 408, 'Drone Upgrade Blueprint' FROM DUAL
UNION ALL SELECT 17, 409, 'Empire Insignia Drops' FROM DUAL
UNION ALL SELECT 9, 410, 'Anti Cloaking Pulse Blueprint' FROM DUAL
UNION ALL SELECT 2, 411, 'Force Field' FROM DUAL
UNION ALL SELECT 23, 413, 'Laboratory' FROM DUAL
UNION ALL SELECT 23, 414, 'Mobile Power Core' FROM DUAL
UNION ALL SELECT 23, 416, 'Moon Mining' FROM DUAL
UNION ALL SELECT 23, 417, 'Mobile Missile Sentry' FROM DUAL
UNION ALL SELECT 23, 418, 'Mobile Shield Generator' FROM DUAL
UNION ALL SELECT 6, 419, 'Combat Battlecruiser' FROM DUAL
UNION ALL SELECT 6, 420, 'Destroyer' FROM DUAL
UNION ALL SELECT 4, 422, 'Gas Isotopes' FROM DUAL
UNION ALL SELECT 4, 423, 'Ice Product' FROM DUAL
UNION ALL SELECT 8, 425, 'Orbital Assault Unit' FROM DUAL
UNION ALL SELECT 23, 426, 'Mobile Projectile Sentry' FROM DUAL
UNION ALL SELECT 4, 427, 'Moon Materials' FROM DUAL
UNION ALL SELECT 4, 428, 'Intermediate Materials' FROM DUAL
UNION ALL SELECT 4, 429, 'Composite' FROM DUAL
UNION ALL SELECT 23, 430, 'Mobile Laser Sentry' FROM DUAL
UNION ALL SELECT 11, 435, 'Deadspace Overseer' FROM DUAL
UNION ALL SELECT 24, 436, 'Simple Reaction' FROM DUAL
UNION ALL SELECT 23, 438, 'Mobile Reactor' FROM DUAL
UNION ALL SELECT 23, 439, 'Electronic Warfare Battery' FROM DUAL
UNION ALL SELECT 23, 440, 'Sensor Dampening Battery' FROM DUAL
UNION ALL SELECT 23, 441, 'Stasis Webification Battery' FROM DUAL
UNION ALL SELECT 23, 443, 'Warp Scrambling Battery' FROM DUAL
UNION ALL SELECT 23, 444, 'Shield Hardening Array' FROM DUAL
UNION ALL SELECT 23, 445, 'Force Field Array' FROM DUAL
UNION ALL SELECT 11, 446, 'Customs Official' FROM DUAL
UNION ALL SELECT 9, 447, 'Construction Component Blueprints' FROM DUAL
UNION ALL SELECT 2, 448, 'Audit Log Secure Container' FROM DUAL
UNION ALL SELECT 23, 449, 'Mobile Hybrid Sentry' FROM DUAL
UNION ALL SELECT 25, 450, 'Arkonor' FROM DUAL
UNION ALL SELECT 25, 451, 'Bistot' FROM DUAL
UNION ALL SELECT 25, 452, 'Crokite' FROM DUAL
UNION ALL SELECT 25, 453, 'Dark Ochre' FROM DUAL
UNION ALL SELECT 25, 454, 'Hedbergite' FROM DUAL
UNION ALL SELECT 25, 455, 'Hemorphite' FROM DUAL
UNION ALL SELECT 25, 456, 'Jaspet' FROM DUAL
UNION ALL SELECT 25, 457, 'Kernite' FROM DUAL
UNION ALL SELECT 25, 458, 'Plagioclase' FROM DUAL
UNION ALL SELECT 25, 459, 'Pyroxeres' FROM DUAL
UNION ALL SELECT 25, 460, 'Scordite' FROM DUAL
UNION ALL SELECT 25, 461, 'Spodumain' FROM DUAL
UNION ALL SELECT 25, 462, 'Veldspar' FROM DUAL
UNION ALL SELECT 6, 463, 'Mining Barge' FROM DUAL
UNION ALL SELECT 7, 464, 'Strip Miner' FROM DUAL
UNION ALL SELECT 25, 465, 'Ice' FROM DUAL
UNION ALL SELECT 25, 467, 'Gneiss' FROM DUAL
UNION ALL SELECT 25, 468, 'Mercoxit' FROM DUAL
UNION ALL SELECT 25, 469, 'Omber' FROM DUAL
UNION ALL SELECT 18, 470, 'Unanchoring Drone' FROM DUAL
UNION ALL SELECT 23, 471, 'Corporate Hangar Array' FROM DUAL
UNION ALL SELECT 7, 472, 'System Scanner' FROM DUAL
UNION ALL SELECT 23, 473, 'Tracking Array' FROM DUAL
UNION ALL SELECT 17, 474, 'Acceleration Gate Keys' FROM DUAL
UNION ALL SELECT 7, 475, 'Microwarpdrive' FROM DUAL
UNION ALL SELECT 8, 476, 'XL Torpedo' FROM DUAL
UNION ALL SELECT 9, 477, 'Mining Barge Blueprint' FROM DUAL
UNION ALL SELECT 9, 478, 'System Scanner Blueprint' FROM DUAL
UNION ALL SELECT 8, 479, 'Scanner Probe' FROM DUAL
UNION ALL SELECT 23, 480, 'Stealth Emitter Array' FROM DUAL
UNION ALL SELECT 7, 481, 'Scan Probe Launcher' FROM DUAL
UNION ALL SELECT 8, 482, 'Mining Crystal' FROM DUAL
UNION ALL SELECT 7, 483, 'Frequency Mining Laser' FROM DUAL
UNION ALL SELECT 24, 484, 'Complex Reactions' FROM DUAL
UNION ALL SELECT 6, 485, 'Dreadnought' FROM DUAL
UNION ALL SELECT 9, 486, 'Scan Probe Blueprint' FROM DUAL
UNION ALL SELECT 9, 487, 'Destroyer Blueprint' FROM DUAL
UNION ALL SELECT 9, 489, 'Battlecruiser Blueprint' FROM DUAL
UNION ALL SELECT 9, 490, 'Strip Miner Blueprint' FROM DUAL
UNION ALL SELECT 8, 492, 'Survey Probe' FROM DUAL
UNION ALL SELECT 17, 493, 'Overseer Personal Effects' FROM DUAL
UNION ALL SELECT 11, 494, 'Deadspace Overseer''s Structure' FROM DUAL
UNION ALL SELECT 11, 495, 'Deadspace Overseer''s Sentry' FROM DUAL
UNION ALL SELECT 11, 496, 'Deadspace Overseer''s Belongings' FROM DUAL
UNION ALL SELECT 8, 497, 'Fuel' FROM DUAL
UNION ALL SELECT 8, 498, 'Modifications' FROM DUAL
UNION ALL SELECT 7, 499, 'New EW Testing' FROM DUAL
UNION ALL SELECT 8, 500, 'Festival Charges' FROM DUAL
UNION ALL SELECT 7, 501, 'Festival Launcher' FROM DUAL
UNION ALL SELECT 2, 502, 'Cosmic Signature' FROM DUAL
UNION ALL SELECT 9, 503, 'Elite Hauler Blueprint' FROM DUAL
UNION ALL SELECT 9, 504, 'Target Painter Blueprint' FROM DUAL
UNION ALL SELECT 16, 505, 'Fake Skills' FROM DUAL
UNION ALL SELECT 7, 506, 'Missile Launcher Cruise' FROM DUAL
UNION ALL SELECT 7, 507, 'Missile Launcher Rocket' FROM DUAL
UNION ALL SELECT 7, 508, 'Missile Launcher Torpedo' FROM DUAL
UNION ALL SELECT 7, 509, 'Missile Launcher Light' FROM DUAL
UNION ALL SELECT 7, 510, 'Missile Launcher Heavy' FROM DUAL
UNION ALL SELECT 7, 511, 'Missile Launcher Rapid Light' FROM DUAL
UNION ALL SELECT 7, 512, 'Missile Launcher Defender' FROM DUAL
UNION ALL SELECT 6, 513, 'Freighter' FROM DUAL
UNION ALL SELECT 7, 514, 'ECM Stabilizer' FROM DUAL
UNION ALL SELECT 7, 515, 'Siege Module' FROM DUAL
UNION ALL SELECT 9, 516, 'Siege Module Blueprint' FROM DUAL
UNION ALL SELECT 2, 517, 'Agents in Space' FROM DUAL
UNION ALL SELECT 7, 518, 'Anti Ballistic Defense System' FROM DUAL
UNION ALL SELECT 25, 519, 'Terran Artifacts' FROM DUAL
UNION ALL SELECT 11, 520, 'Storyline Frigate' FROM DUAL
UNION ALL SELECT 17, 521, 'Identification' FROM DUAL
UNION ALL SELECT 11, 522, 'Storyline Cruiser' FROM DUAL
UNION ALL SELECT 11, 523, 'Storyline Battleship' FROM DUAL
UNION ALL SELECT 7, 524, 'Missile Launcher XL Torpedo' FROM DUAL
UNION ALL SELECT 9, 525, 'Freighter Blueprint' FROM DUAL
UNION ALL SELECT 17, 526, 'Commodities' FROM DUAL
UNION ALL SELECT 11, 527, 'Storyline Mission Frigate' FROM DUAL
UNION ALL SELECT 17, 528, 'Artifacts and Prototypes' FROM DUAL
UNION ALL SELECT 17, 530, 'Materials and Compounds' FROM DUAL
UNION ALL SELECT 9, 532, 'Gang Coordinator Blueprint' FROM DUAL
UNION ALL SELECT 11, 533, 'Storyline Mission Cruiser' FROM DUAL
UNION ALL SELECT 11, 534, 'Storyline Mission Battleship' FROM DUAL
UNION ALL SELECT 9, 535, 'Construction Platform Blueprint' FROM DUAL
UNION ALL SELECT 17, 536, 'Structure Components' FROM DUAL
UNION ALL SELECT 9, 537, 'Dreadnought Blueprint' FROM DUAL
UNION ALL SELECT 7, 538, 'Data Miners' FROM DUAL
UNION ALL SELECT 6, 540, 'Command Ship' FROM DUAL
UNION ALL SELECT 6, 541, 'Interdictor' FROM DUAL
UNION ALL SELECT 6, 543, 'Exhumer' FROM DUAL
UNION ALL SELECT 18, 544, 'Energy Neutralizer Drone' FROM DUAL
UNION ALL SELECT 18, 545, 'Warp Scrambling Drone' FROM DUAL
UNION ALL SELECT 7, 546, 'Mining Upgrade' FROM DUAL
UNION ALL SELECT 6, 547, 'Carrier' FROM DUAL
UNION ALL SELECT 8, 548, 'Interdiction Probe' FROM DUAL
UNION ALL SELECT 18, 549, 'Fighter Drone' FROM DUAL
UNION ALL SELECT 11, 550, 'Asteroid Angel Cartel Frigate' FROM DUAL
UNION ALL SELECT 11, 551, 'Asteroid Angel Cartel Cruiser' FROM DUAL
UNION ALL SELECT 11, 552, 'Asteroid Angel Cartel Battleship' FROM DUAL
UNION ALL SELECT 11, 553, 'Asteroid Angel Cartel Officer' FROM DUAL
UNION ALL SELECT 11, 554, 'Asteroid Angel Cartel Hauler' FROM DUAL
UNION ALL SELECT 11, 555, 'Asteroid Blood Raiders Cruiser' FROM DUAL
UNION ALL SELECT 11, 556, 'Asteroid Blood Raiders Battleship' FROM DUAL
UNION ALL SELECT 11, 557, 'Asteroid Blood Raiders Frigate' FROM DUAL
UNION ALL SELECT 11, 558, 'Asteroid Blood Raiders Hauler' FROM DUAL
UNION ALL SELECT 11, 559, 'Asteroid Blood Raiders Officer' FROM DUAL
UNION ALL SELECT 11, 560, 'Asteroid Guristas Battleship' FROM DUAL
UNION ALL SELECT 11, 561, 'Asteroid Guristas Cruiser' FROM DUAL
UNION ALL SELECT 11, 562, 'Asteroid Guristas Frigate' FROM DUAL
UNION ALL SELECT 11, 563, 'Asteroid Guristas Hauler' FROM DUAL
UNION ALL SELECT 11, 564, 'Asteroid Guristas Officer' FROM DUAL
UNION ALL SELECT 11, 565, 'Asteroid Sansha''s Nation Battleship' FROM DUAL
UNION ALL SELECT 11, 566, 'Asteroid Sansha''s Nation Cruiser' FROM DUAL
UNION ALL SELECT 11, 567, 'Asteroid Sansha''s Nation Frigate' FROM DUAL
UNION ALL SELECT 11, 568, 'Asteroid Sansha''s Nation Hauler' FROM DUAL
UNION ALL SELECT 11, 569, 'Asteroid Sansha''s Nation Officer' FROM DUAL
UNION ALL SELECT 11, 570, 'Asteroid Serpentis Battleship' FROM DUAL
UNION ALL SELECT 11, 571, 'Asteroid Serpentis Cruiser' FROM DUAL
UNION ALL SELECT 11, 572, 'Asteroid Serpentis Frigate' FROM DUAL
UNION ALL SELECT 11, 573, 'Asteroid Serpentis Hauler' FROM DUAL
UNION ALL SELECT 11, 574, 'Asteroid Serpentis Officer' FROM DUAL
UNION ALL SELECT 11, 575, 'Asteroid Angel Cartel Destroyer' FROM DUAL
UNION ALL SELECT 11, 576, 'Asteroid Angel Cartel BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 577, 'Asteroid Blood Raiders Destroyer' FROM DUAL
UNION ALL SELECT 11, 578, 'Asteroid Blood Raiders BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 579, 'Asteroid Guristas Destroyer' FROM DUAL
UNION ALL SELECT 11, 580, 'Asteroid Guristas BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 581, 'Asteroid Sansha''s Nation Destroyer' FROM DUAL
UNION ALL SELECT 11, 582, 'Asteroid Sansha''s Nation BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 583, 'Asteroid Serpentis Destroyer' FROM DUAL
UNION ALL SELECT 11, 584, 'Asteroid Serpentis BattleCruiser' FROM DUAL
UNION ALL SELECT 7, 585, 'Remote Hull Repairer' FROM DUAL
UNION ALL SELECT 7, 586, 'Drone Modules' FROM DUAL
UNION ALL SELECT 7, 588, 'Super Weapon' FROM DUAL
UNION ALL SELECT 7, 589, 'Interdiction Sphere Launcher' FROM DUAL
UNION ALL SELECT 7, 590, 'Jump Portal Generator' FROM DUAL
UNION ALL SELECT 11, 593, 'Deadspace Angel Cartel BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 594, 'Deadspace Angel Cartel Battleship' FROM DUAL
UNION ALL SELECT 11, 595, 'Deadspace Angel Cartel Cruiser' FROM DUAL
UNION ALL SELECT 11, 596, 'Deadspace Angel Cartel Destroyer' FROM DUAL
UNION ALL SELECT 11, 597, 'Deadspace Angel Cartel Frigate' FROM DUAL
UNION ALL SELECT 11, 602, 'Deadspace Blood Raiders BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 603, 'Deadspace Blood Raiders Battleship' FROM DUAL
UNION ALL SELECT 11, 604, 'Deadspace Blood Raiders Cruiser' FROM DUAL
UNION ALL SELECT 11, 605, 'Deadspace Blood Raiders Destroyer' FROM DUAL
UNION ALL SELECT 11, 606, 'Deadspace Blood Raiders Frigate' FROM DUAL
UNION ALL SELECT 11, 611, 'Deadspace Guristas BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 612, 'Deadspace Guristas Battleship' FROM DUAL
UNION ALL SELECT 11, 613, 'Deadspace Guristas Cruiser' FROM DUAL
UNION ALL SELECT 11, 614, 'Deadspace Guristas Destroyer' FROM DUAL
UNION ALL SELECT 11, 615, 'Deadspace Guristas Frigate' FROM DUAL
UNION ALL SELECT 11, 620, 'Deadspace Sansha''s Nation BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 621, 'Deadspace Sansha''s Nation Battleship' FROM DUAL
UNION ALL SELECT 11, 622, 'Deadspace Sansha''s Nation Cruiser' FROM DUAL
UNION ALL SELECT 11, 623, 'Deadspace Sansha''s Nation Destroyer' FROM DUAL
UNION ALL SELECT 11, 624, 'Deadspace Sansha''s Nation Frigate' FROM DUAL
UNION ALL SELECT 11, 629, 'Deadspace Serpentis BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 630, 'Deadspace Serpentis Battleship' FROM DUAL
UNION ALL SELECT 11, 631, 'Deadspace Serpentis Cruiser' FROM DUAL
UNION ALL SELECT 11, 632, 'Deadspace Serpentis Destroyer' FROM DUAL
UNION ALL SELECT 11, 633, 'Deadspace Serpentis Frigate' FROM DUAL
UNION ALL SELECT 7, 638, 'Navigation Computer' FROM DUAL
UNION ALL SELECT 18, 639, 'Electronic Warfare Drone' FROM DUAL
UNION ALL SELECT 18, 640, 'Logistic Drone' FROM DUAL
UNION ALL SELECT 18, 641, 'Stasis Webifying Drone' FROM DUAL
UNION ALL SELECT 7, 642, 'Super Gang Enhancer' FROM DUAL
UNION ALL SELECT 9, 643, 'Carrier Blueprint' FROM DUAL
UNION ALL SELECT 7, 644, 'Drone Navigation Computer' FROM DUAL
UNION ALL SELECT 7, 645, 'Drone Damage Modules' FROM DUAL
UNION ALL SELECT 7, 646, 'Drone Tracking Modules' FROM DUAL
UNION ALL SELECT 7, 647, 'Drone Control Range Module' FROM DUAL
UNION ALL SELECT 8, 648, 'Advanced Rocket' FROM DUAL
UNION ALL SELECT 2, 649, 'Freight Container' FROM DUAL
UNION ALL SELECT 7, 650, 'Tractor Beam' FROM DUAL
UNION ALL SELECT 9, 651, 'Super Weapon Blueprint' FROM DUAL
UNION ALL SELECT 17, 652, 'Lease' FROM DUAL
UNION ALL SELECT 8, 653, 'Advanced Light Missile' FROM DUAL
UNION ALL SELECT 8, 654, 'Advanced Heavy Assault Missile' FROM DUAL
UNION ALL SELECT 8, 655, 'Advanced Heavy Missile' FROM DUAL
UNION ALL SELECT 8, 656, 'Advanced Cruise Missile' FROM DUAL
UNION ALL SELECT 8, 657, 'Advanced Torpedo' FROM DUAL
UNION ALL SELECT 7, 658, 'Cynosural Field Generator' FROM DUAL
UNION ALL SELECT 6, 659, 'Supercarrier' FROM DUAL
UNION ALL SELECT 7, 660, 'Energy Vampire Slayer' FROM DUAL
UNION ALL SELECT 24, 661, 'Simple Biochemical Reactions' FROM DUAL
UNION ALL SELECT 24, 662, 'Complex Biochemical Reactions' FROM DUAL
UNION ALL SELECT 8, 663, 'Mercoxit Mining Crystal' FROM DUAL
UNION ALL SELECT 11, 665, 'Mission Amarr Empire Frigate' FROM DUAL
UNION ALL SELECT 11, 666, 'Mission Amarr Empire Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 667, 'Mission Amarr Empire Battleship' FROM DUAL
UNION ALL SELECT 11, 668, 'Mission Amarr Empire Cruiser' FROM DUAL
UNION ALL SELECT 11, 669, 'Mission Amarr Empire Destroyer' FROM DUAL
UNION ALL SELECT 11, 670, 'Mission Amarr Empire Other' FROM DUAL
UNION ALL SELECT 11, 671, 'Mission Caldari State Frigate' FROM DUAL
UNION ALL SELECT 11, 672, 'Mission Caldari State Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 673, 'Mission Caldari State Cruiser' FROM DUAL
UNION ALL SELECT 11, 674, 'Mission Caldari State Battleship' FROM DUAL
UNION ALL SELECT 11, 675, 'Mission Caldari State Other' FROM DUAL
UNION ALL SELECT 11, 676, 'Mission Caldari State Destroyer' FROM DUAL
UNION ALL SELECT 11, 677, 'Mission Gallente Federation Frigate' FROM DUAL
UNION ALL SELECT 11, 678, 'Mission Gallente Federation Cruiser' FROM DUAL
UNION ALL SELECT 11, 679, 'Mission Gallente Federation Destroyer' FROM DUAL
UNION ALL SELECT 11, 680, 'Mission Gallente Federation Battleship' FROM DUAL
UNION ALL SELECT 11, 681, 'Mission Gallente Federation Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 682, 'Mission Gallente Federation Other' FROM DUAL
UNION ALL SELECT 11, 683, 'Mission Minmatar Republic Frigate' FROM DUAL
UNION ALL SELECT 11, 684, 'Mission Minmatar Republic Destroyer' FROM DUAL
UNION ALL SELECT 11, 685, 'Mission Minmatar Republic Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 686, 'Mission Minmatar Republic Other' FROM DUAL
UNION ALL SELECT 11, 687, 'Mission Khanid Frigate' FROM DUAL
UNION ALL SELECT 11, 688, 'Mission Khanid Destroyer' FROM DUAL
UNION ALL SELECT 11, 689, 'Mission Khanid Cruiser' FROM DUAL
UNION ALL SELECT 11, 690, 'Mission Khanid Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 691, 'Mission Khanid Battleship' FROM DUAL
UNION ALL SELECT 11, 692, 'Mission Khanid Other' FROM DUAL
UNION ALL SELECT 11, 693, 'Mission CONCORD Frigate' FROM DUAL
UNION ALL SELECT 11, 694, 'Mission CONCORD Destroyer' FROM DUAL
UNION ALL SELECT 11, 695, 'Mission CONCORD Cruiser' FROM DUAL
UNION ALL SELECT 11, 696, 'Mission CONCORD Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 697, 'Mission CONCORD Battleship' FROM DUAL
UNION ALL SELECT 11, 698, 'Mission CONCORD Other' FROM DUAL
UNION ALL SELECT 11, 699, 'Mission Mordu Frigate' FROM DUAL
UNION ALL SELECT 11, 700, 'Mission Mordu Destroyer' FROM DUAL
UNION ALL SELECT 11, 701, 'Mission Mordu Cruiser' FROM DUAL
UNION ALL SELECT 11, 702, 'Mission Mordu Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 703, 'Mission Mordu Battleship' FROM DUAL
UNION ALL SELECT 11, 704, 'Mission Mordu Other' FROM DUAL
UNION ALL SELECT 11, 705, 'Mission Minmatar Republic Cruiser' FROM DUAL
UNION ALL SELECT 11, 706, 'Mission Minmatar Republic Battleship' FROM DUAL
UNION ALL SELECT 23, 707, 'Jump Portal Array' FROM DUAL
UNION ALL SELECT 23, 709, 'Scanner Array' FROM DUAL
UNION ALL SELECT 23, 710, 'Logistics Array' FROM DUAL
UNION ALL SELECT 2, 711, 'Harvestable Cloud' FROM DUAL
UNION ALL SELECT 4, 712, 'Biochemical Material' FROM DUAL
UNION ALL SELECT 11, 715, 'Destructible Agents In Space' FROM DUAL
UNION ALL SELECT 17, 716, 'Data Interfaces' FROM DUAL
UNION ALL SELECT 9, 718, 'Booster Blueprints' FROM DUAL
UNION ALL SELECT 20, 721, 'Temp' FROM DUAL
UNION ALL SELECT 9, 722, 'Advanced Hybrid Charge Blueprint' FROM DUAL
UNION ALL SELECT 9, 723, 'Tractor Beam Blueprint' FROM DUAL
UNION ALL SELECT 9, 724, 'Implant Blueprints' FROM DUAL
UNION ALL SELECT 9, 725, 'Advanced Projectile Ammo Blueprint' FROM DUAL
UNION ALL SELECT 9, 726, 'Advanced Frequency Crystal Blueprint' FROM DUAL
UNION ALL SELECT 9, 727, 'Mining Crystal Blueprint' FROM DUAL
UNION ALL SELECT 35, 728, 'Decryptors - Amarr' FROM DUAL
UNION ALL SELECT 35, 729, 'Decryptors - Minmatar' FROM DUAL
UNION ALL SELECT 35, 730, 'Decryptors - Gallente' FROM DUAL
UNION ALL SELECT 35, 731, 'Decryptors - Caldari' FROM DUAL
UNION ALL SELECT 17, 732, 'Decryptors - Sleepers' FROM DUAL
UNION ALL SELECT 17, 733, 'Decryptors - Yan Jung' FROM DUAL
UNION ALL SELECT 17, 734, 'Decryptors - Takmahl' FROM DUAL
UNION ALL SELECT 17, 735, 'Decryptors - Talocan' FROM DUAL
UNION ALL SELECT 7, 737, 'Gas Cloud Scoops' FROM DUAL
UNION ALL SELECT 20, 738, 'Cyber Armor' FROM DUAL
UNION ALL SELECT 20, 739, 'Cyber Drones' FROM DUAL
UNION ALL SELECT 20, 740, 'Cyber Electronic Systems' FROM DUAL
UNION ALL SELECT 20, 741, 'Cyber Engineering' FROM DUAL
UNION ALL SELECT 20, 742, 'Cyber Gunnery' FROM DUAL
UNION ALL SELECT 20, 743, 'Cyber Production' FROM DUAL
UNION ALL SELECT 20, 744, 'Cyber Leadership' FROM DUAL
UNION ALL SELECT 20, 745, 'Cyber Learning' FROM DUAL
UNION ALL SELECT 20, 746, 'Cyber Missile' FROM DUAL
UNION ALL SELECT 20, 747, 'Cyber Navigation' FROM DUAL
UNION ALL SELECT 20, 748, 'Cyber Science' FROM DUAL
UNION ALL SELECT 20, 749, 'Cyber Shields' FROM DUAL
UNION ALL SELECT 20, 750, 'Cyber Social' FROM DUAL
UNION ALL SELECT 20, 751, 'Cyber Trade' FROM DUAL
UNION ALL SELECT 7, 753, 'ECM Enhancer' FROM DUAL
UNION ALL SELECT 4, 754, 'Salvaged Materials' FROM DUAL
UNION ALL SELECT 11, 755, 'Asteroid Rogue Drone BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 756, 'Asteroid Rogue Drone Battleship' FROM DUAL
UNION ALL SELECT 11, 757, 'Asteroid Rogue Drone Cruiser' FROM DUAL
UNION ALL SELECT 11, 758, 'Asteroid Rogue Drone Destroyer' FROM DUAL
UNION ALL SELECT 11, 759, 'Asteroid Rogue Drone Frigate' FROM DUAL
UNION ALL SELECT 11, 760, 'Asteroid Rogue Drone Hauler' FROM DUAL
UNION ALL SELECT 11, 761, 'Asteroid Rogue Drone Swarm' FROM DUAL
UNION ALL SELECT 7, 762, 'Inertial Stabilizer' FROM DUAL
UNION ALL SELECT 7, 763, 'Nanofiber Internal Structure' FROM DUAL
UNION ALL SELECT 7, 764, 'Overdrive Injector System' FROM DUAL
UNION ALL SELECT 7, 765, 'Expanded Cargohold' FROM DUAL
UNION ALL SELECT 7, 766, 'Power Diagnostic System' FROM DUAL
UNION ALL SELECT 7, 767, 'Capacitor Power Relay' FROM DUAL
UNION ALL SELECT 7, 768, 'Capacitor Flux Coil' FROM DUAL
UNION ALL SELECT 7, 769, 'Reactor Control Unit' FROM DUAL
UNION ALL SELECT 7, 770, 'Shield Flux Coil' FROM DUAL
UNION ALL SELECT 7, 771, 'Missile Launcher Heavy Assault' FROM DUAL
UNION ALL SELECT 8, 772, 'Heavy Assault Missile' FROM DUAL
UNION ALL SELECT 7, 773, 'Rig Armor' FROM DUAL
UNION ALL SELECT 7, 774, 'Rig Shield' FROM DUAL
UNION ALL SELECT 7, 775, 'Rig Energy Weapon' FROM DUAL
UNION ALL SELECT 7, 776, 'Rig Hybrid Weapon' FROM DUAL
UNION ALL SELECT 7, 777, 'Rig Projectile Weapon' FROM DUAL
UNION ALL SELECT 7, 778, 'Rig Drones' FROM DUAL
UNION ALL SELECT 7, 779, 'Rig Launcher' FROM DUAL
UNION ALL SELECT 7, 781, 'Rig Core' FROM DUAL
UNION ALL SELECT 7, 782, 'Rig Navigation' FROM DUAL
UNION ALL SELECT 20, 783, 'Cyber X Specials' FROM DUAL
UNION ALL SELECT 11, 784, 'Large Collidable Ship' FROM DUAL
UNION ALL SELECT 7, 786, 'Rig Electronic Systems' FROM DUAL
UNION ALL SELECT 9, 787, 'Rig Blueprint' FROM DUAL
UNION ALL SELECT 11, 789, 'Asteroid Angel Cartel Commander Frigate' FROM DUAL
UNION ALL SELECT 11, 790, 'Asteroid Angel Cartel Commander Cruiser' FROM DUAL
UNION ALL SELECT 11, 791, 'Asteroid Blood Raiders Commander Cruiser' FROM DUAL
UNION ALL SELECT 11, 792, 'Asteroid Blood Raiders Commander Frigate' FROM DUAL
UNION ALL SELECT 11, 793, 'Asteroid Angel Cartel Commander BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 794, 'Asteroid Angel Cartel Commander Destroyer' FROM DUAL
UNION ALL SELECT 11, 795, 'Asteroid Blood Raiders Commander BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 796, 'Asteroid Blood Raiders Commander Destroyer' FROM DUAL
UNION ALL SELECT 11, 797, 'Asteroid Guristas Commander BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 798, 'Asteroid Guristas Commander Cruiser' FROM DUAL
UNION ALL SELECT 11, 799, 'Asteroid Guristas Commander Destroyer' FROM DUAL
UNION ALL SELECT 11, 800, 'Asteroid Guristas Commander Frigate' FROM DUAL
UNION ALL SELECT 11, 801, 'Deadspace Rogue Drone BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 802, 'Deadspace Rogue Drone Battleship' FROM DUAL
UNION ALL SELECT 11, 803, 'Deadspace Rogue Drone Cruiser' FROM DUAL
UNION ALL SELECT 11, 804, 'Deadspace Rogue Drone Destroyer' FROM DUAL
UNION ALL SELECT 11, 805, 'Deadspace Rogue Drone Frigate' FROM DUAL
UNION ALL SELECT 11, 806, 'Deadspace Rogue Drone Swarm' FROM DUAL
UNION ALL SELECT 11, 807, 'Asteroid Sansha''s Nation Commander BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 808, 'Asteroid Sansha''s Nation Commander Cruiser' FROM DUAL
UNION ALL SELECT 11, 809, 'Asteroid Sansha''s Nation Commander Destroyer' FROM DUAL
UNION ALL SELECT 11, 810, 'Asteroid Sansha''s Nation Commander Frigate' FROM DUAL
UNION ALL SELECT 11, 811, 'Asteroid Serpentis Commander BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 812, 'Asteroid Serpentis Commander Cruiser' FROM DUAL
UNION ALL SELECT 11, 813, 'Asteroid Serpentis Commander Destroyer' FROM DUAL
UNION ALL SELECT 11, 814, 'Asteroid Serpentis Commander Frigate' FROM DUAL
UNION ALL SELECT 7, 815, 'Clone Vat Bay' FROM DUAL
UNION ALL SELECT 11, 816, 'Mission Generic Battleships' FROM DUAL
UNION ALL SELECT 11, 817, 'Mission Generic Cruisers' FROM DUAL
UNION ALL SELECT 11, 818, 'Mission Generic Frigates' FROM DUAL
UNION ALL SELECT 11, 819, 'Deadspace Overseer Frigate' FROM DUAL
UNION ALL SELECT 11, 820, 'Deadspace Overseer Cruiser' FROM DUAL
UNION ALL SELECT 11, 821, 'Deadspace Overseer Battleship' FROM DUAL
UNION ALL SELECT 11, 822, 'Mission Thukker Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 823, 'Mission Thukker Battleship' FROM DUAL
UNION ALL SELECT 11, 824, 'Mission Thukker Cruiser' FROM DUAL
UNION ALL SELECT 11, 825, 'Mission Thukker Destroyer' FROM DUAL
UNION ALL SELECT 11, 826, 'Mission Thukker Frigate' FROM DUAL
UNION ALL SELECT 11, 827, 'Mission Thukker Other' FROM DUAL
UNION ALL SELECT 11, 828, 'Mission Generic Battle Cruisers' FROM DUAL
UNION ALL SELECT 11, 829, 'Mission Generic Destroyers' FROM DUAL
UNION ALL SELECT 6, 830, 'Covert Ops' FROM DUAL
UNION ALL SELECT 6, 831, 'Interceptor' FROM DUAL
UNION ALL SELECT 6, 832, 'Logistics' FROM DUAL
UNION ALL SELECT 6, 833, 'Force Recon Ship' FROM DUAL
UNION ALL SELECT 6, 834, 'Stealth Bomber' FROM DUAL
UNION ALL SELECT 2, 835, 'Station Upgrade Platform' FROM DUAL
UNION ALL SELECT 2, 836, 'Station Improvement Platform' FROM DUAL
UNION ALL SELECT 23, 837, 'Energy Neutralizing Battery' FROM DUAL
UNION ALL SELECT 23, 838, 'Cynosural Generator Array' FROM DUAL
UNION ALL SELECT 23, 839, 'Cynosural System Jammer' FROM DUAL
UNION ALL SELECT 23, 840, 'Structure Repair Array' FROM DUAL
UNION ALL SELECT 9, 841, 'Starbase - Control Tower Blueprints' FROM DUAL
UNION ALL SELECT 7, 842, 'Burst Projectors' FROM DUAL
UNION ALL SELECT 11, 843, 'Asteroid Rogue Drone Commander BattleCruiser' FROM DUAL
UNION ALL SELECT 11, 844, 'Asteroid Rogue Drone Commander Battleship' FROM DUAL
UNION ALL SELECT 11, 845, 'Asteroid Rogue Drone Commander Cruiser' FROM DUAL
UNION ALL SELECT 11, 846, 'Asteroid Rogue Drone Commander Destroyer' FROM DUAL
UNION ALL SELECT 11, 847, 'Asteroid Rogue Drone Commander Frigate' FROM DUAL
UNION ALL SELECT 11, 848, 'Asteroid Angel Cartel Commander Battleship' FROM DUAL
UNION ALL SELECT 11, 849, 'Asteroid Blood Raiders Commander Battleship' FROM DUAL
UNION ALL SELECT 11, 850, 'Asteroid Guristas Commander Battleship' FROM DUAL
UNION ALL SELECT 11, 851, 'Asteroid Sansha''s Nation Commander Battleship' FROM DUAL
UNION ALL SELECT 11, 852, 'Asteroid Serpentis Commander Battleship' FROM DUAL
UNION ALL SELECT 9, 853, 'Starbase - Laser Battery Blueprints' FROM DUAL
UNION ALL SELECT 9, 854, 'Starbase - Projectile Battery Blueprints' FROM DUAL
UNION ALL SELECT 9, 855, 'Starbase - Hybrid Battery Blueprints' FROM DUAL
UNION ALL SELECT 9, 856, 'Starbase - ECM Jamming Array Blueprints' FROM DUAL
UNION ALL SELECT 9, 857, 'Starbase - Warp Scrambling Battery Blueprints' FROM DUAL
UNION ALL SELECT 9, 858, 'Starbase - Stasis Webification Battery Blueprints' FROM DUAL
UNION ALL SELECT 9, 859, 'Starbase - Sensor Dampening Array Blueprints' FROM DUAL
UNION ALL SELECT 9, 860, 'Starbase - Energy Neutralizing Battery Blueprints' FROM DUAL
UNION ALL SELECT 11, 861, 'Mission Fighter Drone' FROM DUAL
UNION ALL SELECT 7, 862, 'Missile Launcher Bomb' FROM DUAL
UNION ALL SELECT 8, 863, 'Bomb ECM' FROM DUAL
UNION ALL SELECT 8, 864, 'Bomb Energy' FROM DUAL
UNION ALL SELECT 11, 865, 'Mission Amarr Empire Carrier' FROM DUAL
UNION ALL SELECT 11, 866, 'Mission Caldari State Carrier' FROM DUAL
UNION ALL SELECT 11, 867, 'Mission Gallente Federation Carrier' FROM DUAL
UNION ALL SELECT 11, 868, 'Mission Minmatar Republic Carrier' FROM DUAL
UNION ALL SELECT 9, 870, 'Remote Hull Repairer Blueprint' FROM DUAL
UNION ALL SELECT 9, 871, 'Starbase - Missile Battery Blueprints' FROM DUAL
UNION ALL SELECT 5, 872, 'Outpost Improvements' FROM DUAL
UNION ALL SELECT 17, 873, 'Capital Construction Components' FROM DUAL
UNION ALL SELECT 2, 874, 'Disruptable Station Services' FROM DUAL
UNION ALL SELECT 11, 875, 'Mission Faction Transports' FROM DUAL
UNION ALL SELECT 5, 876, 'Outpost Upgrades' FROM DUAL
UNION ALL SELECT 23, 877, 'Target Painting Battery' FROM DUAL
UNION ALL SELECT 7, 878, 'Cloak Enhancements' FROM DUAL
UNION ALL SELECT 17, 879, 'Slave Reception' FROM DUAL
UNION ALL SELECT 17, 880, 'Sleeper Components' FROM DUAL
UNION ALL SELECT 24, 881, 'Freedom Programs' FROM DUAL
UNION ALL SELECT 24, 882, 'Enslavement Programs' FROM DUAL
UNION ALL SELECT 6, 883, 'Capital Industrial Ship' FROM DUAL
UNION ALL SELECT 17, 884, 'Test Compressed Ore' FROM DUAL
UNION ALL SELECT 2, 885, 'Cosmic Anomaly' FROM DUAL
UNION ALL SELECT 4, 886, 'Rogue Drone Components' FROM DUAL
UNION ALL SELECT 9, 888, 'Ore Compression Blueprints' FROM DUAL
UNION ALL SELECT 9, 889, 'Ore Enhancement Blueprints' FROM DUAL
UNION ALL SELECT 9, 890, 'Ice Compression Blueprints' FROM DUAL
UNION ALL SELECT 9, 891, 'Starbase - Mobile Laboratory Blueprints' FROM DUAL
UNION ALL SELECT 8, 892, 'Planet Satellites' FROM DUAL
UNION ALL SELECT 6, 893, 'Electronic Attack Ship' FROM DUAL
UNION ALL SELECT 6, 894, 'Heavy Interdiction Cruiser' FROM DUAL
UNION ALL SELECT 7, 896, 'Rig Security Transponder' FROM DUAL
UNION ALL SELECT 2, 897, 'Covert Cynosural Field' FROM DUAL
UNION ALL SELECT 6, 898, 'Black Ops' FROM DUAL
UNION ALL SELECT 7, 899, 'Warp Disrupt Field Generator' FROM DUAL
UNION ALL SELECT 6, 900, 'Marauder' FROM DUAL
UNION ALL SELECT 7, 901, 'Mining Enhancer' FROM DUAL
UNION ALL SELECT 6, 902, 'Jump Freighter' FROM DUAL
UNION ALL SELECT 25, 903, 'Ancient Compressed Ice' FROM DUAL
UNION ALL SELECT 7, 904, 'Rig Mining' FROM DUAL
UNION ALL SELECT 7, 905, 'Covert Cynosural Field Generator' FROM DUAL
UNION ALL SELECT 6, 906, 'Combat Recon Ship' FROM DUAL
UNION ALL SELECT 8, 907, 'Tracking Script' FROM DUAL
UNION ALL SELECT 8, 908, 'Warp Disruption Script' FROM DUAL
UNION ALL SELECT 8, 909, 'Tracking Disruption Script' FROM DUAL
UNION ALL SELECT 8, 910, 'Sensor Booster Script' FROM DUAL
UNION ALL SELECT 8, 911, 'Sensor Dampener Script' FROM DUAL
UNION ALL SELECT 9, 912, 'Script Blueprint' FROM DUAL
UNION ALL SELECT 17, 913, 'Advanced Capital Construction Components' FROM DUAL
UNION ALL SELECT 9, 914, 'Advanced Capital Construction Component Blueprints' FROM DUAL
UNION ALL SELECT 9, 915, 'Capital Construction Blueprints' FROM DUAL
UNION ALL SELECT 8, 916, 'Nanite Repair Paste' FROM DUAL
UNION ALL SELECT 9, 917, 'Data Miner Blueprint' FROM DUAL
UNION ALL SELECT 9, 918, 'Scan Probe Launcher Blueprint' FROM DUAL
UNION ALL SELECT 2, 920, 'Effect Beacon' FROM DUAL
UNION ALL SELECT 11, 922, 'Capture Point' FROM DUAL
UNION ALL SELECT 11, 924, 'Mission Faction Battleship' FROM DUAL
UNION ALL SELECT 11, 925, 'FW Infrastructure Hub' FROM DUAL
UNION ALL SELECT 11, 927, 'Mission Faction Haulers' FROM DUAL
UNION ALL SELECT 11, 934, 'Zombie Entities' FROM DUAL
UNION ALL SELECT 26, 935, 'WorldSpace' FROM DUAL
UNION ALL SELECT 29, 937, 'Decorations' FROM DUAL
UNION ALL SELECT 49, 940, 'Furniture' FROM DUAL
UNION ALL SELECT 6, 941, 'Industrial Command Ship' FROM DUAL
UNION ALL SELECT 5, 943, 'Legacy Currency' FROM DUAL
UNION ALL SELECT 9, 944, 'Capital Industrial Ship Blueprint' FROM DUAL
UNION ALL SELECT 9, 945, 'Industrial Command Ship Blueprint' FROM DUAL
UNION ALL SELECT 11, 952, 'Mission Container' FROM DUAL
UNION ALL SELECT 32, 954, 'Defensive Subsystem' FROM DUAL
UNION ALL SELECT 17, 955, 'Depricated Subsystems' FROM DUAL
UNION ALL SELECT 32, 956, 'Offensive Subsystem' FROM DUAL
UNION ALL SELECT 32, 957, 'Propulsion Subsystem' FROM DUAL
UNION ALL SELECT 32, 958, 'Core Subsystem' FROM DUAL
UNION ALL SELECT 11, 959, 'Deadspace Sleeper Sleepless Sentinel' FROM DUAL
UNION ALL SELECT 11, 960, 'Deadspace Sleeper Awakened Sentinel' FROM DUAL
UNION ALL SELECT 11, 961, 'Deadspace Sleeper Emergent Sentinel' FROM DUAL
UNION ALL SELECT 6, 963, 'Strategic Cruiser' FROM DUAL
UNION ALL SELECT 17, 964, 'Hybrid Tech Components' FROM DUAL
UNION ALL SELECT 9, 965, 'Hybrid Component Blueprints' FROM DUAL
UNION ALL SELECT 4, 966, 'Ancient Salvage' FROM DUAL
UNION ALL SELECT 4, 967, 'Wormhole Minerals' FROM DUAL
UNION ALL SELECT 34, 971, 'Sleeper Propulsion Relics' FROM DUAL
UNION ALL SELECT 8, 972, 'Obsolete Probes' FROM DUAL
UNION ALL SELECT 9, 973, 'Subsystem Blueprints' FROM DUAL
UNION ALL SELECT 4, 974, 'Hybrid Polymers' FROM DUAL
UNION ALL SELECT 63, 976, 'Festival Charges Expired' FROM DUAL
UNION ALL SELECT 24, 977, 'Hybrid Reactions' FROM DUAL
UNION ALL SELECT 35, 979, 'Decryptors - Hybrid' FROM DUAL
UNION ALL SELECT 11, 982, 'Deadspace Sleeper Sleepless Defender' FROM DUAL
UNION ALL SELECT 11, 983, 'Deadspace Sleeper Sleepless Patroller' FROM DUAL
UNION ALL SELECT 11, 984, 'Deadspace Sleeper Awakened Defender' FROM DUAL
UNION ALL SELECT 11, 985, 'Deadspace Sleeper Awakened Patroller' FROM DUAL
UNION ALL SELECT 11, 986, 'Deadspace Sleeper Emergent Defender' FROM DUAL
UNION ALL SELECT 11, 987, 'Deadspace Sleeper Emergent Patroller' FROM DUAL
UNION ALL SELECT 2, 988, 'Wormhole' FROM DUAL
UNION ALL SELECT 34, 990, 'Sleeper Electronics Relics' FROM DUAL
UNION ALL SELECT 34, 991, 'Sleeper Offensive Relics' FROM DUAL
UNION ALL SELECT 34, 992, 'Sleeper Engineering Relics' FROM DUAL
UNION ALL SELECT 34, 993, 'Sleeper Defensive Relics' FROM DUAL
UNION ALL SELECT 2, 995, 'Secondary Sun' FROM DUAL
UNION ALL SELECT 9, 996, 'Strategic Cruiser Blueprints' FROM DUAL
UNION ALL SELECT 34, 997, 'Sleeper Hull Relics' FROM DUAL
UNION ALL SELECT 40, 1003, 'Territorial Claim Unit' FROM DUAL
UNION ALL SELECT 40, 1004, 'Defense Bunkers' FROM DUAL
UNION ALL SELECT 40, 1005, 'Sovereignty Blockade Unit' FROM DUAL
UNION ALL SELECT 11, 1006, 'Mission Faction Cruiser' FROM DUAL
UNION ALL SELECT 11, 1007, 'Mission Faction Frigate' FROM DUAL
UNION ALL SELECT 8, 1010, 'Compact XL Torpedo' FROM DUAL
UNION ALL SELECT 40, 1012, 'Infrastructure Hub' FROM DUAL
UNION ALL SELECT 9, 1013, 'Supercarrier Blueprints' FROM DUAL
UNION ALL SELECT 39, 1016, 'Strategic Upgrades' FROM DUAL
UNION ALL SELECT 8, 1019, 'XL Cruise Missile' FROM DUAL
UNION ALL SELECT 39, 1020, 'Industrial Upgrades' FROM DUAL
UNION ALL SELECT 39, 1021, 'Military Upgrades' FROM DUAL
UNION ALL SELECT 6, 1022, 'Prototype Exploration Ship' FROM DUAL
UNION ALL SELECT 18, 1023, 'Fighter Bomber' FROM DUAL
UNION ALL SELECT 46, 1025, 'Orbital Infrastructure' FROM DUAL
UNION ALL SELECT 41, 1026, 'Extractors' FROM DUAL
UNION ALL SELECT 41, 1027, 'Command Centers' FROM DUAL
UNION ALL SELECT 41, 1028, 'Processors' FROM DUAL
UNION ALL SELECT 41, 1029, 'Storage Facilities' FROM DUAL
UNION ALL SELECT 41, 1030, 'Spaceports' FROM DUAL
UNION ALL SELECT 17, 1031, 'Planetary Resources' FROM DUAL
UNION ALL SELECT 42, 1032, 'Planet Solid - Raw Resource' FROM DUAL
UNION ALL SELECT 42, 1033, 'Planet Liquid-Gas - Raw Resource' FROM DUAL
UNION ALL SELECT 43, 1034, 'Refined Commodities - Tier 2' FROM DUAL
UNION ALL SELECT 42, 1035, 'Planet Organic - Raw Resource' FROM DUAL
UNION ALL SELECT 41, 1036, 'Planetary Links' FROM DUAL
UNION ALL SELECT 43, 1040, 'Specialized Commodities - Tier 3' FROM DUAL
UNION ALL SELECT 43, 1041, 'Advanced Commodities - Tier 4' FROM DUAL
UNION ALL SELECT 43, 1042, 'Basic Commodities - Tier 1' FROM DUAL
UNION ALL SELECT 9, 1045, 'Sovereignty Structure Blueprint' FROM DUAL
UNION ALL SELECT 9, 1046, 'Nanite Repair Paste Blueprint' FROM DUAL
UNION ALL SELECT 9, 1048, 'Starbase Blueprint' FROM DUAL
UNION ALL SELECT 11, 1051, 'Incursion Sansha''s Nation Industrial' FROM DUAL
UNION ALL SELECT 11, 1052, 'Incursion Sansha''s Nation Capital' FROM DUAL
UNION ALL SELECT 11, 1053, 'Incursion Sansha''s Nation Frigate' FROM DUAL
UNION ALL SELECT 11, 1054, 'Incursion Sansha''s Nation Cruiser' FROM DUAL
UNION ALL SELECT 11, 1056, 'Incursion Sansha''s Nation Battleship' FROM DUAL
UNION ALL SELECT 41, 1063, 'Extractor Control Units' FROM DUAL
UNION ALL SELECT 26, 1067, 'MaterialZone' FROM DUAL
UNION ALL SELECT 26, 1068, 'DetailMesh' FROM DUAL
UNION ALL SELECT 2, 1071, 'Flashpoint' FROM DUAL
UNION ALL SELECT 46, 1073, 'Test Orbitals' FROM DUAL
UNION ALL SELECT 49, 1079, 'Generic' FROM DUAL
UNION ALL SELECT 41, 1081, 'Mercenary Bases' FROM DUAL
UNION ALL SELECT 41, 1082, 'Capsuleer Bases' FROM DUAL
UNION ALL SELECT 30, 1083, 'Eyewear' FROM DUAL
UNION ALL SELECT 30, 1084, 'Tattoos' FROM DUAL
UNION ALL SELECT 30, 1085, 'Piercings' FROM DUAL
UNION ALL SELECT 30, 1086, 'Scars' FROM DUAL
UNION ALL SELECT 30, 1087, 'Mid Layer' FROM DUAL
UNION ALL SELECT 30, 1088, 'Outer' FROM DUAL
UNION ALL SELECT 30, 1089, 'Tops' FROM DUAL
UNION ALL SELECT 30, 1090, 'Bottoms' FROM DUAL
UNION ALL SELECT 30, 1091, 'Footwear' FROM DUAL
UNION ALL SELECT 30, 1092, 'Headwear' FROM DUAL
UNION ALL SELECT 30, 1093, 'Makeup' FROM DUAL
UNION ALL SELECT 53, 1105, 'Lens Flares' FROM DUAL
UNION ALL SELECT 46, 1106, 'Orbital Construction Platform' FROM DUAL
UNION ALL SELECT 53, 1107, 'Particle Systems' FROM DUAL
UNION ALL SELECT 53, 1108, 'Animated Lights' FROM DUAL
UNION ALL SELECT 29, 1109, 'Audio' FROM DUAL
UNION ALL SELECT 54, 1110, 'Point Lights' FROM DUAL
UNION ALL SELECT 54, 1111, 'Box Lights' FROM DUAL
UNION ALL SELECT 54, 1112, 'Spot Lights' FROM DUAL
UNION ALL SELECT 17, 1118, 'Surface Infrastructure Prefab Units' FROM DUAL
UNION ALL SELECT 29, 1121, 'Perception Points' FROM DUAL
UNION ALL SELECT 7, 1122, 'Salvager' FROM DUAL
UNION ALL SELECT 9, 1123, 'Salvager Blueprint' FROM DUAL
UNION ALL SELECT 59, 1126, 'PhysicalPortals' FROM DUAL
UNION ALL SELECT 4, 1136, 'Fuel Block' FROM DUAL
UNION ALL SELECT 9, 1137, 'Fuel Block Blueprint' FROM DUAL
UNION ALL SELECT 9, 1139, 'Mining Laser Upgrade Blueprint' FROM DUAL
UNION ALL SELECT 17, 1141, 'Research Data' FROM DUAL
UNION ALL SELECT 9, 1142, 'Energy Neutralizer Drone Blueprint' FROM DUAL
UNION ALL SELECT 9, 1143, 'Electronic Warfare Drone Blueprint' FROM DUAL
UNION ALL SELECT 9, 1144, 'Logistic Drone Blueprint' FROM DUAL
UNION ALL SELECT 9, 1145, 'Heavy Fighter Blueprint' FROM DUAL
UNION ALL SELECT 9, 1146, 'Light Fighter Blueprint' FROM DUAL
UNION ALL SELECT 9, 1147, 'Stasis Webifying Drone Blueprint' FROM DUAL
UNION ALL SELECT 22, 1149, 'Mobile Jump Disruptor' FROM DUAL
UNION ALL SELECT 7, 1150, 'Armor Resistance Shift Hardener' FROM DUAL
UNION ALL SELECT 9, 1151, 'Armor Resistance Shift Hardener Blueprint' FROM DUAL
UNION ALL SELECT 9, 1152, 'Drone Damage Module Blueprint' FROM DUAL
UNION ALL SELECT 8, 1153, 'Shield Booster Script' FROM DUAL
UNION ALL SELECT 7, 1154, 'Signature Suppressor' FROM DUAL
UNION ALL SELECT 9, 1155, 'Signature Suppressor Blueprint' FROM DUAL
UNION ALL SELECT 7, 1156, 'Ancillary Shield Booster' FROM DUAL
UNION ALL SELECT 9, 1157, 'Fueled Shield Booster Blueprint' FROM DUAL
UNION ALL SELECT 8, 1158, 'Heavy Defender Missile' FROM DUAL
UNION ALL SELECT 18, 1159, 'Salvage Drone' FROM DUAL
UNION ALL SELECT 9, 1160, 'Survey Probe Blueprints' FROM DUAL
UNION ALL SELECT 9, 1162, 'Container Blueprints' FROM DUAL
UNION ALL SELECT 2, 1165, 'Satellite' FROM DUAL
UNION ALL SELECT 11, 1166, 'FW Minmatar Republic Frigate' FROM DUAL
UNION ALL SELECT 11, 1167, 'FW Caldari State Frigate' FROM DUAL
UNION ALL SELECT 11, 1168, 'FW Gallente Federation Frigate' FROM DUAL
UNION ALL SELECT 11, 1169, 'FW Amarr Empire Frigate' FROM DUAL
UNION ALL SELECT 11, 1174, 'Asteroid Rogue Drone Officer' FROM DUAL
UNION ALL SELECT 11, 1175, 'FW Amarr Empire Destroyer' FROM DUAL
UNION ALL SELECT 11, 1176, 'FW Caldari State Destroyer' FROM DUAL
UNION ALL SELECT 11, 1177, 'FW Gallente Federation Destroyer' FROM DUAL
UNION ALL SELECT 11, 1178, 'FW Minmatar Republic Destroyer' FROM DUAL
UNION ALL SELECT 11, 1179, 'FW Amarr Empire Cruiser' FROM DUAL
UNION ALL SELECT 11, 1180, 'FW Caldari State Cruiser' FROM DUAL
UNION ALL SELECT 11, 1181, 'FW Gallente Federation Cruiser' FROM DUAL
UNION ALL SELECT 11, 1182, 'FW Minmatar Republic Cruiser' FROM DUAL
UNION ALL SELECT 11, 1183, 'FW Amarr Empire Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 1184, 'FW Caldari State Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 1185, 'FW Gallente Federation Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 1186, 'FW Minmatar Republic Battlecruiser' FROM DUAL
UNION ALL SELECT 7, 1189, 'Micro Jump Drive' FROM DUAL
UNION ALL SELECT 9, 1190, 'Salvage Drone Blueprint' FROM DUAL
UNION ALL SELECT 9, 1191, 'Micro Jump Drive Blueprint' FROM DUAL
UNION ALL SELECT 63, 1194, 'Special Edition Commodities' FROM DUAL
UNION ALL SELECT 63, 1195, 'Tournament Cards: New Eden Open YC 114' FROM DUAL
UNION ALL SELECT 9, 1197, 'Special Edition Commodity Blueprints' FROM DUAL
UNION ALL SELECT 2, 1198, 'Orbital Target' FROM DUAL
UNION ALL SELECT 7, 1199, 'Ancillary Armor Repairer' FROM DUAL
UNION ALL SELECT 9, 1200, 'Fueled Armor Repairer Blueprint' FROM DUAL
UNION ALL SELECT 6, 1201, 'Attack Battlecruiser' FROM DUAL
UNION ALL SELECT 6, 1202, 'Blockade Runner' FROM DUAL
UNION ALL SELECT 17, 1206, 'Security Tags' FROM DUAL
UNION ALL SELECT 11, 1207, 'Scatter Container' FROM DUAL
UNION ALL SELECT 16, 1209, 'Shields' FROM DUAL
UNION ALL SELECT 16, 1210, 'Armor' FROM DUAL
UNION ALL SELECT 23, 1212, 'Personal Hangar' FROM DUAL
UNION ALL SELECT 16, 1213, 'Targeting' FROM DUAL
UNION ALL SELECT 16, 1216, 'Engineering' FROM DUAL
UNION ALL SELECT 16, 1217, 'Scanning' FROM DUAL
UNION ALL SELECT 16, 1218, 'Resource Processing' FROM DUAL
UNION ALL SELECT 16, 1220, 'Neural Enhancement' FROM DUAL
UNION ALL SELECT 9, 1222, 'ECM Stabilizer Blueprint' FROM DUAL
UNION ALL SELECT 7, 1223, 'Scanning Upgrade' FROM DUAL
UNION ALL SELECT 9, 1224, 'Scanning Upgrade Blueprint' FROM DUAL
UNION ALL SELECT 63, 1225, 'Tournament Cards: Alliance Tournament All Stars' FROM DUAL
UNION ALL SELECT 7, 1226, 'Survey Probe Launcher' FROM DUAL
UNION ALL SELECT 9, 1227, 'Survey Probe Launcher Blueprint' FROM DUAL
UNION ALL SELECT 20, 1228, 'Cyber Targeting' FROM DUAL
UNION ALL SELECT 20, 1229, 'Cyber Resource Processing' FROM DUAL
UNION ALL SELECT 20, 1230, 'Cyber Scanning' FROM DUAL
UNION ALL SELECT 20, 1231, 'Cyber Biology' FROM DUAL
UNION ALL SELECT 7, 1232, 'Rig Resource Processing' FROM DUAL
UNION ALL SELECT 7, 1233, 'Rig Scanning' FROM DUAL
UNION ALL SELECT 7, 1234, 'Rig Targeting' FROM DUAL
UNION ALL SELECT 7, 1238, 'Scanning Upgrade Time' FROM DUAL
UNION ALL SELECT 9, 1239, 'Scanning Upgrade Time Blueprint' FROM DUAL
UNION ALL SELECT 16, 1240, 'Subsystems' FROM DUAL
UNION ALL SELECT 16, 1241, 'Planet Management' FROM DUAL
UNION ALL SELECT 7, 1245, 'Missile Launcher Rapid Heavy' FROM DUAL
UNION ALL SELECT 22, 1246, 'Mobile Depot' FROM DUAL
UNION ALL SELECT 22, 1247, 'Mobile Siphon Unit' FROM DUAL
UNION ALL SELECT 17, 1248, 'Bounty Encrypted Bonds' FROM DUAL
UNION ALL SELECT 22, 1249, 'Mobile Cyno Inhibitor' FROM DUAL
UNION ALL SELECT 22, 1250, 'Mobile Tractor Unit' FROM DUAL
UNION ALL SELECT 11, 1252, 'Ghost Sites Angel Cartel Cruiser' FROM DUAL
UNION ALL SELECT 11, 1255, 'Ghost Sites Blood Raiders Cruiser' FROM DUAL
UNION ALL SELECT 11, 1259, 'Ghost Sites Guristas Cruiser' FROM DUAL
UNION ALL SELECT 11, 1262, 'Ghost Sites Serpentis Cruiser' FROM DUAL
UNION ALL SELECT 11, 1265, 'Ghost Sites Sanshas Cruiser' FROM DUAL
UNION ALL SELECT 9, 1267, 'Mobile Siphon Unit Blueprint' FROM DUAL
UNION ALL SELECT 9, 1268, 'Mobile Cynosural Inhibitor Blueprint' FROM DUAL
UNION ALL SELECT 9, 1269, 'Mobile Depot Blueprint' FROM DUAL
UNION ALL SELECT 9, 1270, 'Mobile Tractor Unit Blueprint' FROM DUAL
UNION ALL SELECT 30, 1271, 'Prosthetics' FROM DUAL
UNION ALL SELECT 22, 1273, 'Encounter Surveillance System' FROM DUAL
UNION ALL SELECT 22, 1274, 'Mobile Decoy Unit' FROM DUAL
UNION ALL SELECT 22, 1275, 'Mobile Scan Inhibitor' FROM DUAL
UNION ALL SELECT 22, 1276, 'Mobile Micro Jump Unit' FROM DUAL
UNION ALL SELECT 9, 1277, 'Encounter Surveillance System Blueprint' FROM DUAL
UNION ALL SELECT 23, 1282, 'Compression Array' FROM DUAL
UNION ALL SELECT 6, 1283, 'Expedition Frigate' FROM DUAL
UNION ALL SELECT 11, 1285, 'Asteroid Mordus Legion Commander Frigate' FROM DUAL
UNION ALL SELECT 11, 1286, 'Asteroid Mordus Legion Commander Cruiser' FROM DUAL
UNION ALL SELECT 11, 1287, 'Asteroid Mordus Legion Commander Battleship' FROM DUAL
UNION ALL SELECT 11, 1288, 'Ghost Sites Mordu''s Legion' FROM DUAL
UNION ALL SELECT 7, 1289, 'Warp Accelerator' FROM DUAL
UNION ALL SELECT 7, 1292, 'Drone Tracking Enhancer' FROM DUAL
UNION ALL SELECT 9, 1293, 'Mobile Scan Inhibitor Blueprint' FROM DUAL
UNION ALL SELECT 9, 1294, 'Mobile Micro Jump Unit Blueprint' FROM DUAL
UNION ALL SELECT 9, 1295, 'Mobile Decoy Unit Blueprint' FROM DUAL
UNION ALL SELECT 22, 1297, 'Mobile Vault' FROM DUAL
UNION ALL SELECT 7, 1299, 'Jump Drive Economizer' FROM DUAL
UNION ALL SELECT 5, 1301, 'Services' FROM DUAL
UNION ALL SELECT 35, 1304, 'Generic Decryptor' FROM DUAL
UNION ALL SELECT 6, 1305, 'Tactical Destroyer' FROM DUAL
UNION ALL SELECT 7, 1306, 'Ship Modifiers' FROM DUAL
UNION ALL SELECT 11, 1307, 'Roaming Sleepers Cruiser' FROM DUAL
UNION ALL SELECT 7, 1308, 'Rig Anchor' FROM DUAL
UNION ALL SELECT 9, 1309, 'Tactical Destroyer Blueprint' FROM DUAL
UNION ALL SELECT 11, 1310, 'Drifter Battleship' FROM DUAL
UNION ALL SELECT 5, 1311, 'Super Kerr-Induced Nanocoatings' FROM DUAL
UNION ALL SELECT 22, 1312, 'Observatory Structures' FROM DUAL
UNION ALL SELECT 7, 1313, 'Entosis Link' FROM DUAL
UNION ALL SELECT 17, 1314, 'Unknown Components' FROM DUAL
UNION ALL SELECT 2, 1316, 'Entosis Command Node' FROM DUAL
UNION ALL SELECT 9, 1317, 'Infrastructure Upgrade Blueprint' FROM DUAL
UNION ALL SELECT 9, 1318, 'Entosis Link Blueprint' FROM DUAL
UNION ALL SELECT 29, 1319, 'Miscellaneous' FROM DUAL
UNION ALL SELECT 66, 1321, 'Structure Citadel Service Module' FROM DUAL
UNION ALL SELECT 66, 1322, 'Structure Resource Processing Service Module' FROM DUAL
UNION ALL SELECT 66, 1323, 'Structure Observatory Service Module' FROM DUAL
UNION ALL SELECT 66, 1324, 'Structure Navigation Service Module' FROM DUAL
UNION ALL SELECT 66, 1325, 'Structure Administration Service Module' FROM DUAL
UNION ALL SELECT 66, 1326, 'Structure Advertisement Service Module' FROM DUAL
UNION ALL SELECT 66, 1327, 'Structure XL Missile Launcher' FROM DUAL
UNION ALL SELECT 66, 1328, 'Structure Guided Bomb Launcher' FROM DUAL
UNION ALL SELECT 66, 1329, 'Structure Energy Neutralizer' FROM DUAL
UNION ALL SELECT 66, 1330, 'Structure Area Denial Module' FROM DUAL
UNION ALL SELECT 66, 1331, 'Structure Burst Projector' FROM DUAL
UNION ALL SELECT 66, 1332, 'Structure ECM Battery' FROM DUAL
;
INSERT INTO BASE_GROUPS (category_id, group_id, name)
          SELECT 66, 1333, 'Structure Doomsday Weapon' FROM DUAL
UNION ALL SELECT 7, 1395, 'Missile Guidance Enhancer' FROM DUAL
UNION ALL SELECT 7, 1396, 'Missile Guidance Computer' FROM DUAL
UNION ALL SELECT 9, 1397, 'Missile Guidance Enhancer Blueprint' FROM DUAL
UNION ALL SELECT 9, 1399, 'Missile Guidance Computer Blueprint' FROM DUAL
UNION ALL SELECT 8, 1400, 'Missile Guidance Script' FROM DUAL
UNION ALL SELECT 11, 1402, 'Amarr Navy Roaming Battleship' FROM DUAL
UNION ALL SELECT 65, 1404, 'Engineering Complex' FROM DUAL
UNION ALL SELECT 65, 1405, 'Laboratory' FROM DUAL
UNION ALL SELECT 65, 1406, 'Refinery' FROM DUAL
UNION ALL SELECT 65, 1407, 'Observatory Array' FROM DUAL
UNION ALL SELECT 65, 1408, 'Upwell Jump Gate' FROM DUAL
UNION ALL SELECT 65, 1409, 'Administration Hub' FROM DUAL
UNION ALL SELECT 65, 1410, 'Advertisement Center' FROM DUAL
UNION ALL SELECT 11, 1411, 'Amarr Navy Roaming Cruiser' FROM DUAL
UNION ALL SELECT 11, 1412, 'Amarr Navy Roaming Capital' FROM DUAL
UNION ALL SELECT 11, 1413, 'Amarr Navy Roaming Logistics' FROM DUAL
UNION ALL SELECT 11, 1414, 'Amarr Navy Roaming Frigate' FROM DUAL
UNION ALL SELECT 66, 1415, 'Structure Engineering Service Module' FROM DUAL
UNION ALL SELECT 66, 1416, 'Structure Research Service Module' FROM DUAL
UNION ALL SELECT 66, 1429, 'Structure Weapon Upgrade' FROM DUAL
UNION ALL SELECT 66, 1430, 'Structure Fitting Module' FROM DUAL
UNION ALL SELECT 66, 1441, 'Structure Stasis Webifier' FROM DUAL
UNION ALL SELECT 66, 1442, 'Structure Warp Scrambler' FROM DUAL
UNION ALL SELECT 11, 1452, 'Irregular Drone' FROM DUAL
UNION ALL SELECT 11, 1453, 'Irregular EW Drone' FROM DUAL
UNION ALL SELECT 11, 1454, 'Irregular Fighter' FROM DUAL
UNION ALL SELECT 11, 1455, 'Irregular Fighter Squadron' FROM DUAL
UNION ALL SELECT 9, 1461, 'Unknown Blueprint' FROM DUAL
UNION ALL SELECT 9, 1462, 'Structure Blueprints' FROM DUAL
UNION ALL SELECT 11, 1465, 'Mission Generic Supercarrier' FROM DUAL
UNION ALL SELECT 6, 1527, 'Logistics Frigate' FROM DUAL
UNION ALL SELECT 11, 1529, 'Deadspace Sleeper Upgraded Avenger' FROM DUAL
UNION ALL SELECT 11, 1530, 'Drifter Response Battleship' FROM DUAL
UNION ALL SELECT 7, 1533, 'Micro Jump Field Generators' FROM DUAL
UNION ALL SELECT 6, 1534, 'Command Destroyer' FROM DUAL
UNION ALL SELECT 66, 1535, 'Structure Signal Amplifier' FROM DUAL
UNION ALL SELECT 87, 1537, 'Support Fighter' FROM DUAL
UNION ALL SELECT 6, 1538, 'Force Auxiliary' FROM DUAL
UNION ALL SELECT 9, 1542, 'Command Destroyer Blueprint' FROM DUAL
UNION ALL SELECT 9, 1543, 'Micro Jump Field Generator Blueprint' FROM DUAL
UNION ALL SELECT 16, 1545, 'Structure Management' FROM DUAL
UNION ALL SELECT 8, 1546, 'Structure Anti-Capital Missile' FROM DUAL
UNION ALL SELECT 8, 1547, 'Structure Anti-Subcapital Missile' FROM DUAL
UNION ALL SELECT 8, 1548, 'Guided Bomb' FROM DUAL
UNION ALL SELECT 8, 1549, 'Structure ECM script' FROM DUAL
UNION ALL SELECT 8, 1550, 'Structure Stasis Webifier Script' FROM DUAL
UNION ALL SELECT 8, 1551, 'Structure Warp Disruptor Script' FROM DUAL
UNION ALL SELECT 8, 1559, 'Structure Resistance Switcher Script' FROM DUAL
UNION ALL SELECT 66, 1562, 'Structure Multirole Missile Launcher' FROM DUAL
UNION ALL SELECT 11, 1566, 'Irregular Shuttle' FROM DUAL
UNION ALL SELECT 11, 1567, 'Irregular Corvette' FROM DUAL
UNION ALL SELECT 11, 1568, 'Irregular Frigate' FROM DUAL
UNION ALL SELECT 8, 1569, 'Guidance Disruption Script' FROM DUAL
UNION ALL SELECT 66, 1570, 'Structure Assembly Rig M - Small Tech I Ship' FROM DUAL
UNION ALL SELECT 66, 1579, 'Structure Assembly Rig M - Medium Tech I Ship' FROM DUAL
UNION ALL SELECT 66, 1580, 'Structure Assembly Rig M - Large Tech I Ship' FROM DUAL
UNION ALL SELECT 66, 1581, 'Structure Assembly Rig M - Small Tech II Ship' FROM DUAL
UNION ALL SELECT 66, 1582, 'Structure Assembly Rig M - Medium Tech II Ship' FROM DUAL
UNION ALL SELECT 66, 1583, 'Structure Assembly Rig M - Large Tech II Ship' FROM DUAL
UNION ALL SELECT 66, 1584, 'Structure Assembly Rig M - Drone' FROM DUAL
UNION ALL SELECT 66, 1585, 'Structure Assembly Rig M - Consumable' FROM DUAL
UNION ALL SELECT 66, 1586, 'Structure Assembly Rig M - Module' FROM DUAL
UNION ALL SELECT 66, 1587, 'Structure Assembly Rig L - Module, Consumable, Drone' FROM DUAL
UNION ALL SELECT 66, 1588, 'Structure Assembly Rig L - Tech I Ship' FROM DUAL
UNION ALL SELECT 66, 1589, 'Structure Assembly Rig L - Tech II Ship' FROM DUAL
UNION ALL SELECT 66, 1590, 'Structure Assembly Rig L - Tech II Component' FROM DUAL
UNION ALL SELECT 66, 1591, 'Structure Assembly Rig L - Tech I Capital Component' FROM DUAL
UNION ALL SELECT 66, 1592, 'Structure Assembly Rig L - Structure Component' FROM DUAL
UNION ALL SELECT 66, 1593, 'Structure Assembly Rig L - Subsystem' FROM DUAL
UNION ALL SELECT 66, 1594, 'Structure Assembly Rig XL - Equipment' FROM DUAL
UNION ALL SELECT 66, 1595, 'Structure Assembly Rig XL - Ship' FROM DUAL
UNION ALL SELECT 66, 1596, 'Structure Assembly Rig XL - All Components' FROM DUAL
UNION ALL SELECT 66, 1598, 'Structure Laboratory Rig M - Small Ship' FROM DUAL
UNION ALL SELECT 66, 1599, 'Structure Laboratory Rig M - Medium Ship' FROM DUAL
UNION ALL SELECT 66, 1600, 'Structure Laboratory Rig M - Large Ship' FROM DUAL
UNION ALL SELECT 66, 1601, 'Structure Laboratory Rig M - Drone' FROM DUAL
UNION ALL SELECT 66, 1602, 'Structure Laboratory Rig M - Consumable' FROM DUAL
UNION ALL SELECT 66, 1603, 'Structure Laboratory Rig M - Module' FROM DUAL
UNION ALL SELECT 66, 1604, 'Structure Laboratory Rig L - Module, Consumable, Drone' FROM DUAL
UNION ALL SELECT 66, 1605, 'Structure Laboratory Rig L - Ship' FROM DUAL
UNION ALL SELECT 66, 1606, 'Structure Laboratory Rig L - Component' FROM DUAL
UNION ALL SELECT 66, 1607, 'Structure Laboratory Rig L - Capital Component' FROM DUAL
UNION ALL SELECT 66, 1608, 'Structure Laboratory Rig L - Structure Component' FROM DUAL
UNION ALL SELECT 66, 1609, 'Structure Laboratory Rig L - Subsystem' FROM DUAL
UNION ALL SELECT 66, 1610, 'Structure Laboratory Rig XL - Equipment' FROM DUAL
UNION ALL SELECT 66, 1611, 'Structure Laboratory Rig XL - Ship' FROM DUAL
UNION ALL SELECT 66, 1612, 'Structure Laboratory Rig XL - All Components' FROM DUAL
UNION ALL SELECT 66, 1613, 'Structure Combat Rig M - Missile Application' FROM DUAL
UNION ALL SELECT 66, 1614, 'Structure Combat Rig M - Missile Projection' FROM DUAL
UNION ALL SELECT 66, 1615, 'Structure Combat Rig M - Energy Neutralizer Projection' FROM DUAL
UNION ALL SELECT 66, 1616, 'Structure Combat Rig M - Energy Neutralizer Cap Reduction' FROM DUAL
UNION ALL SELECT 66, 1617, 'Structure Citadel Rig M - Drone Hitpoints' FROM DUAL
UNION ALL SELECT 66, 1618, 'Structure Citadel Rig M - Drone Speed' FROM DUAL
UNION ALL SELECT 66, 1619, 'Structure Combat Rig M - EW projection' FROM DUAL
UNION ALL SELECT 66, 1620, 'Structure Combat Rig M - EW Cap Reduction' FROM DUAL
UNION ALL SELECT 66, 1621, 'Structure Combat Rig M - Max Targets' FROM DUAL
UNION ALL SELECT 66, 1622, 'Structure Combat Rig M - Boosted Sensors' FROM DUAL
UNION ALL SELECT 66, 1629, 'Structure Combat Rig L - Missile Application and Projection' FROM DUAL
UNION ALL SELECT 66, 1630, 'Structure Combat Rig L - Energy Neutralizer Projection and Cap Reduction' FROM DUAL
UNION ALL SELECT 66, 1631, 'Structure Citadel Rig L - Drone Hitpoints and Speed' FROM DUAL
UNION ALL SELECT 66, 1632, 'Structure Combat Rig L - EW Projection and Cap Reduction' FROM DUAL
UNION ALL SELECT 66, 1633, 'Structure Combat Rig L - AoE Launcher Application and Projection' FROM DUAL
UNION ALL SELECT 66, 1634, 'Structure Combat Rig L - Point Defense Battery Application and Projection' FROM DUAL
UNION ALL SELECT 66, 1635, 'Structure Combat Rig L - Max Targets and Sensor Boosting' FROM DUAL
UNION ALL SELECT 66, 1639, 'Structure Combat Rig XL - Missile and AoE Missile' FROM DUAL
UNION ALL SELECT 66, 1640, 'Structure Citadel Rig XL - Drone and PDB' FROM DUAL
UNION ALL SELECT 66, 1641, 'Structure Combat Rig XL - Energy Neutralizer and EW' FROM DUAL
UNION ALL SELECT 66, 1642, 'Structure Combat Rig XL - Doomsday and Targeting' FROM DUAL
UNION ALL SELECT 66, 1647, 'OLD Structure Resource Rig M - HS Ore Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1648, 'OLD Structure Resource Rig L - Ore Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1649, 'OLD Structure Resource Rig XL - Reprocessing' FROM DUAL
UNION ALL SELECT 87, 1652, 'Light Fighter' FROM DUAL
UNION ALL SELECT 87, 1653, 'Heavy Fighter' FROM DUAL
UNION ALL SELECT 65, 1657, 'Citadel' FROM DUAL
UNION ALL SELECT 11, 1664, 'Irregular Destroyer' FROM DUAL
UNION ALL SELECT 11, 1665, 'Irregular Cruiser' FROM DUAL
UNION ALL SELECT 11, 1666, 'Irregular Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 1667, 'Irregular Battleship' FROM DUAL
UNION ALL SELECT 30, 1670, 'Augmentations' FROM DUAL
UNION ALL SELECT 7, 1672, 'Stasis Grappler' FROM DUAL
UNION ALL SELECT 7, 1673, 'Missile Launcher Rapid Torpedo' FROM DUAL
UNION ALL SELECT 7, 1674, 'Missile Launcher XL Cruise' FROM DUAL
UNION ALL SELECT 4, 1676, 'Named Components' FROM DUAL
UNION ALL SELECT 8, 1677, 'Advanced XL Torpedo' FROM DUAL
UNION ALL SELECT 8, 1678, 'Advanced XL Cruise Missile' FROM DUAL
UNION ALL SELECT 9, 1679, 'Support Fighter Blueprint' FROM DUAL
UNION ALL SELECT 11, 1681, 'Asteroid Angel Cartel Dreadnought' FROM DUAL
UNION ALL SELECT 11, 1682, 'Asteroid Angel Cartel Titan' FROM DUAL
UNION ALL SELECT 11, 1683, 'Asteroid Blood Raider Dreadnought' FROM DUAL
UNION ALL SELECT 11, 1684, 'Asteroid Blood Raider Titan' FROM DUAL
UNION ALL SELECT 11, 1685, 'Asteroid Guristas Dreadnought' FROM DUAL
UNION ALL SELECT 11, 1686, 'Asteroid Guristas Titan' FROM DUAL
UNION ALL SELECT 11, 1687, 'Asteroid Sansha''s Nation Dreadnought' FROM DUAL
UNION ALL SELECT 11, 1688, 'Asteroid Sansha''s Nation Supercarrier' FROM DUAL
UNION ALL SELECT 11, 1689, 'Asteroid Serpentis Dreadnought' FROM DUAL
UNION ALL SELECT 11, 1690, 'Asteroid Serpentis Titan' FROM DUAL
UNION ALL SELECT 11, 1691, 'Asteroid Rogue Drone Carrier' FROM DUAL
UNION ALL SELECT 11, 1692, 'Asteroid Rogue Drone Supercarrier' FROM DUAL
UNION ALL SELECT 66, 1693, 'OLD Structure Resource Rig M - LNS Ore Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1694, 'OLD Structure Resource Rig M - Ice 1 Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1695, 'OLD Structure Resource Rig M - Ice 2 Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1696, 'OLD Structure Resource Rig L - Ice Reprocessing' FROM DUAL
UNION ALL SELECT 7, 1697, 'Ancillary Remote Shield Booster' FROM DUAL
UNION ALL SELECT 7, 1698, 'Ancillary Remote Armor Repairer' FROM DUAL
UNION ALL SELECT 7, 1699, 'Flex Armor Hardener' FROM DUAL
UNION ALL SELECT 7, 1700, 'Flex Shield Hardener' FROM DUAL
UNION ALL SELECT 8, 1701, 'Flex Armor Hardener Script' FROM DUAL
UNION ALL SELECT 8, 1702, 'Flex Shield Hardener Script' FROM DUAL
UNION ALL SELECT 9, 1703, 'Burst Projector Blueprint' FROM DUAL
UNION ALL SELECT 2, 1704, 'Super Weapon Beacon' FROM DUAL
UNION ALL SELECT 7, 1706, 'Capital Sensor Array' FROM DUAL
UNION ALL SELECT 9, 1707, 'Structure Module Blueprint' FROM DUAL
UNION ALL SELECT 9, 1708, 'Structure Rig Blueprint' FROM DUAL
UNION ALL SELECT 9, 1709, 'Unpublished Structure Module and Rig Blueprints' FROM DUAL
UNION ALL SELECT 66, 1717, 'Unpublished Structure Modules' FROM DUAL
UNION ALL SELECT 9, 1718, 'Force Auxiliary Blueprint' FROM DUAL
UNION ALL SELECT 66, 1719, 'Structure Disruption Battery' FROM DUAL
UNION ALL SELECT 11, 1720, 'Roaming Serpentis Battleship' FROM DUAL
UNION ALL SELECT 11, 1721, 'Roaming Serpentis Cruiser' FROM DUAL
UNION ALL SELECT 11, 1722, 'Roaming Serpentis Frigate' FROM DUAL
UNION ALL SELECT 9, 1723, 'Shield Resistance Shift Hardener Blueprint' FROM DUAL
UNION ALL SELECT 11, 1724, 'Irregular Dreadnought' FROM DUAL
UNION ALL SELECT 11, 1725, 'Irregular Force Auxiliary' FROM DUAL
UNION ALL SELECT 11, 1726, 'Irregular Carrier' FROM DUAL
UNION ALL SELECT 11, 1727, 'Roaming Angel Cartel Battleship' FROM DUAL
UNION ALL SELECT 11, 1728, 'Roaming Angel Cartel Cruiser' FROM DUAL
UNION ALL SELECT 11, 1729, 'Roaming Angel Cartel Frigate' FROM DUAL
UNION ALL SELECT 20, 1730, 'Special Edition Implant' FROM DUAL
UNION ALL SELECT 11, 1731, 'Irregular Super Carrier' FROM DUAL
UNION ALL SELECT 11, 1734, 'Roaming Blood Raider Frigate' FROM DUAL
UNION ALL SELECT 11, 1735, 'Roaming Blood Raider Cruiser' FROM DUAL
UNION ALL SELECT 11, 1736, 'Roaming Blood Raider Battlecruiser' FROM DUAL
UNION ALL SELECT 11, 1737, 'Roaming Blood Raider Battleship' FROM DUAL
UNION ALL SELECT 5, 1739, 'Skill Injectors' FROM DUAL
UNION ALL SELECT 11, 1759, 'Irregular Titan' FROM DUAL
UNION ALL SELECT 11, 1761, 'Irregular Mining Frigate' FROM DUAL
UNION ALL SELECT 11, 1762, 'Irregular Mining Barge' FROM DUAL
UNION ALL SELECT 11, 1764, '♦ Mining Frigate' FROM DUAL
UNION ALL SELECT 11, 1765, '♦ Mining Barge' FROM DUAL
UNION ALL SELECT 11, 1766, '♦ Mining Exhumer' FROM DUAL
UNION ALL SELECT 11, 1767, '♦ Mining Hauler' FROM DUAL
UNION ALL SELECT 11, 1768, 'Seeker Scouts' FROM DUAL
UNION ALL SELECT 8, 1769, 'Shield Command Burst Charges' FROM DUAL
UNION ALL SELECT 7, 1770, 'Command Burst' FROM DUAL
UNION ALL SELECT 8, 1771, 'Mining Foreman Burst Charges' FROM DUAL
UNION ALL SELECT 8, 1772, 'Skirmish Command Burst Charges' FROM DUAL
UNION ALL SELECT 8, 1773, 'Information Command Burst Charges' FROM DUAL
UNION ALL SELECT 8, 1774, 'Armor Command Burst Charges' FROM DUAL
UNION ALL SELECT 11, 1788, 'Hidden Zenith Drifters' FROM DUAL
UNION ALL SELECT 11, 1789, 'Hidden Zenith Amarr Battleship' FROM DUAL
UNION ALL SELECT 11, 1790, 'Hidden Zenith Amarr Cruiser' FROM DUAL
UNION ALL SELECT 11, 1791, 'Hidden Zenith Amarr Frigate' FROM DUAL
UNION ALL SELECT 11, 1792, 'Hidden Zenith Caldari Battleship ' FROM DUAL
UNION ALL SELECT 11, 1793, 'Hidden Zenith Caldari Cruiser' FROM DUAL
UNION ALL SELECT 11, 1794, 'Hidden Zenith Caldari Frigate' FROM DUAL
UNION ALL SELECT 11, 1795, 'Hidden Zenith Gallente Battleship' FROM DUAL
UNION ALL SELECT 11, 1796, 'Hidden Zenith Gallente Cruiser' FROM DUAL
UNION ALL SELECT 11, 1797, 'Hidden Zenith Gallente Frigate' FROM DUAL
UNION ALL SELECT 11, 1798, 'Hidden Zenith Minmatar Battleship' FROM DUAL
UNION ALL SELECT 11, 1799, 'Hidden Zenith Minmatar Cruiser' FROM DUAL
UNION ALL SELECT 11, 1800, 'Hidden Zenith Minmatar Frigate' FROM DUAL
UNION ALL SELECT 11, 1803, '♦ Frigate' FROM DUAL
UNION ALL SELECT 11, 1804, 'Hidden Zenith Amarr Capital' FROM DUAL
UNION ALL SELECT 11, 1805, 'Hidden Zenith Caldari Capital' FROM DUAL
UNION ALL SELECT 11, 1806, 'Hidden Zenith Gallente Capital' FROM DUAL
UNION ALL SELECT 11, 1807, 'Hidden Zenith Minmatar Capital' FROM DUAL
UNION ALL SELECT 9, 1810, 'Command Burst Charge Blueprint' FROM DUAL
UNION ALL SELECT 9, 1812, 'Command Burst Blueprint' FROM DUAL
UNION ALL SELECT 11, 1813, '♦ Cruiser' FROM DUAL
UNION ALL SELECT 11, 1814, '♦ Battleship' FROM DUAL
UNION ALL SELECT 7, 1815, 'Titan Phenomena Generator' FROM DUAL
UNION ALL SELECT 66, 1816, 'Structure Engineering Rig M - Equipment ME' FROM DUAL
UNION ALL SELECT 17, 1818, 'Strong Boxes' FROM DUAL
UNION ALL SELECT 66, 1819, 'Structure Engineering Rig M - Equipment TE' FROM DUAL
UNION ALL SELECT 66, 1820, 'Structure Engineering Rig M - Ammunition ME' FROM DUAL
UNION ALL SELECT 66, 1821, 'Structure Engineering Rig M - Ammunition TE' FROM DUAL
UNION ALL SELECT 66, 1822, 'Structure Engineering Rig M - Drone and Fighter ME' FROM DUAL
UNION ALL SELECT 66, 1823, 'Structure Engineering Rig M - Drone and Fighter TE' FROM DUAL
UNION ALL SELECT 66, 1824, 'Structure Engineering Rig M - Basic Small Ship ME' FROM DUAL
UNION ALL SELECT 66, 1825, 'Structure Engineering Rig M - Basic Small Ship TE' FROM DUAL
UNION ALL SELECT 66, 1826, 'Structure Engineering Rig M - Basic Medium Ship ME' FROM DUAL
UNION ALL SELECT 66, 1827, 'Structure Engineering Rig M - Basic Medium Ship TE' FROM DUAL
UNION ALL SELECT 66, 1828, 'Structure Engineering Rig M - Basic Large Ship ME' FROM DUAL
UNION ALL SELECT 66, 1829, 'Structure Engineering Rig M - Basic Large Ship TE' FROM DUAL
UNION ALL SELECT 66, 1830, 'Structure Engineering Rig M - Advanced Small Ship ME' FROM DUAL
UNION ALL SELECT 66, 1831, 'Structure Engineering Rig M - Advanced Small Ship TE' FROM DUAL
UNION ALL SELECT 66, 1832, 'Structure Engineering Rig M - Advanced Medium Ship ME' FROM DUAL
UNION ALL SELECT 66, 1833, 'Structure Engineering Rig M - Advanced Medium Ship TE' FROM DUAL
UNION ALL SELECT 66, 1834, 'Structure Engineering Rig M - Advanced Large Ship ME' FROM DUAL
UNION ALL SELECT 66, 1835, 'Structure Engineering Rig M - Advanced Large Ship TE' FROM DUAL
UNION ALL SELECT 66, 1836, 'Structure Engineering Rig M - Advanced Component ME' FROM DUAL
UNION ALL SELECT 66, 1837, 'Structure Engineering Rig M - Advanced Component TE' FROM DUAL
UNION ALL SELECT 66, 1838, 'Structure Engineering Rig M - Basic Capital Component TE' FROM DUAL
UNION ALL SELECT 66, 1839, 'Structure Engineering Rig M - Basic Capital Component ME' FROM DUAL
UNION ALL SELECT 66, 1840, 'Structure Engineering Rig M - Structure ME' FROM DUAL
UNION ALL SELECT 66, 1841, 'Structure Engineering Rig M - Structure TE' FROM DUAL
UNION ALL SELECT 66, 1842, 'Structure Engineering Rig M - Invention Cost Optimization' FROM DUAL
UNION ALL SELECT 66, 1843, 'Structure Engineering Rig M - Invention Accelerator' FROM DUAL
UNION ALL SELECT 66, 1844, 'Structure Engineering Rig M - ME Research Cost Optimization' FROM DUAL
UNION ALL SELECT 66, 1845, 'Structure Engineering Rig M - ME Research Accelerator' FROM DUAL
UNION ALL SELECT 66, 1846, 'Structure Engineering Rig M - TE Research Cost Optimization' FROM DUAL
UNION ALL SELECT 66, 1847, 'Structure Engineering Rig M - TE Research Accelerator' FROM DUAL
UNION ALL SELECT 66, 1848, 'Structure Engineering Rig M - Blueprint Copy Cost Optimization' FROM DUAL
UNION ALL SELECT 66, 1849, 'Structure Engineering Rig M - Blueprint Copy Accelerator' FROM DUAL
UNION ALL SELECT 66, 1850, 'Structure Engineering Rig L - Equipment Efficiency' FROM DUAL
UNION ALL SELECT 66, 1851, 'Structure Engineering Rig L - Ammunition Efficiency' FROM DUAL
UNION ALL SELECT 66, 1852, 'Structure Engineering Rig L - Drone and Fighter Efficiency' FROM DUAL
UNION ALL SELECT 66, 1853, 'Structure Engineering Rig L - Basic Small Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1854, 'Structure Engineering Rig L - Basic Medium Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1855, 'Structure Engineering Rig L - Basic Large Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1856, 'Structure Engineering Rig L - Advanced Small Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1857, 'Structure Engineering Rig L - Advanced Medium Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1858, 'Structure Engineering Rig L - Advanced Large Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1859, 'Structure Engineering Rig L - Capital Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1860, 'Structure Engineering Rig L - Advanced Component Efficiency' FROM DUAL
UNION ALL SELECT 66, 1861, 'Structure Engineering Rig L - Basic Capital Component Efficiency' FROM DUAL
UNION ALL SELECT 66, 1862, 'Structure Engineering Rig L - Structure Efficiency' FROM DUAL
UNION ALL SELECT 66, 1863, 'Structure Engineering Rig L - Invention Optimization' FROM DUAL
UNION ALL SELECT 66, 1864, 'Structure Engineering Rig L - ME Research Optimization' FROM DUAL
UNION ALL SELECT 66, 1865, 'Structure Engineering Rig L - TE Research Optimization' FROM DUAL
UNION ALL SELECT 66, 1866, 'Structure Engineering Rig L - Blueprint Copy Optimization' FROM DUAL
UNION ALL SELECT 66, 1867, 'Structure Engineering Rig XL - Equipment and Consumable Efficiency' FROM DUAL
UNION ALL SELECT 66, 1868, 'Structure Engineering Rig XL - Ship Efficiency' FROM DUAL
UNION ALL SELECT 66, 1869, 'Structure Engineering Rig XL - Structure and Component Efficiency' FROM DUAL
UNION ALL SELECT 66, 1870, 'Structure Engineering Rig XL - Laboratory Optimization' FROM DUAL
UNION ALL SELECT 11, 1872, 'Structure Entities' FROM DUAL
UNION ALL SELECT 5, 1875, 'PLEX' FROM DUAL
UNION ALL SELECT 65, 1876, '♦ Engineering Complex' FROM DUAL
UNION ALL SELECT 11, 1878, '♦ Titan' FROM DUAL
UNION ALL SELECT 11, 1879, '♦ Force Auxiliary' FROM DUAL
UNION ALL SELECT 11, 1880, '♦ Dreadnought' FROM DUAL
UNION ALL SELECT 2, 1882, 'MassiveEnvironments' FROM DUAL
UNION ALL SELECT 25, 1884, 'Ubiquitous Moon Asteroids' FROM DUAL
UNION ALL SELECT 17, 1886, 'Technical Data Chips' FROM DUAL
UNION ALL SELECT 66, 1887, 'Structure Moon Drilling Service Module' FROM DUAL
UNION ALL SELECT 9, 1888, 'Composite Reaction Formulas' FROM DUAL
UNION ALL SELECT 9, 1889, 'Polymer Reaction Formulas' FROM DUAL
UNION ALL SELECT 9, 1890, 'Biochemical Reaction Formulas' FROM DUAL
UNION ALL SELECT 9, 1891, 'Depricated Subsystem Blueprints' FROM DUAL
UNION ALL SELECT 7, 1894, 'Non-Repeating Hardeners' FROM DUAL
UNION ALL SELECT 11, 1895, 'Irregular Industrial' FROM DUAL
UNION ALL SELECT 11, 1896, '♦ Industrial Command ' FROM DUAL
UNION ALL SELECT 11, 1909, '♦ Battlecruiser' FROM DUAL
UNION ALL SELECT 25, 1911, 'Empire Asteroids' FROM DUAL
UNION ALL SELECT 66, 1912, 'Structure Drilling Rig M - Efficiency' FROM DUAL
UNION ALL SELECT 66, 1913, 'Structure Drilling Rig M - Stability' FROM DUAL
UNION ALL SELECT 66, 1914, 'Structure Drilling Rig L - Proficiency' FROM DUAL
UNION ALL SELECT 2, 1915, 'Moon Mining Beacon' FROM DUAL
UNION ALL SELECT 25, 1920, 'Common Moon Asteroids' FROM DUAL
UNION ALL SELECT 25, 1921, 'Uncommon Moon Asteroids' FROM DUAL
UNION ALL SELECT 25, 1922, 'Rare Moon Asteroids' FROM DUAL
UNION ALL SELECT 25, 1923, 'Exceptional Moon Asteroids' FROM DUAL
UNION ALL SELECT 65, 1924, '♦ Forward Operating Base' FROM DUAL
UNION ALL SELECT 11, 1925, 'Irregular Industrial Command Ship' FROM DUAL
UNION ALL SELECT 11, 1926, 'Irregular Freighter' FROM DUAL
UNION ALL SELECT 11, 1927, 'Irregular Structure' FROM DUAL
UNION ALL SELECT 11, 1928, 'Irregular Container' FROM DUAL
UNION ALL SELECT 11, 1929, 'Irregular - Unidentified' FROM DUAL
UNION ALL SELECT 66, 1933, 'Structure Composite Reactor Rig M - TE' FROM DUAL
UNION ALL SELECT 66, 1934, 'Structure Composite Reactor Rig M - ME' FROM DUAL
UNION ALL SELECT 66, 1935, 'Structure Hybrid Reactor Rig M - TE' FROM DUAL
UNION ALL SELECT 66, 1936, 'Structure Hybrid Reactor Rig M - ME' FROM DUAL
UNION ALL SELECT 66, 1937, 'Structure Biochemical Reactor Rig M - TE' FROM DUAL
UNION ALL SELECT 66, 1938, 'Structure Biochemical Reactor Rig M - ME' FROM DUAL
UNION ALL SELECT 66, 1939, 'Structure Reactor Rig L - Efficiency' FROM DUAL
UNION ALL SELECT 2, 1940, 'Moon Chunk' FROM DUAL
UNION ALL SELECT 66, 1941, 'Structure Resource Rig M - Asteroid Ore Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1942, 'Structure Resource Rig M - Ice Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1943, 'Structure Resource Rig M - Moon Ore Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1944, 'Structure Resource Rig L - Reprocessing' FROM DUAL
UNION ALL SELECT 66, 1945, 'Structure Resource Rig XL - Reprocessing' FROM DUAL
UNION ALL SELECT 9, 1948, 'Cyber Scanning Implant Blueprints' FROM DUAL
UNION ALL SELECT 91, 1950, 'Permanent SKIN' FROM DUAL
UNION ALL SELECT 91, 1951, 'Volatile SKIN' FROM DUAL
UNION ALL SELECT 91, 1952, '7-Day SKIN' FROM DUAL
UNION ALL SELECT 91, 1953, '30-Day SKIN' FROM DUAL
UNION ALL SELECT 91, 1954, '90-Day SKIN' FROM DUAL
UNION ALL SELECT 91, 1955, '1-Year SKIN' FROM DUAL
UNION ALL SELECT 11, 1956, 'Drifter Reinforcements' FROM DUAL
UNION ALL SELECT 66, 1962, 'Structure QA Modules' FROM DUAL
UNION ALL SELECT 17, 1964, 'Mutaplasmids' FROM DUAL
UNION ALL SELECT 66, 1966, 'Structure Capacitor Battery' FROM DUAL
UNION ALL SELECT 66, 1967, 'Structure Capacitor Power Relay' FROM DUAL
UNION ALL SELECT 66, 1968, 'Structure Armor Reinforcer' FROM DUAL
UNION ALL SELECT 7, 1969, 'Abyssal Modules' FROM DUAL
UNION ALL SELECT 2, 1971, 'Abyssal Hazards' FROM DUAL
UNION ALL SELECT 6, 1972, 'Flag Cruiser' FROM DUAL
UNION ALL SELECT 2, 1973, 'Locators' FROM DUAL
UNION ALL SELECT 66, 1974, 'Structure Festival Launcher' FROM DUAL
UNION ALL SELECT 2, 1975, 'Non-Interactable Object' FROM DUAL
UNION ALL SELECT 8, 1976, 'Structure Festival Charges' FROM DUAL
UNION ALL SELECT 17, 1977, 'Trinary Data Vaults' FROM DUAL
UNION ALL SELECT 2, 1978, 'Industrial Support Facility' FROM DUAL
UNION ALL SELECT 17, 1979, 'Abyssal Filaments' FROM DUAL
UNION ALL SELECT 2, 1980, 'Non-Scalable Clouds' FROM DUAL
UNION ALL SELECT 2, 1981, 'Triglavian Support Pylons' FROM DUAL
UNION ALL SELECT 11, 1982, 'Abyssal Spaceship Entities' FROM DUAL
UNION ALL SELECT 2, 1983, 'Abyssal Environment' FROM DUAL
UNION ALL SELECT 66, 1984, 'Outpost Conversion Rigs' FROM DUAL
UNION ALL SELECT 7, 1986, 'Precursor Weapon' FROM DUAL
UNION ALL SELECT 8, 1987, 'Exotic Plasma Charge' FROM DUAL
UNION ALL SELECT 7, 1988, 'Entropic Radiation Sink' FROM DUAL
UNION ALL SELECT 8, 1989, 'Advanced Exotic Plasma Charge' FROM DUAL
UNION ALL SELECT 9, 1990, 'Precursor Weapon Blueprint' FROM DUAL
UNION ALL SELECT 2, 1991, 'Filament Trace' FROM DUAL
UNION ALL SELECT 9, 1992, 'Entropic Radiation Sink Blueprint' FROM DUAL
UNION ALL SELECT 9, 1993, 'Exotic Plasma Charge Blueprint' FROM DUAL
UNION ALL SELECT 9, 1994, 'Advanced Exotic Plasma Charge Blueprint' FROM DUAL
UNION ALL SELECT 17, 1995, 'Triglavian Data' FROM DUAL
UNION ALL SELECT 4, 1996, 'Abyssal Materials' FROM DUAL
UNION ALL SELECT 11, 1997, 'Abyssal Drone Entities' FROM DUAL
UNION ALL SELECT 2, 1998, 'Station Conversion Monuments' FROM DUAL
UNION ALL SELECT 6, 2001, 'Citizen Ships' FROM DUAL
UNION ALL SELECT 17, 2002, 'Triglavian Datastreams' FROM DUAL
UNION ALL SELECT 7, 2003, 'Citizen Modules' FROM DUAL
UNION ALL SELECT 7, 2004, 'Citizen Mining Laser' FROM DUAL
UNION ALL SELECT 22, 2005, 'Deployable Advertisement' FROM DUAL
UNION ALL SELECT 25, 2006, 'Deadspace Asteroids' FROM DUAL
UNION ALL SELECT 7, 2008, 'Mass Entanglers' FROM DUAL
UNION ALL SELECT 11, 2009, 'Precursor Cache' FROM DUAL
UNION ALL SELECT 9, 2010, 'Mass Entangler Blueprints' FROM DUAL
UNION ALL SELECT 7, 2013, 'Stasis Nullifiers' FROM DUAL
UNION ALL SELECT 65, 2015, 'Upwell Monument' FROM DUAL
UNION ALL SELECT 65, 2016, 'Upwell Cyno Jammer' FROM DUAL
UNION ALL SELECT 65, 2017, 'Upwell Cyno Beacon' FROM DUAL
UNION ALL SELECT 7, 2018, 'Mutadaptive Remote Armor Repairer' FROM DUAL
UNION ALL SELECT 9, 2019, 'Mutadaptive Remote Armor Repairer Blueprint' FROM DUAL
UNION ALL SELECT 2, 2020, 'Cynosural Fields' FROM DUAL
UNION ALL SELECT 25, 2022, 'Temporal Resources' FROM DUAL
UNION ALL SELECT 9, 2023, 'Cyber Electronics Implant Blueprints' FROM DUAL
UNION ALL SELECT 25, 2024, 'Fluorite' FROM DUAL
UNION ALL SELECT 17, 2026, 'Triglavian Artifacts' FROM DUAL
UNION ALL SELECT 11, 4028, 'Triglavian Entities' FROM DUAL
UNION ALL SELECT 25, 4029, 'Talassonite' FROM DUAL
UNION ALL SELECT 25, 4030, 'Rakovene' FROM DUAL
UNION ALL SELECT 25, 4031, 'Bezdnacine' FROM DUAL
UNION ALL SELECT 2, 4033, 'Destructible Effect Beacon' FROM DUAL
UNION ALL SELECT 11, 4034, 'EDENCOM Entities' FROM DUAL
UNION ALL SELECT 11, 4035, 'Drifter Entities' FROM DUAL
UNION ALL SELECT 11, 4036, 'Sleeper Entities' FROM DUAL
UNION ALL SELECT 11, 4037, 'Rogue Drone Entities' FROM DUAL
UNION ALL SELECT 91, 4040, '180-Day SKIN' FROM DUAL
UNION ALL SELECT 17, 4041, 'Jump Filaments' FROM DUAL
UNION ALL SELECT 17, 4050, 'Abyssal Proving Filaments' FROM DUAL
UNION ALL SELECT 9, 4052, 'Jump Filament Blueprint' FROM DUAL
UNION ALL SELECT 11, 4053, 'Irregular Capsule' FROM DUAL
UNION ALL SELECT 2, 4055, 'Invisible Beacon' FROM DUAL
UNION ALL SELECT 30, 4057, 'Masks' FROM DUAL
UNION ALL SELECT 7, 4060, 'Vorton Projector' FROM DUAL
UNION ALL SELECT 8, 4061, 'Advanced Condenser Pack' FROM DUAL
UNION ALL SELECT 8, 4062, 'Condenser Pack' FROM DUAL
UNION ALL SELECT 9, 4064, 'Vorton Projector Blueprint' FROM DUAL
UNION ALL SELECT 9, 4065, 'Condenser Pack Blueprint' FROM DUAL
UNION ALL SELECT 9, 4066, 'Advanced Condenser Pack Blueprint' FROM DUAL
UNION ALL SELECT 7, 4067, 'Vorton Projector Upgrade' FROM DUAL
UNION ALL SELECT 9, 4069, 'Vorton Projector Upgrade Blueprints' FROM DUAL
UNION ALL SELECT 2, 4070, 'Exotic Artefact' FROM DUAL
UNION ALL SELECT 29, 4071, 'Type Graveyard' FROM DUAL
UNION ALL SELECT 17, 4072, 'Expired Jump Filaments' FROM DUAL
UNION ALL SELECT 11, 4073, 'Temporary Collidable Structure' FROM DUAL
UNION ALL SELECT 2, 4079, 'Encounter Surveillance System' FROM DUAL
UNION ALL SELECT 2, 4081, 'Disrupted Gate' FROM DUAL
UNION ALL SELECT 66, 4086, 'Quantum Cores' FROM DUAL
UNION ALL SELECT 17, 4087, 'Triglavian Space Filaments' FROM DUAL
UNION ALL SELECT 8, 4088, 'Interdiction Burst Probes' FROM DUAL
UNION ALL SELECT 2100, 4090, 'QA Expert Systems' FROM DUAL
UNION ALL SELECT 2100, 4091, 'Standard Expert Systems' FROM DUAL
UNION ALL SELECT 22, 4093, 'Mobile Cynosural Beacon' FROM DUAL
UNION ALL SELECT 25, 4094, 'Scalable Decorative Asteroid' FROM DUAL
UNION ALL SELECT 9, 4095, 'Mobile Cynosural Beacon Blueprint' FROM DUAL
UNION ALL SELECT 4, 4096, 'Molecular-Forged Materials' FROM DUAL
UNION ALL SELECT 9, 4097, 'Molecular-Forged Reaction Formulas' FROM DUAL
UNION ALL SELECT 2100, 4098, 'Promotional Expert Systems' FROM DUAL
UNION ALL SELECT 2, 4100, 'Logo Display Monument' FROM DUAL
UNION ALL SELECT 17, 4102, 'ESS Reserve Bank Keys' FROM DUAL
UNION ALL SELECT 11, 4105, 'Unidentified Entities' FROM DUAL
UNION ALL SELECT 11, 4106, 'AIR Entities I' FROM DUAL
UNION ALL SELECT 22, 4107, 'Mobile Observatory' FROM DUAL
UNION ALL SELECT 9, 4108, 'Mobile Observatory Blueprint' FROM DUAL
UNION ALL SELECT 7, 4117, 'Interdiction Nullifier' FROM DUAL
UNION ALL SELECT 9, 4118, 'Interdiction Nullifier Blueprint' FROM DUAL
UNION ALL SELECT 7, 4127, 'Covert Jump Portal Generator' FROM DUAL
UNION ALL SELECT 11, 4128, 'AIR Entities II' FROM DUAL
UNION ALL SELECT 11, 4130, 'Unidentified Entities II' FROM DUAL
UNION ALL SELECT 22, 4137, 'Mobile Analysis Beacon' FROM DUAL
UNION ALL SELECT 7, 4138, 'Gas Cloud Harvesters' FROM DUAL
UNION ALL SELECT 9, 4139, 'Gas Harvester Blueprint' FROM DUAL
UNION ALL SELECT 9, 4141, 'Mobile Analysis Beacon Blueprint' FROM DUAL
UNION ALL SELECT 17, 4142, 'Rogue Drone Analysis Data' FROM DUAL
UNION ALL SELECT 17, 4145, 'Warp Matrix Filaments' FROM DUAL
UNION ALL SELECT 25, 4161, 'AIR Ore Asteroid Resources' FROM DUAL
UNION ALL SELECT 17, 4165, 'Peculiar Materials' FROM DUAL
UNION ALL SELECT 2, 4168, 'Compressed Gas' FROM DUAL
UNION ALL SELECT 7, 4174, 'Compressors' FROM DUAL
UNION ALL SELECT 9, 4175, 'Compressors Blueprints' FROM DUAL
UNION ALL SELECT 7, 4184, 'Industrial Jump Portal Generator' FROM DUAL
UNION ALL SELECT 8, 4186, 'Structure Area Denial Ammunition' FROM DUAL
UNION ALL SELECT 9, 4188, 'Structure Area Denial Ammo Blueprint' FROM DUAL
UNION ALL SELECT 2, 4430, 'Asteroid Belt' FROM DUAL
UNION ALL SELECT 11, 4431, 'FW Amarr Empire Battleship' FROM DUAL
UNION ALL SELECT 11, 4432, 'FW Caldari State Battleship' FROM DUAL
UNION ALL SELECT 11, 4433, 'FW Gallente Federation Battleship' FROM DUAL
UNION ALL SELECT 11, 4434, 'FW Minmatar Republic Battleship' FROM DUAL
UNION ALL SELECT 350001, 350858, 'Infantry Weapons' FROM DUAL
UNION ALL SELECT 350001, 351064, 'Infantry Dropsuits' FROM DUAL
UNION ALL SELECT 350001, 351121, 'Infantry Modules' FROM DUAL
UNION ALL SELECT 350001, 351210, 'Infantry Vehicles' FROM DUAL
UNION ALL SELECT 350001, 351648, 'Infantry Skills' FROM DUAL
UNION ALL SELECT 350001, 351844, 'Infantry Equipment' FROM DUAL
UNION ALL SELECT 350001, 354641, 'Infantry Skill Enhancers' FROM DUAL
UNION ALL SELECT 350001, 354753, 'Infantry Installations' FROM DUAL
UNION ALL SELECT 350001, 364204, 'Surface Infrastructure' FROM DUAL
UNION ALL SELECT 350001, 367487, 'Services ' FROM DUAL
UNION ALL SELECT 350001, 367580, 'Agents' FROM DUAL
UNION ALL SELECT 350001, 367594, 'Visual Customization' FROM DUAL
UNION ALL SELECT 350001, 367774, 'Salvage Containers' FROM DUAL
UNION ALL SELECT 350001, 367776, 'Salvage Decryptors' FROM DUAL
UNION ALL SELECT 350001, 368656, 'Battle Salvage' FROM DUAL
UNION ALL SELECT 350001, 368666, 'Warbarge' FROM DUAL
UNION ALL SELECT 350001, 368726, 'Infantry Color Skin' FROM DUAL
;
