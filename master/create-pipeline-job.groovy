import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

def jobDslScript = """
pipelineJob("DevOps037") {
          
    properties {
        disableConcurrentBuilds()
    }

    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        github('DmyMi/spring-petclinic','https')
                        name('origin')
                    }
                    branches('jenkins')
                    browser {
                        gitWeb('https://github.com/DmyMi/spring-petclinic')
                    }
                    
                    extensions {
                        cleanBeforeCheckout()
                        pruneBranches()
                    }
                }
            scriptPath('Jenkinsfile')
            }
        }
    }
    description('Example Job Seed')
    displayName('DevOps037')
}
"""
def workspace = new File('.')

def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)

def scriptLoader = new DslScriptLoader(jobManagement)
scriptLoader.runScript(jobDslScript)