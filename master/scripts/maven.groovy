import hudson.tasks.Maven.MavenInstallation;
import hudson.tools.InstallSourceProperty;
import hudson.tools.ToolProperty;
import hudson.tools.ToolPropertyDescriptor;
import hudson.util.DescribableList;

def mavenDesc = jenkins.model.Jenkins.instance.getExtensionList(hudson.tasks.Maven.DescriptorImpl.class)[0]

def isp = new InstallSourceProperty()
def autoInstaller = new hudson.tasks.Maven.MavenInstaller("3.5.3")
isp.installers.add(autoInstaller)

def proplist = new DescribableList<ToolProperty<?>, ToolPropertyDescriptor>()
proplist.add(isp)

def installation = new MavenInstallation("Maven 3.5.3", "", proplist)

mavenDesc.setInstallations(installation)
mavenDesc.save()