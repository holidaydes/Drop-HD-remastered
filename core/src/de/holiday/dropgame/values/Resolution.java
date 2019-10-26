package de.holiday.dropgame.values;

public enum Resolution {

    WINDOW_WIDTH(1360f),
    WINDOW_HEIGHT(768f),

    ITEM_BUCKET_WIDTH(96),
    ITEM_BUCKET_HEIGHT(96),

    ITEM_DROP_WIDTH(96),
    ITEM_DROP_HEIGHT(96);

    public float value;

    Resolution(float value) {
        this.value = value;
    }
}
