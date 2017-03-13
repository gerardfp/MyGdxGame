package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

class Player {

    private static final float SPEED = 3f;

    Vector2 pos;
    private float speed;

    Rectangle bounds;

    Weapon weapon;

    float gameTimer = 0;
    int gamePops = 0;
    int lives = 3;

    Player(float x){
        pos = new Vector2(x, 8);

        weapon = new Weapon();

        bounds = new Rectangle(pos.x, pos.y, Assets.playerAnim.getRegionWidth(), Assets.playerAnim.getRegionHeight());
    }

    void update (float delta) {
        gameTimer += delta;

        processKeys();
        move();

        weapon.update(delta);

        bounds.setPosition(pos);
    }

    void processKeys () {

        if ((Gdx.input.isKeyPressed(Input.Keys.W)) && ! weapon.SHOOTING) {
            shoot();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            speed = -SPEED;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            speed = SPEED;
        } else {
            speed = 0;
        }
    }

    void move() {
        setPos(pos.x + speed);
    }

    void setPos(float x){
        pos.x = x;
        bounds.setX(pos.x);
    }

    void shoot(){
        weapon.shoot(pos.x+6, pos.y);
    }
}
