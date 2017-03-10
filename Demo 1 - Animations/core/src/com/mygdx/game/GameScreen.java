package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class GameScreen extends MyGdxGameScreen {
    SpriteBatch batch;

    Animation<TextureRegion> bobRunLeft;
    Animation<TextureRegion> bobRunRight;
    Animation<TextureRegion> bobJumpLeft;
    Animation<TextureRegion> bobJumpRight;
    Animation<TextureRegion> bobIdleLeft;
    Animation<TextureRegion> bobIdleRight;

    Bob bob;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        bob = new Bob(0, 0);

        Texture bobTexture = new Texture(Gdx.files.internal("bob.png"));
        TextureRegion[] split = new TextureRegion(bobTexture).split(20, 20)[0];
        TextureRegion[] mirror = new TextureRegion(bobTexture).split(20, 20)[0];
        for (TextureRegion region : mirror)
            region.flip(true, false);
        bobRunRight = new Animation(0.1f, split[0], split[1]);
        bobRunLeft = new Animation(0.1f, mirror[0], mirror[1]);
        bobJumpRight = new Animation(0.1f, split[2], split[3]);
        bobJumpLeft = new Animation(0.1f, mirror[2], mirror[3]);
        bobIdleRight = new Animation(0.5f, split[0], split[4]);
        bobIdleLeft = new Animation(0.5f, mirror[0], mirror[4]);
    }

    @Override
    public void render(float delta) {

        // update
        bob.update(delta);

        // render
        Animation<TextureRegion> anim = null;
        boolean loop = true;
        if (bob.state == Bob.RUN) {
            if (bob.dir == Bob.LEFT)
                anim = bobRunLeft;
            else
                anim = bobRunRight;
        }
        if (bob.state == Bob.IDLE) {
            if (bob.dir == Bob.LEFT) {
                anim = bobIdleLeft;
            }
            else
                anim = bobIdleRight;
        }
        if (bob.state == Bob.JUMP) {
            if (bob.dir == Bob.LEFT)
                anim = bobJumpLeft;
            else
                anim = bobJumpRight;
        }


        Gdx.gl.glClearColor(0.52f, 0.80f, 0.87f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(anim.getKeyFrame(bob.stateTime, loop), bob.pos.x, bob.pos.y);
        batch.end();
    }


    @Override
    public void hide() {
        batch.dispose();
    }
}
