package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

class Assets {

    static Animation<TextureRegion>[] playerAnims = new Animation[3];
    static Texture background;

    {
        TextureAtlas spriteSheet = new TextureAtlas("drfred.txt");

        playerAnims[Player.RIGHT] = new Animation<TextureRegion>(0.1f, spriteSheet.findRegions("walk"));
        Array<TextureAtlas.AtlasRegion> rightFrames = spriteSheet.findRegions("walk");
        for(TextureRegion tr : rightFrames) tr.flip(true, false);
        playerAnims[Player.LEFT] = new Animation<TextureRegion>(0.1f, rightFrames);
        playerAnims[Player.IDLE]  = new Animation<TextureRegion>(0.1f, spriteSheet.findRegions("idle"));

        background = new Texture("map.png");
    }
}
