package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

class Weapon {

    Vector2 pos = new Vector2();

    boolean SHOOTING = false;
    float stateTime;

    Rectangle bounds;

    Weapon(){
        bounds = new Rectangle(0, 0, Assets.harpoonAnim.getKeyFrame(0).getRegionWidth(), 0);

    }

    void shoot(float x, float y){
        pos.x=x;
        pos.y=y;

        SHOOTING = true;
        bounds.setPosition(pos);
    }

    void endShoot(){
        bounds.setPosition(0,0);
        bounds.setHeight(0);

        SHOOTING = false;
    }

    void update(float delta){
        stateTime += delta;

        if(SHOOTING)
            bounds.setHeight(bounds.getHeight()+2);
    }
}
