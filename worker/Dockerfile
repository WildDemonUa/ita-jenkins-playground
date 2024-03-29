FROM ubuntu:22.04

# Preparations for sshd
RUN apt-get update -qq && apt-get install -qqy locales &&\
    locale-gen en_US.UTF-8 &&\
    apt-get -q upgrade -y --no-install-recommends &&\
    apt-get -q install -y --no-install-recommends openssh-server &&\
    apt-get -q autoremove &&\
    apt-get -q clean -y && rm -rf /var/lib/apt/lists/* && rm -f /var/cache/apt/*.bin &&\
    sed -i 's|session    required     pam_loginuid.so|session    optional     pam_loginuid.so|g' /etc/pam.d/sshd &&\
    mkdir -p /var/run/sshd

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

RUN apt-get update -qq && apt-get install -qqy \
    apt-transport-https \
    ca-certificates \
    curl \
    lxc \
    iptables \
    git \
    sudo

RUN apt-get update && apt-get install -y software-properties-common openjdk-11-jdk-headless

# Install Docker from Docker Inc. repositories.
RUN curl -sSL https://get.docker.com/ | sh

# Set user jenkins to the image
RUN useradd -m -d /home/jenkins -s /bin/sh jenkins &&\
    echo "jenkins:jenkins" | chpasswd
#-ou 0 -g 0
RUN adduser jenkins sudo
RUN usermod -aG docker jenkins
RUN echo 'jenkins   ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers

EXPOSE 22

USER jenkins

# Default command
CMD ["sudo", "/usr/sbin/sshd", "-D"]
