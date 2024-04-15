package com.bitdragon.rpc.tool;

import cn.hutool.core.io.FileUtil;
import com.baidu.bjf.remoting.protobuf.ProtobufIDLProxy;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class JProtoPojoGenTool {
    private static final String PROTO_BASE_PATH = "D:\\jiuyulingjing\\gitea\\proto-project\\rpc\\proto";
    private static final String POJO_SAVE_PATH = "D:\\jiuyulingjing\\gitea\\server\\code\\jylj\\components\\rpc-proto\\src\\main\\java";

    private static void genJPBPojoFileFromProto() throws IOException {
        List<File> allProtoFile = JProtoPojoGenTool.getAllProtoFileFromPath();
        if (allProtoFile == null || allProtoFile.isEmpty()) {
            return;
        }

        for (File protoFile : allProtoFile) {
            log.info("generate with: {}",protoFile.getName());
            ProtobufIDLProxy.generateSource(protoFile,new File(POJO_SAVE_PATH));
//            InputStream fis = FileUtil.getInputStream(protoFile);
//            ProtobufIDLProxy.generateSource(fis, new File("D:\\jiuyulingjing\\gitea\\server\\code\\jylj\\components\\rpc-proto\\src\\main\\java"));
        }
    }


    private static List<File> getAllProtoFileFromPath() {
        if (!FileUtil.exist(PROTO_BASE_PATH)) {
            log.error("proto file path not exist.path:{}", PROTO_BASE_PATH);
            return null;
        }

        if (!FileUtil.isDirectory(PROTO_BASE_PATH)) {
            log.error("proto file path not is directory.path:{}", PROTO_BASE_PATH);
            return null;
        }

        return FileUtil.loopFiles(PROTO_BASE_PATH, pathname ->
                FileUtil.getSuffix(pathname).equals("proto") &&
                        !FileUtil.getName(pathname).equals("base.proto"));
    }


    public static void main(String[] args) throws IOException {
        JProtoPojoGenTool.genJPBPojoFileFromProto();
    }
}
