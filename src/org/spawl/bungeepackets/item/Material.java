package org.spawl.bungeepackets.item;

import java.util.Map;

import com.google.common.collect.Maps;

public enum Material
{
	AIR(0, 0), 
	STONE(1), 
	GRASS(2), 
	DIRT(3), 
	COBBLESTONE(4), 
	WOOD(5), 
	SAPLING(6), 
	BEDROCK(7), 
	WATER(8), 
	STATIONARY_WATER(9), 
	LAVA(10), 
	STATIONARY_LAVA(11), 
	SAND(12), 
	GRAVEL(13), 
	GOLD_ORE(14), 
	IRON_ORE(15), 
	COAL_ORE(16), 
	LOG(17), 
	LEAVES(18), 
	SPONGE(19), 
	GLASS(20), 
	LAPIS_ORE(21), 
	LAPIS_BLOCK(22), 
	DISPENSER(23), 
	SANDSTONE(24), 
	NOTE_BLOCK(25), 
	BED_BLOCK(26), 
	POWERED_RAIL(27), 
	DETECTOR_RAIL(28), 
	PISTON_STICKY_BASE(29), 
	WEB(30), 
	LONG_GRASS(31), 
	DEAD_BUSH(32), 
	PISTON_BASE(33), 
	PISTON_EXTENSION(34), 
	WOOL(35), 
	PISTON_MOVING_PIECE(36), 
	YELLOW_FLOWER(37), 
	RED_ROSE(38), 
	BROWN_MUSHROOM(39), 
	RED_MUSHROOM(40), 
	GOLD_BLOCK(41), 
	IRON_BLOCK(42), 
	DOUBLE_STEP(43), 
	STEP(44), 
	BRICK(45), 
	TNT(46), 
	BOOKSHELF(47), 
	MOSSY_COBBLESTONE(48), 
	OBSIDIAN(49), 
	TORCH(50), 
	FIRE(51), 
	MOB_SPAWNER(52), 
	WOOD_STAIRS(53), 
	CHEST(54), 
	REDSTONE_WIRE(55), 
	DIAMOND_ORE(56), 
	DIAMOND_BLOCK(57), 
	WORKBENCH(58), 
	CROPS(59), 
	SOIL(60), 
	FURNACE(61), 
	BURNING_FURNACE(62), 
	SIGN_POST(63, 64), 
	WOODEN_DOOR(64), 
	LADDER(65), 
	RAILS(66), 
	COBBLESTONE_STAIRS(67), 
	WALL_SIGN(68, 64), 
	LEVER(69), 
	STONE_PLATE(70), 
	IRON_DOOR_BLOCK(71), 
	WOOD_PLATE(72), 
	REDSTONE_ORE(73), 
	GLOWING_REDSTONE_ORE(74), 
	REDSTONE_TORCH_OFF(75), 
	REDSTONE_TORCH_ON(76), 
	STONE_BUTTON(77), 
	SNOW(78), 
	ICE(79), 
	SNOW_BLOCK(80), 
	CACTUS(81), 
	CLAY(82), 
	SUGAR_CANE_BLOCK(83), 
	JUKEBOX(84), 
	FENCE(85), 
	PUMPKIN(86), 
	NETHERRACK(87), 
	SOUL_SAND(88), 
	GLOWSTONE(89), 
	PORTAL(90), 
	JACK_O_LANTERN(91), 
	CAKE_BLOCK(92, 64), 
	DIODE_BLOCK_OFF(93), 
	DIODE_BLOCK_ON(94), 
	LOCKED_CHEST(95), 
	STAINED_GLASS(95), 
	TRAP_DOOR(96), 
	MONSTER_EGGS(97), 
	SMOOTH_BRICK(98), 
	HUGE_MUSHROOM_1(99), 
	HUGE_MUSHROOM_2(100), 
	IRON_FENCE(101), 
	THIN_GLASS(102), 
	MELON_BLOCK(103), 
	PUMPKIN_STEM(104), 
	MELON_STEM(105), 
	VINE(106), 
	FENCE_GATE(107), 
	BRICK_STAIRS(108), 
	SMOOTH_STAIRS(109), 
	MYCEL(110), 
	WATER_LILY(111), 
	NETHER_BRICK(112), 
	NETHER_FENCE(113), 
	NETHER_BRICK_STAIRS(114), 
	NETHER_WARTS(115), 
	ENCHANTMENT_TABLE(116), 
	BREWING_STAND(117), 
	CAULDRON(118), 
	ENDER_PORTAL(119), 
	ENDER_PORTAL_FRAME(120), 
	ENDER_STONE(121), 
	DRAGON_EGG(122), 
	REDSTONE_LAMP_OFF(123), 
	REDSTONE_LAMP_ON(124), 
	WOOD_DOUBLE_STEP(125), 
	WOOD_STEP(126), 
	COCOA(127), 
	SANDSTONE_STAIRS(128), 
	EMERALD_ORE(129), 
	ENDER_CHEST(130), 
	TRIPWIRE_HOOK(131), 
	TRIPWIRE(132), 
	EMERALD_BLOCK(133), 
	SPRUCE_WOOD_STAIRS(134), 
	BIRCH_WOOD_STAIRS(135), 
	JUNGLE_WOOD_STAIRS(136), 
	COMMAND(137), 
	BEACON(138), 
	COBBLE_WALL(139), 
	FLOWER_POT(140), 
	CARROT(141), 
	POTATO(142), 
	WOOD_BUTTON(143), 
	SKULL(144), 
	ANVIL(145), 
	TRAPPED_CHEST(146), 
	GOLD_PLATE(147), 
	IRON_PLATE(148), 
	REDSTONE_COMPARATOR_OFF(149), 
	REDSTONE_COMPARATOR_ON(150), 
	DAYLIGHT_DETECTOR(151), 
	REDSTONE_BLOCK(152), 
	QUARTZ_ORE(153), 
	HOPPER(154), 
	QUARTZ_BLOCK(155), 
	QUARTZ_STAIRS(156), 
	ACTIVATOR_RAIL(157), 
	DROPPER(158), 
	STAINED_CLAY(159), 
	STAINED_GLASS_PANE(160), 
	LEAVES_2(161), 
	LOG_2(162), 
	ACACIA_STAIRS(163), 
	DARK_OAK_STAIRS(164), 
	SLIME_BLOCK(165), 
	BARRIER(166), 
	IRON_TRAPDOOR(167), 
	PRISMARINE(168), 
	SEA_LANTERN(169), 
	HAY_BLOCK(170), 
	CARPET(171), 
	HARD_CLAY(172), 
	COAL_BLOCK(173), 
	PACKED_ICE(174), 
	DOUBLE_PLANT(175), 
	STANDING_BANNER(176), 
	WALL_BANNER(177), 
	DAYLIGHT_DETECTOR_INVERTED(178), 
	RED_SANDSTONE(179), 
	RED_SANDSTONE_STAIRS(180), 
	DOUBLE_STONE_SLAB2(181), 
	STONE_SLAB2(182), 
	SPRUCE_FENCE_GATE(183), 
	BIRCH_FENCE_GATE(184), 
	JUNGLE_FENCE_GATE(185), 
	DARK_OAK_FENCE_GATE(186), 
	ACACIA_FENCE_GATE(187), 
	SPRUCE_FENCE(188), 
	BIRCH_FENCE(189), 
	JUNGLE_FENCE(190), 
	DARK_OAK_FENCE(191), 
	ACACIA_FENCE(192), 
	SPRUCE_DOOR(193), 
	BIRCH_DOOR(194), 
	JUNGLE_DOOR(195), 
	ACACIA_DOOR(196), 
	DARK_OAK_DOOR(197), 
	IRON_SPADE(256, 1, 250), 
	IRON_PICKAXE(257, 1, 250), 
	IRON_AXE(258, 1, 250), 
	FLINT_AND_STEEL(259, 1, 64), 
	APPLE(260), 
	BOW(261, 1, 384), 
	ARROW(262), 
	COAL(263), 
	DIAMOND(264), 
	IRON_INGOT(265), 
	GOLD_INGOT(266), 
	IRON_SWORD(267, 1, 250), 
	WOOD_SWORD(268, 1, 59), 
	WOOD_SPADE(269, 1, 59), 
	WOOD_PICKAXE(270, 1, 59), 
	WOOD_AXE(271, 1, 59), 
	STONE_SWORD(272, 1, 131), 
	STONE_SPADE(273, 1, 131), 
	STONE_PICKAXE(274, 1, 131), 
	STONE_AXE(275, 1, 131), 
	DIAMOND_SWORD(276, 1, 1561), 
	DIAMOND_SPADE(277, 1, 1561), 
	DIAMOND_PICKAXE(278, 1, 1561), 
	DIAMOND_AXE(279, 1, 1561), 
	STICK(280), 
	BOWL(281), 
	MUSHROOM_SOUP(282, 1), 
	GOLD_SWORD(283, 1, 32), 
	GOLD_SPADE(284, 1, 32), 
	GOLD_PICKAXE(285, 1, 32), 
	GOLD_AXE(286, 1, 32), 
	STRING(287), 
	FEATHER(288), 
	SULPHUR(289), 
	WOOD_HOE(290, 1, 59), 
	STONE_HOE(291, 1, 131), 
	IRON_HOE(292, 1, 250), 
	DIAMOND_HOE(293, 1, 1561), 
	GOLD_HOE(294, 1, 32), 
	SEEDS(295), 
	WHEAT(296), 
	BREAD(297), 
	LEATHER_HELMET(298, 1, 55), 
	LEATHER_CHESTPLATE(299, 1, 80), 
	LEATHER_LEGGINGS(300, 1, 75), 
	LEATHER_BOOTS(301, 1, 65), 
	CHAINMAIL_HELMET(302, 1, 165), 
	CHAINMAIL_CHESTPLATE(303, 1, 240), 
	CHAINMAIL_LEGGINGS(304, 1, 225), 
	CHAINMAIL_BOOTS(305, 1, 195), 
	IRON_HELMET(306, 1, 165), 
	IRON_CHESTPLATE(307, 1, 240), 
	IRON_LEGGINGS(308, 1, 225), 
	IRON_BOOTS(309, 1, 195), 
	DIAMOND_HELMET(310, 1, 363), 
	DIAMOND_CHESTPLATE(311, 1, 528), 
	DIAMOND_LEGGINGS(312, 1, 495), 
	DIAMOND_BOOTS(313, 1, 429), 
	GOLD_HELMET(314, 1, 77), 
	GOLD_CHESTPLATE(315, 1, 112), 
	GOLD_LEGGINGS(316, 1, 105), 
	GOLD_BOOTS(317, 1, 91), 
	FLINT(318), 
	PORK(319), 
	GRILLED_PORK(320), 
	PAINTING(321), 
	GOLDEN_APPLE(322), 
	SIGN(323, 16), 
	WOOD_DOOR(324, 64), 
	BUCKET(325, 16), 
	WATER_BUCKET(326, 1), 
	LAVA_BUCKET(327, 1), 
	MINECART(328, 1), 
	SADDLE(329, 1), 
	IRON_DOOR(330, 64), 
	REDSTONE(331), 
	SNOW_BALL(332, 16), 
	BOAT(333, 1), 
	LEATHER(334), 
	MILK_BUCKET(335, 1), 
	CLAY_BRICK(336), 
	CLAY_BALL(337), 
	SUGAR_CANE(338), 
	PAPER(339), 
	BOOK(340), 
	SLIME_BALL(341), 
	STORAGE_MINECART(342, 1), 
	POWERED_MINECART(343, 1), 
	EGG(344, 16), 
	COMPASS(345), 
	FISHING_ROD(346, 1, 64), 
	WATCH(347), 
	GLOWSTONE_DUST(348), 
	RAW_FISH(349), 
	COOKED_FISH(350), 
	INK_SACK(351), 
	BONE(352), 
	SUGAR(353), 
	CAKE(354, 1), 
	BED(355, 1), 
	DIODE(356), 
	COOKIE(357), 
	MAP(358), 
	SHEARS(359, 1, 238), 
	MELON(360), 
	PUMPKIN_SEEDS(361), 
	MELON_SEEDS(362), 
	RAW_BEEF(363), 
	COOKED_BEEF(364), 
	RAW_CHICKEN(365), 
	COOKED_CHICKEN(366), 
	ROTTEN_FLESH(367), 
	ENDER_PEARL(368, 16), 
	BLAZE_ROD(369), 
	GHAST_TEAR(370), 
	GOLD_NUGGET(371), 
	NETHER_STALK(372), 
	POTION(373, 1), 
	GLASS_BOTTLE(374), 
	SPIDER_EYE(375), 
	FERMENTED_SPIDER_EYE(376), 
	BLAZE_POWDER(377), 
	MAGMA_CREAM(378), 
	BREWING_STAND_ITEM(379), 
	CAULDRON_ITEM(380), 
	EYE_OF_ENDER(381), 
	SPECKLED_MELON(382), 
	MONSTER_EGG(383, 64), 
	EXP_BOTTLE(384, 64), 
	FIREBALL(385, 64), 
	BOOK_AND_QUILL(386, 1), 
	WRITTEN_BOOK(387, 16), 
	EMERALD(388, 64), 
	ITEM_FRAME(389), 
	FLOWER_POT_ITEM(390), 
	CARROT_ITEM(391), 
	POTATO_ITEM(392), 
	BAKED_POTATO(393), 
	POISONOUS_POTATO(394), 
	EMPTY_MAP(395), 
	GOLDEN_CARROT(396), 
	SKULL_ITEM(397), 
	CARROT_STICK(398, 1, 25), 
	NETHER_STAR(399), 
	PUMPKIN_PIE(400), 
	FIREWORK(401), 
	FIREWORK_CHARGE(402), 
	ENCHANTED_BOOK(403, 1), 
	REDSTONE_COMPARATOR(404), 
	NETHER_BRICK_ITEM(405), 
	QUARTZ(406), 
	EXPLOSIVE_MINECART(407, 1), 
	HOPPER_MINECART(408, 1), 
	PRISMARINE_SHARD(409), 
	PRISMARINE_CRYSTALS(410), 
	RABBIT(411), 
	COOKED_RABBIT(412), 
	RABBIT_STEW(413, 1), 
	RABBIT_FOOT(414), 
	RABBIT_HIDE(415), 
	ARMOR_STAND(416, 16), 
	IRON_BARDING(417, 1), 
	GOLD_BARDING(418, 1), 
	DIAMOND_BARDING(419, 1), 
	LEASH(420), 
	NAME_TAG(421), 
	COMMAND_MINECART(422, 1), 
	MUTTON(423), 
	COOKED_MUTTON(424), 
	BANNER(425, 16), 
	SPRUCE_DOOR_ITEM(427), 
	BIRCH_DOOR_ITEM(428), 
	JUNGLE_DOOR_ITEM(429), 
	ACACIA_DOOR_ITEM(430), 
	DARK_OAK_DOOR_ITEM(431), 
	GOLD_RECORD(2256, 1), 
	GREEN_RECORD(2257, 1), 
	RECORD_3(2258, 1), 
	RECORD_4(2259, 1), 
	RECORD_5(2260, 1), 
	RECORD_6(2261, 1), 
	RECORD_7(2262, 1), 
	RECORD_8(2263, 1), 
	RECORD_9(2264, 1), 
	RECORD_10(2265, 1), 
	RECORD_11(2266, 1), 
	RECORD_12(2267, 1);

