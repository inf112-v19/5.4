package com.jarlescene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Stats extends Table {

    Sprite sprite;

    public Stats(Skin skin){

        sprite = new Sprite(new Texture(Gdx.files.internal("heart.png")));

        //this.setDebug(true);

        this.add(new Label("HP:", skin));
        this.row();
        for(int i=0; i<3; i++){
            this.add(new Image(sprite.getTexture())).size(30).uniform();
        }


    }

}
