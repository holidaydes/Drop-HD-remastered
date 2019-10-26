package de.holiday.dropgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import de.holiday.dropgame.core.Drop;
import de.holiday.dropgame.utils.*;
import de.holiday.dropgame.values.*;

import java.util.Iterator;

public class GameScreen implements Screen {
    private final Drop game;
 
    private Texture dropImage;
    private Texture bucketImage;
    private Texture rainBackground;
    private Sound dropSound;
    private Sound loseSound;
    private Music rainMusic;
    private Rectangle bucket;
    private Array<Rectangle> raindrops;
    private long lastDropTime;
    private int gatheredDrops = 0;
    private int lostDrops = 0;
    private Label gatheredDropsDisplay;
    private Label lostDropsDisplay;
    private boolean running = true;
 
    public GameScreen(final Drop game) {
        this.game = game;

        Label gatheredDropsLabel = ElementFactory.generateLabel(StringValues.LABEL_GATHERED_DROPS, game.defaultFont);
        gatheredDropsDisplay = ElementFactory.generateLabel(String.format(StringValues.START_GATHERED_DROPS_AMOUNT, game.configuration.getDropGatheringLimit()), game.defaultFont);
        Label lostDropsLabel = ElementFactory.generateLabel(StringValues.LABEL_LOST_DROPS, game.defaultFont);
        lostDropsDisplay = ElementFactory.generateLabel(String.format(StringValues.START_LOST_DROPS_AMOUNT, game.configuration.getDropLosingLimit()), game.defaultFont);

        Table hud = ElementFactory.generateDefaultTable();
        hud.left().top().pad(10f);
        hud.row().pad(5f);
        hud.add(gatheredDropsLabel);
        hud.add(gatheredDropsDisplay).width(100f);
        hud.row().pad(5f);
        hud.add(lostDropsLabel);
        hud.add(lostDropsDisplay).width(100f);

        this.game.stage.addActor(hud);

        dropImage = new Texture(Assets.ITEM_DROP.load());
        bucketImage = new Texture(Assets.ITEM_BUCKET.load());
        rainBackground = new Texture(Assets.BACKGROUND_RAIN.load());

        dropSound = Gdx.audio.newSound(Assets.SOUND_DROP.load());
        loseSound = Gdx.audio.newSound(Assets.SOUND_FUCK.load());
        rainMusic = Gdx.audio.newMusic(Assets.SOUND_RAIN.load());
        rainMusic.setLooping(true);

        bucket = new Rectangle();
        bucket.x = Resolution.WINDOW_WIDTH.value / 2 - Resolution.ITEM_BUCKET_WIDTH.value / 2;
        bucket.y = 20;

        bucket.width = Resolution.ITEM_BUCKET_WIDTH.value;
        bucket.height = Resolution.ITEM_BUCKET_HEIGHT.value;

        raindrops = new Array<>();
        spawnRaindrop();
    }
 
    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, Resolution.WINDOW_WIDTH.value - Resolution.ITEM_DROP_WIDTH.value);
        raindrop.y = Resolution.WINDOW_HEIGHT.value;
        raindrop.width = Resolution.ITEM_DROP_WIDTH.value;
        raindrop.height = Resolution.ITEM_DROP_HEIGHT.value;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }
 
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        game.batch.draw(rainBackground, 0, 0, Resolution.WINDOW_WIDTH.value, Resolution.WINDOW_HEIGHT.value);
        game.batch.draw(bucketImage, bucket.x, bucket.y, Resolution.ITEM_BUCKET_WIDTH.value, Resolution.ITEM_BUCKET_HEIGHT.value);
        for (Rectangle raindrop : raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y, Resolution.ITEM_DROP_WIDTH.value, Resolution.ITEM_DROP_HEIGHT.value);
        }
        if (!running) {
            game.titleFont.draw(game.batch, StringValues.LABEL_PAUSED, Resolution.WINDOW_WIDTH.value / 2 - 100f / 2, Resolution.WINDOW_HEIGHT.value / 2, 100f, Align.center, false);
        }
        game.batch.end();

        game.stage.act();
        game.stage.draw();

        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            running = !running;
            if (!running) {
                rainMusic.pause();
                Gdx.app.log(Tag.ACTION.name(), StringValues.ACTION_PAUSE);
            } else {
                rainMusic.play();
                Gdx.app.log(Tag.ACTION.name(), StringValues.ACTION_RESUME);
            }
        }

        if (running) {
            update(delta);
        }
    }

    private void update(float delta) {
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            bucket.x = touchPos.x - Resolution.ITEM_BUCKET_WIDTH.value / 2;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            bucket.x -= 1500 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            bucket.x += 1500 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > Resolution.WINDOW_WIDTH.value - Resolution.ITEM_BUCKET_WIDTH.value)
            bucket.x = Resolution.WINDOW_WIDTH.value - Resolution.ITEM_BUCKET_WIDTH.value;

        if (TimeUtils.nanoTime() - lastDropTime > game.configuration.getDropSpawnTime())
            spawnRaindrop();

        Iterator<Rectangle> iterator = raindrops.iterator();
        while (iterator.hasNext()) {
            Rectangle raindrop = iterator.next();
            raindrop.y -= game.configuration.getDropSpeed() * Gdx.graphics.getDeltaTime();
            if (raindrop.y + Resolution.ITEM_DROP_HEIGHT.value < 0){
                lostDrops++;
                lostDropsDisplay.setText(lostDrops + " / " + game.configuration.getDropLosingLimit());
                loseSound.play();
                iterator.remove();
            }
            if (raindrop.overlaps(bucket)) {
                gatheredDrops++;
                gatheredDropsDisplay.setText(gatheredDrops + " / " + game.configuration.getDropGatheringLimit());
                dropSound.play();
                iterator.remove();
            }
        }

        if (gatheredDrops == game.configuration.getDropGatheringLimit() || lostDrops == game.configuration.getDropLosingLimit()) {
            Gdx.app.log(Tag.NAVIGATE.name(), StringValues.SCREEN_END);
            dispose();
            game.setScreen(new EndScreen(game, Result.getResult(gatheredDrops, lostDrops, game.configuration)));
        }
    }
 
    @Override
    public void resize(int width, int height) {
        Gdx.app.log(Tag.RESIZE.name(), String.format(StringValues.VALUE_RESOLUTION, width, height));
        game.viewport.update(width, height);
        game.stage.getViewport().update(width, height, false);
    }
 
    @Override
    public void show() {
        rainMusic.play();
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
        game.stage.clear();
        dropImage.dispose();
        bucketImage.dispose();
        rainBackground.dispose();
        dropSound.dispose();
        loseSound.dispose();
        rainMusic.dispose();
        Gdx.app.log(Tag.DISPOSE.name(), StringValues.SCREEN_GAME);
    }
 
}