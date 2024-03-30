import 'dart:io';

const FILENAME = "input.txt";

void main() {
  partOne();
  partTwo();
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

void partTwo() {
  final file = File(FILENAME).readAsLinesSync();
  final List<List<int>> array =
      List.generate(1000, (_) => List.filled(1000, 0));

  final List<bool> results = List.generate(file.length, (int index) => true);

  for (var line in file) {
    final idRegex = RegExp(r'#(\d+)');
    final id = int.parse(idRegex.firstMatch(line)!.group(1)!);

    final padding = RegExp(r'((\d+),(\d+))');
    final leftPadding = int.parse(padding.firstMatch(line)!.group(2)!);
    final topPadding = int.parse(padding.firstMatch(line)!.group(3)!);

    final size = RegExp(r'((\d+)x(\d+))');
    final width = int.parse(size.firstMatch(line)!.group(2)!);
    final height = int.parse(size.firstMatch(line)!.group(3)!);

    for (var i = 0; i < width; i++) {
      for (var j = 0; j < height; j++) {
        var value = array[topPadding + j][leftPadding + i];

        if (value != 0) {
          results[value - 1] = false;
          results[id - 1] = false;
          array[topPadding + j][leftPadding + i] = id;
        } else {
          array[topPadding + j][leftPadding + i] = id;
        }
      }
    }
  }

  print(results.indexOf(true) + 1);
}
