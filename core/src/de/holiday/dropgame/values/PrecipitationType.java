package de.holiday.dropgame.values;

public enum PrecipitationType {
    DRIZZLE("Drizzle", 50, 5, 1_000_000_000l, 400),
    RAIN("Rain", 100, 10, 350_000_000l, 500),
    CLOUDBURST("Cloudburst", 250, 50, 200_000_000l, 600),
    RAINING_CATS_AND_DOGS("Raining cats and dogs", 500, 100, 100_000_000l, 600),
    CUSTOM("Custom", 100, 50, 350_000_000l, 500);

    public String name;
    public int dropGatheringLimit;
    public int dropLosingLimit;
    public long dropSpawnTime;
    public int dropSpeed;

    PrecipitationType(String name, int dropGatheringLimit, int dropLosingLimit, long dropSpawnTime, int dropSpeed) {
        this.name = name;
        this.dropGatheringLimit = dropGatheringLimit;
        this.dropLosingLimit = dropLosingLimit;
        this.dropSpawnTime = dropSpawnTime;
        this.dropSpeed = dropSpeed;
    }
}
