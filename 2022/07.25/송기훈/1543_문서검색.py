text = input()
search = input()

# 012345678
# ababababa	len = 9
# abaxabaxx	len = 3
# i = 6일때까지만 체크

i = 0
count = 0

# i가 range를 차례로 도는 것이 아니라
# 값을 불규칙적으로 점프해야 되므로 while 사용
while i <= (len(text) - len(search)):
	text_sub = text[i: i + len(search)]
	if (text_sub == search):
		count += 1
		i += len(search)
	else:
		i += 1
print(count)

