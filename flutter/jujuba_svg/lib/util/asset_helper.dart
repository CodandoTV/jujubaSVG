import 'package:flutter/services.dart' show rootBundle;

class AssetHelper {
  static Future<String> loadAssetContent(String assetPath) async {
    return await rootBundle.loadString(assetPath);
  }
}
