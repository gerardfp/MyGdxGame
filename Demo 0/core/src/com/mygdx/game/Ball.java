package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Ball {
    int x=Gdx.graphics.getWidth()/2, y=Gdx.graphics.getHeight();
    float speedX=3, speedY=0;
    static float reboundSpeed = 13;
    int directionX = 1, directionY = -1;
}
