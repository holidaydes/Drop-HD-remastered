package de.holiday.dropgame.values;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public enum Assets {

    BACKGROUND_MAIN("backgrounds/mainMenu.jpg"),
    BACKGROUND_NOT_RAIN_MAN("backgrounds/notRainMan.jpg"),
    BACKGROUND_RAIN("backgrounds/rain.jpg"),
    BACKGROUND_RAIN_MAN("backgrounds/rainMan.jpg"),
    BACKGROUND_RAINING_CHAMPION("backgrounds/rainingChampion.jpg"),

    FONT_ROMAN_SERIF("fonts/RomanSerif.ttf"),

    ITEM_DROP("items/drop.png"),
    ITEM_BUCKET("items/bucket.png"),

    SKIN_UI_JSON("skins/uiskin.json"),
    SKIN_UI_ATLAS("skins/uiskin.atlas"),

    SOUND_CRYING("sounds/crying.mp3"),
    SOUND_DROP("sounds/drop.wav"),
    SOUND_FORMULA_ONE("sounds/formulaOne.mp3"),
    SOUND_FUCK("sounds/fuck.mp3"),
    SOUND_RAIN("sounds/rain.mp3"),
    SOUND_RAIN_MAN("sounds/rainingMen.mp3");

    String path;

    Assets(String path) {
        this.path = path;
    }

    public FileHandle load() {
        return Gdx.files.internal(path);
    }
}
