package com.gy.just.VoltageMonitor.Model.Bean.DetailInfo;

import java.io.Serializable;

/**
 * Created by gy on 2016/4/29.
 */
public class VolTabBean implements Serializable{

    private Node[] list;

    private float min;
    private float max;

    private float left;
    private float right;

    public Node[] getList() {
        return list;
    }

    public void setList(Node[] list) {
        this.list = list;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public class Node implements Serializable{
        public int rn;
        public float time;
        public String timeStr;
        public float vol;
    }
}
