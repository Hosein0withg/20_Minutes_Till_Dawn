package com.tilldown.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.tilldown.Model.Tree;

import java.util.ArrayList;

public class SpawnTree implements Screen {
    private ArrayList<Tree> trees = new ArrayList<>();
    private final int mapWidth = 2700;
    private final int mapHeight = 2000;
    private final int treeCount = 20;
    private SpriteBatch batch;

    public SpawnTree() {
        batch = new SpriteBatch();
        spawnTrees();
    }

    public void spawnTrees() {
        for (int i = 0; i < treeCount; i++) {
            int x = MathUtils.random(10, mapWidth - 10);
            int y = MathUtils.random(10, mapHeight - 10);
            Tree tree = new Tree(x, y);
            trees.add(tree);
        }
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        for (Tree tree : trees) {
            tree.getTexture().dispose();
        }
    }
}
