package com.mygdx.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    Vector2 pos;
    Vector2 speed = new Vector2();

    Circle bounds;

    static float[] newBallDelays = {35, 25, 12, 5, 3};
    static float[] reboundSpeeds = {2.2f, 2.05f, 1.95f, 1.75f, 1.6f};
    static float[] xSpeeds = {0.8f, 0.7f, 0.6f, 0.5f, 0.4f};
    float gravity = -0.02f;
    float reboundSpeed;
    int type = 0;

    Ball(int type, float x, float y){
        pos = new Vector2(x, y);
        this.type = type;

        speed.x = xSpeeds[type];
        speed.y = 0;

        reboundSpeed = reboundSpeeds[type];

        float radius = Assets.ballAnims[type].getRegionWidth()/2;
        bounds = new Circle(x, y, radius);
    }

    Ball(int type, float x, float y, float direction){
        this(type, x, y);

        speed.x *= direction;
        speed.y = reboundSpeeds[type]+y*gravity/2;
    }

    void update(){
        setPos(pos.x + speed.x, pos.y + speed.y);
        speed.y += gravity;
    }

    void setPos(float x, float y){
        pos.x = x;
        pos.y = y;

        bounds.setPosition(pos.x + bounds.radius, pos.y + bounds.radius);
    }

    void reboundY(){
        speed.y = reboundSpeed;
    }

    void reboundX(){
        speed.x *= -1;
    }
}
