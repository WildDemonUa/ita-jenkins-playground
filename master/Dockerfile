FROM jenkins/jenkins:2.230

RUN echo 2.0 > /usr/share/jenkins/ref/jenkins.install.UpgradeWizard.state
COPY scripts/*.groovy /usr/share/jenkins/ref/init.groovy.d/
COPY create-pipeline-job.groovy /usr/share/jenkins/ref/create-pipeline-job.groovy
COPY create-view-job.groovy /usr/share/jenkins/ref/create-view-job.groovy
COPY plugins.txt /plugins.txt
RUN /usr/local/bin/install-plugins.sh < /plugins.txt

# Set Defaults
ENV JAVA_OPTS="-Xmx4096m -Djenkins.install.runSetupWizard=false"
