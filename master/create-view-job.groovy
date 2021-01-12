import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

def viewDslScript = """
dashboardView('My View') {
    jobs {
        name("DevOps037")
    }
    columns {
        status()
        weather()
        buildButton()
    }
    topPortlets {
        jenkinsJobsList {
            displayName('cool jobs')
        }
    }
    leftPortlets {
        testStatisticsChart()
    }
    rightPortlets {
        testTrendChart()
    }
    bottomPortlets {
        iframe {
            effectiveUrl('https://github.com/DmyMi/spring-petclinic')
        }
        testStatisticsGrid()
        buildStatistics()
    }
}
"""
def workspace = new File('.')

def jobManagement = new JenkinsJobManagement(System.out, [:], workspace)

def scriptLoader = new DslScriptLoader(jobManagement)
scriptLoader.runScript(viewDslScript)