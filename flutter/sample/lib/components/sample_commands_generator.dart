import 'package:panda_svg/core/commander/command.dart';
import 'package:panda_svg/model/node_info.dart';
import 'package:sampleapp/widgets/bottom_sheet_ui_model.dart';

class SampleCommandsGenerator {
  static List<BottomSheetUiModel> generate(NodeInfo nodeInfo) {
    List<BottomSheetUiModel> uiModels = [
      SelectedNodeUiModel(nodeIdName: nodeInfo.id),
      CommandUiModel(
        commandName: 'Change element background color',
        command: UpdateBackgroundColor(id: nodeInfo.id, colorHex: '#000000'),
      ),
      CommandUiModel(
        commandName: 'Change root SVG background Color',
        command: UpdateRootBackgroundColor(colorInHex: '#000000'),
      ),
      CommandUiModel(
        commandName: 'Add rounded image',
        command: AddRoundedImage(
          elementId: nodeInfo.id,
          imageId: 'nasa',
          imageUrl: 'https://i.imgur.com/LQIsf.jpeg',
          widthInPx: 100,
          heightInPx: 100,
          coordinate: nodeInfo.coordinate,
        ),
      ),
      CommandUiModel(
        commandName: 'Remove element',
        command: RemoveNode(
          id: nodeInfo.id,
        ),
      )
    ];
    return uiModels;
  }
}