	private final int id;
	private static final Map<Integer, Material> BY_ID;
	private static final Map<String, Material> BY_NAME;
	private final int maxStack;
	private final short durability;

	static {
		BY_ID = Maps.newHashMap();
		BY_NAME = Maps.newHashMap();

		for(Material material : values()) {
			BY_ID.put(material.id, material);
			BY_NAME.put(material.name(), material);
		}
	}

	private Material(int id)
	{
		this(id, 64);
	}

	private Material(int id, int stack) {
		this(id, stack, 0);
	}

	private Material(int id, int stack, int durability) {
		this.id = id;
		this.maxStack = stack;
		this.durability = (short) durability;
	}

	public int getId()
	{
		return this.id;
	}

	public int getMaxStackSize()
	{
		return this.maxStack;
	}

	public short getMaxDurability()
	{
		return this.durability;
	}

	public boolean isBlock()
	{
		return this.id < 256;
	}

	public boolean isEdible()
	{
		switch (id) {
		case 204:
		case 226:
		case 241:
		case 263:
		case 264:
		case 266:
		case 293:
		case 294:
		case 301:
		case 304:
		case 307:
		case 308:
		case 309:
		case 310:
		case 311:
		case 319:
		case 335:
		case 336:
		case 337:
		case 338:
		case 340:
		case 344:
		case 355:
		case 356:
		case 357:
		case 367:
		case 368:
			return true;
		}
		return false;
	}

