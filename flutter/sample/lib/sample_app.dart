import 'package:flutter/material.dart';
import 'package:jujuba_svg/core/commander/command.dart';
import 'package:jujuba_svg/core/commander/jujuba_commander.dart';
import 'package:jujuba_svg/core/jujuba_widget.dart';
import 'package:jujuba_svg/model/node_info.dart';
import 'package:sampleapp/components/random_colors_generator.dart';
import 'package:sampleapp/components/bottom_sheet_elements_generator.dart';
import 'package:jujuba_svg/util/asset_helper.dart';
import 'package:sampleapp/widgets/bottom_sheet_ui_model.dart';
import 'package:sampleapp/widgets/commands_bottom_sheet_widget.dart';

class SampleApp extends StatefulWidget {
  final JujubaCommander commander = JujubaCommander();

  SampleApp({super.key});

  @override
  State<SampleApp> createState() => _SampleAppState();
}

class _SampleAppState extends State<SampleApp> {
  String? _svgText;
  List<BottomSheetUiModel> bottomSheetUiModels = [];

  @override
  void initState() {
    super.initState();

    _loadSvgSample();
  }

  Future<void> _loadSvgSample() async {
    final brazilSvgText = await AssetHelper.loadAssetContent('svg/brazil.svg');

    setState(() {
      _svgText = brazilSvgText;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Todo list',
      home: Scaffold(
        bottomSheet: CommandsBottomSheetWidget(
          uiModels: bottomSheetUiModels,
          onSelectCommand: (uiModel) => _onTriggerCommand(uiModel),
        ),
        body: Center(
          child: _svgText == null
              ? const Text(
                  'Nothing here',
                )
              : JujubaSVGWidget(
                  commander: widget.commander,
                  svgText: _svgText!,
                  onElementClick: (nodeInfo) => {_updateSelectedNode(nodeInfo)},
                ),
        ),
      ),
    );
  }

  _updateSelectedNode(NodeInfo nodeInfo) {
    setState(() {
      bottomSheetUiModels = BottomSheetElementsGenerator.generate(nodeInfo);
    });
  }

  _onTriggerCommand(CommandUiModel uiModel) {
    final randomColorInHex = RandomColorsGenerator.getRandomRainbowColor();

    switch (uiModel.type) {
      case CommandType.changeElementBackgroundColor:
        widget.commander.execute(
          UpdateBackgroundColor(
            id: uiModel.nodeId,
            colorHex: randomColorInHex,
          ),
        );
        break;
      case CommandType.changeRootBackgroundColor:
        widget.commander.execute(
          UpdateRootBackgroundColor(colorInHex: randomColorInHex),
        );
        break;
      case CommandType.addRoundedImage:
        widget.commander.execute(
          AddRoundedImage(
            elementId: uiModel.nodeId,
            imageId: 'nasa',
            imageUrl: 'https://i.imgur.com/LQIsf.jpeg',
            widthInPx: 100,
            heightInPx: 100,
            coordinate: uiModel.coordinate,
          ),
        );
        break;
      case CommandType.removeElement:
        widget.commander.execute(
          RemoveNode(
            id: uiModel.nodeId,
          ),
        );
        break;
      case CommandType.customCommand:
        widget.commander.execute(
          CustomCommand(
            jsCode: "updateBackgroundColor('${uiModel.nodeId}', '#000000');",
          ),
        );
        break;
    }
  }
}
