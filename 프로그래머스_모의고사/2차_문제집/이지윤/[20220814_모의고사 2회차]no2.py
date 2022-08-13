def count_not_zero(_arr) -> int:
    count = 0
    for e in _arr:
        if e>0: count +=1
    return count

def check_available(_arr_a, _arr_b) -> bool:
    val_exist_a = count_not_zero(_arr_a)
    val_exist_b = count_not_zero(_arr_a)
    if val_exist_a == val_exist_b : return True
    return False

def make_counting_arr(_topping, _max_val) -> list:
    arr = [0]*(_max_val+1)     # 또는 10,000
    for element in _topping:
        arr[element] += 1
    return arr

def solution(topping : list):
    answer, max_val = 0, max(topping)
    count_a, count_b = [0]*(max_val+1), make_counting_arr(topping, max_val)
    cake_a, cake_b = [], topping
    for i in range(len(topping)-1):
        moving_element = cake_b.pop()
        cake_a.append(moving_element)
        count_a[moving_element] += 1
        count_b[moving_element] -= 1
        if check_available(count_a, count_b) : answer += 1
    return answer


print(solution([1,2,1,3,1,4,1,2]))
