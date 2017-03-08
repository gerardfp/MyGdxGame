package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class GameScreen extends MyGdxGameScreen {
    SpriteBatch batch;
    Texture img;

    Array<Ball> balls = new Array<Ball>();

    float newBallTimer;
    float gravity = -0.4f;
    float spriteSize = 100;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        balls.add(new Ball());

        newBallTimer = 0;
    }

    public void update(float delta){
        newBallTimer += delta;

        for(Ball b : balls){
            b.x += b.speedX * b.directionX;
            b.y += b.speedY * b.directionY;

            b.speedY += gravity * b.directionY;

            if(b.x > Gdx.graphics.getWidth() - spriteSize || b.x < 0){
                b.directionX *= -1;
            }

            if(b.y < 0){
                b.speedY = b.reboundSpeed;
            }

            if(b.y < 0 || b.speedY <= 0){
                b.directionY *= -1;
            }
        }

        if(newBallTimer > 1){
            balls.add(new Ball());
            newBallTimer = 0;
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0.52f, 0.80f, 0.87f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for(Ball b: balls) {
            batch.draw(img, b.x, b.y, spriteSize, spriteSize);
        }
        batch.end();
    }


    @Override
    public void hide() {
        batch.dispose();
        img.dispose();
    }
}
