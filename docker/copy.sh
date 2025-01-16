#!/bin/sh

# 复制项目的文件到对应docker路径，便于一键生成镜像。
usage() {
	echo "Usage: sh copy.sh"
	exit 1
}


# copy sql
echo "begin copy sql "
cp ../sql/ry_20240629.sql ./mysql/db
cp ../sql/ry_config_20240902.sql ./mysql/db

# copy html
echo "begin copy html "
cp -r ../detail-ui/dist/** ./nginx/html/dist


# copy jar
echo "begin copy detail-gateway "
cp ../detail-gateway/target/detail-gateway.jar ./ruoyi/gateway/jar

echo "begin copy detail-auth "
cp ../detail-auth/target/detail-auth.jar ./ruoyi/auth/jar

echo "begin copy detail-visual "
cp ../detail-visual/detail-monitor/target/detail-visual-monitor.jar  ./ruoyi/visual/monitor/jar

echo "begin copy detail-modules-system "
cp ../detail-modules/detail-system/target/detail-modules-system.jar ./ruoyi/modules/system/jar

echo "begin copy detail-modules-file "
cp ../detail-modules/detail-file/target/detail-modules-file.jar ./ruoyi/modules/file/jar

echo "begin copy detail-modules-job "
cp ../detail-modules/detail-job/target/detail-modules-job.jar ./ruoyi/modules/job/jar

echo "begin copy detail-modules-gen "
cp ../detail-modules/detail-gen/target/detail-modules-gen.jar ./ruoyi/modules/gen/jar

