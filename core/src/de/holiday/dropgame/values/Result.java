package de.holiday.dropgame.values;

import de.holiday.dropgame.utils.GameConfiguration;

public enum Result {
    WIN(StringValues.RESULT_WIN, Assets.SOUND_RAIN_MAN, Assets.BACKGROUND_RAIN_MAN),
    LOSE(StringValues.RESULT_LOSE, Assets.SOUND_CRYING, Assets.BACKGROUND_NOT_RAIN_MAN),
    RAINING_CHAMPION(StringValues.RESULT_RAINING_CHAMPION, Assets.SOUND_FORMULA_ONE, Assets.BACKGROUND_RAINING_CHAMPION);

    public String text;
    public Assets music;
    public Assets background;

    Result(String text, Assets music, Assets background) {
        this.text = text;
        this.music = music;
        this.background = background;
    }

    public static Result getResult(int gatheredDrops, int lostDrops, GameConfiguration configuration) {
        if (gatheredDrops == configuration.getDropGatheringLimit() && lostDrops != 0) {
            return Result.WIN;
        } else if (gatheredDrops == configuration.getDropGatheringLimit() && lostDrops == 0) {
            return Result.RAINING_CHAMPION;
        } else {
            return Result.LOSE;
        }
    }
}
