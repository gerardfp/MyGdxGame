package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

class Player {
    static final int IDLE = 0;
    static final int LEFT = 1;
    static final int RIGHT = 2;

    private static final float SPEED = 3f;

    float pos;
    private float speed;

    int state;
    float stateTime;

    Rectangle bounds;

    Player(float x){
        pos = x;

        state = IDLE;
        stateTime = 0;
        bounds = new Rectangle(x, 0, Assets.playerAnims[0].getKeyFrame(0).getRegionWidth(), Assets.playerAnims[0].getKeyFrame(0).getRegionHeight());
    }

    void update (float delta) {
        stateTime += delta;

        processKeys();

        setPos(pos + speed);
    }

    void setPos(float x){
        pos = x;
        bounds.setX(pos);
    }

    void processKeys () {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            state = LEFT;
            speed = -SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            state = RIGHT;
            speed = SPEED;
        } else {
            state = IDLE;
            speed = 0;
        }
    }
}
