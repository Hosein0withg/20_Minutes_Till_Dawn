package com.tilldown.Model;

public class CollisionRect {
    public float x, y;
    public float width, height;

    public CollisionRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean overlap(CollisionRect other) {
        if ((x < (other.x + other.width) && (other.x + other.width) <= (this.x + this.width)) ||
            this.x <= other.x && other.x < (this.x + this.width)) {
            if ((this.y < (other.y + other.height) && (other.y + other.height) <= (this.y + this.height)) ||
                this.y <= other.y && other.y < (this.y + this.height)) {
                return true;
            }
        }
        return false;
    }

    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean collidesWith(CollisionRect rect) {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y;
    }
}
