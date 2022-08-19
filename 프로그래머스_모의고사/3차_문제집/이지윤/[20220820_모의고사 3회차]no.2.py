# 빵 : 1, 야채 : 2, 고기 : 3 => 유일한 포장 조건 : 1-3-2-1
HAMBURGER_LEN = 4

def checkIsHamburger(_stack) -> bool:
    if len(_stack)<HAMBURGER_LEN : return False
    if _stack[-1]==1 and _stack[-2]==3 and _stack[-3]==2 and  _stack[-4]==1 :
        for _ in range(HAMBURGER_LEN):
            _stack.pop()
        return True
    else : return False

def solution(ingredient):
    answer = 0
    stack = []
    for element in ingredient:
        if not stack and element==1 : stack.append(element)
        if stack :
            stack.append(element)
            if element==1 and checkIsHamburger(stack):
                answer += 1
    return answer
