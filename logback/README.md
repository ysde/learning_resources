# Custom SizeAndTimeBasedRollingPolicy

## Logback version (1.2.3)

If you have used TimeBasedRollingPolicy and manually call rollover() method, you must have the experience of getting `java.lang.NullPointer` exception. [https://jira.qos.ch/browse/LOGBACK-995]()

Here is an example of creating your own `SizeAndTimeBasedRollingPolicy`, and make it manually- rollover possible.

## Solution

The whole point is to let `elapsedPeriodsFileName` not null when `rollover` is called.

`elapsedPeriodsFileName = tbrp.fileNamePatternWithoutCompSuffix.convertMultipleArguments(new java.util.Date(getCurrentTime()), 0);`

## Example of manually rollover
```
 ch.qos.logback.classic.Logger logF = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.my.com.pany");
        RollingFileAppender<ILoggingEvent> appender = (RollingFileAppender<ILoggingEvent>) logF.getAppender("test");
        appender.rollover();
```

###Reference: 
[https://github.com/qos-ch/logback/pull/381/files#diff-fec655d597270867e6d536f40b28083e]()