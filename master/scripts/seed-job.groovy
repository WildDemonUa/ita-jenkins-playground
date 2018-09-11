import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

def jobDslScript = new File('/var/jenkins_home/create-pipeline-job.groovy')
def viewDslScript = new File('/var/jenkins_home/create-view-job.groovy')
def workspace = new File('.')

def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)

def scriptLoader = new DslScriptLoader(jobManagement)
scriptLoader.runScript(jobDslScript.text)
scriptLoader.runScript(viewDslScript.text)
