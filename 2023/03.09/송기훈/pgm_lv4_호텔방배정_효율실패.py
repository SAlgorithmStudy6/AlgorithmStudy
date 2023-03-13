from collections import deque

# 채점 결과
# 정확성: 78.8
# 효율성: 0.0

# 1


def solution(k, room_number):
    rooms = [i for i in range(1, k+1)]
    queue = deque(rooms)
    answer = []

    for number in room_number:
        room_list = list(queue)
        filtered_room = filter(lambda x: x >= number, room_list)
        selected = min(filtered_room)
        answer.append(selected)
        queue.remove(selected)

    return answer

# 2


def solution(k, room_number):
    answer = []

    for number in room_number:
        idx = number
        while True:
            if idx not in answer:
                answer.append(idx)
                break
            else:
                idx += 1
    return answer


solution(10, [1, 3, 4, 1, 3, 1])
