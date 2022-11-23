#!/bin/bash

# Send the log output from this script to user-data.log, syslog, and the console
exec > >(tee /var/log/user-data.log|logger -t user-data -s 2>/dev/console) 2>&1

sudo amazon-linux-extras install java-openjdk11 -y
sudo yum install -y wget

sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
sudo yum upgrade -y
sudo yum install jenkins -y

PLUGIN_CLI_VERSION=2.12.9
PLUGIN_CLI_URL=https://github.com/jenkinsci/plugin-installation-manager-tool/releases/download/$${PLUGIN_CLI_VERSION}/jenkins-plugin-manager-$${PLUGIN_CLI_VERSION}.jar
sudo curl -fsSL $${PLUGIN_CLI_URL} -o /opt/jenkins-plugin-manager.jar

sudo cat <<EOF | sudo tee /plugins.txt
${plugins}
EOF

sudo runuser -u jenkins -- java -jar /opt/jenkins-plugin-manager.jar -f /plugins.txt -d /var/lib/jenkins/plugins/ -w /usr/share/java/jenkins.war

sudo systemctl enable jenkins
sudo systemctl start jenkins