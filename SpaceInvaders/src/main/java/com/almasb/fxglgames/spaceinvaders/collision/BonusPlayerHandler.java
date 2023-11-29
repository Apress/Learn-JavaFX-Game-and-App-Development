/*
 * The MIT License (MIT)
 *
 * FXGL - JavaFX Game Library
 *
 * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.almasb.fxglgames.spaceinvaders.collision;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxglgames.spaceinvaders.BonusType;
import com.almasb.fxglgames.spaceinvaders.SpaceInvadersType;
import com.almasb.fxglgames.spaceinvaders.components.SubTypeComponent;
import com.almasb.fxglgames.spaceinvaders.event.BonusPickupEvent;
import javafx.geometry.Point2D;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class BonusPlayerHandler extends CollisionHandler {

    public BonusPlayerHandler() {
        super(SpaceInvadersType.BONUS, SpaceInvadersType.PLAYER);
    }

    @Override
    protected void onCollisionBegin(Entity bonus, Entity player) {
        BonusType type = (BonusType) bonus.getComponent(SubTypeComponent.class).getValue();
        fire(new BonusPickupEvent(BonusPickupEvent.ANY, type));

        bonus.getComponent(CollidableComponent.class).setValue(false);
        bonus.setUpdateEnabled(false);

        animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.ELASTIC.EASE_IN())
                .onFinished(bonus::removeFromWorld)
                .scale(bonus)
                .to(new Point2D(0, 0))
                .buildAndPlay();
    }
}
