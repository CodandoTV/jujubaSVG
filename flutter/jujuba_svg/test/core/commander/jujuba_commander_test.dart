import 'package:jujuba_svg/core/commander/jujuba_commander.dart';
import 'package:jujuba_svg/core/commander/command.dart';
import 'package:jujuba_svg/model/node_coordinate.dart';
import 'package:test/test.dart';

void main() {
  late JujubaCommander commander;

  setUp(() {
    commander = JujubaCommander();
  });

  test('should emit correct JS for UpdateBackgroundColor', () async {
    final command = UpdateBackgroundColor(
      id: 'node1',
      colorHex: '#FFFFFF',
    );

    expectLater(
      commander.stream,
      emits("updateBackgroundColor('node1','#FFFFFF');"),
    );

    commander.execute(command);
  });

  test('should emit correct JS for UpdateStrokeColor', () async {
    final command = UpdateStrokeColor(
      id: 'node2',
      colorHex: '#000000',
    );

    expectLater(
      commander.stream,
      emits("updateStrokeColor('node2','#000000');"),
    );

    commander.execute(command);
  });

  test('should emit correct JS for UpdateStrokeWidth', () async {
    final command = UpdateStrokeWidth(
      id: 'node3',
      widthInPx: 5,
    );

    expectLater(
      commander.stream,
      emits("updateStrokeWidth('node3',5);"),
    );

    commander.execute(command);
  });

  test('should emit correct JS for RemoveNode', () async {
    final command = RemoveNode(id: 'node4');

    expectLater(
      commander.stream,
      emits("removeNode('node4');"),
    );

    commander.execute(command);
  });

  test('should emit correct JS for UpdateRootBackgroundColor', () async {
    final command = UpdateRootBackgroundColor(colorInHex: '#ABCDEF');

    expectLater(
      commander.stream,
      emits("updateRootBackgroundColor('#ABCDEF');"),
    );

    commander.execute(command);
  });

  test('should emit correct JS for AddRoundedImage', () async {
    final command = AddRoundedImage(
      elementId: 'container',
      imageId: 'img1',
      imageUrl: 'http://example.com/image.png',
      widthInPx: 100,
      heightInPx: 200,
      coordinate: NodeCoordinate(x: 50, y: 75),
    );

    expectLater(
      commander.stream,
      emits("addRoundedImage('container','img1','http://example.com/image.png',"
          "'100','200','50.0','75.0');"),
    );

    commander.execute(command);
  });
}
