FROM ubuntu:latest

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
    git

RUN apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java
RUN apt-get update 
RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections &&\
    apt-get install -y oracle-java8-installer

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

# Default command
CMD ["/usr/sbin/sshd", "-D"]
