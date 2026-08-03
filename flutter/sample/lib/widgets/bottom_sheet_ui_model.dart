import 'package:jujuba_svg/model/node_coordinate.dart';

sealed class BottomSheetUiModel {}

class SelectedNodeUiModel extends BottomSheetUiModel {
  String nodeIdName;
  SelectedNodeUiModel({
    required this.nodeIdName,
  });
}

class CommandUiModel extends BottomSheetUiModel {
  String nodeId;
  String commandName;
  CommandType type;
  NodeCoordinate elementPosition;

  CommandUiModel({
    required this.nodeId,
    required this.commandName,
    required this.type,
    required this.elementPosition,
  });
}

enum CommandType {
  changeElementBackgroundColor,
  changeRootBackgroundColor,
  addRoundedImage,
  removeElement,
  customCommand,
}
