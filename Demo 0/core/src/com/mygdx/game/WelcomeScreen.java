package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WelcomeScreen extends MyGdxGameScreen {

    float startGameTimer = 0;
    BitmapFont font;
    SpriteBatch batch;

    public WelcomeScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        startGameTimer += delta;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Bouncing balls " + (int) startGameTimer, Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()/2);
        batch.end();

        if(startGameTimer > 3){
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        font.dispose();
    }
}