	public static Material getMaterial(int id)
	{
		if (BY_ID.containsKey(id))
			return BY_ID.get(id);
		return null;
	}

	public static Material getMaterial(String name)
	{
		return (Material)BY_NAME.get(name);
	}

	public static Material matchMaterial(String name)
	{
		Material result = null;
		try
		{
			result = getMaterial(Integer.parseInt(name));
		} catch (NumberFormatException localNumberFormatException) {
		}
		if (result == null) {
			String filtered = name.toUpperCase();

			filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
			result = (Material)BY_NAME.get(filtered);
		}

		return result;
	}

	public boolean isRecord()
	{
		return (this.id >= GOLD_RECORD.id) && (this.id <= RECORD_12.id);
	}

	public boolean isSolid()
	{
		if ((!isBlock()) || (this.id == 0)) {
			return false;
		}
		switch (this) {
		case ACACIA_DOOR_ITEM:
		case ACACIA_FENCE:
		case ACACIA_FENCE_GATE:
		case ACACIA_STAIRS:
		case ACTIVATOR_RAIL:
		case ANVIL:
		case BANNER:
		case BARRIER:
		case BEACON:
		case BED:
		case BEDROCK:
		case BED_BLOCK:
		case BIRCH_DOOR:
		case BIRCH_DOOR_ITEM:
		case BIRCH_FENCE:
		case BIRCH_FENCE_GATE:
		case BIRCH_WOOD_STAIRS:
		case BLAZE_POWDER:
		case BLAZE_ROD:
		case BOAT:
		case BONE:
		case BOOK_AND_QUILL:
		case BREWING_STAND:
		case BREWING_STAND_ITEM:
		case BRICK:
		case BRICK_STAIRS:
		case CAKE:
		case CAKE_BLOCK:
		case CARPET:
		case CARROT:
		case CARROT_ITEM:
		case CARROT_STICK:
		case CAULDRON:
		case CAULDRON_ITEM:
		case CHAINMAIL_BOOTS:
		case CHAINMAIL_LEGGINGS:
		case CHEST:
		case CLAY:
		case CLAY_BRICK:
		case COAL:
		case COAL_BLOCK:
		case COBBLESTONE:
		case COBBLESTONE_STAIRS:
		case COBBLE_WALL:
		case COCOA:
		case COMMAND:
		case COOKED_BEEF:
		case COOKED_CHICKEN:
		case COOKED_MUTTON:
		case COOKED_RABBIT:
		case COOKIE:
		case CROPS:
		case DARK_OAK_DOOR:
		case DAYLIGHT_DETECTOR:
		case DAYLIGHT_DETECTOR_INVERTED:
		case DEAD_BUSH:
		case DETECTOR_RAIL:
		case DIAMOND_AXE:
		case DIAMOND_BARDING:
		case DIAMOND_BLOCK:
		case DIAMOND_BOOTS:
		case DIAMOND_CHESTPLATE:
		case DIAMOND_HELMET:
		case DIAMOND_LEGGINGS:
		case DIAMOND_ORE:
		case DIAMOND_SWORD:
		case DIODE:
		case DIODE_BLOCK_OFF:
		case DIODE_BLOCK_ON:
		case DIRT:
		case DISPENSER:
		case DOUBLE_PLANT:
		case DOUBLE_STEP:
		case DOUBLE_STONE_SLAB2:
		case DRAGON_EGG:
		case EMERALD_BLOCK:
		case EMERALD_ORE:
		case EMPTY_MAP:
		case ENCHANTED_BOOK:
		case ENDER_CHEST:
		case ENDER_PEARL:
		case ENDER_PORTAL:
		case ENDER_STONE:
		case EXPLOSIVE_MINECART:
		case EXP_BOTTLE:
		case FEATHER:
		case FENCE:
		case FENCE_GATE:
		case FERMENTED_SPIDER_EYE:
		case FIRE:
		case FIREBALL:
		case FIREWORK:
		case FISHING_ROD:
		case FLINT:
		case FLINT_AND_STEEL:
		case FURNACE:
		case GHAST_TEAR:
		case GLASS:
		case GLASS_BOTTLE:
		case GLOWING_REDSTONE_ORE:
		case GLOWSTONE:
		case GLOWSTONE_DUST:
		case GOLD_BOOTS:
		case GOLD_CHESTPLATE:
		case GOLD_HELMET:
		case GOLD_HOE:
		case GOLD_NUGGET:
		case GOLD_ORE:
		case GOLD_PICKAXE:
		case GOLD_PLATE:
		case GOLD_RECORD:
		case GOLD_SPADE:
		case GRASS:
		case GRAVEL:
		case GREEN_RECORD:
		case GRILLED_PORK:
		case HARD_CLAY:
		case HAY_BLOCK:
		case HOPPER:
		case HOPPER_MINECART:
		case HUGE_MUSHROOM_1:
		case HUGE_MUSHROOM_2:
		case ICE:
		case INK_SACK:
		case IRON_AXE:
		case IRON_BLOCK:
		case IRON_BOOTS:
		case IRON_CHESTPLATE:
		case IRON_DOOR_BLOCK:
		case IRON_FENCE:
		case IRON_HELMET:
		case IRON_HOE:
		case IRON_INGOT:
		case IRON_LEGGINGS:
		case IRON_ORE:
		case IRON_PICKAXE:
		case IRON_PLATE:
		case IRON_SPADE:
		case IRON_SWORD:
		case IRON_TRAPDOOR:
		case ITEM_FRAME:
		case JACK_O_LANTERN:
		case JUKEBOX:
		case JUNGLE_DOOR:
		case JUNGLE_DOOR_ITEM:
		case JUNGLE_FENCE:
		case JUNGLE_FENCE_GATE:
		case JUNGLE_WOOD_STAIRS:
		case LADDER:
		case LAPIS_BLOCK:
			return true;
		case AIR:
		case APPLE:
		case ARMOR_STAND:
		case ARROW:
		case BAKED_POTATO:
		case BOOK:
		case BOOKSHELF:
		case BOW:
		case BOWL:
		case BREAD:
		case BROWN_MUSHROOM:
		case BUCKET:
		case BURNING_FURNACE:
		case CACTUS:
		case CHAINMAIL_CHESTPLATE:
		case CHAINMAIL_HELMET:
		case CLAY_BALL:
		case COAL_ORE:
		case COMMAND_MINECART:
		case COMPASS:
		case COOKED_FISH:
		case DARK_OAK_DOOR_ITEM:
		case DARK_OAK_FENCE:
		case DARK_OAK_FENCE_GATE:
		case DARK_OAK_STAIRS:
		case DIAMOND:
		case DIAMOND_HOE:
		case DIAMOND_PICKAXE:
		case DIAMOND_SPADE:
		case DROPPER:
		case EGG:
		case EMERALD:
		case ENCHANTMENT_TABLE:
		case ENDER_PORTAL_FRAME:
		case EYE_OF_ENDER:
		case FIREWORK_CHARGE:
		case FLOWER_POT:
		case FLOWER_POT_ITEM:
		case GOLDEN_APPLE:
		case GOLDEN_CARROT:
		case GOLD_AXE:
		case GOLD_BARDING:
		case GOLD_BLOCK:
		case GOLD_INGOT:
		case GOLD_LEGGINGS:
		case GOLD_SWORD:
		case IRON_BARDING:
		case IRON_DOOR:
		default:
			break; 
		} return false;
	}

