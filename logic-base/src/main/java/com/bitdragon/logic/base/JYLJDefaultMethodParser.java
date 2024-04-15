package com.bitdragon.logic.base;

import com.iohao.game.action.skeleton.core.ActionCommand;
import com.iohao.game.action.skeleton.core.DataCodecKit;
import com.iohao.game.action.skeleton.core.flow.parser.MethodParser;
import com.iohao.game.action.skeleton.protocol.wrapper.ByteValueList;
import com.iohao.game.common.kit.CollKit;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JYLJDefaultMethodParser implements MethodParser {
    @Override
    public Class<?> getActualClazz(ActionCommand.MethodParamResultInfo methodParamResultInfo) {
        return methodParamResultInfo.getActualTypeArgumentClazz();
    }

    @Override
    public Object parseParam(byte[] data, ActionCommand.ParamInfo paramInfo) {
        Class<?> actualTypeArgumentClazz = paramInfo.getActualTypeArgumentClazz();

        if (paramInfo.isList()) {
            if (Objects.isNull(data)) {
                return Collections.emptyList();
            }

            ByteValueList byteValueList = DataCodecKit.decode(data, ByteValueList.class);

            if (CollKit.isEmpty(byteValueList.values)) {
                return Collections.emptyList();
            }

            return byteValueList.values.stream()
                    .map(bytes -> DataCodecKit.decode(bytes, actualTypeArgumentClazz))
                    .toList();
        }

        if (Objects.isNull(data)) {
            try {
                return actualTypeArgumentClazz.getDeclaredConstructor().newInstance();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        return DataCodecKit.decode(data, actualTypeArgumentClazz);
    }

    @Override
    public Object parseResult(ActionCommand.ActionMethodReturnInfo actionMethodReturnInfo, Object methodResult) {

        if (actionMethodReturnInfo.isList()) {

            List<Object> list = (List<Object>) methodResult;

            ByteValueList byteValueList = new ByteValueList();
            byteValueList.values = list.stream()
                    .map(DataCodecKit::encode)
                    .collect(Collectors.toList());

            return byteValueList;
        }

        return methodResult;
    }
}
