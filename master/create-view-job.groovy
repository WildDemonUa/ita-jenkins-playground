#!groovy
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