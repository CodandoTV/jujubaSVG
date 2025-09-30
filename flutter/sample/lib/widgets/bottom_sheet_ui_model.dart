import 'package:jujuba_svg/core/commander/command.dart';

sealed class BottomSheetUiModel {}

class SelectedNodeUiModel extends BottomSheetUiModel {
  String nodeIdName;
  SelectedNodeUiModel({
    required this.nodeIdName,
  });
}

class CommandUiModel extends BottomSheetUiModel {
  String commandName;
  Command command;

  CommandUiModel({
    required this.commandName,
    required this.command,
  });
}
