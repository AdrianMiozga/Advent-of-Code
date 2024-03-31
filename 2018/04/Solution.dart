import 'dart:io';
import 'dart:math';

const FILENAME = "input.txt";

void main() {
  partOne();
}

class Action {
  final DateTime dateTime;
  final String instruction;

  Action(this.dateTime, this.instruction);
}

class Guard {
  final int id;
  var fallAsleepTime = 0;
  final List<int> minutes = List.generate(60, (index) => 0);

  Guard(this.id);
}

void partOne() {
  final file = File(FILENAME).readAsLinesSync();
  final List<Action> actions = List.empty(growable: true);

  for (var line in file) {
    final regex = RegExp(r'\[(\d+)-(\d+)-(\d+) (\d+):(\d+)\] (.+)');
    final match = regex.firstMatch(line)!;

    final year = int.parse(match.group(1)!);
    final month = int.parse(match.group(2)!);
    final day = int.parse(match.group(3)!);

    final hour = int.parse(match.group(4)!);
    final minute = int.parse(match.group(5)!);

    final dateTime = DateTime(year, month, day, hour, minute);

    final action = match.group(6)!;

    actions.add(Action(dateTime, action));
  }

  actions.sort((Action a, Action b) => a.dateTime.compareTo(b.dateTime));

  final List<Guard> guards = List.empty(growable: true);

  var currentId = 0;
  var fall = 0;
  var wake = 0;

  for (var action in actions) {
    if (action.instruction.contains("shift")) {
      final regex = RegExp(r'#(\d+)');
      final id = int.parse(regex.firstMatch(action.instruction)!.group(1)!);

      if (!guards.map((element) => element.id).contains(id)) {
        guards.add(Guard(id));
      }

      currentId = id;
    } else if (action.instruction.contains("falls")) {
      fall = action.dateTime.minute;
    } else if (action.instruction.contains("wakes")) {
      wake = action.dateTime.minute;

      for (var guard in guards) {
        if (guard.id == currentId) {
          guard.fallAsleepTime += (wake - fall);

          for (var i = fall; i < wake; i++) {
            guard.minutes[i]++;
          }

          break;
        }
      }
    }
  }

  guards.sort((a, b) => a.fallAsleepTime.compareTo(b.fallAsleepTime));

  final result = guards.last;

  print(result.minutes.indexOf(result.minutes.reduce(max)) * result.id);
}
