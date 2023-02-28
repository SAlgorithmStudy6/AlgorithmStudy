from collections import deque


def solution(operations):
    answer = []
    q = deque()
    for operation in operations:
        action, num = operation.split()
        if (action == "I"):
            q.append(int(num))
        elif (action == "D"):
            # if q is not empty
            if q:
                if (num == "1"):
                    q.remove(max(q))
                elif (num == "-1"):
                    q.remove(min(q))

    # if q is not empty
    if q:
        answer = [max(q), min(q)]
    else:
        answer = [0, 0]
    return answer


print(solution(["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]))
print(solution(["I -45", "I 653", "D 1", "I -642",
      "I 45", "I 97", "D 1", "D -1", "I 333"]))
