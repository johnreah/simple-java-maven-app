FROM maven:3.6.3-openjdk-15

RUN useradd -m -u 1000 -s /bin/bash jenkins

RUN yum install -y openssh-clients
