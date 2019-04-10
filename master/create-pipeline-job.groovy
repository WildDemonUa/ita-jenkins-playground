#!groovy

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
