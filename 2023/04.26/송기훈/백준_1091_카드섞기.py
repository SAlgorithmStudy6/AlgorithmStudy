import sys

sys.stdin = open("input.txt", "r", encoding="UTF-8")

n = int(input())
p = list(map(int, input().split()))  # 결과가 될 배열
s = list(map(int, input().split()))
answer = 0

card = []

for i in range(n):
    card.append(i)

while True:
    flag = False

    for i in range(n):
        # 결과와 같지 않으면 섞어준다
        if card[i] % 3 != p[i]:
            answer += 1
            temp = []

            # j번째 카드는 s[j]로 이동한다
            for j in range(n):
                # 이동한 카드가 자기 자리로 이동하지 않으면 flag는 True
                # 증명
                # https://injae-kim.github.io/problem_solving/2020/02/20/baekjoon-1091.html
                if s[card[j]] != j:
                    flag = True

                temp.append(s[card[j]])

            card = temp

            break
    else:
        break

    if not flag:
        answer = -1
        break

print(answer)
