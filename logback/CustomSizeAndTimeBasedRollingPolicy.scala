package ch.qos.logback.core.rolling

import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP.Usage
import ch.qos.logback.core.util.FileSize
import java.io.File

class CustomSizeAndTimeBasedRollingPolicy[E] extends TimeBasedRollingPolicy[E] {

  @SuppressWarnings(Array("org.wartremover.warts.Null", "org.wartremover.warts.Var"))
  var maxFileSize: FileSize = null

  override def start(): Unit = {
    val sizeAndTimeBasedFNATP = new CustomSizeAndTimeBasedFNATP[E](Usage.EMBEDDED)
    if (maxFileSize == null) {
      addError("maxFileSize property is mandatory.")
    } else {
      addInfo("Archive files will be limited to [" + maxFileSize.getSize.toString + "] each.")
    }

    sizeAndTimeBasedFNATP.setMaxFileSize(maxFileSize)

    timeBasedFileNamingAndTriggeringPolicy = sizeAndTimeBasedFNATP

    if (!isUnboundedTotalSizeCap() && totalSizeCap.getSize() < maxFileSize.getSize()) {
      addError("totalSizeCap of [" + totalSizeCap.getSize.toString + "] is smaller than maxFileSize [" + maxFileSize.getSize.toString + "] which is non-sensical");
    }

    super.start()
  }

  def setMaxFileSize(aMaxFileSize: FileSize) {
    this.maxFileSize = aMaxFileSize
  }


  override def toString()= {
    "c.q.l.core.rolling.SizeAndTimeBasedRollingPolicy@"+this.hashCode().toString
  }

}


