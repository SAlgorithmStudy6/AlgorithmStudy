
def solution(_n, _m, _arr) -> int:
    return 0


def main():
    n, m = map(int, input().split())
    n_list = [list(map(int, input().split())) for i in range(n)]
    answer = solution(n, m, n_list)
    print(answer)


# -- 실행 함수
if __name__ == '__main__':
    main()