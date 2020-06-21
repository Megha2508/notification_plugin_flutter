import 'dart:async';

import 'package:flutter/services.dart';

class NotificationEvent {
  NotificationEvent(this._packageName) {
    _timeStamp = DateTime.now();
  }

  final String _packageName;
  String _text;
  String _title;
  DateTime _timeStamp;

  String get packageName => _packageName;
  String get text => _text;
  String get title => _title;
  DateTime get timeStamp => _timeStamp;

  @override
  String toString() {
    return '[$packageName sent $text from $title @ $timeStamp]';
  }
}

NotificationEvent _notificationEvent(String event) {
  return NotificationEvent(event);
}

class Notifications {
  static const EventChannel _notificationEventChannel =
      EventChannel('notifications.eventChannel');

  Stream<NotificationEvent> _notificationStream;

  Stream<NotificationEvent> get stream {
    _notificationStream ??= _notificationEventChannel
        .receiveBroadcastStream()
        .map((event) => _notificationEvent(event));
    return _notificationStream;
  }
}
