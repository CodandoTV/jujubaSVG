# JujubaSVG — Example

A minimal example demonstrating how to use the `jujuba_svg` library.

For a full-featured sample app, see [`flutter/sample/`](https://github.com/CodandoTV/jujubaSVG/tree/main/flutter/sample).

## Setup

### 1. Add the dependency

```yaml
dependencies:
  jujuba_svg: ^2.0.0
```

### 2. Create a `JujubaCommander`

```dart
import 'package:jujuba_svg/core/commander/jujuba_commander.dart';

final commander = JujubaCommander();
```

### 3. Load SVG content

```dart
import 'package:jujuba_svg/util/asset_helper.dart';

final svgText = await AssetHelper.loadAssetContent('svg/my_map.svg');
```

### 4. Render the widget

```dart
import 'package:jujuba_svg/core/jujuba_widget.dart';

JujubaSVGWidget(
  commander: commander,
  svgText: svgText,
  onElementClick: (nodeInfo) {
    print('Clicked: ${nodeInfo.id}');
  },
)
```

## Executing commands

All commands are dispatched through `JujubaCommander.execute()`:

### Update element background color

```dart
import 'package:jujuba_svg/core/commander/command.dart';

commander.execute(UpdateBackgroundColor(
  id: 'element_1',
  colorHex: '#FF0000',
));
```

### Update root background color

```dart
commander.execute(UpdateRootBackgroundColor(
  colorInHex: '#0000FF',
));
```

### Update stroke color

```dart
commander.execute(UpdateStrokeColor(
  id: 'element_1',
  colorHex: '#00FF00',
));
```

### Update stroke width

```dart
commander.execute(UpdateStrokeWidth(
  id: 'element_1',
  widthInPx: 3,
));
```

### Remove an element

```dart
commander.execute(RemoveNode(
  id: 'element_1',
));
```

### Add a rounded image

```dart
import 'package:jujuba_svg/model/node_coordinate.dart';

commander.execute(AddRoundedImage(
  elementId: 'element_1',
  imageId: 'photo_1',
  imageUrl: 'https://example.com/image.png',
  widthInPx: 100,
  heightInPx: 100,
  elementPosition: NodeCoordinate(x: 50.0, y: 50.0),
));
```

### Custom JavaScript command

```dart
commander.execute(CustomCommand(
  jsCode: "updateBackgroundColor('element_1', '#000000');",
));
```

## Full example

```dart
import 'package:flutter/material.dart';
import 'package:jujuba_svg/core/commander/command.dart';
import 'package:jujuba_svg/core/commander/jujuba_commander.dart';
import 'package:jujuba_svg/core/jujuba_widget.dart';
import 'package:jujuba_svg/model/node_info.dart';
import 'package:jujuba_svg/util/asset_helper.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: SvgPage(),
      ),
    );
  }
}

class SvgPage extends StatefulWidget {
  @override
  State<SvgPage> createState() => _SvgPageState();
}

class _SvgPageState extends State<SvgPage> {
  final JujubaCommander commander = JujubaCommander();
  String? _svgText;

  @override
  void initState() {
    super.initState();
    _loadSvg();
  }

  Future<void> _loadSvg() async {
    final svg = await AssetHelper.loadAssetContent('svg/my_map.svg');
    setState(() => _svgText = svg);
  }

  @override
  Widget build(BuildContext context) {
    if (_svgText == null) {
      return const Center(child: CircularProgressIndicator());
    }

    return JujubaSVGWidget(
      commander: commander,
      svgText: _svgText!,
      onElementClick: (NodeInfo nodeInfo) {
        commander.execute(UpdateBackgroundColor(
          id: nodeInfo.id,
          colorHex: '#FF0000',
        ));
      },
    );
  }
}
```
