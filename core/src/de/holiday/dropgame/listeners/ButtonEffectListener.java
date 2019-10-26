package de.holiday.dropgame.listeners;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ButtonEffectListener extends ClickListener {

    private TextButton button;

    public ButtonEffectListener(TextButton button) {
        super();
        this.button = button;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        button.setScale(1.05f, 1.05f);
        super.enter(event, x, y, pointer, fromActor);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        button.setScale(1f);
        super.exit(event, x, y, pointer, toActor);
    }

}
