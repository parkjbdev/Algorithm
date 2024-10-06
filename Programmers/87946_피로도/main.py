# answer = 0
# visited = []
# 
# def recursion(k, cnt, dungeons):
#     global visited, answer
#     
#     if k <= 0: return
#     answer = max(cnt, answer)
#     
#     for i, dungeon in enumerate(dungeons):
#         if not visited[i] and k >= dungeon[0]:
#             visited[i] = True
#             recursion(k - dungeon[1], cnt + 1, dungeons)
#             visited[i] = False
# 
# def solution(k, dungeons):
#     global visited, answer
#     
#     answer = 0
#     visited = [False for _ in range(len(dungeons))]
#     
#     recursion(k, 0, dungeons)
#     
#     return answer

from itertools import permutations

def solution(k, dungeons):
    answer = 0
    for dungeons in permutations(dungeons):
        temp_k = k
        cnt = 0
        for dungeon in dungeons:
            if temp_k >= dungeon[0]:
                temp_k -= dungeon[1]
                cnt += 1
            else: break
        answer = max(cnt, answer)
    
    return answer
