import 'package:jujuba_svg/model/node_coordinate.dart';
import 'package:jujuba_svg/model/node_info.dart';
import 'package:test/test.dart';

void main() {
  test('NodeInfo.fromJson parses elementPosition and cursorPosition', () {
    const json = '{"id":"node1","elementX":100.0,"elementY":200.0,"cursorX":150.0,"cursorY":250.0}';

    final nodeInfo = NodeInfo.fromJson(json);

    expect(nodeInfo.id, 'node1');
    expect(nodeInfo.elementPosition.x, 100.0);
    expect(nodeInfo.elementPosition.y, 200.0);
    expect(nodeInfo.cursorPosition, isNotNull);
    expect(nodeInfo.cursorPosition!.x, 150.0);
    expect(nodeInfo.cursorPosition!.y, 250.0);
  });

  test('NodeInfo constructor works without cursorPosition', () {
    const nodeInfo = NodeInfo(
      id: 'node1',
      elementPosition: NodeCoordinate(x: 10, y: 20),
    );

    expect(nodeInfo.id, 'node1');
    expect(nodeInfo.elementPosition.x, 10);
    expect(nodeInfo.elementPosition.y, 20);
    expect(nodeInfo.cursorPosition, isNull);
  });

  test('NodeInfo constructor works with cursorPosition', () {
    const nodeInfo = NodeInfo(
      id: 'node1',
      elementPosition: NodeCoordinate(x: 10, y: 20),
      cursorPosition: NodeCoordinate(x: 30, y: 40),
    );

    expect(nodeInfo.cursorPosition, isNotNull);
    expect(nodeInfo.cursorPosition!.x, 30);
    expect(nodeInfo.cursorPosition!.y, 40);
  });
}
