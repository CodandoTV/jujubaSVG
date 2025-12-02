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
        coordinate: nodeInfo.coordinate,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: 'Change root SVG background Color',
        type: CommandType.changeRootBackgroundColor,
        coordinate: nodeInfo.coordinate,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: 'Add rounded image',
        type: CommandType.addRoundedImage,
        coordinate: nodeInfo.coordinate,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: 'Remove element',
        type: CommandType.removeElement,
        coordinate: nodeInfo.coordinate,
      ),
      CommandUiModel(
        nodeId: nodeInfo.id,
        commandName: '[Custom Command] Apply black',
        type: CommandType.customCommand,
        coordinate: nodeInfo.coordinate,
      ),
    ];
    return uiModels;
  }
}
