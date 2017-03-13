package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;


class World {
    SpriteBatch batch;

    Player player;

    OrthographicCamera cam;

    int WORLD_WIDTH;
    int WORLD_HEIGHT;

    World(OrthographicCamera camera) {
        WORLD_WIDTH = Assets.background.getWidth();
        WORLD_HEIGHT = Assets.background.getHeight();
        this.cam = camera;

        batch = new SpriteBatch();

        player = new Player(40);
    }

    void update(float delta){
        player.update(delta);
        handleInput();
        collide();

        cam.position.set(player.pos,   cam.viewportHeight / 2f, 0);
        cam.position.x = MathUtils.clamp(cam.position.x, cam.viewportWidth / 2f,  WORLD_WIDTH - cam.viewportWidth / 2f);

        cam.update();
    }

    void collide(){

        if(player.bounds.x < 0){
            player.setPos(0);
        } else if(player.bounds.x + player.bounds.width > WORLD_WIDTH){
            player.setPos(WORLD_WIDTH - player.bounds.width);
        }
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            cam.zoom -= 0.02;
        }

        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 1f);
    }

    void render(){

        batch.setProjectionMatrix(cam.combined);
        batch.begin();

        batch.draw(Assets.background,0,0);
        batch.draw(Assets.playerAnims[player.state].getKeyFrame(player.stateTime, true), player.pos, 5);
        batch.end();
    }
}
