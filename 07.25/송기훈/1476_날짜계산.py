E, S, M = map(int, input().split())

# 조건문에서 생기게 될 오류 방지
if E == 15:
	E = 0
if S == 28:
	S = 0
if M == 19:
	M = 0

i = 0

while (True):
	result = 15 * i + E
	if (result % 28 == S and result % 19 == M):
		if (result != 0):
			break
		else:
			i += 1
	else:
		i += 1

print(result)
