import 'package:flutter/material.dart';
import 'package:jujuba_svg/core/commander/jujuba_commander.dart';
import 'package:jujuba_svg/core/constants.dart';
import 'package:jujuba_svg/model/node_coordinate.dart';
import 'package:jujuba_svg/model/node_info.dart';
import 'package:webview_flutter/webview_flutter.dart';

class JujubaSVGWidget extends StatefulWidget {
  final String svgText;
  final JujubaCommander commander;
  final Function(NodeInfo) onElementClick;

  const JujubaSVGWidget({
    super.key,
    required this.svgText,
    required this.commander,
    required this.onElementClick,
  });

  @override
  State<StatefulWidget> createState() => _JujubaWebViewState();
}

class _JujubaWebViewState extends State<JujubaSVGWidget> {
  late final WebViewController _controller;

  _JujubaWebViewState();

  @override
  void initState() {
    super.initState();

    _setup();
  }

  Future<void> _setup() async {
    _controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..addJavaScriptChannel(
        jujubaFlutterChannelName,
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
