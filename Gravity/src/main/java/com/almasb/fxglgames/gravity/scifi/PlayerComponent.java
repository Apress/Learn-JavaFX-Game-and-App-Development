///*
// * The MIT License (MIT)
// *
// * FXGL - JavaFX Game Library
// *
// * Copyright (c) 2015-2017 AlmasB (almaslvl@gmail.com)
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in
// * all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package com.almasb.fxglgames.gravity.scifi;
//
//import com.almasb.fxgl.entity.Entity;
//import com.almasb.fxgl.entity.SpawnData;
//import com.almasb.fxgl.entity.component.Component;
//import com.almasb.fxgl.entity.components.ViewComponent;
//import com.almasb.fxgl.physics.PhysicsComponent;
//import com.almasb.fxgl.texture.Texture;
//import javafx.geometry.Point2D;
//
///**
// * @author Almas Baimagambetov (almaslvl@gmail.com)
// */
//public class PlayerComponent extends Component {
//
//    private ViewComponent view;
//    private PhysicsComponent physics;
//
//    private double oldX = 0;
//    private boolean isStatic = true;
//
//    private Texture staticTexture;
//    private Texture animatedTexture;
//
//    public PlayerComponent(Texture staticTexture, Texture animatedTexture) {
//        this.staticTexture = staticTexture;
//        this.animatedTexture = animatedTexture;
//    }
//
//    @Override
//    public void onAdded() {
//        oldX = position.getX();
//        view.getView().addNode(staticTexture);
//    }
//
//    // not the most elegant solution for static checks
//    // will replace when physics API allows us to check if body is ready
//    // then simply query velocity magnitude
//    @Override
//    public void onUpdate(double tpf) {
//        //if (oldX == position.getX()) {
//        if (Math.abs(physics.getVelocityX()) == 0) {
//            if (!isStatic) {
//                view.getView().removeNode(animatedTexture);
//                view.getView().addNode(staticTexture);
//                isStatic = true;
//            }
//        }
//
//        oldX = position.getX();
//
//        if (Math.abs(physics.getVelocityX()) < 140)
//            physics.setVelocityX(0);
//    }
//
//    public void left() {
//        view.getView().setScaleX(-1);
//        physics.setVelocityX(-150);
//
//        if (isStatic) {
//            view.getView().removeNode(staticTexture);
//            view.getView().addNode(animatedTexture);
//            isStatic = false;
//        }
//    }
//
//    public void right() {
//        view.getView().setScaleX(1);
//        physics.setVelocityX(150);
//
//        if (isStatic) {
//            view.getView().removeNode(staticTexture);
//            view.getView().addNode(animatedTexture);
//            isStatic = false;
//        }
//    }
//
//    public void jump() {
//        physics.setVelocityY(-350);
//    }
//
//    public void stop() {
//        physics.setVelocityX(physics.getVelocityX() * 0.7);
//    }
//
//    public void shoot(Point2D endPoint) {
//        double x = position.getX();
//        double y = position.getY();
//
//        Point2D velocity = endPoint
//                .subtract(x, y)
//                .normalize()
//                .multiply(500);
//
//        getEntity().getWorld().spawn("Arrow",
//                new SpawnData(x, y)
//                        .put("velocity", velocity)
//                        .put("shooter", getEntity()));
//    }
//}
