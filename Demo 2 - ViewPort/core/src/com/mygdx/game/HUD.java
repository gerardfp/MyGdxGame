package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {

    World world;
    SpriteBatch batch;
    BitmapFont font;
    OrthographicCamera camera;

    HUD(World world, OrthographicCamera camera){
        this.world = world;
        this.camera = camera;
        this.camera.position.x = camera.viewportWidth /2f;
        this.camera.position.y = camera.viewportHeight /2f;
        camera.update();

        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    public void render(){
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "Dr. Fred", camera.viewportWidth-60, camera.viewportHeight-10);
        batch.end();
    }
}
