package com.bitdragon.rpc.zzzgen;

import com.iohao.game.common.kit.ArrayKit;
import com.iohao.game.widget.light.protobuf.ProtoGenerateFile;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class LightProtoGen {
    private static final String SAVE_PROTO_PATH = "D:\\jiuyulingjing\\gitea\\proto-project\\rpc\\proto";
    public static void main(String[] args) {
        /*
         * .proto 文件生成
         * 相关文档 https://www.yuque.com/iohao/game/vpe2t6
         *
         * ######################################################
         * 会将项目的 pb 生成到当前项目的 target/proto/common.proto 文件中
         * ######################################################
         */

        // 需要扫描的包名
        String currentDir = System.getProperty("user.dir");
        log.info("currentDIR:{}",currentDir);

        // 生成 .proto 文件存放的目录
        String generateFolder = ArrayKit.join(new String[]{SAVE_PROTO_PATH}, File.separator);

        ProtoGenerateFile protoGenerateFile = ProtoGenerateFile.builder()
                .protoSourcePath(currentDir)                                 // 源码目录
                .protoPackagePath("com.bitdragon.rpc")            // 需要扫描的包名
                .generateFolder(generateFolder)                              // 生成 .proto 文件存放的目录
                .build();

        // 生成 .proto 文件
        protoGenerateFile.generate();

        log.info("finish!!!!");
    }
}
