import 'dart:convert';

import 'package:jujuba_svg/model/node_coordinate.dart';

/// Represents information about a specific SVG node within an SVG.
///
/// The [NodeInfo] class holds the identifier of a node along with its
/// element position and cursor position, as described by [NodeCoordinate].
///
/// This class is used by the [JujubaCommander] to trigger commands in a specific
/// SVG region or node.
///
/// Example:
/// ```dart
/// final node = NodeInfo(
///   id: 'circle_1',
///   elementPosition: NodeCoordinate(x: 10, y: 20),
/// );
///
/// print(node.id); // circle_1
/// print(node.elementPosition.x); // 10
/// ```
///
/// See also:
/// - [NodeCoordinate], which defines the position of a node.
class NodeInfo {
  /// The unique identifier of the node.
  ///
  /// This value is usually extracted from the SVG element’s `id` attribute.
  final String id;

  /// The element position information of the node.
  ///
  /// Describes where the node is located within the SVG coordinate space.
  final NodeCoordinate elementPosition;

  /// The cursor position at the moment of the click event, if available.
  ///
  /// This value is only populated via [NodeInfo.fromJson] when receiving
  /// a click event from the JavaScript bridge. It is `null` when
  /// [NodeInfo] is constructed manually.
  final NodeCoordinate? cursorPosition;

  /// Creates a new immutable [NodeInfo] with the given [id], [elementPosition],
  /// and optional [cursorPosition].
  const NodeInfo({
    required this.id,
    required this.elementPosition,
    this.cursorPosition,
  });

  /// Creates a [NodeInfo] from a JSON string received from the JavaScript bridge.
  ///
  /// The expected JSON format is:
  /// ```json
  /// {
  ///   "id": "nodeId",
  ///   "elementX": 100.0,
  ///   "elementY": 200.0,
  ///   "cursorX": 150.0,
  ///   "cursorY": 250.0
  /// }
  /// ```
  factory NodeInfo.fromJson(String json) {
    final map = jsonDecode(json) as Map<String, dynamic>;
    return NodeInfo(
      id: map['id'] as String,
      elementPosition: NodeCoordinate(
        x: (map['elementX'] as num).toDouble(),
        y: (map['elementY'] as num).toDouble(),
      ),
      cursorPosition: NodeCoordinate(
        x: (map['cursorX'] as num).toDouble(),
        y: (map['cursorY'] as num).toDouble(),
      ),
    );
  }
}
