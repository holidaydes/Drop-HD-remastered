package de.holiday.dropgame.utils;

import de.holiday.dropgame.values.PrecipitationType;

public class GameConfiguration {
    public static final int DROP_GATHERING_LIMIT_MIN = 1;
    public static final int DROP_GATHERING_LIMIT_MAX = 1000;
    public static final int DROP_LOSING_LIMIT_MIN = 1;
    public static final int DROP_LOSING_LIMIT_MAX = 500;

    public static final long DROP_SPAWN_TIME_MIN = 150_000_000l;
    public static final long DROP_SPAWN_TIME_MAX = 1_000_000_000l;
    public static final int DROP_SPEED_MIN = 300;
    public static final int DROP_SPEED_MAX = 600;

    private int dropGatheringLimit;
    private int dropLosingLimit;
    private long dropSpawnTime;
    private int dropSpeed;
    private PrecipitationType precipitationType;

    public GameConfiguration() {
        setPrecipitationType(PrecipitationType.RAIN);
    }

    public PrecipitationType getPrecipitationType() {
        return precipitationType;
    }

    public void setPrecipitationType(PrecipitationType precipitationType) {
        this.dropGatheringLimit = precipitationType.dropGatheringLimit;
        this.dropLosingLimit = precipitationType.dropLosingLimit;
        this.dropSpawnTime = precipitationType.dropSpawnTime;
        this.dropSpeed = precipitationType.dropSpeed;
        this.precipitationType = precipitationType;
    }

    public int getDropGatheringLimit() {
        return dropGatheringLimit;
    }

    public void setDropGatheringLimit(int dropGatheringLimit) {
        if (dropGatheringLimit >= DROP_GATHERING_LIMIT_MIN && dropGatheringLimit <= DROP_GATHERING_LIMIT_MAX) {
            this.dropGatheringLimit = dropGatheringLimit;
        }
    }

    public int getDropLosingLimit() {
        return dropLosingLimit;
    }

    public void setDropLosingLimit(int dropLosingLimit) {
        if (dropLosingLimit >= DROP_LOSING_LIMIT_MIN && dropLosingLimit <= DROP_LOSING_LIMIT_MAX) {
            this.dropLosingLimit = dropLosingLimit;
        }
    }

    public long getDropSpawnTime() {
        return dropSpawnTime;
    }

    public void setDropSpawnTime(long dropSpawnTime) {
        if (dropSpawnTime >= DROP_SPAWN_TIME_MIN && dropSpawnTime <= DROP_SPAWN_TIME_MAX) {
            this.dropSpawnTime = dropSpawnTime;
        }
    }

    public String getPrecipitationTypeName() {
        return precipitationType.name;
    }

    public int getDropSpeed() {
        return dropSpeed;
    }

    public void setDropSpeed(int dropSpeed) {
        if (dropSpeed >= DROP_SPEED_MIN && dropSpeed <= DROP_SPEED_MAX) {
            this.dropSpeed = dropSpeed;
        }
    }

}
