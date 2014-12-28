log4j.rootCategory=ERROR, stdout, R
log4j.category.com.jfinal=DEBUG,stdout,R
log4j.additivity.com.jfinal=false

log4j.category.${project.packageName}=DEBUG,stdout,R
log4j.additivity.${project.packageName}=false
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.encoding=UTF-8
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=${_conversionPattern}

log4j.appender.R=org.apache.log4j.RollingFileAppender
log4j.appender.R.encoding=UTF-8
log4j.appender.R.File=./${project.name}.log  
log4j.appender.R.MaxFileSize=100Mb
log4j.appender.R.MaxBackupIndex=1
log4j.appender.R.layout=org.apache.log4j.PatternLayout
log4j.appender.R.layout.ConversionPattern=${_conversionPattern}
