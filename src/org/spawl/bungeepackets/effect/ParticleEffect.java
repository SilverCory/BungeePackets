package org.spawl.bungeepackets.effect;

public class ParticleEffect {
	
	public static final ParticleEffect EXPLODE = new ParticleEffect("explode",0),
			EXPLODE_LARGE = new ParticleEffect("largeexplode",1),
			EXPLODE_HUGE = new ParticleEffect("hugeexplosion",2),
			FIREWORK_SPARK = new ParticleEffect("fireworksSpark",3),
			BUBBLE = new ParticleEffect("bubble",4),
			SPLASH = new ParticleEffect("splash",5),
			WAKE = new ParticleEffect("wake",6),
			SUSPENDED = new ParticleEffect("suspended",7),
			DEPTH_SUSPEND = new ParticleEffect("depthsuspend",8),
			CRIT = new ParticleEffect("crit",9),
			CRIT_MAGIC = new ParticleEffect("magicCrit",10),
			SMOKE = new ParticleEffect("smoke",11),
			SMOKE_LARGE = new ParticleEffect("largesmoke",12),
			SPELL = new ParticleEffect("spell",13),
			SPELL_INSTANT = new ParticleEffect("instantSpell",14),
			SPELL_MOB = new ParticleEffect("mobSpell",15),
			SPELL_MOB_AMBIENT = new ParticleEffect("mobSpellAmbient",16),
			WITCH_MAGIC = new ParticleEffect("witchMagic",17),
			DRIP_WATER = new ParticleEffect("dripWater",18),
			DRIP_LAVA = new ParticleEffect("dripLava",19),
			VILLAGER_ANGRY = new ParticleEffect("angryVillager",20),
			VILLAGER_HAPPY = new ParticleEffect("happyVillager",21),
			TOWN_AURA = new ParticleEffect("townaura",22),
			NOTE = new ParticleEffect("note",23),
			PORTAL = new ParticleEffect("portal",24),
			ENCHANTMENT_TABLE = new ParticleEffect("enchantmenttable",25),
			FLAME = new ParticleEffect("flame",26),
			LAVA = new ParticleEffect("lava",27),
			FOOTSTEP = new ParticleEffect("footstep",28),
			CLOUD = new ParticleEffect("cloud",29),
			RED_DUST = new ParticleEffect("reddust",30),
			SNOWBALL_POOF = new ParticleEffect("snowballpoof",31),
			SNOW_SHOVEL = new ParticleEffect("snowshovel", 32),
			SLIME = new ParticleEffect("slime",33),
			HEART = new ParticleEffect("heart",34),
			BARRIER = new ParticleEffect("barrier",35),
			DROPLET = new ParticleEffect("droplet",39),
			TAKE = new ParticleEffect("take",40),
			MOB_APPEARANCE = new ParticleEffect("mobappearance",41),
			DRAGON_BREATH = new ParticleEffect("dragonbreath",42),
			END_ROD = new ParticleEffect("endRod",43),
			DAMAGE_INDICATOR = new ParticleEffect("damageIndicator",44),
			SWEEP_ATTACK = new ParticleEffect("sweepAttack",45);
	
	/**
	 * Used to get the icon crack for items!
	 * @param id
	 * @param data
	 * @return the sound id
	 */
	public static ParticleEffect getIconCrack(int id, int data) {
		return new ParticleEffect("iconcrack", 36, new int[]{id, data});
	}
	
	/**
	 * Returns block break animation.
	 * @param id
	 * @param data
	 * @return
	 */
	public static ParticleEffect getBlockCrack(int id, int data) {
		return new ParticleEffect("blockcrack", 37, new int[]{id+(data<<12)});
	}
	
	/**
	 * Returns dust from armor stands being broken
	 * @param id
	 * @return
	 */
	public static ParticleEffect getBlockDust(int id) {
		return new ParticleEffect("blockdust", 38, new int[]{id});
	}

	
	private int[] data;
	private int id;
	private String name;
	
	public ParticleEffect(String name, int id) {
		this(name, id, new int[0]);
	}
	
	public ParticleEffect(String name, int id, int[] data) {
		this.name = name;
		this.id = id;
		this.data = data;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int[] getData() {
		return data;
	}
	
}
