package de.holiday.dropgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import de.holiday.dropgame.core.Drop;
import de.holiday.dropgame.listeners.ButtonEffectListener;
import de.holiday.dropgame.listeners.PrecipitationTypeInputListener;
import de.holiday.dropgame.utils.*;
import de.holiday.dropgame.values.*;

import java.util.LinkedList;

public class OptionsScreen implements Screen {

    private final Drop game;
    private Texture mainBackground;
    private TextButton backToMainMenuButton;
    private LinkedList<TextButton> precipitationTypeButtons = new LinkedList<>();
    private Skin skin = ElementFactory.generateDefaultSkin();

    private Label dropGatheringLimitValueLabel;
    private Label dropLosingLimitValueLabel;
    private Label dropSpawnTimeValueLabel;
    private Label dropSpeedValueLabel;

    private Slider dropGatheringLimitSlider;
    private Slider dropLosingLimitSlider;
    private Slider dropSpeedSlider;
    private Slider dropSpawnTimeSlider;

    public OptionsScreen(final Drop game) {
        this.game = game;

        mainBackground = new Texture(Assets.BACKGROUND_MAIN.load());

        Label precipitationTypeLabel = ElementFactory.generateLabel(StringValues.LABEL_PRECIPITATION_TYPE, this.game.smallFont);
        Label dropGatheringLimitLabel = ElementFactory.generateLabel(StringValues.LABEL_DROP_GATHERING_LIMIT, this.game.smallFont);
        Label dropLosingLimitLabel = ElementFactory.generateLabel(StringValues.LABEL_DROP_LOSING_LIMIT, this.game.smallFont);
        Label dropSpawnTimeLabel = ElementFactory.generateLabel(StringValues.LABEL_DROP_SPAWN_TIME, this.game.smallFont);
        Label dropSpeedLabel = ElementFactory.generateLabel(StringValues.LABEL_DROP_SPEED, this.game.smallFont);

        dropGatheringLimitValueLabel = ElementFactory.generateLabel(String.valueOf(this.game.configuration.getDropGatheringLimit()), this.game.smallFont);
        dropLosingLimitValueLabel = ElementFactory.generateLabel(String.valueOf(this.game.configuration.getDropLosingLimit()), this.game.smallFont);
        dropSpawnTimeValueLabel = ElementFactory.generateLabel(String.format("%,d" , this.game.configuration.getDropSpawnTime()), this.game.smallFont);
        dropSpeedValueLabel = ElementFactory.generateLabel(String.valueOf(this.game.configuration.getDropSpeed()), this.game.smallFont);

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

        dropGatheringLimitSlider = new Slider(GameConfiguration.DROP_GATHERING_LIMIT_MIN, GameConfiguration.DROP_GATHERING_LIMIT_MAX, 1f, false, skin);
        dropGatheringLimitSlider.setValue(this.game.configuration.getDropGatheringLimit());
        dropGatheringLimitSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log(Tag.VALUE.name(), String.format(StringValues.VALUE_DROP_GATHERING_LIMIT, dropGatheringLimitSlider.getValue()));
                game.configuration.setDropGatheringLimit(Math.round(dropGatheringLimitSlider.getValue()));
            }
        });

        dropLosingLimitSlider = new Slider(GameConfiguration.DROP_LOSING_LIMIT_MIN, GameConfiguration.DROP_LOSING_LIMIT_MAX, 1f, false, skin);
        dropLosingLimitSlider.setValue(this.game.configuration.getDropLosingLimit());
        dropLosingLimitSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log(Tag.VALUE.name(), String.format(StringValues.VALUE_DROP_LOSING_LIMIT, dropLosingLimitSlider.getValue()));
                game.configuration.setDropLosingLimit(Math.round(dropLosingLimitSlider.getValue()));
            }
        });

        dropSpawnTimeSlider = new Slider(GameConfiguration.DROP_SPAWN_TIME_MIN, GameConfiguration.DROP_SPAWN_TIME_MAX, 50_000_000f, false, skin);
        dropSpawnTimeSlider.setValue(this.game.configuration.getDropSpawnTime());
        dropSpawnTimeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log(Tag.VALUE.name(), String.format(StringValues.VALUE_DROP_SPAWN_TIME, dropSpawnTimeSlider.getValue()));
                game.configuration.setDropSpawnTime(Math.round(dropSpawnTimeSlider.getValue()));
            }
        });

        dropSpeedSlider = new Slider(GameConfiguration.DROP_SPEED_MIN, GameConfiguration.DROP_SPEED_MAX, 100f, false, skin);
        dropSpeedSlider.setValue(this.game.configuration.getDropSpeed());
        dropSpeedSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log(Tag.VALUE.name(), String.format(StringValues.VALUE_DROP_SPEED, dropSpeedSlider.getValue()));
                game.configuration.setDropSpeed(Math.round(dropSpeedSlider.getValue()));
            }
        });

        Table customSettingsTable = ElementFactory.generateDefaultTable();
        customSettingsTable.add(dropGatheringLimitLabel);
        customSettingsTable.row();
        customSettingsTable.add(dropGatheringLimitValueLabel);
        customSettingsTable.row().padBottom(5f);
        customSettingsTable.add(dropGatheringLimitSlider);
        customSettingsTable.row();
        customSettingsTable.add(dropLosingLimitLabel);
        customSettingsTable.row();
        customSettingsTable.add(dropLosingLimitValueLabel);
        customSettingsTable.row().padBottom(5f);
        customSettingsTable.add(dropLosingLimitSlider);
        customSettingsTable.row();
        customSettingsTable.add(dropSpawnTimeLabel);
        customSettingsTable.row();
        customSettingsTable.add(dropSpawnTimeValueLabel);
        customSettingsTable.row().padBottom(5f);
        customSettingsTable.add(dropSpawnTimeSlider);
        customSettingsTable.row();
        customSettingsTable.add(dropSpeedLabel);
        customSettingsTable.row();
        customSettingsTable.add(dropSpeedValueLabel);
        customSettingsTable.row();
        customSettingsTable.add(dropSpeedSlider);
        customSettingsTable.row();

        VerticalGroup customSettingsVerticalGroup = new VerticalGroup();
        customSettingsVerticalGroup.setDebug(ElementFactory.TABLE_DEBUG_ENABLED);
        customSettingsVerticalGroup.addActor(customSettingsTable);

        VerticalGroup precipitationTypeDescriptionVerticalGroup = new VerticalGroup();
        precipitationTypeDescriptionVerticalGroup.setDebug(ElementFactory.TABLE_DEBUG_ENABLED);
        precipitationTypeDescriptionVerticalGroup.addActor(backToMainMenuButton);

        generatePrecipitationTypeButtons();

        Table precipitationTypeChooserTable = ElementFactory.generateDefaultTable();
        precipitationTypeChooserTable.add(precipitationTypeLabel).padBottom(10f);
        for (TextButton difficultyButton : precipitationTypeButtons) {
            precipitationTypeChooserTable.row().padBottom(5f);
            precipitationTypeChooserTable.add(difficultyButton);
        }

        VerticalGroup precipitationTypeChooserVerticalGroup = new VerticalGroup();
        precipitationTypeChooserVerticalGroup.setDebug(ElementFactory.TABLE_DEBUG_ENABLED);
        precipitationTypeChooserVerticalGroup.addActor(precipitationTypeChooserTable);

        Table table = ElementFactory.generateDefaultTable();
        table.row().expand();
        table.add().uniform();
        table.add(precipitationTypeChooserVerticalGroup).uniform();
        table.add(customSettingsVerticalGroup).uniform().padRight(20f);
        table.row().expandX().colspan(3);
        table.add(precipitationTypeDescriptionVerticalGroup).bottom().left().padLeft(20f).padBottom(20f);

        this.game.stage.addActor(table);
    }

    @Override
    public void show() {

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

        updateWidgets();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(Tag.RESIZE.name(), String.format(StringValues.VALUE_RESOLUTION, width, height));
        game.viewport.update(width, height);
        game.stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.stage.clear();
        mainBackground.dispose();
        Gdx.app.log(Tag.DISPOSE.name(), StringValues.SCREEN_OPTIONS);
    }

    private void generatePrecipitationTypeButtons() {
        for (final PrecipitationType precipitationType : PrecipitationType.values()) {
            TextButton precipitationTypeButton = ElementFactory.generateDefaultTextButton(precipitationType.name, this.game.defaultFont);
            precipitationTypeButton.addListener(new PrecipitationTypeInputListener(precipitationType, precipitationTypeButton) {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log(Tag.SELECT.name(), this.precipitationType.name);
                    game.configuration.setPrecipitationType(this.precipitationType);
                    return true;
                }
            });

            precipitationTypeButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    precipitationTypeButtons.forEach(
                            precipitationTypeButton -> {
                                if (precipitationTypeButton.getText().toString().equals(game.configuration.getPrecipitationTypeName())) {
                                    precipitationTypeButton.getStyle().fontColor = Color.BLACK;
                                } else {
                                    precipitationTypeButton.getStyle().fontColor = Color.WHITE;
                                }
                            }
                    );
                }
            });

            precipitationTypeButton.addListener(new ButtonEffectListener(precipitationTypeButton));
            if (game.configuration.getPrecipitationType() == precipitationType) {
                precipitationTypeButton.getStyle().fontColor = Color.BLACK;
            }

            precipitationTypeButtons.add(precipitationTypeButton);
        }
    }

    private void updateWidgets() {
        dropGatheringLimitValueLabel.setText(String.valueOf(this.game.configuration.getDropGatheringLimit()));
        dropLosingLimitValueLabel.setText(String.valueOf(this.game.configuration.getDropLosingLimit()));
        dropSpawnTimeValueLabel.setText(String.format("%,d" , this.game.configuration.getDropSpawnTime()));
        dropSpeedValueLabel.setText(String.valueOf(this.game.configuration.getDropSpeed()));

        dropGatheringLimitSlider.setValue(this.game.configuration.getDropGatheringLimit());
        dropLosingLimitSlider.setValue(this.game.configuration.getDropLosingLimit());
        dropSpawnTimeSlider.setValue(this.game.configuration.getDropSpawnTime());
        dropSpeedSlider.setValue(this.game.configuration.getDropSpeed());

        if (game.configuration.getPrecipitationType() == PrecipitationType.CUSTOM) {
            enableSettings(false);
        } else {
            enableSettings(true);
        }
    }

    private void enableSettings(boolean disable) {
        dropGatheringLimitSlider.setDisabled(disable);
        dropGatheringLimitSlider.setVisible(!disable);
        dropLosingLimitSlider.setDisabled(disable);
        dropLosingLimitSlider.setVisible(!disable);
        dropSpeedSlider.setDisabled(disable);
        dropSpeedSlider.setVisible(!disable);
        dropSpawnTimeSlider.setDisabled(disable);
        dropSpawnTimeSlider.setVisible(!disable);
    }

}
