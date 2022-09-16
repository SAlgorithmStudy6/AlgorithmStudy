import sys; sys.stdin = open("input.txt"); sys.setrecursionlimit(150000)

def divide_selected(_selected_arr) -> (list, list):
    selected_arr, not_selected_arr = [], []
    for index in range(n):
        if _selected_arr[index] : selected_arr.append(index)
        else : not_selected_arr.append(index)
    return selected_arr, not_selected_arr

def calculate_synergy(_arr) -> int:
    synergy_sum = 0; len_arr = len(_arr)
    for i1 in range(len_arr-1):
        for i2 in range(i1, len_arr):
            index1, index2 = _arr[i1], _arr[i2]
            synergy_sum += info_arr[index1][index2] + info_arr[index2][index1]
    return synergy_sum

def combination(_index, _selected_n, _selected_arr : list) :
    global answer
    if comb_count > comb_total_num//2: return
    if _selected_n==n//2:
        food_a, food_b = divide_selected(_selected_arr)
        synergy_a, synergy_b = calculate_synergy(food_a), calculate_synergy(food_b)
        answer = min(answer, abs(synergy_a - synergy_b))
        return
    if _index==n-1:
        return
    _selected_arr[_index] = True
    combination(_index + 1, _selected_n + 1, _selected_arr)
    _selected_arr[_index] = False
    combination(_index+1, _selected_n, _selected_arr)

# ------------------------------------------------------------------------------------------------------------------------------------------------------------------------

def combination_num(_n, _r) -> int:
    total_num = 1
    for num in range(_n, _n-_r, -1):
        total_num*=num
    for num in range(_n-_r, 0, -1):
        total_num/=num
    return int(total_num)

T = int(input())
for t in range(1, T+1):
    n = int(input())
    info_arr = [list(map(int, input().split())) for _ in range(n)]
    food_list = []
    selected_total_n, selected = 0, [False]*n
    comb_total_num = combination_num(n, n // 2); comb_count = 0
    answer = float("inf")
    combination(0, 0, selected)
    print(f"#{t} {answer}")
