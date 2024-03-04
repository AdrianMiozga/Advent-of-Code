import 'dart:collection';
import 'dart:convert';
import 'dart:io';

const FILENAME = "input.txt";

void main() {
  partOne();
  partTwo();
}

void partOne() {
  final file = File(FILENAME).readAsLinesSync();
  final list = [0, 0];

  for (var line in file) {
    final HashMap<String, int> characters = HashMap();

    for (var i = 0; i < line.length; i++) {
      if (characters.containsKey(line[i])) {
        characters[line[i]] = (characters[line[i]])! + 1;
      } else {
        characters[line[i]] = 1;
      }
    }

    for (var element in characters.values.toSet()) {
      if (element < 2 || element > 3) {
        continue;
      }

      list[element - 2]++;
    }
  }

  var result = 1;

  for (var element in list) {
    result *= element;
  }

  print(result);
}

void partTwo() {
  final file = File(FILENAME).readAsLinesSync();

  for (var i = 0; i < file.length; i++) {
    next:
    for (var j = 0; j < file.length; j++) {
      if (i == j) {
        continue;
      }

      var difference = -1;

      for (var k = 0; k < file[0].length; k++) {
        if (file[i][k] != file[j][k]) {
          if (difference != -1) {
            continue next;
          }

          difference = k;
        }
      }

      print(file[i].replaceRange(difference, difference + 1, ''));
      return;
    }
  }
}
