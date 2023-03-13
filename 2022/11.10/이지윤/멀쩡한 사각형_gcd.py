import math 

def gcd(a, b) :
    while b>0:
        a, b = b, a%b
    return a

def solution(w,h):
    answer = w*h
    gdc = gcd(w,h) #math.gcd(w,h)
    return answer - (w+h-gdc)
