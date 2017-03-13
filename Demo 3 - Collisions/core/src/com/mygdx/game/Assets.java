package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Assets {

    static TextureRegion playerAnim;
    static Animation<TextureRegion> harpoonAnim;
    static TextureRegion[] ballAnims = new TextureRegion[5];
    static TextureRegion background;

    {
        TextureAtlas spriteSheet = new TextureAtlas("superpang.txt");

        playerAnim = spriteSheet.findRegion("shoot");

        harpoonAnim = new Animation<TextureRegion>(0.05f, spriteSheet.findRegions("harpoon"));

        for(int i=0; i<5; i++) ballAnims[i] = spriteSheet.findRegion("ball" + i);

        background = spriteSheet.findRegion("background");
    }
}
