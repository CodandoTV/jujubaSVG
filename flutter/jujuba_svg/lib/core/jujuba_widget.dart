import 'package:flutter/material.dart';
import 'package:jujuba_svg/core/commander/jujuba_commander.dart';
import 'package:jujuba_svg/core/constants.dart';
import 'package:jujuba_svg/model/node_coordinate.dart';
import 'package:jujuba_svg/model/node_info.dart';
import 'package:jujuba_svg/util/asset_helper.dart';
import 'package:webview_flutter/webview_flutter.dart';

/// A widget that renders and interacts with SVG content inside a [WebView].
///
/// The [JujubaSVGWidget] provides two-way communication between Flutter and
/// the JavaScript layer responsible for manipulating the SVG.
/// It allows:
/// 1. **Sending commands** to modify the SVG dynamically using [JujubaCommander].
/// 2. **Receiving user interactions**, such as clicks on SVG elements,
///    which are reported via [onElementClick].
///
/// This widget is especially useful for applications that render dynamic,
/// interactive, or data-driven SVGs (e.g., editors, charts, or design tools).
///
/// ---
///
/// ### Example
/// ```dart
/// final commander = JujubaCommander();
///
/// JujubaSVGWidget(
///   svgText: '<svg><circle id="circle1" cx="10" cy="10" r="5" fill="red" /></svg>',
///   commander: commander,
///   onElementClick: (node) {
///     print('Clicked node: ${node.id} at (${node.coordinate.x}, ${node.coordinate.y})');
///   },
/// );
///
/// // Later, update the SVG node color dynamically
/// commander.execute(UpdateBackgroundColor(
///   id: 'circle1',
///   colorHex: '#00FF00',
/// ));
/// ```
///
/// ---
///
/// ### How it works
/// - Loads an HTML document containing the provided [svgText].
/// - Sets up a JavaScript channel ([jujubaFlutterChannelName]) to receive messages from the SVG.
/// - Forwards Dart-to-JavaScript commands via the [JujubaCommander].
/// - When an SVG element is clicked, the WebView sends a message back,
///   which is parsed into a [NodeInfo] and passed to [onElementClick].
///
/// ---
///
/// ### Parameters
/// - [svgText]: The raw SVG markup to render inside the WebView.
/// - [commander]: The [JujubaCommander] responsible for dispatching commands
///   as JavaScript calls.
/// - [onElementClick]: Callback invoked when an SVG element is clicked.
///   Receives a [NodeInfo] describing the clicked node.
///
/// ---
///
/// ### Requirements
/// - JavaScript must be enabled in the WebView.
class JujubaSVGWidget extends StatefulWidget {
  /// The raw SVG markup that will be rendered inside the WebView.
  final String svgText;

  /// The command dispatcher used to send JavaScript commands to manipulate the SVG.
  final JujubaCommander commander;

  /// Callback triggered when an SVG element is clicked,
  /// providing a [NodeInfo] object that contains the element’s ID and coordinates.
  final Function(NodeInfo) onElementClick;

  /// Creates a [JujubaSVGWidget].
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

  /// Initializes the [WebViewController], sets up JavaScript channels,
  /// loads the SVG content, and starts listening to [JujubaCommander] commands.
  Future<void> _setup() async {
    _controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..addJavaScriptChannel(
        jujubaFlutterChannelName,
        onMessageReceived: (message) => {_processJSMessage(message.message)},
      );

    final svgText = widget.svgText;
    final baseJS = await AssetHelper.loadAssetContent(
      'packages/jujuba_svg/js/base_js.js',
    );
    final completeHtml = baseHtml
        .replaceFirst(htmlBaseJSMarker, baseJS)
        .replaceFirst(htmlSVGMarker, svgText);

    await _controller.loadHtmlString(completeHtml);

    widget.commander.stream.listen(
      (jsCommand) => _runJavascript(jsCommand),
    );
  }

  /// Runs the provided JavaScript command inside the WebView.
  ///
  /// This allows dynamic manipulation of the SVG after it is rendered.
  Future<void> _runJavascript(String jsCommand) async {
    final result = await _controller.runJavaScriptReturningResult(
      jsCommand,
    );
    debugPrint(result.toString());
  }

  /// Handles messages sent from the JavaScript layer.
  ///
  /// The expected format is `"id,x,y"`, where:
  /// - `id` is the SVG element identifier.
  /// - `x` and `y` are the element’s coordinates.
  ///
  /// Converts the message into a [NodeInfo] instance and invokes [onElementClick].
  void _processJSMessage(String jsMessage) {
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
