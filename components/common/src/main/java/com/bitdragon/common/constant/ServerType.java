package com.bitdragon.common.constant;

public enum ServerType {
    EXTERNAL(1,"external"),
    EXTERNAL_WS(2,"external_ws"),
    GATEWAY(3,"gateway"),
    LOGIN(4,"login"),
    LOBBY(5,"lobby"),
    CHAT(6,"chat")
    ;

    public int typeCode;
    public String typeName;

    private ServerType(int typeCode,String typeName){
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public static ServerType getServerTypeByName(String typeName){
        for(ServerType type : ServerType.values()){
            if(type.typeName.equals(typeName)){
                return type;
            }
        }
        return null;
    }
}
