package de.holiday.dropgame.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import de.holiday.dropgame.values.Assets;

public class ElementFactory {

    public static final boolean DEFAULT_FILL_PARENT = true;
    public static final boolean DEFAULT_TRANSFORM_ENABLED = true;
    public static final boolean TABLE_DEBUG_ENABLED = false;
    public static final int SMALL_FONT_SIZE = 24;
    public static final int FONT_SIZE = 32;
    public static final int TITLE_FONT_SIZE = 48;

    public static Label generateLabel(String labelText, BitmapFont font) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        return new Label(labelText, labelStyle);
    }

    public static BitmapFont generateBitmapFont(Assets assets, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(assets.load());
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    public static Table generateDefaultTable() {
        return generateTable(DEFAULT_FILL_PARENT, TABLE_DEBUG_ENABLED);
    }

    public static Table generateTable(boolean fillParent, boolean debug) {
        Table table = new Table();
        table.setFillParent(fillParent);
        table.setDebug(debug);
        return table;
    }

    public static TextButton generateDefaultTextButton(String text, BitmapFont font) {
        return generateTextButton(text, font, DEFAULT_TRANSFORM_ENABLED);
    }

    public static TextButton generateTextButton(String text, BitmapFont font, boolean transform) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        TextButton textButton = new TextButton(text, textButtonStyle);
        textButton.setTransform(transform);
        return textButton;
    }

    public static Skin generateDefaultSkin() {
        return new Skin(Assets.SKIN_UI_JSON.load(), new TextureAtlas(Assets.SKIN_UI_ATLAS.load()));
    }

}
