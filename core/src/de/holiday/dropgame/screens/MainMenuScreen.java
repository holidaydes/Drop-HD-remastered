package de.holiday.dropgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import de.holiday.dropgame.core.Drop;
import de.holiday.dropgame.listeners.ButtonEffectListener;
import de.holiday.dropgame.utils.*;
import de.holiday.dropgame.values.Assets;
import de.holiday.dropgame.values.Resolution;
import de.holiday.dropgame.values.StringValues;
import de.holiday.dropgame.values.Tag;

public class MainMenuScreen implements Screen {
 
    private final Drop game;
 
    private Texture mainBackground;
    private TextButton startButton;
    private TextButton optionButton;
    private TextButton exitButton;
 
    public MainMenuScreen(final Drop game) {
        this.game = game;

        mainBackground = new Texture(Assets.BACKGROUND_MAIN.load());

        startButton = ElementFactory.generateDefaultTextButton(StringValues.BUTTON_START, this.game.defaultFont);
        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(Tag.NAVIGATE.name(), StringValues.SCREEN_GAME);
                dispose();
                game.setScreen(new GameScreen(game));
                return true;
            }
        });
        startButton.addListener(new ButtonEffectListener(startButton));

        optionButton = ElementFactory.generateDefaultTextButton(StringValues.BUTTON_OPTIONS, this.game.defaultFont);
        optionButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(Tag.NAVIGATE.name(), StringValues.SCREEN_OPTIONS);
                dispose();
                game.setScreen(new OptionsScreen(game));
                return true;
            }
        });
        optionButton.addListener(new ButtonEffectListener(optionButton));

        exitButton = ElementFactory.generateDefaultTextButton(StringValues.BUTTON_EXIT, this.game.defaultFont);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(Tag.ACTION.name(), StringValues.ACTION_EXIT);
                Gdx.app.exit();
                return true;
            }
        });
        exitButton.addListener(new ButtonEffectListener(exitButton));

        Label precipitationTypeLabel = ElementFactory.generateLabel(String.format(StringValues.LABEL_CURRENT_PRECIPITATION_TYPE, game.configuration.getPrecipitationTypeName()), this.game.smallFont);
        Label versionLabel = ElementFactory.generateLabel(String.format(StringValues.LABEL_VERSION, StringValues.VERSION), this.game.smallFont);

        Table table = ElementFactory.generateDefaultTable();
        table.row().expand().padBottom(2.5f).padTop(100f).colspan(2);
        table.add(startButton).bottom();
        table.row().expandX().padBottom(2.5f).colspan(2);
        table.add(optionButton);
        table.row().expandX().colspan(2);
        table.add(exitButton);
        table.row().expand();
        table.add(precipitationTypeLabel).left().bottom().padLeft(20f).padBottom(20f);
        table.add(versionLabel).right().bottom().padRight(20f).padBottom(20f);

        this.game.stage.addActor(table);
    }
 
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
 
        game.batch.begin();
        game.batch.draw(mainBackground, 0, 0, Resolution.WINDOW_WIDTH.value, Resolution.WINDOW_HEIGHT.value);
        game.batch.end();

        game.stage.act();
        game.stage.draw();
    }
 
    @Override
    public void resize(int width, int height) {
        Gdx.app.log(Tag.RESIZE.name(), String.format(StringValues.VALUE_RESOLUTION, width, height));
        game.viewport.update(width, height);
        game.stage.getViewport().update(width, height, true);
    }
 
    @Override
    public void show() {
    }
 
    @Override
    public void hide() {
    }
 
    @Override
    public void pause() {
    }
 
    @Override
    public void resume() {
    }
 
    @Override
    public void dispose() {
        mainBackground.dispose();
        game.stage.clear();
        Gdx.app.log(Tag.DISPOSE.name(), StringValues.SCREEN_MAIN_MENU);
    }
}
