import 'package:flutter/material.dart';
import 'package:jujuba_svg/core/commander/jujuba_commander.dart';
import 'package:jujuba_svg/core/jujuba_widget.dart';
import 'package:jujuba_svg/model/node_info.dart';
import 'package:sampleapp/components/sample_commands_generator.dart';
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
          onSelectCommand: (uiModel) =>
              {widget.commander.execute(uiModel.command)},
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
      bottomSheetUiModels = SampleCommandsGenerator.generate(nodeInfo);
    });
  }
}
