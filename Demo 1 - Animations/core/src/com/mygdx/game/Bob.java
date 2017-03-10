package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by gerard on 10/03/2017.
 */

public class Bob {
    static final int IDLE = 0;
    static final int RUN = 1;
    static final int JUMP = 2;

    static final int LEFT = -1;
    static final int RIGHT = 1;

    static final float JUMP_VELOCITY = 10;
    static final float GRAVITY = 0.4f;
    static final float SPEED = 6f;

    Vector2 pos = new Vector2();
    Vector2 speed = new Vector2();

    int state;
    float stateTime;
    int dir = LEFT;

    public Bob(float x, float y){
        pos.x = x;
        pos.y = y;

        state = IDLE;
        stateTime = 0;
    }

    public void update (float deltaTime) {
        processKeys();

        speed.y += -GRAVITY;

        tryMove();

        stateTime += deltaTime;
    }

    public void processKeys () {

        if ((Gdx.input.isKeyPressed(Input.Keys.W)) && state != JUMP) {
            state = JUMP;
            speed.y = JUMP_VELOCITY;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            if (state != JUMP) state = RUN;
            dir = LEFT;
            speed.x = SPEED * dir;

        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (state != JUMP) state = RUN;
            dir = RIGHT;
            speed.x = SPEED * dir;

        } else {

            if (state != JUMP) state = IDLE;
            speed.x = 0;
        }
    }

    public void tryMove () {
        pos.x += speed.x;

        pos.y += speed.y;

        if (pos.y <= 0) {
            pos.y = 0;
            state = Math.abs(speed.x) > 0.1f ? RUN : IDLE;
        }
    }
}