	public boolean isTransparent()
	{
		if (!isBlock()) {
			return false;
		}
		switch (id) {
		case 1:
		case 7:
		case 28:
		case 29:
		case 32:
		case 33:
		case 38:
		case 39:
		case 40:
		case 41:
		case 51:
		case 52:
		case 56:
		case 60:
		case 66:
		case 67:
		case 70:
		case 76:
		case 77:
		case 78:
		case 79:
		case 84:
		case 91:
		case 94:
		case 95:
		case 106:
		case 107:
		case 108:
		case 113:
		case 117:
		case 121:
		case 129:
		case 133:
		case 134:
		case 142:
		case 143:
		case 144:
		case 145:
		case 146:
		case 151:
		case 152:
		case 159:
		case 173:
		case 177:
			return true;
		}
		return false;
	}

	public boolean isFlammable()
	{
		if (!isBlock()) {
			return false;
		}
		switch (id) {
		case 6:
		case 18:
		case 19:
		case 26:
		case 27:
		case 32:
		case 33:
		case 36:
		case 47:
		case 48:
		case 54:
		case 55:
		case 59:
		case 64:
		case 65:
		case 69:
		case 73:
		case 85:
		case 86:
		case 98:
		case 101:
		case 102:
		case 108:
		case 109:
		case 127:
		case 128:
		case 136:
		case 137:
		case 138:
		case 148:
		case 153:
		case 163:
		case 164:
		case 165:
		case 166:
		case 173:
		case 177:
		case 178:
		case 179:
		case 180:
		case 185:
		case 186:
		case 187:
		case 188:
		case 189:
		case 190:
		case 191:
		case 192:
		case 193:
		case 194:
		case 195:
		case 196:
		case 197:
		case 198:
		case 199:
			return true;
		}
		return false;
	}

