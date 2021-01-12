import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

def jobDslScript = """
pipelineJob("DevOps037") {
    
    // checkoutRetryCount(3)
    
    concurrentBuild()
    
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
                        //Sets the strategy that Jenkins will use to choose what branches to build in what order.
                        choosingStrategy {
                            alternative()
                        }
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