import 'dart:async';

import 'package:flutter/services.dart';

class RNPlugin {
  static const MethodChannel _channel =
  const MethodChannel('lmy/toRNPlugin');
  static Future<String> toRN() async => await _channel.invokeMethod('toRNPage');
}