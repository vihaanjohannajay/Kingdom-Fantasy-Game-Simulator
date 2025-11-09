import java.util.*;

// ===================== KingdomConfig =====================
final class KingdomConfig
{
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes, Map<String, Integer> resourceLimits)
    {
        if (kingdomName == null || kingdomName.isBlank())
            throw new IllegalArgumentException("Kingdom name cannot be empty");
        if (foundingYear <= 0)
            throw new IllegalArgumentException("Founding year must be positive");
        if (allowedStructureTypes == null || allowedStructureTypes.length == 0)
            throw new IllegalArgumentException("Must allow at least one structure type");
        if (resourceLimits == null || resourceLimits.isEmpty())
            throw new IllegalArgumentException("Resource limits cannot be empty");

        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length);
        this.resourceLimits = new HashMap<>(resourceLimits);
    }

    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
    public String[] getAllowedStructureTypes() { return Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length); }
    public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

    public static KingdomConfig createDefaultKingdom()
    {
        return new KingdomConfig(
                "Avaloria",
                1000,
                new String[]{"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"},
                Map.of("Gold", 10000, "Mana", 5000)
        );
    }

    public static KingdomConfig createFromTemplate(String type)
    {
        switch (type.toLowerCase())
        {
            case "magic":
                return new KingdomConfig("Mystara", 1200, new String[]{"WizardTower", "MysticLibrary"}, Map.of("Mana", 10000));
            case "military":
                return new KingdomConfig("Ironhold", 800, new String[]{"EnchantedCastle", "DragonLair"}, Map.of("Gold", 20000));
            default:
                return createDefaultKingdom();
        }
    }

    @Override
    public String toString() { return kingdomName + " (Founded: " + foundingYear + ")"; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof KingdomConfig)) return false;
        KingdomConfig that = (KingdomConfig) o;
        return foundingYear == that.foundingYear &&
                kingdomName.equals(that.kingdomName) &&
                Arrays.equals(allowedStructureTypes, that.allowedStructureTypes) &&
                resourceLimits.equals(that.resourceLimits);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(kingdomName, foundingYear, resourceLimits);
        result = 31 * result + Arrays.hashCode(allowedStructureTypes);
        return result;
    }
}

// ===================== MagicalStructure =====================
class MagicalStructure
{
    private final String structureId;
    private final long constructionTimestamp;
    private final String structureName;
    private final String location;

    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    public MagicalStructure(String name, String location)
    {
        this(name, location, 100, true);
    }

    public MagicalStructure(String name, String location, int power)
    {
        this(name, location, power, true);
    }

    public MagicalStructure(String name, String location, int power, boolean active)
    {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("Location cannot be empty");
        if (power < MIN_MAGIC_POWER || power > MAX_MAGIC_POWER) throw new IllegalArgumentException("Invalid magic power");

        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        this.magicPower = power;
        this.isActive = active;
        this.currentMaintainer = "Unknown";
    }

    public String getStructureId() { return structureId; }
    public long getConstructionTimestamp() { return constructionTimestamp; }
    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }

    public int getMagicPower() { return magicPower; }
    public void setMagicPower(int magicPower)
    {
        if (magicPower >= MIN_MAGIC_POWER && magicPower <= MAX_MAGIC_POWER)
            this.magicPower = magicPower;
    }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getCurrentMaintainer() { return currentMaintainer; }
    public void setCurrentMaintainer(String currentMaintainer) { this.currentMaintainer = currentMaintainer; }

    @Override
    public String toString()
    {
        return structureName + " at " + location + " (Power=" + magicPower + ", Active=" + isActive + ")";
    }
}

// ===================== WizardTower =====================
class WizardTower
{
    private final int maxSpellCapacity;
    private List<String> knownSpells;
    private String currentWizard;
    private final MagicalStructure core;

    public WizardTower(String name, String location)
    {
        this(name, location, 200, 10, new ArrayList<>(List.of("Light", "Shield")), "Unknown");
    }

    public WizardTower(String name, String location, int power, int capacity, List<String> spells, String wizard)
    {
        this.core = new MagicalStructure(name, location, power, true);
        this.maxSpellCapacity = capacity;
        this.knownSpells = new ArrayList<>(spells);
        this.currentWizard = wizard;
    }

    @Override
    public String toString()
    {
        return "WizardTower{" + "capacity=" + maxSpellCapacity +
                ", spells=" + knownSpells +
                ", wizard='" + currentWizard + '\'' +
                ", core=" + core + "}";
    }
}

