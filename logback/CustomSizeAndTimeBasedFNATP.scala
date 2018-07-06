package ch.qos.logback.core.rolling

import java.io.File

import ch.qos.logback.core.CoreConstants
import ch.qos.logback.core.CoreConstants._
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP.Usage
import ch.qos.logback.core.rolling.helper.FileFilterUtil

class CustomSizeAndTimeBasedFNATP[E](usage: Usage) extends SizeAndTimeBasedFNATP[E](usage) {

  override def start(): Unit = {
    // we depend on certain fields having been initialized in super class
    super.start()

    if (usage == Usage.DIRECT) {
      addWarn(CoreConstants.SIZE_AND_TIME_BASED_FNATP_IS_DEPRECATED)
      addWarn("For more information see " + MANUAL_URL_PREFIX + "appenders.html#SizeAndTimeBasedRollingPolicy");
    }

    if (super.isErrorFree()) {

      if (maxFileSize == null) {
        addError("maxFileSize property is mandatory.")
        withErrors()
      }

      if (!validateDateAndIntegerTokens()) {
        withErrors()
      }

      archiveRemover = createArchiveRemover()
      archiveRemover.setContext(context)

      // we need to get the correct value of currentPeriodsCounter.
      // usually the value is 0, unless the appender or the application
      // is stopped and restarted within the same period
      val regex = tbrp.fileNamePattern.toRegexForFixedDate(dateInCurrentPeriod)
      val stemRegex = FileFilterUtil.afterLastSlash(regex)

      computeCurrentPeriodsHighestCounterValue(stemRegex)

      if (isErrorFree()) {
        started = true
      }

      //add this line to make manually rollover work
      elapsedPeriodsFileName = tbrp.fileNamePatternWithoutCompSuffix.convertMultipleArguments(new java.util.Date(getCurrentTime()), Integer.valueOf(0))
    }
  }

  private def validateDateAndIntegerTokens()= {
    var inError = false
    if (tbrp.fileNamePattern.getIntegerTokenConverter() == null) {
      addError(SizeAndTimeBasedFNATP.MISSING_INT_TOKEN + tbrp.fileNamePatternStr + "]")
      addError(CoreConstants.SEE_MISSING_INTEGER_TOKEN)
      inError = true
    }
    if (tbrp.fileNamePattern.getPrimaryDateTokenConverter() == null) {
      addError(SizeAndTimeBasedFNATP.MISSING_DATE_TOKEN + tbrp.fileNamePatternStr + "]")
      inError = true
    }

    !inError
  }

}

