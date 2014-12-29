apply plugin: 'java'
apply plugin: 'jetty'
apply plugin: 'war'
sourceCompatibility = 1.6
repositories {
    mavenCentral()
}

dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.11'
    compile group: 'com.jfinal', name: 'jfinal', version: '1.9'
    compile group: 'com.jfinal', name: 'jfinal-ext', version: '3.1.2'
    compile'org.freemarker:freemarker:2.3.19'
    compile'mysql:mysql-connector-java:5.1.4'
    compile'joda-time:joda-time:2.2'
    compile'log4j:log4j:1.2.16'
    compile'junit:junit:4.11'
    compile'com.google.guava:guava:18.0'
    compile'com.jfinal:jetty-server:8.1.8'
    compile'com.alibaba:druid:0.2.25'
    compile 'org.projectlombok:lombok:1.14.8'
//    compile'com.github.peichhorn:lombok-pg:0.11.3'
    compile'org.beanshell:bsh:2.0b5'
    compile'commons-io:commons-io:2.4'
    compile'org.apache.ant:ant:1.9.2'
    compile'org.apache.poi:poi-ooxml:3.10.1'

}