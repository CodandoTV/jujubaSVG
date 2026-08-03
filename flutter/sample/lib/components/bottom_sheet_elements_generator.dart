import 'package:jujuba_svg/model/node_info.dart';
import 'package:sampleapp/widgets/bottom_sheet_ui_model.dart';

class BottomSheetElementsGenerator {
  static List<BottomSheetUiModel> generate(NodeInfo nodeInfo) {
    List<BottomSheetUiModel> uiModels = [
      SelectedNodeUiModel(nodeIdName: nodeInfo.id),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: 'Change element background color',
        type: CommandType.changeElementBackgroundColor,
        elementPosition: nodeInfo.elementPosition,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: 'Change root SVG background Color',
        type: CommandType.changeRootBackgroundColor,
        elementPosition: nodeInfo.elementPosition,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: 'Add rounded image',
        type: CommandType.addRoundedImage,
        elementPosition: nodeInfo.elementPosition,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: 'Remove element',
        type: CommandType.removeElement,
        elementPosition: nodeInfo.elementPosition,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: '[Custom Command] Apply black',
        type: CommandType.customCommand,
        elementPosition: nodeInfo.elementPosition,
      ),
    ];
    return uiModels;
  }
}
