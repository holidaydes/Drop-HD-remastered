package de.holiday.dropgame.listeners;

import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import de.holiday.dropgame.values.PrecipitationType;

public class PrecipitationTypeInputListener extends InputListener {
    public PrecipitationType precipitationType;
    public TextButton button;

    public PrecipitationTypeInputListener(PrecipitationType precipitationType, TextButton button) {
        super();
        this.precipitationType = precipitationType;
        this.button = button;
    }
}
