package com.churkovainteam.kghomework.common;

public enum ObjToken {
    VERTEX("v"),
    TEXTURE("vt"),
    NORMAL("vn"),
    FACE("f");

    private final String text;

    private ObjToken(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }
}