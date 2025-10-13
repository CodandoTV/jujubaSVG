import 'package:jujuba_svg/model/node_coordinate.dart';

/// Base class to represent any command compatible to the [JujubaCommander]
sealed class Command {}

/// Update the background color of a node.
/// [id] of the node.
/// [colorHex] in hexadecimal format for the background.
class UpdateBackgroundColor extends Command {
  /// Target node id
  final String id;

  /// New color in hexadecimal format
  final String colorHex;

  /// Create a new UpdateBackgroundColor command object
  UpdateBackgroundColor({
    required this.id,
    required this.colorHex,
  });
}

/// Update the stroke color of a node.
/// [id] of the node.
/// [colorHex] in hexadecimal format for the stroke.
class UpdateStrokeColor extends Command {
  /// Target node id
  final String id;

  /// Color in hexadecimal format
  final String colorHex;

  /// Create a new UpdateStrokeColor command object
  UpdateStrokeColor({
    required this.id,
    required this.colorHex,
  });
}

/// Update the stroke width of a node.
/// [id] of the node.
/// [widthInPx] in pixels.
class UpdateStrokeWidth extends Command {
  /// Target node id
  final String id;

  /// Width in pixels
  final int widthInPx;

  /// Create a new UpdateStrokeWidth command object
  UpdateStrokeWidth({
    required this.id,
    required this.widthInPx,
  });
}

/// Remove a node.
/// [id] of the node.
class RemoveNode extends Command {
  /// Target node id
  final String id;

  /// Create a new RemoveNode command object
  RemoveNode({
    required this.id,
  });
}

/// Update the root background color.
/// [colorInHex] in hexadecimal format
class UpdateRootBackgroundColor extends Command {
  /// Color in hexadecimal format
  final String colorInHex;

  /// Create a new UpdateRootBackgroundColor command object
  UpdateRootBackgroundColor({
    required this.colorInHex,
  });
}

/// Add a rounded image into the same parent of the elementId.
/// [elementId] The id of the element, the rounded image will
/// be added at the same level of this elementId, so they will have the
/// same parent node.
/// [imageId] The id of the new element to be added
/// [imageUrl] The url where the image is available
/// [widthInPx] The width of the image in pixels
/// [heightInPx] The height of the image in pixels
/// [coordinate] The coordinates where the image will be added
class AddRoundedImage implements Command {
  /// Target node id
  final String elementId;

  /// Id that will represents this new image added into the SVG map
  final String imageId;

  /// Address of the new image (request will be made based on it)
  final String imageUrl;

  /// Width in pixels
  final int widthInPx;

  /// Height in pixels
  final int heightInPx;

  /// Specific coordinate where the image will be added
  final NodeCoordinate coordinate;

  /// Create a new AddRoundedImage command object.
  const AddRoundedImage({
    required this.elementId,
    required this.imageId,
    required this.imageUrl,
    required this.widthInPx,
    required this.heightInPx,
    required this.coordinate,
  });
}
