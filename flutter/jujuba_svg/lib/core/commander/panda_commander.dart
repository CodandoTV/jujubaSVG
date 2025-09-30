import 'dart:async';

import 'package:panda_svg/core/commander/command.dart';

class PandaCommander {
  final _command = StreamController<String>.broadcast();

  Stream<String> get stream => _command.stream;

  void execute(Command command) {
    _command.add(_convertToJSCode(command));
  }

  // ignore_for_file: lines_longer_than_80_chars
  String _convertToJSCode(Command command) {
    switch (command) {
      case UpdateBackgroundColor():
        return 'updateBackgroundColor(\'${command.id}\',\'${command.colorHex}\');';
      case UpdateStrokeColor():
        return 'updateStrokeColor(\'${command.id}\',\'${command.colorHex}\');';
      case UpdateStrokeWidth():
        return 'updateStrokeWidth(\'${command.id}\',${command.widthInPx});';
      case RemoveNode():
        return 'removeNode(\'${command.id}\');';
      case UpdateRootBackgroundColor():
        return 'updateRootBackgroundColor(\'${command.colorInHex}\');';
      case AddRoundedImage():
        return 'addRoundedImage('
            '\'${command.elementId}\','
            '\'${command.imageId}\','
            '\'${command.imageUrl}\','
            '\'${command.widthInPx}\','
            '\'${command.heightInPx}\','
            '\'${command.coordinate.x}\','
            '\'${command.coordinate.y}\''
            ');';
    }
  }
}
