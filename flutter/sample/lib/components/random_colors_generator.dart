import 'dart:math';

class RandomColorsGenerator {
  static final rainbowColors = [
    '#FF0000', // Red
    '#FF7F00', // Orange
    '#FFFF00', // Yellow
    '#00FF00', // Green
    '#0000FF', // Blue
    '#4B0082', // Indigo
    '#8B00FF', // Violet
  ];

  static String getRandomRainbowColor() {
    final random = Random();
    final randomColorIndex = random.nextInt(rainbowColors.length);
    return rainbowColors[randomColorIndex];
  }
}
