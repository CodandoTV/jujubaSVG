import 'package:jujuba_svg/model/node_coordinate.dart';

/// Represents information about a specific SVG node within an SVG.
///
/// The [NodeInfo] class holds the identifier of a node along with its
/// position or coordinate information, as described by a [NodeCoordinate].
///
/// This class is used by the [JujubaCommander] to trigger commands in a specific
/// SVG region or node.
///
/// Example:
/// ```dart
/// final node = NodeInfo(
///   id: 'circle_1',
///   coordinate: NodeCoordinate(x: 10, y: 20),
/// );
///
/// print(node.id); // circle_1
/// print(node.coordinate.x); // 10
/// ```
///
/// See also:
/// - [NodeCoordinate], which defines the position or transformation of a node.
class NodeInfo {
  /// The unique identifier of the node.
  ///
  /// This value is usually extracted from the SVG element’s `id` attribute.
  final String id;

  // The coordinate or position information of the node.
  ///
  /// Describes where the node is located within the SVG coordinate space.
  final NodeCoordinate coordinate;

  /// Creates a new immutable [NodeInfo] with the given [id] and [coordinate].
  const NodeInfo({
    required this.id,
    required this.coordinate,
  });
}