	public boolean isBurnable()
	{
		if (!isBlock()) {
			return false;
		}
		switch (id) {
		case 6:
		case 18:
		case 19:
		case 32:
		case 33:
		case 36:
		case 38:
		case 39:
		case 47:
		case 48:
		case 54:
		case 86:
		case 108:
		case 109:
		case 127:
		case 128:
		case 136:
		case 137:
		case 138:
		case 163:
		case 164:
		case 172:
		case 173:
		case 175:
		case 177:
		case 185:
		case 186:
		case 187:
		case 188:
		case 189:
		case 190:
		case 191:
		case 192:
		case 193:
		case 194:
			return true;
		}
		return false;
	}

	public boolean isOccluding()
	{
		if (!isBlock()) {
			return false;
		}
		switch (id) {
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 8:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 20:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 36:
		case 42:
		case 43:
		case 44:
		case 46:
		case 48:
		case 49:
		case 50:
		case 53:
		case 57:
		case 58:
		case 59:
		case 62:
		case 63:
		case 74:
		case 75:
		case 81:
		case 83:
		case 85:
		case 87:
		case 88:
		case 89:
		case 92:
		case 99:
		case 100:
		case 101:
		case 102:
		case 105:
		case 112:
		case 114:
		case 122:
		case 123:
		case 125:
		case 126:
		case 127:
		case 131:
		case 135:
		case 139:
		case 155:
		case 157:
		case 160:
		case 161:
		case 164:
		case 167:
		case 168:
		case 170:
		case 172:
		case 174:
		case 175:
		case 176:
		case 181:
		case 183:
			return true;
		}
		return false;
	}

	public boolean hasGravity()
	{
		if (!isBlock()) {
			return false;
		}
		switch (id) {
		case 13:
		case 14:
		case 147:
			return true;
		}
		return false;
	}
}
