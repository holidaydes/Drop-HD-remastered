package de.holiday.dropgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import de.holiday.dropgame.values.Resolution;
import de.holiday.dropgame.values.Result;
import de.holiday.dropgame.values.StringValues;
import de.holiday.dropgame.values.Tag;

public class EndScreen implements Screen {
 
    private final Drop game;
    private Music music;
    private Texture background;
    private TextButton startNewGameButton;
    private TextButton backToMainMenuButton;
    private TextButton exitButton;
 
    public EndScreen(final Drop game, Result result) {
        this.game = game;

        Label endTextLabel;

        endTextLabel = ElementFactory.generateLabel(result.text, this.game.titleFont);
        music = Gdx.audio.newMusic(result.music.load());
        background = new Texture(result.background.load());

        music.setLooping(true);

        startNewGameButton = ElementFactory.generateDefaultTextButton(StringValues.BUTTON_NEW_GAME, this.game.defaultFont);
        startNewGameButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(Tag.NAVIGATE.name(), StringValues.SCREEN_GAME);
                dispose();
                game.setScreen(new GameScreen(game));
                return true;
            }
        });
        startNewGameButton.addListener(new ButtonEffectListener(startNewGameButton));

        backToMainMenuButton = ElementFactory.generateDefaultTextButton(StringValues.BUTTON_BACK_TO_MAIN_MENU, this.game.defaultFont);
        backToMainMenuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.log(Tag.NAVIGATE.name(), StringValues.SCREEN_MAIN_MENU);
                dispose();
                game.setScreen(new MainMenuScreen(game));
                return true;
            }
        });
        backToMainMenuButton.addListener(new ButtonEffectListener(backToMainMenuButton));

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

        Table table = ElementFactory.generateDefaultTable();
        table.row().expand().padBottom(200f);
        table.add(endTextLabel).bottom();
        table.row().padLeft(20f).padBottom(2.5f);
        table.add(startNewGameButton).left();
        table.row().padLeft(20f).padBottom(2.5f);
        table.add(backToMainMenuButton).left();
        table.row().padLeft(20f).padBottom(20f);
        table.add(exitButton).left();

        this.game.stage.addActor(table);
    }
 
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
 
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
 
        game.batch.begin();
        game.batch.draw(background, 0, 0, Resolution.WINDOW_WIDTH.value, Resolution.WINDOW_HEIGHT.value);
        game.batch.end();

        game.stage.act();
        game.stage.draw();
    }
 
    @Override
    public void resize(int width, int height) {
        Gdx.app.log(Tag.RESIZE.name(), String.format(StringValues.VALUE_RESOLUTION, width, height));
        game.viewport.update(width, height);
        game.stage.getViewport().update(width, height, false);
    }
 
    @Override
    public void show() {
        music.play();
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
        music.dispose();
        background.dispose();
        game.stage.clear();
        Gdx.app.log(Tag.DISPOSE.name(), StringValues.SCREEN_END);
    }
}
