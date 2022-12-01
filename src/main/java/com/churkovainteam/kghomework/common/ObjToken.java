package com.churkovainteam.kghomework.common;

public enum ObjToken {
    VERTEX("v"),
    TEXTURE("vt"),
    NORMAL("vn"),
    FACE("f");

    private final String text;

    ObjToken(String text) {
        this.text = text;
    }

    public String toString() {
        return this.text;
    }

    public static ObjToken fromString(String strToken) {
        for (ObjToken token : ObjToken.values()) {
            if (strToken.equals(token.text)) {
                return token;
            }
        }

        throw new IllegalArgumentException("Illegal OBJ token name: " + strToken + ".");
    }
}