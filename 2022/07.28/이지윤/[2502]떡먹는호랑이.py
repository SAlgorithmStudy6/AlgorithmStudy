#------------------------------------------------------------------------------------------------
def is_fibo(_arr, _d) -> bool:
    for i in range(3, _d) :
        _arr[i] = _arr[i - 1] + _arr[i - 2]
    if _arr[_d] == _arr[_d - 1] + _arr[_d - 2] : return True
    else : return False


def solution(_arr, _d, _limit) -> (int, int):
    count = 1
    while count < _limit + 1:           # index <= limit
        for i in range(count, _limit + 1):  # index <= limit
            _arr[1], _arr[2] = count, i
            if is_fibo(_arr, _d): return _arr[1], _arr[2]
        count += 1
    return -1, -1


def main():
    d, k = map(int, input().split())
    arr = [0] * (d + 1)                  # 배열 선언
    arr[d] = k                           # 배열 값 대입
    limit = (k//(d-2))                   # 초기 값들 최대 범위 설정
    a, b = solution(arr,  d, limit)
    print(a, b, sep='\n')


# -- 실행 함수 ------------------------------------------------------------------------------------
if __name__ == '__main__':
    main()