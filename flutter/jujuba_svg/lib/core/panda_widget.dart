import 'package:flutter/material.dart';
import 'package:panda_svg/core/commander/panda_commander.dart';
import 'package:panda_svg/core/constants.dart';
import 'package:panda_svg/model/node_coordinate.dart';
import 'package:panda_svg/model/node_info.dart';
import 'package:webview_flutter/webview_flutter.dart';

class PandaWidget extends StatefulWidget {
  final String svgText;
  final PandaCommander commander;
  final Function(NodeInfo) onElementClick;

  const PandaWidget({
    super.key,
    required this.svgText,
    required this.commander,
    required this.onElementClick,
  });

  @override
  State<StatefulWidget> createState() => _PandaWebViewState();
}

class _PandaWebViewState extends State<PandaWidget> {
  late final WebViewController _controller;

  _PandaWebViewState();

  @override
  void initState() {
    super.initState();

    _setup();
  }

  Future<void> _setup() async {
    _controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..addJavaScriptChannel(
        pandaFlutterChannelName,
        onMessageReceived: (message) => {_processJSMessage(message.message)},
      );

    final svgText = widget.svgText;

    final completeHtml = baseHtml.replaceFirst(htmlSVGMarker, svgText);

    await _controller.loadHtmlString(completeHtml);

    widget.commander.stream.listen(
      (jsCommand) => _runJavascript(jsCommand),
    );
  }

  Future<void> _runJavascript(String jsCommand) async {
    final result = await _controller.runJavaScriptReturningResult(
      jsCommand,
    );
    debugPrint(result.toString());
  }

  _processJSMessage(String jsMessage) {
    try {
      final elements = jsMessage.split(',');
      final id = elements[0];
      final x = double.parse(elements[1]);
      final y = double.parse(elements[2]);

      final nodeCoordinate = NodeCoordinate(x: x, y: y);
      final nodeInfo = NodeInfo(id: id, coordinate: nodeCoordinate);
      widget.onElementClick(nodeInfo);
    } catch (e) {
      debugPrint(e.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return WebViewWidget(
      controller: _controller,
    );
  }
}
