package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import static com.badlogic.gdx.math.MathUtils.random;

class World {

    boolean DEBUG = true;

    SpriteBatch batch;
    ShapeRenderer shapeRenderer = new ShapeRenderer();


    Player player;
    Array<Ball> balls;
    Array<Ball> removeBalls = new Array<Ball>();

    OrthographicCamera camera;

    float newBallTimer = 0;
    float newBallDelay;

    int WORLD_WIDTH;
    int WORLD_HEIGHT;
    int WORLD_BOUNDS = 8;

    World(int WIDTH, int HEIGHT, OrthographicCamera camera) {
        WORLD_WIDTH = WIDTH;
        WORLD_HEIGHT = HEIGHT;
        this.camera = camera;

        this.camera.position.x = WORLD_WIDTH * 0.5f;
        this.camera.position.y = WORLD_HEIGHT * 0.5f;

        camera.update();

        batch = new SpriteBatch();

        batch.setProjectionMatrix(camera.combined);

        player = new Player(0);
        balls = new Array<Ball>();

        int ballType = (int) (random()*5);
        balls.add(new Ball(ballType, WORLD_WIDTH/2, WORLD_HEIGHT));
        newBallDelay = Ball.newBallDelays[ballType];
    }

    void update(float delta){

        newBallTimer += delta;

        player.update(delta);

        for(Ball b : balls) {
            b.update();
        }

        collide();

        if(newBallTimer > newBallDelay) {
            int ballType = (int) (random()*5);
            balls.add(new Ball(ballType, WORLD_WIDTH/2, WORLD_HEIGHT));
            newBallDelay = Ball.newBallDelays[ballType];
            newBallTimer = 0;
        }
    }

    void collide(){

        for(Ball b : balls) {
            if (b.bounds.y < WORLD_BOUNDS + b.bounds.radius) {
                b.reboundY();
            }
            if (b.bounds.x < WORLD_BOUNDS + b.bounds.radius ){
                b.setPos(WORLD_BOUNDS, b.pos.y);
                b.reboundX();
            } else if(b.bounds.x > WORLD_WIDTH - WORLD_BOUNDS - b.bounds.radius) {
                b.setPos(WORLD_WIDTH - WORLD_BOUNDS - b.bounds.radius*2, b.pos.y);
                b.reboundX();
            }

            if(Intersector.overlaps(b.bounds, player.weapon.bounds)){
                removeBalls.add(b);
                player.weapon.endShoot();
            }
        }

        for(Ball b : removeBalls){
            balls.removeValue(b, true);
            if(b.type < 4) {
                balls.add(new Ball(b.type + 1, b.pos.x, b.pos.y, b.speed.x/b.speed.x));
                balls.add(new Ball(b.type + 1, b.pos.x, b.pos.y, -b.speed.x/b.speed.x));
            }
        }
        removeBalls.clear();

        if(player.bounds.x < WORLD_BOUNDS){
            player.setPos(WORLD_BOUNDS);
        } else if(player.bounds.x + player.bounds.width > WORLD_WIDTH - WORLD_BOUNDS){
            player.setPos(WORLD_WIDTH - WORLD_BOUNDS - player.bounds.width);
        }

        if(player.weapon.bounds.height >= WORLD_HEIGHT - WORLD_BOUNDS){
            player.weapon.endShoot();
        }
    }

    void render(){

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(Assets.background, 0, 0);

        if(player.weapon.SHOOTING) {
            TextureRegion t = Assets.harpoonAnim.getKeyFrame(player.weapon.stateTime, true);
            t.setRegionHeight((int) player.weapon.bounds.height);

            batch.draw(t, player.weapon.pos.x, player.weapon.pos.y);
        }


        batch.draw(Assets.playerAnim, player.pos.x, player.pos.y);


        for(Ball b : balls) {
            batch.draw(Assets.ballAnims[b.type], b.pos.x, b.pos.y);
        }
        batch.end();

        if(DEBUG) {
            // Draw bounds
            shapeRenderer.setProjectionMatrix(camera.combined);

            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

            shapeRenderer.rect(player.bounds.x, player.bounds.y, player.bounds.width, player.bounds.height);

            if (player.weapon.SHOOTING) {
                shapeRenderer.rect(player.weapon.bounds.x, player.weapon.bounds.y, player.weapon.bounds.width, player.weapon.bounds.height);
            }

            for (Ball b : balls) {
                shapeRenderer.circle(b.bounds.x, b.bounds.y, b.bounds.radius);
            }

            shapeRenderer.end();
        }
    }
}
