# import sys; sys.stdin = open("input.txt")

profit = 0
def calc_max_profit(_row_index, _col_index, _col_last_index, _limit, _sum, _result) :
    global profit
    if _sum > _limit : return
    if _col_index == _col_last_index :
        profit = max(profit, _result)
        return
    value = arr[_row_index][_col_index]
    calc_max_profit(_row_index, _col_index + 1, _col_last_index, _limit, _sum+value, _result+value**2)
    calc_max_profit(_row_index, _col_index + 1, _col_last_index, _limit, _sum, _result)

def solution(_limit) -> int:
    global profit
    max_profit = 0
    for first_row_index in range(n - 1):
        for first_col_index in range(n):
            if first_col_index + m > n: break
            calc_max_profit(first_row_index, first_col_index, first_col_index + m, _limit, 0, 0)
            profit1 = profit
            profit = 0
            for second_row_index in range(first_row_index + 1, n):
                for second_col_index in range(n):
                    if second_col_index + m > n: break
                    calc_max_profit(second_row_index, second_col_index, second_col_index + m, _limit, 0, 0)
                    profit2 = profit
                    profit = 0
                    max_profit = max(max_profit, profit1 + profit2)
    return max_profit

T = int(input())
for t in range(1,T+1):
    n, m, c = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    answer = solution(c)
    print(f"#{t} {answer}")
