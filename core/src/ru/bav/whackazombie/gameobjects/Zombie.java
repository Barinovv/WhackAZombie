package ru.bav.whackazombie.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;


/**
 * Класс устанавливает спрайты позицию, размеры отображения зомби
 *
 * @author Barinov 1518
 */
public class Zombie {

    public Sprite zombieSprite; // спрайт для отображения зомби
    public Vector2 position = new Vector2();// позиция зомби
    public float height,width; // размеры зомби
    public float scaleFactor; // коэффициент масштабирования зомби в зависимости от экрана устройства

    public void render(SpriteBatch batch){

        zombieSprite.draw(batch);
    }


}
