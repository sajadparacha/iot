FROM ubi8/ubi:8.5
LABEL description ="This container file is created y Sajjad"
MAINTAINER "Sajjad Paracha" <sajadparacha@gmail.com>
USER nexus
ARG NEXUS_VERSION=2.14.3-02 
VAR
ENV NEXUS_HOME = /opt/nexus

RUN

RUN ln -s ${NEXUS_HOME}/nexus-${NEXUS_VERSION}${NEXUS_HOME}/nexus2
RUN chown -R nexus:nexus ${NEXUS_HOME}/
ADD nexus-2.14.3-02-bundle.tar.gz ${NEXUS_HOME}/
COPY nexus-start.sh ${NEXUS_HOME}/
 
CMD [".sh","nexus-start.sh"]

WORKDIR ["/opt/nexus"]

VOLUME ["/opt/nexus/sonatype-work"]
