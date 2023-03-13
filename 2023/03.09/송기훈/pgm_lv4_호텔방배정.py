import sys

sys.setrecursionlimit(10**8)


def find_empty(number, rooms):
    if number not in rooms:
        rooms[number] = number+1
        return number

    empty = find_empty(rooms[number], rooms)
    # 빈 방이 나오기 전 방문했던 number들 모두 empty + 1로 갱신
    rooms[number] = empty + 1
    return empty


def solution(k, room_number):
    answer = []
    # 해당 value는 다음 빈 방 번호 값
    # a방을 요구 했을 때 빈 방이 아니라면
    # 남은 빈 방 중 제일 작은 값으로 점프시키는 역할
    rooms = dict()

    for number in room_number:
        empty = find_empty(number, rooms)
        answer.append(empty)

    return answer


solution(10, [1, 3, 4, 1, 3, 1])
