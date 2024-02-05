import 'dart:collection';
import 'dart:io';

const FILENAME = "input.txt";

void main() {
  partOne();
  partTwo();
}

void partOne() {
  final file = File(FILENAME).readAsLinesSync();

  var frequency = 0;

  for (var line in file) {
    frequency += int.parse(line);
  }

  print(frequency);
}

void partTwo() {
  final file = File(FILENAME).readAsLinesSync();

  final frequencies = HashSet<int>();
  frequencies.add(0);

  var frequency = 0;
  var index = 0;

  while (true) {
    frequency += int.parse(file[index]);

    if (frequencies.contains(frequency)) {
      print(frequency);
      break;
    } else {
      frequencies.add(frequency);
    }

    index++;

    if (index >= file.length) {
      index = 0;
    }
  }
}