// ===================== EnchantedCastle =====================
class EnchantedCastle
{
    private final String castleType;
    private int defenseRating;
    private boolean hasDrawbridge;
    private final MagicalStructure core;

    public EnchantedCastle(String name, String location, String type)
    {
        this.core = new MagicalStructure(name, location, 300, true);
        this.castleType = type;
        this.defenseRating = 100;
        this.hasDrawbridge = true;
    }

    @Override
    public String toString()
    {
        return "EnchantedCastle{" + "type=" + castleType +
                ", defense=" + defenseRating +
                ", drawbridge=" + hasDrawbridge +
                ", core=" + core + "}";
    }
}

// ===================== MysticLibrary =====================
class MysticLibrary
{
    private final Map<String, String> bookCollection;
    private int knowledgeLevel;
    private final MagicalStructure core;

    public MysticLibrary(String name, String location, Map<String, String> books)
    {
        this.core = new MagicalStructure(name, location, 150, true);
        this.bookCollection = new HashMap<>(books);
        this.knowledgeLevel = books.size() * 10;
    }

    @Override
    public String toString()
    {
        return "MysticLibrary{" + "books=" + bookCollection.size() +
                ", knowledgeLevel=" + knowledgeLevel +
                ", core=" + core + "}";
    }
}

// ===================== DragonLair =====================
class DragonLair
{
    private final String dragonType;
    private long treasureValue;
    private int territorialRadius;
    private final MagicalStructure core;

    public DragonLair(String name, String location, String type, long treasure)
    {
        this.core = new MagicalStructure(name, location, 500, true);
        this.dragonType = type;
        this.treasureValue = treasure;
        this.territorialRadius = 50;
    }

    @Override
    public String toString()
    {
        return "DragonLair{" + "dragonType='" + dragonType + '\'' +
                ", treasure=" + treasureValue +
                ", radius=" + territorialRadius +
                ", core=" + core + "}";
    }
}

// ===================== KingdomManager =====================
class KingdomManager
{
    private final List<Object> structures;
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config)
    {
        this.config = config;
        this.structures = new ArrayList<>();
    }

    public void addStructure(Object structure) { structures.add(structure); }

    public static boolean canStructuresInteract(Object s1, Object s2)
    {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
               (s1 instanceof EnchantedCastle && s2 instanceof DragonLair);
    }

    public static String performMagicBattle(Object attacker, Object defender)
    {
        if (attacker instanceof WizardTower && defender instanceof DragonLair)
            return "Wizard Tower casts spells against the Dragon!";
        if (attacker instanceof DragonLair && defender instanceof EnchantedCastle)
            return "Dragon attacks the Castle!";
        return "No significant battle occurred.";
    }

    public static int calculateKingdomPower(Object[] structures)
    {
        int total = 0;
        for (Object s : structures)
        {
            if (s instanceof WizardTower) total += 200;
            else if (s instanceof EnchantedCastle) total += 300;
            else if (s instanceof MysticLibrary) total += 150;
            else if (s instanceof DragonLair) total += 500;
        }
        return total;
    }

    private String determineStructureCategory(Object structure)
    {
        if (structure instanceof WizardTower) return "WizardTower";
        if (structure instanceof EnchantedCastle) return "EnchantedCastle";
        if (structure instanceof MysticLibrary) return "MysticLibrary";
        if (structure instanceof DragonLair) return "DragonLair";
        return "Unknown";
    }
}

// ===================== Main =====================
public class Main
{
    public static void main(String[] args)
    {
        KingdomConfig config = KingdomConfig.createDefaultKingdom();
        KingdomManager manager = new KingdomManager(config);

        WizardTower tower = new WizardTower("Merlin's Tower", "Highlands");
        EnchantedCastle castle = new EnchantedCastle("IronKeep", "Valley", "Royal");
        MysticLibrary library = new MysticLibrary("Arcane Library", "City", Map.of("Spellbook1", "Fireball", "Tome2", "Healing"));
        DragonLair lair = new DragonLair("Smaug's Lair", "Mountain", "Fire Dragon", 10000);

        manager.addStructure(tower);
        manager.addStructure(castle);
        manager.addStructure(library);
        manager.addStructure(lair);

        System.out.println(config);
        System.out.println(tower);
        System.out.println(castle);
        System.out.println(library);
        System.out.println(lair);

        System.out.println("Can interact? " + KingdomManager.canStructuresInteract(tower, library));
        System.out.println("Battle: " + KingdomManager.performMagicBattle(tower, lair));

        Object[] arr = {tower, castle, library, lair};
        System.out.println("Kingdom Power = " + KingdomManager.calculateKingdomPower(arr));
    }
}
