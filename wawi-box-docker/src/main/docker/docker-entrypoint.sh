#!/bin/bash

export TERM=xterm-256color
#set -e

JAVA_OPTS="-cp /app/postgresql-42.2.2.jar:/app/wawi-box-crawler-1.0.0-SNAPSHOT.jar "

APP_PKG="wawi-box-crawler-pkg.jar"

APP="com.jkaref.wawi.crawler.Application 999999999999"

JAR_BIN=$(which unzip)
JAVA_BIN=$(which java)

ntpd -gq
service ntp start

sleep 5

chown -R postgres:postgres /etc/postgresql
mkdir -p /var/lib/postgresql && chown -R postgres:postgres /var/lib/postgresql
mkdir -p /var/log/postgresql && chown -R postgres:postgres /var/log/postgresql

service postgresql start

sudo -u postgres psql -c "ALTER user postgres WITH PASSWORD 'postgres'"
sudo -u postgres psql -c "CREATE DATABASE wawibox"
sudo -u postgres /bin/sh -c 'cat /app/wawi-box-*.sql | psql wawibox'
sleep 5

#/usr/local/bin/wait-for-postgres.sh

sleep 5

cd /app

echo "Starting wawi-box-crawler"
${JAR_BIN} ${APP_PKG}

sleep 2

${JAVA_BIN} ${JAVA_OPTS} ${APP}

exec "$@"

