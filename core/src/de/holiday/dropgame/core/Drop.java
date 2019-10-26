package de.holiday.dropgame.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.holiday.dropgame.screens.MainMenuScreen;
import de.holiday.dropgame.values.Assets;
import de.holiday.dropgame.utils.ElementFactory;
import de.holiday.dropgame.utils.GameConfiguration;

public class Drop extends Game {

    public SpriteBatch batch;
    public BitmapFont smallFont;
    public BitmapFont defaultFont;
    public BitmapFont titleFont;
    public Viewport viewport;
    public Stage stage;
    public OrthographicCamera camera;
    public GameConfiguration configuration;

    public void create() {
        batch = new SpriteBatch();

        smallFont = ElementFactory.generateBitmapFont(Assets.FONT_ROMAN_SERIF, ElementFactory.SMALL_FONT_SIZE);
        defaultFont = ElementFactory.generateBitmapFont(Assets.FONT_ROMAN_SERIF, ElementFactory.FONT_SIZE);
        titleFont = ElementFactory.generateBitmapFont(Assets.FONT_ROMAN_SERIF, ElementFactory.TITLE_FONT_SIZE);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport);
        configuration = new GameConfiguration();

        Gdx.input.setInputProcessor(stage);
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        smallFont.dispose();
        defaultFont.dispose();
        titleFont.dispose();
        stage.dispose();
    }

}
