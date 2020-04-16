def ENVIRONMENT_PARAM
def SCENARIO_PARAM
pipeline {
    agent any    
    parameters {
      string(name: 'scenarioParam',
        defaultValue: '@login',
        description: 'Ingresa escenario de prueba')
    }
    stages {
        stage(pruebas) {
        steps {
          script {
            SCENARIO_PARAM = "${params.scenarioParam}"
//             sh "pwd"
            bat "mvn clean verify -Dcucumber.options='--tags ${SCENARIO_PARAM}'"
          }
        }
      }
    }
}