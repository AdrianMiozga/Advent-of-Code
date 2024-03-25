import 'dart:io';

const FILENAME = "input.txt";

void main() {
  partOne();
}

void partOne() {
  final file = File(FILENAME).readAsLinesSync();
  final List<List<int>> array =
      List.generate(1000, (_) => List.filled(1000, 0));
  var counter = 0;

  for (var line in file) {
    final padding = RegExp(r'((\d+),(\d+))');
    final leftPadding = int.parse(padding.firstMatch(line)!.group(2)!);
    final topPadding = int.parse(padding.firstMatch(line)!.group(3)!);

    final size = RegExp(r'((\d+)x(\d+))');
    final width = int.parse(size.firstMatch(line)!.group(2)!);
    final height = int.parse(size.firstMatch(line)!.group(3)!);

    for (var i = 0; i < width; i++) {
      for (var j = 0; j < height; j++) {
        array[topPadding + j][leftPadding + i]++;

        if (array[topPadding + j][leftPadding + i] == 2) {
          counter++;
        }
      }
    }
  }

  print(counter);
}
