import sys
sys.stdin = open("input.txt")

from itertools import permutations

MAX_PLAYER_NUM = 9
GET_SCORE = 4

def count_score(_arrange, _n, _arr) -> int:
    arrange_index, score, out, = 0, 0, 0
    for game_index in range(_n):
        field, out = [0]*4, 0
        while out<3:
            player_index = _arrange[arrange_index]
            play_hit = _arr[game_index][player_index]
            if play_hit==0 :
                out +=1
                field[0] = 0
            else :
                field[0] = 1
                for i in range(3, -1, -1):
                    if field[i] and i + play_hit >= GET_SCORE:
                        score += 1
                        field[i] = 0
                    if i + play_hit < GET_SCORE:
                        field[i + play_hit], field[i] = field[i], 0
            arrange_index = (arrange_index+1)%MAX_PLAYER_NUM
    return score

def solution(_n, _arr) -> int:
    max_count = 0
    for arrange in permutations([i for i in range(1,9)],8) :
        arrange_list = list(arrange)
        arrange_list.insert(3, 0)
        max_count = max(max_count, count_score(arrange_list, _n, _arr))
    return max_count


n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
answer = solution(n, arr)
print(answer)

