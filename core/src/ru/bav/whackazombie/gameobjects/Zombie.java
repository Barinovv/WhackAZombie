package ru.bav.whackazombie.gameobjects;

import com.badlogic.gdx.Gdx;
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
    public float height, width; // размеры зомби
    public float scaleFactor; // коэффициент масштабирования зомби в зависимости от экрана устройства

    public enum State {GOINGUP, GOINGDOWN, UNDERGROUND};

     // определение состояний зомби
    public State state = State.GOINGUP; // переменная, описывающая текущее состояние зомби
    public float currentHeight = 0.0f; // текущая величина высоты зомби относительно ямы
    public float speed = 2f; // скорость, с которой зомби будет двигаться вверх/вниз
    public float timeUnderGround = 0.0f; // время с тех пор, как зомби спрятался под землю
    public float maxTimeUnderGround = 0.8f; // максимальное время, разрешенное зомби оставаться под землей

    public void render(SpriteBatch batch) {

        zombieSprite.draw(batch);
    }

    public void update() {
        switch (state) {
            case UNDERGROUND:

                if (timeUnderGround >= maxTimeUnderGround) {
                    state = State.GOINGUP;
                    timeUnderGround = 0.0f;
                } else {
                    timeUnderGround += Gdx.graphics.getDeltaTime();
                }
                break;

            // увеличиваем высоту до максимума, как только высота достигнет максимума - меняем состояние
            case GOINGUP:
                currentHeight += speed;
                if (currentHeight >= height) {
                    currentHeight = height;
                    state = State.GOINGDOWN;
                }
                break;
            // уменьшаем высоту до минимума(0), как только выоста достигнет минимума - меняем состояние
            case GOINGDOWN:
                currentHeight -= speed;
                if (currentHeight <= 0.0) {
                    currentHeight = 0.0f;
                    state = State.GOINGUP;
                }
                break;
        }
        // рисуем только часть изображения зомби. Зависит от высоты над ямой
        zombieSprite.setRegion(0, 0, (int) (width / scaleFactor), (int) (currentHeight / scaleFactor));
        zombieSprite.setSize(zombieSprite.getWidth(), currentHeight);
    }
}
