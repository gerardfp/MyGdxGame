package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen extends MyGdxGameScreen {

    World world;

    OrthographicCamera worldCamera;
    FitViewport worldViewport;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        if (! Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Gdx.graphics.setWindowedMode(256*3,192*3);
        }

        int SCENE_WIDTH = 256;
        int SCENE_HEIGHT = 192;

        worldCamera = new OrthographicCamera();
        worldViewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT, worldCamera);
        worldViewport.apply();

        world = new World(SCENE_WIDTH, SCENE_HEIGHT, worldCamera);

    }

    @Override
    public void render(float delta) {
        world.update(delta);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.render();
    }

    public void resize(int width, int height) {
        worldViewport.update(width, height);
    }
}
