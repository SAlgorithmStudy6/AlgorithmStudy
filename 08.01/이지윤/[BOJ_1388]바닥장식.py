# 문제 풀이의 핵심 : 중복을 세지 않아야 한다는 것! 이걸 아래와 같이 구현이 가능하다.
# 구현 방법 1. 걍 단순 DFS 돌리고, visited 체크를 하여 중복을 예외 처리 하기
# 구현 방법 2. [얘만 가능한 풀이] 최악의 경우가 되는 최대 수인 1짜리 나무판자로만 이루어진 경우에서 이어지는 경우를 뺴기
#            => 단, n개가 이어져 한 덩어리가 될 때, n이 아닌 n-1을 빼주어야 함을 유의할 것!
#            => 또한, 전체 답이 행들의 답의 합과 같다는 특징을 이용할 수 있다는 것을 참고할 것.

#------------------------------------------------------------------------------------------------
namu = ['-', '|']

def count(_type, _iter, _arr) -> int:
    _count = 0
    for i in range(_iter-1):
        if _arr[i] == namu[_type] and _arr[i+1] == namu[_type]: _count += 1
    return _count

def solution(_n,_m,_arr) -> int:
    col_arr = list(map(list,zip(*_arr)))
    count_arr = [0, 0]
    for x in range(_n): count_arr[0] += count(0, _m, _arr[x])
    for x in range(_m): count_arr[1] += count(1, _n, col_arr[x])
    return _n*_m - sum(count_arr)

def main():
    n, m = map(int, input().split())
    decoration = [list(input()) for _ in range(n)]
    answer = solution(n, m, decoration)
    print(answer)

# -- 실행 함수 ------------------------------------------------------------------------------------
if __name__ == '__main__':
    main()