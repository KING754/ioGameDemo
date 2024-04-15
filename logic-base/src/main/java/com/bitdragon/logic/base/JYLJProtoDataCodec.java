package com.bitdragon.logic.base;

import com.iohao.game.action.skeleton.core.codec.DataCodec;
import com.iohao.game.common.kit.ProtoKit;

import java.util.Objects;


@SuppressWarnings("unchecked")
public class JYLJProtoDataCodec implements DataCodec {
    //    byte[] EMPTY_BYTES = CommonConst.EMPTY_BYTES;
    byte[] EMPTY_BYTES = new byte[0];

    @Override
    public byte[] encode(Object data) {
        return ProtoKit.toBytes(data);
    }

    @Override
    public <T> T decode(byte[] data, Class<?> dataClass) {

        if (Objects.isNull(data)) {
            data = EMPTY_BYTES;
        }

        return (T) ProtoKit.parseProtoByte(data, dataClass);
    }
}
