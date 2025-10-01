import 'package:jujuba_svg/model/node_coordinate.dart';

sealed class Command {}

/// Update the background color of a node.
/// - [id] of the node.
/// - [colorHex] in hexadecimal format for the background.
class UpdateBackgroundColor extends Command {
  final String id;
  final String colorHex;

  UpdateBackgroundColor({
    required this.id,
    required this.colorHex,
  });
}

/// Update the stroke color of a node.
/// - [id] of the node.
/// - [colorHex] in hexadecimal format for the stroke.
class UpdateStrokeColor extends Command {
  final String id;
  final String colorHex;

  UpdateStrokeColor({
    required this.id,
    required this.colorHex,
  });
}

/// Update the stroke width of a node.
/// - [id] of the node.
/// - [widthInPx] in pixels.
class UpdateStrokeWidth extends Command {
  final String id;
  final int widthInPx;

  UpdateStrokeWidth({
    required this.id,
    required this.widthInPx,
  });
}

/// Remove a node.
/// - [id] of the node.
class RemoveNode extends Command {
  final String id;

  RemoveNode({
    required this.id,
  });
}

/// Update the root background color.
/// - [colorInHex]
class UpdateRootBackgroundColor extends Command {
  final String colorInHex;

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
  final String elementId;
  final String imageId;
  final String imageUrl;
  final int widthInPx;
  final int heightInPx;
  final NodeCoordinate coordinate;

  const AddRoundedImage({
    required this.elementId,
    required this.imageId,
    required this.imageUrl,
    required this.widthInPx,
    required this.heightInPx,
    required this.coordinate,
  });
}
