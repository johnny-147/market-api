package com.kemiex.marketapi.model;

public enum Type {
    ITALIAN_PASTA("ITALIAN_PASTA"),
    GALUSTE_BANATENE("GALUSTE_BANATENE"),
    SPATZLE("SPATZLE");

    Type(String type) {
        this.type = type;
    };

    private String type;

    public String getType() {
        return type;
    }

    public static String getEnumByString(String format){
        for(Type e : Type.values()){
            if(e.type.equals(format)) return e.name();
        }
        return null;
    }
}
