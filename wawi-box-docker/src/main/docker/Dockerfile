# #
# # (c) 2018-present [j]karef GmbH.
# #


#
# docker build -t jkaref-java . && docker run -d jkaref-java &&  docker exec -it $(docker ps -q) bash
#

FROM java:8-jdk

LABEL vendor=\[j\]karef\ GmbH \
      com.jkaref.base.hostname="wawi-box-crawlerr" \
      com.jkaref.base.is-beta="true"\
      com.jkaref.base.is-production="false" \
      com.jkaref.base.version="0.0.2" \
      com.jkaref.base.release-date="2018-05-03"

MAINTAINER Matthias Muenzner <matthias.muenzner@jkaref.com>

ENV TERM xterm-256color

ENV SET_CONTAINER_TIMEZONE false
ENV CONTAINER_TIMEZONE "Europe/Berlin"

RUN apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ xenial-pgdg main" > /etc/apt/sources.list.d/pgdg.list

RUN     apt-get -yq update                          \
    &&  apt-get -yq install libpostgresql-jdbc-java \
                            software-properties-common \
                            ntp                     \
                            sudo                    \
                            libpostgresql-jdbc-java \
                            postgresql-client       \
                            debconf-utils           \
                            postgresql-9.6          \
                            postgresql-client-9.6   \
                            postgresql-contrib-9.6

RUN     add-apt-repository "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main"                       \
    &&  echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | sudo debconf-set-selections \
    &&  apt-get update -yq                                                                                          \
    &&  apt-get install -yq oracle-java8-installer

RUN     chown -R postgres:postgres /etc/postgresql
RUN     mkdir -p /var/lib/postgresql \
    &&  chown -R postgres:postgres /var/lib/postgresql
RUN     mkdir -p /var/log/postgresql \
    &&  chown -R postgres:postgres /var/log/postgresql

COPY --chown=postgres:postgres conf/etc/postgresql/9.6/main/conf.d /etc/postgresql/9.6/main/conf.d
COPY --chown=postgres:postgres conf/etc/postgresql/9.6/main/pg_hba.conf /etc/postgresql/9.6/main/pg_hba.conf

VOLUME [                    \
    "/var/lib/postgresql",  \
    "/var/log/postgresql"   \
]
COPY ./app/ /app/

COPY bin/wait-for-postgres.sh /usr/local/bin/.

RUN echo "Activating wait-for-postgres.sh"
RUN chmod +x /usr/local/bin/wait-for-postgres.sh

RUN echo "Adding custom entrypoint"
COPY ./docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

EXPOSE 5432

ENTRYPOINT ["/docker-entrypoint.sh"]

CMD tail -F /var/log/bootstrap.log
