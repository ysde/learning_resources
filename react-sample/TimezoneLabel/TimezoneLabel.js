import React  from 'react';

function getTimezoneOffset() {
  var offsetInMinutes = new Date().getTimezoneOffset();
  var totalHour = Math.abs(offsetInMinutes) / 60;
  var hour = Math.floor(Math.abs(offsetInMinutes) / 60);
  var minute = (totalHour - hour) * 60;
  var offsetString = `${('0' + hour).slice(-2)}:${(minute + '0')}`;

  return ((offsetInMinutes < 0) ? '+' : '-') + offsetString;
}

function TimezoneLabel(props) {
  return <label>Timezone : UTC ({getTimezoneOffset()}) {Intl.DateTimeFormat().resolvedOptions().timeZone}</label>
}


export const timezoneLabel = <TimezoneLabel />;
