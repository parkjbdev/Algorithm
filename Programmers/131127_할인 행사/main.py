#  자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우
from collections import defaultdict

def solution(want, number, discount):
    answer = 0
    discount_cnt = defaultdict(int)
    
    for i in range(10):
        discount_cnt[discount[i]] += 1
    
    for i in range(len(discount) - 9):
        for wanted_item, wanted_cnt in zip(want, number):
            if discount_cnt[wanted_item] < wanted_cnt:
                break
        else: answer += 1
        if i + 10 >= len(discount): break
        
        discount_cnt[discount[i]] -= 1
        discount_cnt[discount[i + 10]] += 1
    
    return answer
