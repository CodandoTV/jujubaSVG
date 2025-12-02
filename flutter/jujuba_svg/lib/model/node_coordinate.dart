/// Represents a two-dimensional coordinate in the SVG.
///
/// The [NodeCoordinate] class defines the horizontal ([x]) and vertical ([y])
/// position of a node. It is typically used to describe where an element is
/// located within an SVG coordinate system or any other 2D layout.
///
/// Example:
/// ```dart
/// final coordinate = NodeCoordinate(x: 12.5, y: 42.0);
///
/// print(coordinate.x); // 12.5
/// print(coordinate.y); // 42.0
/// ```
class NodeCoordinate {
  /// The horizontal position of the node.
  final double x;

  /// The vertical position of the node.
  final double y;

  /// Creates a new immutable [NodeCoordinate] with the given [x] and [y] values.
  const NodeCoordinate({
    required this.x,
    required this.y,
  });
}
