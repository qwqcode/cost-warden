#!/bin/sh

# 转发 db 容器的 3306 端口到容器内 localhost
socat TCP4-LISTEN:3306,reuseaddr,fork TCP:db:3306 &

exec ${CATALINA_HOME}/bin/catalina.sh jpda run
